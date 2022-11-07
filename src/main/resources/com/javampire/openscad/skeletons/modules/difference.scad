/**
 * Subtracts the 2nd (and all further) child nodes from the first one (logical and not). May be used with either 2D or 3D objects, but don't mix them.
 *
 * Usage example:
 *
 *     difference() {
 *         cylinder (h = 4, r=1, center = true, $fn=100);
 *         rotate ([90,0,0]) cylinder (h = 4, r=0.9, center = true, $fn=100);
 *     }
 *
 * Difference with multiple children (note in the second example the result of adding a union of the 1st and 2nd children) :
 *
 *     // Usage example for difference of multiple children:
 *     $fn=90;
 *     difference(){
 *                                                 cylinder(r=5,h=20,center=true);
 *         rotate([00,140,-45]) color("LightBlue") cylinder(r=2,h=25,center=true);
 *         rotate([00,40,-50])                     cylinder(r=2,h=30,center=true);
 *         translate([0,0,-10])rotate([00,40,-50]) cylinder(r=1.4,h=30,center=true);
 *     }
 *
 *     // second instance with added union
 *     translate([10,10,0]){
 *         difference(){
 *           union(){        // combine 1st and 2nd children
 *                                                     cylinder(r=5,h=20,center=true);
 *             rotate([00,140,-45]) color("LightBlue") cylinder(r=2,h=25,center=true);
 *           }
 *           rotate([00,40,-50])                       cylinder(r=2,h=30,center=true);
 *           translate([0,0,-10])rotate([00,40,-50])   cylinder(r=1.4,h=30,center=true);
 *         }
 *     }
 *
 * @param object_main() Main object from which to substract the following.
 * @param object_n() List of objects to substract from object_main().
 */
module difference() { object_main(), object_n() }