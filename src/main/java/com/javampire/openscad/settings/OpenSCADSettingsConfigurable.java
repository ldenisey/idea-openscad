package com.javampire.openscad.settings;

import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.io.File;

public class OpenSCADSettingsConfigurable implements SearchableConfigurable.Parent, Configurable.NoScroll {

    private final Project myProject;
    private JPanel settingsPanel;
    private TextFieldWithBrowseButton openSCADExecutablePath;
    private JCheckBox allowPreviewEditor;
    private JLabel allowPreviewEditorText;

    OpenSCADSettingsConfigurable(final Project project) {
        myProject = project;

        openSCADExecutablePath.getTextField().addActionListener(e -> {
            if (openSCADExecutablePath.getText().isEmpty()) {
                allowPreviewEditor.setEnabled(false);
                allowPreviewEditor.setSelected(false);
                allowPreviewEditorText.setEnabled(false);
            } else {
                allowPreviewEditor.setEnabled(true);
                allowPreviewEditorText.setEnabled(true);
            }
        });
    }

    @NotNull
    @Override
    public String getId() {
        return getClass().getName();
    }

    @Nullable
    @Override
    public Runnable enableSearch(final String option) {
        return null;
    }

    @Nls
    @Override
    public String getDisplayName() {
        return "OpenSCAD";
    }

    @Nullable
    @Override
    public String getHelpTopic() {
        return null;
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        return settingsPanel;
    }

    @Override
    public boolean isModified() {
        final OpenSCADSettings openSCADSettings = OpenSCADSettings.getInstance();
        return allowPreviewEditor.isSelected() != openSCADSettings.isAllowPreviewEditor()
                || !openSCADExecutablePath.getText().equals(openSCADSettings.getOpenSCADExecutable());
    }

    @Override
    public void apply() {
        final OpenSCADSettings openSCADSettings = OpenSCADSettings.getInstance();
        openSCADSettings.setOpenSCADExecutable(openSCADExecutablePath.getText());
        openSCADSettings.setAllowPreviewEditor(allowPreviewEditor.isSelected());
        OpenSCADSettingsStartupActivity.updateOpenSCADLibraries(myProject);
    }

    @Override
    public void reset() {
        final OpenSCADSettings openSCADSettings = OpenSCADSettings.getInstance();
        final String openSCADExecutable = openSCADSettings.getOpenSCADExecutable();
        openSCADExecutablePath.setText(openSCADExecutable != null ? openSCADExecutable : "");
        allowPreviewEditor.setSelected(openSCADSettings.isAllowPreviewEditor());
    }

    @Override
    public void disposeUIResources() {
    }

    private void createUIComponents() {
        openSCADExecutablePath = new TextFieldWithBrowseButton();

        final FileChooserDescriptor executableDescriptor = FileChooserDescriptorFactory.createSingleLocalFileDescriptor().withFileFilter(
                virtualFile -> virtualFile.isInLocalFileSystem() && new File(virtualFile.getPath()).canExecute()
        );
        openSCADExecutablePath.addBrowseFolderListener(
                "Choose OpenSCAD Executable",
                "Choose OpenSCAD executable",
                myProject,
                executableDescriptor
        );
    }

    @NotNull
    @Override
    public Configurable[] getConfigurables() {
        return new Configurable[0];
    }

    @Override
    public boolean hasOwnContent() {
        return true;
    }
}
