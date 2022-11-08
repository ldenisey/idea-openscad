package com.javampire.openscad.documentation;

import com.intellij.lang.ASTNode;
import com.intellij.lang.documentation.AbstractDocumentationProvider;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.util.PsiTreeUtil;
import com.javampire.openscad.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.regex.Pattern;

import static com.javampire.openscad.parser.OpenSCADParserTokenSets.DOC_IN_PARENT;

public class OpenSCADDocumentationProvider extends AbstractDocumentationProvider {

    private static final Logger LOG = Logger.getInstance(OpenSCADDocumentationProvider.class);

    private static final Pattern MULTILINE_PATTERN = Pattern.compile("\\R");

    @Override
    public String generateDoc(final PsiElement element, @Nullable final PsiElement originalElement) {
        LOG.debug("generateDoc called with: " + element + ", " + originalElement);
        return getDocString(element);
    }

    @Override
    @Nullable
    public String getQuickNavigateInfo(final PsiElement element, final PsiElement originalElement) {
        if (element instanceof OpenSCADModuleDeclaration) {
            return getLocationString(element) + "\nmodule " + OpenSCADPsiUtils.getNameWithArgumentList((OpenSCADNamedElement) element, true);
        } else if (element instanceof OpenSCADFunctionDeclaration) {
            return getLocationString(element) + "\nfunction " + OpenSCADPsiUtils.getNameWithArgumentList((OpenSCADNamedElement) element, true);
        } else if (element instanceof OpenSCADVariableDeclaration) {
            return getLocationString(element) + "\n" + element.getText();
        }
        return null;
    }

    private static String getLocationString(final PsiElement element) {
        final PsiFile file = element.getContainingFile();
        return file != null ? " [" + file.getName() + "]" : "";
    }

    @Nullable
    public static String getDocString(@Nullable final PsiElement element) {
        if (element == null) {
            return null;
        }
        final ASTNode node = element.getNode();
        if (node == null) {
            return null;
        }
        if (DOC_IN_PARENT.contains(node.getElementType())) {
            return getDocString(element.getParent());
        }
        final PsiElement docElement = PsiTreeUtil.skipWhitespacesBackward(element);
        if (docElement == null) {
            return null;
        }
        final ASTNode docNode = docElement.getNode();
        if (docNode == null) {
            return null;
        }
        String text = docElement.getText();
        if (text == null) {
            return null;
        }
        IElementType docNodeElementType = docNode.getElementType();
        if (docNodeElementType == OpenSCADTypes.COMMENT_SINGLELINE_BLOCK) {
            text = text.replaceAll("(?sm)^\\s*//", "");
        } else if (docNodeElementType != OpenSCADTypes.COMMENT_DOC) {
            text = null;
        } else {
            text = text.replaceFirst("(?s)^\\s*/\\*\\*", "");
            text = text.replaceFirst("(?s)\\s*\\*/\\s*$", "");
            text = text.replaceAll("(?sm)^\\s*\\*", "");
        }
        // If there's no documentation comment placed before the element, and if the element
        // is on one line with an end of line comment, take that comment as documentation
        if (text == null && !isMultiLine(element)) {
            final PsiElement nextComment = PsiTreeUtil.skipWhitespacesForward(element);
            if (nextComment == null) {
                return null;
            }
            final ASTNode commentNode = nextComment.getNode();
            if (commentNode == null) {
                return null;
            }
            if (commentNode.getElementType() == OpenSCADTypes.COMMENT_SINGLELINE) {
                for (PsiElement wsElement : PsiTreeUtil.getElementsOfRange(element, nextComment)) {
                    if (isMultiLine(wsElement)) {
                        return null;
                    }
                }
                text = commentNode.getText();
                text = text.replaceAll("(?sm)^\\s*//", "");
            }
        }
        if (text != null) {
            text = text.replaceAll("<", "&lt;");
            text = text.replaceAll(">", "&gt;");
            text = "<pre>" + text + "</pre>";
        }
        LOG.debug("Help text: " + text);
        return text;
    }

    private static boolean isMultiLine(@NotNull PsiElement element) {
        return MULTILINE_PATTERN.matcher(element.getText()).find();
    }
}
