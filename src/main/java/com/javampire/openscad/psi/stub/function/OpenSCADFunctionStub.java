package com.javampire.openscad.psi.stub.function;

import com.intellij.psi.stubs.StubElement;
import com.javampire.openscad.psi.OpenSCADFunctionDeclaration;

public interface OpenSCADFunctionStub extends StubElement<OpenSCADFunctionDeclaration> {

    String getName();

}
