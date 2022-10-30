package com.javampire.openscad.psi.stub.variable;

import com.intellij.psi.stubs.StubElement;
import com.javampire.openscad.psi.OpenSCADVariableDeclaration;

public interface OpenSCADVariableStub extends StubElement<OpenSCADVariableDeclaration> {

    String getName();

}
