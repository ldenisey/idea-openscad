package com.javampire.openscad.references;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FilenameIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.ProjectScope;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class OpenSCADResolver {

    public static List<PsiFile> findModuleContentFile(@NotNull final Module module, @NotNull final String fileRelativePath) {
        return findFilesByRelativePath(module.getProject(), module.getModuleContentScope(), fileRelativePath);
    }

    public static List<PsiFile> findModuleLibrary(@NotNull final Module module, @NotNull final String fileRelativePath) {
        return findFilesByRelativePath(module.getProject(), module.getModuleWithLibrariesScope(), fileRelativePath);
    }

    public static List<PsiFile> findProjectContentFile(@NotNull final Project project, @NotNull final String fileRelativePath) {
        return findFilesByRelativePath(project, ProjectScope.getContentScope(project), fileRelativePath);
    }

    public static List<PsiFile> findProjectLibrary(@NotNull final Project project, @NotNull final String fileRelativePath) {
        return findFilesByRelativePath(project, ProjectScope.getLibrariesScope(project), fileRelativePath);
    }

    public static List<PsiFile> findFilesByRelativePath(@NotNull final Project project, @NotNull final GlobalSearchScope scope, @NotNull final String fileRelativePath) {
        final String name = new File(fileRelativePath).getName();
        final Collection<VirtualFile> potentialFiles = FilenameIndex.getVirtualFilesByName(name, scope);
        final List<PsiFile> fileList = new ArrayList<>();
        final String relativePath = fileRelativePath.startsWith("/") ? fileRelativePath : "/" + fileRelativePath;
        for (final VirtualFile f : potentialFiles) {
            if (f.getPath().endsWith(relativePath)) {
                fileList.add(PsiManager.getInstance(project).findFile(f));
            }
        }
        return fileList;
    }
}
