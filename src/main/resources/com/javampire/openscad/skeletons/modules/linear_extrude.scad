/**
 * Linear Extrusion is an operation that takes a 2D object as input and generates a 3D object as a result.
 *
 * In OpenSCAD Extrusion is always performed on the projection (shadow) of the 2d object xy plane and along the Z axis; so if you rotate or apply other transformations to the 2d object before extrusion, its shadow shape is what is extruded.
 *
 * Although the extrusion is linear along the Z axis, a twist parameter is available that causes the object to be rotated around the Z axis as it is extruding upward. This can be used to rotate the object at its center, as if it is a spiral pillar, or produce a helical extrusion around the Z axis, like a pig's tail.
 *
 * A scale parameter is also included so that the object can be expanded or contracted over the extent of the extrusion, allowing extrusions to be flared inward or outward.
 *
 * Usage:
 *     linear_extrude(height = 5,
 *                    center = true,
 *                    convexity = 10,
 *                    twist = -fanrot,
 *                    slices = 20,
 *                    scale = 1.0,
 *                    $fn = 16) {}
 *
 * You must use parameter names due to a backward compatibility issue.
 *
 * @param number_h Height, must be positive.
 * @param boolean Center is similar to the parameter center of cylinders. If center is false
 *  the linear extrusion Z range is from 0 to height; if it is true, the range is from -height/2 to height/2.
 * @param number If the extrusion fails for a non-trivial 2D shape, try setting the convexity parameter (the default is not 10, but 10 is a "good" value to try).
 * @param twist Twist is the number of degrees of through which the shape is extruded.
 *  Setting the parameter twist = 360 will extrude through one revolution. The twist direction follows the left hand rule.
 * @param number_sl Default is 20. The slices parameter defines the number of intermediate points along the Z axis of the extrusion. Its default increases with the value of twist.
 *  Explicitly setting slices may improve the output refinement.
 *  The special variables $fn, $fs and $fa can also be used to improve the output.
 *  If slices is not defined, its value is taken from the defined $fn value.
 * @param number_sc Scales the 2D shape by this value over the height of the extrusion.
 *  Scale can be a scalar or a vector. Note that if scale is a vector, the resulting side walls may be nonplanar. Use twist=0 and the slices parameter to avoid asymmetry.
 */
module linear_extrude(height = number_h, center = boolean, convexity = number, twist = -fanrot, slices = number_sl, scale = number_sc) {}