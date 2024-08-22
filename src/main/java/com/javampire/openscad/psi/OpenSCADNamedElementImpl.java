package com.javampire.openscad.psi;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.javampire.openscad.parser.OpenSCADParserTokenSets;
import org.jetbrains.annotations.NotNull;

import static com.javampire.openscad.parser.OpenSCADParserTokenSets.*;

public abstract class OpenSCADNamedElementImpl extends ASTWrapperPsiElement implements OpenSCADNamedElement {

    public OpenSCADNamedElementImpl(@NotNull ASTNode node) {
        super(node);
    }

    public static String getName(@NotNull final PsiElement element) {
        final ASTNode elementNode = element.getNode();
        final ASTNode nameNode = getNameNode(elementNode);
        if (nameNode == null) {
            return null;
        } else if (elementNode.getElementType() == OpenSCADTypes.IMPORT_PATH_REF) {
            return nameNode.getText().replaceAll("^.*/([^/]*)$", "$1");
        } else {
            return nameNode.getText();
        }
    }

    @Override
    public String getName() {
        return getName(this);
    }

    public static PsiElement setName(@NotNull final PsiElement element, @NotNull final String newName) {
        final ASTNode elementNode = element.getNode();
        final ASTNode nameNode = getNameNode(elementNode);
        if (nameNode == null || elementNode.getElementType() == OpenSCADTypes.IMPORT_PATH_REF) {
            return null;
        } else {
            final PsiElement newNameElement = OpenSCADElementFactory.createIdentifier(element.getProject(), newName);
            elementNode.replaceChild(nameNode, newNameElement.getNode());
        }
        return element;
    }

    @Override
    public PsiElement setName(@NotNull final String newName) {
        return setName(this, newName);
    }

    public static ASTNode getNameNode(@NotNull final ASTNode node) {
        if (OpenSCADParserTokenSets.NAMED_WITH_IDENTIFIER.contains(node.getElementType())) {
            return node.findChildByType(OpenSCADTypes.IDENTIFIER);
        } else if (node.getElementType() == OpenSCADTypes.BUILTIN_EXPR_REF) {
            return node.findChildByType(BUILTIN_EXPR_REF_KEYWORDS);
        } else if (node.getElementType() == OpenSCADTypes.BUILTIN_OBJ_REF) {
            return node.findChildByType(BUILTIN_OBJ_REF_KEYWORDS);
        } else if (node.getElementType() == OpenSCADTypes.BUILTIN_OVERRIDABLE_OBJ_REF) {
            return node.findChildByType(BUILTIN_OVERRIDABLE_OBJ_REF_KEYWORDS);
        } else if (node.getElementType() == OpenSCADTypes.BUILTIN_OVERRIDABLE_OP_REF) {
            return node.findChildByType(BUILTIN_OVERRIDABLE_OP_REF_KEYWORDS);
        } else if (node.getElementType() == OpenSCADTypes.BUILTIN_OVERRIDABLE_OP_AS_FUNCTION_REF) {
            return node.findChildByType(BUILTIN_OVERRIDABLE_OP_AS_FUNCTION_REF_KEYWORDS);
        } else if (node.getElementType() == OpenSCADTypes.TEST_EXP_REF) {
            return node.findChildByType(TEST_EXP_REF_KEYWORDS);
        } else if (node.getElementType() == OpenSCADTypes.COMMON_OP_REF) {
            return node.findChildByType(COMMON_OP_REF_KEYWORDS);
        } else if (node.getElementType() == OpenSCADTypes.IMPORT) {
            final PsiElement pathElement = PsiTreeUtil.findChildOfType(node.getPsi(), OpenSCADImportPathRef.class);
            if (pathElement != null) {
                return pathElement.getNode();
            }
            return node.findChildByType(OpenSCADTypes.IMPORT_PATH_REF);
        } else if (node.getElementType() == OpenSCADTypes.QUALIFICATION_EXPR) {
            final ASTNode lastChildNode = node.getLastChildNode();
            if (lastChildNode != null && lastChildNode.getElementType() == OpenSCADTypes.IDENTIFIER) {
                return lastChildNode;
            }
        }
        return null;
    }
}
