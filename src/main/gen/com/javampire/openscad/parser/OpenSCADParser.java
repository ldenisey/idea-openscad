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

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, EXTENDS_SETS_);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    r = parse_root_(t, b);
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b) {
    return parse_root_(t, b, 0);
  }

  static boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return openSCADFile(b, l + 1);
  }

  public static final TokenSet[] EXTENDS_SETS_ = new TokenSet[] {
    create_token_set_(BLOCK_OBJ, BUILTIN_OBJ, EMPTY_OBJ, FOR_OBJ,
      IF_OBJ, MODULE_CALL_OBJ, OBJECT),
    create_token_set_(BACKGROUND_OP, BUILTIN_OP, DEBUG_OP, DISABLE_OP,
      MODIFIER_OP, MODULE_CALL_OP, OPERATOR, ROOT_OP),
    create_token_set_(AND_EXPR, ASSERT_EXPR, BUILTIN_EXPR, CONDITIONAL_EXPR,
      DIV_EXPR, EACH_EXPR, ECHO_EXPR, ELVIS_EXPR,
      EXPR, FUNCTION_CALL_EXPR, FUNCTION_LITERAL_EXPR, INDEX_EXPR,
      LIST_EXPR, LITERAL_EXPR, MINUS_EXPR, MODULO_EXPR,
      MUL_EXPR, OR_EXPR, PAREN_EXPR, PLUS_EXPR,
      POWER_EXPR, QUALIFICATION_EXPR, RANGE_EXPR, TEST_EXPR,
      UNARY_MIN_EXPR, UNARY_NEGATE_EXPR, UNARY_PLUS_EXPR, VARIABLE_REF_EXPR,
      VECTOR_EXPR),
  };

  /* ********************************************************** */
  // [ parameter_reference EQUALS ] expr
  public static boolean arg_assignment(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arg_assignment")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ARG_ASSIGNMENT, "<arg assignment>");
    r = arg_assignment_0(b, l + 1);
    r = r && expr(b, l + 1, -1);
    exit_section_(b, l, m, r, false, OpenSCADParser::recover_arg);
    return r;
  }

  // [ parameter_reference EQUALS ]
  private static boolean arg_assignment_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arg_assignment_0")) return false;
    arg_assignment_0_0(b, l + 1);
    return true;
  }

  // parameter_reference EQUALS
  private static boolean arg_assignment_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arg_assignment_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = parameter_reference(b, l + 1);
    r = r && consumeToken(b, EQUALS);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // LPARENTH arg_assignment? ( COMMA arg_assignment )* RPARENTH
  public static boolean arg_assignment_list(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arg_assignment_list")) return false;
    if (!nextTokenIs(b, LPARENTH)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, ARG_ASSIGNMENT_LIST, null);
    r = consumeToken(b, LPARENTH);
    p = r; // pin = 1
    r = r && report_error_(b, arg_assignment_list_1(b, l + 1));
    r = p && report_error_(b, arg_assignment_list_2(b, l + 1)) && r;
    r = p && consumeToken(b, RPARENTH) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // arg_assignment?
  private static boolean arg_assignment_list_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arg_assignment_list_1")) return false;
    arg_assignment(b, l + 1);
    return true;
  }

  // ( COMMA arg_assignment )*
  private static boolean arg_assignment_list_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arg_assignment_list_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!arg_assignment_list_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "arg_assignment_list_2", c)) break;
    }
    return true;
  }

  // COMMA arg_assignment
  private static boolean arg_assignment_list_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arg_assignment_list_2_0")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeToken(b, COMMA);
    p = r; // pin = 1
    r = r && arg_assignment(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // IDENTIFIER [EQUALS expr]
  public static boolean arg_declaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arg_declaration")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ARG_DECLARATION, "<arg declaration>");
    r = consumeToken(b, IDENTIFIER);
    r = r && arg_declaration_1(b, l + 1);
    exit_section_(b, l, m, r, false, OpenSCADParser::recover_arg);
    return r;
  }

  // [EQUALS expr]
  private static boolean arg_declaration_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arg_declaration_1")) return false;
    arg_declaration_1_0(b, l + 1);
    return true;
  }

  // EQUALS expr
  private static boolean arg_declaration_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arg_declaration_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, EQUALS);
    r = r && expr(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // LPARENTH arg_declaration? ( COMMA arg_declaration? )* RPARENTH
  public static boolean arg_declaration_list(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arg_declaration_list")) return false;
    if (!nextTokenIs(b, LPARENTH)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, ARG_DECLARATION_LIST, null);
    r = consumeToken(b, LPARENTH);
    p = r; // pin = 1
    r = r && report_error_(b, arg_declaration_list_1(b, l + 1));
    r = p && report_error_(b, arg_declaration_list_2(b, l + 1)) && r;
    r = p && consumeToken(b, RPARENTH) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // arg_declaration?
  private static boolean arg_declaration_list_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arg_declaration_list_1")) return false;
    arg_declaration(b, l + 1);
    return true;
  }

  // ( COMMA arg_declaration? )*
  private static boolean arg_declaration_list_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arg_declaration_list_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!arg_declaration_list_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "arg_declaration_list_2", c)) break;
    }
    return true;
  }

  // COMMA arg_declaration?
  private static boolean arg_declaration_list_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arg_declaration_list_2_0")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeToken(b, COMMA);
    p = r; // pin = 1
    r = r && arg_declaration_list_2_0_1(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // arg_declaration?
  private static boolean arg_declaration_list_2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arg_declaration_list_2_0_1")) return false;
    arg_declaration(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // LPARENTH (expr | IDENTIFIER) (COMMA (expr | IDENTIFIER |  STRING_LITERAL))* RPARENTH
  public static boolean assert_arg_list(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "assert_arg_list")) return false;
    if (!nextTokenIs(b, LPARENTH)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LPARENTH);
    r = r && assert_arg_list_1(b, l + 1);
    r = r && assert_arg_list_2(b, l + 1);
    r = r && consumeToken(b, RPARENTH);
    exit_section_(b, m, ASSERT_ARG_LIST, r);
    return r;
  }

  // expr | IDENTIFIER
  private static boolean assert_arg_list_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "assert_arg_list_1")) return false;
    boolean r;
    r = expr(b, l + 1, -1);
    if (!r) r = consumeToken(b, IDENTIFIER);
    return r;
  }

  // (COMMA (expr | IDENTIFIER |  STRING_LITERAL))*
  private static boolean assert_arg_list_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "assert_arg_list_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!assert_arg_list_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "assert_arg_list_2", c)) break;
    }
    return true;
  }

  // COMMA (expr | IDENTIFIER |  STRING_LITERAL)
  private static boolean assert_arg_list_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "assert_arg_list_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && assert_arg_list_2_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // expr | IDENTIFIER |  STRING_LITERAL
  private static boolean assert_arg_list_2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "assert_arg_list_2_0_1")) return false;
    boolean r;
    r = expr(b, l + 1, -1);
    if (!r) r = consumeToken(b, IDENTIFIER);
    if (!r) r = consumeToken(b, STRING_LITERAL);
    return r;
  }

  /* ********************************************************** */
  // ASSERT_KEYWORD assert_arg_list
  public static boolean assert_element(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "assert_element")) return false;
    if (!nextTokenIs(b, ASSERT_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ASSERT_KEYWORD);
    r = r && assert_arg_list(b, l + 1);
    exit_section_(b, m, ASSERT_ELEMENT, r);
    return r;
  }

  /* ********************************************************** */
  // PERC
  public static boolean background_op(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "background_op")) return false;
    if (!nextTokenIs(b, PERC)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PERC);
    exit_section_(b, m, BACKGROUND_OP, r);
    return r;
  }

  /* ********************************************************** */
  // LPARENTH if_element expr RPARENTH
  public static boolean bind_else_element(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "bind_else_element")) return false;
    if (!nextTokenIs(b, LPARENTH)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LPARENTH);
    r = r && if_element(b, l + 1);
    r = r && expr(b, l + 1, -1);
    r = r && consumeToken(b, RPARENTH);
    exit_section_(b, m, BIND_ELSE_ELEMENT, r);
    return r;
  }

  /* ********************************************************** */
  // LBRACE statement* RBRACE
  public static boolean block_obj(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "block_obj")) return false;
    if (!nextTokenIs(b, LBRACE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACE);
    r = r && block_obj_1(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, BLOCK_OBJ, r);
    return r;
  }

  // statement*
  private static boolean block_obj_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "block_obj_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!statement(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "block_obj_1", c)) break;
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
  public static boolean builtin_expr_ref(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "builtin_expr_ref")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, BUILTIN_EXPR_REF, "<builtin expr ref>");
    r = consumeToken(b, ABS_KEYWORD);
    if (!r) r = consumeToken(b, ACOS_KEYWORD);
    if (!r) r = consumeToken(b, ASIN_KEYWORD);
    if (!r) r = consumeToken(b, ATAN_KEYWORD);
    if (!r) r = consumeToken(b, ATAN2_KEYWORD);
    if (!r) r = consumeToken(b, CEIL_KEYWORD);
    if (!r) r = consumeToken(b, CHR_KEYWORD);
    if (!r) r = consumeToken(b, CONCAT_KEYWORD);
    if (!r) r = consumeToken(b, COS_KEYWORD);
    if (!r) r = consumeToken(b, CROSS_KEYWORD);
    if (!r) r = consumeToken(b, EXP_KEYWORD);
    if (!r) r = consumeToken(b, FLOOR_KEYWORD);
    if (!r) r = consumeToken(b, LEN_KEYWORD);
    if (!r) r = consumeToken(b, LN_KEYWORD);
    if (!r) r = consumeToken(b, LOG_KEYWORD);
    if (!r) r = consumeToken(b, LOOKUP_KEYWORD);
    if (!r) r = consumeToken(b, MAX_KEYWORD);
    if (!r) r = consumeToken(b, MIN_KEYWORD);
    if (!r) r = consumeToken(b, NORM_KEYWORD);
    if (!r) r = consumeToken(b, ORD_KEYWORD);
    if (!r) r = consumeToken(b, PARENT_MODULE_KEYWORD);
    if (!r) r = consumeToken(b, POW_KEYWORD);
    if (!r) r = consumeToken(b, RANDS_KEYWORD);
    if (!r) r = consumeToken(b, ROUND_KEYWORD);
    if (!r) r = consumeToken(b, SEARCH_KEYWORD);
    if (!r) r = consumeToken(b, SIGN_KEYWORD);
    if (!r) r = consumeToken(b, SIN_KEYWORD);
    if (!r) r = consumeToken(b, SQRT_KEYWORD);
    if (!r) r = consumeToken(b, STR_KEYWORD);
    if (!r) r = consumeToken(b, TAN_KEYWORD);
    if (!r) r = consumeToken(b, VERSION_KEYWORD);
    if (!r) r = consumeToken(b, VERSION_NUM_KEYWORD);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // (builtin_obj_ref arg_assignment_list SEMICOLON)
  //                 | (builtin_overridable_obj_ref arg_assignment_list SEMICOLON)
  //                 | (builtin_op block_obj)
  //                 | (builtin_op statement)
  //                 | (echo_element+ object)
  //                 | (assert_element+ object)
  public static boolean builtin_obj(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "builtin_obj")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, BUILTIN_OBJ, "<builtin obj>");
    r = builtin_obj_0(b, l + 1);
    if (!r) r = builtin_obj_1(b, l + 1);
    if (!r) r = builtin_obj_2(b, l + 1);
    if (!r) r = builtin_obj_3(b, l + 1);
    if (!r) r = builtin_obj_4(b, l + 1);
    if (!r) r = builtin_obj_5(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // builtin_obj_ref arg_assignment_list SEMICOLON
  private static boolean builtin_obj_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "builtin_obj_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = builtin_obj_ref(b, l + 1);
    r = r && arg_assignment_list(b, l + 1);
    r = r && consumeToken(b, SEMICOLON);
    exit_section_(b, m, null, r);
    return r;
  }

  // builtin_overridable_obj_ref arg_assignment_list SEMICOLON
  private static boolean builtin_obj_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "builtin_obj_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = builtin_overridable_obj_ref(b, l + 1);
    r = r && arg_assignment_list(b, l + 1);
    r = r && consumeToken(b, SEMICOLON);
    exit_section_(b, m, null, r);
    return r;
  }

  // builtin_op block_obj
  private static boolean builtin_obj_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "builtin_obj_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = builtin_op(b, l + 1);
    r = r && block_obj(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // builtin_op statement
  private static boolean builtin_obj_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "builtin_obj_3")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = builtin_op(b, l + 1);
    r = r && statement(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // echo_element+ object
  private static boolean builtin_obj_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "builtin_obj_4")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = builtin_obj_4_0(b, l + 1);
    r = r && object(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // echo_element+
  private static boolean builtin_obj_4_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "builtin_obj_4_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = echo_element(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!echo_element(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "builtin_obj_4_0", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // assert_element+ object
  private static boolean builtin_obj_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "builtin_obj_5")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = builtin_obj_5_0(b, l + 1);
    r = r && object(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // assert_element+
  private static boolean builtin_obj_5_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "builtin_obj_5_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = assert_element(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!assert_element(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "builtin_obj_5_0", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ASSERT_KEYWORD
  //                   | CHILD_KEYWORD
  //                   | CHILDREN_KEYWORD
  //                   | ECHO_KEYWORD
  //                   | IMPORT_KEYWORD
  //                   | IMPORT_DXF_KEYWORD
  //                   | IMPORT_STL_KEYWORD
  //                   | POLYGON_KEYWORD
  //                   | POLYHEDRON_KEYWORD
  //                   | SURFACE_KEYWORD
  //                   | TEXT_KEYWORD
  public static boolean builtin_obj_ref(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "builtin_obj_ref")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, BUILTIN_OBJ_REF, "<builtin obj ref>");
    r = consumeToken(b, ASSERT_KEYWORD);
    if (!r) r = consumeToken(b, CHILD_KEYWORD);
    if (!r) r = consumeToken(b, CHILDREN_KEYWORD);
    if (!r) r = consumeToken(b, ECHO_KEYWORD);
    if (!r) r = consumeToken(b, IMPORT_KEYWORD);
    if (!r) r = consumeToken(b, IMPORT_DXF_KEYWORD);
    if (!r) r = consumeToken(b, IMPORT_STL_KEYWORD);
    if (!r) r = consumeToken(b, POLYGON_KEYWORD);
    if (!r) r = consumeToken(b, POLYHEDRON_KEYWORD);
    if (!r) r = consumeToken(b, SURFACE_KEYWORD);
    if (!r) r = consumeToken(b, TEXT_KEYWORD);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // common_op_ref arg_assignment_list
  //                 | builtin_overridable_op_ref arg_assignment_list
  //                 | builtin_overridable_op_as_function_ref arg_assignment_list
  public static boolean builtin_op(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "builtin_op")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, BUILTIN_OP, "<builtin op>");
    r = builtin_op_0(b, l + 1);
    if (!r) r = builtin_op_1(b, l + 1);
    if (!r) r = builtin_op_2(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // common_op_ref arg_assignment_list
  private static boolean builtin_op_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "builtin_op_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = common_op_ref(b, l + 1);
    r = r && arg_assignment_list(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // builtin_overridable_op_ref arg_assignment_list
  private static boolean builtin_op_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "builtin_op_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = builtin_overridable_op_ref(b, l + 1);
    r = r && arg_assignment_list(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // builtin_overridable_op_as_function_ref arg_assignment_list
  private static boolean builtin_op_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "builtin_op_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = builtin_overridable_op_as_function_ref(b, l + 1);
    r = r && arg_assignment_list(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // builtin_overridable_op_as_function_ref arg_assignment_list
  //                             | builtin_overridable_op_ref arg_assignment_list
  public static boolean builtin_overridable_expr_ref(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "builtin_overridable_expr_ref")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, BUILTIN_OVERRIDABLE_EXPR_REF, "<builtin overridable expr ref>");
    r = builtin_overridable_expr_ref_0(b, l + 1);
    if (!r) r = builtin_overridable_expr_ref_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // builtin_overridable_op_as_function_ref arg_assignment_list
  private static boolean builtin_overridable_expr_ref_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "builtin_overridable_expr_ref_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = builtin_overridable_op_as_function_ref(b, l + 1);
    r = r && arg_assignment_list(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // builtin_overridable_op_ref arg_assignment_list
  private static boolean builtin_overridable_expr_ref_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "builtin_overridable_expr_ref_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = builtin_overridable_op_ref(b, l + 1);
    r = r && arg_assignment_list(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // CIRCLE_KEYWORD
  //                   | CUBE_KEYWORD
  //                   | CYLINDER_KEYWORD
  //                   | SPHERE_KEYWORD
  //                   | SQUARE_KEYWORD
  static boolean builtin_overridable_obj_keywords(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "builtin_overridable_obj_keywords")) return false;
    boolean r;
    r = consumeToken(b, CIRCLE_KEYWORD);
    if (!r) r = consumeToken(b, CUBE_KEYWORD);
    if (!r) r = consumeToken(b, CYLINDER_KEYWORD);
    if (!r) r = consumeToken(b, SPHERE_KEYWORD);
    if (!r) r = consumeToken(b, SQUARE_KEYWORD);
    return r;
  }

  /* ********************************************************** */
  // builtin_overridable_obj_keywords
  public static boolean builtin_overridable_obj_ref(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "builtin_overridable_obj_ref")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, BUILTIN_OVERRIDABLE_OBJ_REF, "<builtin overridable obj ref>");
    r = builtin_overridable_obj_keywords(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // builtin_overridable_op_keywords | builtin_overridable_obj_keywords
  public static boolean builtin_overridable_op_as_function_ref(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "builtin_overridable_op_as_function_ref")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, BUILTIN_OVERRIDABLE_OP_AS_FUNCTION_REF, "<builtin overridable op as function ref>");
    r = builtin_overridable_op_keywords(b, l + 1);
    if (!r) r = builtin_overridable_obj_keywords(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // OFFSET_KEYWORD
  //                   | TRANSLATE_KEYWORD
  //                   | HULL_KEYWORD
  //                   | MIRROR_KEYWORD
  //                   | SCALE_KEYWORD
  //                   | DIFFERENCE_KEYWORD
  //                   | INTERSECTION_KEYWORD
  //                   | UNION_KEYWORD
  static boolean builtin_overridable_op_keywords(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "builtin_overridable_op_keywords")) return false;
    boolean r;
    r = consumeToken(b, OFFSET_KEYWORD);
    if (!r) r = consumeToken(b, TRANSLATE_KEYWORD);
    if (!r) r = consumeToken(b, HULL_KEYWORD);
    if (!r) r = consumeToken(b, MIRROR_KEYWORD);
    if (!r) r = consumeToken(b, SCALE_KEYWORD);
    if (!r) r = consumeToken(b, DIFFERENCE_KEYWORD);
    if (!r) r = consumeToken(b, INTERSECTION_KEYWORD);
    if (!r) r = consumeToken(b, UNION_KEYWORD);
    return r;
  }

  /* ********************************************************** */
  // builtin_overridable_op_keywords
  public static boolean builtin_overridable_op_ref(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "builtin_overridable_op_ref")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, BUILTIN_OVERRIDABLE_OP_REF, "<builtin overridable op ref>");
    r = builtin_overridable_op_keywords(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // COMMENT_C_STYLE | COMMENT_DOC | COMMENT_SINGLELINE | COMMENT_SINGLELINE_BLOCK | COMMENT_CUSTOMIZER_VALUE | COMMENT_CUSTOMIZER_TABS
  static boolean comment_item(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "comment_item")) return false;
    boolean r;
    r = consumeToken(b, COMMENT_C_STYLE);
    if (!r) r = consumeToken(b, COMMENT_DOC);
    if (!r) r = consumeToken(b, COMMENT_SINGLELINE);
    if (!r) r = consumeToken(b, COMMENT_SINGLELINE_BLOCK);
    if (!r) r = consumeToken(b, COMMENT_CUSTOMIZER_VALUE);
    if (!r) r = consumeToken(b, COMMENT_CUSTOMIZER_TABS);
    return r;
  }

  /* ********************************************************** */
  // COLOR_KEYWORD
  //                 | LINEAR_EXTRUDE_KEYWORD
  //                 | MINKOWSKI_KEYWORD
  //                 | MULTMATRIX_KEYWORD
  //                 | PROJECTION_KEYWORD
  //                 | RENDER_KEYWORD
  //                 | RESIZE_KEYWORD
  //                 | ROTATE_KEYWORD
  //                 | ROTATE_EXTRUDE_KEYWORD
  public static boolean common_op_ref(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "common_op_ref")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, COMMON_OP_REF, "<common op ref>");
    r = consumeToken(b, COLOR_KEYWORD);
    if (!r) r = consumeToken(b, LINEAR_EXTRUDE_KEYWORD);
    if (!r) r = consumeToken(b, MINKOWSKI_KEYWORD);
    if (!r) r = consumeToken(b, MULTMATRIX_KEYWORD);
    if (!r) r = consumeToken(b, PROJECTION_KEYWORD);
    if (!r) r = consumeToken(b, RENDER_KEYWORD);
    if (!r) r = consumeToken(b, RESIZE_KEYWORD);
    if (!r) r = consumeToken(b, ROTATE_KEYWORD);
    if (!r) r = consumeToken(b, ROTATE_EXTRUDE_KEYWORD);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // operator object
  static boolean compound_obj(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "compound_obj")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = operator(b, l + 1);
    r = r && object(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // HASH
  public static boolean debug_op(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "debug_op")) return false;
    if (!nextTokenIs(b, HASH)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, HASH);
    exit_section_(b, m, DEBUG_OP, r);
    return r;
  }

  /* ********************************************************** */
  // variable_declaration | module_declaration | function_declaration
  static boolean declaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "declaration")) return false;
    boolean r;
    r = variable_declaration(b, l + 1);
    if (!r) r = module_declaration(b, l + 1);
    if (!r) r = function_declaration(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // MUL
  public static boolean disable_op(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "disable_op")) return false;
    if (!nextTokenIs(b, MUL)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, MUL);
    exit_section_(b, m, DISABLE_OP, r);
    return r;
  }

  /* ********************************************************** */
  // LPARENTH ((IDENTIFIER EQUALS expr) | expr | IDENTIFIER | STRING_LITERAL) (COMMA ((IDENTIFIER EQUALS expr) | expr | IDENTIFIER |  STRING_LITERAL))* RPARENTH
  public static boolean echo_arg_list(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "echo_arg_list")) return false;
    if (!nextTokenIs(b, LPARENTH)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LPARENTH);
    r = r && echo_arg_list_1(b, l + 1);
    r = r && echo_arg_list_2(b, l + 1);
    r = r && consumeToken(b, RPARENTH);
    exit_section_(b, m, ECHO_ARG_LIST, r);
    return r;
  }

  // (IDENTIFIER EQUALS expr) | expr | IDENTIFIER | STRING_LITERAL
  private static boolean echo_arg_list_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "echo_arg_list_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = echo_arg_list_1_0(b, l + 1);
    if (!r) r = expr(b, l + 1, -1);
    if (!r) r = consumeToken(b, IDENTIFIER);
    if (!r) r = consumeToken(b, STRING_LITERAL);
    exit_section_(b, m, null, r);
    return r;
  }

  // IDENTIFIER EQUALS expr
  private static boolean echo_arg_list_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "echo_arg_list_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, IDENTIFIER, EQUALS);
    r = r && expr(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (COMMA ((IDENTIFIER EQUALS expr) | expr | IDENTIFIER |  STRING_LITERAL))*
  private static boolean echo_arg_list_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "echo_arg_list_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!echo_arg_list_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "echo_arg_list_2", c)) break;
    }
    return true;
  }

  // COMMA ((IDENTIFIER EQUALS expr) | expr | IDENTIFIER |  STRING_LITERAL)
  private static boolean echo_arg_list_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "echo_arg_list_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && echo_arg_list_2_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (IDENTIFIER EQUALS expr) | expr | IDENTIFIER |  STRING_LITERAL
  private static boolean echo_arg_list_2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "echo_arg_list_2_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = echo_arg_list_2_0_1_0(b, l + 1);
    if (!r) r = expr(b, l + 1, -1);
    if (!r) r = consumeToken(b, IDENTIFIER);
    if (!r) r = consumeToken(b, STRING_LITERAL);
    exit_section_(b, m, null, r);
    return r;
  }

  // IDENTIFIER EQUALS expr
  private static boolean echo_arg_list_2_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "echo_arg_list_2_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, IDENTIFIER, EQUALS);
    r = r && expr(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ECHO_KEYWORD echo_arg_list
  public static boolean echo_element(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "echo_element")) return false;
    if (!nextTokenIs(b, ECHO_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ECHO_KEYWORD);
    r = r && echo_arg_list(b, l + 1);
    exit_section_(b, m, ECHO_ELEMENT, r);
    return r;
  }

  /* ********************************************************** */
  // ELSE_KEYWORD [if_element]
  public static boolean else_element(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "else_element")) return false;
    if (!nextTokenIs(b, ELSE_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ELSE_KEYWORD);
    r = r && else_element_1(b, l + 1);
    exit_section_(b, m, ELSE_ELEMENT, r);
    return r;
  }

  // [if_element]
  private static boolean else_element_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "else_element_1")) return false;
    if_element(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // SEMICOLON
  public static boolean empty_obj(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "empty_obj")) return false;
    if (!nextTokenIs(b, SEMICOLON)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SEMICOLON);
    exit_section_(b, m, EMPTY_OBJ, r);
    return r;
  }

  /* ********************************************************** */
  // for_declaration_cstyle | (IDENTIFIER EQUALS (list_expr | range_expr | expr) (COMMA IDENTIFIER EQUALS (list_expr | range_expr | expr))*)
  public static boolean for_declaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "for_declaration")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FOR_DECLARATION, "<for declaration>");
    r = for_declaration_cstyle(b, l + 1);
    if (!r) r = for_declaration_1(b, l + 1);
    exit_section_(b, l, m, r, false, OpenSCADParser::recover_arg);
    return r;
  }

  // IDENTIFIER EQUALS (list_expr | range_expr | expr) (COMMA IDENTIFIER EQUALS (list_expr | range_expr | expr))*
  private static boolean for_declaration_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "for_declaration_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, IDENTIFIER, EQUALS);
    r = r && for_declaration_1_2(b, l + 1);
    r = r && for_declaration_1_3(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // list_expr | range_expr | expr
  private static boolean for_declaration_1_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "for_declaration_1_2")) return false;
    boolean r;
    r = list_expr(b, l + 1);
    if (!r) r = range_expr(b, l + 1);
    if (!r) r = expr(b, l + 1, -1);
    return r;
  }

  // (COMMA IDENTIFIER EQUALS (list_expr | range_expr | expr))*
  private static boolean for_declaration_1_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "for_declaration_1_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!for_declaration_1_3_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "for_declaration_1_3", c)) break;
    }
    return true;
  }

  // COMMA IDENTIFIER EQUALS (list_expr | range_expr | expr)
  private static boolean for_declaration_1_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "for_declaration_1_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, COMMA, IDENTIFIER, EQUALS);
    r = r && for_declaration_1_3_0_3(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // list_expr | range_expr | expr
  private static boolean for_declaration_1_3_0_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "for_declaration_1_3_0_3")) return false;
    boolean r;
    r = list_expr(b, l + 1);
    if (!r) r = range_expr(b, l + 1);
    if (!r) r = expr(b, l + 1, -1);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER EQUALS expr (COMMA IDENTIFIER EQUALS expr)* SEMICOLON conditional_expr (COMA conditional_expr)* SEMICOLON IDENTIFIER EQUALS expr (COMMA IDENTIFIER EQUALS expr)*
  public static boolean for_declaration_cstyle(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "for_declaration_cstyle")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, IDENTIFIER, EQUALS);
    r = r && expr(b, l + 1, -1);
    r = r && for_declaration_cstyle_3(b, l + 1);
    r = r && consumeToken(b, SEMICOLON);
    r = r && expr(b, l + 1, -1);
    r = r && for_declaration_cstyle_6(b, l + 1);
    r = r && consumeTokens(b, 0, SEMICOLON, IDENTIFIER, EQUALS);
    r = r && expr(b, l + 1, -1);
    r = r && for_declaration_cstyle_11(b, l + 1);
    exit_section_(b, m, FOR_DECLARATION_CSTYLE, r);
    return r;
  }

  // (COMMA IDENTIFIER EQUALS expr)*
  private static boolean for_declaration_cstyle_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "for_declaration_cstyle_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!for_declaration_cstyle_3_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "for_declaration_cstyle_3", c)) break;
    }
    return true;
  }

  // COMMA IDENTIFIER EQUALS expr
  private static boolean for_declaration_cstyle_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "for_declaration_cstyle_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, COMMA, IDENTIFIER, EQUALS);
    r = r && expr(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (COMA conditional_expr)*
  private static boolean for_declaration_cstyle_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "for_declaration_cstyle_6")) return false;
    while (true) {
      int c = current_position_(b);
      if (!for_declaration_cstyle_6_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "for_declaration_cstyle_6", c)) break;
    }
    return true;
  }

  // COMA conditional_expr
  private static boolean for_declaration_cstyle_6_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "for_declaration_cstyle_6_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMA);
    r = r && expr(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (COMMA IDENTIFIER EQUALS expr)*
  private static boolean for_declaration_cstyle_11(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "for_declaration_cstyle_11")) return false;
    while (true) {
      int c = current_position_(b);
      if (!for_declaration_cstyle_11_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "for_declaration_cstyle_11", c)) break;
    }
    return true;
  }

  // COMMA IDENTIFIER EQUALS expr
  private static boolean for_declaration_cstyle_11_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "for_declaration_cstyle_11_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, COMMA, IDENTIFIER, EQUALS);
    r = r && expr(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // LPARENTH for_declaration RPARENTH
  public static boolean for_declaration_list(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "for_declaration_list")) return false;
    if (!nextTokenIs(b, LPARENTH)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, FOR_DECLARATION_LIST, null);
    r = consumeToken(b, LPARENTH);
    p = r; // pin = 1
    r = r && report_error_(b, for_declaration(b, l + 1));
    r = p && consumeToken(b, RPARENTH) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // FOR_KEYWORD for_declaration_list
  public static boolean for_element(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "for_element")) return false;
    if (!nextTokenIs(b, FOR_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, FOR_KEYWORD);
    r = r && for_declaration_list(b, l + 1);
    exit_section_(b, m, FOR_ELEMENT, r);
    return r;
  }

  /* ********************************************************** */
  // full_arg_op_identifier full_arg_declaration_list (block_obj | statement)
  public static boolean for_obj(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "for_obj")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FOR_OBJ, "<for obj>");
    r = full_arg_op_identifier(b, l + 1);
    r = r && full_arg_declaration_list(b, l + 1);
    r = r && for_obj_2(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // block_obj | statement
  private static boolean for_obj_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "for_obj_2")) return false;
    boolean r;
    r = block_obj(b, l + 1);
    if (!r) r = statement(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER EQUALS expr+
  public static boolean full_arg_declaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "full_arg_declaration")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FULL_ARG_DECLARATION, "<full arg declaration>");
    r = consumeTokens(b, 0, IDENTIFIER, EQUALS);
    r = r && full_arg_declaration_2(b, l + 1);
    exit_section_(b, l, m, r, false, OpenSCADParser::recover_arg);
    return r;
  }

  // expr+
  private static boolean full_arg_declaration_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "full_arg_declaration_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = expr(b, l + 1, -1);
    while (r) {
      int c = current_position_(b);
      if (!expr(b, l + 1, -1)) break;
      if (!empty_element_parsed_guard_(b, "full_arg_declaration_2", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // LPARENTH full_arg_declaration? ( COMMA+ full_arg_declaration )* RPARENTH
  public static boolean full_arg_declaration_list(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "full_arg_declaration_list")) return false;
    if (!nextTokenIs(b, LPARENTH)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, FULL_ARG_DECLARATION_LIST, null);
    r = consumeToken(b, LPARENTH);
    p = r; // pin = 1
    r = r && report_error_(b, full_arg_declaration_list_1(b, l + 1));
    r = p && report_error_(b, full_arg_declaration_list_2(b, l + 1)) && r;
    r = p && consumeToken(b, RPARENTH) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // full_arg_declaration?
  private static boolean full_arg_declaration_list_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "full_arg_declaration_list_1")) return false;
    full_arg_declaration(b, l + 1);
    return true;
  }

  // ( COMMA+ full_arg_declaration )*
  private static boolean full_arg_declaration_list_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "full_arg_declaration_list_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!full_arg_declaration_list_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "full_arg_declaration_list_2", c)) break;
    }
    return true;
  }

  // COMMA+ full_arg_declaration
  private static boolean full_arg_declaration_list_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "full_arg_declaration_list_2_0")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = full_arg_declaration_list_2_0_0(b, l + 1);
    p = r; // pin = 1
    r = r && full_arg_declaration(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // COMMA+
  private static boolean full_arg_declaration_list_2_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "full_arg_declaration_list_2_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    while (r) {
      int c = current_position_(b);
      if (!consumeToken(b, COMMA)) break;
      if (!empty_element_parsed_guard_(b, "full_arg_declaration_list_2_0_0", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // INTERSECTION_FOR_KEYWORD
  //                                  | ASSIGN_KEYWORD
  //                                  | FOR_KEYWORD
  //                                  | LET_KEYWORD
  static boolean full_arg_op_identifier(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "full_arg_op_identifier")) return false;
    boolean r;
    r = consumeToken(b, INTERSECTION_FOR_KEYWORD);
    if (!r) r = consumeToken(b, ASSIGN_KEYWORD);
    if (!r) r = consumeToken(b, FOR_KEYWORD);
    if (!r) r = consumeToken(b, LET_KEYWORD);
    return r;
  }

  /* ********************************************************** */
  // FUNCTION_KEYWORD (builtin_overridable_op_keywords | builtin_overridable_obj_keywords | IDENTIFIER) arg_declaration_list EQUALS expr SEMICOLON
  public static boolean function_declaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "function_declaration")) return false;
    if (!nextTokenIs(b, FUNCTION_KEYWORD)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, FUNCTION_DECLARATION, null);
    r = consumeToken(b, FUNCTION_KEYWORD);
    p = r; // pin = 1
    r = r && report_error_(b, function_declaration_1(b, l + 1));
    r = p && report_error_(b, arg_declaration_list(b, l + 1)) && r;
    r = p && report_error_(b, consumeToken(b, EQUALS)) && r;
    r = p && report_error_(b, expr(b, l + 1, -1)) && r;
    r = p && consumeToken(b, SEMICOLON) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // builtin_overridable_op_keywords | builtin_overridable_obj_keywords | IDENTIFIER
  private static boolean function_declaration_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "function_declaration_1")) return false;
    boolean r;
    r = builtin_overridable_op_keywords(b, l + 1);
    if (!r) r = builtin_overridable_obj_keywords(b, l + 1);
    if (!r) r = consumeToken(b, IDENTIFIER);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER
  public static boolean function_name_ref(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "function_name_ref")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    exit_section_(b, m, FUNCTION_NAME_REF, r);
    return r;
  }

  /* ********************************************************** */
  // IF_KEYWORD LPARENTH expr RPARENTH [expr &ELSE_KEYWORD]
  public static boolean if_element(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "if_element")) return false;
    if (!nextTokenIs(b, IF_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, IF_KEYWORD, LPARENTH);
    r = r && expr(b, l + 1, -1);
    r = r && consumeToken(b, RPARENTH);
    r = r && if_element_4(b, l + 1);
    exit_section_(b, m, IF_ELEMENT, r);
    return r;
  }

  // [expr &ELSE_KEYWORD]
  private static boolean if_element_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "if_element_4")) return false;
    if_element_4_0(b, l + 1);
    return true;
  }

  // expr &ELSE_KEYWORD
  private static boolean if_element_4_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "if_element_4_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = expr(b, l + 1, -1);
    r = r && if_element_4_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &ELSE_KEYWORD
  private static boolean if_element_4_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "if_element_4_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = consumeToken(b, ELSE_KEYWORD);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // IF_KEYWORD paren_expr (block_obj | statement) (ELSE_KEYWORD IF_KEYWORD paren_expr (block_obj | statement))* [ELSE_KEYWORD (block_obj | statement)]
  public static boolean if_obj(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "if_obj")) return false;
    if (!nextTokenIs(b, IF_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IF_KEYWORD);
    r = r && paren_expr(b, l + 1);
    r = r && if_obj_2(b, l + 1);
    r = r && if_obj_3(b, l + 1);
    r = r && if_obj_4(b, l + 1);
    exit_section_(b, m, IF_OBJ, r);
    return r;
  }

  // block_obj | statement
  private static boolean if_obj_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "if_obj_2")) return false;
    boolean r;
    r = block_obj(b, l + 1);
    if (!r) r = statement(b, l + 1);
    return r;
  }

  // (ELSE_KEYWORD IF_KEYWORD paren_expr (block_obj | statement))*
  private static boolean if_obj_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "if_obj_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!if_obj_3_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "if_obj_3", c)) break;
    }
    return true;
  }

  // ELSE_KEYWORD IF_KEYWORD paren_expr (block_obj | statement)
  private static boolean if_obj_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "if_obj_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, ELSE_KEYWORD, IF_KEYWORD);
    r = r && paren_expr(b, l + 1);
    r = r && if_obj_3_0_3(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // block_obj | statement
  private static boolean if_obj_3_0_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "if_obj_3_0_3")) return false;
    boolean r;
    r = block_obj(b, l + 1);
    if (!r) r = statement(b, l + 1);
    return r;
  }

  // [ELSE_KEYWORD (block_obj | statement)]
  private static boolean if_obj_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "if_obj_4")) return false;
    if_obj_4_0(b, l + 1);
    return true;
  }

  // ELSE_KEYWORD (block_obj | statement)
  private static boolean if_obj_4_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "if_obj_4_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ELSE_KEYWORD);
    r = r && if_obj_4_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // block_obj | statement
  private static boolean if_obj_4_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "if_obj_4_0_1")) return false;
    boolean r;
    r = block_obj(b, l + 1);
    if (!r) r = statement(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // include_import
  //     | use_import
  public static boolean import_$(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "import_$")) return false;
    if (!nextTokenIs(b, "<import $>", INCLUDE_KEYWORD, USE_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, IMPORT, "<import $>");
    r = include_import(b, l + 1);
    if (!r) r = use_import(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // IMPORT_PATH
  public static boolean import_path_ref(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "import_path_ref")) return false;
    if (!nextTokenIs(b, IMPORT_PATH)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IMPORT_PATH);
    exit_section_(b, m, IMPORT_PATH_REF, r);
    return r;
  }

  /* ********************************************************** */
  // INCLUDE_KEYWORD IMPORT_START import_path_ref IMPORT_END
  public static boolean include_import(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "include_import")) return false;
    if (!nextTokenIs(b, INCLUDE_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, INCLUDE_KEYWORD, IMPORT_START);
    r = r && import_path_ref(b, l + 1);
    r = r && consumeToken(b, IMPORT_END);
    exit_section_(b, m, INCLUDE_IMPORT, r);
    return r;
  }

  /* ********************************************************** */
  // statement
  //     | comment_item
  static boolean item(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "item")) return false;
    boolean r;
    r = statement(b, l + 1);
    if (!r) r = comment_item(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // LET_KEYWORD full_arg_declaration_list
  public static boolean let_element(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "let_element")) return false;
    if (!nextTokenIs(b, LET_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LET_KEYWORD);
    r = r && full_arg_declaration_list(b, l + 1);
    exit_section_(b, m, LET_ELEMENT, r);
    return r;
  }

  /* ********************************************************** */
  // LPARENTH expr (COMMA expr)* RPARENTH
  public static boolean list_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "list_expr")) return false;
    if (!nextTokenIs(b, LPARENTH)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LPARENTH);
    r = r && expr(b, l + 1, -1);
    r = r && list_expr_2(b, l + 1);
    r = r && consumeToken(b, RPARENTH);
    exit_section_(b, m, LIST_EXPR, r);
    return r;
  }

  // (COMMA expr)*
  private static boolean list_expr_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "list_expr_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!list_expr_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "list_expr_2", c)) break;
    }
    return true;
  }

  // COMMA expr
  private static boolean list_expr_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "list_expr_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && expr(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // background_op
  //               | debug_op
  //               | root_op
  //               | disable_op
  public static boolean modifier_op(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "modifier_op")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, MODIFIER_OP, "<modifier op>");
    r = background_op(b, l + 1);
    if (!r) r = debug_op(b, l + 1);
    if (!r) r = root_op(b, l + 1);
    if (!r) r = disable_op(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // module_obj_name_ref arg_assignment_list SEMICOLON
  public static boolean module_call_obj(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "module_call_obj")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = module_obj_name_ref(b, l + 1);
    r = r && arg_assignment_list(b, l + 1);
    r = r && consumeToken(b, SEMICOLON);
    exit_section_(b, m, MODULE_CALL_OBJ, r);
    return r;
  }

  /* ********************************************************** */
  // module_op_name_ref arg_assignment_list
  public static boolean module_call_op(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "module_call_op")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = module_op_name_ref(b, l + 1);
    r = r && arg_assignment_list(b, l + 1);
    exit_section_(b, m, MODULE_CALL_OP, r);
    return r;
  }

  /* ********************************************************** */
  // MODULE_KEYWORD IDENTIFIER arg_declaration_list statement
  public static boolean module_declaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "module_declaration")) return false;
    if (!nextTokenIs(b, MODULE_KEYWORD)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, MODULE_DECLARATION, null);
    r = consumeTokens(b, 1, MODULE_KEYWORD, IDENTIFIER);
    p = r; // pin = 1
    r = r && report_error_(b, arg_declaration_list(b, l + 1));
    r = p && statement(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // IDENTIFIER
  public static boolean module_obj_name_ref(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "module_obj_name_ref")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    exit_section_(b, m, MODULE_OBJ_NAME_REF, r);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER
  public static boolean module_op_name_ref(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "module_op_name_ref")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    exit_section_(b, m, MODULE_OP_NAME_REF, r);
    return r;
  }

  /* ********************************************************** */
  // builtin_obj
  //          | if_obj
  //          | for_obj
  //          | module_call_obj
  //          | block_obj
  //          | compound_obj
  //          | empty_obj
  public static boolean object(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, OBJECT, "<object>");
    r = builtin_obj(b, l + 1);
    if (!r) r = if_obj(b, l + 1);
    if (!r) r = for_obj(b, l + 1);
    if (!r) r = module_call_obj(b, l + 1);
    if (!r) r = block_obj(b, l + 1);
    if (!r) r = compound_obj(b, l + 1);
    if (!r) r = empty_obj(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // item*
  static boolean openSCADFile(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "openSCADFile")) return false;
    while (true) {
      int c = current_position_(b);
      if (!item(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "openSCADFile", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // builtin_op
  //            | modifier_op
  //            | module_call_op
  public static boolean operator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "operator")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, OPERATOR, "<operator>");
    r = builtin_op(b, l + 1);
    if (!r) r = modifier_op(b, l + 1);
    if (!r) r = module_call_op(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER
  public static boolean parameter_reference(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameter_reference")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    exit_section_(b, m, PARAMETER_REFERENCE, r);
    return r;
  }

  /* ********************************************************** */
  // ! (COMMA | RPARENTH)
  static boolean recover_arg(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "recover_arg")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !recover_arg_0(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // COMMA | RPARENTH
  private static boolean recover_arg_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "recover_arg_0")) return false;
    boolean r;
    r = consumeToken(b, COMMA);
    if (!r) r = consumeToken(b, RPARENTH);
    return r;
  }

  /* ********************************************************** */
  // ! (COMMA | RBRACKET)
  static boolean recover_vector(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "recover_vector")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !recover_vector_0(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // COMMA | RBRACKET
  private static boolean recover_vector_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "recover_vector_0")) return false;
    boolean r;
    r = consumeToken(b, COMMA);
    if (!r) r = consumeToken(b, RBRACKET);
    return r;
  }

  /* ********************************************************** */
  // comment_item* (echo_element | assert_element)
  public static boolean reporting_element(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "reporting_element")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, REPORTING_ELEMENT, "<reporting element>");
    r = reporting_element_0(b, l + 1);
    r = r && reporting_element_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // comment_item*
  private static boolean reporting_element_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "reporting_element_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!comment_item(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "reporting_element_0", c)) break;
    }
    return true;
  }

  // echo_element | assert_element
  private static boolean reporting_element_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "reporting_element_1")) return false;
    boolean r;
    r = echo_element(b, l + 1);
    if (!r) r = assert_element(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // EXCL
  public static boolean root_op(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "root_op")) return false;
    if (!nextTokenIs(b, EXCL)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, EXCL);
    exit_section_(b, m, ROOT_OP, r);
    return r;
  }

  /* ********************************************************** */
  // comment_item | (object | declaration | import) comment_item*
  static boolean statement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "statement")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = comment_item(b, l + 1);
    if (!r) r = statement_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (object | declaration | import) comment_item*
  private static boolean statement_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "statement_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = statement_1_0(b, l + 1);
    r = r && statement_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // object | declaration | import
  private static boolean statement_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "statement_1_0")) return false;
    boolean r;
    r = object(b, l + 1);
    if (!r) r = declaration(b, l + 1);
    if (!r) r = import_$(b, l + 1);
    return r;
  }

  // comment_item*
  private static boolean statement_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "statement_1_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!comment_item(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "statement_1_1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // IS_UNDEF_KEYWORD
  //                | IS_LIST_KEYWORD
  //                | IS_NUM_KEYWORD
  //                | IS_BOOL_KEYWORD
  //                | IS_STRING_KEYWORD
  //                | IS_FUNCTION_KEYWORD
  public static boolean test_exp_ref(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "test_exp_ref")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TEST_EXP_REF, "<test exp ref>");
    r = consumeToken(b, IS_UNDEF_KEYWORD);
    if (!r) r = consumeToken(b, IS_LIST_KEYWORD);
    if (!r) r = consumeToken(b, IS_NUM_KEYWORD);
    if (!r) r = consumeToken(b, IS_BOOL_KEYWORD);
    if (!r) r = consumeToken(b, IS_STRING_KEYWORD);
    if (!r) r = consumeToken(b, IS_FUNCTION_KEYWORD);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // USE_KEYWORD IMPORT_START import_path_ref IMPORT_END
  public static boolean use_import(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "use_import")) return false;
    if (!nextTokenIs(b, USE_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, USE_KEYWORD, IMPORT_START);
    r = r && import_path_ref(b, l + 1);
    r = r && consumeToken(b, IMPORT_END);
    exit_section_(b, m, USE_IMPORT, r);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER EQUALS expr SEMICOLON
  public static boolean variable_declaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variable_declaration")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, VARIABLE_DECLARATION, null);
    r = consumeTokens(b, 1, IDENTIFIER, EQUALS);
    p = r; // pin = 1
    r = r && report_error_(b, expr(b, l + 1, -1));
    r = p && consumeToken(b, SEMICOLON) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // comment_item* (vector_item_expr | expr )
  static boolean vector_item(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "vector_item")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_);
    r = vector_item_0(b, l + 1);
    r = r && vector_item_1(b, l + 1);
    exit_section_(b, l, m, r, false, OpenSCADParser::recover_vector);
    return r;
  }

  // comment_item*
  private static boolean vector_item_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "vector_item_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!comment_item(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "vector_item_0", c)) break;
    }
    return true;
  }

  // vector_item_expr | expr
  private static boolean vector_item_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "vector_item_1")) return false;
    boolean r;
    r = vector_item_expr(b, l + 1);
    if (!r) r = expr(b, l + 1, -1);
    return r;
  }

  /* ********************************************************** */
  // LPARENTH? (comment_item* (vector_item_prefix_element | reporting_element))+ comment_item* expr? RPARENTH?
  static boolean vector_item_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "vector_item_expr")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = vector_item_expr_0(b, l + 1);
    r = r && vector_item_expr_1(b, l + 1);
    r = r && vector_item_expr_2(b, l + 1);
    r = r && vector_item_expr_3(b, l + 1);
    r = r && vector_item_expr_4(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // LPARENTH?
  private static boolean vector_item_expr_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "vector_item_expr_0")) return false;
    consumeToken(b, LPARENTH);
    return true;
  }

  // (comment_item* (vector_item_prefix_element | reporting_element))+
  private static boolean vector_item_expr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "vector_item_expr_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = vector_item_expr_1_0(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!vector_item_expr_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "vector_item_expr_1", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // comment_item* (vector_item_prefix_element | reporting_element)
  private static boolean vector_item_expr_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "vector_item_expr_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = vector_item_expr_1_0_0(b, l + 1);
    r = r && vector_item_expr_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // comment_item*
  private static boolean vector_item_expr_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "vector_item_expr_1_0_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!comment_item(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "vector_item_expr_1_0_0", c)) break;
    }
    return true;
  }

  // vector_item_prefix_element | reporting_element
  private static boolean vector_item_expr_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "vector_item_expr_1_0_1")) return false;
    boolean r;
    r = vector_item_prefix_element(b, l + 1);
    if (!r) r = reporting_element(b, l + 1);
    return r;
  }

  // comment_item*
  private static boolean vector_item_expr_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "vector_item_expr_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!comment_item(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "vector_item_expr_2", c)) break;
    }
    return true;
  }

  // expr?
  private static boolean vector_item_expr_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "vector_item_expr_3")) return false;
    expr(b, l + 1, -1);
    return true;
  }

  // RPARENTH?
  private static boolean vector_item_expr_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "vector_item_expr_4")) return false;
    consumeToken(b, RPARENTH);
    return true;
  }

  /* ********************************************************** */
  // for_element | if_element | bind_else_element | else_element | let_element | EACH_KEYWORD
  static boolean vector_item_prefix_element(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "vector_item_prefix_element")) return false;
    boolean r;
    r = for_element(b, l + 1);
    if (!r) r = if_element(b, l + 1);
    if (!r) r = bind_else_element(b, l + 1);
    if (!r) r = else_element(b, l + 1);
    if (!r) r = let_element(b, l + 1);
    if (!r) r = consumeToken(b, EACH_KEYWORD);
    return r;
  }

  /* ********************************************************** */
  // Expression root: expr
  // Operator priority table:
  // 0: POSTFIX(elvis_expr) BINARY(conditional_expr)
  // 1: BINARY(and_expr) BINARY(or_expr)
  // 2: BINARY(plus_expr) BINARY(minus_expr)
  // 3: BINARY(mul_expr) BINARY(div_expr) BINARY(modulo_expr) BINARY(power_expr)
  // 4: PREFIX(unary_plus_expr) PREFIX(unary_min_expr) PREFIX(unary_negate_expr)
  // 5: ATOM(range_expr) ATOM(echo_expr) ATOM(assert_expr) PREFIX(test_expr)
  //    ATOM(builtin_expr) PREFIX(each_expr) ATOM(function_call_expr) ATOM(variable_ref_expr)
  //    ATOM(vector_expr) PREFIX(paren_expr) ATOM(literal_expr) PREFIX(function_literal_expr)
  //    POSTFIX(index_expr) POSTFIX(qualification_expr)
  public static boolean expr(PsiBuilder b, int l, int g) {
    if (!recursion_guard_(b, l, "expr")) return false;
    addVariant(b, "<expr>");
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, "<expr>");
    r = unary_plus_expr(b, l + 1);
    if (!r) r = unary_min_expr(b, l + 1);
    if (!r) r = unary_negate_expr(b, l + 1);
    if (!r) r = range_expr(b, l + 1);
    if (!r) r = echo_expr(b, l + 1);
    if (!r) r = assert_expr(b, l + 1);
    if (!r) r = test_expr(b, l + 1);
    if (!r) r = builtin_expr(b, l + 1);
    if (!r) r = each_expr(b, l + 1);
    if (!r) r = function_call_expr(b, l + 1);
    if (!r) r = variable_ref_expr(b, l + 1);
    if (!r) r = vector_expr(b, l + 1);
    if (!r) r = paren_expr(b, l + 1);
    if (!r) r = literal_expr(b, l + 1);
    if (!r) r = function_literal_expr(b, l + 1);
    p = r;
    r = r && expr_0(b, l + 1, g);
    exit_section_(b, l, m, null, r, p, null);
    return r || p;
  }

  public static boolean expr_0(PsiBuilder b, int l, int g) {
    if (!recursion_guard_(b, l, "expr_0")) return false;
    boolean r = true;
    while (true) {
      Marker m = enter_section_(b, l, _LEFT_, null);
      if (g < 0 && elvis_expr_0(b, l + 1)) {
        r = true;
        exit_section_(b, l, m, ELVIS_EXPR, r, true, null);
      }
      else if (g < 0 && conditional_expr_0(b, l + 1)) {
        r = expr(b, l, 0);
        exit_section_(b, l, m, CONDITIONAL_EXPR, r, true, null);
      }
      else if (g < 1 && consumeTokenSmart(b, AND)) {
        r = expr(b, l, 1);
        exit_section_(b, l, m, AND_EXPR, r, true, null);
      }
      else if (g < 1 && consumeTokenSmart(b, OR)) {
        r = expr(b, l, 1);
        exit_section_(b, l, m, OR_EXPR, r, true, null);
      }
      else if (g < 2 && consumeTokenSmart(b, PLUS)) {
        r = expr(b, l, 2);
        exit_section_(b, l, m, PLUS_EXPR, r, true, null);
      }
      else if (g < 2 && consumeTokenSmart(b, MINUS)) {
        r = expr(b, l, 2);
        exit_section_(b, l, m, MINUS_EXPR, r, true, null);
      }
      else if (g < 3 && consumeTokenSmart(b, MUL)) {
        r = expr(b, l, 3);
        exit_section_(b, l, m, MUL_EXPR, r, true, null);
      }
      else if (g < 3 && consumeTokenSmart(b, DIV)) {
        r = expr(b, l, 3);
        exit_section_(b, l, m, DIV_EXPR, r, true, null);
      }
      else if (g < 3 && consumeTokenSmart(b, PERC)) {
        r = expr(b, l, 3);
        exit_section_(b, l, m, MODULO_EXPR, r, true, null);
      }
      else if (g < 3 && consumeTokenSmart(b, EXP)) {
        r = expr(b, l, 3);
        exit_section_(b, l, m, POWER_EXPR, r, true, null);
      }
      else if (g < 5 && index_expr_0(b, l + 1)) {
        r = true;
        exit_section_(b, l, m, INDEX_EXPR, r, true, null);
      }
      else if (g < 5 && parseTokensSmart(b, 0, DOT, IDENTIFIER)) {
        r = true;
        exit_section_(b, l, m, QUALIFICATION_EXPR, r, true, null);
      }
      else {
        exit_section_(b, l, m, null, false, false, null);
        break;
      }
    }
    return r;
  }

  // QUERY (elvis_expr | expr) COLON (elvis_expr | expr)
  private static boolean elvis_expr_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "elvis_expr_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, QUERY);
    r = r && elvis_expr_0_1(b, l + 1);
    r = r && consumeToken(b, COLON);
    r = r && elvis_expr_0_3(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // elvis_expr | expr
  private static boolean elvis_expr_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "elvis_expr_0_1")) return false;
    boolean r;
    r = expr(b, l + 1, -1);
    if (!r) r = expr(b, l + 1, -1);
    return r;
  }

  // elvis_expr | expr
  private static boolean elvis_expr_0_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "elvis_expr_0_3")) return false;
    boolean r;
    r = expr(b, l + 1, -1);
    if (!r) r = expr(b, l + 1, -1);
    return r;
  }

  // LT | GT | LE | GE | EQ | NE
  private static boolean conditional_expr_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "conditional_expr_0")) return false;
    boolean r;
    r = consumeTokenSmart(b, LT);
    if (!r) r = consumeTokenSmart(b, GT);
    if (!r) r = consumeTokenSmart(b, LE);
    if (!r) r = consumeTokenSmart(b, GE);
    if (!r) r = consumeTokenSmart(b, EQ);
    if (!r) r = consumeTokenSmart(b, NE);
    return r;
  }

  public static boolean unary_plus_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unary_plus_expr")) return false;
    if (!nextTokenIsSmart(b, PLUS)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeTokenSmart(b, PLUS);
    p = r;
    r = p && expr(b, l, 4);
    exit_section_(b, l, m, UNARY_PLUS_EXPR, r, p, null);
    return r || p;
  }

  public static boolean unary_min_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unary_min_expr")) return false;
    if (!nextTokenIsSmart(b, MINUS)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeTokenSmart(b, MINUS);
    p = r;
    r = p && expr(b, l, 4);
    exit_section_(b, l, m, UNARY_MIN_EXPR, r, p, null);
    return r || p;
  }

  public static boolean unary_negate_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unary_negate_expr")) return false;
    if (!nextTokenIsSmart(b, EXCL)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeTokenSmart(b, EXCL);
    p = r;
    r = p && expr(b, l, 4);
    exit_section_(b, l, m, UNARY_NEGATE_EXPR, r, p, null);
    return r || p;
  }

  // LBRACKET expr COLON expr [ COLON expr ] RBRACKET
  public static boolean range_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "range_expr")) return false;
    if (!nextTokenIsSmart(b, LBRACKET)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, LBRACKET);
    r = r && expr(b, l + 1, -1);
    r = r && consumeToken(b, COLON);
    r = r && expr(b, l + 1, -1);
    r = r && range_expr_4(b, l + 1);
    r = r && consumeToken(b, RBRACKET);
    exit_section_(b, m, RANGE_EXPR, r);
    return r;
  }

  // [ COLON expr ]
  private static boolean range_expr_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "range_expr_4")) return false;
    range_expr_4_0(b, l + 1);
    return true;
  }

  // COLON expr
  private static boolean range_expr_4_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "range_expr_4_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, COLON);
    r = r && expr(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  // echo_element expr?
  public static boolean echo_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "echo_expr")) return false;
    if (!nextTokenIsSmart(b, ECHO_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = echo_element(b, l + 1);
    r = r && echo_expr_1(b, l + 1);
    exit_section_(b, m, ECHO_EXPR, r);
    return r;
  }

  // expr?
  private static boolean echo_expr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "echo_expr_1")) return false;
    expr(b, l + 1, -1);
    return true;
  }

  // assert_element expr?
  public static boolean assert_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "assert_expr")) return false;
    if (!nextTokenIsSmart(b, ASSERT_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = assert_element(b, l + 1);
    r = r && assert_expr_1(b, l + 1);
    exit_section_(b, m, ASSERT_EXPR, r);
    return r;
  }

  // expr?
  private static boolean assert_expr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "assert_expr_1")) return false;
    expr(b, l + 1, -1);
    return true;
  }

  public static boolean test_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "test_expr")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = test_expr_0(b, l + 1);
    p = r;
    r = p && expr(b, l, -1);
    r = p && report_error_(b, consumeToken(b, RPARENTH)) && r;
    exit_section_(b, l, m, TEST_EXPR, r, p, null);
    return r || p;
  }

  // test_exp_ref LPARENTH
  private static boolean test_expr_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "test_expr_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = test_exp_ref(b, l + 1);
    r = r && consumeToken(b, LPARENTH);
    exit_section_(b, m, null, r);
    return r;
  }

  // builtin_expr_ref arg_assignment_list
  //                | LET_KEYWORD full_arg_declaration_list expr
  //                | builtin_overridable_expr_ref
  public static boolean builtin_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "builtin_expr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, BUILTIN_EXPR, "<builtin expr>");
    r = builtin_expr_0(b, l + 1);
    if (!r) r = builtin_expr_1(b, l + 1);
    if (!r) r = builtin_overridable_expr_ref(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // builtin_expr_ref arg_assignment_list
  private static boolean builtin_expr_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "builtin_expr_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = builtin_expr_ref(b, l + 1);
    r = r && arg_assignment_list(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // LET_KEYWORD full_arg_declaration_list expr
  private static boolean builtin_expr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "builtin_expr_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, LET_KEYWORD);
    r = r && full_arg_declaration_list(b, l + 1);
    r = r && expr(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  public static boolean each_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "each_expr")) return false;
    if (!nextTokenIsSmart(b, EACH_KEYWORD)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeTokenSmart(b, EACH_KEYWORD);
    p = r;
    r = p && expr(b, l, -1);
    exit_section_(b, l, m, EACH_EXPR, r, p, null);
    return r || p;
  }

  // function_name_ref arg_assignment_list+
  public static boolean function_call_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "function_call_expr")) return false;
    if (!nextTokenIsSmart(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = function_name_ref(b, l + 1);
    r = r && function_call_expr_1(b, l + 1);
    exit_section_(b, m, FUNCTION_CALL_EXPR, r);
    return r;
  }

  // arg_assignment_list+
  private static boolean function_call_expr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "function_call_expr_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = arg_assignment_list(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!arg_assignment_list(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "function_call_expr_1", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // IDENTIFIER
  public static boolean variable_ref_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variable_ref_expr")) return false;
    if (!nextTokenIsSmart(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, IDENTIFIER);
    exit_section_(b, m, VARIABLE_REF_EXPR, r);
    return r;
  }

  // LBRACKET [vector_item ( COMMA (vector_item | &RBRACKET) )*] RBRACKET
  public static boolean vector_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "vector_expr")) return false;
    if (!nextTokenIsSmart(b, LBRACKET)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, VECTOR_EXPR, null);
    r = consumeTokenSmart(b, LBRACKET);
    p = r; // pin = 1
    r = r && report_error_(b, vector_expr_1(b, l + 1));
    r = p && consumeToken(b, RBRACKET) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // [vector_item ( COMMA (vector_item | &RBRACKET) )*]
  private static boolean vector_expr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "vector_expr_1")) return false;
    vector_expr_1_0(b, l + 1);
    return true;
  }

  // vector_item ( COMMA (vector_item | &RBRACKET) )*
  private static boolean vector_expr_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "vector_expr_1_0")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = vector_item(b, l + 1);
    p = r; // pin = 1
    r = r && vector_expr_1_0_1(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // ( COMMA (vector_item | &RBRACKET) )*
  private static boolean vector_expr_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "vector_expr_1_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!vector_expr_1_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "vector_expr_1_0_1", c)) break;
    }
    return true;
  }

  // COMMA (vector_item | &RBRACKET)
  private static boolean vector_expr_1_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "vector_expr_1_0_1_0")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeTokenSmart(b, COMMA);
    p = r; // pin = 1
    r = r && vector_expr_1_0_1_0_1(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // vector_item | &RBRACKET
  private static boolean vector_expr_1_0_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "vector_expr_1_0_1_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = vector_item(b, l + 1);
    if (!r) r = vector_expr_1_0_1_0_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &RBRACKET
  private static boolean vector_expr_1_0_1_0_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "vector_expr_1_0_1_0_1_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = consumeTokenSmart(b, RBRACKET);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  public static boolean paren_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "paren_expr")) return false;
    if (!nextTokenIsSmart(b, LPARENTH)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeTokenSmart(b, LPARENTH);
    p = r;
    r = p && expr(b, l, -1);
    r = p && report_error_(b, consumeToken(b, RPARENTH)) && r;
    exit_section_(b, l, m, PAREN_EXPR, r, p, null);
    return r || p;
  }

  // TRUE_KEYWORD
  //                | FALSE_KEYWORD
  //                | UNDEF_KEYWORD
  //                | NUMBER_LITERAL
  //                | STRING_LITERAL
  public static boolean literal_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "literal_expr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, LITERAL_EXPR, "<literal expr>");
    r = consumeTokenSmart(b, TRUE_KEYWORD);
    if (!r) r = consumeTokenSmart(b, FALSE_KEYWORD);
    if (!r) r = consumeTokenSmart(b, UNDEF_KEYWORD);
    if (!r) r = consumeTokenSmart(b, NUMBER_LITERAL);
    if (!r) r = consumeTokenSmart(b, STRING_LITERAL);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  public static boolean function_literal_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "function_literal_expr")) return false;
    if (!nextTokenIsSmart(b, FUNCTION_KEYWORD)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = function_literal_expr_0(b, l + 1);
    p = r;
    r = p && expr(b, l, -1);
    exit_section_(b, l, m, FUNCTION_LITERAL_EXPR, r, p, null);
    return r || p;
  }

  // FUNCTION_KEYWORD arg_declaration_list
  private static boolean function_literal_expr_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "function_literal_expr_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, FUNCTION_KEYWORD);
    r = r && arg_declaration_list(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // LBRACKET (elvis_expr | <<innerExpression>>) RBRACKET
  private static boolean index_expr_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "index_expr_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, LBRACKET);
    r = r && index_expr_0_1(b, l + 1);
    r = r && consumeToken(b, RBRACKET);
    exit_section_(b, m, null, r);
    return r;
  }

  // elvis_expr | <<innerExpression>>
  private static boolean index_expr_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "index_expr_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = expr(b, l + 1, -1);
    if (!r) r = innerExpression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

}
