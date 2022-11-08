/**
 * Creates a cube in the first octant. When center is true, the cube is centered on the origin.
 * Argument names are optional if given in the order shown here.
 *
 *     cube(size = [x,y,z], center = true/false);
 *     cube(size =  x ,     center = true/false);
 *
 * Default values:
 *
 *     cube();   yields:  cube(size = [1, 1, 1], center = false);
 *
 * Example 1 - all these are equivalent:
 *
 *     cube(size = 18);
 *     cube(18);
 *     cube([18, 18, 18]);
 *     cube(18, false);
 *     cube([18,18,18], false);
 *     cube([18,18,18], center=false);
 *     cube(size = [18,18,18], center = false);
 *     cube(center = false, size = [18,18,18] );
 *
 * Example 2 - equivalent scripts:
 *
 *     cube([18,28,8],true);
 *     box=[18,28,8];cube(box,true);
 *
 * @param number Single value for x, y and z lengths.
 * @param vector_3 x, y and z lengths.
 * @param center Default false. If false, one corner is at (0, 0, 0); if true cube is centered at (0, 0, 0).
 */
module cube(size = number, size = vector_3, center = boolean>false){}