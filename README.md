![Java CI](https://github.com/ldenisey/idea-openscad/workflows/Java%20CI/badge.svg)

# OpenSCAD Language Support for IntelliJ Platform

OpenSCAD language plugin for IntelliJ Platform IDEs (Idea, PyCharm, etc.). It provides :

* Preview split panel, based on OpenSCAD rendering with [viewstl](https://github.com/omrips/viewstl).
* syntax highlighting
* formatting
* code folding support
* structure views
* code completion
* code navigation
* library support
* actions for opening OpenSCAD and exporting model

A color scheme coming close to what the built-in OpenSCAD editor uses is provided and can be selected in the preferences :
*Settings* -> *Editor* -> *Color Scheme* -> *OpenSCAD* -> *Scheme* -> *OpenSCAD.Default*.

Formatting options are configurable in the preferences : *Settings* -> *Editor* -> *Code Style* -> *OpenSCAD*.

## Configuration

This plugin will search for an OpenSCAD executable in standard installation paths at startup.
If your installation path is not found, you can set it in *Settings* -> *Languages & Frameworks* -> *OpenSCAD*.

If set, global libraries are automatically detected and added to your project, for navigation and code completion.

## Context menu

When OpenSCAD is configured, a right click on a scad file will give you access to two context menu actions :

- *Render* : To open an OpenSCAD instance for the given file.
- *Export as...* : To export your model in various format using OpenSCAD command line.

## Preview panel

The preview panel is available if OpenSCAD is installed on your machine.

The preview is done by generating a temporary STL file from your current editor content using OpenSCAD command line,
then displayed in an HTML page using [viewstl](https://github.com/omrips/viewstl).

You can refresh the preview by clicking on the refresh button at the top right of the preview editor.
Note that the refresh can take some time if your model is complex due to STL file generation. For faster preview,
you can temporarily set the [$fn variable](https://en.wikibooks.org/wiki/OpenSCAD_User_Manual/Other_Language_Features#.24fa.2C_.24fs_and_.24fn)
to reduce the complexity of the rendering.

Temporary files are kept in the compilation output path of your project (generally the *out* folder at the root of the project).
They are automatically deleted when the IDE is closed. If needed, you can safely delete the *html* folder in the compilation
output path when all the scad editors are closed. In any case, it is not advised to modify, copy or save those files in a CVS (Git, ...).

## Issues and requests

Issues and requests are tracked in the [Issues tab](https://github.com/ldenisey/idea-openscad/issues). Follow the templates to submit a new one.

## How to contribute

It is a free and opened plugin. Any help for coding, testing and reviewing are welcome !
Have a look at [dedicated page](CONTRIBUTING.md).