<!-- Keep a Changelog guide -> https://keepachangelog.com -->

# OpenSCAD Intellij plugin ChangeLog

## Unreleased
### Added

### Changed

### Deprecated

### Removed

### Fixed

### Security

## 2.3.1
### Added
- Adding a notification to activate preview editor when OpenSCAD executable is already configured (plugin update use case)
- Adding "Open in OpenSCAD" and "Export as ..." actions in preview panel toolbar
- Adding "off", "amf", "3mf", "dxf", "csg" and "pdf" as possible export format in "Export as" actions

### Changed
- Preview are loaded asynchronously to avoid UI freezes during load and refresh
- Context menu "Render" changed to "Open in OpenSCAD" for more clarity

### Fixed
- [Issue-34](https://github.com/ldenisey/idea-openscad/issues/34) - Fix preview temporary folder selection in IDE without compilers (Webstorm, ...)

## 1.0.0
### Added
- Syntax highlighting
- Code folding