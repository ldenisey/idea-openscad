package com.javampire.openscad.action;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.util.IconLoader;
import com.javampire.openscad.editor.OpenSCADPreviewFileEditor;
import org.jetbrains.annotations.NotNull;

/**
 * Can be called from preview toolbar. Toggles on and off the preview axis.
 */
public class ToggleAxisAction extends OpenSCADAction {

    @Override
    public void update(@NotNull final AnActionEvent event) {
        final Presentation presentation = checkOpenSCADPrerequisites(event);
        if (presentation.isVisible()) {
            presentation.setText("Toggle Axis");
            presentation.setDescription("Show or hide the preview axis");
            final OpenSCADPreviewFileEditor previewEditor = event.getData(OpenSCADDataKeys.PREVIEW_EDITOR);
            if (previewEditor != null && Boolean.TRUE.equals(previewEditor.getEditorConfig().getShowAxis())) {
                presentation.setIcon(IconLoader.getIcon("/com/javampire/openscad/icons/axis.svg", getClass()));
            } else {
                presentation.setIcon(IconLoader.getIcon("/com/javampire/openscad/icons/axis_grayed.svg", getClass()));
            }
        }
    }

    @Override
    public void actionPerformed(@NotNull final AnActionEvent event) {
        final OpenSCADPreviewFileEditor previewEditor = event.getData(OpenSCADDataKeys.PREVIEW_EDITOR);
        if (previewEditor != null) {
            previewEditor.getEditorConfig().toggleShowAxis();
        }
    }
}
