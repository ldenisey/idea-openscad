/**
 * Multiplies the geometry of all child elements with the given 4x4 transformation matrix.
 *
 * Usage: multmatrix(m = [...]) { ... }
 *
 * This is a breakdown of what you can do with the independent elements in the matrix (for the first three rows):
 *
 *     [Scale X]  [Scale X sheared along Y]  [Scale X sheared along Z]  [Translate X]
 *     [Scale Y sheared along X]  [Scale Y]  [Scale Y sheared along Z]  [Translate Y]
 *     [Scale Z sheared along X]  [Scale Z sheared along Y]  [Scale Z]  [Translate Z]
 *
 * The fourth row is used in 3D environments to define a view of the object. It is not used in OpenSCAD and should be [0,0,0,1]
 *
 * Example which rotates by 45 degrees in XY plane and translates by [10,20,30], ie the same as translate([10,20,30]) rotate([0,0,45]) would do:
 *
 *     angle=45;
 *     multmatrix(m = [ [cos(angle), -sin(angle), 0, 10],
 *                      [sin(angle),  cos(angle), 0, 20],
 *                      [         0,           0, 1, 30],
 *                      [         0,           0, 0,  1]
 *                   ]) union() {
 *        cylinder(r=10.0,h=10,center=false);
 *        cube(size=[10,10,10],center=false);
 *     }
 *
 * Example that skews a model, something that is not possible with the
 * other transformations. Also shows you can have the matrix in a variable:
 *
 *     M = [ [ 1  , 0  , 0  , 0   ],
 *           [ 0  , 1  , 0.7, 0   ],  // The "0.7" is the skew value; pushed along the y axis
 *           [ 0  , 0  , 1  , 0   ],
 *           [ 0  , 0  , 0  , 1   ] ] ;
 *     multmatrix(M) {  union() {
 *         cylinder(r=10.0,h=10,center=false);
 *         cube(size=[10,10,10],center=false);
 *     } }
 *
 * @param vector 4x4 transformation matrix.
 */
module multmatrix(vector) {}