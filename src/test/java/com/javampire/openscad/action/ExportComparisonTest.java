package com.javampire.openscad.action;

import com.intellij.openapi.diagnostic.Logger;
import junit.framework.TestCase;
import net.minidev.json.JSONObject;
import org.junit.Ignore;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Comparison test, no need to run it regularly.
 */
@Ignore
public class ExportComparisonTest extends TestCase {
    private final static Logger LOG = Logger.getInstance(ExportComparisonTest.class);

    private final static String[] extensions = {"stl", "off", "amf", "3mf", "csg", "dxf", "svg", "pdf", "png", "echo", "ast", "term", "nef3", "nefdbg"};

    private final static String openSCADExecutable = "C:\\Program Files\\OpenSCAD\\openscad.exe";
    private final static String SCAD_FILE_URL = "https://raw.githubusercontent.com/openscad/openscad/master/examples/Basics/logo.scad";

    public void testCompareExportExecutionTime() throws IOException {
        // Getting a scad file
        final File scadFile = Files.createTempFile("exportTestFile", ".scad").toFile();
        final ReadableByteChannel readableByteChannel = Channels.newChannel(new URL(SCAD_FILE_URL).openStream());
        try (final FileOutputStream fileOutputStream = new FileOutputStream(scadFile)) {
            fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
        }

        // Looping through export format and running 10 export commands each
        final Map<String, Map<Integer, Long>> result = new HashMap<>();
        for (final String ext : extensions) {
            LOG.info("Testing format " + ext);
            final Map<Integer, Long> innerResult = new HashMap<>();
            final Path exportFile = Files.createTempFile("exportTest", "." + ext);
            final List<String> arguments = List.of("-o", exportFile.toString(), scadFile.getPath());
            for (int i = 0; i < 10; i++) {
                final OpenSCADExecutor executor = new OpenSCADExecutor(openSCADExecutable, arguments);
                long executionTime = executeAndGetTime(executor);
                if (executor.getException() != null || executor.getReturnCode() != 0) {
                    executionTime = -1;
                    LOG.info(executor.getReturnCode() + ":" + executor.getStderr());
                }
                innerResult.put(i, executionTime);
                LOG.info("    Run " + i + " : " + executionTime);
            }
            result.put(ext, innerResult);
        }
        LOG.info(new JSONObject(result).toJSONString());
    }

    private long executeAndGetTime(final OpenSCADExecutor executor) {
        final long startTime = System.nanoTime();
        OpenSCADExecutor.startAndWaitFor(executor);
        final long endTime = System.nanoTime();
        return (endTime - startTime);
    }
}
