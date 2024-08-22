package com.javampire.openscad.formatter;

import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.TokenType;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import com.intellij.psi.formatter.common.AbstractBlock;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static com.javampire.openscad.psi.OpenSCADTypes.*;

public class OpenSCADBlock extends AbstractBlock {

    private final OpenSCADIndentBuilder myOpenSCADIndentBuilder;
    private final CommonCodeStyleSettings mySettings;
    private final SpacingBuilder mySpacingBuilder;
    // Shared among multiple children sub-blocks
    private Alignment myChildAlignment = null;
    private boolean childAlignmentCreated = false;

    protected OpenSCADBlock(@NotNull ASTNode node,
                            @Nullable Alignment alignment,
                            @Nullable Wrap wrap,
                            @NotNull CommonCodeStyleSettings settings,
                            @NotNull SpacingBuilder spacingBuilder,
                            @NotNull OpenSCADIndentBuilder openSCADIndentBuilder) {
        super(node, wrap, alignment);
        mySettings = settings;
        mySpacingBuilder = spacingBuilder;
        myOpenSCADIndentBuilder = openSCADIndentBuilder;
    }

    @Override
    public Indent getIndent() {
        return myOpenSCADIndentBuilder.getChildIndent(myNode);
    }

    @Override
    protected List<Block> buildChildren() {
        List<Block> blocks = new ArrayList<>();
        final IElementType elementType = myNode.getElementType();
        for (ASTNode child = myNode.getFirstChildNode(); child != null; child = child.getTreeNext()) {
            // Filtering blank and empty elements
            @NotNull IElementType childType = child.getElementType();
            if (childType != TokenType.WHITE_SPACE && child.getTextRange().getLength() > 0) {
                @Nullable Alignment childAlignment;
                Wrap childWrap = getChildWrap(elementType, childType);
                childAlignment = getChildAlignment(elementType, childType);
                blocks.add(new OpenSCADBlock(child, childAlignment, childWrap, mySettings, mySpacingBuilder, myOpenSCADIndentBuilder));
            }
        }
        return blocks;
    }

    private @Nullable Wrap getChildWrap(IElementType elementType, IElementType childType) {
        @Nullable Wrap childWrap;
        if (elementType == MODULE_CALL_OP || elementType == BUILTIN_OP) {
            childWrap = Wrap.createWrap(mySettings.CALL_PARAMETERS_WRAP, false);
        } else if (elementType == FUNCTION_DECLARATION) {
            childWrap = Wrap.createWrap(mySettings.METHOD_PARAMETERS_WRAP, false);
        } else if (elementType == COMMENT_CUSTOMIZER_VALUE) {
            // Don't wrap long customizer menu values
            childWrap = Wrap.createWrap(WrapType.NONE, false);
        } else if (elementType == ARG_DECLARATION) {
            // Wrap at the start of the declaration or assignment
            childWrap = Wrap.createWrap(WrapType.CHOP_DOWN_IF_LONG, false);
        } else if (elementType == ARG_ASSIGNMENT) {
            // Wrap at the start of the declaration or assignment
            childWrap = Wrap.createWrap(WrapType.CHOP_DOWN_IF_LONG, false);
        } else if (childType == COMMA) {
            // Wrap at the start of the vector element if needed
            if (elementType == VECTOR_EXPR)
                childWrap = Wrap.createWrap(WrapType.NONE, false);
            else
                childWrap = Wrap.createWrap(WrapType.CHOP_DOWN_IF_LONG, false);
        } else {
            childWrap = myWrap;
        }
        return childWrap;
    }

    @Nullable
    @Override
    public Spacing getSpacing(@Nullable Block child1, @NotNull Block child2) {
        return mySpacingBuilder.getSpacing(this, child1, child2);
    }

    @Override
    public boolean isLeaf() {
        return myNode.getFirstChildNode() == null;
    }

    @NotNull
    @Override
    public ChildAttributes getChildAttributes(int newChildIndex) {
        if (myNode.getElementType() == BLOCK_OBJ) {
            return new ChildAttributes(Indent.getNormalIndent(), null);
        } else {
            return new ChildAttributes(Indent.getNoneIndent(), null);
        }
    }

    private Alignment getAlignmentForChildren() {
        if (!childAlignmentCreated) {
            myChildAlignment = Alignment.createAlignment();
            childAlignmentCreated = true;
        }
        return myChildAlignment;
    }

    private Alignment getChildAlignment(IElementType elementType, IElementType childType) {

        if (elementType == BLOCK_OBJ || elementType == IF_OBJ || elementType == FOR_OBJ || elementType == BUILTIN_OBJ)
        {
            childAlignmentCreated = false;
            myChildAlignment = null;
            return null;
        }
        if (elementType == ARG_ASSIGNMENT_LIST && childType != LPARENTH) {
            if (!mySettings.ALIGN_MULTILINE_PARAMETERS) {
                return null;
            }
            return getAlignmentForChildren();
        } else if ((elementType == ARG_DECLARATION_LIST || elementType == FULL_ARG_DECLARATION_LIST) && childType != LPARENTH) {
            return getAlignmentForChildren();
        } else if (elementType == VECTOR_EXPR && childType != LBRACKET) {
            return getAlignmentForChildren();
        }
        if (!childAlignmentCreated)
            return myAlignment;
        else
            return null;
    }
}

