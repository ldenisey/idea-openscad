package com.javampire.openscad.psi;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.tree.IElementType;
import com.javampire.openscad.OpenSCADFileType;
import com.javampire.openscad.psi.stub.function.OpenSCADFunctionStubElementType;
import com.javampire.openscad.psi.stub.module.OpenSCADModuleStubElementType;
import com.javampire.openscad.psi.stub.variable.OpenSCADVariableStubElementType;

public class OpenSCADElementFactory {

    public static PsiElement createIdentifier(final Project project, final String name) {
        final OpenSCADFile file = createFile(project, name + "=0;");
        return file.getFirstChild();
    }

    public static OpenSCADFile createFile(final Project project, final String text) {
        final String name = "dummy.scad";
        return (OpenSCADFile) PsiFileFactory.getInstance(project).createFileFromText(name, OpenSCADFileType.INSTANCE, text);
    }

    public static IElementType getElementType(final String debugName) {
        if ("MODULE_DECLARATION".equals(debugName)) {
            return OpenSCADModuleStubElementType.INSTANCE;
        } else if ("FUNCTION_DECLARATION".equals(debugName)) {
            return OpenSCADFunctionStubElementType.INSTANCE;
        } else if ("VARIABLE_DECLARATION".equals(debugName)) {
            return OpenSCADVariableStubElementType.INSTANCE;
        } else {
            return new OpenSCADElementType(debugName);
        }
    }
}
