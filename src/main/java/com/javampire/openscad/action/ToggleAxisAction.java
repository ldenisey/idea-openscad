package com.javampire.openscad.action;

import com.intellij.openapi.actionSystem.ActionPlaces;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.util.IconLoader;
import com.javampire.openscad.editor.OpenSCADPreviewFileEditorConfiguration;
import org.jetbrains.annotations.NotNull;

/**
 * Can be called from preview toolbar. Toggles on and off the preview axis.
 */
public class ToggleAxisAction extends OpenSCADAction {

    @Override
    public void update(@NotNull final AnActionEvent event) {
        super.update(event);
        if (ActionPlaces.EDITOR_TOOLBAR.equals(event.getPlace())) {
            final Presentation presentation = event.getPresentation();
            if (presentation.isEnabled()) {
                presentation.setText("Toggle Axis");
                presentation.setDescription("Show or hide the preview axis");
                final OpenSCADPreviewFileEditorConfiguration editorConfig = event.getData(OpenSCADDataKeys.EDITOR_CONFIG);
                if (editorConfig != null && Boolean.TRUE.equals(editorConfig.getShowAxis())) {
                    presentation.setIcon(IconLoader.getIcon("/com/javampire/openscad/icons/axis.svg", getClass()));
                } else {
                    presentation.setIcon(IconLoader.getIcon("/com/javampire/openscad/icons/axis_grayed.svg", getClass()));
                }
            }
        }
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        final OpenSCADPreviewFileEditorConfiguration editorConfig = event.getData(OpenSCADDataKeys.EDITOR_CONFIG);
        if (editorConfig != null) {
            editorConfig.toggleShowAxis();
        }
    }
}
