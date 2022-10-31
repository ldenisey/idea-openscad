/**
 * Creates a square or rectangle in the first quadrant. When center is true
 * the square is centered on the origin. Argument names are optional if given
 * in the order shown here.
 *
 *     square(size = [x, y], center = true/false);
 *     square(size =  x    , center = true/false);
 *
 * Default values:
 *     square();   yields:  square(size = [1, 1], center = false);
 *
 * Example 1 - 10x10 square equivalent scripts:
 *
 *     square(size = 10);
 *     square(10);
 *     square([10,10]);
 *
 *     square(10,false);
 *     square([10,10],false);
 *     square([10,10],center=false);
 *     square(size = [10, 10], center = false);
 *     square(center = false,size = [10, 10] );
 *
 * Example 2 - 20x10 square equivalent scripts:
 *
 *     square([20,10],true);
 *     a=[20,10];square(a,true);
 *
 * @param vector2 Single value, square with both sides this length 2 value array [x,y], rectangle with dimensions x and y
 * @param boolean Default is false. 1st (positive) quadrant, one corner at (0,0) true, square is centered at (0,0)
 */
module square(size = vector2, center = boolean){}