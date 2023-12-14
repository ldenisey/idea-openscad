package com.javampire.openscad.action;

import com.intellij.openapi.actionSystem.*;
import com.intellij.psi.PsiFile;
import com.javampire.openscad.OpenSCADLanguage;
import com.javampire.openscad.settings.OpenSCADSettings;
import org.jetbrains.annotations.NotNull;

public abstract class OpenSCADAction extends AnAction {

    /**
     * Set the presentation enable and visible if OpenSCAD executable is found and if the target file is an OpenSCAD one.
     * @param event Action event.
     * @return Event presentation.
     */
    protected Presentation checkOpenSCADPrerequisites(@NotNull final AnActionEvent event) {
        final Presentation presentation = event.getPresentation();
        if (OpenSCADSettings.getInstance().hasExecutable()
                && (ActionPlaces.isPopupPlace(event.getPlace()) || ActionPlaces.EDITOR_TOOLBAR.equals(event.getPlace()))) {
            final PsiFile psiFile = event.getData(CommonDataKeys.PSI_FILE);
            presentation.setEnabledAndVisible(psiFile != null && psiFile.getLanguage() == OpenSCADLanguage.INSTANCE);
        } else {
            presentation.setEnabledAndVisible(false);
        }
        return presentation;
    }
}
