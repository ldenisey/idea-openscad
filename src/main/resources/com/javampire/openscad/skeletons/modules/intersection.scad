/**
 * Creates the intersection of all child nodes. This keeps the overlapping
 * portion (logical and). Only the area which is common or shared by all
 * children is retained.
 *
 * May be used with either 2D or 3D objects, but don't mix them.
 *
 * Usage example:
 *
 *     intersection() {
 *         cylinder (h = 4, r=1, center = true, $fn=100);
 *         rotate ([90,0,0]) cylinder (h = 4, r=0.9, center = true, $fn=100);
 *     }
 */
module intersection() {}