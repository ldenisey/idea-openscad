/**
 * Creates a cylinder or cone centered about the z axis. When center is true, it is also centered vertically along the z axis.
 *
 * Parameter names are optional if given in the order shown here. If a parameter is named, all following parameters must also be named.
 *
 * NOTE:
 *     If r, d, d1 or d2 are used they must be named.
 *
 *     cylinder(h = height, r1 = BottomRadius, r2 = TopRadius, center = true/false);
 *
 * NOTES:
 *     $fa, $fs and $fn must be named.
 *
 * Defaults:
 *     cylinder();  yields: cylinder($fn = 0, $fa = 12, $fs = 2, h = 1, r1 = 1, r2 = 1, center = false);
 *
 * Example 1 - equivalent scripts:
 *
 *     cylinder(h=15, r1=9.5, r2=19.5, center=false);
 *     cylinder(  15,    9.5,    19.5, false);
 *     cylinder(  15,    9.5,    19.5);
 *     cylinder(  15,    9.5, d2=39  );
 *     cylinder(  15, d1=19,  d2=39  );
 *     cylinder(  15, d1=19,  r2=19.5);
 *
 * Example 2 - equivalent scripts:
 *
 *     cylinder(h=15, r1=10, r2=0, center=true);
 *     cylinder(  15,    10,    0,        true);
 *     cylinder(h=15, d1=20, d2=0, center=true);
 *
 * Example 3 - equivalent scripts:
 *
 *     cylinder(h=20, r=10, center=true);
 *     cylinder(  20,   10, 10,true);
 *     cylinder(  20, d=20, center=true);
 *     cylinder(  20,r1=10, d2=20, center=true);
 *     cylinder(  20,r1=10, d2=2*10, center=true);
 *
 * Larger values of $fn create smoother, more circular, surfaces at the cost of
 * longer rendering time. Some use medium values during development for the faster
 * rendering, then change to a larger value for the final F6 rendering.
 *
 * However, use of small values can produce some interesting non circular objects.
 * Examples:
 *
 *     cylinder(20,20,20,$fn=3);
 *     cylinder(20,20,00,$fn=4);
 *     cylinder(20,20,10,$fn=4);
 *
 * When using cylinder() with difference() to place holes in objects, the holes
 * will be undersized. This is because circular paths are approximated with
 * polygons inscribed within in a circle. The points of the polygon are on
 * the circle, but straight lines between are inside. To have all of the hole
 * larger than the true circle, the polygon must lie wholly outside of the circle
 * (circumscribed).
 *
 * Notes on accuracy:
 *     Circle objects are approximated. The algorithm for doing this matters when you want 3d printed holes to be the right size.
 *
 * @param number_h Height of the cylinder or cone.
 * @param number_r Radius of cylinder. r1 = r2 = r.
 * @param number_r1 Radius, bottom of cone.
 * @param number_r2 Radius, top of cone.
 * @param number_d Diameter of cylinder. r1 = r2 = d /2.
 * @param number_d1 Diameter, bottom of cone. r1 = d1 /2.
 * @param number_d2 Diameter, top of cone. r2 = d2 /2.
 * @param boolean False (default), z ranges from 0 to h true, z ranges from -h/2 to +h/2.
 * @param number_$fn Default 0. Fixed number of fragments in 360 degrees. Values of 3 or more override $fa and $fs.
 * @param number_$fa Default 12. Minimum angle (in degrees) of each fragment.
 * @param number_$fs Default 2. Minimum circumferential length of each fragment.
 * @since 2014.03 for d, d1, d2.
 */
module cylinder(h = number_h, r = number_r, r1 = number_r1, r2 = number_r2, d = number_d, d1 = number_d1, d2 = number_d2, center = boolean, $fn = number_$fn, $fa = number_$fa, $fs = number_$fs){}