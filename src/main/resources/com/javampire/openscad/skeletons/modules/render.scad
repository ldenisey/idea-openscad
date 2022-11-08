/**
 * Warning:
 *     Using render, always calculates the CSG model for this tree
 *     (even in OpenCSG preview mode).
 *     This can make previewing very slow and OpenSCAD to appear to hang/freeze.
 *
 * Usage example:
 *
 *     render(convexity = 1) { ... }
 *
 * @param number Integer, default is 1. The convexity parameter specifies the maximum number of front and
 *  back sides a ray intersecting the object might penetrate. This parameter is
 *  only needed for correctly displaying the object in OpenCSG preview mode and
 *  has no effect on the polyhedron rendering.
 *  Setting it to 10 should work fine for most cases.
 */
module render(convexity = number){}