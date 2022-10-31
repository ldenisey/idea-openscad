package com.javampire.openscad;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.indexing.IndexableSetContributor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BuiltinContributor extends IndexableSetContributor {

    private static final Logger LOG = Logger.getInstance(BuiltinContributor.class);

    public static final String BUILTIN_PATH = "/com/javampire/openscad/skeletons";
    private static final String BUILTIN_MODULES_PATH = BUILTIN_PATH + "/modules";
    private static final String BUILTIN_FUNCTIONS_PATH = BUILTIN_PATH + "/functions";

    public static BuiltinContributor INSTANCE;

    public BuiltinContributor() {
        super();
        INSTANCE = this;
    }

    @NotNull
    @Override
    public Set<VirtualFile> getAdditionalRootsToIndex() {
        final HashSet<VirtualFile> result = new HashSet<>();
        result.addAll(getFunctionBuiltinsFile());
        result.addAll(getModuleBuiltinsFile());
        return result;
    }

    public List<VirtualFile> getFunctionBuiltinsFile() {
        final List<VirtualFile> files = getScadFiles(BUILTIN_FUNCTIONS_PATH);
        if (files == null) {
            LOG.warn("Can not find builtin functions folder, completion and documentation will not be exhaustive.");
            return Collections.emptyList();
        }
        return files;
    }

    public List<VirtualFile> getModuleBuiltinsFile() {
        final List<VirtualFile> files = getScadFiles(BUILTIN_MODULES_PATH);
        if (files == null) {
            LOG.warn("Can not find builtin modules folder, completion and documentation will not be exhaustive.");
            return Collections.emptyList();
        }
        return files;
    }

    private @Nullable List<VirtualFile> getScadFiles(@NotNull final String resourcePath) {
        final VirtualFile builtinsRoot = VfsUtil.findFileByURL(getClass().getResource(resourcePath));
        if (builtinsRoot == null) {
            return null;
        }
        return VfsUtil.collectChildrenRecursively(builtinsRoot).parallelStream()
                .filter(virtualFile -> !virtualFile.isDirectory())
                .filter(virtualFile -> virtualFile.getFileType() == OpenSCADFileType.INSTANCE)
                .collect(Collectors.toList());
    }
}
