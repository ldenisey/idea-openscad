package com.javampire.openscad.parser;

import com.intellij.testFramework.ParsingTestCase;
import org.junit.Test;

import java.io.IOException;

public class BaseParsingTest extends ParsingTestCase {
    public BaseParsingTest() {
        super("", "scad", new OpenSCADParserDefinition());
    }

    @Override
    protected String getTestDataPath() {
        return "src/test/testData/openscad/parser/base";
    }

    @Override
    protected boolean skipSpaces() {
        return false;
    }

    @Override
    protected boolean includeRanges() {
        return true;
    }

    public void testassert() throws IOException {
        doTest("_psidump");
    }

    @Test
    public void testassign() throws IOException {
        doTest("_psidump");
    }

    @Test
    public void testcomment() throws IOException {
        doTest("_psidump");
    }

    @Test
    public void testconcat() throws IOException {
        doTest("_psidump");
    }

    @Test
    public void testeach() throws IOException {
        doTest("_psidump");
    }

    @Test
    public void testecho() throws IOException {
        doTest("_psidump");
    }

    @Test
    public void testelvis() throws IOException {
        doTest("_psidump");
    }

    @Test
    public void testfor() throws IOException {
        doTest("_psidump");
    }

    @Test
    public void testfunction() throws IOException {
        doTest("_psidump");
    }

    @Test
    public void testif() throws IOException {
        doTest("_psidump");
    }

    @Test
    public void testinclude() throws IOException {
        doTest("_psidump");
    }

    @Test
    public void testintersection_for() throws IOException {
        doTest("_psidump");
    }

    @Test
    public void testis_bool() throws IOException {
        doTest("_psidump");
    }

    @Test
    public void testis_function() throws IOException {
        doTest("_psidump");
    }

    @Test
    public void testis_list() throws IOException {
        doTest("_psidump");
    }

    @Test
    public void testis_num() throws IOException {
        doTest("_psidump");
    }

    @Test
    public void testis_string() throws IOException {
        doTest("_psidump");
    }

    @Test
    public void testis_undef() throws IOException {
        doTest("_psidump");
    }

    @Test
    public void testlen() throws IOException {
        doTest("_psidump");
    }

    @Test
    public void testlet() throws IOException {
        doTest("_psidump");
    }

    @Test
    public void testord() throws IOException {
        doTest("_psidump");
    }

    @Test
    public void testtypes() throws IOException {
        doTest("_psidump");
    }
}
