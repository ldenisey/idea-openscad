package com.javampire.openscad.formatter;

import com.intellij.formatting.*;
import com.intellij.psi.PsiFile;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import com.javampire.openscad.OpenSCADLanguage;
import com.javampire.openscad.psi.stub.function.OpenSCADFunctionStubElementType;
import com.javampire.openscad.psi.stub.module.OpenSCADModuleStubElementType;
import org.jetbrains.annotations.NotNull;

import static com.javampire.openscad.parser.OpenSCADParserTokenSets.*;
import static com.javampire.openscad.psi.OpenSCADTypes.*;

public class OpenSCADFormattingModelBuilder implements FormattingModelBuilder {

    @Override
    public @NotNull FormattingModel createModel(@NotNull final FormattingContext formattingContext) {
        final PsiFile elementPsiFile = formattingContext.getPsiElement().getContainingFile();
        final CodeStyleSettings settings = formattingContext.getCodeStyleSettings();
        final CommonCodeStyleSettings openSCADSettings = settings.getCommonSettings(OpenSCADLanguage.INSTANCE);

        final OpenSCADBlock fileBlock = new OpenSCADBlock(
                elementPsiFile.getViewProvider().getPsi(OpenSCADLanguage.INSTANCE).getNode(),
                null,
                Wrap.createWrap(WrapType.NORMAL, false),
                openSCADSettings,
                createSpacingBuilder(settings),
                new OpenSCADIndentBuilder(settings));
        return FormattingModelProvider.createFormattingModelForPsiFile(elementPsiFile, fileBlock, settings);
    }

    private SpacingBuilder createSpacingBuilder(CodeStyleSettings settings) {
        final CommonCodeStyleSettings commonSettings = settings.getCommonSettings(OpenSCADLanguage.INSTANCE);
        final OpenSCADCodeStyleSettings openSCADSettings = settings.getCustomSettings(OpenSCADCodeStyleSettings.class);

        // Configure spacing rules below
        return new SpacingBuilder(commonSettings)

                // Before parenthesis
                .afterInside(IDENTIFIER, OpenSCADFunctionStubElementType.INSTANCE).spaceIf(commonSettings.SPACE_BEFORE_METHOD_PARENTHESES)
                .afterInside(IDENTIFIER, OpenSCADModuleStubElementType.INSTANCE).spaceIf(commonSettings.SPACE_BEFORE_METHOD_PARENTHESES)
                .beforeInside(ARG_ASSIGNMENT_LIST, MODULE_CALL_OBJ).spaceIf(commonSettings.SPACE_BEFORE_METHOD_CALL_PARENTHESES)
                .afterInside(BUILTIN_OBJ_REF, BUILTIN_OBJ).spaceIf(commonSettings.SPACE_BEFORE_METHOD_CALL_PARENTHESES)
                .afterInside(BUILTIN_OVERRIDABLE_OBJ_REF, BUILTIN_OBJ).spaceIf(commonSettings.SPACE_BEFORE_METHOD_CALL_PARENTHESES)
                .afterInside(COMMON_OP_REF, BUILTIN_OP).spaceIf(commonSettings.SPACE_BEFORE_METHOD_CALL_PARENTHESES)
                .afterInside(BUILTIN_EXPR_REF, BUILTIN_EXPR).spaceIf(commonSettings.SPACE_BEFORE_METHOD_CALL_PARENTHESES)
                .afterInside(BUILTIN_OVERRIDABLE_EXPR_REF, BUILTIN_EXPR).spaceIf(commonSettings.SPACE_BEFORE_METHOD_CALL_PARENTHESES)
                .after(IF_KEYWORD).spaceIf(commonSettings.SPACE_BEFORE_IF_PARENTHESES)
                .after(FOR_KEYWORD).spaceIf(commonSettings.SPACE_BEFORE_FOR_PARENTHESES)

                // Unary operators
                .afterInside(PLUS, UNARY_PLUS_EXPR).spaceIf(commonSettings.SPACE_AROUND_UNARY_OPERATOR)
                .afterInside(MINUS, UNARY_MIN_EXPR).spaceIf(commonSettings.SPACE_AROUND_UNARY_OPERATOR)
                .afterInside(EXCL, UNARY_NEGATE_EXPR).spaceIf(commonSettings.SPACE_AROUND_UNARY_OPERATOR)

                // Around operators
                .aroundInside(EQUALS, ARG_ASSIGNMENT).spaceIf(openSCADSettings.SPACE_AROUND_EQ_IN_ARG_ASSIGNMENT)
                .aroundInside(EQUALS, ARG_DECLARATION).spaceIf(openSCADSettings.SPACE_AROUND_EQ_IN_NAMED_PARAMETER)
                .around(EQUALS).spaceIf(commonSettings.SPACE_AROUND_ASSIGNMENT_OPERATORS)
                .around(LOGICAL_OPERATORS).spaceIf(commonSettings.SPACE_AROUND_LOGICAL_OPERATORS)
                .around(EQUALITY_OPERATORS).spaceIf(commonSettings.SPACE_AROUND_EQUALITY_OPERATORS)
                .around(RELATIONAL_OPERATORS).spaceIf(commonSettings.SPACE_AROUND_RELATIONAL_OPERATORS)
                .around(ADDITIVE_OPERATORS).spaceIf(commonSettings.SPACE_AROUND_ADDITIVE_OPERATORS)
                .around(MULTIPLICATIVE_OPERATORS).spaceIf(commonSettings.SPACE_AROUND_MULTIPLICATIVE_OPERATORS)

                // Before left brace
                .beforeInside(BLOCK_OBJ, OpenSCADModuleStubElementType.INSTANCE).spaceIf(commonSettings.SPACE_BEFORE_METHOD_LBRACE)
                .beforeInside(BLOCK_OBJ, BUILTIN_OBJ).spaceIf(commonSettings.SPACE_BEFORE_METHOD_LBRACE)
                .beforeInside(BLOCK_OBJ, IF_OBJ).spaceIf(commonSettings.SPACE_BEFORE_IF_LBRACE)
                .beforeInside(BLOCK_OBJ, FOR_OBJ).spaceIf(commonSettings.SPACE_BEFORE_FOR_LBRACE)

                // Before keywords
                .before(ELSE_KEYWORD).spaceIf(commonSettings.SPACE_BEFORE_ELSE_KEYWORD)

                // Within
                .afterInside(LBRACE, BLOCK_OBJ).spaceIf(commonSettings.SPACE_WITHIN_BRACES)
                .beforeInside(RBRACE, BLOCK_OBJ).spaceIf(commonSettings.SPACE_WITHIN_BRACES)
                .after(LBRACKET).spaceIf(commonSettings.SPACE_WITHIN_BRACKETS)
                .before(RBRACKET).spaceIf(commonSettings.SPACE_WITHIN_BRACKETS)
                .afterInside(LPARENTH, ARG_DECLARATION_LIST).spaceIf(commonSettings.SPACE_WITHIN_METHOD_PARENTHESES)
                .beforeInside(RPARENTH, ARG_DECLARATION_LIST).spaceIf(commonSettings.SPACE_WITHIN_METHOD_PARENTHESES)
                .afterInside(LPARENTH, ARG_ASSIGNMENT_LIST).spaceIf(commonSettings.SPACE_WITHIN_METHOD_CALL_PARENTHESES)
                .beforeInside(RPARENTH, ARG_ASSIGNMENT_LIST).spaceIf(commonSettings.SPACE_WITHIN_METHOD_CALL_PARENTHESES)
                .afterInside(LPARENTH, PAREN_EXPR).spaceIf(commonSettings.SPACE_WITHIN_IF_PARENTHESES)
                .beforeInside(RPARENTH, PAREN_EXPR).spaceIf(commonSettings.SPACE_WITHIN_IF_PARENTHESES)
                .afterInside(LPARENTH, FULL_ARG_DECLARATION_LIST).spaceIf(commonSettings.SPACE_WITHIN_FOR_PARENTHESES)
                .beforeInside(RPARENTH, FULL_ARG_DECLARATION_LIST).spaceIf(commonSettings.SPACE_WITHIN_FOR_PARENTHESES)

                // Other
                .after(COMMA).spaceIf(commonSettings.SPACE_AFTER_COMMA)
                .before(COMMA).spaceIf(commonSettings.SPACE_BEFORE_COMMA)
                ;
    }
}
