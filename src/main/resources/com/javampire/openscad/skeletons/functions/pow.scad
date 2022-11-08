/**
 * Mathematical power function.
 *
 * Usage examples:
 *
 *     for (i = [0:5]) {
 *       translate([i*25,0,0]) {
 *         cylinder(h = pow(2,i)*5, r=10);
 *         echo (i, pow(2,i));
 *       }
 *     }
 *
 *     echo(pow(10,2)); // means 10^2 or 10*10
 *     // result: ECHO: 100
 *
 *     echo(pow(10,3)); // means 10^3 or 10*10*10
 *     // result: ECHO: 1000
 *
 *     echo(pow(125,1/3)); // means 125^(0.333...) which equals calculating the cube root of 125
 *     // result: ECHO: 5
 *
 * @param number_b Decimal base.
 * @param number_e Decimal exponent.
 * @return Base^exponent.
 */
function pow(number_b, number_e) = ();