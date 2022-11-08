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

public class OpenSCADBuiltinExprImpl extends OpenSCADExprImpl implements OpenSCADBuiltinExpr {

  public OpenSCADBuiltinExprImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull OpenSCADVisitor visitor) {
    visitor.visitBuiltinExpr(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof OpenSCADVisitor) accept((OpenSCADVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public OpenSCADArgAssignmentList getArgAssignmentList() {
    return findChildByClass(OpenSCADArgAssignmentList.class);
  }

  @Override
  @Nullable
  public OpenSCADBuiltinExprRef getBuiltinExprRef() {
    return findChildByClass(OpenSCADBuiltinExprRef.class);
  }

  @Override
  @Nullable
  public OpenSCADExpr getExpr() {
    return findChildByClass(OpenSCADExpr.class);
  }

  @Override
  @Nullable
  public OpenSCADLetOp getLetOp() {
    return findChildByClass(OpenSCADLetOp.class);
  }

}
