package com.javampire.openscad.action;

import com.intellij.openapi.actionSystem.ActionPlaces;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.TextEditorWithPreview;
import com.intellij.openapi.util.IconLoader;
import com.intellij.ui.AnimatedIcon;
import com.javampire.openscad.editor.OpenSCADPreviewFileEditor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Used for refreshing {@link com.javampire.openscad.editor.OpenSCADPreviewFileEditor} preview.
 */
public class RefreshPreviewAction extends ExportAction {

    public final static String TEXT = "Refresh Preview";

    @Override
    public void update(@NotNull final AnActionEvent event) {
        final Presentation presentation = checkOpenSCADPrerequisites(event);
        if (presentation.isVisible()) {
            presentation.setText(TEXT);
            presentation.setDescription("Generate a new STL file to update preview");
            if (ActionPlaces.EDITOR_TOOLBAR.equals(event.getPlace())) {
                presentation.setIcon(performing ? new AnimatedIcon.Default() : IconLoader.getIcon("/com/javampire/openscad/icons/refresh.svg", getClass()));
            } else if (ActionPlaces.EDITOR_TAB_POPUP.equals(event.getPlace()) || ActionPlaces.EDITOR_POPUP.equals(event.getPlace())) {
                final OpenSCADPreviewFileEditor openSCADPreviewFileEditor = getOpenSCADPreviewFileEditor(event);
                presentation.setVisible(openSCADPreviewFileEditor != null && openSCADPreviewFileEditor.isPreviewShown());
            }
        }
    }

    @Override
    @Nullable
    protected String getDestinationFilePath(@NotNull final AnActionEvent event) {
        final OpenSCADPreviewFileEditor openSCADPreviewFileEditor = getOpenSCADPreviewFileEditor(event);
        if (openSCADPreviewFileEditor != null) {
            return openSCADPreviewFileEditor.getPreviewSite().previewFile.getPath();
        }
        return null;
    }

    @Override
    protected Boolean preExecution(@NotNull final AnActionEvent event) {
        final OpenSCADPreviewFileEditor openSCADPreviewFileEditor = getOpenSCADPreviewFileEditor(event);
        if (openSCADPreviewFileEditor != null) {
            openSCADPreviewFileEditor.getEditorConfig().saveConfiguration();
            return true;
        }
        return false;
    }

    @Override
    protected void postExecution(@NotNull final AnActionEvent event) {
        final OpenSCADPreviewFileEditor openSCADPreviewFileEditor = getOpenSCADPreviewFileEditor(event);
        if (openSCADPreviewFileEditor != null) {
            openSCADPreviewFileEditor.getEditorConfig().loadConfiguration();
        }
    }

    private OpenSCADPreviewFileEditor getOpenSCADPreviewFileEditor(@NotNull final AnActionEvent event) {
        if (event.getProject() != null) {
            final FileEditor selectedEditor = FileEditorManager.getInstance(event.getProject()).getSelectedEditor();
            if (selectedEditor instanceof TextEditorWithPreview) {
                final FileEditor previewEditor = ((TextEditorWithPreview) selectedEditor).getPreviewEditor();
                if (previewEditor instanceof OpenSCADPreviewFileEditor) {
                    return ((OpenSCADPreviewFileEditor) previewEditor);
                }
            }
        }
        return null;
    }
}
