package com.javampire.openscad.editor;

import com.intellij.lang.CodeDocumentationAwareCommenterEx;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.javampire.openscad.psi.OpenSCADTypes;
import org.jetbrains.annotations.Nullable;

public class OpenSCADCommenter implements CodeDocumentationAwareCommenterEx {

    private static final Logger LOG = Logger.getInstance(OpenSCADCommenter.class);

    @Override
    public String getLineCommentPrefix() {
        return "//";
    }

    @Override
    public String getBlockCommentPrefix() {
        return "/*";
    }

    @Override
    public String getBlockCommentSuffix() {
        return "*/";
    }

    @Override
    public String getCommentedBlockCommentPrefix() {
        return null;
    }

    @Override
    public String getCommentedBlockCommentSuffix() {
        return null;
    }

    @Override
    @Nullable
    public IElementType getLineCommentTokenType() {
        return OpenSCADTypes.COMMENT_SINGLELINE;
    }

    @Override
    @Nullable
    public IElementType getBlockCommentTokenType() {
        return OpenSCADTypes.COMMENT_C_STYLE;
    }

    @Override
    @Nullable
    public IElementType getDocumentationCommentTokenType() {
        return OpenSCADTypes.COMMENT_DOC;
    }

    @Override
    public String getDocumentationCommentPrefix() {
        return "/**";
    }

    @Override
    public String getDocumentationCommentLinePrefix() {
        return "*";
    }

    @Override
    public String getDocumentationCommentSuffix() {
        return "*/";
    }

    @Override
    public boolean isDocumentationComment(final PsiComment element) {
        LOG.debug("isDocumentationComment called with: " + element);
        return false;
    }

    @Override
    public boolean isDocumentationCommentText(final PsiElement element) {
        LOG.debug("isDocumentationCommentText called with: " + element);
        return false;
    }
}
