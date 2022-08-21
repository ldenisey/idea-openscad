package com.javampire.openscad.editor;

import com.intellij.ide.browsers.actions.WebPreviewVirtualFile;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.vfs.VirtualFile;

public class OpenSCADPreviewSite implements Disposable {

    public final VirtualFile scadFile;
    public WebPreviewVirtualFile htmlFile;
    public VirtualFile previewFile;

    public OpenSCADPreviewSite(VirtualFile scadFile) {
        this.scadFile = scadFile;
    }

    @Override
    public void dispose() {

    }
}
