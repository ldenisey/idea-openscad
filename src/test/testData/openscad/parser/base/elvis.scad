a = 1; b = 2; c = a == b ? 4 : 5 ;                  //  5
a = 1; b = 2; c = a == b ? "a==b" : "a!=b" ;        //  "a!=b"

TrueValue = true; FalseValue = false;
a = 5; test = a == 1;
echo(test ? TrueValue : FalseValue);       // false

L = 75; R = 2; test = (L / R) > 25;
TrueValue = [test, L, R, L / R, cos(30)];
FalseValue = [test, L, R, sin(15)];
a1 = test ? TrueValue : FalseValue ;         // [true, 75, 2, 37.5, 0.866025]

// recursion - find the sum of the values in a vector (array) by calling itself
// from the start (or s'th element) to the i'th element - remember elements are zero based
function sumv(v, i, s = 0) = (i == s ? v[i] : v[i] + sumv(v, i - 1, s));
vec = [10, 20, 30, 40];
echo("sum vec=", sumv(vec, 2, 1)); // calculates 20+30=50

// find the maximum value in a vector
function maxv(v, m = - 999999999999, i = 0) =
    (i == len(v))
    ?     m
    :     (m > v[i])
    ?    maxv(v, m, i + 1)
    :    maxv(v, v[i], i + 1);

v = [7, 3, 9, 3, 5, 6];
echo("max", maxv(v));   // ECHO: "max", 9

a = 4;
echo(a > 2 ? (a < 3 ? "1" : "2") : "3");
echo(a > 2 ? a < 3 ? "1" : "2" : "3");
echo(a > 2 ? (a < 3 ? (a < 3 ? "1" : "1") : (a < 3 ? "1" : "1")) : (a < 3 ? "1" : "1"));
echo(a > 2 ? a < 3 ? a < 3 ? "1" : "1" : a < 3 ? "1" : "1" : a < 3 ? "1" : "1");

edgetype = "profile";
offsets = edgetype == "profile"? maxv(-1,1) : [0, 0];
