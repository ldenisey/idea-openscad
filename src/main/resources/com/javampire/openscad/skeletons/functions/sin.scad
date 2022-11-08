/**
 * Mathematical sine function of degrees.
 *
 * Usage example 1:
 *
 * for (i = [0:5]) {
 *   echo(360*i/6, sin(360*i/6)*80, cos(360*i/6)*80);
 *   translate([sin(360*i/6)*80, cos(360*i/6)*80, 0 ])
 *     cylinder(h = 200, r=10);
 * }
 *
 * Usage example 2:
 *
 *  for(i=[0:36])
 *     translate([i*10,0,0])
 *        cylinder(r=5,h=sin(i*10)*50+60);
 *
 * @param number Decimal. Angle in degrees.
 * @return Sine.
 */
function sin(number) = ();