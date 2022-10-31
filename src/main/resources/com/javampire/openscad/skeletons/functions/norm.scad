/**
 * Returns the euclidean norm of a vector. Note this returns the actual numeric
 * length while len returns the number of elements in the vector or array.
 *
 * Usage examples:
 *
 *     a = [1, 2, 3, 4];
 *     b = "abcd";
 *     c = [];
 *     d = "";
 *     e = [[1, 2, 3, 4], [1, 2, 3], [1, 2], [1]];
 *     echo(norm(a));     // 5.47723
 *     echo(norm(b));     // undef
 *     echo(norm(c));     // 0
 *     echo(norm(d));     // undef
 *     echo(norm(e[0]));  // 5.47723
 *     echo(norm(e[1]));  // 3.74166
 *     echo(norm(e[2]));  // 2.23607
 *     echo(norm(e[3]));  // 1
 *
 * @param vector Vector of any number of elements.
 * @return Euclidian norm of the vector.
 */
function norm(vector) = ();