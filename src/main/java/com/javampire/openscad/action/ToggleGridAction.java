package com.javampire.openscad.action;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.util.IconLoader;
import com.javampire.openscad.editor.OpenSCADPreviewFileEditor;
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
            final OpenSCADPreviewFileEditor previewEditor = event.getData(OpenSCADDataKeys.PREVIEW_EDITOR);
            if (previewEditor != null && Boolean.TRUE.equals(previewEditor.getEditorConfig().getShowAxis())) {
                presentation.setIcon(IconLoader.getIcon("/com/javampire/openscad/icons/grid.svg", getClass()));
            } else {
                presentation.setIcon(IconLoader.getIcon("/com/javampire/openscad/icons/grid_grayed.svg", getClass()));
            }
        }
    }

    @Override
    public void actionPerformed(@NotNull final AnActionEvent event) {
        final OpenSCADPreviewFileEditor previewEditor = event.getData(OpenSCADDataKeys.PREVIEW_EDITOR);
        if (previewEditor != null) {
            previewEditor.getEditorConfig().toggleShowGrid();
        }
    }
}
