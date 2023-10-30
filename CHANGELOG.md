<!-- Keep a Changelog guide -> https://keepachangelog.com -->

# OpenSCAD Intellij plugin ChangeLog

## Unreleased

### Added
- [Issue-80](https://github.com/ldenisey/idea-openscad/pull/80) Adding formatting of unary operators, thanks to [Bert Baron](https://github.com/bertbaron)

### Changed
- [Issue-85](https://github.com/ldenisey/idea-openscad/pull/85) Refactor Build GitHub action to move Jetbrains plugin verification into a dedicated workflow that will automatically compute versions to test in parallel
- [Issue-86](https://github.com/ldenisey/idea-openscad/pull/86) Fix EAP incompatibilities : removal of third party library
- Unifying settings code example

### Removed

### Fixed
- Bump dependencies

## 2.3.3

### Added
- [Issue-39](https://github.com/ldenisey/idea-openscad/issues/39) highlight customizer comments

### Changed
- [Issue-45](https://github.com/ldenisey/idea-openscad/issues/45) Preview should not be computed when editor is in text only view

### Fixed
- [Issue-46](https://github.com/ldenisey/idea-openscad/issues/46) Some keywords are parsed as identifiers
- [Issue-41](https://github.com/ldenisey/idea-openscad/issues/41) Show preview activation popup only on OpenSCAD projects
- Bump dependencies
- GitHub set-output action migration

## 1.0.0

### Added
- Syntax highlighting
- Code folding
