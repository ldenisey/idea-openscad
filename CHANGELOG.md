<!-- Keep a Changelog guide -> https://keepachangelog.com -->

# OpenSCAD Intellij plugin ChangeLog

## Unreleased
Main repository is transferred from https://github.com/ncsaba/idea-openscad to https://github.com/ldenisey/idea-openscad.

### Added
- Issue-34 - 2019.05 features not fully supported
- Issue-38 - 2019.05 list comprehensions does not parse
- Issue-91 - "include" can be included in a block object
- Issue-92 - Code style: can disable indent in cascade transformations

### Changed
- Dependency updates, cleaning build configuration

### Deprecated

### Removed

### Fixed
- Issue-97 / PR-102 - Limit documentation provider for language OpenSCAD only (from kadhonn)
- Issue-80 / Issue-89 - Identifiers can start with digits

### Security
## 2.1.1
### Fixed
- Issue-71 & Issue-74 - Fix color identifier detection
- Issue-77 - Fix npe when invoking file contextual action menu

## 2.1.0
### Changed
- "Generate" ... actions have been transformed into an "Export as ..." action that allow for target file path and type selection.

### Fix
- Issue-59 NullPointerException In Intellij
- Issue-62 Doesn't open app correctly if file path includes spaces

## 2.0.1
### Changed
- Update GitHub actions and changelog format

### Fix
- Fix [Issue-56](https://github.com/ncsaba/idea-openscad/issues/56)

## 2.0.0
### Added
- Add code formatter
- Add settings for OpenSCAD libraries and executable
- Add import reference
- Add editor context menu open OpenSCAD and generate actions
- Add completion for variables, modules and functions

### Changed
- Update compatibility version from 192.2549 to no limit

### Fix
- Fix deprecated calls

## 1.3.0
### Added
- Added structure view
- Partial code navigation (modules/functions/variables without considering context)
- Documentation popups

### Changed
- Change version number to 1.3.0

### Fix
- Fix known grammar parsing issues

## 1.2.0
### Fix
- Fixed change-log from the default text

## 1.1.0
### Changed
- Updated plugin name to conform to standards for uploading to the jetbrains plugin repository.

## 1.0.0
### Added
- Syntax highlighting
- Code folding
