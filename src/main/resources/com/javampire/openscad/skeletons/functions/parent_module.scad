/**
 * $parent_modules contains the number of modules in the instantiation stack.
 * parent_module(i) returns the name of the module i levels above the current
 * module in the instantiation stack. The stack is independent of where the
 * modules are defined. It's where they're instantiated that counts. This can
 * be used to e.g. build BOMs.
 *
 * Example:
 *
 *     module top() {
 *       children();
 *     }
 *     module middle() {
 *       children();
 *     }
 *     top() middle() echo(parent_module(0));  // prints "middle"
 *     top() middle() echo(parent_module(1));  // prints "top"
 *
 * @param number Number of levels to go up the instantiation stack.
 * @return Name of the i-th parent module.
 */
function parent_module(number) = ();