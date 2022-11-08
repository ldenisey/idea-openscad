package com.javampire.openscad.completion;

import com.intellij.testFramework.fixtures.LightJavaCodeInsightFixtureTestCase;
import com.javampire.openscad.OpenSCADFileType;
import org.junit.Test;

import java.util.List;

public class CompletionTest extends LightJavaCodeInsightFixtureTestCase {

    @Override
    protected String getTestDataPath() {
        return "src/test/testData/openscad/completion";
    }

    @Test
    public void testCompletion_Function_builtin_simple() {
        myFixture.configureByText(OpenSCADFileType.INSTANCE, "co<caret>");
        myFixture.completeBasic();
        final List<String> lookupElementStrings = myFixture.getLookupElementStrings();
        assertNotNull(lookupElementStrings);
        assertSameElements(lookupElementStrings, "color", "concat", "cos", "acos");
    }

    @Test
    public void testCompletion_Function_userdefined_simple() {
        myFixture.configureByText(OpenSCADFileType.INSTANCE, "function cocorico() = 42;\ncoco<caret>");
        myFixture.completeBasic();
        myFixture.checkResult("function cocorico() = 42;\ncocorico");
    }

    @Test
    public void testCompletion_Function_userdefined_simple_with_builtins() {
        myFixture.configureByText(OpenSCADFileType.INSTANCE, "co<caret>\nfunction cocorico() = 42;");
        myFixture.completeBasic();
        final List<String> lookupElementStrings = myFixture.getLookupElementStrings();
        assertNotNull(lookupElementStrings);
        assertSameElements(lookupElementStrings, "cocorico", "color", "concat", "cos", "acos");
    }

    @Test
    public void testCompletion_Function_userdefined_inaccessible() {
        myFixture.configureByText(OpenSCADFileType.INSTANCE,
                "module dummy() {\n" +
                        "    function cocorico() = 42;\n" +
                        "    color(cocorico());\n" +
                        "}\n\n" +
                        "co<caret>");
        myFixture.completeBasic();
        final List<String> lookupElementStrings = myFixture.getLookupElementStrings();
        assertNotNull(lookupElementStrings);
        assertSameElements(lookupElementStrings, "color", "concat", "cos", "acos");
    }

    @Test
    public void testCompletion_Import_simple() {
        myFixture.copyFileToProject("include_sub.scad");
        myFixture.copyFileToProject("include_main.scad");
        myFixture.configureByText(OpenSCADFileType.INSTANCE, "include <include_main.scad>\n\nsa<caret>");
        myFixture.completeBasic();
        myFixture.checkResult("include <include_main.scad>\n\nsame");
    }

    @Test
    public void testCompletion_Import_subimport() {
        myFixture.copyFileToProject("include_sub.scad");
        myFixture.copyFileToProject("include_main.scad");
        myFixture.configureByText(OpenSCADFileType.INSTANCE, "include <include_main.scad>\n\nno<caret>");
        myFixture.completeBasic();
        final List<String> lookupElementStrings = myFixture.getLookupElementStrings();
        assertNotNull(lookupElementStrings);
        assertSameElements(lookupElementStrings, "not", "norm");
    }

    @Test
    public void testCompletion_Import_recursive() {
        myFixture.copyFileToProject("include_recursive.scad");
        myFixture.copyFileToProject("include_recursive2.scad");
        myFixture.configureByText(OpenSCADFileType.INSTANCE, "include <include_recursive.scad>\n\nno<caret>");
        myFixture.completeBasic();
        final List<String> lookupElementStrings = myFixture.getLookupElementStrings();
        assertNotNull(lookupElementStrings);
        assertSameElements(lookupElementStrings, "not", "norm");
    }

    @Test
    public void testCompletion_Variable_userdefined_simple() {
        myFixture.configureByText(OpenSCADFileType.INSTANCE, "var1 = 1;\nva<caret>");
        myFixture.completeBasic();
        myFixture.checkResult("var1 = 1;\nvar1");
    }

    @Test
    public void testCompletion_Variable_let_op_for() {
        myFixture.configureByText(OpenSCADFileType.INSTANCE, "module innerModule(var1) {\n" +
                "    for (var2 = [3:5]) {\n" +
                "        if (var2 != 4)\n" +
                "            let(var3 = 3) echo(var1, va<caret>);\n" +
                "    }\n" +
                "}");
        myFixture.completeBasic();
        final List<String> lookupElementStrings = myFixture.getLookupElementStrings();
        assertNotNull(lookupElementStrings);
        assertSameElements(lookupElementStrings, "var1", "var2", "var3");
    }

    @Test
    public void testCompletion_Variable_let_expr() {
        myFixture.configureByText(OpenSCADFileType.INSTANCE, "var1 = 1;\n" +
                "x = let(var2 = 2) var1 + va<caret>;");
        myFixture.completeBasic();
        final List<String> lookupElementStrings = myFixture.getLookupElementStrings();
        assertNotNull(lookupElementStrings);
        assertSameElements(lookupElementStrings, "var1", "var2");
    }
}
