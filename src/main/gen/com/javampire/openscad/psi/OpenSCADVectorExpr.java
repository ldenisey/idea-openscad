// This is a generated file. Not intended for manual editing.
package com.javampire.openscad.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface OpenSCADVectorExpr extends OpenSCADExpr {

  @NotNull
  List<OpenSCADBindElseElement> getBindElseElementList();

  @NotNull
  List<OpenSCADElseElement> getElseElementList();

  @NotNull
  List<OpenSCADExpr> getExprList();

  @NotNull
  List<OpenSCADForElement> getForElementList();

  @NotNull
  List<OpenSCADIfElement> getIfElementList();

  @NotNull
  List<OpenSCADLetElement> getLetElementList();

  @NotNull
  List<OpenSCADReportingElement> getReportingElementList();

}
