/**
 * Mathematical tangent function of degrees.
 *
 * Usage example:
 *
 * for (i = [0:5]) {
 *   echo(360*i/6, tan(360*i/6)*80);
 *   translate([tan(360*i/6)*80, 0, 0 ])
 *     cylinder(h = 200, r=10);
 * }
 *
 * @param number Decimal. Angle in degrees.
 * @return Tangent.
 */
function tan(number) = ();