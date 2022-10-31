/**
 * The search() function is a general-purpose function to find one or more
 * (or all) occurrences of a value or list of values in a vector, string or
 * more complex list-of-list construct.
 *
 * Usage
 *     search( match_value , string_or_vector [, num_returns_per_match [, index_col_num ] ] );
 *
 * See example023.scad included with OpenSCAD for a renderable example.
 *
 * Example 1:
 *     search("a","abcdabcd"); -> [0]
 *
 * Example 2:
 *     search("e","abcdabcd"); -> []
 *
 * Example 3:
 *     search("a","abcdabcd",0); -> [[0, 4]]
 *
 * Example 4:
 *     data=[ ["a",1],["b",2],["c",3],["d",4],["a",5],["b",6],["c",7],["d",8],["e",9] ];
 *     search("a", data, num_returns_per_match=0); -> [[0, 4]] (see also Example 6 below)
 *
 * Example 5:
 *     //Search on different column; return Index values
 *     data= [ ["a",1],["b",2],["c",3],["d",4],["a",5],["b",6],["c",7],["d",8],["e",3] ];
 *     search(3, data, num_returns_per_match=0, index_col_num=1);
 * Returns:
 *     [2, 8]
 *
 * Example 6:
 *     // Search on list of values, return all matches per search vector element.
 *     data= [ ["a",1],["b",2],["c",3],["d",4],["a",5],["b",6],["c",7],["d",8],["e",9] ];
 *     search("abc", data, num_returns_per_match=0);
 * Returns:
 *     [[0, 4], [1, 5], [2, 6]]
 *
 * Example 7:
 *     // Search on list of values, return first match per search vector element;
 *     // special case return vector.
 *     data = [ ["a",1],["b",2],["c",3],["d",4],["a",5],["b",6],["c",7],["d",8],["e",9] ];
 *     search("abc", data, num_returns_per_match=1);
 * Returns:
 *     [0, 1, 2]
 *
 * Example 8:
 *     // Search on list of values, return first two matches per search vector element;
 *     // vector of vectors.
 *     data= [ ["a",1],["b",2],["c",3],["d",4],["a",5],["b",6],["c",7],["d",8],["e",9] ];
 *     search("abce", data, num_returns_per_match=2);
 * Returns:
 *     [[0, 4], [1, 5], [2, 6], [8]]
 *
 * Example 9:
 *     // Search on list of strings
 *     lTable2=[ ["cat",1],["b",2],["c",3],["dog",4],["a",5],["b",6],["c",7],["d",8],["e",9],["apple",10],["a",11] ];
 *     lSearch2=["b","zzz","a","c","apple","dog"];
 *     l2=search(lSearch2,lTable2);
 *     echo(str("Default list string search (",lSearch2,"): ",l2));
 * Returns
 *     ECHO: "Default list string search (["b", "zzz", "a", "c", "apple", "dog"]): [1, [], 4, 2, 9, 3]"
 *
 * Getting the right results
 *     // workout which vectors get the results
 *     v=[ ["O",2],["p",3],["e",9],["n",4],["S",5],["C",6],["A",7],["D",8] ];
 *     //
 *     echo(v[0]);                        // -> ["O",2]
 *     echo(v[1]);                        // -> ["p",3]
 *     echo(v[1][0],v[1][1]);             // -> "p",3
 *     echo(search("p",v));               // find "p" -> [1]
 *     echo(search("p",v)[0]);            // -> 1
 *     echo(search(9,v,0,1));             // find  9  -> [2]
 *     echo(v[search(9,v,0,1)[0]]);       // -> ["e",9]
 *     echo(v[search(9,v,0,1)[0]][0]);    // -> "e"
 *     echo(v[search(9,v,0,1)[0]][1]);    // -> 9
 *     echo(v[search("p",v,1,0)[0]][1]);  // -> 3
 *     echo(v[search("p",v,1,0)[0]][0]);  // -> "p"
 *     echo(v[search("d",v,1,0)[0]][0]);  // "d" not found -> undef
 *     echo(v[search("D",v,1,0)[0]][1]);  // -> 8
 *
 * @param match_value Can be a single value or vector of values.
 *  Strings are treated as vectors-of-characters to iterate over; the search function does not search for substrings.
 *  Note: If match_value is a vector of strings, search will look for exact string matches. See Example 9 below.
 * @param string_or_vector The string or vector to search for matches.
 * @param num_returns_per_match Default 1. By default, search only looks for one match per element of match_value
 *  to return as a list of indices.
 *  If num_returns_per_match > 1, search returns a list of lists of up to
 *  num_returns_per_match index values for each element of match_value.
 *  See Example 8 below.
 *  If num_returns_per_match = 0, search returns a list of lists of all
 *  matching index values for each element of match_value.
 *  See Example 6 below.
 * @param index_col_num Default 0. When string_or_vector is a vector-of-vectors, multidimensional table or
 *  more complex list-of-lists construct, the match_value may not be found
 *  in the first (index_col_num = 0) column.
 *  See Example 5 below for a simple usage example.
 * @return Vector of search results.
 */
function search(match_value , string_or_vector, num_returns_per_match, index_col_num) = ();