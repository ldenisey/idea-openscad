/**
 * Creates a circle at the origin. All parameters, except r, must be named.
 *
 * $fa, $fs and $fn must be named.
 *
 * Defaults:
 *     circle();  yields: circle($fn = 0, $fa = 12, $fs = 2, r = 1);
 *
 * Circle resolution is based on size, using $fa or $fs.
 * For a small, high resolution circle you can make a large circle, then scale it down, or you could set $fn or other special variables.
 *
 * Note:
 *     These examples exceed the resolution of a 3d printer as well
 *     as of the display screen.
 *
 * Example 1:
 *
 *     // create a high resolution circle with a radius of 2.
 *     scale([1/100, 1/100, 1/100]) circle(200);
 *     // Another way:
 *     circle(2, $fn=50);
 *
 * Example 2 - equivalent scripts:
 *
 *     circle(10);
 *     circle(r=10);
 *     circle(d=20);
 *     circle(d=2+9*2);
 *
 * Example 3 - ellipse equivalent scripts:
 *
 *     // An ellipse can be created from a circle by using either
 *     // scale() or resize() to make the x and y dimensions unequal.
 *
 *     resize([30,10])circle(d=20);
 *     scale([1.5,.5])circle(d=20);
 *
 * Example 4 - regular polygon:
 *
 *     // A regular polygon of 3 or more sides can be created by a hack
 *     // of using circle() with $fn set to the number of sides.
 *     // But DON'T USE IT! The only purpose of circle is creating circles
 *     // and ellipses. Software is free to render and process them as circles.
 *     // Also $fn can be redefined and your "polygons" will become true circles.
 *     // Use the code below instead:
 *
 *     module regular_polygon(order, r=1){
 *         angles=[ for (i = [0:order-1]) i*(360/order) ];
 *         coords=[ for (th=angles) [r*cos(th), r*sin(th)] ];
 *         polygon(coords);
 *     }
 *
 *     // The polygon is inscribed within the circle with all sides (and angles)
 *     // equal. One corner points to the positive x direction. For irregular
 *     // shapes see the polygon primitive below.
 *
 *     translate([-42,  0]){circle(20,$fn=3);%circle(20,$fn=90);}
 *     translate([  0,  0]) circle(20,$fn=4);
 *     translate([ 42,  0]) circle(20,$fn=5);
 *     translate([-42,-42]) circle(20,$fn=6);
 *     translate([  0,-42]) circle(20,$fn=8);
 *     translate([ 42,-42]) circle(20,$fn=12);
 *
 *     color("black"){
 *         translate([-42,  0,1])text("3",7,,center);
 *         translate([  0,  0,1])text("4",7,,center);
 *         translate([ 42,  0,1])text("5",7,,center);
 *         translate([-42,-42,1])text("6",7,,center);
 *         translate([  0,-42,1])text("8",7,,center);
 *         translate([ 42,-42,1])text("12",7,,center);
 *     }
 *
 * @param number Circle radius.
 * @param r Circle radius, 'r = ' is optional.
 * @param d Circle diameter (only available in versions later than 2014.03).
 * @param number_$fn Default 0. Fixed number of fragments in 360 degrees. Values of 3 or more override $fa and $fs.
 * @param number_$fa Default 12. Minimum angle (in degrees) of each fragment.
 * @param number_$fs Default 2. Minimum circumferential length of each fragment.
 */
module circle(r = number, d = number, $fn = number_$fn, $fa = number_$fa, $fs = number_$fs){}