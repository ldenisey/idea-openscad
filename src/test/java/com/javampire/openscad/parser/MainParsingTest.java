package com.javampire.openscad.parser;

import com.intellij.testFramework.ParsingTestCase;
import org.junit.Test;

import java.io.IOException;

public class MainParsingTest extends ParsingTestCase {
    public MainParsingTest() {
        super("", "scad", new OpenSCADParserDefinition());
    }

    @Override
    protected String getTestDataPath() {
        return "src/test/testData/openscad/parser";
    }

    @Override
    protected boolean skipSpaces() {
        return false;
    }

    @Override
    protected boolean includeRanges() {
        return true;
    }

    @Test
    public void testCyclicUsedFile() throws IOException {
        doTest("_psidump");
    }

    @Test
    public void testSubColorProvider() throws IOException {
        doTest("_psidump");
    }

    @Test
    public void testColorProvider() throws IOException {
        doTest("_psidump");
    }

    @Test
    public void testMain() throws IOException {
        doTest("_psidump");
    }
}
