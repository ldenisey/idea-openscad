/**
 * Translates (moves) its child elements along the specified vector. The argument name is optional.
 *
 * Example:
 *     translate(v = [x, y, z]) { ... }
 *
 *     cube(2,center = true);
 *     translate([5,0,0])
 *        sphere(1,center = true);
 *
 * @param vector3 Vector of 3 numbers.
 */
module translate(v = vector3){}