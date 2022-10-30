package com.javampire.openscad.parser;

import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IStubFileElementType;
import com.intellij.psi.tree.TokenSet;
import com.javampire.openscad.OpenSCADLanguage;
import com.javampire.openscad.lexer.OpenSCADLexerAdapter;
import com.javampire.openscad.psi.OpenSCADFile;
import org.jetbrains.annotations.NotNull;

import static com.javampire.openscad.parser.OpenSCADParserTokenSets.*;
import static com.javampire.openscad.psi.OpenSCADTypes.Factory;

public class OpenSCADParserDefinition implements ParserDefinition {

    public static final IStubFileElementType FILE = new IStubFileElementType(OpenSCADLanguage.INSTANCE);

    @NotNull
    @Override
    public Lexer createLexer(Project project) {
        return new OpenSCADLexerAdapter();
    }

    @NotNull
    public TokenSet getWhitespaceTokens() {
        return WHITE_SPACES;
    }

    @NotNull
    public TokenSet getCommentTokens() {
        return COMMENTS;
    }

    @NotNull
    public TokenSet getStringLiteralElements() {
        return STRINGS;
    }

    @NotNull
    public PsiParser createParser(final Project project) {
        return new OpenSCADParser();
    }

    @Override
    public IStubFileElementType getFileNodeType() {
        return FILE;
    }

    public PsiFile createFile(FileViewProvider viewProvider) {
        return new OpenSCADFile(viewProvider);
    }

    public SpaceRequirements spaceExistenceTypeBetweenTokens(ASTNode left, ASTNode right) {
        return SpaceRequirements.MAY;
    }

    @NotNull
    public PsiElement createElement(ASTNode node) {
        return Factory.createElement(node);
    }
}
