package com.javampire.openscad.settings;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.process.CapturingProcessHandler;
import com.intellij.ide.IdeBundle;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationAction;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.impl.text.TextEditorImpl;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ModifiableModelsProvider;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.roots.OrderEnumerator;
import com.intellij.openapi.roots.OrderRootType;
import com.intellij.openapi.roots.libraries.Library;
import com.intellij.openapi.roots.libraries.LibraryTable;
import com.intellij.openapi.roots.libraries.LibraryTablesRegistrar;
import com.intellij.openapi.startup.StartupActivity;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.CommonProcessors;
import com.javampire.openscad.OpenSCADFileType;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OpenSCADSettingsStartupActivity implements StartupActivity {

    private static final int EXE_SEARCH_TIMEOUT = 5 * 60 * 1000;

    private final static String[] UNIX_EXECUTABLE_NAMES = {"OpenSCAD", "openscad"};

    private final static String[] WINDOWS_EXECUTABLE_NAMES = {"OpenSCAD.exe"};

    private final static List<String> MACOS_PATH_OPTIONS = Arrays.asList(
            "/Applications/MacPorts/OpenSCAD.app/Contents/MacOS",
            "/Applications/OpenSCAD.app/Contents/MacOS",
            "/usr/local/bin",
            "/usr/bin"
    );

    private final static List<String> LINUX_PATH_OPTIONS = Arrays.asList(
            "/usr/local/bin",
            "/usr/bin"
    );

    private final static List<String> WINDOWS_PATH_OPTIONS = Arrays.asList(
            "C:\\Program Files\\OpenSCAD",
            "\\Program Files\\OpenSCAD"
    );

    private final static String OPENSCAD_LIBRARY_NAME = "OpenSCAD Libraries";

    @Override
    public void runActivity(@NotNull final Project project) {
        final OpenSCADSettings settings = OpenSCADSettings.getInstance();

        if (!settings.hasExecutable()) {
            ProgressManager.getInstance().runProcessWithProgressSynchronously(
                    () -> {
                        final String suggestedExecutablePath = searchExecutablePath();
                        if (suggestedExecutablePath == null) {
                            new Notification(
                                    OpenSCADSettings.class.getSimpleName(),
                                    "Could not find the OpenSCAD executable",
                                    "Preview and action menu are disabled. If you do have OpenSCAD installed on your machine, configure it in Settings -> Languages & Frameworks -> OpenSCAD",
                                    NotificationType.WARNING
                            ).notify(project);
                            settings.setAllowPreviewEditor(false);
                        } else {
                            settings.setOpenSCADExecutable(suggestedExecutablePath);
                            settings.setAllowPreviewEditor(true);
                            new Notification(
                                    OpenSCADSettings.class.getSimpleName(),
                                    "OpenSCAD executable has been found",
                                    "Preview and action menu are enabled. You can check and modify the configuration Settings -> Languages & Frameworks -> OpenSCAD",
                                    NotificationType.INFORMATION
                            ).notify(project);
                        }
                    },
                    "Configuring OpenSCAD executable",
                    false,
                    project
            );
        }

        if (settings.hasExecutable()) {
            updateOpenSCADLibraries(project);
            if (!settings.isAllowPreviewEditor()) {
                final Notification previewNotification = new Notification(
                        OpenSCADSettings.class.getSimpleName(),
                        "OpenSCAD files preview is available",
                        "The split preview editor allows previewing your model without opening OpenSCAD. " +
                                "It displays an STL generated from OpenSCAD command line. Some information like colors " +
                                "are missing but the overall model is the exact result of OpensCAD rendering.",
                        NotificationType.INFORMATION
                );
                previewNotification.setSuggestionType(true);
                previewNotification.addAction(
                        NotificationAction.createSimpleExpiring("Enable preview", () -> {
                            settings.setAllowPreviewEditor(true);
                            // Closing all opened scad file text editors to open them in preview editors
                            final FileEditorManager fileEditorManager = FileEditorManager.getInstance(project);
                            for (final FileEditor fileEditor : fileEditorManager.getAllEditors()) {
                                if (fileEditor instanceof TextEditorImpl) {
                                    final VirtualFile file = fileEditor.getFile();
                                    if (file.getFileType() == OpenSCADFileType.INSTANCE) {
                                        fileEditorManager.closeFile(file);
                                        fileEditorManager.openFile(file, fileEditorManager.getSelectedEditor() == fileEditor);
                                    }
                                }
                            }
                        })
                );
                previewNotification.addAction(
                        NotificationAction.createSimpleExpiring(
                                IdeBundle.message("sys.health.acknowledge.action"),
                                () -> previewNotification.setDoNotAskFor(project)
                        )
                );
                previewNotification.notify(project);
            }
        }
    }

    public static void updateOpenSCADLibraries(final Project project) {
        final List<String> libraryPaths = OpenSCADInfo.getLibraryPaths();
        if (libraryPaths != null && !libraryPaths.isEmpty()) {
            ApplicationManager.getApplication().runWriteAction(() -> {
                final Library library = createLibrary(libraryPaths);
                addLibraryToModules(project, library);
            });
        }
    }

    /**
     * Add all {@code paths} in a library.
     *
     * @param paths List of paths of the new library.
     */
    public static Library createLibrary(@NotNull final List<String> paths) {
        // Create (or get) library
        final LibraryTable.ModifiableModel model = LibraryTablesRegistrar.getInstance().getLibraryTable().getModifiableModel();
        Library library = model.getLibraryByName(OPENSCAD_LIBRARY_NAME);
        if (library == null) {
            library = model.createLibrary(OPENSCAD_LIBRARY_NAME);
        }

        // Add paths to the library
        final Library.ModifiableModel modifiableModel = library.getModifiableModel();
        for (final String root : library.getUrls(OrderRootType.CLASSES)) {
            modifiableModel.removeRoot(root, OrderRootType.CLASSES);
        }
        for (final String path : paths) {
            modifiableModel.addRoot("file://" + path, OrderRootType.CLASSES);
        }
        modifiableModel.commit();
        model.commit();

        return library;
    }

    private static void addLibraryToModules(@NotNull final Project project, @NotNull final Library library) {
        final ModifiableModelsProvider modelsProvider = ApplicationManager.getApplication().getService(ModifiableModelsProvider.class);
        for (final Module module : ModuleManager.getInstance(project).getModules()) {
            // https://intellij-support.jetbrains.com/hc/en-us/community/posts/115000160370-How-to-list-module-dependencies-
            final List<Library> moduleLibraries = new ArrayList<>();
            OrderEnumerator.orderEntries(module).forEachLibrary(new CommonProcessors.CollectProcessor<>(moduleLibraries));

            // If library is not yet in the module, add it
            if (moduleLibraries.stream().noneMatch(it -> OPENSCAD_LIBRARY_NAME.equals(it.getName()))) {
                final ModifiableRootModel modifiableModel = modelsProvider.getModuleModifiableModel(module);
                modifiableModel.addLibraryEntry(library);
                modelsProvider.commitModuleModifiableModel(modifiableModel);
            }
        }
    }

    public static String searchExecutablePath() {
        String executablePath = null;
        if (SystemInfo.isWindows) {
            // Try first to detect if the executable is on the PATH
            final String path = System.getenv("PATH");
            executablePath = filterWindowsPathOptions(StringUtil.split(path, ";"));

            // If not found, check common locations
            if (executablePath == null) {
                executablePath = filterWindowsPathOptions(WINDOWS_PATH_OPTIONS);
            }
        } else if (SystemInfo.isUnix) {
            // Try to detect from environment (i.e. PATH) on non-windows systems
            for (String executableName : UNIX_EXECUTABLE_NAMES) {
                try {
                    executablePath = new CapturingProcessHandler(
                            new GeneralCommandLine("which", executableName)
                    ).runProcess(EXE_SEARCH_TIMEOUT).getStdout().trim();

                    if (!executablePath.isEmpty()) {
                        break;
                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
            if (executablePath != null && executablePath.isEmpty()) {
                executablePath = null;
            }

            // If not found, check common locations
            if (executablePath == null) {
                executablePath = filterUnixPathOptions(LINUX_PATH_OPTIONS);
            }
        } else if (SystemInfo.isMac) {
            // Check common locations
            executablePath = filterUnixPathOptions(MACOS_PATH_OPTIONS);
        }

        // If running unit tests on server without openscad, give a fake path
        if (executablePath == null && ApplicationManager.getApplication().isUnitTestMode()) {
            return "/fake/path/for/unit/tests";
        }

        return executablePath;
    }

    private static String filterWindowsPathOptions(final List<String> pathOptions) {
        for (String pathEntry : pathOptions) {
            if (pathEntry.startsWith("\"") && pathEntry.endsWith("\"")) {
                if (pathEntry.length() < 2) continue;
                pathEntry = pathEntry.substring(1, pathEntry.length() - 1);
            }
            for (String executableName : WINDOWS_EXECUTABLE_NAMES) {
                File f = new File(pathEntry, executableName);
                if (f.exists()) {
                    try {
                        return f.getCanonicalPath();
                    } catch (IOException e) {
                        return f.getPath();
                    }
                }
            }
        }
        return null;
    }

    private static String filterUnixPathOptions(final List<String> pathOptions) {
        for (String pathEntry : pathOptions) {
            for (String executableName : UNIX_EXECUTABLE_NAMES) {
                File f = new File(pathEntry, executableName);
                if (f.isFile() && f.canExecute()) {
                    try {
                        return f.getCanonicalPath();
                    } catch (IOException e) {
                        return f.getPath();
                    }
                }
            }
        }
        return null;
    }
}
