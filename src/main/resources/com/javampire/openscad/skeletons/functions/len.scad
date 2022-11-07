/**
 * Mathematical length function.
 *
 * Usage examples:
 *
 *   str1="abcdef"; len_str1=len(str1);
 *   echo(str1,len_str1); // ECHO: "abcdef", 6
 *
 *   a=6; len_a=len(a);
 *   echo(a,len_a); // ECHO: 6, undef
 *
 *   array1=[1,2,3,4,5,6,7,8]; len_array1=len(array1);
 *   echo(array1,len_array1); // ECHO: [1, 2, 3, 4, 5, 6, 7, 8], 8
 *
 *   array2=[[0,0],[0,1],[1,0],[1,1]]; len_array2=len(array2);
 *   echo(array2,len_array2); // ECHO: [[0, 0], [0, 1], [1, 0], [1, 1]], 4
 *
 *   len_array2_2=len(array2[2]);
 *   echo(array2[2],len_array2_2); // ECHO: [1, 0], 2
 *
 * This function allows (e.g.) the parsing of an array, a vector or a string.
 *
 * Usage examples:
 *
 *   str2="4711";
 *   for (i=[0:len(str2)-1])
 *	     echo(str("digit ",i+1,"  :  ",str2[i]));
 *
 * Results:
 *
 *   ECHO: "digit 1  :  4"
 *   ECHO: "digit 2  :  7"
 *   ECHO: "digit 3  :  1"
 *   ECHO: "digit 4  :  1"
 *
 * Note that the len() function is not defined when a simple variable is passed
 * as the parameter.
 *
 * This is useful when handling parameters to a module, similar to how shapes can be
 * defined as a single number, or as an [x,y,z] vector; i.e. cube(5) or cube([5,5,5])
 *
 * For example:
 *
 *   module doIt(size) {
 *	   if (len(size) == undef) {
 *		 // size is a number, use it for x,y & z. (or could be undef)
 *		 do([size,size,size]);
 *	   } else {
 *		 // size is a vector, (could be a string but that would be stupid)
 *		 do(size);
 *	   }
 *   }
 *
 *   doIt(5);	// equivalent to [5,5,5]
 *   doIt([5,5,5]);	// similar to cube(5) v's cube([5,5,5])
 *
 *
 * @param value A vector, array or string value.
 * @return Length of an array, a vector or a string parameter.
 */
function len(value) = ();