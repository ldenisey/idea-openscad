package com.javampire.openscad.action;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.diagnostic.Logger;
import com.javampire.openscad.settings.OpenSCADSettings;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class OpenSCADExecutor {
    private static final Logger LOG = Logger.getInstance(OpenSCADExecutor.class);
    public static final String ERROR_NO_EXE = "Can not find OpenSCAD executable path. Please configure it in Settings -> Languages & Frameworks -> OpenSCAD and retry.";
    public static final String ERROR_EXCEPTION = "Execution of %s failed with exception.";
    public static final String ERROR_RETURN_CODE = "Execution of %s failed with error %s : %s";

    private final String openSCADExecutable;
    private final List<String> arguments;
    private String stderr = null;
    private String stdout = null;
    private Integer returnCode = null;
    private Throwable exception = null;

    protected OpenSCADExecutor(@NotNull final String openSCADExecutable, @NotNull final List<String> arguments) {
        this.openSCADExecutable = openSCADExecutable;
        this.arguments = arguments;
    }

    public List<String> getCommand() {
        final List<String> command = new ArrayList<>();
        command.add(openSCADExecutable);
        command.addAll(arguments);
        return command;
    }

    public String getStderr() {
        return stderr;
    }

    public String getStdout() {
        return stdout;
    }

    public Integer getReturnCode() {
        return returnCode;
    }

    public Throwable getException() {
        return exception;
    }

    /**
     * Execute a command with OpenSCAD executable configured in the settings.
     * If any, exception during execution is accessible with {@link #getException()}.
     *
     * @param arguments Arguments as a list of strings.
     * @return Null if the OpenSCAD executable is not configured, else this with execution information.
     */
    public static @Nullable OpenSCADExecutor execute(@NotNull final List<String> arguments) {
        final OpenSCADSettings settings = OpenSCADSettings.getInstance();
        if (settings.hasExecutable()) {
            final OpenSCADExecutor executor = new OpenSCADExecutor(settings.getOpenSCADExecutable(), arguments);
            ApplicationManager.getApplication().runReadAction(() -> {
                startAndWaitFor(executor);
            });
            return executor;
        } else {
            LOG.warn(ERROR_NO_EXE);
            return null;
        }
    }

    protected static @NotNull OpenSCADExecutor startAndWaitFor(@NotNull final OpenSCADExecutor executor) {
        final ProcessBuilder processBuilder = new ProcessBuilder().command(executor.getCommand());
        try {
            final Process process = processBuilder.start();
            executor.stderr = IOUtils.toString(process.getErrorStream(), StandardCharsets.UTF_8);
            executor.stdout = IOUtils.toString(process.getInputStream(), StandardCharsets.UTF_8);
            executor.returnCode = process.waitFor();
        } catch (final IOException | InterruptedException e) {
            LOG.error("Execution of " + executor.getCommand() + " failed with an exception.", e);
            executor.exception = e;
        }
        return executor;
    }
}
