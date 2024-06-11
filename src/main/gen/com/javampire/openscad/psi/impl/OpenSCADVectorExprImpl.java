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

public class OpenSCADVectorExprImpl extends OpenSCADExprImpl implements OpenSCADVectorExpr {

  public OpenSCADVectorExprImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull OpenSCADVisitor visitor) {
    visitor.visitVectorExpr(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof OpenSCADVisitor) accept((OpenSCADVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<OpenSCADBindElseElement> getBindElseElementList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, OpenSCADBindElseElement.class);
  }

  @Override
  @NotNull
  public List<OpenSCADElseElement> getElseElementList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, OpenSCADElseElement.class);
  }

  @Override
  @NotNull
  public List<OpenSCADExpr> getExprList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, OpenSCADExpr.class);
  }

  @Override
  @NotNull
  public List<OpenSCADForElement> getForElementList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, OpenSCADForElement.class);
  }

  @Override
  @NotNull
  public List<OpenSCADIfElement> getIfElementList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, OpenSCADIfElement.class);
  }

  @Override
  @NotNull
  public List<OpenSCADLetElement> getLetElementList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, OpenSCADLetElement.class);
  }

  @Override
  @NotNull
  public List<OpenSCADReportingElement> getReportingElementList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, OpenSCADReportingElement.class);
  }

}
