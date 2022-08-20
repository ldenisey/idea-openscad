package com.javampire.openscad.action;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.ActionPlaces;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.fileChooser.FileChooserFactory;
import com.intellij.openapi.fileChooser.FileSaverDescriptor;
import com.intellij.openapi.fileChooser.FileSaverDialog;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class ExportAction extends OpenSCADExecutableAction {

    private final String[] extensions = {"stl", "png", "svg", "off", "amf", "3mf", "dxf", "csg", "pdf"};

    @Override
    public void update(@NotNull final AnActionEvent event) {
        super.update(event);
        final Presentation presentation = event.getPresentation();
        if (presentation.isEnabled() && ActionPlaces.EDITOR_TOOLBAR.equals(event.getPlace())) {
            presentation.setIcon(AllIcons.Actions.Download);
            presentation.setText("Export as ...");
            presentation.setDescription("Export selected file as ...");
        }
    }

    @Override
    protected List<String> getArguments(@NotNull final AnActionEvent event) {
        final VirtualFile sourceFile = event.getData(CommonDataKeys.VIRTUAL_FILE);
        if (sourceFile == null) {
            return null;
        }

        final String destinationFilePath = getDestinationFilePath(event);
        if (destinationFilePath == null) {
            return null;
        }

        return Arrays.asList("-o", destinationFilePath, sourceFile.getCanonicalPath());
    }

    @Nullable
    protected String getDestinationFilePath(@NotNull final AnActionEvent event) {
        final VirtualFile sourceFile = event.getData(CommonDataKeys.VIRTUAL_FILE);
        final FileSaverDescriptor fileSaverDescriptor = new FileSaverDescriptor("Save File", "Choose destination file.", this.extensions);
        final FileSaverDialog dialog = FileChooserFactory.getInstance().createSaveFileDialog(fileSaverDescriptor, event.getProject());
        final VirtualFileWrapper vfw = dialog.save(sourceFile.getParent(), sourceFile.getNameWithoutExtension());
        if (vfw == null) {
            // When user click cancel button or close the window
            return null;
        }
        return vfw.getFile().getAbsolutePath();
    }
}
