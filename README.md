![Workflow build](https://github.com/ldenisey/idea-openscad/actions/workflows/build.yml/badge.svg)
[![JetBrains Plugin Version](https://img.shields.io/jetbrains/plugin/v/11198-openscad-language-support?label=Latest%20Plugin%20Release)](https://plugins.jetbrains.com/plugin/11198-openscad-language-support/versions)
[![JetBrains Plugin Rating](https://img.shields.io/jetbrains/plugin/r/rating/11198-openscad-language-support)](https://plugins.jetbrains.com/plugin/11198-openscad-language-support/reviews)
[![JetBrains Plugin Downloads](https://img.shields.io/jetbrains/plugin/d/11198-openscad-language-support)](https://plugins.jetbrains.com/plugin/11198-openscad-language-support)

# OpenSCAD Language Support for IntelliJ Platform

[OpenSCAD](https://openscad.org/index.html) language plugin for IntelliJ Platform IDEs (Idea, PyCharm, etc). It provides :

* Preview split panel, based on [OpenSCAD](https://openscad.org/index.html) rendering
* Syntax highlighting
* Code completion
* Code navigation
* Formatting
* Code folding support
* Structure views
* Library support
* Color picking
* Actions for opening OpenSCAD and exporting model
* Color scheme close to the built-in OpenSCAD editor

## Configuration

### OpenSCAD executable

[OpenSCAD](https://openscad.org/downloads.html) needs to be installed on your machine for the preview editor and the context menu actions.

The plugin will search for an OpenSCAD executable in standard installation paths at startup.

Go in *Settings* -> *Languages & Frameworks* -> *OpenSCAD* to manually set your installation path and activate/deactivate the preview editor.

### Global libraries

The libraries configured in OpenSCAD are automatically added as libraries in your IDE.
You will be able to access them through navigation and code completion with take them into account.

### Formatting

The formatting options are located in *Settings* -> *Editor* -> *Code Style* -> *OpenSCAD*.

### OpenSCAD color scheme

The OpenSCAD color scheme can be loaded in *Settings* -> *Editor* -> *Color Scheme* -> *OpenSCAD* -> *Scheme* -> *OpenSCAD.Default*.

### Shortcuts

You can add shortcuts in *Settings* -> *Keymap* -> *Plugins* -> *OpenSCAD Language Support*.

## Preview panel

The plugin split preview editor will allow you to modify your code and easily check its result in the IDE.

The preview is done through an OpenSCAD STL file generation hence some information like colors are lost.

You can manually refresh the preview by clicking on the ![Refresh icon](/src/main/resources/com/javampire/openscad/icons/refresh.svg) button in the preview panel or in the editor context menu.
Alternatively, you can activate the auto refresh with the button ![Autorefresh icon](/src/main/resources/com/javampire/openscad/icons/autoRefresh.svg) which refresh the preview at every file save.
If your model is complex you can temporarily lower the [$fn variable](https://en.wikibooks.org/wiki/OpenSCAD_User_Manual/Other_Language_Features#.24fa.2C_.24fs_and_.24fn) to speed up the preview generation.

Temporary files are kept in a temporary folder (*out*, *temp*, *tmp* or *.tmp* folder depending on your IDE) at your project root.
If you are using a CVS (i.e. git), best is to ignore this folder.

## Context menu

Right-clicking on a scad file will give you access to two context menu actions :

* *Open in OpenSCAD* : To open an OpenSCAD instance for the given file.
* *Export as...* : To export your model in various format using OpenSCAD command line.
* *Refresh Preview* : If preview is enabled, execute its refresh.

## Issues and requests

Issues and requests are tracked in the [Issues tab](https://github.com/ldenisey/idea-openscad/issues).

## How to contribute

It is a free and opened plugin. Any help for coding, testing and reviewing are welcome !
Have a look at [dedicated page](CONTRIBUTING.md).