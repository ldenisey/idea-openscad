/**
 * Rotational extrusion spins a 2D shape around the Z-axis to form a solid which has
 * rotational symmetry. One way to think of this operation is to imagine a Potter's
 * wheel placed on the X-Y plane with its axis of rotation pointing up towards +Z.
 * Then place the to-be-made object on this virtual Potter's wheel (possibly extended
 * down below the X-Y plane towards -Z, take the cross-section of this object on the
 * X-Z plane but keep only the right half (X >= 0). That is the 2D shape that need to
 * be fed to rotate_extrude() as the child in order to generate this solid.
 *
 * Since a 2D shape is rendered by OpenSCAD on the X-Y plane, an alternative way to
 * think of this operation is as follows: spins a 2D shape around the Y-axis to form
 * a solid. The resultant solid is placed so that its axis of rotation lies along
 * the Z-axis.
 *
 * It can not be used to produce a helix or screw threads.
 *
 * The 2D shape needs to lie completely on either the right (recommended) or the left
 * side of the Y-axis. More precisely speaking, each vertex of the shape must have
 * either x >= 0 or x <= 0. If the shape crosses the X axis a warning will be shown
 * in the console windows and the rotate_extrude() will be ignored. For OpenSCAD
 * versions prior to 2016.xxxx, if the shape is in the negative axis the faces will
 * be inside-out, which may cause undesired effects.
 *
 * Usage:
 *     rotate_extrude(
 *         angle = 360,
 *         convexity = 2
 *     ) {...}
 *
 * You must use parameter names due to a backward compatibility issue.
 *
 * @param number_a Requires version 2016. Defaults to 360. Specifies the number of degrees to sweep, starting at the positive X axis. The direction of the sweep follows the Right Hand Rule, hence a negative angle will sweep clockwise.
 * @param number_c If the extrusion fails for a non-trival 2D shape, try setting the convexity parameter (the default is not 10, but 10 is a "good" value to try).
 * @param number_$fn Default 0. Fixed number of fragments in 360 degrees. Values of 3 or more override $fa and $fs.
 * @param number_$fa Default 12. Minimum angle (in degrees) of each fragment.
 * @param number_$fs Default 2. Minimum circumferential length of each fragment.
 */
module rotate_extrude(angle = number_a, convexity = number_c, $fn = number_$fn, $fa = number_$fa, $fs = number_$fs){}