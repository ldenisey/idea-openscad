package com.javampire.openscad.action;

import com.intellij.openapi.actionSystem.ActionUpdateThread;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.ColorChooserService;
import com.javampire.openscad.OpenSCADIcons;
import com.javampire.openscad.editor.OpenSCADPreviewFileEditor;
import com.javampire.openscad.editor.OpenSCADPreviewFileEditorConfiguration;
import org.jetbrains.annotations.NotNull;


/**
 * Can be called from preview toolbar. Set model color.
 */
public class SetModelColorAction extends OpenSCADAction {

    @Override
    public void update(@NotNull final AnActionEvent event) {
        final Presentation presentation = checkOpenSCADPrerequisites(event);
        if (presentation.isVisible()) {
            presentation.setText("Set Model Color");
            presentation.setDescription("Set preview model color");
            presentation.setIcon(OpenSCADIcons.COLOR_PICKER);
        }
    }

    @Override
    public @NotNull ActionUpdateThread getActionUpdateThread() {
        return ActionUpdateThread.EDT;
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        OpenSCADPreviewFileEditor previewFileEditor = event.getData(OpenSCADDataKeys.PREVIEW_EDITOR);

        if (previewFileEditor == null) {
            final Project project = event.getProject();
            final VirtualFile scadFile = event.getData(CommonDataKeys.VIRTUAL_FILE);
            if (project != null && scadFile != null) {
                previewFileEditor = getOpenSCADPreviewFileEditor(project, scadFile);
            }
        }

        if (previewFileEditor != null) {
            final OpenSCADPreviewFileEditorConfiguration editorConfig = previewFileEditor.getEditorConfig();
            ColorChooserService.getInstance().showPopup(event.getProject(), editorConfig.getModelColor(),
                    (color, source) -> editorConfig.updateModelColor(color));
        }
    }
}
