package com.javampire.openscad.action;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.actionSystem.ActionPlaces;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiFile;
import com.javampire.openscad.OpenSCADLanguage;
import com.javampire.openscad.editor.OpenSCADPreviewFileEditor;
import com.javampire.openscad.settings.OpenSCADSettings;
import org.apache.commons.lang.WordUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class OpenSCADExecutableAction extends AnAction {

    protected boolean performing = false;

    @Override
    public void update(@NotNull final AnActionEvent event) {
        if (!OpenSCADSettings.getInstance().hasExecutable()) {
            event.getPresentation().setEnabledAndVisible(false);
        } else if (ActionPlaces.isPopupPlace(event.getPlace()) || ActionPlaces.EDITOR_TOOLBAR.equals(event.getPlace())) {
            final PsiFile psiFile = event.getData(CommonDataKeys.PSI_FILE);
            event.getPresentation().setEnabledAndVisible(psiFile != null && psiFile.getLanguage() == OpenSCADLanguage.INSTANCE);
        }
    }

    @Override
    public void actionPerformed(@NotNull final AnActionEvent event) {
        // Check arguments
        final List<String> arguments = getArguments(event);
        if (arguments == null) {
            return;
        }

        performing = true;
        ProgressManager.getInstance().run(new Task.Backgroundable(
                event.getProject(),
                WordUtils.capitalizeFully(event.getPresentation().getText()),
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
                            String.format(OpenSCADExecutor.ERROR_EXCEPTION, executor.getCommand()) + ExceptionUtils.getFullStackTrace(executor.getException()),
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
                postSuccessfulExecutionAction(event);
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
     * Actions that will be executed after the execution of the command.
     *
     * @param event Event.
     */
    protected void postSuccessfulExecutionAction(@NotNull final AnActionEvent event) {

    }
}
