package com.javampire.openscad.documentation;

import com.intellij.lang.documentation.AbstractDocumentationProvider;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.javampire.openscad.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class OpenSCADDocumentationProvider extends AbstractDocumentationProvider {

    private static final Logger LOG = Logger.getInstance(OpenSCADDocumentationProvider.class);

    @Override
    public @Nullable PsiElement getCustomDocumentationElement(@NotNull Editor editor, @NotNull PsiFile file, @Nullable PsiElement contextElement, int targetOffset) {
        LOG.debug("getCustomDocumentationElement called with: " + contextElement);
        return contextElement;
    }

    @Override
    public String generateDoc(PsiElement element, @Nullable PsiElement originalElement) {
        LOG.debug("generateDoc called with: " + element + ", " + originalElement);
        return OpenSCADPsiImplUtil.getDocString(element);
    }

    @Override
    @Nullable
    public String getQuickNavigateInfo(PsiElement element, PsiElement originalElement) {
        if (element instanceof OpenSCADModuleDeclaration) {
            return getLocationString(element) + "\nmodule " +
                    OpenSCADPsiImplUtil.getNameWithArgumentList((OpenSCADNamedElement) element, true);
        } else if (element instanceof OpenSCADFunctionDeclaration) {
            return getLocationString(element) + "\nfunction " +
                    OpenSCADPsiImplUtil.getNameWithArgumentList((OpenSCADNamedElement) element, true);
        } else if (element instanceof OpenSCADVariableDeclaration) {
            return getLocationString(element) + "\n" + element.getText();
        }
        return null;
    }

    private static String getLocationString(PsiElement element) {
        final PsiFile file = element.getContainingFile();
        return file != null ? " [" + file.getName() + "]" : "";
    }

}
