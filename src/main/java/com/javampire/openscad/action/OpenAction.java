package com.javampire.openscad.action;

import com.intellij.openapi.actionSystem.ActionPlaces;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.util.IconLoader;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

/**
 * Can be called from context menu or preview toolbar. Calls OpenSCAD with the selected file opened.
 */
public class OpenAction extends OpenSCADExecutableAction {

    @Override
    public void update(@NotNull final AnActionEvent event) {
        super.update(event);
        final Presentation presentation = event.getPresentation();
        presentation.setText("Open In OpenSCAD");
        presentation.setDescription("Open this model in OpenSCAD");
        if (ActionPlaces.EDITOR_TOOLBAR.equals(event.getPlace())) {
            presentation.setIcon(IconLoader.getIcon("/com/javampire/openscad/icons/openscad.png", getClass()));
        }
    }

    @Override
    protected List<String> getArguments(@NotNull final AnActionEvent event) {
        return Collections.singletonList(event.getData(CommonDataKeys.VIRTUAL_FILE).getPath());
    }
}
