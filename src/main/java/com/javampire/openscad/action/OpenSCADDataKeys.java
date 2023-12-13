package com.javampire.openscad.action;

import com.intellij.openapi.actionSystem.DataKey;
import com.intellij.openapi.vfs.VirtualFile;
import com.javampire.openscad.editor.OpenSCADPreviewFileEditorConfiguration;
import org.cef.browser.CefBrowser;

public class OpenSCADDataKeys {
    public static final DataKey<VirtualFile> DESTINATION_VIRTUAL_FILE = DataKey.create("destinationVirtualFile");
    public static final DataKey<CefBrowser> PREVIEW_BROWSER = DataKey.create("previewBrowser");
    public static final DataKey<OpenSCADPreviewFileEditorConfiguration> EDITOR_CONFIG = DataKey.create("editorConfig");
}
