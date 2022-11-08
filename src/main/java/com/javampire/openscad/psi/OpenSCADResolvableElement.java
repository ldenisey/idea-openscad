package com.javampire.openscad.psi;

import com.intellij.psi.PsiReference;
import com.intellij.psi.stubs.AbstractStubIndex;

import javax.annotation.Nullable;

/**
 * Any element that can be resolved, i.e. that have an accessible reference (= a declaration).
 */
public interface OpenSCADResolvableElement extends OpenSCADNamedElement {

    /**
     * Return the stub index of the given element or null if the element is not a stub.
     *
     * @return Stub index or null.
     */
    public @Nullable AbstractStubIndex getStubIndex();

    /**
     * Return the reference in a charge of accessing the definition(s) of the element.
     *
     * @return The element reference.
     */
    public PsiReference getReference();
}
