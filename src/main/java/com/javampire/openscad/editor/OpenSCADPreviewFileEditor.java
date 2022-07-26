package com.javampire.openscad.editor;

import com.intellij.icons.AllIcons;
import com.intellij.ide.IdeBundle;
import com.intellij.ide.browsers.actions.WebPreviewVirtualFile;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.ModalityState;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.impl.EditorHeaderComponent;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorLocation;
import com.intellij.openapi.fileEditor.FileEditorState;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.util.UserDataHolderBase;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.jcef.JCEFHtmlPanel;
import com.intellij.util.Alarm;
import com.intellij.util.ui.components.BorderLayoutPanel;
import com.javampire.openscad.action.ExportAction;
import com.javampire.openscad.action.OpenSCADExecutor;
import com.javampire.openscad.settings.OpenSCADSettings;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class OpenSCADPreviewFileEditor extends UserDataHolderBase implements FileEditor {

    private static final Logger LOG = Logger.getInstance(OpenSCADPreviewFileEditor.class);
    private static final String STL_GENERATION_ERROR_PREFIX = "An error occurred while generating OpenSCAD preview. Deactivating preview.\n\nPlease fix the error and reactivate it in Settings -> Languages & Frameworks -> OpenSCAD.\n\n";

    private final Project project;
    private final VirtualFile scadFile;
    private final Document scadFileDocument;
    private final WebPreviewVirtualFile htmlFile;
    private final VirtualFile stlFile;
    private final BorderLayoutPanel htmlPanelWrapper;
    private @Nullable JCEFHtmlPanel htmlPanel;
    private BorderLayoutPanel previewToolbar;
    private final Alarm mySwingAlarm = new Alarm(Alarm.ThreadToUse.SWING_THREAD, this);
    private final String siteUrl;

    public OpenSCADPreviewFileEditor(@NotNull final Project project, @NotNull final VirtualFile scadFile) {
        this.project = project;
        this.scadFile = scadFile;
        this.scadFileDocument = FileDocumentManager.getInstance().getDocument(scadFile);
        this.htmlFile = OpenSCADPreviewFileService.getInstance(project).createVirtualFile(scadFile);
        this.siteUrl = this.htmlFile.getPreviewUrl().toExternalForm();
        this.stlFile = initStlFile();
        generateStl();
        //this.htmlPanelWrapper = new JPanel(new BorderLayout());
        this.htmlPanelWrapper = new BorderLayoutPanel();
        this.htmlPanelWrapper.addComponentListener(new ComponentAdapter() {
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

    @Nullable
    public JCEFHtmlPanel getHtmlPanel() {
        return htmlPanel;
    }

    @Override
    public @NotNull JComponent getComponent() {
        return htmlPanelWrapper;
    }

    @Override
    public @Nullable JComponent getPreferredFocusedComponent() {
        return htmlPanelWrapper;
    }

    private void attachHtmlPanel() {
        if (htmlPanel == null) {
            htmlPanel = new JCEFHtmlPanel(true, null, this.siteUrl);
            previewToolbar = new BorderLayoutPanel();
            previewToolbar.add(new PreviewToolbar(htmlPanel.getComponent()), BorderLayout.WEST);
            htmlPanelWrapper.add(htmlPanel.getComponent(), BorderLayout.CENTER);
            htmlPanelWrapper.add(previewToolbar, BorderLayout.NORTH);
            if (htmlPanelWrapper.isShowing()) htmlPanelWrapper.validate();
            htmlPanelWrapper.repaint();
        }
    }

    private void detachHtmlPanel() {
        if (htmlPanel != null) {
            htmlPanelWrapper.remove(previewToolbar);
            previewToolbar = null;
            htmlPanelWrapper.remove(htmlPanel.getComponent());
            Disposer.dispose(htmlPanel);
            htmlPanel = null;
        }
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

    private @Nullable VirtualFile initStlFile() {
        AtomicReference<VirtualFile> curStlFile = new AtomicReference<>();
        ApplicationManager.getApplication().runWriteAction(() -> {
            try {
                curStlFile.set(getFile().getParent().findOrCreateChildData(this,
                        htmlFile.getOriginalFile().getNameWithoutExtension() + ".stl"));
            } catch (final IOException ioe) {
                LOG.error("An error occurred while generating STL for scad file preview.", ioe);
            }
        });
        return curStlFile.get();
    }

    public void generateStl() {
        final OpenSCADExecutor executor = OpenSCADExecutor.execute(ExportAction.generateStlArguments(scadFile, stlFile));
        final StringBuilder message = new StringBuilder();
        if (executor == null) {
            message.append(STL_GENERATION_ERROR_PREFIX).append(OpenSCADExecutor.ERROR_NO_EXE);
        } else if (executor.getException() != null) {
            message.append(STL_GENERATION_ERROR_PREFIX).append(String.format(OpenSCADExecutor.ERROR_EXCEPTION, executor.getCommand())).append(ExceptionUtils.getFullStackTrace(executor.getException()));
        } else if (executor.getReturnCode() != 0) {
            final Notification notification = new Notification(
                    OpenSCADPreviewFileEditor.class.getSimpleName(),
                    "Can not update preview because of : " + executor.getStderr(),
                    NotificationType.INFORMATION
            );
            notification.notify(project);
        }

        if (message.length() != 0) {
            ApplicationManager.getApplication().invokeLater(() ->
                    Messages.showErrorDialog(message.toString(), "OpenSCAD Execution Error")
            );
            OpenSCADSettings.getInstance().setAllowPreviewEditor(false);
        }
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

    private class PreviewToolbar extends EditorHeaderComponent {
        public final ActionToolbar toolbar;

        public PreviewToolbar(@NotNull final JComponent targetComponent) {
            super();
            setLayout(new GridBagLayout());
            toolbar = ActionManager.getInstance().createActionToolbar(
                    ActionPlaces.EDITOR_TOOLBAR,
                    new DefaultActionGroup(new RefreshPreviewAction()),
                    true);
            toolbar.setTargetComponent(targetComponent);
            add(toolbar.getComponent());
            toolbar.setReservePlaceAutoPopupIcon(false);
        }
    }

    private class RefreshPreviewAction extends AnAction {
        public RefreshPreviewAction() {
            super("Refresh Preview", "Generate a new STL file to update preview", AllIcons.Actions.Refresh);
        }

        @Override
        public void actionPerformed(@NotNull AnActionEvent e) {
            if (htmlPanel != null) {
                FileDocumentManager.getInstance().saveDocument(scadFileDocument);
                generateStl();
                htmlPanel.getCefBrowser().reload();
            }
        }
    }
}
