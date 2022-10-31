/**
 * is_undef accepts one parameter. If the parameter is undef, this function returns true. If the parameter is not undef,
 * it returns false. When checking a variable (like `is_undef(a)`), it does the variable lookup silently, meaning that
 * is_undef(a) does not cause `WARNING: Ignoring unknown variable 'a'. `
 *
 * The alternative is code like this:
 *
 * if(a==undef){
 *     //code goes here
 * }
 * or
 *
 * b = (a==undef) ? true : false;
 * causes
 *
 * WARNING: Ignoring unknown variable 'a'.
 * is_undef also works for special variables, allowing for things like this:
 *
 * exploded = is_undef($exploded) ? 0 : $exploded; // 1 for exploded view
 *
 * @param variable Variable of any type.
 * @return True or false.
 * @since 2019.05
 */
function is_undef(variable) = ();