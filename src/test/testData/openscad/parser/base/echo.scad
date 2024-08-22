my_h = 50;
my_r = 100;
echo("This is a cylinder with h=", my_h, " and r=", my_r);
echo(my_h = my_h, my_r = my_r); // shortcut
cylinder(h = my_h, r = my_r);

a = 1.0;
b = 1.000002;
echo(a);
echo(b);

if (a == b) {//while echoed the same, the values are still distinct
    echo("a==b");
} else if (a > b) {
    echo("a>b");
} else if (a < b) {
    echo("a<b");
} else {
    echo("???");
}

c = 1000002;
d = 0.000002;
echo(c); //1e+06
echo(d); //2e-06

a = 3; b = 5;

// echo() prints values before evaluating the expression
r1 = echo(a, b) a * b; // ECHO: 3, 5

// using let it's still easy to output the result
r2 = let(r = 2 * a * b) echo(r) r; // ECHO: 30

// use echo statement for showing results
echo(r1, r2); // ECHO: 15, 30

v = [4, 7, 9, 12];
function result(x) = echo(result = x) x;
function sum(x, i = 0) = echo(str("x[", i, "]=", x[i])) result(len(x) > i ? x[i] + sum(x, i + 1) : 0);
echo("sum(v) = ", sum(v));

// Echo doesn't need to be followed by an expr in the elvis_expr
dummy = true ? echo("Is true") : 0;
// ECHO: "Is true"
echo(dummy);
// ECHO: undef

function echo_row(matstr) =
let(
    dummy=[for(row=matstr) echo(row) ]
)
dummy;

