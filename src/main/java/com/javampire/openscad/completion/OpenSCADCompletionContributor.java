package com.javampire.openscad.completion;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.IdeActions;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.keymap.KeymapUtil;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ModifiableModelsProvider;
import com.intellij.openapi.roots.OrderRootType;
import com.intellij.openapi.roots.libraries.Library;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiErrorElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.ProcessingContext;
import com.javampire.openscad.BuiltinContributor;
import com.javampire.openscad.OpenSCADIcons;
import com.javampire.openscad.OpenSCADLanguage;
import com.javampire.openscad.psi.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.javampire.openscad.parser.OpenSCADParserTokenSets.*;

public class OpenSCADCompletionContributor extends CompletionContributor {
    private static final Logger LOG = Logger.getInstance(OpenSCADCompletionContributor.class);

    private static final String _FROM_ = " from ";

    private static List<LookupElement> builtinModules;
    private static List<LookupElement> builtinFunctions;
    private static List<LookupElement> globalLibrariesModulesAndFunctions;

    public OpenSCADCompletionContributor() {
        extend(
                CompletionType.BASIC,
                PlatformPatterns.psiElement().withLanguage(OpenSCADLanguage.INSTANCE),
                new CompletionProvider<>() {
                    @Override
                    protected void addCompletions(
                            @NotNull CompletionParameters parameters,
                            @NotNull ProcessingContext context,
                            @NotNull CompletionResultSet result) {

                        final Project project = parameters.getOriginalFile().getProject();
                        final PsiElement element = parameters.getPosition();

                        // No autocompletion when editing argument lists
                        if (OpenSCADTypes.ARG_DECLARATION == element.getParent().getNode().getElementType()) {
                            return;
                        }

                        // No autocompletion on literal
                        if (OpenSCADTypes.LITERAL_EXPR == element.getParent().getNode().getElementType()) {
                            return;
                        }

                        // No autocompletion for numbers
                        if ("".equals(result.getPrefixMatcher().getPrefix())) {
                            PsiElement previousElement = element.getParent().getPrevSibling();
                            if (previousElement != null) {
                                previousElement = previousElement.getLastChild();
                                if (previousElement instanceof PsiErrorElement) {
                                    previousElement = previousElement.getPrevSibling();
                                }
                                if (previousElement != null && "ERROR_ELEMENT".equals(previousElement.getNode().getElementType().toString())) {
                                    previousElement = element.getParent().getLastChild().getPrevSibling().getLastChild();
                                }
                                if (previousElement != null && OpenSCADTypes.NUMBER_LITERAL == previousElement.getNode().getElementType()) {
                                    return;
                                }
                            }
                        }


                        /*
                            Local declarations
                         */

                        // Add all accessible variables in the current file
                        addLocalVariables(result, element, null);
                        ProgressManager.checkCanceled();

                        // Add all parent arguments (from declaration lists)
                        addAccessibleArgumentDeclarations(result, element);
                        ProgressManager.checkCanceled();

                        // Add local custom modules
                        addLocalModules(result, element, null);
                        ProgressManager.checkCanceled();

                        // Add local custom functions
                        addLocalFunctions(result, element, null);
                        ProgressManager.checkCanceled();


                        /*
                            Imports declarations
                         */
                        for (final OpenSCADImport openSCADImport : OpenSCADPsiUtils.getImportStatementsFrom(element)) {
                            addFromImport(result, openSCADImport, new ArrayList<>());
                        }


                        /*
                            Builtins declarations
                         */

                        // Add builtin modules and functions
                        addBuiltinModules(project, result);
                        addBuiltinFunctions(project, result);
                        ProgressManager.checkCanceled();


                        /*
                            Second completion case
                         */
                        if (parameters.getInvocationCount() % 2 == 0) {
                            // Add all possible functions and method from global libraries
                            addGlobalLibrariesModulesAndFunctions(result, project);
                            result.addLookupAdvertisement("Press " + KeymapUtil.getFirstKeyboardShortcutText(ActionManager.getInstance().getAction(IdeActions.ACTION_CODE_COMPLETION)) + " to see accessible variables, functions and methods.");
                        } else {
                            result.addLookupAdvertisement("Press " + KeymapUtil.getFirstKeyboardShortcutText(ActionManager.getInstance().getAction(IdeActions.ACTION_CODE_COMPLETION)) + " to add non-imported functions and methods.");
                        }
                    }
                }
        );
    }

    /**
     * Add variables, functions and module from imported library.
     *
     * @param result         Result set.
     * @param openSCADImport Import declaration
     * @param analyzedFiles  List of already analyzed files, needed to avoid infinite recursions.
     */
    private void addFromImport(@NotNull final CompletionResultSet result, @NotNull final OpenSCADImport openSCADImport, @NotNull final List<PsiFile> analyzedFiles) {
        final OpenSCADImportPathRef importPathRef = OpenSCADPsiUtils.getImportPathRef(openSCADImport);
        for (final PsiFile psiFile : OpenSCADPsiUtils.getImportFiles(importPathRef)) {
            if (analyzedFiles.contains(psiFile)){
                continue;
            }

            // Add all accessible variables
            addImportAccessibleVariables(result, psiFile, importPathRef.getText());
            ProgressManager.checkCanceled();

            // Add all accessible functions
            addImportAccessibleFunctions(result, psiFile, importPathRef.getText());
            ProgressManager.checkCanceled();

            // Add all accessible modules
            addImportAccessibleModules(result, psiFile, importPathRef.getText());
            ProgressManager.checkCanceled();

            analyzedFiles.add(psiFile);

            // Recursive call
            for (final PsiFile innerImportFile : OpenSCADPsiUtils.getImportFiles(importPathRef)) {
                for (final OpenSCADImport innerOpenSCADImport : OpenSCADPsiUtils.getImportStatementsFrom(innerImportFile)) {
                    LOG.warn("in " + innerImportFile.getVirtualFile().getCanonicalPath() + ", import : " + innerOpenSCADImport.getText());
                    addFromImport(result, innerOpenSCADImport, analyzedFiles);
                }
            }
        }
    }

    /**
     * Get accessible variables declarations for the current element.
     *
     * @param result   Result set.
     * @param element  Psi element.
     * @param tailText Tail text to show in the result.
     */
    private void addLocalVariables(final CompletionResultSet result, final PsiElement element, final String tailText) {
        final List<OpenSCADVariableDeclaration> variableDeclarations = OpenSCADPsiUtils.getAccessibleVariableDeclarations(element);
        result.addAllElements(convertToLookupElements(variableDeclarations, null));
    }

    /**
     * Get accessible variables declarations from parent argument list declarations, i.e. variables declared in function or module parameters.
     *
     * @param result  Result set.
     * @param element Psi element.
     */
    private void addAccessibleArgumentDeclarations(final CompletionResultSet result, final PsiElement element) {
        // Parents with ARG_DECLARATION_LIST : modules and functions
        final List<PsiElement> argDeclarationParents = OpenSCADPsiUtils.getParentsOfType(element, WITH_ARG_DECLARATION_LIST);
        final List<OpenSCADArgDeclaration> argDeclarations = argDeclarationParents.stream()
                .map(e -> PsiTreeUtil.getChildOfType(e, OpenSCADArgDeclarationList.class))
                .filter(Objects::nonNull)
                .flatMap(e -> PsiTreeUtil.getChildrenOfTypeAsList(e, OpenSCADArgDeclaration.class).stream())
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        result.addAllElements(convertToLookupElements(argDeclarations, null));

        // Parents with FULL_ARG_DECLARATION_LIST : for loop
        final List<PsiElement> fullArgDeclarationParents = OpenSCADPsiUtils.getParentsOfType(element, WITH_FULL_ARG_DECLARATION_LIST);
        final List<PsiElement> fullArgDeclarations = fullArgDeclarationParents.stream()
                .map(e -> PsiTreeUtil.getChildOfType(e, OpenSCADFullArgDeclarationList.class))
                .filter(Objects::nonNull)
                .map(e -> PsiTreeUtil.getChildOfType(e, OpenSCADFullArgDeclaration.class))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        result.addAllElements(convertToLookupElements(fullArgDeclarations, null));

        // Parents previous sibling with FULL_ARG_DECLARATION_LIST : let declaration
        final List<PsiElement> letParent = OpenSCADPsiUtils.getParentsOfType(element, LET_PARENT);
        final List<PsiElement> letFullArgDeclaration = letParent.stream()
                .map(PsiElement::getFirstChild)
                .filter(e -> e instanceof OpenSCADLetOp)
                .map(e -> PsiTreeUtil.getChildOfType(e, OpenSCADFullArgDeclarationList.class))
                .filter(Objects::nonNull)
                .map(e -> PsiTreeUtil.getChildOfType(e, OpenSCADFullArgDeclaration.class))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        result.addAllElements(convertToLookupElements(letFullArgDeclaration, null));
    }

    /**
     * Get local module declarations.
     *
     * @param result   Result set.
     * @param element  Psi element.
     * @param tailText Tail text to display with completion result.
     */
    private void addLocalModules(final CompletionResultSet result, final PsiElement element, final String tailText) {
        final List<OpenSCADModuleDeclaration> moduleDeclarations = OpenSCADPsiUtils.getAccessibleModuleDeclarations(element.getContainingFile());
        result.addAllElements(convertToLookupElements(moduleDeclarations, tailText));
    }

    /**
     * Get local function declarations.
     *
     * @param result   Result set.
     * @param element  Psi element.
     * @param tailText Tail text to display with completion result.
     */
    private void addLocalFunctions(final CompletionResultSet result, final PsiElement element, final String tailText) {
        final List<OpenSCADFunctionDeclaration> functionDeclarations = OpenSCADPsiUtils.getAccessibleFunctionDeclarations(element.getContainingFile());
        result.addAllElements(convertToLookupElements(functionDeclarations, tailText));
    }

    /**
     * Get accessible variable declarations from included file.
     *
     * @param result     Result set.
     * @param psiFile    Included psi file.
     * @param importPath Import path as a string for completion details.
     */
    private void addImportAccessibleVariables(final CompletionResultSet result, final PsiFile psiFile, final String importPath) {
        final List<OpenSCADVariableDeclaration> varDeclarations = PsiTreeUtil.getChildrenOfTypeAsList(psiFile, OpenSCADVariableDeclaration.class);
        result.addAllElements(convertToLookupElements(varDeclarations, _FROM_ + importPath));
    }

    /**
     * Get accessible function declarations from included file.
     *
     * @param result     Result set.
     * @param psiFile    Included psi file.
     * @param importPath Import path as a string for completion details.
     */
    private void addImportAccessibleFunctions(final CompletionResultSet result, final PsiFile psiFile, final String importPath) {
        final List<OpenSCADFunctionDeclaration> funcDeclarations = PsiTreeUtil.getChildrenOfTypeAsList(psiFile, OpenSCADFunctionDeclaration.class);
        result.addAllElements(convertToLookupElements(funcDeclarations, _FROM_ + importPath));
    }

    /**
     * Get accessible modules declarations from included file.
     *
     * @param result     Result set.
     * @param psiFile    Included psi file.
     * @param importPath Import path as a string for completion details.
     */
    private void addImportAccessibleModules(final CompletionResultSet result, final PsiFile psiFile, final String importPath) {
        final List<OpenSCADModuleDeclaration> moduleDeclarations = PsiTreeUtil.getChildrenOfTypeAsList(psiFile, OpenSCADModuleDeclaration.class);
        result.addAllElements(convertToLookupElements(moduleDeclarations, _FROM_ + importPath));
    }

    private void addBuiltinModules(@NotNull final Project project, @NotNull final CompletionResultSet result) {
        if (builtinModules == null || builtinModules.isEmpty()) {
            // Add builtin modules
            final List<VirtualFile> builtinFiles = BuiltinContributor.INSTANCE.getModuleBuiltinsFile();
            builtinModules = OpenSCADPsiUtils.getPsiFiles(project, builtinFiles).parallelStream()
                    .map(psiFile -> LookupElementBuilder.create(psiFile.getVirtualFile().getNameWithoutExtension()).withIcon(OpenSCADIcons.MODULE))
                    .collect(Collectors.toList());
        }
        result.addAllElements(builtinModules);
    }

    private void addBuiltinFunctions(@NotNull final Project project, @NotNull final CompletionResultSet result) {
        if (builtinFunctions == null || builtinFunctions.isEmpty()) {
            // Add builtin functions
            final List<VirtualFile> builtinFiles = BuiltinContributor.INSTANCE.getFunctionBuiltinsFile();
            builtinFunctions = OpenSCADPsiUtils.getPsiFiles(project, builtinFiles).parallelStream()
                    .map(psiFile -> LookupElementBuilder.create(psiFile.getVirtualFile().getNameWithoutExtension()).withIcon(OpenSCADIcons.FUNCTION))
                    .collect(Collectors.toList());
        }
        result.addAllElements(builtinFunctions);
    }

    private void addGlobalLibrariesModulesAndFunctions(final CompletionResultSet result, final Project project) {
        if (globalLibrariesModulesAndFunctions == null || globalLibrariesModulesAndFunctions.isEmpty()) {
            globalLibrariesModulesAndFunctions = new ArrayList<>();

            // List global libraries paths
            final ModifiableModelsProvider modelsProvider = ApplicationManager.getApplication().getService(ModifiableModelsProvider.class);
            final Library[] librariesPathRoots = modelsProvider.getLibraryTableModifiableModel().getLibraries();
            final List<VirtualFile> librariesPaths = Arrays.stream(librariesPathRoots)
                    .map(libraryPathsRoot -> libraryPathsRoot.getFiles(OrderRootType.CLASSES))
                    .flatMap(Arrays::stream)
                    .collect(Collectors.toList());

            // For each global library path
            for (final VirtualFile librariesPath : librariesPaths) {
                // List libraries files
                final List<PsiFile> libraries = OpenSCADPsiUtils.getPsiFiles(project, VfsUtil.collectChildrenRecursively(librariesPath));
                for (final PsiFile psiFile : libraries) {
                    final String libraryRelPath = psiFile.getVirtualFile().getCanonicalPath().substring(librariesPath.getCanonicalPath().length() + 1);
                    final List<OpenSCADModuleDeclaration> moduleDeclarations = OpenSCADPsiUtils.getAccessibleModuleDeclarations(psiFile);
                    globalLibrariesModulesAndFunctions.addAll(convertToLookupElements(moduleDeclarations, _FROM_ + libraryRelPath));
                    final List<OpenSCADFunctionDeclaration> functionDeclarations = OpenSCADPsiUtils.getAccessibleFunctionDeclarations(psiFile);
                    globalLibrariesModulesAndFunctions.addAll(convertToLookupElements(functionDeclarations, _FROM_ + libraryRelPath));
                }
            }
        }
        result.addAllElements(globalLibrariesModulesAndFunctions);
    }

    private <T extends PsiElement> List<LookupElement> convertToLookupElements(final List<T> elements, final String tailText) {
        return elements.parallelStream()
                .map(OpenSCADPsiUtils::getPresentation)
                .map(presentation -> {
                    final String text = presentation.getPresentableText();
                    if (text == null) return null;
                    LookupElementBuilder builder = LookupElementBuilder.create(presentation.getPresentableText()).withIcon(presentation.getIcon(true));
                    if (tailText != null) {
                        builder = builder.appendTailText(tailText, true);
                    }
                    return builder;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
