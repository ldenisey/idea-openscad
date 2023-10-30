package com.javampire.openscad.formatter;

import com.intellij.application.options.IndentOptionsEditor;
import com.intellij.lang.Language;
import com.intellij.psi.codeStyle.CodeStyleSettingsCustomizable;
import com.intellij.psi.codeStyle.LanguageCodeStyleSettingsProvider;
import com.javampire.openscad.OpenSCADLanguage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class OpenSCADLanguageCodeStyleSettingsProvider extends LanguageCodeStyleSettingsProvider {

    public final static String CONF_EXAMPLE = "/*\n" +
            "You are reading the \".scad\" entry\n" +
            "for OpenSCAD files.\n" +
            "*/\n" +
            "use <some/path/used_file.scad>\n" +
            "include </another/path/included_file.scad>\n" +
            "\n" +
            "$fn=64; // line-end comment about some_var\n" +
            "some_var = 127; // [1:127]\n" +
            "\n" +
            "/**\n" +
            " * some_module has a very nice documentation.\n" +
            " */\n" +
            "module some_module(var1 = 1, var2, foo) {\n" +
            "    for (i = [0, var2]) {\n" +
            "        let (j = 3) {\n" +
            "            translate([1, 2, j]) {\n" +
            "                rotate([0, 0, 90])\n" +
            "                    color(\"red\") cylinder(1, 2, j);\n" +
            "\n" +
            "            }\n" +
            "        }\n" +
            "    }\n" +
            "\n" +
            "    foo = \"string_value\";\n" +
            "}\n" +
            "\n" +
            "function some_function(var1, var2 = \"string value\") = foo + sin(1.128e+10);\n" +
            "\n" +
            "if (x < max([1, 10]) || !(x > -20 && x == 15)) {\n" +
            "    some_module(x * 2, 42, \"string\");\n" +
            "\n" +
            "} else if (x >= 42) {\n" +
            "    some_function(var1 = x - 3, var2 = \"dummy string\");\n" +
            "\n" +
            "} else \n" +
            "    square(cos(x % 2));\n";

    @NotNull
    @Override
    public Language getLanguage() {
        return OpenSCADLanguage.INSTANCE;
    }

    @Nullable
    @Override
    public IndentOptionsEditor getIndentOptionsEditor() {
        return new OpenSCADIndentOptionsEditor();
    }

    @Override
    public void customizeSettings(@NotNull CodeStyleSettingsCustomizable consumer, @NotNull SettingsType settingsType) {
        if (settingsType == SettingsType.SPACING_SETTINGS) {
            // Before parenthesis
            consumer.showStandardOptions(
                    "SPACE_BEFORE_METHOD_PARENTHESES",
                    "SPACE_BEFORE_METHOD_CALL_PARENTHESES",
                    "SPACE_BEFORE_IF_PARENTHESES",
                    "SPACE_BEFORE_FOR_PARENTHESES"
            );

            // Around operators
            consumer.showStandardOptions(
                    "SPACE_AROUND_ASSIGNMENT_OPERATORS",
                    "SPACE_AROUND_LOGICAL_OPERATORS",
                    "SPACE_AROUND_EQUALITY_OPERATORS",
                    "SPACE_AROUND_RELATIONAL_OPERATORS",
                    "SPACE_AROUND_ADDITIVE_OPERATORS",
                    "SPACE_AROUND_MULTIPLICATIVE_OPERATORS",
                    "SPACE_AROUND_UNARY_OPERATOR"
            );
            consumer.renameStandardOption("SPACE_AROUND_ASSIGNMENT_OPERATORS", "Assignment operator (=)");

            // Before left brace
            consumer.showStandardOptions(
                    "SPACE_BEFORE_METHOD_LBRACE",
                    "SPACE_BEFORE_IF_LBRACE",
                    "SPACE_BEFORE_FOR_LBRACE"
            );
            consumer.renameStandardOption("SPACE_BEFORE_IF_LBRACE", "'if', 'else' left brace");
            consumer.renameStandardOption("SPACE_BEFORE_FOR_LBRACE", "'for', 'intersect_for', 'assign', 'let' left brace");

            // Before keywords
            consumer.showStandardOptions(
                    "SPACE_BEFORE_ELSE_KEYWORD"
            );

            // Within
            consumer.showStandardOptions(
                    "SPACE_WITHIN_BRACES",
                    "SPACE_WITHIN_BRACKETS",
                    "SPACE_WITHIN_METHOD_PARENTHESES",
                    "SPACE_WITHIN_METHOD_CALL_PARENTHESES",
                    "SPACE_WITHIN_IF_PARENTHESES",
                    "SPACE_WITHIN_FOR_PARENTHESES"
            );
            consumer.renameStandardOption("SPACE_WITHIN_FOR_PARENTHESES", "'for', 'intersect_for', 'assign', 'let' parentheses");
            consumer.renameStandardOption("SPACE_AROUND_UNARY_OPERATOR", "Unary operators (!, -, +)");

            // Other
            consumer.showStandardOptions(
                    "SPACE_AFTER_COMMA",
                    "SPACE_BEFORE_COMMA"
            );
        } else if (settingsType == SettingsType.WRAPPING_AND_BRACES_SETTINGS) {
            consumer.showStandardOptions(
                    "RIGHT_MARGIN"
            );
        }
    }

    @Nullable
    @Override
    public String getCodeSample(@NotNull SettingsType settingsType) {
        return CONF_EXAMPLE;
    }
}
