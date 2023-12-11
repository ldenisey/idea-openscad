package com.javampire.openscad.action;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.ActionPlaces;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.AnimatedIcon;
import com.javampire.openscad.editor.OpenSCADPreviewFileEditorConfiguration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Used for generating and refreshing {@link com.javampire.openscad.editor.OpenSCADPreviewFileEditor} preview.
 */
public class RefreshPreviewAction extends ExportAction {

    @Override
    public void update(@NotNull final AnActionEvent event) {
        super.update(event);
        if (ActionPlaces.EDITOR_TOOLBAR.equals(event.getPlace())) {
            final Presentation presentation = event.getPresentation();
            if (presentation.isEnabled()) {
                presentation.setIcon(performing ? new AnimatedIcon.Default() : AllIcons.Actions.Refresh);
                presentation.setText("Refresh Preview");
                presentation.setDescription("Generate a new STL file to update preview");
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
    protected Boolean preExecution(@NotNull final AnActionEvent event) {
        final OpenSCADPreviewFileEditorConfiguration editorConfig = event.getData(OpenSCADDataKeys.EDITOR_CONFIG);
        if (editorConfig != null) {
            editorConfig.saveConfiguration();
            return true;
        }
        return false;
    }

    @Override
    protected void postExecution(@NotNull final AnActionEvent event) {
        final VirtualFile destinationFile = event.getData(OpenSCADDataKeys.DESTINATION_VIRTUAL_FILE);
        if (destinationFile != null) {
            destinationFile.refresh(true, false, () -> {
                final OpenSCADPreviewFileEditorConfiguration editorConfig = event.getData(OpenSCADDataKeys.EDITOR_CONFIG);
                if (editorConfig != null) {
                    editorConfig.loadConfiguration();
                }
            });
        }
    }
}
