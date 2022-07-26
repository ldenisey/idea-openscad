package com.javampire.openscad.action;

import com.intellij.openapi.actionSystem.ActionPlaces;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiFile;
import com.javampire.openscad.OpenSCADLanguage;
import com.javampire.openscad.settings.OpenSCADSettings;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class OpenSCADExecutableAction extends AnAction {

    @Override
    public void update(@NotNull AnActionEvent event) {
        if (!OpenSCADSettings.getInstance().hasExecutable()) {
            event.getPresentation().setEnabledAndVisible(false);
        } else if (ActionPlaces.isPopupPlace(event.getPlace())) {
            final PsiFile psiFile = event.getData(CommonDataKeys.PSI_FILE);
            event.getPresentation().setEnabledAndVisible(psiFile != null && psiFile.getLanguage().isKindOf(OpenSCADLanguage.INSTANCE));
        }
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        // Check arguments
        final List<String> arguments = getArguments(event);
        if (arguments == null) {
            return;
        }

        // Execute command
        final OpenSCADExecutor executor = OpenSCADExecutor.execute(arguments);
        if (executor == null) {
            Messages.showErrorDialog(
                    OpenSCADExecutor.ERROR_NO_EXE,
                    "OpenSCAD Executable not Set"
            );
        } else if (executor.getException() != null) {
            ApplicationManager.getApplication().invokeLater(() ->
                    Messages.showErrorDialog(
                            String.format(OpenSCADExecutor.ERROR_EXCEPTION, executor.getCommand()) + ExceptionUtils.getFullStackTrace(executor.getException()),
                            "OpenSCAD Execution Exception"));
        } else if (executor.getReturnCode() != 0) {
            ApplicationManager.getApplication().invokeLater(() ->
                    Messages.showErrorDialog(
                            String.format(OpenSCADExecutor.ERROR_RETURN_CODE, executor.getCommand(), executor.getReturnCode(), executor.getStderr()),
                            "OpenSCAD Execution Error")
            );
        }
    }

    /**
     * Arguments specific to an action.
     * If null, no command will be executed.
     *
     * @param event Event.
     * @return Arguments.
     */
    abstract protected List<String> getArguments(@NotNull AnActionEvent event);
}
