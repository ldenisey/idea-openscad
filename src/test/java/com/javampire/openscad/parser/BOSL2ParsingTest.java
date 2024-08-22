package com.javampire.openscad.parser;

import com.intellij.testFramework.ParsingTestCase;

import java.io.IOException;

public class BOSL2ParsingTest extends ParsingTestCase {
    public BOSL2ParsingTest() {
        super("", "scad", new OpenSCADParserDefinition());
    }

    @Override
    protected String getTestDataPath() {
        return "src/test/testData/openscad/parser/bosl2";
    }

    @Override
    protected boolean skipSpaces() {
        return false;
    }

    @Override
    protected boolean includeRanges() {
        return true;
    }

    public void testFunctionsAndModules() throws IOException {
        doTest("_psidump");
    }

}
