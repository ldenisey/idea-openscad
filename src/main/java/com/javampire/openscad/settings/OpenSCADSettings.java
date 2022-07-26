package com.javampire.openscad.settings;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;

@State(name = "OpenSCADSettings", storages = @Storage("OpenSCADSettings.xml"))
public class OpenSCADSettings implements PersistentStateComponent<OpenSCADSettings> {

    private String openSCADExecutable = null;
    private boolean allowPreviewEditor = true;

    public static OpenSCADSettings getInstance() {
        return ApplicationManager.getApplication().getService(OpenSCADSettings.class);
    }

    @Override
    public OpenSCADSettings getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull final OpenSCADSettings state) {
        XmlSerializerUtil.copyBean(state, this);
    }

    @Nullable
    public String getOpenSCADExecutable() {
        return openSCADExecutable;
    }

    public void setOpenSCADExecutable(@NotNull final String openSCADExecutable) {
        this.openSCADExecutable = openSCADExecutable;
    }

    public boolean isAllowPreviewEditor() {
        return allowPreviewEditor;
    }

    public void setAllowPreviewEditor(boolean allowPreviewEditor) {
        this.allowPreviewEditor = allowPreviewEditor;
    }

    public boolean hasExecutable() {
        final String executable = OpenSCADSettings.getInstance().getOpenSCADExecutable();
        return !StringUtil.isEmptyOrSpaces(executable) && new File(executable).canExecute();
    }
}