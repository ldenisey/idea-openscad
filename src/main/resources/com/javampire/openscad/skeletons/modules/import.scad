/**
 * Imports a file for use in the current OpenSCAD model. OpenSCAD currently
 * supports import of DXF, OFF and STL (both ASCII and Binary) files.
 * The file extension is used to determine which type.
 *
 * OpenSCAD can export files as  STL, OFF, AMF, DXF, SVG, CSG OR PNG(Image).
 *
 * These file types created by OpenSCAD, or others, can be imported as follows:
 *
 *     STL, OFF and DXF are imported using import().
 *     CSG can be imported using include<> or loaded like an SCAD file
 *     PNG can be imported using surface()
 *     There are open pull requests for SVG and AMF, which require a bit more work/testing.
 *     The file suffix is used to determine type.
 *
 * The File >> Open command may be used to insert this command. The file type
 * filter of the Open File dialog may only show OpenSCAD files, but file name
 * can be replaced with a wildcard (e.g. *.stl) to browse to additional file types.
 *
 * Example:
 *
 *     import("example012.stl", convexity=3);
 *     import("D:/Documents and Settings/User/My Documents/Gear.stl", convexity=3);
 *
 * Windows users must "escape" the backslashes by writing them doubled, or replace the
 * backslashes with forward slashes.
 *
 *     // Read a layer of a 2D DXF file and create a 3D shape.
 *     linear_extrude(height = 5, center = true, convexity = 10)
 *             import_dxf(file = "example009.dxf", layer = "plate");
 *
 * In the latest version of OpenSCAD, import() is now used for importing both 2D
 * (DXF for extrusion) and 3D (STL) files.
 *
 * If you want to render the imported STL file later, you have to make sure that the STL
 * file is "clean". This means that the mesh has to be manifold and should not contain
 * holes nor self-intersections. If the STL is not clean, you might get errors like:
 *
 *     CGAL error in CGAL_Build_PolySet: CGAL ERROR: assertion violation!
 *     Expr: check_protocoll == 0
 *     File: /home/don/openscad_deps/mxe/usr/i686-pc-mingw32/include/CGAL/Polyhedron_incremental_builder_3.h
 *     Line: 199
 *
 * or
 *
 *     CGAL error in CGAL_Nef_polyhedron3(): CGAL ERROR: assertion violation!
 *     Expr: pe_prev->is_border() || !internal::Plane_constructor<Plane>::get_plane(pe_prev->facet(),pe_prev->facet()->plane()).is_degenerate()
 *     File: /home/don/openscad_deps/mxe/usr/i686-pc-mingw32/include/CGAL/Nef_3/polyhedron_3_to_nef_3.h
 *     Line: 253
 *
 * In order to clean the STL file, you have the following options:
 *
 *     * use http://wiki.netfabb.com/Semi-Automatic_Repair_Options .
 *       This will repair the holes but not the self-intersections;
 *     * use netfabb basic. This free software doesn't have the option
 *       to close holes nor can it fix the self-intersections;
 *     * use MeshLab, This free software can fix all the issues.
 *
 * Using MeshLab, you can do:
 *
 *     * Render - Show non Manif Edges
 *     * Render - Show non Manif Vertices
 *     * if found, use Filters - Selection - Select non Manifold Edges or
 *       Select non Manifold Vertices - Apply - Close. Then click button
 *       'Delete the current set of selected vertices...' or check
 *       http://www.youtube.com/watch?v=oDx0Tgy0UHo for an instruction video.
 *       The screen should show "0 non manifold edges", "0 non manifold vertices"
 *     * Next, you can click the icon 'Fill Hole', select all the holes and click
 *       Fill and then Accept. You might have to redo this action a few times.
 *     * Use File - Export Mesh to save the STL.
 *
 * If Meshlab can't fill the last hole then Blender might help:
 *
 *     * Start Blender
 *     * `X, 1` to remove the default object
 *     * File, Import, Stl
 *     * `Tab` to edit the mesh
 *     * `A` to de-select all vertices
 *     * `Alt+Ctrl+Shift+M` to select all non-manifold vertices
 *     * `MMB` to rotate, `Shift+MMB` to pan, `wheel` to zoom
 *     * `C` for "circle" select, `Esc` to finish
 *     * `Alt+M, 1` to merge or `Space` and search for "merge" as alternative
 *     * Merging vertices is a useful way of filling holes where the vertices
 *       are so closely packed that the slight change in geometry is unimportant
 *       compared to the precision of a typical 3D printer
 *
 * @param string A string containing the path to the STL, OFF or DXF file. If the give path
 *  is not absolute, it is resolved relative to the importing script. Note that
 *  when using include<> with a script that uses import(), this is relative to
 *  the script doing the include<>.
 * @param number Optional integer, default is 1. The convexity parameter specifies the maximum number of front
 *  sides (back sides) a ray intersecting the object might penetrate.
 *  This parameter is only needed for correctly displaying the object in OpenCSG
 *  preview mode and has no effect on the polyhedron rendering.
 * @param string_l Optional, default is none. For DXF import only, specify a specific layer to import.
 * @since 2015.03-2
 */
module import(file = string, convexity = number, layer = string_l){}