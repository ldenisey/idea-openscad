package com.javampire.openscad.editor;

import com.intellij.ui.JBColor;
import org.cef.browser.CefBrowser;

import java.awt.*;

public class OpenSCADPreviewFileEditorConfiguration {

    private Boolean showAxis = true;
    private Boolean showGrid = true;
    private Color modelColor = JBColor.YELLOW;
    private String cameraQuaternion;
    private String cameraPosition;

    private final OpenSCADPreviewFileEditor editor;

    public OpenSCADPreviewFileEditorConfiguration(final OpenSCADPreviewFileEditor editor) {
        this.editor = editor;
    }

    private CefBrowser getBrowser() {
        CefBrowser browser = null;
        if (editor.getHtmlPanel() != null) {
            browser = editor.getHtmlPanel().getCefBrowser();
        }
        return browser;
    }

    public Boolean getShowAxis() {
        return showAxis;
    }

    public void setShowAxis(final Boolean showAxis) {
        this.showAxis = showAxis;
    }

    public void applyShowAxis() {
        final CefBrowser browser = getBrowser();
        if (browser != null) {
            browser.executeJavaScript("showAxis(" + showAxis + ")", null, 0);
        }
    }

    public void toggleShowAxis() {
        showAxis = !showAxis;
        applyShowAxis();
    }

    public Boolean getShowGrid() {
        return showGrid;
    }

    public void setShowGrid(final Boolean showGrid) {
        this.showGrid = showGrid;
    }

    public void applyShowGrid() {
        final CefBrowser browser = getBrowser();
        if (browser != null) {
            browser.executeJavaScript("showGrid(" + showGrid + ")", null, 0);
        }
    }

    public void toggleShowGrid() {
        showGrid = !showGrid;
        applyShowGrid();
    }

    public Color getModelColor() {
        return modelColor;
    }

    public void setModelColor(final Color modelColor) {
        this.modelColor = modelColor;
    }

    public void applyModelColor() {
        final CefBrowser browser = getBrowser();
        if (browser != null) {
            final String newColorHex = String.format("0x%02x%02x%02x", modelColor.getRed(), modelColor.getGreen(), modelColor.getBlue());
            browser.executeJavaScript("setModelColor(" + newColorHex + ")", null, 0);
        }
    }

    public void updateModelColor(final Color newColor) {
        modelColor = newColor;
        applyModelColor();
    }

    public void saveConfiguration() {
        final CefBrowser browser = getBrowser();
        if (browser != null) {
            browser.executeJavaScript("saveConfiguration()", null, 0);
        }
    }

    public void loadConfiguration() {
        final CefBrowser browser = getBrowser();
        if (browser != null) {
            browser.executeJavaScript("loadConfiguration()", null, 0);
        }
    }
}
