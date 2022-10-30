// This is a generated file. Not intended for manual editing.
package com.javampire.openscad.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.StubBasedPsiElement;
import com.javampire.openscad.psi.stub.module.OpenSCADModuleStub;
import com.intellij.navigation.ItemPresentation;

public interface OpenSCADModuleDeclaration extends OpenSCADNamedElement, StubBasedPsiElement<OpenSCADModuleStub> {

  @Nullable
  OpenSCADArgDeclarationList getArgDeclarationList();

  @Nullable
  OpenSCADFunctionDeclaration getFunctionDeclaration();

  @Nullable
  OpenSCADImport getImport();

  @Nullable
  OpenSCADModuleDeclaration getModuleDeclaration();

  @Nullable
  OpenSCADObject getObject();

  @Nullable
  OpenSCADVariableDeclaration getVariableDeclaration();

  ItemPresentation getPresentation();

  PsiElement getNameIdentifier();

}
