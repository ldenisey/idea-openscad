package com.javampire.openscad.references;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Computable;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.stubs.AbstractStubIndex;
import com.intellij.psi.util.PsiTreeUtil;
import com.javampire.openscad.BuiltinContributor;
import com.javampire.openscad.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OpenSCADReference extends PsiReferenceBase<OpenSCADResolvableElement> implements PsiPolyVariantReference {

    private final Project project;
    private final String referencedName;
    private final PsiFile myElementFile;

    public OpenSCADReference(@NotNull final OpenSCADResolvableElement element, final TextRange rangeInElement) {
        super(element, rangeInElement);
        myElementFile = element.getContainingFile();
        project = element.getProject();
        if (element instanceof OpenSCADImportPathRef)
            referencedName = element.getText();
        else
            referencedName = element.getName();
    }

    @NotNull
    @Override
    public ResolveResult[] multiResolve(boolean incompleteCode) {
        if (myElement instanceof OpenSCADImportPathRef) {
            return OpenSCADPsiUtils.getImportFiles((OpenSCADImportPathRef) myElement).parallelStream()
                    .map(PsiElementResolveResult::new)
                    .toArray(ResolveResult[]::new);
        } else {
            // Only variables, functions and modules left here. All are stubbed, getting them with their indices.
            final AbstractStubIndex<String, ? extends OpenSCADNamedElement> resolver = myElement.getStubIndex();
            if (resolver == null) {
                return ResolveResult.EMPTY_ARRAY;
            }

            return resolver.get(referencedName, project, GlobalSearchScope.allScope(project))
                    .parallelStream()
                    .map(namedElement -> {
                        // Results that are not directly accessible (out of scope or missing import) are marked as invalid
                        final PsiFile namedElementFile = namedElement.getContainingFile();
                        if (namedElementFile.getVirtualFile().getCanonicalPath().contains(BuiltinContributor.BUILTIN_PATH)) {
                            // Builtins are always accessible
                            return new PsiElementResolveResult(namedElement, true);
                        } else if (myElementFile.equals(namedElementFile)) {
                            // Result in the same file as this, is it accessible scope-wise ?
                            boolean isAccessible = OpenSCADPsiUtils.isAccessibleFrom(namedElement, myElement);
                            return new PsiElementResolveResult(namedElement, isAccessible);
                        } else {
                            // Result is from another file, is there an import statement ?
                            final List<PsiFile> imports = OpenSCADPsiUtils.getImportedFileFrom(myElement);
                            return new PsiElementResolveResult(namedElement, imports.contains(namedElementFile));
                        }
                    })
                    .toArray(ResolveResult[]::new);
        }
    }

    /**
     * Returns the results if only one is found. Returning null in case of multiple responses allow documentation and
     * references endpoints to deal with it differently.
     *
     * @return Single result or null.
     */
    @Nullable
    @Override
    public PsiElement resolve() {
        // Filtering invalid, i.e. out of scope results
        final List<ResolveResult> resolveResults = Arrays.stream(multiResolve(false))
                .filter(ResolveResult::isValidResult)
                .collect(Collectors.toList());

        if (resolveResults.size() < 1)
            return null;
        else if (resolveResults.size() == 1)
            return resolveResults.get(0).getElement();

        // Filtering results based : the closest local declaration or import statement wins
        return resolveResults.parallelStream()
                .filter(ResolveResult::isValidResult)
                .flatMap(curResult -> {
                    PsiElement curElement = curResult.getElement();
                    final PsiFile curResultFile = curElement.getContainingFile();
                    if (!myElementFile.equals(curResultFile)) {
                        if (curResultFile.getVirtualFile().getCanonicalPath().contains(BuiltinContributor.BUILTIN_PATH)) {
                            // Builtins
                            return Map.of(curResult, -1).entrySet().stream();
                        } else {
                            // Imports should be evaluated from their statement
                            curElement = OpenSCADPsiUtils.getImportStatementFrom(curResultFile, myElement);
                        }
                    }

                    // Local match gets their weight from the distance to myElement
                    final PsiElement curResultParent = OpenSCADPsiUtils.getParentScope(curElement);
                    final PsiElement commonParent = ApplicationManager.getApplication().runReadAction(new Computable<PsiElement>() {
                        @Override
                        public PsiElement compute() {
                            return PsiTreeUtil.findCommonParent(curResultParent, myElement);
                        }
                    });
                    if (commonParent == null) {
                        // Not accessible from myElement, should have been set as invalid already
                        return Map.of(curResult, Integer.MIN_VALUE).entrySet().stream();
                    } else {
                        // Adding 1 for local match, to prioritize them over builtins and imports
                        return Map.of(curResult, OpenSCADPsiUtils.getScopeDepth(commonParent)).entrySet().stream();
                    }
                })
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .map(ResolveResult::getElement)
                .orElse(null);
    }
}
