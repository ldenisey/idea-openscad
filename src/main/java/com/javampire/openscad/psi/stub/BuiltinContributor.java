package com.javampire.openscad.psi.stub;

import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.indexing.IndexableSetContributor;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BuiltinContributor extends IndexableSetContributor {

    @NotNull
    @Override
    public Set<VirtualFile> getAdditionalRootsToIndex() {
        final HashSet<VirtualFile> result = new HashSet<>();
        for (final String fileName : Arrays.asList("builtin_functions.scad", "builtin_modules.scad")) {
            final URL builtinFileUrl = this.getClass().getResource(
                    "/com/javampire/openscad/skeletons/" + fileName
            );
            final VirtualFile builtinFile = VfsUtil.findFileByURL(builtinFileUrl);
            result.add(builtinFile);
        }
        return result;
    }
}
