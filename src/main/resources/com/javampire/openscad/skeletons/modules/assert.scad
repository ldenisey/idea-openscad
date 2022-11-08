/**
 * Assert evaluates a logical expression. If the expression evaluates to false, the
 * generation of the preview/render is stopped with an error. A string representation
 * of the expression and, if given, the message is output to the console.
 *
 * [Note: Requires version nightly build]
 *
 * Examples
 *
 * The simplest example is a simple assert(false);, e.g. in a file named assert_example1.scad.
 *
 *     cube();
 *     assert(false);
 *     sphere();
 *
 *     // ERROR: Assertion 'false' failed in file assert_example1.scad, line 2
 *
 * This example has little use, but the simple assert(false);
 * can be used in code sections that should be unreachable.
 *
 * Checking parameters
 *
 * A useful example is checking the validity of input parameters:
 *
 *     module row(cnt = 3){
 *         // Count has to be a positive integer greater 0
 *         assert(cnt > 0);
 *         for (i = [1 : cnt]) {
 *             translate([i * 2, 0, 0]) sphere();
 *         }
 *     }
 *
 *     row(0);
 *
 *     // ERROR: Assertion '(cnt > 0)' failed in file assert_example2.scad, line 3
 *
 * Adding message
 *
 * When writing a library, it could be useful to output additional
 * information to the user in case of an failed assertion.
 *
 *     module row(cnt = 3){
 *         assert(cnt > 0, "Count has to be a positive integer greater 0");
 *         for(i = [1 : cnt]) {
 *             translate([i * 2, 0, 0]) sphere();
 *         }
 *     }
 *
 *     row(0);
 *
 *     // ERROR: Assertion '(cnt > 0)': "Count has to be a positive integer greater 0" failed in file assert_example3.scad, line 2
 *
 * @param boolean The expression to be evaluated as check for the assertion.
 * @param string Optional message to be output in case the assertion failed.
 */
module assert(boolean, string = None){}