package com.javampire.openscad.action;

import com.intellij.openapi.actionSystem.ActionPlaces;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.psi.PsiFile;
import com.javampire.openscad.OpenSCADLanguage;
import com.javampire.openscad.settings.OpenSCADSettings;
import org.jetbrains.annotations.NotNull;

public abstract class OpenSCADAction extends AnAction {

    @Override
    public void update(@NotNull final AnActionEvent event) {
        if (!OpenSCADSettings.getInstance().hasExecutable()) {
            event.getPresentation().setEnabledAndVisible(false);
        } else if (ActionPlaces.isPopupPlace(event.getPlace()) || ActionPlaces.EDITOR_TOOLBAR.equals(event.getPlace())) {
            final PsiFile psiFile = event.getData(CommonDataKeys.PSI_FILE);
            event.getPresentation().setEnabledAndVisible(psiFile != null && psiFile.getLanguage() == OpenSCADLanguage.INSTANCE);
        }
    }
}
