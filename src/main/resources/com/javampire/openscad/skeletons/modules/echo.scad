/**
 * This function prints the contents to the compilation window (aka Console).
 * Useful for debugging code. Also see the String function str().
 *
 * Numeric values are rounded to 5 significant digits.
 *
 * The OpenSCAD console supports a subset of HTML markup language. See Qt Docs for details.
 *
 * It can be handy to use 'variable=variable' as the expression to easily label
 * the variables, see the example below.
 *
 * Usage examples:
 *
 *     my_h=50;
 *     my_r=100;
 *     echo("This is a cylinder with h=", my_h, " and r=", my_r);
 *     echo(my_h=my_h,my_r=my_r); // shortcut
 *     cylinder(h=my_h, r=my_r);
 *
 *     echo("<b>Hello</b> <i>Qt!</i>");
 *
 * Shows in the Console as:
 *
 *     ECHO: "This is a cylinder with h=", 50, " and r=", 100
 *     ECHO: my_h = 50, my_r = 100
 *     ECHO: "Hello Qt!"
 *
 * An example for the rounding:
 *
 *     a=1.0;
 *     b=1.000002;
 *     echo(a);
 *     echo(b);
 *
 *     if(a==b){ //while echoed the same, the values are still distinct
 *         echo ("a==b");
 *     }else if(a>b){
 *         echo ("a>b");
 *     }else if(a<b){
 *         echo ("a<b");
 *     }else{
 *         echo ("???");
 *     }
 *
 * Small and large Numbers:
 *
 *     c=1000002;
 *     d=0.000002;
 *     echo(c); //1e+06
 *     echo(d); //2e-06
 *
 * Working HTML examples:
 *
 *     echo("<h1>Heading</h1>");
 *     echo("<b>Bold</b> <i>italic</i> <big>big</big>");
 *     echo("i<sub>1</sub><sup>2<sup>");
 *     echo("<font color='red'>red</font> <font color='green'>green</font> <font color='blue'>blue</font>");
 *
 * Not really working HTML examples:
 *
 *     echo("<img src='http://www.openscad.org/assets/img/logo.png'></img>");
 *     echo("<a href='http://en.wikibooks.org/'>wikibooks</a>");
 *
 * Note:
 *     the Output can be copy and pasted into OpenOffice, where both the image and the link work fine.
 *
 * @param any String : String to print.
 * Boolean : Boolean to print.
 * Number : Number to print.
 * @param variable Variable to print, will be prefixed by a string mentioning the variable identifier.
 */
module echo(any, variable = variable){}