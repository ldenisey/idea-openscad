package com.javampire.openscad.action;

import com.intellij.openapi.actionSystem.ActionPlaces;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.util.IconLoader;
import com.intellij.ui.ColorPicker;
import com.javampire.openscad.editor.OpenSCADPreviewFileEditorConfiguration;
import org.cef.browser.CefBrowser;
import org.jetbrains.annotations.NotNull;

/**
 * Can be called from preview toolbar. Set model color.
 */
public class SetModelColorAction extends OpenSCADAction {

    @Override
    public void update(@NotNull final AnActionEvent event) {
        super.update(event);
        if (ActionPlaces.EDITOR_TOOLBAR.equals(event.getPlace())) {
            final Presentation presentation = event.getPresentation();
            if (presentation.isEnabled()) {
                presentation.setText("Set Model Color");
                presentation.setDescription("Set preview model color");
                presentation.setIcon(IconLoader.getIcon("/com/javampire/openscad/icons/colorPicker.svg", getClass()));
            }
        }
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        final OpenSCADPreviewFileEditorConfiguration editorConfig = event.getData(OpenSCADDataKeys.EDITOR_CONFIG);
        final CefBrowser browser = event.getData(OpenSCADDataKeys.PREVIEW_BROWSER);
        if (editorConfig != null && browser != null) {
            ColorPicker.showColorPickerPopup(
                    event.getProject(),
                    editorConfig.getModelColor(),
                    (color, source) -> editorConfig.updateModelColor(color)
            );
        }
    }
}
