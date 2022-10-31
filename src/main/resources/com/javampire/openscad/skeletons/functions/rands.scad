/**
 * Random number generator. Generates a constant vector of pseudo random numbers,
 * much like an array. The numbers are doubles not integers. When generating only
 * one number, you still call it with variable[0]
 *
 * Usage examples:
 *
 *      // get a single number
 *      single_rand = rands(0,10,1)[0];
 *      echo(single_rand);
 *
 *      // get a vector of 4 numbers
 *      seed=42;
 *      random_vect=rands(5,15,4,seed);
 *      echo( "Random Vector: ",random_vect);
 *      sphere(r=5);
 *      for(i=[0:3]) {
 *        rotate(360*i/4) {
 *          translate([10+random_vect[i],0,0])
 *            sphere(r=random_vect[i]/2);
 *        }
 *      }
 *      // ECHO: "Random Vector: ", [8.7454, 12.9654, 14.5071, 6.83435]
 *
 * @param number_min Minimum value of random number range.
 * @param number_max Maximum value of random number range.
 * @param number_count Number of random numbers to return as a vector.
 * @param number_seed Optional. Seed value for random number generator for repeatable results. On versions before late 2015, number_seed gets rounded to the nearest integer
 * @return Constant vector of pseudo random numbers.
 */
function rands(number_min, number_max, number_count, number_seed) = ();