/*
   Multi-line "C-style" comment
   can span multiple lines.
 * Star chars are allowed, treated as any other char.
*/

/* * /


 */

// https://github.com/ldenisey/idea-openscad/issues/72
/* **/

// Single line comment can be chained and folded together
// Customizer parameters
/* [Drop down box:] */
// combo box for number
Numbers=2; // [0, 1, 2, 3]

// combo box for string
Strings="foo"; // [foo, bar, baz]

//labeled combo box for numbers
Labeled_values=10; // [10:L, 20:M, 30:XL]

//labeled combo box for string
Labeled_value="S"; // [S:Small, M:Medium, L:Large]

/*[ Slider ]*/
// slider widget for number
slider =34; // [10:100]

//step slider for number
stepSlider=2; //[0:5:100]

/* [Checkbox] */

//description
Variable = true;

/*[Spinbox] */

// spinbox with step size 1
Spinbox = 5;

// spinbox with step size 0.5
Spinbox= 5.5; // .5

/* [Textbox]*/

//Text box for vector with more than 4 elements
Vector6=[12,34,44,43,23,23];

// Text box for string
String="hello";

/* [Special vector] */
//Text box for vector with less than or equal to 4 elements
Vector1=[12]; //[0:2:50]
Vector2=[12,34]; //[0:2:50]
Vector3=[12,34,46]; //[0:2:50]
Vector4=[12,34,46,24]; //[0:2:50]

/**
 * "Doc" comment.
   Stars are optional in document comments.
 */
module foo(){} // foo module comment
// Should not be parsed as a block

/**
*/
function bar()=1;
