/**
 * Set variables to a new value for a sub-tree. The parameters are evaluated sequentially and may depend on each other
 * (as opposed to the deprecated assign() statement).
 *
 * Usage example:
 *
 *     for (i = [10:50])
 *     {
 *         let (angle = i*360/20, r= i*2, distance = r*5)
 *         {
 *             rotate(angle, [1, 0, 0])
 *             translate([0, distance, 0])
 *             sphere(r = r);
 *         }
 *     }
 *
 * @param string1 Name of the first variable.
 * @param any1 Value of the first variable.
 * @param stringN Name of the n-th variable.
 * @param anyN Value of the n-th variable.
 * @since 2019.05
 */
module let(string1 = any1, stringN = anyN) {}
