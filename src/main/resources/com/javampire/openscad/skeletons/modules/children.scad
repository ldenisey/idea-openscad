/**
 * Use of children() allows modules to act as operators applied to any or all
 * of the objects within this module instantiation. In use, operator modules
 * do not end with a semi-colon.
 *
 *     name ( parameter values ){scope of operator}
 *
 * Objects are indexed via integers from 0 to $children-1. OpenSCAD sets $children
 * to the total number of objects within the scope. Objects grouped into a sub
 * scope are treated as one child.
 *
 * Example 1 - Use all children:
 *
 *     module move(x=0,y=0,z=0,rx=0,ry=0,rz=0)
 *     { translate([x,y,z])rotate([rx,ry,rz]) children(); }
 *
 *     move(10)           cube(10,true);
 *     move(-10)          cube(10,true);
 *     move(z=7.07, ry=45)cube(10,true);
 *     move(z=-7.07,ry=45)cube(10,true);
 *
 * Example 2 - Use only the first child, multiple times:
 *
 *     module lineup(num, space) {
 *        for (i = [0 : num-1])
 *          translate([ space*i, 0, 0 ]) children(0);
 *     }
 *
 *     lineup(5, 65){ sphere(30);cube(35);}
 *
 * Example 3 - Separate action for each child:
 *
 *     module SeparateChildren(space){
 *         for ( i= [0:1:$children-1])   // step needed in case $children < 2
 *             translate([i*space,0,0]) {
 *                 children(i);
 *                 text(str(i));
 *             }
 *     }
 *
 *     SeparateChildren(-20){
 *         cube(5);              // 0
 *         sphere(5);            // 1
 *         translate([0,20,0]){  // 2
 *             cube(5);
 *             sphere(5);
 *         }
 *         cylinder(15);         // 3
 *         cube(8,true);         // 4
 *     }
 *     translate([0,40,0])color("lightblue")
 *         SeparateChildren(20){cube(3,true);}
 *
 * Example 4 - Multiple ranges:
 *
 *     module MultiRange(){
 *        color("lightblue") children([0:1]);
 *        color("lightgreen")children([2:$children-2]);
 *        color("lightpink") children($children-1);
 *     }
 *
 *     MultiRange()
 *     {
 *        cube(5);              // 0
 *        sphere(5);            // 1
 *        translate([0,20,0]){  // 2
 *          cube(5);
 *          sphere(5);
 *        }
 *        cylinder(15);         // 3
 *        cube(8,true);         // 4
 *     }
 *
 * Example 5:
 *
 *     // Objects
 *     module arrow(){
 *         cylinder(10);
 *         cube([4,.5,3],true);
 *         cube([.5,4,3],true);
 *         translate([0,0,10]) cylinder(4,2,0,true);
 *     }
 *
 *     module cannon(){
 *         difference(){union()
 *           {sphere(10);cylinder(40,10,8);} cylinder(41,4,4);
 *     } }
 *
 *     module base(){
 *         difference(){
 *           cube([40,30,20],true);
 *           translate([0,0,5])  cube([50,20,15],true);
 *     } }
 *
 *     // Operators
 *     module aim(elevation,azimuth=0) {
 *         rotate([0,0,azimuth]) {
 *             rotate([0,90-elevation,0])
 *                 children(0);
 *             children([1:1:$children-1]);   // step needed in case $children < 2
 *         }
 *     }
 *
 *     aim(30,20)arrow();
 *     aim(35,270)cannon();
 *     aim(15){cannon();base();}
 *
 *     module RotaryCluster(radius=30,number=8)
 *         for (azimuth =[0:360/number:359])
 *           rotate([0,0,azimuth])
 *             translate([radius,0,0]) { children();
 *               translate([40,0,30]) text(str(azimuth)); }
 *
 *     RotaryCluster(200,7) color("lightgreen") aim(15){cannon();base();}
 *     rotate([0,0,110]) RotaryCluster(100,4.5) aim(35)cannon();
 *     color("LightBlue")aim(55,30){cannon();base();}
 *
 * @param any Number : Value or variable to select one child.
 * Range : Select from start to end incremented by step (default to 1 or -1).
 * Vector : Selection of several children.
 */
module children(any = None){}