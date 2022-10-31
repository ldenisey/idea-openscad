/**
 * Creates a sphere at the origin of the coordinate system.
 * The r argument name is optional. To use d instead of r, d must be named.
 *
 * NOTE:
 *     d is only available in versions later than 2014.03.
 *     Debian is currently known to be behind this.
 *
 * Default values:
 *     sphere();   yields:   sphere($fn = 0, $fa = 12, $fs = 2, r = 1);
 *
 * Usage examples:
 *
 *     sphere(r = 1);
 *     sphere(r = 5);
 *     sphere(r = 10);
 *     sphere(d = 2);
 *     sphere(d = 10);
 *     sphere(d = 20);
 *
 *     // this will create a high resolution sphere with a 2mm radius
 *     sphere(2, $fn=100);
 *
 *     // will also create a 2mm high resolution sphere but this one
 *     // does not have as many small triangles on the poles of the sphere
 *     sphere(2, $fa=5, $fs=0.1);
 *
 * @param number_r Radius of the sphere. The resolution of the sphere will be based on the size of the sphere and the $fa, $fs and $fn variables.
 * @param number_d Default is undef. Diameter of the sphere.
 * @param number_$fn Default 0. Fixed number of fragments in 360 degrees. Values of 3 or more override $fa and $fs.
 * @param number_$fa Default 12. Minimum angle (in degrees) of each fragment.
 * @param number_$fs Default 2. Minimum circumferential length of each fragment.
 */
module sphere(r = number_r, d = number_d, $fn = number_$fn, $fa = number_$fa, $fs = number_$fs){}