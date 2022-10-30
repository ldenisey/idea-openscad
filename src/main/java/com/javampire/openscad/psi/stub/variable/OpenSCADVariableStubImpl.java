package com.javampire.openscad.psi.stub.variable;

import com.intellij.psi.stubs.StubBase;
import com.intellij.psi.stubs.StubElement;
import com.javampire.openscad.psi.OpenSCADVariableDeclaration;

public class OpenSCADVariableStubImpl extends StubBase<OpenSCADVariableDeclaration> implements OpenSCADVariableStub {

    private final String name;

    public OpenSCADVariableStubImpl(StubElement parent, String name) {
        super(parent, OpenSCADVariableStubElementType.INSTANCE);
        this.name = name;
    }


    @Override
    public String getName() {
        return this.name;
    }

}
