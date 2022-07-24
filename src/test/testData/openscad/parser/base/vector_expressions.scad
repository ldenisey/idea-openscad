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

// generate a list with all values defined by a range
list1 = [for (i = [0 : 2 : 10]) i];
echo(list1); // ECHO: [0, 2, 4, 6, 8, 10]

// extract every second character of a string
str = "SomeText";
list2 = [for (i = [0 : 2 : len(str) - 1]) str[i]];
echo(list2); // ECHO: ["S", "m", "T", "x"]

// indexed list access, using function to map input values to output values
function func(x) = x < 1 ? 0 : x + func(x - 1);
input = [1, 3, 5, 8];
output = [for (a = [0 : len(input) - 1]) func(input[a])];
echo(output); // ECHO: [1, 6, 15, 36]

// iterate over an existing list
friends = ["John", "Mary", "Alice", "Bob"];
list = [for (i = friends) len(i)];
echo(list); // ECHO: [4, 4, 5, 3]

// map input list to output list
list = [for (i = [2, 3, 5, 7, 11]) i * i];
echo(list); // ECHO: [4, 9, 25, 49, 121]

// calculate Fibonacci numbers
function func(x) = x < 3 ? 1 : func(x - 1) + func(x - 2);
input = [7, 10, 12];
output = [for (a = input) func(a)];
echo(output); // ECHO: [13, 55, 144]

echo([for (c = "String") c]);
// ECHO: ["S", "t", "r", "i", "n", "g"]

echo([for (a = 0, b = 1;a < 5;a = a + 1, b = b + 2) [a, b * b]]);
// ECHO: [[0, 1], [1, 9], [2, 25], [3, 49], [4, 81]]

// Generate fibonacci sequence
echo([for (a = 0, b = 1;a < 1000;x = a + b, a = b, b = x) a]);
// ECHO: [0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987]

// Cumulative sum of values in v
function cumsum(v) = [for (a = v[0] - v[0], i = 0; i < len(v); a = a + v[i], i = i + 1) a + v[i]];
echo(cumsum([1, 2, 3, 4]));
// ECHO: [1, 3, 6, 10]
echo(cumsum([[1, 1], [2, 2], [3, 3]]));
// ECHO: [[1, 1], [3, 3], [6, 6]]

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

list = [for (a = [1 : 4]) let (b = a * a, c = 2 * b) [a, b, c]];
echo(list); // ECHO: [[1, 1, 2], [2, 4, 8], [3, 9, 18], [4, 16, 32]]

// nested loop using multiple variables
flat_result1 = [for (a = [0 : 2], b = [0 : 2]) a == b ? 1 : 0];
echo(flat_result1); // ECHO: [1, 0, 0, 0, 1, 0, 0, 0, 1]

// nested loop using multiple for elements
flat_result2 = [for (a = [0 : 2]) for (b = [0 : 2])  a == b ? 1 : 0];
echo(flat_result2); // ECHO: [1, 0, 0, 0, 1, 0, 0, 0, 1]


// nested loop to generate a bi-dimensional matrix
identity_matrix = [for (a = [0 : 2]) [for (b = [0 : 2]) a == b ? 1 : 0]];
echo(identity_matrix); // ECHO: [[1, 0, 0], [0, 1, 0], [0, 0, 1]]

sma = 20;  // semi-minor axis
smb = 30;  // semi-major axis
polygon(
    [for (a = [0 : 5 : 359]) [sma * sin(a), smb * cos(a)]]
);

function flatten(l) = [for (a = l) for (b = a) b] ;

nested_list = [[1, 2, 3], [4, 5, 6]];
echo(flatten(nested_list)); // ECHO: [1, 2, 3, 4, 5, 6]

function quicksort(arr) = !(len(arr) > 0) ? [] : let(
    pivot = arr[floor(len(arr) / 2)],
    lesser = [for (y = arr) if (y < pivot) y],
    equal = [for (y = arr) if (y == pivot) y],
    greater = [for (y = arr) if (y > pivot) y]
) concat(
quicksort(lesser), equal, quicksort(greater)
);

// use seed in rands() to get reproducible results
unsorted = [for (a = rands(0, 10, 6, 3)) ceil(a)];
echo(unsorted); // ECHO: [6, 1, 8, 9, 3, 2]
echo(quicksort(unsorted)); // ECHO: [1, 2, 3, 6, 8, 9]

function select(vector, indices) = [for (index = indices) vector[index]];

vector1 = [[0, 0], [1, 1], [2, 2], [3, 3], [4, 4]];
selector1 = [4, 0, 3];
vector2 = select(vector1, selector1);    // [[4, 4], [0, 0], [3, 3]]
vector3 = select(vector1, [0, 2, 4, 4, 2, 0]);// [[0, 0], [2, 2], [4, 4],[4, 4], [2, 2], [0, 0]]
// range also works as indices
vector4 = select(vector1, [4:- 1:0]);    // [[4, 4], [3, 3], [2, 2], [1, 1], [0, 0]]

function cat(L1, L2) = [for (i = [0:len(L1) + len(L2) - 1])
        i < len(L1)? L1[i] : L2[i - len(L1)]] ;

echo(cat([1, 2, 3], [4, 5])); //concatenates two OpenSCAD lists [1,2,3] and [4,5], giving [1, 2, 3, 4, 5]

function cat(L1, L2) = [for (L = [L1, L2], a = L) a];
