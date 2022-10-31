/**
 * Calculates the cross product of two vectors in 3D space.
 *
 * Using invalid input parameters (e.g. vectors with a length different from 3 or other types) will produce an undefined result.
 *
 * Usage examples:
 *
 *    echo(cross([2, 3, 4], [5, 6, 7]));   // produces ECHO: [-3, 6, -3]
 *    echo(cross([2, 1, -3], [0, 4, 5]));  // produces ECHO: [17, -10, 8]
 *    echo(cross([2, 3, 4], "5"));         // produces ECHO: undef
 *
 * @param vector3_1 3D vectors.
 * @param vector3_2 3D vectors.
 * @return A vector that is perpendicular to both of the input vectors.
 */
function cross(vector3_1, vector3_2) = ();