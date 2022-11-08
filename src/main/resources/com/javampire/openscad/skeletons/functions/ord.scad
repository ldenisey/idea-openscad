/**
 * Convert a character to a number representing the Unicode code point. If the parameter is not a string, the ord() returns undef.
 *
 * Usage examples:
 *
 *     echo(ord("a"));
 *     // ECHO: 97
 *
 *     echo(ord("BCD"));
 *     // ECHO: 66
 *
 *     echo([for (c = "Hello! 🙂") ord(c)]);
 *     // ECHO: [72, 101, 108, 108, 111, 33, 32, 128578]
 *
 *     txt="1";
 *     echo(ord(txt)-48,txt);
 *     // ECHO: 1,"1" // only converts 1 character
 *
 * @param string Convert the first character of the given string to a Unicode code point.
 * @return Unicode code point.
 * @since 2019.05
 */
function ord(string) = ();