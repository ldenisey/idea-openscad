for (a = [3:5])echo(a);     // 3 4 5
for (a = [3:0.5:5])echo(a); // 3 3.5 4 4.5 5
x1 = 2;
for (a = [x1, PI, 1, 99]) {echo(a);} // 2 3.14159 1 99
for (a = [[1, 2], 6, "s", [[3, 4], [5, 6]]])echo(a);  // [1,2] 6 "s" [[3,4],[5,6]]

color_vec = ["black", "red", "blue", "green", "pink", "purple"];
for (x = [- 20:10:20])
for (y = [0:4])color(color_vec[y])
    for (z = [0, 4, 10])
    {translate([x, y * 5 - 10, z])cube();}

color_vec = ["black", "red", "blue", "green", "pink", "purple"];
for (x = [- 20:10:20],
    y = [0:4],
    z = [0, 4, 10])
translate([x, y * 5 - 10, z]) {color(color_vec[y])cube();}

for (i = [[[0, 0, 0], 20],
        [[10, 12, 10], 50],
        [[20, 24, 20], 70],
        [[30, 36, 30], 10],
        [[20, 48, 40], 30],
        [[10, 60, 50], 40]])
{
    translate([i[0][0], 2 * i[0][1], 0])
        cube([10, 15, i[1]]);
}

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

// nested loop using multiple variables
flat_result1 = [for (a = [0 : 2], b = [0 : 2]) a == b ? 1 : 0];
echo(flat_result1); // ECHO: [1, 0, 0, 0, 1, 0, 0, 0, 1]

// nested loop using multiple for elements
flat_result2 = [for (a = [0 : 2]) for (b = [0 : 2])  a == b ? 1 : 0];
echo(flat_result2); // ECHO: [1, 0, 0, 0, 1, 0, 0, 0, 1]

// nested loop to generate a bi-dimensional matrix
identity_matrix = [for (a = [0 : 2]) [for (b = [0 : 2]) a == b ? 1 : 0]];
echo(identity_matrix); // ECHO: [[1, 0, 0], [0, 1, 0], [0, 0, 1]]

// use seed in rands() to get reproducible results
unsorted = [for (a = rands(0, 10, 6, 3)) ceil(a)];
echo(unsorted); // ECHO: [6, 1, 8, 9, 3, 2]
echo(quicksort(unsorted)); // ECHO: [1, 2, 3, 6, 8, 9]