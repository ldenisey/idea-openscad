/**
 * Displays the child elements using the specified RGB color + alpha value.
 * This is only used for the F5 preview as CGAL and STL (F6) do not currently
 * support color. The alpha value will default to 1.0 (opaque) if not specified.
 *
 * Function signature:
 *
 *     color( c = [r, g, b, a] ) { ... }
 *     color( c = [r, g, b], alpha = 1.0 ) { ... }
 *     color( "colorname", 1.0 ) { ... }
 *
 * Note that the r, g, b, a values are limited to floating point values in the
 * range [0,1] rather than the more traditional integers { 0 ... 255 }.
 * However, nothing prevents you to using R, G, B values from {0 ... 255} with
 * appropriate scaling:
 *
 *     color([ R/255, G/255, B/255 ]) { ... }
 *
 * Since version 2011.12, colors can also be defined by name (case insensitive).
 * For example, to create a red sphere, you can write:
 *
 *     color("red") sphere(5);
 *
 * Alpha is specified as an extra parameter for named colors:
 *
 *     color("Blue",0.5) cube(5);
 *
 * The available color names are taken from the World Wide Web consortium's SVG color list (http://www.w3.org/TR/css3-color/).
 *
 * Example 1: A 3-D multicolor sine wave
 *
 * Here's a code fragment that draws a wavy multicolor object
 *
 *     for(i=[0:36]) {
 *       for(j=[0:36]) {
 *         color( [0.5+sin(10*i)/2, 0.5+sin(10*j)/2, 0.5+sin(10*(i+j))/2] )
 *         translate( [i, j, 0] )
 *         cube( size = [1, 1, 11+10*cos(10*i)*sin(10*j)] );
 *       }
 *     }
 *
 * Being that -1<=sin(x)<=1 then 0<=(1/2 + sin(x)/2)<=1 , allowing for the
 * RGB components assigned to color to remain within the [0,1] interval.
 *
 * Example 2
 *
 * In cases where you want to optionally set a color based on a parameter you can use the following trick:
 *
 *     module myModule(withColors=false) {
 *         c=withColors?"red":undef;
 *         color(c) circle(r=10);
 *     }
 *
 * Setting the colorname to undef will keep the default colors.
 *
 * @param string Predefined color value : "blue", "red", "green", ... See <a href= "https://www.w3.org/TR/css-color-3/#html4"/>
 * @param vector_3 [red, green, blue] vector.
 * @param vector_4 [red, green, blue, alpha] vector.
 * @param number Default 1.0. Alpha value.
 */
module color(string, c = vector_3, c = vector_4, alpha = number) {}
