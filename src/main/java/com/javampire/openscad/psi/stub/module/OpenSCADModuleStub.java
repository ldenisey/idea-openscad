package com.javampire.openscad.psi.stub.module;

import com.intellij.psi.stubs.StubElement;
import com.javampire.openscad.psi.OpenSCADModuleDeclaration;

public interface OpenSCADModuleStub extends StubElement<OpenSCADModuleDeclaration> {

    String getName();

}
