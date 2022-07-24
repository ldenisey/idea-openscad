package com.javampire.openscad.formatter;

import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CustomCodeStyleSettings;

public class OpenSCADCodeStyleSettings extends CustomCodeStyleSettings {

    public boolean INDENT_CASCADING_TRANSFORMATIONS = true;

    protected OpenSCADCodeStyleSettings(CodeStyleSettings container) {
        super("OpenSCADCodeStyleSettings", container);
    }
}
