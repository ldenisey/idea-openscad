include <ColorProvider.scad>

colorProvide(); // code completion should work

subColorProvider(); // code completion should work

t2var = var1; // code completions should work

function t2function(t2arg1, t2arg2) = [t2arg1 * t2arg2];

module t2module(t2arg1, t2arg2) {
    echo(t2arg1) ; // code completions should work
    echo(t2var) ; // code completion should work
    colorProvide(); // code completion should work
    module innerModule(t2arg3) {
        echo(t2arg3) ; // code completion should work
        for (t2for = [3:5]) {
            if (t2for != 4)
                let(t2arg4 = "test") echo(t2for, t2arg4); // code completion should work for both variables
        }
        if (true)
            cube([t2, 1, 12]);

        translate([1, 0, 0]) children(0); // code completion should work
    }
}

t2module("a1", "a2"); // code completion should work


// assert and echo can be chained
x = assert(true,"xx") assert(true,"yy") echo("first") echo("second") 3+3;
