/**
 * Set variables to a new value for a sub-tree.
 *
 * Usage example:
 *
 *     for (i = [10:50])
 *     {
 *         assign (angle = i*360/20, distance = i*10, r = i*2)
 *         {
 *             rotate(angle, [1, 0, 0])
 *             translate([0, distance, 0])
 *             sphere(r = r);
 *         }
 *     }
 *
 *     is equivalent to :
 *
 *     for (i = [10:50])
 *     {
 *         angle = i*360/20;
 *         distance = i*10;
 *         r = i*2;
 *         rotate(angle, [1, 0, 0])
 *         translate([0, distance, 0])
 *         sphere(r = r);
 *     }
 *
 * @param string1 Name of the first variable.
 * @param any1 Value of the first variable.
 * @param stringN Name of the n-th variable.
 * @param anyN Value of the n-th variable.
 * @deprecated Will be removed in future releases. Variables can now be assigned anywhere. If you prefer this way of
 * setting values, the new Let Statement can be used instead.
 */
module assign(string1 = any1, stringN = anyN) {}
