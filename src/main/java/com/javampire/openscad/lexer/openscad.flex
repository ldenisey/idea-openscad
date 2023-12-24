package com.javampire.openscad.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.TokenType;
import com.javampire.openscad.psi.OpenSCADTypes;

%%

%class OpenSCADLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

CRLF=\R
BLANK=[ \t\f]
WHITE_SPACE={CRLF} | {BLANK}

IMPORT_COND={WHITE_SPACE}*"<"
IMPORT_PATH=[^<>]+

BUILTIN_COND={WHITE_SPACE}*"("

COMMENT_CUSTOMIZER_CONTENT="["[a-zA-Z0-9:, -]+"]"
COMMENT_CUSTOMIZER_VALUE="//"{BLANK}*{COMMENT_CUSTOMIZER_CONTENT}
COMMENT_CUSTOMIZER_TABS="/*"{BLANK}*{COMMENT_CUSTOMIZER_CONTENT}{BLANK}*"*/"
COMMENT_MULTILINE_CONTENT=[^*]*\*+([^/*][^*]*\*+)* // = whatever string except "*/"
COMMENT_C_STYLE="/*"{COMMENT_MULTILINE_CONTENT}"/"
COMMENT_DOC="/**"{COMMENT_MULTILINE_CONTENT}"/"
COMMENT_SINGLELINE="//"[^\r\n]*
COMMENT_SINGLELINE_BLOCK={COMMENT_SINGLELINE}({CRLF}{COMMENT_SINGLELINE})+

IDENTIFIER = [a-zA-Z0-9_$]+

DIGIT = [0-9]
DECIMAL = {DIGIT}+ "."? | {DIGIT}* "." {DIGIT}+
NUMBER_LITERAL = {DECIMAL} ([Ee] [+-]? {DIGIT}+)?

ESCAPE_SEQUENCE = \\[^]
STRING_LITERAL = \"  ([^\\\"] | {ESCAPE_SEQUENCE})* \"?

%state IMPORT_PATH_STATE BUILTIN_OVERRIDABLE

%%

<YYINITIAL> {

    {COMMENT_CUSTOMIZER_VALUE}  { return OpenSCADTypes.COMMENT_CUSTOMIZER_VALUE; }
    {COMMENT_CUSTOMIZER_TABS}   { return OpenSCADTypes.COMMENT_CUSTOMIZER_TABS; }
    ^{COMMENT_SINGLELINE_BLOCK} { return OpenSCADTypes.COMMENT_SINGLELINE_BLOCK; }
    {COMMENT_DOC}               { return OpenSCADTypes.COMMENT_DOC; }
    {COMMENT_C_STYLE}           { return OpenSCADTypes.COMMENT_C_STYLE; }
    {COMMENT_SINGLELINE}        { return OpenSCADTypes.COMMENT_SINGLELINE; }

    "false"                     { return OpenSCADTypes.FALSE_KEYWORD; }
    "true"                      { return OpenSCADTypes.TRUE_KEYWORD; }
    "undef"                     { return OpenSCADTypes.UNDEF_KEYWORD; }

    "function"	                { yybegin(BUILTIN_OVERRIDABLE); return OpenSCADTypes.FUNCTION_KEYWORD; }
    "module"	                { yybegin(BUILTIN_OVERRIDABLE); return OpenSCADTypes.MODULE_KEYWORD; }

    "else"                      { return OpenSCADTypes.ELSE_KEYWORD; }
    "for"                       { return OpenSCADTypes.FOR_KEYWORD; }
    "if"                        { return OpenSCADTypes.IF_KEYWORD; }
    "let"                       { return OpenSCADTypes.LET_KEYWORD; }
    "assign"                    { return OpenSCADTypes.ASSIGN_KEYWORD; }
    "each"                      { return OpenSCADTypes.EACH_KEYWORD; }
    "intersection_for"          { return OpenSCADTypes.INTERSECTION_FOR_KEYWORD; }

    "include"                   / {IMPORT_COND}  { yybegin(IMPORT_PATH_STATE); return OpenSCADTypes.INCLUDE_KEYWORD; }
    "use"                       / {IMPORT_COND}  { yybegin(IMPORT_PATH_STATE); return OpenSCADTypes.USE_KEYWORD; }

    "linear_extrude"            / {BUILTIN_COND} { return OpenSCADTypes.LINEAR_EXTRUDE_KEYWORD; }
    "rotate_extrude"            / {BUILTIN_COND} { return OpenSCADTypes.ROTATE_EXTRUDE_KEYWORD; }
    "rotate"                    / {BUILTIN_COND} { return OpenSCADTypes.ROTATE_KEYWORD; }
    "translate"                 / {BUILTIN_COND} { return OpenSCADTypes.TRANSLATE_KEYWORD; }
    "scale"                     / {BUILTIN_COND} { return OpenSCADTypes.SCALE_KEYWORD; }
    "resize"                    / {BUILTIN_COND} { return OpenSCADTypes.RESIZE_KEYWORD; }
    "mirror"                    / {BUILTIN_COND} { return OpenSCADTypes.MIRROR_KEYWORD; }
    "multmatrix"                / {BUILTIN_COND} { return OpenSCADTypes.MULTMATRIX_KEYWORD; }
    "color"                     / {BUILTIN_COND} { return OpenSCADTypes.COLOR_KEYWORD; }
    "offset"                    / {BUILTIN_COND} { return OpenSCADTypes.OFFSET_KEYWORD; }
    "minkowski"                 / {BUILTIN_COND} { return OpenSCADTypes.MINKOWSKI_KEYWORD; }
    "hull"                      / {BUILTIN_COND} { return OpenSCADTypes.HULL_KEYWORD; }
    "union"                     / {BUILTIN_COND} { return OpenSCADTypes.UNION_KEYWORD; }
    "difference"                / {BUILTIN_COND} { return OpenSCADTypes.DIFFERENCE_KEYWORD; }
    "intersection"              / {BUILTIN_COND} { return OpenSCADTypes.INTERSECTION_KEYWORD; }
    "render"                    / {BUILTIN_COND} { return OpenSCADTypes.RENDER_KEYWORD; }
    "projection"                / {BUILTIN_COND} { return OpenSCADTypes.PROJECTION_KEYWORD; }

    "cube"                      / {BUILTIN_COND} { return OpenSCADTypes.CUBE_KEYWORD; }
    "cylinder"                  / {BUILTIN_COND} { return OpenSCADTypes.CYLINDER_KEYWORD; }
    "assert"                    / {BUILTIN_COND} { return OpenSCADTypes.ASSERT_KEYWORD; }
    "echo"                      / {BUILTIN_COND} { return OpenSCADTypes.ECHO_KEYWORD; }
    "sphere"                    / {BUILTIN_COND} { return OpenSCADTypes.SPHERE_KEYWORD; }
    "polyhedron"                / {BUILTIN_COND} { return OpenSCADTypes.POLYHEDRON_KEYWORD; }
    "square"                    / {BUILTIN_COND} { return OpenSCADTypes.SQUARE_KEYWORD; }
    "circle"                    / {BUILTIN_COND} { return OpenSCADTypes.CIRCLE_KEYWORD; }
    "polygon"                   / {BUILTIN_COND} { return OpenSCADTypes.POLYGON_KEYWORD; }
    "text"                      / {BUILTIN_COND} { return OpenSCADTypes.TEXT_KEYWORD; }
    "surface"                   / {BUILTIN_COND} { return OpenSCADTypes.SURFACE_KEYWORD; }
    "child"                     / {BUILTIN_COND} { return OpenSCADTypes.CHILD_KEYWORD; }
    "children"                  / {BUILTIN_COND} { return OpenSCADTypes.CHILDREN_KEYWORD; }
    "import"                    / {BUILTIN_COND} { return OpenSCADTypes.IMPORT_KEYWORD; }
    "import_dxf"                / {BUILTIN_COND} { return OpenSCADTypes.IMPORT_DXF_KEYWORD; }
    "import_stl"                / {BUILTIN_COND} { return OpenSCADTypes.IMPORT_STL_KEYWORD; }

    "is_undef"                  / {BUILTIN_COND} { return OpenSCADTypes.IS_UNDEF_KEYWORD; }
    "is_list"                   / {BUILTIN_COND} { return OpenSCADTypes.IS_LIST_KEYWORD; }
    "is_num"                    / {BUILTIN_COND} { return OpenSCADTypes.IS_NUM_KEYWORD; }
    "is_bool"                   / {BUILTIN_COND} { return OpenSCADTypes.IS_BOOL_KEYWORD; }
    "is_string"                 / {BUILTIN_COND} { return OpenSCADTypes.IS_STRING_KEYWORD; }
    "is_function"               / {BUILTIN_COND} { return OpenSCADTypes.IS_FUNCTION_KEYWORD; }

    "cos"                       / {BUILTIN_COND} { return OpenSCADTypes.COS_KEYWORD; }
    "sin"                       / {BUILTIN_COND} { return OpenSCADTypes.SIN_KEYWORD; }
    "tan"                       / {BUILTIN_COND} { return OpenSCADTypes.TAN_KEYWORD; }
    "acos"                      / {BUILTIN_COND} { return OpenSCADTypes.ACOS_KEYWORD; }
    "asin"                      / {BUILTIN_COND} { return OpenSCADTypes.ASIN_KEYWORD; }
    "atan"                      / {BUILTIN_COND} { return OpenSCADTypes.ATAN_KEYWORD; }
    "atan2"                     / {BUILTIN_COND} { return OpenSCADTypes.ATAN2_KEYWORD; }
    "abs"                       / {BUILTIN_COND} { return OpenSCADTypes.ABS_KEYWORD; }
    "ceil"                      / {BUILTIN_COND} { return OpenSCADTypes.CEIL_KEYWORD; }
    "concat"                    / {BUILTIN_COND} { return OpenSCADTypes.CONCAT_KEYWORD; }
    "cross"                     / {BUILTIN_COND} { return OpenSCADTypes.CROSS_KEYWORD; }
    "exp"                       / {BUILTIN_COND} { return OpenSCADTypes.EXP_KEYWORD; }
    "floor"                     / {BUILTIN_COND} { return OpenSCADTypes.FLOOR_KEYWORD; }
    "ln"                        / {BUILTIN_COND} { return OpenSCADTypes.LN_KEYWORD; }
    "len"                       / {BUILTIN_COND} { return OpenSCADTypes.LEN_KEYWORD; }
    "log"                       / {BUILTIN_COND} { return OpenSCADTypes.LOG_KEYWORD; }
    "lookup"                    / {BUILTIN_COND} { return OpenSCADTypes.LOOKUP_KEYWORD; }
    "max"                       / {BUILTIN_COND} { return OpenSCADTypes.MAX_KEYWORD; }
    "min"                       / {BUILTIN_COND} { return OpenSCADTypes.MIN_KEYWORD; }
    "norm"                      / {BUILTIN_COND} { return OpenSCADTypes.NORM_KEYWORD; }
    "ord"                       / {BUILTIN_COND} { return OpenSCADTypes.ORD_KEYWORD; }
    "pow"                       / {BUILTIN_COND} { return OpenSCADTypes.POW_KEYWORD; }
    "rands"                     / {BUILTIN_COND} { return OpenSCADTypes.RANDS_KEYWORD; }
    "round"                     / {BUILTIN_COND} { return OpenSCADTypes.ROUND_KEYWORD; }
    "sign"                      / {BUILTIN_COND} { return OpenSCADTypes.SIGN_KEYWORD; }
    "sqrt"                      / {BUILTIN_COND} { return OpenSCADTypes.SQRT_KEYWORD; }
    "str"                       / {BUILTIN_COND} { return OpenSCADTypes.STR_KEYWORD; }
    "chr"                       / {BUILTIN_COND} { return OpenSCADTypes.CHR_KEYWORD; }
    "search"                    / {BUILTIN_COND} { return OpenSCADTypes.SEARCH_KEYWORD; }
    "version"                   / {BUILTIN_COND} { return OpenSCADTypes.VERSION_KEYWORD; }
    "version_num"               / {BUILTIN_COND} { return OpenSCADTypes.VERSION_NUM_KEYWORD; }
    "parent_module"             / {BUILTIN_COND} { return OpenSCADTypes.PARENT_MODULE_KEYWORD; }

    "."                         { return OpenSCADTypes.DOT; }
    "="                         { return OpenSCADTypes.EQUALS; }
    ";"                         { return OpenSCADTypes.SEMICOLON; }
    ","                         { return OpenSCADTypes.COMMA; }
    "-"                         { return OpenSCADTypes.MINUS; }
    "+"                         { return OpenSCADTypes.PLUS; }
    "/"                         { return OpenSCADTypes.DIV; }
    "*"                         { return OpenSCADTypes.MUL; }
    "%"                         { return OpenSCADTypes.PERC; }
    "^"                         { return OpenSCADTypes.EXP; }
    "?"                         { return OpenSCADTypes.QUERY; }
    ":"                         { return OpenSCADTypes.COLON; }
    "("                         { return OpenSCADTypes.LPARENTH; }
    ")"                         { return OpenSCADTypes.RPARENTH; }
    "{"                         { return OpenSCADTypes.LBRACE; }
    "}"                         { return OpenSCADTypes.RBRACE; }
    "["                         { return OpenSCADTypes.LBRACKET; }
    "]"                         { return OpenSCADTypes.RBRACKET; }
    "#"                         { return OpenSCADTypes.HASH; }

    "<"                         { return OpenSCADTypes.LT; }
    "<="                        { return OpenSCADTypes.LE; }
    ">"                         { return OpenSCADTypes.GT; }
    ">="                        { return OpenSCADTypes.GE; }
    "=="                        { return OpenSCADTypes.EQ; }
    "!="                        { return OpenSCADTypes.NE; }

    "&&"                        { return OpenSCADTypes.AND; }
    "||"                        { return OpenSCADTypes.OR; }
    "!"                         { return OpenSCADTypes.EXCL; }

    {NUMBER_LITERAL}            { return OpenSCADTypes.NUMBER_LITERAL; }
    {IDENTIFIER}                { return OpenSCADTypes.IDENTIFIER; }
    {STRING_LITERAL}            { return OpenSCADTypes.STRING_LITERAL; }
}

{WHITE_SPACE}+                  { return TokenType.WHITE_SPACE; }

<IMPORT_PATH_STATE> {
    "<"                         { return OpenSCADTypes.IMPORT_START; }
    {IMPORT_PATH}               { return OpenSCADTypes.IMPORT_PATH; }
    ">"                         { yybegin(YYINITIAL); return OpenSCADTypes.IMPORT_END; }
}

<BUILTIN_OVERRIDABLE> {
    "("                         { yybegin(YYINITIAL); return OpenSCADTypes.LPARENTH; }
    {IDENTIFIER}                { yybegin(YYINITIAL); return OpenSCADTypes.IDENTIFIER; }
}

[^]                             { return TokenType.BAD_CHARACTER; }
