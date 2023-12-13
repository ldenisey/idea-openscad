package com.javampire.openscad.action;

import com.intellij.openapi.actionSystem.ActionPlaces;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.vfs.VirtualFile;
import com.javampire.openscad.editor.OpenSCADPreviewFileEditor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Used for generating {@link OpenSCADPreviewFileEditor} preview.
 */
public class GeneratePreviewAction extends ExportAction {

    @Override
    public void update(@NotNull final AnActionEvent event) {
        super.update(event);
        if (ActionPlaces.EDITOR_TOOLBAR.equals(event.getPlace())) {
            final Presentation presentation = event.getPresentation();
            if (presentation.isEnabled()) {
                presentation.setText("Generate Preview");
                presentation.setDescription("Generate a new STL preview file");
            }
        }
    }

    @Override
    @Nullable
    protected String getDestinationFilePath(@NotNull final AnActionEvent event) {
        final VirtualFile destinationFile = event.getData(OpenSCADDataKeys.DESTINATION_VIRTUAL_FILE);
        return destinationFile == null ? null : destinationFile.getPath();
    }

    @Override
    protected void postExecution(@NotNull final AnActionEvent event) {
        final VirtualFile destinationFile = event.getData(OpenSCADDataKeys.DESTINATION_VIRTUAL_FILE);
        if (destinationFile != null) {
            destinationFile.refresh(true, false);
        }
    }
}
