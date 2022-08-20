package com.javampire.openscad.editor;

import com.intellij.ide.browsers.*;
import com.intellij.ide.browsers.actions.WebPreviewVirtualFile;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.CompilerProjectExtension;
import com.intellij.openapi.roots.FileIndexFacade;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VfsUtilCore;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.psi.PsiManager;
import com.intellij.util.Url;
import org.apache.commons.io.FileUtils;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Manage the creation/deletion of temporary files used for previewing scad file with the {@link OpenSCADPreviewFileEditor}.
 */
@Service(Service.Level.PROJECT)
public final class OpenSCADPreviewSiteService implements Disposable {

    private final static Logger LOG = Logger.getInstance(OpenSCADPreviewSiteService.class);
    private final static String HTML = "html";

    private final Project project;
    private VirtualFile htmlDir;

    public OpenSCADPreviewSiteService(@NotNull final Project project) {
        this.project = project;
    }

    public static OpenSCADPreviewSiteService getInstance(@NotNull final Project project) {
        return project.getService(OpenSCADPreviewSiteService.class);
    }

    public WebPreviewVirtualFile createVirtualFile(@NotNull final VirtualFile scadFile) {
        VirtualFile htmlDirTmp = getHtmlDir();
        if (htmlDirTmp == null) {
            LOG.error("Can not create stl file for scad file preview without an html output folder !");
            return null;
        }

        // Creating a dedicated html file based on template one
        final String siteFilesRelativeName = computeSiteFilesRelativeName(scadFile);
        final String htmlFileName = siteFilesRelativeName + ".html";
        final VirtualFile demoIndexFile = htmlDirTmp.findChild("demo.html");
        final Color previewBackgroundColor = EditorColorsManager.getInstance().getSchemeForCurrentUITheme().getDefaultBackground();
        final String previewBackgroundHex = String.format("#%02X%02X%02X", previewBackgroundColor.getRed(), previewBackgroundColor.getGreen(), previewBackgroundColor.getBlue());
        var refObject = new Object() {
            VirtualFile htmlFile = null;
        };
        ApplicationManager.getApplication().runWriteAction(() -> {
            try {
                refObject.htmlFile = htmlDirTmp.findChild(htmlFileName);
                if (refObject.htmlFile == null || !refObject.htmlFile.exists()) {
                    refObject.htmlFile = htmlDirTmp.createChildData(getInstance(project), htmlFileName);
                }
                final String indexContent = VfsUtil.loadText(demoIndexFile)
                        .replaceAll("(demo\\.stl)", siteFilesRelativeName + ".stl")
                        .replaceAll("transparent", previewBackgroundHex);
                refObject.htmlFile.setBinaryContent(indexContent.getBytes(StandardCharsets.UTF_8));
                refObject.htmlFile.setBOM(demoIndexFile.getBOM());
            } catch (IOException ioe) {
                LOG.error("Can not modify index html file for scad file preview !", ioe);
                htmlDir = null;
            }
        });
        if (htmlDir == null) {
            return null;
        }

        // Creating the WebPreviewVirtualFile from the previously created html file
        try {
            final OpenInBrowserRequest browserRequest = OpenInBrowserRequestKt.createOpenInBrowserRequest(
                    PsiManager.getInstance(project).findFile(refObject.htmlFile),
                    false
            );
            if (browserRequest != null) {
                browserRequest.setReloadMode(WebBrowserManager.getInstance().getWebPreviewReloadMode());
                Collection<Url> urls = WebBrowserService.getInstance().getUrlsToOpen(browserRequest, false);
                if (!urls.isEmpty()) {
                    Url url = urls.iterator().next();
                    return new WebPreviewVirtualFile(refObject.htmlFile, url);
                }
            }
        } catch (final WebBrowserUrlProvider.BrowserException e) {
            LOG.error("An error occurred while getting internal preview url.", e);
        }
        return null;
    }

    public VirtualFile getStlFile(final VirtualFile scadFile) {
        final String stlFilesRelativeName = computeSiteFilesRelativeName(scadFile) + ".stl";
        final AtomicReference<VirtualFile> curStlFile = new AtomicReference<>();
        ApplicationManager.getApplication().runWriteAction(() -> {
            try {
                curStlFile.set(getHtmlDir().findOrCreateChildData(this, stlFilesRelativeName));
            } catch (final IOException ioe) {
                LOG.error("An error occurred while generating STL for scad file preview.", ioe);
            }
        });
        return curStlFile.get();
    }

    private VirtualFile getHtmlDir() {
        if (htmlDir == null || !new File(htmlDir.getPath()).exists()) {
            final VirtualFile outputDir = getOutputDir();

            htmlDir = outputDir.findChild(HTML);
            if (htmlDir != null && !new File(htmlDir.getPath()).exists()) {
                htmlDir.refresh(false, true);
                htmlDir = outputDir.findChild(HTML);
            }

            if (htmlDir == null || !new File(htmlDir.getPath()).exists()) {
                ApplicationManager.getApplication().runWriteAction(() -> {
                    try {
                        htmlDir = outputDir.createChildDirectory(getInstance(project), HTML);
                    } catch (IOException ioe) {
                        LOG.error("Can not initialize a temporary directory for scad file preview !", ioe);
                        htmlDir = null;
                        return;
                    }

                    // Copy html and JS resources
                    final VirtualFile jarHtmlRoot = VfsUtil.findFileByURL(getClass().getResource("/html"));
                    try {
                        VfsUtil.copyDirectory(getInstance(project), jarHtmlRoot, htmlDir, null);
                    } catch (final IOException ioe) {
                        LOG.error("Can not create copy resource file for scad file preview !", ioe);
                        htmlDir = null;
                    }
                });
            }
        }
        return htmlDir;
    }

    private VirtualFile getOutputDir() {
        // Basic case, using compiler output path
        final CompilerProjectExtension compilerProjectExtension = CompilerProjectExtension.getInstance(project);
        if (compilerProjectExtension != null) {
            VirtualFile compilerOutputVirtualFile = compilerProjectExtension.getCompilerOutput();
            if (compilerOutputVirtualFile == null) {
                compilerOutputVirtualFile = createOutputDir(compilerProjectExtension.getCompilerOutputUrl());
            }
            return compilerOutputVirtualFile;
        }

        // Current language probably does not have a compiler, getting an existing excluded temp folder
        final Module module = ModuleManager.getInstance(project).getModules()[0];
        final VirtualFile[] rootFiles = ModuleRootManager.getInstance(module).getExcludeRoots();
        if (rootFiles.length > 0) {
            return rootFiles[0];
        }

        // No existing temp folder, creating one from excluded paths
        final String[] excludeRootUrls = ModuleRootManager.getInstance(module).getExcludeRootUrls();
        if (excludeRootUrls.length > 0) {
            return createOutputDir(excludeRootUrls[0]);
        }

        // Last resort, using custom folder
        final String DEFAULT_OUTPUT_FOLDER = "out";
        VirtualFile customFolder = project.getWorkspaceFile().findChild(DEFAULT_OUTPUT_FOLDER);
        if (customFolder == null) {
            customFolder = createOutputDir(project.getBasePath() + "/" + DEFAULT_OUTPUT_FOLDER);
        }
        return customFolder;
    }

    private VirtualFile createOutputDir(final String compilerOutputUrl) {
        final File compilerOutputFile = new File(FileUtil.toSystemDependentName(VfsUtilCore.urlToPath(compilerOutputUrl)));
        compilerOutputFile.mkdirs();
        return VirtualFileManager.getInstance().refreshAndFindFileByNioPath(compilerOutputFile.toPath());
    }

    private String computeSiteFilesRelativeName(@NotNull final VirtualFile scadFile) {
        return scadFile.getPath()
                .substring(0, scadFile.getPath().length() - 5)
                .replace(getModuleContentRoot(scadFile).getPath(), "")
                .replaceAll("^[/\\\\]*", "")
                .replaceAll("[ ./\\\\]", "_");
    }

    private VirtualFile getModuleContentRoot(@NotNull final VirtualFile scadFile) {
        final Module currentModule = FileIndexFacade.getInstance(project).getModuleForFile(scadFile);
        VirtualFile moduleRoot = null;
        for (final VirtualFile rootVirtualFile : ModuleRootManager.getInstance(currentModule).getContentRoots()) {
            if (scadFile.getPath().contains(rootVirtualFile.getPath())) {
                moduleRoot = rootVirtualFile;
                break;
            }
        }
        return moduleRoot;
    }

    @Override
    public void dispose() {
        try {
            FileUtils.deleteDirectory(new File(htmlDir.getPath()));
        } catch (final IOException ioe) {
            LOG.warn("An error occurred while deleting temporary scad preview folder.", ioe);
        }
    }
}
