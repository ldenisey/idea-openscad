// This is a generated file. Not intended for manual editing.
package com.javampire.openscad.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface OpenSCADBuiltinObj extends OpenSCADObject {

  @Nullable
  OpenSCADArgAssignmentList getArgAssignmentList();

  @NotNull
  List<OpenSCADAssertElement> getAssertElementList();

  @Nullable
  OpenSCADBuiltinObjRef getBuiltinObjRef();

  @Nullable
  OpenSCADBuiltinOp getBuiltinOp();

  @Nullable
  OpenSCADBuiltinOverridableObjRef getBuiltinOverridableObjRef();

  @NotNull
  List<OpenSCADEchoElement> getEchoElementList();

  @Nullable
  OpenSCADFunctionDeclaration getFunctionDeclaration();

  @Nullable
  OpenSCADImport getImport();

  @Nullable
  OpenSCADModuleDeclaration getModuleDeclaration();

  @Nullable
  OpenSCADVariableDeclaration getVariableDeclaration();

}
