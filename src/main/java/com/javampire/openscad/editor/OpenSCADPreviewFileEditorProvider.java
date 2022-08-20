package com.javampire.openscad.editor;

import com.intellij.openapi.fileEditor.AsyncFileEditorProvider;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorPolicy;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.jcef.JBCefApp;
import com.javampire.openscad.OpenSCADFileType;
import com.javampire.openscad.settings.OpenSCADSettings;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class OpenSCADPreviewFileEditorProvider implements AsyncFileEditorProvider, DumbAware {

    @Override
    public boolean accept(@NotNull final Project project, @NotNull final VirtualFile scadFile) {
        return scadFile.getFileType() == OpenSCADFileType.INSTANCE
                && OpenSCADSettings.getInstance().isAllowPreviewEditor()
                && JBCefApp.isSupported();
    }

    @Override
    public @NotNull Builder createEditorAsync(@NotNull final Project project, @NotNull final VirtualFile scadFile) {
        return new Builder() {
            @Override
            public FileEditor build() {
                return new OpenSCADPreviewFileEditor(project, scadFile);
            }
        };
    }

    @Override
    public @NotNull OpenSCADPreviewFileEditor createEditor(@NotNull final Project project, @NotNull final VirtualFile scadFile) {
        return (OpenSCADPreviewFileEditor) createEditorAsync(project, scadFile).build();
    }

    @Override
    @NonNls
    public @NotNull String getEditorTypeId() {
        return OpenSCADPreviewFileEditorProvider.class.getSimpleName();
    }

    @NotNull
    @Override
    public FileEditorPolicy getPolicy() {
        return FileEditorPolicy.HIDE_DEFAULT_EDITOR;
    }
}
