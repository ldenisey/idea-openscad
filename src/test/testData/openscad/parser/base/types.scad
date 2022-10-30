cube(5);
x = 4 + y;
rotate(40) square(5, 10);
translate([10, 5]) {circle(5); square(4);}
rotate(60) color("red") {circle(5); square(4);}
color("blue") {translate([5, 3, 0]) sphere(5); rotate([45, 0, 45]) {cylinder(10); cube([5, 6, 7]);}}

inf = 1e200 * 1e200;
nan = 0 / 0;
echo(inf, nan);

echo("The quick brown fox \tjumps \"over\" the lazy dog.\rThe quick brown fox.\nThe \\lazy\\ dog.");

r1 = [0:10];
r2 = [0.5:2.5:20];
echo(r1); // ECHO: [0: 1: 10]
echo(r2); // ECHO: [0.5: 2.5: 20]

var = 25;
xx = 1.25 * cos(50);
y = 2 * xx + var;
logic = true;
MyString = "This is a string";
a_vector = [1, 2, 3];
rr = a_vector[2];      // member of vector
range1 = [- 1.5:0.5:3]; // for() loop range
xx = [0:5];            // alternate for() loop range
p = pow(var, rr[0]);
pp = var ^ rr[0];

a = 0;
if (a == 0)
{
a = 1; //  before 2015.03 this line would generate a Compile Error
//  since 2015.03  no longer an error, but the value a=1 is confined to within the braces {}
}

echo("Variable a is ", a);                // Variable a is undef
if (a == undef) {
echo("Variable a is tested undefined"); // Variable a is tested undefined
}

// scope 1
a = 6;                // create a
echo(a, b);            //                6, undef
translate([5, 0, 0]) {// scope 1.1
a = 10;
b = 16;              // create b
echo(a, b);          //              100, 16   a=10; was overridden by later a=100;
color("blue") {// scope 1.1.1
echo(a, b);        //              100, 20
cube();
b = 20;
}                   // back to 1.1
echo(a, b);          //              100, 16
a = 100;              // override a in 1.1
}                     // back to 1
echo(a, b);            //                6, undef
color("red") {// scope 1.2
cube();
echo(a, b);          //                6, undef
}                     // back to 1
echo(a, b);            //                6, undef
//In this example, scopes 1 and 1.1 are outer scopes to 1.1.1 but 1.2 is not.

{
angle = 45;
}
rotate(angle) square(10);

a = [1, 2, 3];
a = [a, 5, b];
a = [];
a = [5.643];
a = ["a", "b", "string"];
a = [[1, r], [x, y, z, 4, 5]];
a = [3, 5, [6, 7], [[8, 9], [10, [11, 12], 13], c, "string"]];
a = [4 / 3, 6 * 1.5, cos(60)];

cube([width, depth, height]);           // optional spaces shown for clarity
translate([x, y, z])
polygon([[x0, y0], [x1, y1], [x2, y2]]);

cube([10, 15, 20]);
a1 = [1, 2, 3];
a2 = [4, 5];
a3 = [6, 7, 8, 9];
b = [a1, a2, a3];    // [ [1,2,3], [4,5], [6,7,8,9] ]  note increased nesting depth

a = e[5];           // element no 5 (sixth) at   1st nesting level
a = e[5][2];        // element 2 of element 5    2nd nesting level
a = e[5][2][0];     // element 0 of 2 of 5       3rd nesting level
a = e[5][2][0][1];  // element 1 of 0 of 2 of 5  4th nesting level

e = [[1], [], [3, 4, 5], "string", "x", [[10, 11], [12, 13, 14], [[15, 16], [17]]]];  // length 6

a = e.x;    //equivalent to e[0]
a = e.y;    //equivalent to e[1]
a = e.z;    //equivalent to e[2]

//Example which defines a 2D rotation matrix
mr = [
[cos(angle), - sin(angle)],
[sin(angle), cos(angle)]
];

steps = 50;
points = [
// first expression generating the points in the positive Y quadrant
for (a = [0 : steps]) [a, 10 * sin(a * 360 / steps) + 10],
// second expression generating the points in the negative Y quadrant
for (a = [steps : - 1 : 0]) [a, 10 * cos(a * 360 / steps) - 20],
// additional list of fixed points
[10, - 3], [3, 0], [10, 3]
];
polygon(points);

sma = 20;  // semi-minor axis
smb = 30;  // semi-major axis
polygon(
[for (a = [0 : 5 : 359]) [sma * sin(a), smb * cos(a)]]
);