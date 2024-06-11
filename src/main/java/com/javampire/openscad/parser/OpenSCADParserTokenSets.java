package com.javampire.openscad.parser;

import com.intellij.psi.TokenType;
import com.intellij.psi.tree.TokenSet;

import static com.javampire.openscad.psi.OpenSCADTypes.*;

public class OpenSCADParserTokenSets {

    /*
     * The sets defined below are used for syntax highlighting
     */


    public static final TokenSet WHITE_SPACES = TokenSet.create(TokenType.WHITE_SPACE);

    public static final TokenSet COMMENTS = TokenSet.create(
            COMMENT_SINGLELINE, COMMENT_C_STYLE,
            COMMENT_DOC, COMMENT_SINGLELINE_BLOCK
    );

    public static final TokenSet CUSTOMIZER_COMMENTS = TokenSet.create(
            COMMENT_CUSTOMIZER_VALUE, COMMENT_CUSTOMIZER_TABS
    );

    public static final TokenSet STRINGS = TokenSet.create(
            STRING_LITERAL
    );

    public static final TokenSet OPERATOR_KEYWORDS = TokenSet.create(
            LINEAR_EXTRUDE_KEYWORD, ROTATE_EXTRUDE_KEYWORD, ROTATE_KEYWORD,
            RESIZE_KEYWORD, MULTMATRIX_KEYWORD, COLOR_KEYWORD, MINKOWSKI_KEYWORD,
            UNION_KEYWORD, DIFFERENCE_KEYWORD, RENDER_KEYWORD, PROJECTION_KEYWORD
    );

    public static final TokenSet LANGUAGE_KEYWORDS = TokenSet.create(
            ASSIGN_KEYWORD, EACH_KEYWORD, ELSE_KEYWORD, FALSE_KEYWORD, FOR_KEYWORD, INTERSECTION_FOR_KEYWORD,
            FUNCTION_KEYWORD, IF_KEYWORD,
            LET_KEYWORD, MODULE_KEYWORD,
            TRUE_KEYWORD, UNDEF_KEYWORD
    );

    public static final TokenSet OBJECT_KEYWORDS = TokenSet.create(
            ASSERT_KEYWORD, ECHO_KEYWORD, POLYHEDRON_KEYWORD,
            POLYGON_KEYWORD, TEXT_KEYWORD, SURFACE_KEYWORD, CHILD_KEYWORD,
            CHILDREN_KEYWORD, IMPORT_KEYWORD, IMPORT_DXF_KEYWORD, IMPORT_STL_KEYWORD
    );

    public static final TokenSet OVERRIDABLE_OBJECT_KEYWORDS = TokenSet.create(
            CUBE_KEYWORD, CYLINDER_KEYWORD, SPHERE_KEYWORD, SQUARE_KEYWORD, CIRCLE_KEYWORD
    );

    public static final TokenSet OVERRIDABLE_OPERATOR_KEYWORDS = TokenSet.create(
            DIFFERENCE_KEYWORD, HULL_KEYWORD, INTERSECTION_KEYWORD, MIRROR_KEYWORD, OFFSET_KEYWORD,
            SCALE_KEYWORD, TRANSLATE_KEYWORD, UNION_KEYWORD
    );

    public static final TokenSet FUNCTION_KEYWORDS = TokenSet.create(
            IS_UNDEF_KEYWORD, IS_LIST_KEYWORD, IS_NUM_KEYWORD, IS_BOOL_KEYWORD, IS_STRING_KEYWORD, IS_FUNCTION_KEYWORD,
            COS_KEYWORD, SIN_KEYWORD, TAN_KEYWORD, ACOS_KEYWORD, ASIN_KEYWORD, ATAN_KEYWORD, ATAN2_KEYWORD, ABS_KEYWORD,
            CEIL_KEYWORD, CONCAT_KEYWORD, CROSS_KEYWORD, EXP_KEYWORD, FLOOR_KEYWORD, LN_KEYWORD, LEN_KEYWORD,
            LOG_KEYWORD, LOOKUP_KEYWORD, MAX_KEYWORD, MIN_KEYWORD, NORM_KEYWORD, ORD_KEYWORD, POW_KEYWORD,
            RANDS_KEYWORD, ROUND_KEYWORD, SIGN_KEYWORD, SQRT_KEYWORD, STR_KEYWORD, CHR_KEYWORD, SEARCH_KEYWORD,
            VERSION_KEYWORD, VERSION_NUM_KEYWORD, PARENT_MODULE_KEYWORD
    );

    public static final TokenSet PREDEFINED_SYMBOLS = TokenSet.create(
            AND, OR, PLUS,
            MINUS, PERC, DIV,
            MUL, LT, LE,
            GT, GE, NE,
            EQ, EQUALS
    );

    public static final TokenSet SEPARATOR_SYMBOLS = TokenSet.create(
            COLON, SEMICOLON, COMMA
    );

    public static final TokenSet ANGLE_BRACKETS_TOKENS = TokenSet.create(
            IMPORT_START, IMPORT_END
    );

    public static final TokenSet PARENTHESES_TOKENS = TokenSet.create(
            LPARENTH, RPARENTH
    );

    public static final TokenSet BRACES_TOKENS = TokenSet.create(
            LBRACE, RBRACE
    );

    public static final TokenSet BRACKETS_TOKENS = TokenSet.create(
            LBRACKET, RBRACKET
    );

    public static final TokenSet LINE_COMMENT_TOKENS = TokenSet.create(
            COMMENT_SINGLELINE
    );


    /*
     * The sets below are used for code folding
     */


    /**
     * Used for folding import section (can include comments too)
     */
    public static final TokenSet IMPORT_FOLDING_TOKENS = TokenSet.create(
            COMMENT_SINGLELINE, INCLUDE_IMPORT, USE_IMPORT
    );


    /*
     * The sets below are used for element naming/renaming
     */


    /**
     * These elements have their name in the first child with IMPORT_PATH token type
     */
    public static final TokenSet IMPORT_TOKENS = TokenSet.create(
            INCLUDE_IMPORT, USE_IMPORT
    );

    /**
     * These elements have their name in the first child with IDENTIFIER token type
     */
    public static final TokenSet NAMED_WITH_IDENTIFIER = TokenSet.create(
            MODULE_DECLARATION, FUNCTION_DECLARATION,
            ARG_DECLARATION, FULL_ARG_DECLARATION,
            MODULE_OP_NAME_REF, MODULE_OBJ_NAME_REF,
            FUNCTION_NAME_REF, PARAMETER_REFERENCE,
            VARIABLE_REF_EXPR, VARIABLE_DECLARATION
    );

    /**
     * builtin_expr_ref keywords
     */
    public static final TokenSet BUILTIN_EXPR_REF_KEYWORDS = TokenSet.create(
            LET_KEYWORD, ABS_KEYWORD, ACOS_KEYWORD, ASIN_KEYWORD, ATAN_KEYWORD, ATAN2_KEYWORD, CEIL_KEYWORD,
            CHR_KEYWORD, CONCAT_KEYWORD, COS_KEYWORD, CROSS_KEYWORD, EXP_KEYWORD, FLOOR_KEYWORD, LEN_KEYWORD,
            LN_KEYWORD, LOG_KEYWORD, LOOKUP_KEYWORD, MAX_KEYWORD, MIN_KEYWORD, NORM_KEYWORD, ORD_KEYWORD,
            PARENT_MODULE_KEYWORD, POW_KEYWORD, RANDS_KEYWORD, ROUND_KEYWORD, SEARCH_KEYWORD, SIGN_KEYWORD,
            SIN_KEYWORD, SQRT_KEYWORD, STR_KEYWORD, TAN_KEYWORD, VERSION_KEYWORD, VERSION_NUM_KEYWORD
    );

    /**
     * builtin_obj_ref keywords
     */
    public static final TokenSet BUILTIN_OBJ_REF_KEYWORDS = TokenSet.create(
            ASSERT_KEYWORD, CHILD_KEYWORD, CHILDREN_KEYWORD,
            ECHO_KEYWORD, IMPORT_KEYWORD, IMPORT_DXF_KEYWORD, IMPORT_STL_KEYWORD, POLYGON_KEYWORD, POLYHEDRON_KEYWORD,
            SURFACE_KEYWORD, TEXT_KEYWORD
    );

    /**
     * test_exp_ref keywords
     */
    public static final TokenSet TEST_EXP_REF_KEYWORDS = TokenSet.create(
            IS_UNDEF_KEYWORD, IS_LIST_KEYWORD, IS_NUM_KEYWORD, IS_BOOL_KEYWORD, IS_STRING_KEYWORD, IS_FUNCTION_KEYWORD
    );

    /**
     * common_op_ref keywords
     */
    public static final TokenSet COMMON_OP_REF_KEYWORDS = TokenSet.create(
            COLOR_KEYWORD, DIFFERENCE_KEYWORD, LINEAR_EXTRUDE_KEYWORD,
            MINKOWSKI_KEYWORD, MULTMATRIX_KEYWORD, PROJECTION_KEYWORD, RENDER_KEYWORD,
            RESIZE_KEYWORD, ROTATE_KEYWORD, ROTATE_EXTRUDE_KEYWORD, UNION_KEYWORD
    );

    /**
     *  Overridable object and operators, also a combined set where the operators and objects are used as functions
     */
    public static final TokenSet BUILTIN_OVERRIDABLE_OBJ_REF_KEYWORDS = TokenSet.create(
            CIRCLE_KEYWORD, CUBE_KEYWORD, CYLINDER_KEYWORD, SPHERE_KEYWORD, SQUARE_KEYWORD
    );
    public static final TokenSet BUILTIN_OVERRIDABLE_OP_REF_KEYWORDS = TokenSet.create(
            DIFFERENCE_KEYWORD, HULL_KEYWORD, INTERSECTION_KEYWORD, MIRROR_KEYWORD, OFFSET_KEYWORD,
            SCALE_KEYWORD, TRANSLATE_KEYWORD, UNION_KEYWORD

    );
    public static final TokenSet BUILTIN_OVERRIDABLE_OP_AS_FUNCTION_REF_KEYWORDS =
            TokenSet.orSet(BUILTIN_OVERRIDABLE_OBJ_REF_KEYWORDS, BUILTIN_OVERRIDABLE_OP_REF_KEYWORDS);

    /**
     * These elements can't be renamed
     */
    public static final TokenSet NON_RENAMABLE_ELEMENTS = TokenSet.create(
            BUILTIN_EXPR_REF, BUILTIN_OBJ_REF, TEST_EXP_REF,
            COMMON_OP_REF
    );

    /**
     * These elements have their doc-string attached to the parent
     */
    public static final TokenSet DOC_IN_PARENT = TokenSet.create(
            IDENTIFIER
    );


    /*
     * The sets below are used for spacing feature in formatting
     */

    public static final TokenSet LOGICAL_OPERATORS = TokenSet.create(
            AND, OR
    );

    public static final TokenSet EQUALITY_OPERATORS = TokenSet.create(
            EQ, NE
    );

    public static final TokenSet RELATIONAL_OPERATORS = TokenSet.create(
            LT, LE, GT, GE
    );

    public static final TokenSet ADDITIVE_OPERATORS = TokenSet.create(
            MINUS, PLUS
    );

    public static final TokenSet MULTIPLICATIVE_OPERATORS = TokenSet.create(
            DIV, MUL, PERC
    );

    public static final TokenSet MATHEMATICAL_EXPR = TokenSet.create(
            PLUS_EXPR, MINUS_EXPR, DIV_EXPR, MUL_EXPR, MODULO_EXPR
    );


    /*
     * The set below are used for completion
     */


    public static final TokenSet WITH_ARG_DECLARATION_LIST = TokenSet.create(
            MODULE_DECLARATION, FUNCTION_DECLARATION
    );

    public static final TokenSet WITH_FULL_ARG_DECLARATION_LIST = TokenSet.create(
            FOR_OBJ
    );
}
