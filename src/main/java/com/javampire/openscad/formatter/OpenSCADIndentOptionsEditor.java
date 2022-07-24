package com.javampire.openscad.formatter;

import com.intellij.application.options.SmartIndentOptionsEditor;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class OpenSCADIndentOptionsEditor extends SmartIndentOptionsEditor {

    private JCheckBox myIndentCascadingTransformations;

    @Override
    protected void addComponents() {
        super.addComponents();

        myIndentCascadingTransformations = new JCheckBox("Indent cascading transformations");
        add(myIndentCascadingTransformations, false);
    }

    @Override
    public boolean isModified(CodeStyleSettings settings, CommonCodeStyleSettings.IndentOptions options) {
        boolean isModified = super.isModified(settings, options);
        final OpenSCADCodeStyleSettings openSCADCodeStyleSettings = settings.getCustomSettings(OpenSCADCodeStyleSettings.class);
        isModified |= isFieldModified(myIndentCascadingTransformations, openSCADCodeStyleSettings.INDENT_CASCADING_TRANSFORMATIONS);
        return isModified;
    }

    @Override
    public void apply(CodeStyleSettings settings, CommonCodeStyleSettings.IndentOptions options) {
        super.apply(settings, options);
        final OpenSCADCodeStyleSettings openSCADCodeStyleSettings = settings.getCustomSettings(OpenSCADCodeStyleSettings.class);
        openSCADCodeStyleSettings.INDENT_CASCADING_TRANSFORMATIONS = myIndentCascadingTransformations.isSelected();
    }

    @Override
    public void reset(@NotNull final CodeStyleSettings settings, @NotNull final CommonCodeStyleSettings.IndentOptions options) {
        super.reset(settings, options);
        final OpenSCADCodeStyleSettings openSCADCodeStyleSettings = settings.getCustomSettings(OpenSCADCodeStyleSettings.class);
        myIndentCascadingTransformations.setSelected(openSCADCodeStyleSettings.INDENT_CASCADING_TRANSFORMATIONS);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        myIndentCascadingTransformations.setEnabled(enabled);
    }
}
