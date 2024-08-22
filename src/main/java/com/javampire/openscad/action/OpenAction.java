package com.javampire.openscad.action;

import com.intellij.openapi.actionSystem.ActionUpdateThread;
import com.intellij.openapi.actionSystem.ActionPlaces;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.vfs.VirtualFile;
import com.javampire.openscad.OpenSCADIcons;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

/**
 * Can be called from context menu or preview toolbar. Calls OpenSCAD with the selected file opened.
 */
public class OpenAction extends OpenSCADExecutableAction {

    @Override
    public void update(@NotNull final AnActionEvent event) {
        final Presentation presentation = checkOpenSCADPrerequisites(event);
        if (presentation.isVisible()) {
            presentation.setText("Open In OpenSCAD");
            presentation.setDescription("Open this model in OpenSCAD");
            if (ActionPlaces.EDITOR_TOOLBAR.equals(event.getPlace())) {
                presentation.setIcon(OpenSCADIcons.OPENSCAD_LOGO);
            }
        }
    }

    @Override
    protected List<String> getArguments(@NotNull final AnActionEvent event) {
        VirtualFile vf = event.getData(CommonDataKeys.VIRTUAL_FILE);
        if (vf != null)
        {
            return Collections.singletonList(vf.getPath());
        }
        else
            return Collections.emptyList();
    }

    @Override
    public @NotNull ActionUpdateThread getActionUpdateThread() {
        return ActionUpdateThread.EDT;
    }
}
