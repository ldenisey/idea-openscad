/**
 * The is_function check works only for expressions, so it can be applied to function literals or variables containing
 * functions. It does not work with built-in functions or normal function definitions.
 *
 * Usage example:
 *   echo(is_function(function(x) x*x)); // ECHO: true
 *
 *   func = function(x) x+x;
 *   echo(is_function(func)); // ECHO: true
 *
 *   function f(x) = x;
 *   echo(is_function(f)); // WARNING: Ignoring unknown variable 'f' / ECHO: false
 *
 * @param any Any type.
 * @return True or false.
 * @since 2021.01
 */
function is_function(any) = ();