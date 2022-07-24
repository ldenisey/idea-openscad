// Without using "each", a nested list is generated
echo([for (a = [1 : 4]) [a, a * a]]);
// ECHO: [[1, 1], [2, 4], [3, 9], [4, 16]]

// Adding "each" unwraps the inner list, producing a flat list as result
echo([for (a = [1 : 4]) each [a, a * a]]);
// ECHO: [1, 1, 2, 4, 3, 9, 4, 16]

A = [- 2, each [1:2:5], each [6:- 2:0], - 1];
echo(A);
// ECHO: [-2, 1, 3, 5, 6, 4, 2, 0, -1]
echo([for (a = A) 2 * a]);
// ECHO: [-4, 2, 6, 10, 12, 8, 4, 0, -2]
