/**
 * Look up value in table, and linearly interpolate if there's no exact match.
 * The first argument is the key to look up the value for. The second is the
 * lookup table (a vector of key-value pairs).
 *
 * Notes:
 *     There is a bug where out-of-range keys will return the first value in the list.
 *     Newer versions of Openscad should use the top or bottom end of the table as
 *     appropriate instead.
 *
 * Usage example:
 *
 *    Will create a sort of 3D chart made out of cylinders of different height.
 *
 *    function get_cylinder_h(p) = lookup(p, [
 *      [ -200, 5 ],
 *      [ -50, 20 ],
 *      [ -20, 18 ],
 *      [ +80, 25 ],
 *      [ +150, 2 ]
 *    ]);
 *
 *    for (i = [-100:5:+100]) {
 *      // echo(i, get_cylinder_h(i));
 *      translate([ i, 0, -30 ]) cylinder(r1 = 6, r2 = 2, h = get_cylinder_h(i)*3);
 *    }
 *
 * @param key A lookup key.
 * @param lookup_table <key, value> array.
 * @return Interpolated value based on the given key and lookup table.
 */
function lookup(key, lookup_table) = ();