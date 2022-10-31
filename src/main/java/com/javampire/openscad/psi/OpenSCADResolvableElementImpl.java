package com.javampire.openscad.psi;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiReference;
import com.intellij.psi.stubs.AbstractStubIndex;
import com.javampire.openscad.psi.stub.function.OpenSCADFunctionIndex;
import com.javampire.openscad.psi.stub.module.OpenSCADModuleIndex;
import com.javampire.openscad.psi.stub.variable.OpenSCADVariableIndex;
import com.javampire.openscad.references.OpenSCADReference;
import org.jetbrains.annotations.NotNull;

public abstract class OpenSCADResolvableElementImpl extends OpenSCADNamedElementImpl implements OpenSCADResolvableElement {

    private static final Logger LOG = Logger.getInstance(OpenSCADResolvableElementImpl.class);

    public OpenSCADResolvableElementImpl(@NotNull final ASTNode node) {
        super(node);
    }

    public AbstractStubIndex getStubIndex() {
        if (this instanceof OpenSCADModuleObjNameRef
                || this instanceof OpenSCADModuleOpNameRef
                || this instanceof OpenSCADCommonOpRef
                || this instanceof OpenSCADBuiltinObjRef
                || this instanceof OpenSCADLetOpRef
                || this instanceof OpenSCADEchoOpRef
                || this instanceof OpenSCADAssertElementRef) {
            return OpenSCADModuleIndex.getInstance();
        } else if (this instanceof OpenSCADFunctionNameRef
                || this instanceof OpenSCADBuiltinExprRef
                || this instanceof OpenSCADTestExpRef) {
            return OpenSCADFunctionIndex.getInstance();
        } else if (this instanceof OpenSCADVariableRefExpr) {
            return OpenSCADVariableIndex.getInstance();
        } else {
            final String name = getName();
            LOG.warn("getStubResolver(not handled named element of type " + getClass().getName() + "): " + name);
        }
        return null;
    }

    public PsiReference getReference() {
        return new OpenSCADReference(this, new TextRange(0, getTextLength()));
    }

}
