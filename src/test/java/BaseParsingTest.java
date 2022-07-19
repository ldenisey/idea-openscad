import com.intellij.testFramework.ParsingTestCase;
import com.javampire.openscad.parser.OpenSCADParserDefinition;
import org.junit.Test;

import java.io.IOException;

public class BaseParsingTest extends ParsingTestCase {
    public BaseParsingTest() {
        super("", "scad", new OpenSCADParserDefinition());
    }

    @Override
    protected String getTestDataPath() {
        return "src/test/testData/openscad/base";
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
    public void testecho() throws IOException {
        doTest("_psidump");
    }

    @Test
    public void testfunction_literal() throws IOException {
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
    public void testlet() throws IOException {
        doTest("_psidump");
    }

    @Test
    public void testord() throws IOException {
        doTest("_psidump");
    }
}
