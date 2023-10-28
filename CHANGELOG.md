<!-- Keep a Changelog guide -> https://keepachangelog.com -->

# OpenSCAD Intellij plugin ChangeLog

## Unreleased

### Added

### Changed
- [Issue-85](https://github.com/ldenisey/idea-openscad/pull/85) Refactor Build Github action to move Jetbrain plugin verification into a dedicated worflow that will automatically compute versions to test in parallel
- [Issue-86](https://github.com/ldenisey/idea-openscad/pull/86) Migration to Java 17, following [Jetbrains instruction](https://blog.jetbrains.com/platform/2022/08/intellij-project-migrates-to-java-17/). Main consequence is end of support of version lower than 2022.3 (223).

### Removed
- [Issue-86](https://github.com/ldenisey/idea-openscad/pull/86) Support of IDE versions lower than 2022.3 (223)

### Fixed

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
