package com.javampire.openscad.formatter;

import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CustomCodeStyleSettings;

public class OpenSCADCodeStyleSettings extends CustomCodeStyleSettings {

    public boolean INDENT_CASCADING_TRANSFORMATIONS = true;
    public boolean SPACE_AROUND_EQ_IN_NAMED_PARAMETER = false;
    public boolean SPACE_AROUND_EQ_IN_ARG_ASSIGNMENT = false;

    protected OpenSCADCodeStyleSettings(CodeStyleSettings container) {
        super("OpenSCAD", container);
    }
}
