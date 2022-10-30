package com.javampire.openscad.psi.stub.module;

import com.intellij.psi.stubs.StubBase;
import com.intellij.psi.stubs.StubElement;
import com.javampire.openscad.psi.OpenSCADModuleDeclaration;

public class OpenSCADModuleStubImpl extends StubBase<OpenSCADModuleDeclaration> implements OpenSCADModuleStub {

    private final String name;

    public OpenSCADModuleStubImpl(StubElement parent, String name) {
        super(parent, OpenSCADModuleStubElementType.INSTANCE);
        this.name = name;
    }


    @Override
    public String getName() {
        return this.name;
    }

}
