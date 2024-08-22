package com.javampire.openscad.action;

import com.intellij.openapi.actionSystem.ActionUpdateThread;
import com.intellij.openapi.actionSystem.ActionPlaces;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.actionSystem.ex.ActionUtil;
import com.intellij.openapi.actionSystem.impl.SimpleDataContext;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileEvent;
import com.intellij.openapi.vfs.VirtualFileListener;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.javampire.openscad.OpenSCADIcons;
import com.javampire.openscad.editor.OpenSCADPreviewFileEditor;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Can be called from preview toolbar. Toggles on and off the preview axis.
 */
public class ToggleAutoRefreshAction extends OpenSCADAction {

    private VirtualFileListener vfSaveListener = null;

    @Override
    public void update(@NotNull final AnActionEvent event) {
        final Presentation presentation = checkOpenSCADPrerequisites(event);
        if (presentation.isVisible()) {
            presentation.setText("Toggle Auto Refresh On Save");
            presentation.setDescription("Toggle auto refresh on save");
            final OpenSCADPreviewFileEditor previewEditor = event.getData(OpenSCADDataKeys.PREVIEW_EDITOR);
            if (previewEditor != null && Boolean.TRUE.equals(previewEditor.getEditorConfig().getAutoRefresh())) {
                presentation.setIcon(OpenSCADIcons.AUTO_REFRESH);
            } else {
                presentation.setIcon(OpenSCADIcons.AUTO_REFRESH_GRAYED);
            }
        }
    }

    @Override
    public void actionPerformed(@NotNull final AnActionEvent event) {
        final OpenSCADPreviewFileEditor previewEditor = event.getData(OpenSCADDataKeys.PREVIEW_EDITOR);
        if (previewEditor != null) {
            previewEditor.getEditorConfig().toggleAutoRefresh();
            if (previewEditor.getEditorConfig().getAutoRefresh()) {
                createListener(Objects.requireNonNull(event.getProject()), Objects.requireNonNull(event.getData(CommonDataKeys.VIRTUAL_FILE)));
            } else {
                deleteListener();
            }
        }
    }

    @Override
    public @NotNull ActionUpdateThread getActionUpdateThread() {
        return ActionUpdateThread.EDT;
    }

    private void createListener(final @NotNull Project project, final @NotNull VirtualFile scadFile) {
        vfSaveListener = new AutoRefreshListener(project, scadFile);
        VirtualFileManager.getInstance().addVirtualFileListener(vfSaveListener);
    }

    private void deleteListener() {
        VirtualFileManager.getInstance().removeVirtualFileListener(vfSaveListener);
        vfSaveListener = null;
    }

    private class AutoRefreshListener implements VirtualFileListener {
        private final Project project;
        private final VirtualFile scadFile;

        public AutoRefreshListener(final @NotNull Project project, final @NotNull VirtualFile scadFile) {
            this.project = project;
            this.scadFile = scadFile;
        }

        @Override
        public void contentsChanged(final @NotNull VirtualFileEvent event) {
            if (event.isFromSave() && scadFile.equals(event.getFile())) {
                ActionUtil.performActionDumbAwareWithCallbacks(
                        new RefreshPreviewAction(),
                        AnActionEvent.createFromDataContext(
                                ActionPlaces.UNKNOWN,
                                new Presentation(RefreshPreviewAction.TEXT),
                                SimpleDataContext.builder()
                                        .add(CommonDataKeys.PROJECT, project)
                                        .add(CommonDataKeys.VIRTUAL_FILE, scadFile)
                                        .build()
                        )
                );
            }
        }
    }
}
