/**
 * Displays the convex hull of child nodes.
 *
 * Usage example:
 *
 *     hull() {
 *         translate([15,10,0]) circle(10);
 *         circle(10);
 *     }
 *
 * Hull with 2D arguments can only produce a 2D result; translating
 * the constituent 2D parts in the Z direction has no effect.
 *
 * @param object_main() Main object.
 * @param object_n() List of objects to hull with object_main() and with each others.
 */
module hull(){ object_main(), object_n() }