package com.javampire.openscad.formatter;

import com.intellij.formatting.Indent;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.tree.IElementType;

import static com.javampire.openscad.parser.OpenSCADParserTokenSets.BRACES_TOKENS;
import static com.javampire.openscad.parser.OpenSCADParserTokenSets.MATHEMATICAL_EXPR;
import static com.javampire.openscad.psi.OpenSCADTypes.ARG_ASSIGNMENT_LIST;
import static com.javampire.openscad.psi.OpenSCADTypes.ARG_DECLARATION_LIST;
import static com.javampire.openscad.psi.OpenSCADTypes.BLOCK_OBJ;
import static com.javampire.openscad.psi.OpenSCADTypes.BUILTIN_OBJ;
import static com.javampire.openscad.psi.OpenSCADTypes.BUILTIN_OBJ_REF;
import static com.javampire.openscad.psi.OpenSCADTypes.BUILTIN_OP;
import static com.javampire.openscad.psi.OpenSCADTypes.COLON;
import static com.javampire.openscad.psi.OpenSCADTypes.ELVIS_EXPR;
import static com.javampire.openscad.psi.OpenSCADTypes.FOR_OBJ;
import static com.javampire.openscad.psi.OpenSCADTypes.FULL_ARG_DECLARATION_LIST;
import static com.javampire.openscad.psi.OpenSCADTypes.IF_OBJ;
import static com.javampire.openscad.psi.OpenSCADTypes.RPARENTH;
import static com.javampire.openscad.psi.OpenSCADTypes.VECTOR_EXPR;

public class OpenSCADIndentBuilder {

    private final OpenSCADCodeStyleSettings settings;

    public OpenSCADIndentBuilder(final CodeStyleSettings settings) {
        this.settings = settings.getCustomSettings(OpenSCADCodeStyleSettings.class);
    }

    public Indent getChildIndent(final ASTNode node) {
        IElementType elementType = node.getElementType();
        ASTNode parent = node.getTreeParent();

        // Parent node early exit
        if (parent == null || parent.getTreeParent() == null) {
            return Indent.getNoneIndent();
        }
        IElementType parentType = parent.getElementType();

        if (parentType == BLOCK_OBJ && !BRACES_TOKENS.contains(elementType)) { // Indent all BLOCK_OBJ (surrounded by brackets) children by default
            return Indent.getNormalIndent();
        } else if (parentType == BUILTIN_OBJ && elementType != BUILTIN_OP && elementType != BUILTIN_OBJ_REF) { // Indent statements in BUILTIN_OBJ without brackets
            if (settings.INDENT_CASCADING_TRANSFORMATIONS) {
                return Indent.getNormalIndent();
            } else {
                return Indent.getNoneIndent();
            }
        } else if (parentType == IF_OBJ && (elementType == BUILTIN_OBJ || elementType == FOR_OBJ)) { // Indent statements in IF_OBJ without brackets
            return Indent.getNormalIndent();
        } else if (MATHEMATICAL_EXPR.contains(parentType)) {
            return Indent.getNormalIndent();
        } else if (parentType == VECTOR_EXPR) {
            return Indent.getNormalIndent();
        } else if (parentType == ELVIS_EXPR) {
            for (PsiElement child = node.getPsi().getPrevSibling(); child != null; child = child.getPrevSibling()) {
                if (child.getNode().getElementType() == COLON) {
                    return Indent.getContinuationIndent();
                }
            }
            return Indent.getNormalIndent();
        } else if ((parentType == ARG_DECLARATION_LIST || parentType == FULL_ARG_DECLARATION_LIST || parentType == ARG_ASSIGNMENT_LIST) && elementType != RPARENTH) {
            return Indent.getNormalIndent();
        }

        return Indent.getNoneIndent();
    }
}
