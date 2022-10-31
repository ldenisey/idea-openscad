package com.javampire.openscad.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtil;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Computable;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FilenameIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.ProjectScope;
import com.intellij.psi.tree.TokenSet;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.IncorrectOperationException;
import com.intellij.util.PlatformIcons;
import com.javampire.openscad.OpenSCADFileType;
import com.javampire.openscad.OpenSCADIcons;
import com.javampire.openscad.parser.OpenSCADParserTokenSets;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class OpenSCADPsiUtils {

    public static ItemPresentation getPresentation(@NotNull final PsiElement element) {
        return new ItemPresentation() {
            @Nullable
            @Override
            public String getPresentableText() {
                if (element instanceof OpenSCADNamedElement) {
                    return ((OpenSCADNamedElement) element).getName();
                }
                return null;
            }

            @Override
            public String getLocationString() {
                return element.getContainingFile().getName();
            }

            @Nullable
            @Override
            public Icon getIcon(boolean unused) {
                if (element.getNode().getElementType() == OpenSCADTypes.MODULE_DECLARATION) {
                    return OpenSCADIcons.MODULE;
                }
                if (element.getNode().getElementType() == OpenSCADTypes.FUNCTION_DECLARATION) {
                    return OpenSCADIcons.FUNCTION;
                }
                if (element.getNode().getElementType() == OpenSCADTypes.VARIABLE_DECLARATION) {
                    return PlatformIcons.VARIABLE_ICON;
                }
                if (element.getNode().getElementType() == OpenSCADTypes.IMPORT) {
                    return OpenSCADIcons.FILE;
                }
                return null;
            }
        };
    }

    public static PsiElement setName(@NotNull final PsiElement element, final String newName) {
        if (OpenSCADParserTokenSets.NON_RENAMABLE_ELEMENTS.contains(element.getNode().getElementType())) {
            throw new IncorrectOperationException("Builtin functions/modules can't be renamed");
        }
        return element;
    }

    public static PsiElement getNameIdentifier(@NotNull final PsiElement element) {
        final ASTNode nameNode = OpenSCADNamedElementImpl.getNameNode(element.getNode());
        if (nameNode != null) {
            return nameNode.getPsi();
        }
        return null;
    }

    /**
     * Builds the declaration of a module or a function, consisting of name + argument list
     *
     * @param element a module or a function
     * @return name(arg1, ...)
     */
    public static String getNameWithArgumentList(@NotNull final OpenSCADNamedElement element, boolean shortForm) {
        StringBuilder buf = new StringBuilder();
        buf.append(element.getName());
        final ASTNode argListNode = element.getNode().findChildByType(OpenSCADTypes.ARG_DECLARATION_LIST);
        if (argListNode == null) {
            buf.append("()");
        } else if (argListNode.getTextLength() > 100 && shortForm) {
            buf.append("(...)");
        } else {
            buf.append(argListNode.getText());
        }
        return buf.toString();
    }

    /**
     * Recursively get all variables declaration accessible to <code>element</code>, i.e. defined in its parent scopes.
     *
     * @param element Element for which accessible variables will be returned.
     * @return List of accessible variable declarations.
     */
    public static List<OpenSCADVariableDeclaration> getAccessibleVariableDeclarations(@NotNull final PsiElement element) {
        return getAccessibleElements(element, OpenSCADVariableDeclaration.class);
    }

    /**
     * Recursively get all module declaration accessible to <code>element</code>, i.e. defined in its parent scopes.
     *
     * @param element Element for which accessible modules will be returned.
     * @return List of accessible module declarations.
     */
    public static List<OpenSCADModuleDeclaration> getAccessibleModuleDeclarations(@NotNull final PsiElement element) {
        return getAccessibleElements(element, OpenSCADModuleDeclaration.class);
    }

    /**
     * Recursively get all function declaration accessible to <code>element</code>, i.e. defined in its parent scopes.
     *
     * @param element Element for which accessible modules will be returned.
     * @return List of accessible function declarations.
     */
    public static List<OpenSCADFunctionDeclaration> getAccessibleFunctionDeclarations(@NotNull final PsiElement element) {
        return getAccessibleElements(element, OpenSCADFunctionDeclaration.class);
    }

    /**
     * Get all <code>aClass</code> type element in <code>element</code> scope and its parent scopes.
     *
     * @param element Element from which to research the accessible elements.
     * @param aClass  Class of the searched elements type.
     * @return List of accessible element, might be empty.
     */
    private static <T extends PsiElement> @NotNull List<T> getAccessibleElements(@Nullable final PsiElement element, @NotNull final Class<? extends T> aClass) {
        final List<T> elements = new ArrayList<>();
        PsiElement curElement = null;
        if (element instanceof PsiFile) {
            curElement = element;
            elements.addAll(PsiTreeUtil.getChildrenOfTypeAsList(curElement, aClass));
        } else {
            curElement = getParentScope(element);
            while (curElement != null) {
                elements.addAll(PsiTreeUtil.getChildrenOfTypeAsList(curElement, aClass));
                curElement = getParentScope(curElement);
            }
        }
        return elements;
    }

    /**
     * Recursively get all parents of types elementTypes.
     *
     * @param element      Element for which matching parents will be returned.
     * @param elementTypes Allowed parent types.
     * @return List of matching parents.
     */
    public static List<PsiElement> getParentsOfType(@Nullable PsiElement element, @NotNull final TokenSet elementTypes) {
        List<PsiElement> matchingParents = new ArrayList<>();
        if (element != null && !(element instanceof PsiFileBase)) {
            element = element.getParent();
        }
        while (element != null) {
            if (elementTypes.contains(element.getNode().getElementType())) {
                matchingParents.add(element);
            }
            element = element.getParent();
        }
        return matchingParents;
    }

    /**
     * Get the parent scope of the given element.
     *
     * @param element Element within scope.
     * @return Element starting element parent scope.
     */
    public static PsiElement getParentScope(@NotNull final PsiElement element) {
        if (element instanceof PsiFile) {
            return null;
        } else {
            return ApplicationManager.getApplication().runReadAction((Computable<PsiElement>) () -> {
                final PsiElement parentElement = PsiTreeUtil.getParentOfType(element, OpenSCADBlockObj.class, true);
                return parentElement != null ? parentElement : element.getContainingFile();
            });
        }
    }

    /**
     * Returns the depth, i.e. the number of scopes surrounding element from root file.
     *
     * @param element Element.
     * @return Number of scopes.
     */
    public static Integer getScopeDepth(@NotNull final PsiElement element) {
        Integer depth = 0;
        PsiElement curElement = element;
        while (true) {
            final PsiElement parentElement = getParentScope(curElement);
            if (parentElement == null) {
                return depth;
            } else {
                depth++;
                curElement = parentElement;
            }
        }
    }

    /**
     * Returns true if <code>element</code> is accessible from <code>callingElement</code>, false otherwise.
     * <code>element</code> is accessible from <code>callingElement</code> if it is defined in one of the parent scopes of <code>callingElement</code>.
     *
     * @param element        Element to be accessed.
     * @param callingElement Element potentially accessing <code>element</code>.
     * @return True or false.
     */
    public static boolean isAccessibleFrom(@NotNull final PsiElement element, @NotNull final PsiElement callingElement) {
        final PsiElement elementParent = getParentScope(element);
        PsiElement callingElementParent = getParentScope(callingElement);
        while (callingElementParent != null) {
            if (callingElementParent.equals(elementParent)) {
                return true;
            }
            callingElementParent = getParentScope(callingElementParent);
        }
        return false;
    }

    public static OpenSCADImportPathRef getImportPathRef(@NotNull final OpenSCADImport openSCADImport) {
        OpenSCADImportPathRef importPathRef = null;
        final OpenSCADIncludeImport include = openSCADImport.getIncludeImport();
        if (include != null)
            importPathRef = include.getImportPathRef();
        final OpenSCADUseImport use = openSCADImport.getUseImport();
        if (use != null)
            importPathRef = use.getImportPathRef();

        return importPathRef;
    }

    /**
     * Return all import (include or use) statements accessible from <code>element</code>, i.e. in one of <code>element</code>
     * parent blocks.
     *
     * @param element Element searching all accessible imports.
     * @return List of accessible imports statements.
     */
    public static List<OpenSCADImport> getImportStatementsFrom(@NotNull final PsiElement element) {
        final List<OpenSCADImport> imports = new ArrayList<>();
        PsiElement curElement = null;
        if (element instanceof PsiFile) {
            curElement = element;
            imports.addAll(PsiTreeUtil.getChildrenOfTypeAsList(curElement, OpenSCADImport.class));
        } else {
            curElement = getParentScope(element);
            while (curElement != null) {
                imports.addAll(PsiTreeUtil.getChildrenOfTypeAsList(curElement, OpenSCADImport.class));
                curElement = getParentScope(curElement);
            }
        }
        return imports;
    }

    /**
     * return the import statement of a given file, or null if it is not available for <code>element</code>.
     *
     * @param importedFile Imported file.
     * @param element      Element that should have access to <code>importedFile</code>.
     * @return The import object else null.
     */
    public static OpenSCADImport getImportStatementFrom(@NotNull final PsiFile importedFile, @NotNull final PsiElement element) {
        PsiElement elementParent = getParentScope(element);
        while (elementParent != null) {
            final List<OpenSCADImport> imports = PsiTreeUtil.getChildrenOfTypeAsList(elementParent, OpenSCADImport.class);
            for (final OpenSCADImport curImport : imports) {
                final OpenSCADImportPathRef importPathRef = getImportPathRef(curImport);
                if (importPathRef != null) {
                    final VirtualFile file = ApplicationManager.getApplication().runReadAction(new Computable<VirtualFile>() {
                        @Override
                        public VirtualFile compute() {
                            return VfsUtil.findRelativeFile(importPathRef.getText(), element.getContainingFile().getVirtualFile());
                        }
                    });
                    if (importedFile.getVirtualFile().equals(file))
                        return curImport;
                }
            }
            elementParent = getParentScope(elementParent);
        }
        return null;
    }

    /**
     * Return imported (include or use) files accessible from <code>element</code>, i.e. in one of <code>element</code>
     * parent blocks.
     *
     * @param element Element searching all imported files.
     * @return List of imported files.
     */
    public static List<PsiFile> getImportedFileFrom(@NotNull final PsiElement element) {
        return getImportStatementsFrom(element).parallelStream()
                .flatMap(openSCADImport -> {
                    final OpenSCADImportPathRef importPathRef = getImportPathRef(openSCADImport);
                    if (importPathRef != null) {
                        return getImportFiles(importPathRef).stream();
                    }
                    return null;
                })
                .collect(Collectors.toList());
    }

    /**
     * Get the list of files matching the import statement.
     * Should not return multiple files, but project configuration can lead to it.
     *
     * @param importPathRef OpenSCADImportPathRef object containing import path string.
     * @return List of files.
     */
    public static List<PsiFile> getImportFiles(@NotNull final OpenSCADImportPathRef importPathRef) {
        final String path = ApplicationManager.getApplication().runReadAction(new Computable<String>() {
            @Override
            public String compute() {
                return importPathRef.getText();
            }
        });
        final Module module = ApplicationManager.getApplication().runReadAction(new Computable<Module>() {
            @Override
            public Module compute() {
                return ModuleUtil.findModuleForPsiElement(importPathRef);
            }
        });
        List<PsiFile> fileList;
        if (module != null) {
            // If it is an external library
            fileList = findModuleLibrary(module, path);
            if (fileList.isEmpty()) {
                // If not, might be inner module file
                fileList = findModuleContentFile(module, path);
            }
        } else {
            final Project project = ApplicationManager.getApplication().runReadAction(new Computable<Project>() {
                @Override
                public Project compute() {
                    return importPathRef.getProject();
                }
            });
            // If it is an external library
            fileList = findProjectLibrary(project, path);
            if (fileList.isEmpty()) {
                // If not, might be inner module file
                fileList = findProjectContentFile(project, path);
            }
        }
        return fileList;
    }

    public static List<PsiFile> findModuleContentFile(@NotNull final Module module, @NotNull final String fileRelativePath) {
        return findFilesByRelativePath(module.getProject(), module.getModuleContentScope(), fileRelativePath);
    }

    public static List<PsiFile> findModuleLibrary(@NotNull final Module module, @NotNull final String fileRelativePath) {
        return findFilesByRelativePath(module.getProject(), module.getModuleWithLibrariesScope(), fileRelativePath);
    }

    public static List<PsiFile> findProjectContentFile(@NotNull final Project project, @NotNull final String fileRelativePath) {
        return findFilesByRelativePath(project, ProjectScope.getContentScope(project), fileRelativePath);
    }

    public static List<PsiFile> findProjectLibrary(@NotNull final Project project, @NotNull final String fileRelativePath) {
        return findFilesByRelativePath(project, ProjectScope.getLibrariesScope(project), fileRelativePath);
    }

    public static List<PsiFile> findFilesByRelativePath(@NotNull final Project project, @NotNull final GlobalSearchScope scope, @NotNull final String fileRelativePath) {
        final String name = new File(fileRelativePath).getName();
        final List<PsiFile> fileList = new ArrayList<>();
        ApplicationManager.getApplication().runReadAction(() -> {
            final Collection<VirtualFile> potentialFiles = FilenameIndex.getVirtualFilesByName(name, scope);
            final String relativePath = fileRelativePath.startsWith("/") ? fileRelativePath : "/" + fileRelativePath;
            for (final VirtualFile f : potentialFiles) {
                if (f.getPath().endsWith(relativePath)) {
                    fileList.add(PsiManager.getInstance(project).findFile(f));
                }
            }
        });
        return fileList;
    }

    public static List<PsiFile> getPsiFiles(@NotNull final Project project, final List<VirtualFile> vFiles) {
        if (vFiles == null || vFiles.isEmpty()) {
            return Collections.emptyList();
        }

        final PsiManager psiManager = PsiManager.getInstance(project);
        return vFiles.parallelStream()
                .filter(virtualFile -> !virtualFile.isDirectory())
                .map(virtualFile ->
                        ApplicationManager.getApplication().runReadAction((Computable<PsiFile>) () ->
                                psiManager.findFile(virtualFile))
                )
                .filter(Objects::nonNull)
                .filter(PsiElement::isValid)
                .filter(psiFile -> psiFile.getFileType() == OpenSCADFileType.INSTANCE)
                .collect(Collectors.toList());
    }
}
