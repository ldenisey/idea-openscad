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
import com.javampire.openscad.settings.OpenSCADInfo;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

/**
 * Can be called from context menu or preview toolbar. Calls OpenSCAD to export the model in various format.
 */
public class ExportAction extends OpenSCADExecutableAction {

    private static String[] extensions;

    @Override
    public void update(@NotNull final AnActionEvent event) {
        final Presentation presentation = checkOpenSCADPrerequisites(event);
        if (presentation.isVisible()) {
            presentation.setText("Export As ...");
            presentation.setDescription("Export selected file as ...");
            if (ActionPlaces.EDITOR_TOOLBAR.equals(event.getPlace())) {
                presentation.setIcon(AllIcons.Actions.Download);
            }
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
        final FileSaverDescriptor fileSaverDescriptor = new FileSaverDescriptor("Save File", "Choose destination file.", getAvailableExtensions());
        final FileSaverDialog dialog = FileChooserFactory.getInstance().createSaveFileDialog(fileSaverDescriptor, event.getProject());
        final VirtualFileWrapper vfw = dialog.save(sourceFile.getParent(), sourceFile.getNameWithoutExtension());
        if (vfw == null) {
            // When user click cancel button or close the window
            return null;
        }
        return vfw.getFile().getAbsolutePath();
    }

    protected String[] getAvailableExtensions() {
        if (extensions == null) {
            final Integer majorVersion = OpenSCADInfo.getOpenSCADMajorVersion();
            if (majorVersion != null) {
                switch (majorVersion) {
                    case 2021:
                        extensions = new String[]{"stl", "off", "dxf", "csg", "svg", "amf", "3mf", "png", "pdf", "echo", "ast", "term", "nef3", "nefdbg"};
                        break;
                    case 2019:
                        extensions = new String[]{"stl", "off", "dxf", "csg", "svg", "amf", "3mf", "png", "echo", "ast", "term", "nef3", "nefdbg"};
                        break;
                    case 2015:
                        extensions = new String[]{"stl", "off", "dxf", "csg", "svg", "amf"};
                        break;
                    default:
                        // Older or unknown version
                        extensions = new String[]{"stl", "off", "dxf", "csg"};
                        break;
                }
            }
        }
        return extensions;
    }
}
