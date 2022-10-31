/**
 * Return a vector containing the arguments.
 * Where an argument is a vector the elements of the vector are individually added to the result vector.
 * Strings are distinct from vectors in this case.
 *
 * Usage examples:
 *
 *   echo(concat("a","b","c","d","e","f"));      // produces ECHO: ["a", "b", "c", "d", "e", "f"]
 *   echo(concat(["a","b","c"],["d","e","f"]));  // produces ECHO: ["a", "b", "c", "d", "e", "f"]
 *   echo(concat(1,2,3,4,5,6));                  // produces ECHO: [1, 2, 3, 4, 5, 6]
 *   echo(concat([ [1],[2] ], [ [3] ]));         // produces ECHO: [[1], [2], [3]]
 *   echo(concat([1,2,3],[4,5,6]));              // produces ECHO: [1, 2, 3, 4, 5, 6]
 *   echo(concat("abc","def"));                  // produces ECHO: ["abc", "def"]
 *   echo(str("abc","def"));                     // produces ECHO: "abcdef"
 *
 * @param values List of any number of values of any types.
 * @return Vector containing the arguments.
 * @since 2015.03
 */
function concat(values) = ();