package com.javampire.openscad.action;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.javampire.openscad.editor.OpenSCADPreviewFileEditor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Used for generating {@link OpenSCADPreviewFileEditor} preview.
 */
public class GeneratePreviewAction extends ExportAction {

    public final static String TEXT = "Generate Preview";

    protected OpenSCADPreviewFileEditor previewFileEditor;

    @Override
    public void update(@NotNull final AnActionEvent event) {
        final Presentation presentation = checkOpenSCADPrerequisites(event);
        if (presentation.isVisible()) {
            presentation.setText(TEXT);
            presentation.setDescription("Generate a new STL preview file");
        }
    }

    @Override
    protected @Nullable VirtualFile getScadFile(@NotNull AnActionEvent event) {
        previewFileEditor = event.getData(OpenSCADDataKeys.PREVIEW_EDITOR);

        if (previewFileEditor == null) {
            final Project project = event.getProject();
            final VirtualFile scadFile = event.getData(CommonDataKeys.VIRTUAL_FILE);
            if (project != null && scadFile != null) {
                previewFileEditor = getOpenSCADPreviewFileEditor(project, scadFile);
            }
        }

        return previewFileEditor != null ? previewFileEditor.getPreviewSite().scadFile : null;
    }

    @Override
    @Nullable
    protected String getPreviewFilePath(@NotNull final AnActionEvent event) {
        if (previewFileEditor != null && previewFileEditor.getPreviewSite() != null)
            return previewFileEditor.getPreviewSite().previewFile.getPath();
        else
            return null;
    }

    @Override
    protected void postExecution(@NotNull final AnActionEvent event) {
        previewFileEditor.getPreviewSite().previewFile.refresh(true, false);
    }
}
