package com.javampire.openscad.action;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.ui.Messages;
import com.javampire.openscad.editor.OpenSCADPreviewFileEditor;
import org.jetbrains.annotations.NotNull;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

public abstract class OpenSCADExecutableAction extends OpenSCADAction {

    protected boolean performing = false;

    @Override
    public void actionPerformed(@NotNull final AnActionEvent event) {
        // Check arguments
        final List<String> arguments = getArguments(event);
        if (arguments == null) {
            return;
        }

        if (!preExecution(event)) {
            return;
        }

        performing = true;
        ProgressManager.getInstance().run(new Task.Backgroundable(
                event.getProject(),
                capitalizeFully(event.getPresentation().getText()),
                true,
                null
        ) {
            @Override
            public void run(@NotNull ProgressIndicator progressIndicator) {
                // Execute command
                final OpenSCADExecutor executor = OpenSCADExecutor.execute(arguments);
                if (executor == null) {
                    Messages.showErrorDialog(
                            OpenSCADExecutor.ERROR_NO_EXE,
                            "OpenSCAD Executable not Set"
                    );
                } else if (executor.getException() != null) {
                    final Notification notification = new Notification(
                            OpenSCADPreviewFileEditor.class.getSimpleName(),
                            "OpenSCAD execution exception",
                            String.format(OpenSCADExecutor.ERROR_EXCEPTION, executor.getCommand()) + getFullStackTrace(executor.getException()),
                            NotificationType.ERROR
                    );
                    notification.notify(event.getProject());
                } else if (executor.getReturnCode() != 0) {
                    final Notification notification = new Notification(
                            OpenSCADPreviewFileEditor.class.getSimpleName(),
                            "OpenSCAD execution error",
                            String.format(OpenSCADExecutor.ERROR_RETURN_CODE, executor.getCommand(), executor.getReturnCode(), executor.getStderr()),
                            NotificationType.ERROR
                    );
                    notification.notify(event.getProject());
                }
                postExecution(event);
                performing = false;
            }
        });
    }

    /**
     * Arguments specific to an action.
     * If null, no command will be executed.
     *
     * @param event Event.
     * @return Arguments.
     */
    abstract protected List<String> getArguments(@NotNull final AnActionEvent event);

    /**
     * Actions that will be executed before the execution of the command.
     *
     * @param event Event.
     * @return True to proceed with the action, false to cancel it.
     */
    protected Boolean preExecution(@NotNull final AnActionEvent event) {
        return true;
    }

    /**
     * Actions that will be executed after the execution of the command.
     *
     * @param event Event.
     */
    protected void postExecution(@NotNull final AnActionEvent event) {

    }

    /**
     * Capitalize first letter of each word.
     *
     * @param str String to capitalize.
     * @return Capitalized string.
     */
    private String capitalizeFully(final String str) {
        final char[] chars = str.toLowerCase().toCharArray();
        boolean capitalizeNext = true;
        for (int i = 0; i < str.length(); i++) {
            if (capitalizeNext) {
                chars[i] = Character.toTitleCase(chars[i]);
                capitalizeNext = false;
            } else if (Character.isWhitespace(chars[i])) {
                capitalizeNext = true;
            }
        }
        return new String(chars);
    }

    /**
     * Pretty print stack trace.
     *
     * @param throwable Exception.
     * @return Pretty printed stack trace.
     */
    private String getFullStackTrace(final Throwable throwable) {
        final StringWriter sw = new StringWriter();
        throwable.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }
}
