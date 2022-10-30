// This is a generated file. Not intended for manual editing.
package com.javampire.openscad.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.javampire.openscad.psi.OpenSCADTypes.*;
import com.javampire.openscad.psi.stub.module.OpenSCADModuleDeclarationStubElementImpl;
import com.javampire.openscad.psi.*;
import com.intellij.navigation.ItemPresentation;
import com.javampire.openscad.psi.stub.module.OpenSCADModuleStub;
import com.intellij.psi.stubs.IStubElementType;

public class OpenSCADModuleDeclarationImpl extends OpenSCADModuleDeclarationStubElementImpl implements OpenSCADModuleDeclaration {

  public OpenSCADModuleDeclarationImpl(@NotNull OpenSCADModuleStub stub, @NotNull IStubElementType type) {
    super(stub, type);
  }

  public OpenSCADModuleDeclarationImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull OpenSCADVisitor visitor) {
    visitor.visitModuleDeclaration(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof OpenSCADVisitor) accept((OpenSCADVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public OpenSCADArgDeclarationList getArgDeclarationList() {
    return findChildByClass(OpenSCADArgDeclarationList.class);
  }

  @Override
  @Nullable
  public OpenSCADFunctionDeclaration getFunctionDeclaration() {
    return findChildByClass(OpenSCADFunctionDeclaration.class);
  }

  @Override
  @Nullable
  public OpenSCADImport getImport() {
    return findChildByClass(OpenSCADImport.class);
  }

  @Override
  @Nullable
  public OpenSCADModuleDeclaration getModuleDeclaration() {
    return findChildByClass(OpenSCADModuleDeclaration.class);
  }

  @Override
  @Nullable
  public OpenSCADObject getObject() {
    return findChildByClass(OpenSCADObject.class);
  }

  @Override
  @Nullable
  public OpenSCADVariableDeclaration getVariableDeclaration() {
    return findChildByClass(OpenSCADVariableDeclaration.class);
  }

  @Override
  public ItemPresentation getPresentation() {
    return OpenSCADPsiImplUtil.getPresentation(this);
  }

  @Override
  public PsiElement getNameIdentifier() {
    return OpenSCADPsiImplUtil.getNameIdentifier(this);
  }

}
