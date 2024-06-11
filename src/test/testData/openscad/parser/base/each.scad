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

// Identifiers and expressions are allowed after the each keyword
k = [1, 2, 3];
B = [0, each k, 0];
echo(B);
// ECHO: [0, 1, 2, 3, 0]
k2 = [4, 5, 6];
C = [0, each k+k2, 0];
echo(C);
// ECHO: [0, 5, 7, 9, 0]

function flatten(l) =
    !is_list(l)? l :
            [for (a=l) if (is_list(a)) (each a) else a];

y = [
        [0, 1],
    if (true) each flatten([1, 2]) else [5, 6]];
echo(y);
// ECHO: [[0, 1], 1, 2]

x = [for(i=[0:1])
        [for(k=["a", "bc", "def"])
        each if (len(k)>1) i]];
echo(x);
// ECHO: [[0, 0], [1, 1]]

