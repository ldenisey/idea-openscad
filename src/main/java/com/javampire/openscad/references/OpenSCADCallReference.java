package com.javampire.openscad.references;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.intellij.psi.search.GlobalSearchScope;
import com.javampire.openscad.psi.OpenSCADNamedElement;
import com.javampire.openscad.psi.OpenSCADResolvableElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class OpenSCADCallReference extends PsiReferenceBase<OpenSCADResolvableElement> implements PsiPolyVariantReference {

    private static final Logger LOG = Logger.getInstance(OpenSCADCallReference.class);

    private final String referencedName;

    public OpenSCADCallReference(@NotNull OpenSCADResolvableElement element, TextRange textRange) {
        super(element, textRange);
        referencedName = element.getName();
    }

    @NotNull
    @Override
    public ResolveResult[] multiResolve(boolean incompleteCode) {
        Project project = myElement.getProject();
        final OpenSCADReferenceResolver resolver = myElement.getReferenceResolver();
        if (resolver == null) {
            return ResolveResult.EMPTY_ARRAY;
        }
        final Collection<? extends OpenSCADNamedElement> elementResults = resolver.get(
                this.referencedName, project, GlobalSearchScope.allScope(project)
        );
        LOG.debug("multiResolve elementResults:" + elementResults);
        final List<ResolveResult> results = new ArrayList<>();
        for (OpenSCADNamedElement calledElement : elementResults) {
            results.add(new PsiElementResolveResult(calledElement));
        }
        LOG.debug("multiResolve results:" + results);
        return results.toArray(new ResolveResult[0]);
    }

    @Nullable
    @Override
    public PsiElement resolve() {
        LOG.debug("resolve called");
        final ResolveResult[] resolveResults = multiResolve(false);
        return resolveResults.length == 1 ? resolveResults[0].getElement() : null;
    }

    @Override
    public String toString() {
        return "OpenSCADCallReference(" + this.referencedName + ", " + getRangeInElement() + ")";
    }
}
