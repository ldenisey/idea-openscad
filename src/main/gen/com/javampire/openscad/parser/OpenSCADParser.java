// This is a generated file. Not intended for manual editing.
package com.javampire.openscad.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static com.javampire.openscad.psi.OpenSCADTypes.*;
import static com.javampire.openscad.parser.OpenSCADParserUtil.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class OpenSCADParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType root_, PsiBuilder builder_) {
    parseLight(root_, builder_);
    return builder_.getTreeBuilt();
  }

  public void parseLight(IElementType root_, PsiBuilder builder_) {
    boolean result_;
    builder_ = adapt_builder_(root_, builder_, this, EXTENDS_SETS_);
    Marker marker_ = enter_section_(builder_, 0, _COLLAPSE_, null);
    result_ = parse_root_(root_, builder_);
    exit_section_(builder_, 0, marker_, root_, result_, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType root_, PsiBuilder builder_) {
    return parse_root_(root_, builder_, 0);
  }

  static boolean parse_root_(IElementType root_, PsiBuilder builder_, int level_) {
    return openSCADFile(builder_, level_ + 1);
  }

  public static final TokenSet[] EXTENDS_SETS_ = new TokenSet[] {
    create_token_set_(BLOCK_OBJ, BUILTIN_OBJ, EMPTY_OBJ, FOR_OBJ,
      IF_OBJ, MODULE_CALL_OBJ, OBJECT),
    create_token_set_(BACKGROUND_OP, BUILTIN_OP, DEBUG_OP, DISABLE_OP,
      MODIFIER_OP, MODULE_CALL_OP, OPERATOR, ROOT_OP),
    create_token_set_(AND_EXPR, ASSERT_EXPR, BUILTIN_EXPR, CONDITIONAL_EXPR,
      DIV_EXPR, ECHO_EXPR, ELVIS_EXPR, EXPR,
      FUNCTION_CALL_EXPR, FUNCTION_LITERAL_EXPR, INDEX_EXPR, LIST_EXPR,
      LITERAL_EXPR, MINUS_EXPR, MODULO_EXPR, MUL_EXPR,
      OR_EXPR, PAREN_EXPR, PLUS_EXPR, POWER_EXPR,
      QUALIFICATION_EXPR, RANGE_EXPR, TEST_EXPR, UNARY_MIN_EXPR,
      UNARY_NEGATE_EXPR, UNARY_PLUS_EXPR, VARIABLE_REF_EXPR, VECTOR_EXPR),
  };

  /* ********************************************************** */
  // [ parameter_reference EQUALS ] expr
  public static boolean arg_assignment(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "arg_assignment")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ARG_ASSIGNMENT, "<arg assignment>");
    result_ = arg_assignment_0(builder_, level_ + 1);
    result_ = result_ && expr(builder_, level_ + 1, -1);
    exit_section_(builder_, level_, marker_, result_, false, OpenSCADParser::recover_arg);
    return result_;
  }

  // [ parameter_reference EQUALS ]
  private static boolean arg_assignment_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "arg_assignment_0")) return false;
    arg_assignment_0_0(builder_, level_ + 1);
    return true;
  }

  // parameter_reference EQUALS
  private static boolean arg_assignment_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "arg_assignment_0_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = parameter_reference(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, EQUALS);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // LPARENTH arg_assignment? ( COMMA+ ( arg_assignment | &RPARENTH ) )* RPARENTH
  public static boolean arg_assignment_list(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "arg_assignment_list")) return false;
    if (!nextTokenIs(builder_, LPARENTH)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ARG_ASSIGNMENT_LIST, null);
    result_ = consumeToken(builder_, LPARENTH);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, arg_assignment_list_1(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, arg_assignment_list_2(builder_, level_ + 1)) && result_;
    result_ = pinned_ && consumeToken(builder_, RPARENTH) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // arg_assignment?
  private static boolean arg_assignment_list_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "arg_assignment_list_1")) return false;
    arg_assignment(builder_, level_ + 1);
    return true;
  }

  // ( COMMA+ ( arg_assignment | &RPARENTH ) )*
  private static boolean arg_assignment_list_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "arg_assignment_list_2")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!arg_assignment_list_2_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "arg_assignment_list_2", pos_)) break;
    }
    return true;
  }

  // COMMA+ ( arg_assignment | &RPARENTH )
  private static boolean arg_assignment_list_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "arg_assignment_list_2_0")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = arg_assignment_list_2_0_0(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && arg_assignment_list_2_0_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // COMMA+
  private static boolean arg_assignment_list_2_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "arg_assignment_list_2_0_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    while (result_) {
      int pos_ = current_position_(builder_);
      if (!consumeToken(builder_, COMMA)) break;
      if (!empty_element_parsed_guard_(builder_, "arg_assignment_list_2_0_0", pos_)) break;
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // arg_assignment | &RPARENTH
  private static boolean arg_assignment_list_2_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "arg_assignment_list_2_0_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = arg_assignment(builder_, level_ + 1);
    if (!result_) result_ = arg_assignment_list_2_0_1_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // &RPARENTH
  private static boolean arg_assignment_list_2_0_1_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "arg_assignment_list_2_0_1_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _AND_);
    result_ = consumeToken(builder_, RPARENTH);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // IDENTIFIER [EQUALS expr]
  public static boolean arg_declaration(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "arg_declaration")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ARG_DECLARATION, "<arg declaration>");
    result_ = consumeToken(builder_, IDENTIFIER);
    result_ = result_ && arg_declaration_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, OpenSCADParser::recover_arg);
    return result_;
  }

  // [EQUALS expr]
  private static boolean arg_declaration_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "arg_declaration_1")) return false;
    arg_declaration_1_0(builder_, level_ + 1);
    return true;
  }

  // EQUALS expr
  private static boolean arg_declaration_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "arg_declaration_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, EQUALS);
    result_ = result_ && expr(builder_, level_ + 1, -1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // LPARENTH arg_declaration? ( COMMA arg_declaration? )* RPARENTH
  public static boolean arg_declaration_list(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "arg_declaration_list")) return false;
    if (!nextTokenIs(builder_, LPARENTH)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ARG_DECLARATION_LIST, null);
    result_ = consumeToken(builder_, LPARENTH);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, arg_declaration_list_1(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, arg_declaration_list_2(builder_, level_ + 1)) && result_;
    result_ = pinned_ && consumeToken(builder_, RPARENTH) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // arg_declaration?
  private static boolean arg_declaration_list_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "arg_declaration_list_1")) return false;
    arg_declaration(builder_, level_ + 1);
    return true;
  }

  // ( COMMA arg_declaration? )*
  private static boolean arg_declaration_list_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "arg_declaration_list_2")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!arg_declaration_list_2_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "arg_declaration_list_2", pos_)) break;
    }
    return true;
  }

  // COMMA arg_declaration?
  private static boolean arg_declaration_list_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "arg_declaration_list_2_0")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = consumeToken(builder_, COMMA);
    pinned_ = result_; // pin = 1
    result_ = result_ && arg_declaration_list_2_0_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // arg_declaration?
  private static boolean arg_declaration_list_2_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "arg_declaration_list_2_0_1")) return false;
    arg_declaration(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // "assert" LPARENTH expr (',' STRING_LITERAL)? RPARENTH
  public static boolean assert_element(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "assert_element")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ASSERT_ELEMENT, "<assert element>");
    result_ = consumeToken(builder_, "assert");
    result_ = result_ && consumeToken(builder_, LPARENTH);
    result_ = result_ && expr(builder_, level_ + 1, -1);
    result_ = result_ && assert_element_3(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RPARENTH);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // (',' STRING_LITERAL)?
  private static boolean assert_element_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "assert_element_3")) return false;
    assert_element_3_0(builder_, level_ + 1);
    return true;
  }

  // ',' STRING_LITERAL
  private static boolean assert_element_3_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "assert_element_3_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, ",");
    result_ = result_ && consumeToken(builder_, STRING_LITERAL);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // PERC
  public static boolean background_op(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "background_op")) return false;
    if (!nextTokenIs(builder_, PERC)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, PERC);
    exit_section_(builder_, marker_, BACKGROUND_OP, result_);
    return result_;
  }

  /* ********************************************************** */
  // LPARENTH if_element expr RPARENTH
  public static boolean bind_else_element(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "bind_else_element")) return false;
    if (!nextTokenIs(builder_, LPARENTH)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, LPARENTH);
    result_ = result_ && if_element(builder_, level_ + 1);
    result_ = result_ && expr(builder_, level_ + 1, -1);
    result_ = result_ && consumeToken(builder_, RPARENTH);
    exit_section_(builder_, marker_, BIND_ELSE_ELEMENT, result_);
    return result_;
  }

  /* ********************************************************** */
  // LBRACE statement* RBRACE
  public static boolean block_obj(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "block_obj")) return false;
    if (!nextTokenIs(builder_, LBRACE)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, LBRACE);
    result_ = result_ && block_obj_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RBRACE);
    exit_section_(builder_, marker_, BLOCK_OBJ, result_);
    return result_;
  }

  // statement*
  private static boolean block_obj_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "block_obj_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!statement(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "block_obj_1", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // ABS_KEYWORD
  //                    | ACOS_KEYWORD
  //                    | ASIN_KEYWORD
  //                    | ATAN_KEYWORD
  //                    | ATAN2_KEYWORD
  //                    | CEIL_KEYWORD
  //                    | CHR_KEYWORD
  //                    | CONCAT_KEYWORD
  //                    | COS_KEYWORD
  //                    | CROSS_KEYWORD
  //                    | EXP_KEYWORD
  //                    | FLOOR_KEYWORD
  //                    | LEN_KEYWORD
  //                    | LN_KEYWORD
  //                    | LOG_KEYWORD
  //                    | LOOKUP_KEYWORD
  //                    | MAX_KEYWORD
  //                    | MIN_KEYWORD
  //                    | NORM_KEYWORD
  //                    | ORD_KEYWORD
  //                    | PARENT_MODULE_KEYWORD
  //                    | POW_KEYWORD
  //                    | RANDS_KEYWORD
  //                    | ROUND_KEYWORD
  //                    | SEARCH_KEYWORD
  //                    | SIGN_KEYWORD
  //                    | SIN_KEYWORD
  //                    | SQRT_KEYWORD
  //                    | STR_KEYWORD
  //                    | TAN_KEYWORD
  //                    | VERSION_KEYWORD
  //                    | VERSION_NUM_KEYWORD
  public static boolean builtin_expr_ref(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "builtin_expr_ref")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, BUILTIN_EXPR_REF, "<builtin expr ref>");
    result_ = consumeToken(builder_, ABS_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, ACOS_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, ASIN_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, ATAN_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, ATAN2_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, CEIL_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, CHR_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, CONCAT_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, COS_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, CROSS_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, EXP_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, FLOOR_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, LEN_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, LN_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, LOG_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, LOOKUP_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, MAX_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, MIN_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, NORM_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, ORD_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, PARENT_MODULE_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, POW_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, RANDS_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, ROUND_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, SEARCH_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, SIGN_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, SIN_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, SQRT_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, STR_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, TAN_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, VERSION_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, VERSION_NUM_KEYWORD);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // (builtin_obj_ref arg_assignment_list SEMICOLON)
  //                 | (builtin_op block_obj)
  //                 | (builtin_op statement)
  public static boolean builtin_obj(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "builtin_obj")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, BUILTIN_OBJ, "<builtin obj>");
    result_ = builtin_obj_0(builder_, level_ + 1);
    if (!result_) result_ = builtin_obj_1(builder_, level_ + 1);
    if (!result_) result_ = builtin_obj_2(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // builtin_obj_ref arg_assignment_list SEMICOLON
  private static boolean builtin_obj_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "builtin_obj_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = builtin_obj_ref(builder_, level_ + 1);
    result_ = result_ && arg_assignment_list(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, SEMICOLON);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // builtin_op block_obj
  private static boolean builtin_obj_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "builtin_obj_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = builtin_op(builder_, level_ + 1);
    result_ = result_ && block_obj(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // builtin_op statement
  private static boolean builtin_obj_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "builtin_obj_2")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = builtin_op(builder_, level_ + 1);
    result_ = result_ && statement(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // ASSERT_KEYWORD
  //                   | CHILD_KEYWORD
  //                   | CHILDREN_KEYWORD
  //                   | CIRCLE_KEYWORD
  //                   | CUBE_KEYWORD
  //                   | CYLINDER_KEYWORD
  //                   | ECHO_KEYWORD
  //                   | IMPORT_KEYWORD
  //                   | IMPORT_DXF_KEYWORD
  //                   | IMPORT_STL_KEYWORD
  //                   | POLYGON_KEYWORD
  //                   | POLYHEDRON_KEYWORD
  //                   | SPHERE_KEYWORD
  //                   | SQUARE_KEYWORD
  //                   | SURFACE_KEYWORD
  //                   | TEXT_KEYWORD
  public static boolean builtin_obj_ref(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "builtin_obj_ref")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, BUILTIN_OBJ_REF, "<builtin obj ref>");
    result_ = consumeToken(builder_, ASSERT_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, CHILD_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, CHILDREN_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, CIRCLE_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, CUBE_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, CYLINDER_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, ECHO_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, IMPORT_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, IMPORT_DXF_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, IMPORT_STL_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, POLYGON_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, POLYHEDRON_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, SPHERE_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, SQUARE_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, SURFACE_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, TEXT_KEYWORD);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // common_op_ref arg_assignment_list
  public static boolean builtin_op(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "builtin_op")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, BUILTIN_OP, "<builtin op>");
    result_ = common_op_ref(builder_, level_ + 1);
    result_ = result_ && arg_assignment_list(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // COMMENT_C_STYLE | COMMENT_DOC | COMMENT_SINGLELINE | COMMENT_SINGLELINE_BLOCK | COMMENT_CUSTOMIZER_VALUE | COMMENT_CUSTOMIZER_TABS
  static boolean comment_item(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "comment_item")) return false;
    boolean result_;
    result_ = consumeToken(builder_, COMMENT_C_STYLE);
    if (!result_) result_ = consumeToken(builder_, COMMENT_DOC);
    if (!result_) result_ = consumeToken(builder_, COMMENT_SINGLELINE);
    if (!result_) result_ = consumeToken(builder_, COMMENT_SINGLELINE_BLOCK);
    if (!result_) result_ = consumeToken(builder_, COMMENT_CUSTOMIZER_VALUE);
    if (!result_) result_ = consumeToken(builder_, COMMENT_CUSTOMIZER_TABS);
    return result_;
  }

  /* ********************************************************** */
  // COLOR_KEYWORD
  //                 | DIFFERENCE_KEYWORD
  //                 | HULL_KEYWORD
  //                 | INTERSECTION_KEYWORD
  //                 | LINEAR_EXTRUDE_KEYWORD
  //                 | MINKOWSKI_KEYWORD
  //                 | MIRROR_KEYWORD
  //                 | MULTMATRIX_KEYWORD
  //                 | OFFSET_KEYWORD
  //                 | PROJECTION_KEYWORD
  //                 | RENDER_KEYWORD
  //                 | RESIZE_KEYWORD
  //                 | ROTATE_KEYWORD
  //                 | ROTATE_EXTRUDE_KEYWORD
  //                 | SCALE_KEYWORD
  //                 | TRANSLATE_KEYWORD
  //                 | UNION_KEYWORD
  public static boolean common_op_ref(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "common_op_ref")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, COMMON_OP_REF, "<common op ref>");
    result_ = consumeToken(builder_, COLOR_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, DIFFERENCE_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, HULL_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, INTERSECTION_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, LINEAR_EXTRUDE_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, MINKOWSKI_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, MIRROR_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, MULTMATRIX_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, OFFSET_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, PROJECTION_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, RENDER_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, RESIZE_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, ROTATE_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, ROTATE_EXTRUDE_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, SCALE_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, TRANSLATE_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, UNION_KEYWORD);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // operator object
  static boolean compound_obj(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "compound_obj")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = operator(builder_, level_ + 1);
    result_ = result_ && object(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // HASH
  public static boolean debug_op(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "debug_op")) return false;
    if (!nextTokenIs(builder_, HASH)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, HASH);
    exit_section_(builder_, marker_, DEBUG_OP, result_);
    return result_;
  }

  /* ********************************************************** */
  // variable_declaration | module_declaration | function_declaration
  static boolean declaration(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "declaration")) return false;
    boolean result_;
    result_ = variable_declaration(builder_, level_ + 1);
    if (!result_) result_ = module_declaration(builder_, level_ + 1);
    if (!result_) result_ = function_declaration(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // MUL
  public static boolean disable_op(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "disable_op")) return false;
    if (!nextTokenIs(builder_, MUL)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, MUL);
    exit_section_(builder_, marker_, DISABLE_OP, result_);
    return result_;
  }

  /* ********************************************************** */
  // LPARENTH ((IDENTIFIER EQUALS expr) | expr | IDENTIFIER | STRING_LITERAL) (COMMA ((IDENTIFIER EQUALS expr) | expr | IDENTIFIER |  STRING_LITERAL))* RPARENTH
  public static boolean echo_arg_list(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "echo_arg_list")) return false;
    if (!nextTokenIs(builder_, LPARENTH)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, LPARENTH);
    result_ = result_ && echo_arg_list_1(builder_, level_ + 1);
    result_ = result_ && echo_arg_list_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RPARENTH);
    exit_section_(builder_, marker_, ECHO_ARG_LIST, result_);
    return result_;
  }

  // (IDENTIFIER EQUALS expr) | expr | IDENTIFIER | STRING_LITERAL
  private static boolean echo_arg_list_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "echo_arg_list_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = echo_arg_list_1_0(builder_, level_ + 1);
    if (!result_) result_ = expr(builder_, level_ + 1, -1);
    if (!result_) result_ = consumeToken(builder_, IDENTIFIER);
    if (!result_) result_ = consumeToken(builder_, STRING_LITERAL);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // IDENTIFIER EQUALS expr
  private static boolean echo_arg_list_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "echo_arg_list_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, IDENTIFIER, EQUALS);
    result_ = result_ && expr(builder_, level_ + 1, -1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (COMMA ((IDENTIFIER EQUALS expr) | expr | IDENTIFIER |  STRING_LITERAL))*
  private static boolean echo_arg_list_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "echo_arg_list_2")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!echo_arg_list_2_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "echo_arg_list_2", pos_)) break;
    }
    return true;
  }

  // COMMA ((IDENTIFIER EQUALS expr) | expr | IDENTIFIER |  STRING_LITERAL)
  private static boolean echo_arg_list_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "echo_arg_list_2_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && echo_arg_list_2_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (IDENTIFIER EQUALS expr) | expr | IDENTIFIER |  STRING_LITERAL
  private static boolean echo_arg_list_2_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "echo_arg_list_2_0_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = echo_arg_list_2_0_1_0(builder_, level_ + 1);
    if (!result_) result_ = expr(builder_, level_ + 1, -1);
    if (!result_) result_ = consumeToken(builder_, IDENTIFIER);
    if (!result_) result_ = consumeToken(builder_, STRING_LITERAL);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // IDENTIFIER EQUALS expr
  private static boolean echo_arg_list_2_0_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "echo_arg_list_2_0_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, IDENTIFIER, EQUALS);
    result_ = result_ && expr(builder_, level_ + 1, -1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // "echo" echo_arg_list
  public static boolean echo_element(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "echo_element")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ECHO_ELEMENT, "<echo element>");
    result_ = consumeToken(builder_, "echo");
    result_ = result_ && echo_arg_list(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // ELSE_KEYWORD [if_element]
  public static boolean else_element(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "else_element")) return false;
    if (!nextTokenIs(builder_, ELSE_KEYWORD)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, ELSE_KEYWORD);
    result_ = result_ && else_element_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, ELSE_ELEMENT, result_);
    return result_;
  }

  // [if_element]
  private static boolean else_element_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "else_element_1")) return false;
    if_element(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // SEMICOLON
  public static boolean empty_obj(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "empty_obj")) return false;
    if (!nextTokenIs(builder_, SEMICOLON)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, SEMICOLON);
    exit_section_(builder_, marker_, EMPTY_OBJ, result_);
    return result_;
  }

  /* ********************************************************** */
  // for_declaration_cstyle | (IDENTIFIER EQUALS (list_expr | range_expr | expr) (COMMA IDENTIFIER EQUALS (list_expr | range_expr | expr))*)
  public static boolean for_declaration(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "for_declaration")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, FOR_DECLARATION, "<for declaration>");
    result_ = for_declaration_cstyle(builder_, level_ + 1);
    if (!result_) result_ = for_declaration_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, OpenSCADParser::recover_arg);
    return result_;
  }

  // IDENTIFIER EQUALS (list_expr | range_expr | expr) (COMMA IDENTIFIER EQUALS (list_expr | range_expr | expr))*
  private static boolean for_declaration_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "for_declaration_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, IDENTIFIER, EQUALS);
    result_ = result_ && for_declaration_1_2(builder_, level_ + 1);
    result_ = result_ && for_declaration_1_3(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // list_expr | range_expr | expr
  private static boolean for_declaration_1_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "for_declaration_1_2")) return false;
    boolean result_;
    result_ = list_expr(builder_, level_ + 1);
    if (!result_) result_ = range_expr(builder_, level_ + 1);
    if (!result_) result_ = expr(builder_, level_ + 1, -1);
    return result_;
  }

  // (COMMA IDENTIFIER EQUALS (list_expr | range_expr | expr))*
  private static boolean for_declaration_1_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "for_declaration_1_3")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!for_declaration_1_3_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "for_declaration_1_3", pos_)) break;
    }
    return true;
  }

  // COMMA IDENTIFIER EQUALS (list_expr | range_expr | expr)
  private static boolean for_declaration_1_3_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "for_declaration_1_3_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, COMMA, IDENTIFIER, EQUALS);
    result_ = result_ && for_declaration_1_3_0_3(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // list_expr | range_expr | expr
  private static boolean for_declaration_1_3_0_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "for_declaration_1_3_0_3")) return false;
    boolean result_;
    result_ = list_expr(builder_, level_ + 1);
    if (!result_) result_ = range_expr(builder_, level_ + 1);
    if (!result_) result_ = expr(builder_, level_ + 1, -1);
    return result_;
  }

  /* ********************************************************** */
  // IDENTIFIER EQUALS expr (COMMA IDENTIFIER EQUALS expr)* SEMICOLON conditional_expr (COMA conditional_expr)* SEMICOLON IDENTIFIER EQUALS expr (COMMA IDENTIFIER EQUALS expr)*
  public static boolean for_declaration_cstyle(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "for_declaration_cstyle")) return false;
    if (!nextTokenIs(builder_, IDENTIFIER)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, IDENTIFIER, EQUALS);
    result_ = result_ && expr(builder_, level_ + 1, -1);
    result_ = result_ && for_declaration_cstyle_3(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, SEMICOLON);
    result_ = result_ && expr(builder_, level_ + 1, -1);
    result_ = result_ && for_declaration_cstyle_6(builder_, level_ + 1);
    result_ = result_ && consumeTokens(builder_, 0, SEMICOLON, IDENTIFIER, EQUALS);
    result_ = result_ && expr(builder_, level_ + 1, -1);
    result_ = result_ && for_declaration_cstyle_11(builder_, level_ + 1);
    exit_section_(builder_, marker_, FOR_DECLARATION_CSTYLE, result_);
    return result_;
  }

  // (COMMA IDENTIFIER EQUALS expr)*
  private static boolean for_declaration_cstyle_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "for_declaration_cstyle_3")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!for_declaration_cstyle_3_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "for_declaration_cstyle_3", pos_)) break;
    }
    return true;
  }

  // COMMA IDENTIFIER EQUALS expr
  private static boolean for_declaration_cstyle_3_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "for_declaration_cstyle_3_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, COMMA, IDENTIFIER, EQUALS);
    result_ = result_ && expr(builder_, level_ + 1, -1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (COMA conditional_expr)*
  private static boolean for_declaration_cstyle_6(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "for_declaration_cstyle_6")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!for_declaration_cstyle_6_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "for_declaration_cstyle_6", pos_)) break;
    }
    return true;
  }

  // COMA conditional_expr
  private static boolean for_declaration_cstyle_6_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "for_declaration_cstyle_6_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMA);
    result_ = result_ && expr(builder_, level_ + 1, -1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (COMMA IDENTIFIER EQUALS expr)*
  private static boolean for_declaration_cstyle_11(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "for_declaration_cstyle_11")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!for_declaration_cstyle_11_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "for_declaration_cstyle_11", pos_)) break;
    }
    return true;
  }

  // COMMA IDENTIFIER EQUALS expr
  private static boolean for_declaration_cstyle_11_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "for_declaration_cstyle_11_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, COMMA, IDENTIFIER, EQUALS);
    result_ = result_ && expr(builder_, level_ + 1, -1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // LPARENTH for_declaration RPARENTH
  public static boolean for_declaration_list(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "for_declaration_list")) return false;
    if (!nextTokenIs(builder_, LPARENTH)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, FOR_DECLARATION_LIST, null);
    result_ = consumeToken(builder_, LPARENTH);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, for_declaration(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, RPARENTH) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // FOR_KEYWORD for_declaration_list
  public static boolean for_element(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "for_element")) return false;
    if (!nextTokenIs(builder_, FOR_KEYWORD)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, FOR_KEYWORD);
    result_ = result_ && for_declaration_list(builder_, level_ + 1);
    exit_section_(builder_, marker_, FOR_ELEMENT, result_);
    return result_;
  }

  /* ********************************************************** */
  // full_arg_op_identifier full_arg_declaration_list (block_obj | statement)
  public static boolean for_obj(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "for_obj")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, FOR_OBJ, "<for obj>");
    result_ = full_arg_op_identifier(builder_, level_ + 1);
    result_ = result_ && full_arg_declaration_list(builder_, level_ + 1);
    result_ = result_ && for_obj_2(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // block_obj | statement
  private static boolean for_obj_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "for_obj_2")) return false;
    boolean result_;
    result_ = block_obj(builder_, level_ + 1);
    if (!result_) result_ = statement(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // IDENTIFIER EQUALS expr
  public static boolean full_arg_declaration(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "full_arg_declaration")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, FULL_ARG_DECLARATION, "<full arg declaration>");
    result_ = consumeTokens(builder_, 0, IDENTIFIER, EQUALS);
    result_ = result_ && expr(builder_, level_ + 1, -1);
    exit_section_(builder_, level_, marker_, result_, false, OpenSCADParser::recover_arg);
    return result_;
  }

  /* ********************************************************** */
  // LPARENTH full_arg_declaration? ( COMMA+ ( full_arg_declaration | &RPARENTH ) )* RPARENTH
  public static boolean full_arg_declaration_list(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "full_arg_declaration_list")) return false;
    if (!nextTokenIs(builder_, LPARENTH)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, FULL_ARG_DECLARATION_LIST, null);
    result_ = consumeToken(builder_, LPARENTH);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, full_arg_declaration_list_1(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, full_arg_declaration_list_2(builder_, level_ + 1)) && result_;
    result_ = pinned_ && consumeToken(builder_, RPARENTH) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // full_arg_declaration?
  private static boolean full_arg_declaration_list_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "full_arg_declaration_list_1")) return false;
    full_arg_declaration(builder_, level_ + 1);
    return true;
  }

  // ( COMMA+ ( full_arg_declaration | &RPARENTH ) )*
  private static boolean full_arg_declaration_list_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "full_arg_declaration_list_2")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!full_arg_declaration_list_2_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "full_arg_declaration_list_2", pos_)) break;
    }
    return true;
  }

  // COMMA+ ( full_arg_declaration | &RPARENTH )
  private static boolean full_arg_declaration_list_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "full_arg_declaration_list_2_0")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = full_arg_declaration_list_2_0_0(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && full_arg_declaration_list_2_0_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // COMMA+
  private static boolean full_arg_declaration_list_2_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "full_arg_declaration_list_2_0_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    while (result_) {
      int pos_ = current_position_(builder_);
      if (!consumeToken(builder_, COMMA)) break;
      if (!empty_element_parsed_guard_(builder_, "full_arg_declaration_list_2_0_0", pos_)) break;
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // full_arg_declaration | &RPARENTH
  private static boolean full_arg_declaration_list_2_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "full_arg_declaration_list_2_0_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = full_arg_declaration(builder_, level_ + 1);
    if (!result_) result_ = full_arg_declaration_list_2_0_1_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // &RPARENTH
  private static boolean full_arg_declaration_list_2_0_1_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "full_arg_declaration_list_2_0_1_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _AND_);
    result_ = consumeToken(builder_, RPARENTH);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // INTERSECTION_FOR_KEYWORD
  //                                  | ASSIGN_KEYWORD
  //                                  | FOR_KEYWORD
  //                                  | LET_KEYWORD
  static boolean full_arg_op_identifier(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "full_arg_op_identifier")) return false;
    boolean result_;
    result_ = consumeToken(builder_, INTERSECTION_FOR_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, ASSIGN_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, FOR_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, LET_KEYWORD);
    return result_;
  }

  /* ********************************************************** */
  // FUNCTION_KEYWORD IDENTIFIER arg_declaration_list EQUALS expr SEMICOLON
  public static boolean function_declaration(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "function_declaration")) return false;
    if (!nextTokenIs(builder_, FUNCTION_KEYWORD)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, FUNCTION_DECLARATION, null);
    result_ = consumeTokens(builder_, 1, FUNCTION_KEYWORD, IDENTIFIER);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, arg_declaration_list(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, consumeToken(builder_, EQUALS)) && result_;
    result_ = pinned_ && report_error_(builder_, expr(builder_, level_ + 1, -1)) && result_;
    result_ = pinned_ && consumeToken(builder_, SEMICOLON) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // IDENTIFIER
  public static boolean function_name_ref(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "function_name_ref")) return false;
    if (!nextTokenIs(builder_, IDENTIFIER)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, IDENTIFIER);
    exit_section_(builder_, marker_, FUNCTION_NAME_REF, result_);
    return result_;
  }

  /* ********************************************************** */
  // IF_KEYWORD LPARENTH expr RPARENTH [expr &ELSE_KEYWORD]
  public static boolean if_element(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "if_element")) return false;
    if (!nextTokenIs(builder_, IF_KEYWORD)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, IF_KEYWORD, LPARENTH);
    result_ = result_ && expr(builder_, level_ + 1, -1);
    result_ = result_ && consumeToken(builder_, RPARENTH);
    result_ = result_ && if_element_4(builder_, level_ + 1);
    exit_section_(builder_, marker_, IF_ELEMENT, result_);
    return result_;
  }

  // [expr &ELSE_KEYWORD]
  private static boolean if_element_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "if_element_4")) return false;
    if_element_4_0(builder_, level_ + 1);
    return true;
  }

  // expr &ELSE_KEYWORD
  private static boolean if_element_4_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "if_element_4_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = expr(builder_, level_ + 1, -1);
    result_ = result_ && if_element_4_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // &ELSE_KEYWORD
  private static boolean if_element_4_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "if_element_4_0_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _AND_);
    result_ = consumeToken(builder_, ELSE_KEYWORD);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // IF_KEYWORD paren_expr (block_obj | statement) (ELSE_KEYWORD IF_KEYWORD paren_expr (block_obj | statement))* [ELSE_KEYWORD (block_obj | statement)]
  public static boolean if_obj(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "if_obj")) return false;
    if (!nextTokenIs(builder_, IF_KEYWORD)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, IF_KEYWORD);
    result_ = result_ && paren_expr(builder_, level_ + 1);
    result_ = result_ && if_obj_2(builder_, level_ + 1);
    result_ = result_ && if_obj_3(builder_, level_ + 1);
    result_ = result_ && if_obj_4(builder_, level_ + 1);
    exit_section_(builder_, marker_, IF_OBJ, result_);
    return result_;
  }

  // block_obj | statement
  private static boolean if_obj_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "if_obj_2")) return false;
    boolean result_;
    result_ = block_obj(builder_, level_ + 1);
    if (!result_) result_ = statement(builder_, level_ + 1);
    return result_;
  }

  // (ELSE_KEYWORD IF_KEYWORD paren_expr (block_obj | statement))*
  private static boolean if_obj_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "if_obj_3")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!if_obj_3_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "if_obj_3", pos_)) break;
    }
    return true;
  }

  // ELSE_KEYWORD IF_KEYWORD paren_expr (block_obj | statement)
  private static boolean if_obj_3_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "if_obj_3_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, ELSE_KEYWORD, IF_KEYWORD);
    result_ = result_ && paren_expr(builder_, level_ + 1);
    result_ = result_ && if_obj_3_0_3(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // block_obj | statement
  private static boolean if_obj_3_0_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "if_obj_3_0_3")) return false;
    boolean result_;
    result_ = block_obj(builder_, level_ + 1);
    if (!result_) result_ = statement(builder_, level_ + 1);
    return result_;
  }

  // [ELSE_KEYWORD (block_obj | statement)]
  private static boolean if_obj_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "if_obj_4")) return false;
    if_obj_4_0(builder_, level_ + 1);
    return true;
  }

  // ELSE_KEYWORD (block_obj | statement)
  private static boolean if_obj_4_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "if_obj_4_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, ELSE_KEYWORD);
    result_ = result_ && if_obj_4_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // block_obj | statement
  private static boolean if_obj_4_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "if_obj_4_0_1")) return false;
    boolean result_;
    result_ = block_obj(builder_, level_ + 1);
    if (!result_) result_ = statement(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // (
  //     include_import
  //     | use_import
  // ){
  //  }
  public static boolean import_$(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "import_$")) return false;
    if (!nextTokenIs(builder_, "<import $>", INCLUDE_KEYWORD, USE_KEYWORD)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, IMPORT, "<import $>");
    result_ = import_0(builder_, level_ + 1);
    result_ = result_ && import_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // include_import
  //     | use_import
  private static boolean import_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "import_0")) return false;
    boolean result_;
    result_ = include_import(builder_, level_ + 1);
    if (!result_) result_ = use_import(builder_, level_ + 1);
    return result_;
  }

  // {
  //  }
  private static boolean import_1(PsiBuilder builder_, int level_) {
    return true;
  }

  /* ********************************************************** */
  // IMPORT_PATH
  public static boolean import_path_ref(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "import_path_ref")) return false;
    if (!nextTokenIs(builder_, IMPORT_PATH)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, IMPORT_PATH);
    exit_section_(builder_, marker_, IMPORT_PATH_REF, result_);
    return result_;
  }

  /* ********************************************************** */
  // INCLUDE_KEYWORD IMPORT_START import_path_ref IMPORT_END
  public static boolean include_import(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "include_import")) return false;
    if (!nextTokenIs(builder_, INCLUDE_KEYWORD)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, INCLUDE_KEYWORD, IMPORT_START);
    result_ = result_ && import_path_ref(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, IMPORT_END);
    exit_section_(builder_, marker_, INCLUDE_IMPORT, result_);
    return result_;
  }

  /* ********************************************************** */
  // statement
  //     | comment_item
  static boolean item(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "item")) return false;
    boolean result_;
    result_ = statement(builder_, level_ + 1);
    if (!result_) result_ = comment_item(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // LET_KEYWORD full_arg_declaration_list
  public static boolean let_element(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "let_element")) return false;
    if (!nextTokenIs(builder_, LET_KEYWORD)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, LET_KEYWORD);
    result_ = result_ && full_arg_declaration_list(builder_, level_ + 1);
    exit_section_(builder_, marker_, LET_ELEMENT, result_);
    return result_;
  }

  /* ********************************************************** */
  // LPARENTH expr (COMMA expr)* RPARENTH
  public static boolean list_expr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "list_expr")) return false;
    if (!nextTokenIs(builder_, LPARENTH)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, LPARENTH);
    result_ = result_ && expr(builder_, level_ + 1, -1);
    result_ = result_ && list_expr_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RPARENTH);
    exit_section_(builder_, marker_, LIST_EXPR, result_);
    return result_;
  }

  // (COMMA expr)*
  private static boolean list_expr_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "list_expr_2")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!list_expr_2_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "list_expr_2", pos_)) break;
    }
    return true;
  }

  // COMMA expr
  private static boolean list_expr_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "list_expr_2_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && expr(builder_, level_ + 1, -1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // background_op
  //               | debug_op
  //               | root_op
  //               | disable_op
  public static boolean modifier_op(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "modifier_op")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, MODIFIER_OP, "<modifier op>");
    result_ = background_op(builder_, level_ + 1);
    if (!result_) result_ = debug_op(builder_, level_ + 1);
    if (!result_) result_ = root_op(builder_, level_ + 1);
    if (!result_) result_ = disable_op(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // module_obj_name_ref arg_assignment_list SEMICOLON
  public static boolean module_call_obj(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "module_call_obj")) return false;
    if (!nextTokenIs(builder_, IDENTIFIER)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = module_obj_name_ref(builder_, level_ + 1);
    result_ = result_ && arg_assignment_list(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, SEMICOLON);
    exit_section_(builder_, marker_, MODULE_CALL_OBJ, result_);
    return result_;
  }

  /* ********************************************************** */
  // module_op_name_ref arg_assignment_list
  public static boolean module_call_op(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "module_call_op")) return false;
    if (!nextTokenIs(builder_, IDENTIFIER)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = module_op_name_ref(builder_, level_ + 1);
    result_ = result_ && arg_assignment_list(builder_, level_ + 1);
    exit_section_(builder_, marker_, MODULE_CALL_OP, result_);
    return result_;
  }

  /* ********************************************************** */
  // MODULE_KEYWORD IDENTIFIER arg_declaration_list statement
  public static boolean module_declaration(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "module_declaration")) return false;
    if (!nextTokenIs(builder_, MODULE_KEYWORD)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, MODULE_DECLARATION, null);
    result_ = consumeTokens(builder_, 1, MODULE_KEYWORD, IDENTIFIER);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, arg_declaration_list(builder_, level_ + 1));
    result_ = pinned_ && statement(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // IDENTIFIER
  public static boolean module_obj_name_ref(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "module_obj_name_ref")) return false;
    if (!nextTokenIs(builder_, IDENTIFIER)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, IDENTIFIER);
    exit_section_(builder_, marker_, MODULE_OBJ_NAME_REF, result_);
    return result_;
  }

  /* ********************************************************** */
  // IDENTIFIER
  public static boolean module_op_name_ref(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "module_op_name_ref")) return false;
    if (!nextTokenIs(builder_, IDENTIFIER)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, IDENTIFIER);
    exit_section_(builder_, marker_, MODULE_OP_NAME_REF, result_);
    return result_;
  }

  /* ********************************************************** */
  // builtin_obj
  //          | if_obj
  //          | for_obj
  //          | module_call_obj
  //          | block_obj
  //          | compound_obj
  //          | empty_obj
  public static boolean object(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "object")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, OBJECT, "<object>");
    result_ = builtin_obj(builder_, level_ + 1);
    if (!result_) result_ = if_obj(builder_, level_ + 1);
    if (!result_) result_ = for_obj(builder_, level_ + 1);
    if (!result_) result_ = module_call_obj(builder_, level_ + 1);
    if (!result_) result_ = block_obj(builder_, level_ + 1);
    if (!result_) result_ = compound_obj(builder_, level_ + 1);
    if (!result_) result_ = empty_obj(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // item*
  static boolean openSCADFile(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "openSCADFile")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!item(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "openSCADFile", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // builtin_op
  //            | modifier_op
  //            | module_call_op
  public static boolean operator(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "operator")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, OPERATOR, "<operator>");
    result_ = builtin_op(builder_, level_ + 1);
    if (!result_) result_ = modifier_op(builder_, level_ + 1);
    if (!result_) result_ = module_call_op(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // IDENTIFIER
  public static boolean parameter_reference(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "parameter_reference")) return false;
    if (!nextTokenIs(builder_, IDENTIFIER)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, IDENTIFIER);
    exit_section_(builder_, marker_, PARAMETER_REFERENCE, result_);
    return result_;
  }

  /* ********************************************************** */
  // ! (COMMA | RPARENTH)
  static boolean recover_arg(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "recover_arg")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !recover_arg_0(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // COMMA | RPARENTH
  private static boolean recover_arg_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "recover_arg_0")) return false;
    boolean result_;
    result_ = consumeToken(builder_, COMMA);
    if (!result_) result_ = consumeToken(builder_, RPARENTH);
    return result_;
  }

  /* ********************************************************** */
  // ! (COMMA | RBRACKET)
  static boolean recover_vector(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "recover_vector")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !recover_vector_0(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // COMMA | RBRACKET
  private static boolean recover_vector_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "recover_vector_0")) return false;
    boolean result_;
    result_ = consumeToken(builder_, COMMA);
    if (!result_) result_ = consumeToken(builder_, RBRACKET);
    return result_;
  }

  /* ********************************************************** */
  // EXCL
  public static boolean root_op(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "root_op")) return false;
    if (!nextTokenIs(builder_, EXCL)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, EXCL);
    exit_section_(builder_, marker_, ROOT_OP, result_);
    return result_;
  }

  /* ********************************************************** */
  // object | declaration | import
  static boolean statement(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "statement")) return false;
    boolean result_;
    result_ = object(builder_, level_ + 1);
    if (!result_) result_ = declaration(builder_, level_ + 1);
    if (!result_) result_ = import_$(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // IS_UNDEF_KEYWORD
  //                | IS_LIST_KEYWORD
  //                | IS_NUM_KEYWORD
  //                | IS_BOOL_KEYWORD
  //                | IS_STRING_KEYWORD
  //                | IS_FUNCTION_KEYWORD
  public static boolean test_exp_ref(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "test_exp_ref")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, TEST_EXP_REF, "<test exp ref>");
    result_ = consumeToken(builder_, IS_UNDEF_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, IS_LIST_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, IS_NUM_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, IS_BOOL_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, IS_STRING_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, IS_FUNCTION_KEYWORD);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // USE_KEYWORD IMPORT_START import_path_ref IMPORT_END
  public static boolean use_import(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "use_import")) return false;
    if (!nextTokenIs(builder_, USE_KEYWORD)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, USE_KEYWORD, IMPORT_START);
    result_ = result_ && import_path_ref(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, IMPORT_END);
    exit_section_(builder_, marker_, USE_IMPORT, result_);
    return result_;
  }

  /* ********************************************************** */
  // IDENTIFIER EQUALS expr SEMICOLON
  public static boolean variable_declaration(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "variable_declaration")) return false;
    if (!nextTokenIs(builder_, IDENTIFIER)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, VARIABLE_DECLARATION, null);
    result_ = consumeTokens(builder_, 1, IDENTIFIER, EQUALS);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, expr(builder_, level_ + 1, -1));
    result_ = pinned_ && consumeToken(builder_, SEMICOLON) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // (for_element | if_element | bind_else_element | else_element | let_element | echo_element | assert_element)* expr
  static boolean vector_item(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "vector_item")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = vector_item_0(builder_, level_ + 1);
    result_ = result_ && expr(builder_, level_ + 1, -1);
    exit_section_(builder_, level_, marker_, result_, false, OpenSCADParser::recover_vector);
    return result_;
  }

  // (for_element | if_element | bind_else_element | else_element | let_element | echo_element | assert_element)*
  private static boolean vector_item_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "vector_item_0")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!vector_item_0_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "vector_item_0", pos_)) break;
    }
    return true;
  }

  // for_element | if_element | bind_else_element | else_element | let_element | echo_element | assert_element
  private static boolean vector_item_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "vector_item_0_0")) return false;
    boolean result_;
    result_ = for_element(builder_, level_ + 1);
    if (!result_) result_ = if_element(builder_, level_ + 1);
    if (!result_) result_ = bind_else_element(builder_, level_ + 1);
    if (!result_) result_ = else_element(builder_, level_ + 1);
    if (!result_) result_ = let_element(builder_, level_ + 1);
    if (!result_) result_ = echo_element(builder_, level_ + 1);
    if (!result_) result_ = assert_element(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // Expression root: expr
  // Operator priority table:
  // 0: POSTFIX(elvis_expr) BINARY(conditional_expr)
  // 1: BINARY(and_expr) BINARY(or_expr)
  // 2: BINARY(plus_expr) BINARY(minus_expr)
  // 3: BINARY(mul_expr) BINARY(div_expr) BINARY(modulo_expr) BINARY(power_expr)
  // 4: PREFIX(unary_plus_expr) PREFIX(unary_min_expr) PREFIX(unary_negate_expr)
  // 5: ATOM(range_expr) PREFIX(echo_expr) PREFIX(assert_expr) PREFIX(test_expr)
  //    ATOM(builtin_expr) ATOM(function_call_expr) ATOM(variable_ref_expr) ATOM(vector_expr)
  //    PREFIX(paren_expr) ATOM(literal_expr) PREFIX(function_literal_expr) POSTFIX(index_expr)
  //    POSTFIX(qualification_expr)
  public static boolean expr(PsiBuilder builder_, int level_, int priority_) {
    if (!recursion_guard_(builder_, level_, "expr")) return false;
    addVariant(builder_, "<expr>");
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<expr>");
    result_ = unary_plus_expr(builder_, level_ + 1);
    if (!result_) result_ = unary_min_expr(builder_, level_ + 1);
    if (!result_) result_ = unary_negate_expr(builder_, level_ + 1);
    if (!result_) result_ = range_expr(builder_, level_ + 1);
    if (!result_) result_ = echo_expr(builder_, level_ + 1);
    if (!result_) result_ = assert_expr(builder_, level_ + 1);
    if (!result_) result_ = test_expr(builder_, level_ + 1);
    if (!result_) result_ = builtin_expr(builder_, level_ + 1);
    if (!result_) result_ = function_call_expr(builder_, level_ + 1);
    if (!result_) result_ = variable_ref_expr(builder_, level_ + 1);
    if (!result_) result_ = vector_expr(builder_, level_ + 1);
    if (!result_) result_ = paren_expr(builder_, level_ + 1);
    if (!result_) result_ = literal_expr(builder_, level_ + 1);
    if (!result_) result_ = function_literal_expr(builder_, level_ + 1);
    pinned_ = result_;
    result_ = result_ && expr_0(builder_, level_ + 1, priority_);
    exit_section_(builder_, level_, marker_, null, result_, pinned_, null);
    return result_ || pinned_;
  }

  public static boolean expr_0(PsiBuilder builder_, int level_, int priority_) {
    if (!recursion_guard_(builder_, level_, "expr_0")) return false;
    boolean result_ = true;
    while (true) {
      Marker marker_ = enter_section_(builder_, level_, _LEFT_, null);
      if (priority_ < 0 && elvis_expr_0(builder_, level_ + 1)) {
        result_ = true;
        exit_section_(builder_, level_, marker_, ELVIS_EXPR, result_, true, null);
      }
      else if (priority_ < 0 && conditional_expr_0(builder_, level_ + 1)) {
        result_ = expr(builder_, level_, 0);
        exit_section_(builder_, level_, marker_, CONDITIONAL_EXPR, result_, true, null);
      }
      else if (priority_ < 1 && consumeTokenSmart(builder_, AND)) {
        result_ = expr(builder_, level_, 1);
        exit_section_(builder_, level_, marker_, AND_EXPR, result_, true, null);
      }
      else if (priority_ < 1 && consumeTokenSmart(builder_, OR)) {
        result_ = expr(builder_, level_, 1);
        exit_section_(builder_, level_, marker_, OR_EXPR, result_, true, null);
      }
      else if (priority_ < 2 && consumeTokenSmart(builder_, PLUS)) {
        result_ = expr(builder_, level_, 2);
        exit_section_(builder_, level_, marker_, PLUS_EXPR, result_, true, null);
      }
      else if (priority_ < 2 && consumeTokenSmart(builder_, MINUS)) {
        result_ = expr(builder_, level_, 2);
        exit_section_(builder_, level_, marker_, MINUS_EXPR, result_, true, null);
      }
      else if (priority_ < 3 && consumeTokenSmart(builder_, MUL)) {
        result_ = expr(builder_, level_, 3);
        exit_section_(builder_, level_, marker_, MUL_EXPR, result_, true, null);
      }
      else if (priority_ < 3 && consumeTokenSmart(builder_, DIV)) {
        result_ = expr(builder_, level_, 3);
        exit_section_(builder_, level_, marker_, DIV_EXPR, result_, true, null);
      }
      else if (priority_ < 3 && consumeTokenSmart(builder_, PERC)) {
        result_ = expr(builder_, level_, 3);
        exit_section_(builder_, level_, marker_, MODULO_EXPR, result_, true, null);
      }
      else if (priority_ < 3 && consumeTokenSmart(builder_, EXP)) {
        result_ = expr(builder_, level_, 3);
        exit_section_(builder_, level_, marker_, POWER_EXPR, result_, true, null);
      }
      else if (priority_ < 5 && index_expr_0(builder_, level_ + 1)) {
        result_ = true;
        exit_section_(builder_, level_, marker_, INDEX_EXPR, result_, true, null);
      }
      else if (priority_ < 5 && parseTokensSmart(builder_, 0, DOT, IDENTIFIER)) {
        result_ = true;
        exit_section_(builder_, level_, marker_, QUALIFICATION_EXPR, result_, true, null);
      }
      else {
        exit_section_(builder_, level_, marker_, null, false, false, null);
        break;
      }
    }
    return result_;
  }

  // QUERY (elvis_expr | expr) COLON (elvis_expr | expr)
  private static boolean elvis_expr_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "elvis_expr_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokenSmart(builder_, QUERY);
    result_ = result_ && elvis_expr_0_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, COLON);
    result_ = result_ && elvis_expr_0_3(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // elvis_expr | expr
  private static boolean elvis_expr_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "elvis_expr_0_1")) return false;
    boolean result_;
    result_ = expr(builder_, level_ + 1, -1);
    if (!result_) result_ = expr(builder_, level_ + 1, -1);
    return result_;
  }

  // elvis_expr | expr
  private static boolean elvis_expr_0_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "elvis_expr_0_3")) return false;
    boolean result_;
    result_ = expr(builder_, level_ + 1, -1);
    if (!result_) result_ = expr(builder_, level_ + 1, -1);
    return result_;
  }

  // LT | GT | LE | GE | EQ | NE
  private static boolean conditional_expr_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "conditional_expr_0")) return false;
    boolean result_;
    result_ = consumeTokenSmart(builder_, LT);
    if (!result_) result_ = consumeTokenSmart(builder_, GT);
    if (!result_) result_ = consumeTokenSmart(builder_, LE);
    if (!result_) result_ = consumeTokenSmart(builder_, GE);
    if (!result_) result_ = consumeTokenSmart(builder_, EQ);
    if (!result_) result_ = consumeTokenSmart(builder_, NE);
    return result_;
  }

  public static boolean unary_plus_expr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "unary_plus_expr")) return false;
    if (!nextTokenIsSmart(builder_, PLUS)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeTokenSmart(builder_, PLUS);
    pinned_ = result_;
    result_ = pinned_ && expr(builder_, level_, 4);
    exit_section_(builder_, level_, marker_, UNARY_PLUS_EXPR, result_, pinned_, null);
    return result_ || pinned_;
  }

  public static boolean unary_min_expr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "unary_min_expr")) return false;
    if (!nextTokenIsSmart(builder_, MINUS)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeTokenSmart(builder_, MINUS);
    pinned_ = result_;
    result_ = pinned_ && expr(builder_, level_, 4);
    exit_section_(builder_, level_, marker_, UNARY_MIN_EXPR, result_, pinned_, null);
    return result_ || pinned_;
  }

  public static boolean unary_negate_expr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "unary_negate_expr")) return false;
    if (!nextTokenIsSmart(builder_, EXCL)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeTokenSmart(builder_, EXCL);
    pinned_ = result_;
    result_ = pinned_ && expr(builder_, level_, 4);
    exit_section_(builder_, level_, marker_, UNARY_NEGATE_EXPR, result_, pinned_, null);
    return result_ || pinned_;
  }

  // [EACH_KEYWORD] LBRACKET expr COLON expr [ COLON expr ] RBRACKET
  public static boolean range_expr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "range_expr")) return false;
    if (!nextTokenIsSmart(builder_, EACH_KEYWORD, LBRACKET)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, RANGE_EXPR, "<range expr>");
    result_ = range_expr_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, LBRACKET);
    result_ = result_ && expr(builder_, level_ + 1, -1);
    result_ = result_ && consumeToken(builder_, COLON);
    result_ = result_ && expr(builder_, level_ + 1, -1);
    result_ = result_ && range_expr_5(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RBRACKET);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // [EACH_KEYWORD]
  private static boolean range_expr_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "range_expr_0")) return false;
    consumeTokenSmart(builder_, EACH_KEYWORD);
    return true;
  }

  // [ COLON expr ]
  private static boolean range_expr_5(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "range_expr_5")) return false;
    range_expr_5_0(builder_, level_ + 1);
    return true;
  }

  // COLON expr
  private static boolean range_expr_5_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "range_expr_5_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokenSmart(builder_, COLON);
    result_ = result_ && expr(builder_, level_ + 1, -1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  public static boolean echo_expr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "echo_expr")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = echo_element(builder_, level_ + 1);
    pinned_ = result_;
    result_ = pinned_ && expr(builder_, level_, -1);
    exit_section_(builder_, level_, marker_, ECHO_EXPR, result_, pinned_, null);
    return result_ || pinned_;
  }

  public static boolean assert_expr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "assert_expr")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = assert_element(builder_, level_ + 1);
    pinned_ = result_;
    result_ = pinned_ && expr(builder_, level_, -1);
    exit_section_(builder_, level_, marker_, ASSERT_EXPR, result_, pinned_, null);
    return result_ || pinned_;
  }

  public static boolean test_expr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "test_expr")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = test_expr_0(builder_, level_ + 1);
    pinned_ = result_;
    result_ = pinned_ && expr(builder_, level_, -1);
    result_ = pinned_ && report_error_(builder_, consumeToken(builder_, RPARENTH)) && result_;
    exit_section_(builder_, level_, marker_, TEST_EXPR, result_, pinned_, null);
    return result_ || pinned_;
  }

  // test_exp_ref LPARENTH
  private static boolean test_expr_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "test_expr_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = test_exp_ref(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, LPARENTH);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // builtin_expr_ref arg_assignment_list
  //                | LET_KEYWORD full_arg_declaration_list expr
  public static boolean builtin_expr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "builtin_expr")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, BUILTIN_EXPR, "<builtin expr>");
    result_ = builtin_expr_0(builder_, level_ + 1);
    if (!result_) result_ = builtin_expr_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // builtin_expr_ref arg_assignment_list
  private static boolean builtin_expr_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "builtin_expr_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = builtin_expr_ref(builder_, level_ + 1);
    result_ = result_ && arg_assignment_list(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // LET_KEYWORD full_arg_declaration_list expr
  private static boolean builtin_expr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "builtin_expr_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokenSmart(builder_, LET_KEYWORD);
    result_ = result_ && full_arg_declaration_list(builder_, level_ + 1);
    result_ = result_ && expr(builder_, level_ + 1, -1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // function_name_ref arg_assignment_list+
  public static boolean function_call_expr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "function_call_expr")) return false;
    if (!nextTokenIsSmart(builder_, IDENTIFIER)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = function_name_ref(builder_, level_ + 1);
    result_ = result_ && function_call_expr_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, FUNCTION_CALL_EXPR, result_);
    return result_;
  }

  // arg_assignment_list+
  private static boolean function_call_expr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "function_call_expr_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = arg_assignment_list(builder_, level_ + 1);
    while (result_) {
      int pos_ = current_position_(builder_);
      if (!arg_assignment_list(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "function_call_expr_1", pos_)) break;
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // IDENTIFIER
  public static boolean variable_ref_expr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "variable_ref_expr")) return false;
    if (!nextTokenIsSmart(builder_, IDENTIFIER)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokenSmart(builder_, IDENTIFIER);
    exit_section_(builder_, marker_, VARIABLE_REF_EXPR, result_);
    return result_;
  }

  // [EACH_KEYWORD] LBRACKET [vector_item ( COMMA+ (vector_item | &RBRACKET) )*] RBRACKET
  public static boolean vector_expr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "vector_expr")) return false;
    if (!nextTokenIsSmart(builder_, EACH_KEYWORD, LBRACKET)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, VECTOR_EXPR, "<vector expr>");
    result_ = vector_expr_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, LBRACKET);
    pinned_ = result_; // pin = 2
    result_ = result_ && report_error_(builder_, vector_expr_2(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, RBRACKET) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // [EACH_KEYWORD]
  private static boolean vector_expr_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "vector_expr_0")) return false;
    consumeTokenSmart(builder_, EACH_KEYWORD);
    return true;
  }

  // [vector_item ( COMMA+ (vector_item | &RBRACKET) )*]
  private static boolean vector_expr_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "vector_expr_2")) return false;
    vector_expr_2_0(builder_, level_ + 1);
    return true;
  }

  // vector_item ( COMMA+ (vector_item | &RBRACKET) )*
  private static boolean vector_expr_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "vector_expr_2_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = vector_item(builder_, level_ + 1);
    result_ = result_ && vector_expr_2_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ( COMMA+ (vector_item | &RBRACKET) )*
  private static boolean vector_expr_2_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "vector_expr_2_0_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!vector_expr_2_0_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "vector_expr_2_0_1", pos_)) break;
    }
    return true;
  }

  // COMMA+ (vector_item | &RBRACKET)
  private static boolean vector_expr_2_0_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "vector_expr_2_0_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = vector_expr_2_0_1_0_0(builder_, level_ + 1);
    result_ = result_ && vector_expr_2_0_1_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // COMMA+
  private static boolean vector_expr_2_0_1_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "vector_expr_2_0_1_0_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokenSmart(builder_, COMMA);
    while (result_) {
      int pos_ = current_position_(builder_);
      if (!consumeTokenSmart(builder_, COMMA)) break;
      if (!empty_element_parsed_guard_(builder_, "vector_expr_2_0_1_0_0", pos_)) break;
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // vector_item | &RBRACKET
  private static boolean vector_expr_2_0_1_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "vector_expr_2_0_1_0_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = vector_item(builder_, level_ + 1);
    if (!result_) result_ = vector_expr_2_0_1_0_1_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // &RBRACKET
  private static boolean vector_expr_2_0_1_0_1_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "vector_expr_2_0_1_0_1_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _AND_);
    result_ = consumeTokenSmart(builder_, RBRACKET);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  public static boolean paren_expr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "paren_expr")) return false;
    if (!nextTokenIsSmart(builder_, LPARENTH)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeTokenSmart(builder_, LPARENTH);
    pinned_ = result_;
    result_ = pinned_ && expr(builder_, level_, -1);
    result_ = pinned_ && report_error_(builder_, consumeToken(builder_, RPARENTH)) && result_;
    exit_section_(builder_, level_, marker_, PAREN_EXPR, result_, pinned_, null);
    return result_ || pinned_;
  }

  // TRUE_KEYWORD
  //                | FALSE_KEYWORD
  //                | UNDEF_KEYWORD
  //                | NUMBER_LITERAL
  //                | STRING_LITERAL
  public static boolean literal_expr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "literal_expr")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, LITERAL_EXPR, "<literal expr>");
    result_ = consumeTokenSmart(builder_, TRUE_KEYWORD);
    if (!result_) result_ = consumeTokenSmart(builder_, FALSE_KEYWORD);
    if (!result_) result_ = consumeTokenSmart(builder_, UNDEF_KEYWORD);
    if (!result_) result_ = consumeTokenSmart(builder_, NUMBER_LITERAL);
    if (!result_) result_ = consumeTokenSmart(builder_, STRING_LITERAL);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  public static boolean function_literal_expr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "function_literal_expr")) return false;
    if (!nextTokenIsSmart(builder_, FUNCTION_KEYWORD)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = function_literal_expr_0(builder_, level_ + 1);
    pinned_ = result_;
    result_ = pinned_ && expr(builder_, level_, -1);
    exit_section_(builder_, level_, marker_, FUNCTION_LITERAL_EXPR, result_, pinned_, null);
    return result_ || pinned_;
  }

  // FUNCTION_KEYWORD arg_declaration_list
  private static boolean function_literal_expr_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "function_literal_expr_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokenSmart(builder_, FUNCTION_KEYWORD);
    result_ = result_ && arg_declaration_list(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // LBRACKET <<innerExpression>> RBRACKET
  private static boolean index_expr_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "index_expr_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokenSmart(builder_, LBRACKET);
    result_ = result_ && innerExpression(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RBRACKET);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

}
