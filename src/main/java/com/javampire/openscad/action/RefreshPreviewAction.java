package com.javampire.openscad.action;

import com.intellij.openapi.actionSystem.ActionPlaces;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.util.IconLoader;
import com.intellij.ui.AnimatedIcon;
import com.javampire.openscad.editor.OpenSCADPreviewFileEditor;
import org.jetbrains.annotations.NotNull;

/**
 * Used for refreshing {@link com.javampire.openscad.editor.OpenSCADPreviewFileEditor} preview.
 */
public class RefreshPreviewAction extends GeneratePreviewAction {

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
                final OpenSCADPreviewFileEditor openSCADPreviewFileEditor = getOpenSCADPreviewFileEditor(event.getProject(), event.getData(CommonDataKeys.VIRTUAL_FILE));
                presentation.setVisible(openSCADPreviewFileEditor != null && openSCADPreviewFileEditor.isPreviewShown());
            }
        }
    }

    @Override
    protected Boolean preExecution(@NotNull final AnActionEvent event) {
        if (previewFileEditor != null) {
            previewFileEditor.getEditorConfig().saveConfiguration();
            return true;
        }
        return false;
    }

    @Override
    protected void postExecution(@NotNull final AnActionEvent event) {
        if (previewFileEditor != null) {
            previewFileEditor.getEditorConfig().loadConfiguration();
        }
    }
}
