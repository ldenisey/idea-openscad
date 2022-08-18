package com.javampire.openscad.editor;

import com.intellij.openapi.fileEditor.*;
import com.intellij.openapi.fileEditor.impl.text.PsiAwareTextEditorProvider;
import com.intellij.openapi.fileEditor.impl.text.TextEditorProvider;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.FileIndexFacade;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class OpenSCADTextEditorWithPreviewProvider implements AsyncFileEditorProvider, DumbAware {

    @NotNull
    private final TextEditorProvider textEditorProvider;
    @NotNull
    private final OpenSCADPreviewFileEditorProvider previewEditorProvider;

    public OpenSCADTextEditorWithPreviewProvider() {
        textEditorProvider = new PsiAwareTextEditorProvider();
        previewEditorProvider = new OpenSCADPreviewFileEditorProvider();
    }

    @Override
    public boolean accept(@NotNull final Project project, @NotNull final VirtualFile file) {
        return textEditorProvider.accept(project, file)
                && previewEditorProvider.accept(project, file)
                // Excluding files that are not part of a module : external libraries, skeletons, external files, ...
                && FileIndexFacade.getInstance(project).getModuleForFile(file) != null;
    }

    @NotNull
    @Override
    public FileEditor createEditor(@NotNull final Project project, @NotNull final VirtualFile file) {
        return createEditorAsync(project, file).build();
    }

    @Override
    public @NotNull Builder createEditorAsync(@NotNull final Project project, @NotNull final VirtualFile file) {
        return new Builder() {
            @Override
            public FileEditor build() {
                final TextEditor textEditor = (TextEditor) textEditorProvider.createEditor(project, file);
                final FileEditor rendererEditor = previewEditorProvider.createEditor(project, file);
                return new TextEditorWithPreview(
                        textEditor,
                        rendererEditor,
                        "OpenSCADTextEditorWithPreview",
                        TextEditorWithPreview.Layout.SHOW_EDITOR_AND_PREVIEW,
                        false
                );
            }
        };
    }

    @Override
    @NonNls
    public @NotNull String getEditorTypeId() {
        return OpenSCADTextEditorWithPreviewProvider.class.getSimpleName();
    }

    @Override
    public @NotNull FileEditorPolicy getPolicy() {
        return FileEditorPolicy.HIDE_DEFAULT_EDITOR;
    }
}
