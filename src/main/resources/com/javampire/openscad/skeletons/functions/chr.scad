/**
 * Convert numbers to a string containing character with the corresponding code.
 * OpenSCAD uses Unicode, so the number is interpreted as Unicode code point.
 * Numbers outside the valid code point range will produce an empty string.
 *
 * Note:
 *     When used with echo() the output to the console for character codes greater than 127 is platform dependent.
 *
 * Usage examples:
 *
 *     echo(chr(65), chr(97));      // ECHO: "A", "a"
 *     echo(chr(65, 97));           // ECHO: "Aa"
 *     echo(chr([66, 98]));         // ECHO: "Bb"
 *     echo(chr([97 : 2 : 102]));   // ECHO: "ace"
 *     echo(chr(-3));               // ECHO: ""
 *     echo(chr(9786), chr(9788));  // ECHO: "☺", "☼"
 *     echo(len(chr(9788)));        // ECHO: 1
 *
 * @param value Number : Convert one code point to a string of length 1 (number of bytes depending on UTF-8 encoding) if the code point is valid.
                Vector : Convert all code points given in the argument vector to a string.
                Range : Convert all code points produced by the range argument to a string.
 * @return String result of converting the code point(s) to characters.
 * @since 2015.03
 */
function chr(value) = ();