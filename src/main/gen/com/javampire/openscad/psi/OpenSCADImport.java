// This is a generated file. Not intended for manual editing.
package com.javampire.openscad.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.navigation.ItemPresentation;

public interface OpenSCADImport extends PsiElement {

  @Nullable
  OpenSCADIncludeImport getIncludeImport();

  @Nullable
  OpenSCADUseImport getUseImport();

  ItemPresentation getPresentation();

}
