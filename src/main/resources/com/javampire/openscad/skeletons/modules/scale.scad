/**
 * Scales its child elements using the specified vector. The argument name is optional.
 *
 * Usage Example:
 *     scale(v = [x, y, z]) { ... }
 *
 *     cube(10);
 *     translate([15,0,0]) scale([0.5,1,2]) cube(10);
 *
 * Note:
 *     Do not use negative scale values. Negative scale values appear to work for previews,
 *     but they lead to unpredictable errors when rendering through CGAL. Use the mirror() function instead.
 *
 * @param v Vector of 3 numbers.
 */
module scale(v = vector3){}