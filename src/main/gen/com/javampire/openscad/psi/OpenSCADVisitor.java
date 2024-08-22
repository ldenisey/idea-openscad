// This is a generated file. Not intended for manual editing.
package com.javampire.openscad.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;
import com.intellij.psi.StubBasedPsiElement;
import com.javampire.openscad.psi.stub.function.OpenSCADFunctionStub;
import com.javampire.openscad.psi.stub.variable.OpenSCADVariableStub;
import com.javampire.openscad.psi.stub.module.OpenSCADModuleStub;

public class OpenSCADVisitor extends PsiElementVisitor {

  public void visitAndExpr(@NotNull OpenSCADAndExpr o) {
    visitExpr(o);
  }

  public void visitArgAssignment(@NotNull OpenSCADArgAssignment o) {
    visitPsiElement(o);
  }

  public void visitArgAssignmentList(@NotNull OpenSCADArgAssignmentList o) {
    visitPsiElement(o);
  }

  public void visitArgDeclaration(@NotNull OpenSCADArgDeclaration o) {
    visitNamedElement(o);
  }

  public void visitArgDeclarationList(@NotNull OpenSCADArgDeclarationList o) {
    visitPsiElement(o);
  }

  public void visitAssertArgList(@NotNull OpenSCADAssertArgList o) {
    visitPsiElement(o);
  }

  public void visitAssertElement(@NotNull OpenSCADAssertElement o) {
    visitPsiElement(o);
  }

  public void visitAssertExpr(@NotNull OpenSCADAssertExpr o) {
    visitExpr(o);
  }

  public void visitBackgroundOp(@NotNull OpenSCADBackgroundOp o) {
    visitOperator(o);
  }

  public void visitBindElseElement(@NotNull OpenSCADBindElseElement o) {
    visitPsiElement(o);
  }

  public void visitBlockObj(@NotNull OpenSCADBlockObj o) {
    visitObject(o);
  }

  public void visitBuiltinExpr(@NotNull OpenSCADBuiltinExpr o) {
    visitExpr(o);
  }

  public void visitBuiltinExprRef(@NotNull OpenSCADBuiltinExprRef o) {
    visitResolvableElement(o);
  }

  public void visitBuiltinObj(@NotNull OpenSCADBuiltinObj o) {
    visitObject(o);
  }

  public void visitBuiltinObjRef(@NotNull OpenSCADBuiltinObjRef o) {
    visitResolvableElement(o);
  }

  public void visitBuiltinOp(@NotNull OpenSCADBuiltinOp o) {
    visitOperator(o);
  }

  public void visitBuiltinOverridableExprRef(@NotNull OpenSCADBuiltinOverridableExprRef o) {
    visitPsiElement(o);
  }

  public void visitBuiltinOverridableObjRef(@NotNull OpenSCADBuiltinOverridableObjRef o) {
    visitResolvableElement(o);
  }

  public void visitBuiltinOverridableOpAsFunctionRef(@NotNull OpenSCADBuiltinOverridableOpAsFunctionRef o) {
    visitResolvableElement(o);
  }

  public void visitBuiltinOverridableOpRef(@NotNull OpenSCADBuiltinOverridableOpRef o) {
    visitResolvableElement(o);
  }

  public void visitCommonOpRef(@NotNull OpenSCADCommonOpRef o) {
    visitResolvableElement(o);
  }

  public void visitConditionalExpr(@NotNull OpenSCADConditionalExpr o) {
    visitExpr(o);
  }

  public void visitDebugOp(@NotNull OpenSCADDebugOp o) {
    visitOperator(o);
  }

  public void visitDisableOp(@NotNull OpenSCADDisableOp o) {
    visitOperator(o);
  }

  public void visitDivExpr(@NotNull OpenSCADDivExpr o) {
    visitExpr(o);
  }

  public void visitEachExpr(@NotNull OpenSCADEachExpr o) {
    visitExpr(o);
  }

  public void visitEchoArgList(@NotNull OpenSCADEchoArgList o) {
    visitPsiElement(o);
  }

  public void visitEchoElement(@NotNull OpenSCADEchoElement o) {
    visitPsiElement(o);
  }

  public void visitEchoExpr(@NotNull OpenSCADEchoExpr o) {
    visitExpr(o);
  }

  public void visitElseElement(@NotNull OpenSCADElseElement o) {
    visitPsiElement(o);
  }

  public void visitElvisExpr(@NotNull OpenSCADElvisExpr o) {
    visitExpr(o);
  }

  public void visitEmptyObj(@NotNull OpenSCADEmptyObj o) {
    visitObject(o);
  }

  public void visitExpr(@NotNull OpenSCADExpr o) {
    visitPsiElement(o);
  }

  public void visitForDeclaration(@NotNull OpenSCADForDeclaration o) {
    visitPsiElement(o);
  }

  public void visitForDeclarationCstyle(@NotNull OpenSCADForDeclarationCstyle o) {
    visitPsiElement(o);
  }

  public void visitForDeclarationList(@NotNull OpenSCADForDeclarationList o) {
    visitPsiElement(o);
  }

  public void visitForElement(@NotNull OpenSCADForElement o) {
    visitPsiElement(o);
  }

  public void visitForObj(@NotNull OpenSCADForObj o) {
    visitObject(o);
  }

  public void visitFullArgDeclaration(@NotNull OpenSCADFullArgDeclaration o) {
    visitNamedElement(o);
  }

  public void visitFullArgDeclarationList(@NotNull OpenSCADFullArgDeclarationList o) {
    visitPsiElement(o);
  }

  public void visitFunctionCallExpr(@NotNull OpenSCADFunctionCallExpr o) {
    visitExpr(o);
  }

  public void visitFunctionDeclaration(@NotNull OpenSCADFunctionDeclaration o) {
    visitNamedElement(o);
    // visitStubBasedPsiElement(o);
  }

  public void visitFunctionLiteralExpr(@NotNull OpenSCADFunctionLiteralExpr o) {
    visitExpr(o);
  }

  public void visitFunctionNameRef(@NotNull OpenSCADFunctionNameRef o) {
    visitResolvableElement(o);
  }

  public void visitIfElement(@NotNull OpenSCADIfElement o) {
    visitPsiElement(o);
  }

  public void visitIfObj(@NotNull OpenSCADIfObj o) {
    visitObject(o);
  }

  public void visitImport(@NotNull OpenSCADImport o) {
    visitPsiElement(o);
  }

  public void visitImportPathRef(@NotNull OpenSCADImportPathRef o) {
    visitPsiElement(o);
  }

  public void visitIncludeImport(@NotNull OpenSCADIncludeImport o) {
    visitPsiElement(o);
  }

  public void visitIndexExpr(@NotNull OpenSCADIndexExpr o) {
    visitExpr(o);
  }

  public void visitLetElement(@NotNull OpenSCADLetElement o) {
    visitPsiElement(o);
  }

  public void visitListExpr(@NotNull OpenSCADListExpr o) {
    visitExpr(o);
  }

  public void visitLiteralExpr(@NotNull OpenSCADLiteralExpr o) {
    visitExpr(o);
  }

  public void visitMinusExpr(@NotNull OpenSCADMinusExpr o) {
    visitExpr(o);
  }

  public void visitModifierOp(@NotNull OpenSCADModifierOp o) {
    visitOperator(o);
  }

  public void visitModuleCallObj(@NotNull OpenSCADModuleCallObj o) {
    visitObject(o);
  }

  public void visitModuleCallOp(@NotNull OpenSCADModuleCallOp o) {
    visitOperator(o);
  }

  public void visitModuleDeclaration(@NotNull OpenSCADModuleDeclaration o) {
    visitNamedElement(o);
    // visitStubBasedPsiElement(o);
  }

  public void visitModuleObjNameRef(@NotNull OpenSCADModuleObjNameRef o) {
    visitResolvableElement(o);
  }

  public void visitModuleOpNameRef(@NotNull OpenSCADModuleOpNameRef o) {
    visitResolvableElement(o);
  }

  public void visitModuloExpr(@NotNull OpenSCADModuloExpr o) {
    visitExpr(o);
  }

  public void visitMulExpr(@NotNull OpenSCADMulExpr o) {
    visitExpr(o);
  }

  public void visitObject(@NotNull OpenSCADObject o) {
    visitPsiElement(o);
  }

  public void visitOperator(@NotNull OpenSCADOperator o) {
    visitPsiElement(o);
  }

  public void visitOrExpr(@NotNull OpenSCADOrExpr o) {
    visitExpr(o);
  }

  public void visitParameterReference(@NotNull OpenSCADParameterReference o) {
    visitNamedElement(o);
  }

  public void visitParenExpr(@NotNull OpenSCADParenExpr o) {
    visitExpr(o);
  }

  public void visitPlusExpr(@NotNull OpenSCADPlusExpr o) {
    visitExpr(o);
  }

  public void visitPowerExpr(@NotNull OpenSCADPowerExpr o) {
    visitExpr(o);
  }

  public void visitQualificationExpr(@NotNull OpenSCADQualificationExpr o) {
    visitExpr(o);
    // visitResolvableElement(o);
  }

  public void visitRangeExpr(@NotNull OpenSCADRangeExpr o) {
    visitExpr(o);
  }

  public void visitReportingElement(@NotNull OpenSCADReportingElement o) {
    visitPsiElement(o);
  }

  public void visitRootOp(@NotNull OpenSCADRootOp o) {
    visitOperator(o);
  }

  public void visitTestExpRef(@NotNull OpenSCADTestExpRef o) {
    visitResolvableElement(o);
  }

  public void visitTestExpr(@NotNull OpenSCADTestExpr o) {
    visitExpr(o);
  }

  public void visitUnaryMinExpr(@NotNull OpenSCADUnaryMinExpr o) {
    visitExpr(o);
  }

  public void visitUnaryNegateExpr(@NotNull OpenSCADUnaryNegateExpr o) {
    visitExpr(o);
  }

  public void visitUnaryPlusExpr(@NotNull OpenSCADUnaryPlusExpr o) {
    visitExpr(o);
  }

  public void visitUseImport(@NotNull OpenSCADUseImport o) {
    visitPsiElement(o);
  }

  public void visitVariableDeclaration(@NotNull OpenSCADVariableDeclaration o) {
    visitNamedElement(o);
    // visitStubBasedPsiElement(o);
  }

  public void visitVariableRefExpr(@NotNull OpenSCADVariableRefExpr o) {
    visitExpr(o);
    // visitResolvableElement(o);
  }

  public void visitVectorExpr(@NotNull OpenSCADVectorExpr o) {
    visitExpr(o);
  }

  public void visitNamedElement(@NotNull OpenSCADNamedElement o) {
    visitPsiElement(o);
  }

  public void visitResolvableElement(@NotNull OpenSCADResolvableElement o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
