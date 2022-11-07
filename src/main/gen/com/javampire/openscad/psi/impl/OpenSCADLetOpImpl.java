// This is a generated file. Not intended for manual editing.
package com.javampire.openscad.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.javampire.openscad.psi.OpenSCADTypes.*;
import com.javampire.openscad.psi.*;

public class OpenSCADLetOpImpl extends OpenSCADOperatorImpl implements OpenSCADLetOp {

  public OpenSCADLetOpImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull OpenSCADVisitor visitor) {
    visitor.visitLetOp(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof OpenSCADVisitor) accept((OpenSCADVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public OpenSCADFullArgDeclarationList getFullArgDeclarationList() {
    return findNotNullChildByClass(OpenSCADFullArgDeclarationList.class);
  }

  @Override
  @NotNull
  public OpenSCADLetOpRef getLetOpRef() {
    return findNotNullChildByClass(OpenSCADLetOpRef.class);
  }

}
