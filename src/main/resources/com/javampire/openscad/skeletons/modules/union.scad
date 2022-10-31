/**
 * Creates a union of all its child nodes. This is the sum of all children (logical or).
 * May be used with either 2D or 3D objects, but don't mix them.
 *
 * Usage example:
 *
 *     union() {
 *         cylinder (h = 4, r=1, center = true, $fn=100);
 *         rotate ([90,0,0]) cylinder (h = 4, r=0.9, center = true, $fn=100);
 *     }
 *
 * Remark:
 *     union is implicit when not used. But it is mandatory, for example,
 *     in difference to group first child nodes into one.
 */
module union() {}