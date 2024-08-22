package com.javampire.openscad.formatter;

import com.intellij.application.options.IndentOptionsEditor;
import com.intellij.lang.Language;
import com.intellij.psi.codeStyle.CodeStyleSettingsCustomizable;
import com.intellij.psi.codeStyle.LanguageCodeStyleSettingsProvider;
import com.javampire.openscad.OpenSCADLanguage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class OpenSCADLanguageCodeStyleSettingsProvider extends LanguageCodeStyleSettingsProvider {

    public final static String CONF_EXAMPLE = """
            /*
            You are reading the ".scad" entry
            for OpenSCAD files.
            */
            use <some/path/used_file.scad>
            include </another/path/included_file.scad>

            $fn=64; // line-end comment about some_var
            some_var = 127; // [1:127]

            /**
             * some_module has a very nice documentation.
             */
            module some_module(var1 = 1, var2, foo) {
                for (i = [0, var2]) {
                    let (j = 3) {
                        translate([1, 2, j]) {
                            rotate([0, 0, 90])
                                color("red") cylinder(1, 2, j);

                        }
                    }
                }

                foo = "string_value";
            }

            function some_function(var1, var2 = "string value") = foo + sin(1.128e+10);

            if (x < max([1, 10]) || !(x > -20 && x == 15)) {
                some_module(x * 2, 42, "string");

            } else if (x >= 42) {
                some_function(var1 = x - 3, var2 = "dummy string");

            } else\s
                square(cos(x % 2));
            """;

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
            consumer.showCustomOption(OpenSCADCodeStyleSettings.class, "SPACE_AROUND_EQ_IN_NAMED_PARAMETER",
                    "Around = in named parameter", "Other");
            consumer.showCustomOption(OpenSCADCodeStyleSettings.class, "SPACE_AROUND_EQ_IN_ARG_ASSIGNMENT",
                    "Around = in argument assignment", "Other");

        } else if (settingsType == SettingsType.WRAPPING_AND_BRACES_SETTINGS) {
            consumer.showStandardOptions(
                    "RIGHT_MARGIN",
                    "CALL_PARAMETERS_WRAP",
                    "METHOD_PARAMETERS_WRAP",
                    ""
            );
        } else if (settingsType == SettingsType.INDENT_SETTINGS) {
            consumer.showCustomOption(OpenSCADCodeStyleSettings.class,
                    "INDENT_CASCADING_TRANSFORMATIONS",
                    "Indent cascading transformations",
                    "Tabs and Indents"
            );
        }
    }

    @Nullable
    @Override
    public String getCodeSample(@NotNull SettingsType settingsType) {
        return CONF_EXAMPLE;
    }
}
