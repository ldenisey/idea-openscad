package com.javampire.openscad.settings;

import com.intellij.openapi.diagnostic.Logger;
import com.javampire.openscad.action.OpenSCADExecutor;
import org.apache.commons.collections.map.LinkedMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class OpenSCADInfo {
    private static final Logger LOG = Logger.getInstance(OpenSCADInfo.class);
    private static String infoString = null;
    private static LinkedMap infoMap = null;

    private OpenSCADInfo() {
    }

    private static String getInfoString() {
        if (infoString == null) {
            // Run the OpenSCAD executable to get info
            final OpenSCADExecutor executor = OpenSCADExecutor.execute(Arrays.asList("--info"));
            if (executor == null) {
                LOG.warn(OpenSCADExecutor.ERROR_NO_EXE);
            } else if (executor.getException() != null) {
                LOG.error(String.format(OpenSCADExecutor.ERROR_EXCEPTION, executor.getCommand()), executor.getException());
            } else if (executor.getReturnCode() != 0) {
                LOG.error(String.format(OpenSCADExecutor.ERROR_RETURN_CODE, executor.getCommand(), executor.getReturnCode(), executor.getStderr()));
            } else {
                infoString = executor.getStdout();
            }
        }
        return infoString;
    }

    protected static void setInfoString(final String infoString) {
        OpenSCADInfo.infoString = infoString;
    }

    protected static void reset() {
        setInfoString(null);
        infoMap = null;
    }

    private static LinkedMap getInfoMap() {
        if (infoMap == null) {
            infoMap = new LinkedMap();
            parseInfoString();
        }
        return infoMap;
    }

    private static void parseInfoString() {
        String key = null;
        StringBuilder value = new StringBuilder();
        final String infoString = getInfoString();
        if (infoString == null) {
            return;
        }

        for (final String line : getInfoString().split("\\R")) {
            if (line.isEmpty()) {
                if (key != null) {
                    // End of multiline pair
                    infoMap.put(key, value.toString().trim());
                    key = null;
                    value = new StringBuilder();
                }
                continue;
            }

            if (line.startsWith("  ")) {
                // Multiline continuation value
                value.append(line.trim()).append("\n");
                continue;
            }

            if (!line.contains(":")) {
                // Most probably caused by string property that contains a return char
                if (key == null) {
                    // Appending to the value of the last inserted key
                    final String lastKey = (String) infoMap.lastKey();
                    final String lastValue = infoMap.get(lastKey) + "\n" + line.trim();
                    infoMap.remove(lastKey);
                    infoMap.put(lastKey, lastValue);
                } else {
                    value.append(line.trim()).append("\n");
                }
                continue;
            }

            final int firstColonPos = line.indexOf(":");
            if (firstColonPos == line.length() - 1) {
                // Starting new multiline entry
                key = line.substring(0, firstColonPos);
            } else {
                // Key value inline pair
                if (key != null) {
                    // End of multiline pair
                    infoMap.put(key, value.toString().trim());
                    key = null;
                    value = new StringBuilder();
                }
                infoMap.put(line.substring(0, firstColonPos), line.substring(firstColonPos + 2));
            }
        }
    }

    public static @Nullable String getStringInfo(@NotNull final String key) {
        return (String) getInfoMap().get(key);
    }

    public static @Nullable List<String> getListInfo(@NotNull final String key) {
        final String value = (String) getInfoMap().get(key);
        if (value == null) {
            return null;
        } else {
            return Arrays.asList(value.split("\n"));
        }
    }

    public static @Nullable List<String> getLibraryPaths() {
        return getListInfo("OpenSCAD library path");
    }

    public static @Nullable String getApplicationPath() {
        return getStringInfo("Application Path");
    }

    public static @Nullable String getOpenSCADVersion() {
        return getStringInfo("OpenSCAD Version");
    }

    public static Set<String> getInfoKeys() {
        return getInfoMap().keySet();
    }
}
