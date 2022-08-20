package com.javampire.openscad.editor;

import com.intellij.ide.IdeBundle;
import com.intellij.ide.browsers.actions.WebPreviewVirtualFile;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.actionSystem.ex.ActionUtil;
import com.intellij.openapi.actionSystem.impl.SimpleDataContext;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.ModalityState;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorLocation;
import com.intellij.openapi.fileEditor.FileEditorState;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.util.UserDataHolderBase;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.jcef.JCEFHtmlPanel;
import com.intellij.util.Alarm;
import com.intellij.util.ui.components.BorderLayoutPanel;
import com.javampire.openscad.action.ExportAction;
import com.javampire.openscad.action.OpenAction;
import com.javampire.openscad.action.OpenSCADDataKeys;
import com.javampire.openscad.action.RefreshPreviewAction;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

public class OpenSCADPreviewFileEditor extends UserDataHolderBase implements FileEditor {
    private static final Logger LOG = Logger.getInstance(OpenSCADPreviewFileEditor.class);

    private final VirtualFile scadFile;
    private final VirtualFile stlFile;
    private final WebPreviewVirtualFile htmlFile;
    private final MainPanel mainPanel;
    private @Nullable JCEFHtmlPanel htmlPanel;
    private ActionToolbar previewToolbar;
    private final Alarm mySwingAlarm = new Alarm(Alarm.ThreadToUse.SWING_THREAD, this);

    public OpenSCADPreviewFileEditor(@NotNull final Project project, @NotNull final VirtualFile scadFile) {
        this.scadFile = scadFile;
        this.htmlFile = OpenSCADPreviewSiteService.getInstance(project).createVirtualFile(scadFile);
        this.stlFile = OpenSCADPreviewSiteService.getInstance(project).getStlFile(scadFile);
        this.mainPanel = new MainPanel();
        this.mainPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                mySwingAlarm.addRequest(
                        () -> attachHtmlPanel(),
                        0,
                        ModalityState.stateForComponent(getComponent())
                );
            }

            @Override
            public void componentHidden(ComponentEvent e) {
                mySwingAlarm.addRequest(
                        () -> detachHtmlPanel(),
                        0,
                        ModalityState.stateForComponent(getComponent())
                );
            }
        });
        attachHtmlPanel();
    }

    public VirtualFile getFile() {
        return htmlFile.getOriginalFile();
    }

    @Override
    public @NotNull JComponent getComponent() {
        return mainPanel;
    }

    @Override
    public @Nullable JComponent getPreferredFocusedComponent() {
        return mainPanel;
    }

    private void attachHtmlPanel() {
        if (htmlPanel == null) {
            htmlPanel = new JCEFHtmlPanel(true, null, htmlFile.getPreviewUrl().toExternalForm());
            previewToolbar = createToolbar(htmlPanel.getComponent());
            mainPanel.add(previewToolbar.getComponent(), BorderLayout.NORTH);
            mainPanel.add(htmlPanel.getComponent(), BorderLayout.CENTER);
            if (mainPanel.isShowing()) mainPanel.validate();
            mainPanel.repaint();
            htmlPanel.getCefBrowser().reload();
            forcePreviewRefresh();
        }
    }

    private void detachHtmlPanel() {
        if (htmlPanel != null) {
            mainPanel.remove(previewToolbar.getComponent());
            previewToolbar = null;
            mainPanel.remove(htmlPanel.getComponent());
            Disposer.dispose(htmlPanel);
            htmlPanel = null;
        }
    }

    private ActionToolbar createToolbar(final JComponent targetComponent) {
        ActionToolbar actionToolbar = ActionManager.getInstance().createActionToolbar(
                ActionPlaces.EDITOR_TOOLBAR,
                new DefaultActionGroup(new RefreshPreviewAction(), new Separator(), new OpenAction(), new ExportAction()),
                true);
        actionToolbar.setTargetComponent(targetComponent);
        return actionToolbar;
    }

    private void forcePreviewRefresh() {
        final AnAction refreshAction = new RefreshPreviewAction();
        final AnActionEvent event = AnActionEvent.createFromDataContext(
                ActionPlaces.EDITOR_TOOLBAR,
                new Presentation("Refresh Preview"),
                SimpleDataContext.builder()
                        .add(CommonDataKeys.VIRTUAL_FILE, scadFile)
                        .add(OpenSCADDataKeys.DESTINATION_VIRTUAL_FILE, stlFile)
                        .build()
        );
        ActionUtil.performActionDumbAwareWithCallbacks(refreshAction, event);
    }

    @Override
    public @Nls(capitalization = Nls.Capitalization.Title)
    @NotNull String getName() {
        return IdeBundle.message("web.preview.file.editor.name", scadFile.getName());
    }

    @Override
    public void setState(@NotNull FileEditorState state) {
    }

    @Override
    public boolean isModified() {
        return false;
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public void addPropertyChangeListener(@NotNull PropertyChangeListener listener) {
    }

    @Override
    public void removePropertyChangeListener(@NotNull PropertyChangeListener listener) {
    }

    @Override
    public @Nullable FileEditorLocation getCurrentLocation() {
        return null;
    }

    @Override
    public void dispose() {
        detachHtmlPanel();
        ApplicationManager.getApplication().runWriteAction(() -> {
            try {
                stlFile.delete(this);
                getFile().delete(this);
            } catch (final IOException ioe) {
                LOG.warn("An error occurred while deleting temporary scad preview files.", ioe);
            }
        });
    }

    private class MainPanel extends BorderLayoutPanel implements DataProvider {
        @Override
        public @Nullable Object getData(@NotNull @NonNls final String dataId) {
            if (OpenSCADDataKeys.DESTINATION_VIRTUAL_FILE.is(dataId)) {
                return stlFile;
            }
            return null;
        }
    }
}
