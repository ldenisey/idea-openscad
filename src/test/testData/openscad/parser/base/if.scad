if (b == a)  cube(4);
if (b < a) {cube(4); cylinder(6);}
if (b && a) {cube(4); cylinder(6);}
if (b != a)  cube(4); else cylinder(3);
if (b) {cube(4); cylinder(6);} else {cylinder(10, 5, 5);}
if (!true) {cube(4); cylinder(6);} else  cylinder(10, 5, 5);
if (x > y)   cube(1, center = false); else {cube(size = 2, center = true);}
if (a == 4) {} else  echo("a is not 4");
if ((b < 5) && (a > 8)) {cube(4);} else {cylinder(3);}
if (b < 5 && a > 8)       cube(4); else  cylinder(3);

if ((k < 8) && (m > 1)) cube(10);
else if (y == 6) {sphere(6);cube(10);}
else if (y == 7)    color("blue")sphere(5);
else if (k + m != 8) {cylinder(15, 5, 0);sphere(8);}
else             color("green") {cylinder(12, 5, 0);sphere(8);}

list = [for (a = [1 : 8]) if (a % 2 == 0) a];
function cat(L1, L2) = [for (L = [L1, L2], a = L) a];

echo(cat([1, 2, 3], [4, 5])); //concatenates two OpenSCAD lists [1,2,3] and [4,5], giving [1, 2, 3, 4, 5]
echo(list); // ECHO: [2, 4, 6, 8]

list = [- 10:5];
echo([for (n = list) if (n % 2 == 0 || n >= 0) n % 2 == 0 ? n / 2 : n]);

echo([for (a = [- 3:5]) if (a % 2 == 0) [a, a / 2] else if (a > 0) [a, a]]);
// ECHO: [[-2, -1], [0, 0], [1, 1], [2, 1], [3, 3], [4, 2], [5, 5]];

echo([for (a = [- 3:5]) if (a % 2 == 0 || (a % 2 != 0 && a > 0)) a % 2 == 0 ? [a, a / 2] : [a, a]]);
// ECHO: [[-2, -1], [0, 0], [1, 1], [2, 1], [3, 3], [4, 2], [5, 5]];

// even numbers are dropped, multiples of 4 are substituted by -1
echo([for (i = [0:10]) if (i % 2 == 0) (if (i % 4 == 0) - 1) else i]);
// ECHO: [-1, 1, 3, -1, 5, 7, -1, 9]

// odd numbers are dropped, multiples of 4 are substituted by -1
echo([for (i = [0:10]) if (i % 2 == 0) if (i % 4 == 0) - 1 else i]);
// ECHO: [-1, 2, -1, 6, -1, 10]
