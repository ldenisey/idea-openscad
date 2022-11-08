/**
 * Mirrors the child element on a plane through the origin.
 *
 * Function signature:
 *
 *     mirror(v = [x, y, z]) { ... }
 *
 * Examples
 *
 * The original is on the right side. Note that mirror doesn't make a copy.
 * Like rotate and scale, it changes the object.
 *
 *     hand(); // original
 *     mirror([1,0,0]) hand();
 *
 *     hand(); // original
 *     mirror([1,1,0]) hand();
 *
 *     hand(); // original
 *     mirror([1,1,1]) hand();
 *
 *     rotate([0,0,10]) cube([3,2,1]);
 *     mirror([1,0,0]) translate([1,0,0]) rotate([0,0,10]) cube([3,2,1]);
 *
 * @param vector3 Normal vector of a plane intersecting the origin through which to mirror the object.
 */
module mirror(v = vector3) {}