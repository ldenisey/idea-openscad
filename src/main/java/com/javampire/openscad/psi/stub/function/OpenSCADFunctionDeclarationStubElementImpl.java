package com.javampire.openscad.psi.stub.function;

import com.intellij.extapi.psi.StubBasedPsiElementBase;
import com.intellij.lang.ASTNode;
import com.intellij.lang.Language;
import com.intellij.psi.PsiElement;
import com.intellij.psi.stubs.IStubElementType;
import com.javampire.openscad.OpenSCADLanguage;
import com.javampire.openscad.psi.OpenSCADNamedElementImpl;
import org.jetbrains.annotations.NotNull;

public class OpenSCADFunctionDeclarationStubElementImpl extends StubBasedPsiElementBase<OpenSCADFunctionStub> {

    public OpenSCADFunctionDeclarationStubElementImpl(@NotNull final OpenSCADFunctionStub stub, @NotNull final IStubElementType nodeType) {
        super(stub, nodeType);
    }

    public OpenSCADFunctionDeclarationStubElementImpl(@NotNull final ASTNode node) {
        super(node);
    }

    @Override
    @NotNull
    public Language getLanguage() {
        return OpenSCADLanguage.INSTANCE;
    }

    @Override
    public String getName() {
        final OpenSCADFunctionStub stub = getStub();
        // If the stub already exists, returning its name
        if (stub != null) {
            return stub.getName();
        }

        // Stub does not exist yet, searching for the name in the AST
        return OpenSCADNamedElementImpl.getName(this);
    }

    public PsiElement setName(@NotNull final String newName) {
        return OpenSCADNamedElementImpl.setName(this, newName);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" + getNode().getElementType() + ")";
    }
}
