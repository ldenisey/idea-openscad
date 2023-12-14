package com.javampire.openscad.action;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.util.IconLoader;
import com.javampire.openscad.editor.OpenSCADPreviewFileEditorConfiguration;
import org.jetbrains.annotations.NotNull;

/**
 * Can be called from preview toolbar. Toggles on and off the preview grid.
 */
public class ToggleGridAction extends OpenSCADAction {

    @Override
    public void update(@NotNull final AnActionEvent event) {
        final Presentation presentation = checkOpenSCADPrerequisites(event);
        if (presentation.isVisible()) {
            presentation.setText("Toggle Grid");
            presentation.setDescription("Show or hide the preview grid");
            final OpenSCADPreviewFileEditorConfiguration editorConfig = event.getData(OpenSCADDataKeys.EDITOR_CONFIG);
            if (editorConfig != null && Boolean.TRUE.equals(editorConfig.getShowGrid())) {
                presentation.setIcon(IconLoader.getIcon("/com/javampire/openscad/icons/grid.svg", getClass()));
            } else {
                presentation.setIcon(IconLoader.getIcon("/com/javampire/openscad/icons/grid_grayed.svg", getClass()));
            }
        }
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        final OpenSCADPreviewFileEditorConfiguration editorConfig = event.getData(OpenSCADDataKeys.EDITOR_CONFIG);
        if (editorConfig != null) {
            editorConfig.toggleShowGrid();
        }
    }
}
