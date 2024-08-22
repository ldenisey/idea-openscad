include <BOSL2/std.scad>

// square as a function
linear_extrude(height = 5)
    polygon(square([3, 4], center = true));

R1 = [square(10,center=true), square(9,center=true)];
echo(R1[1]);
// ECHO: [[4.5, -4.5], [-4.5, -4.5], [-4.5, 4.5], [4.5, 4.5]]

R2 = [square(9,center=true)];

// union as a function
assert(are_regions_equal(union(R1,R2), [square(10,center=true)]));

// union and square as modules
union()
{
    square(10,center=true);
    square(9,center=false);
}

// intersection as function
R6 = [square(9.5,center=true), square(9,center=true)];
assert(are_regions_equal(intersection(R6,R1), R6));
assert(are_regions_equal(intersection(R1,R6), R6));

// intersection as module
right(15)
color("green")
    intersection()
    {
        for (i = [0:1])
        {
            polygon(R1[i]);
            polygon(R6[i]);
        }
    }

// move, difference, and circle as functions
shape1 = move([-8,-8,0], p=circle(d=30));
shape2 = move([ 8, 8,0], p=circle(d=30));
color("blue") left(60) region(difference(shape1,shape2));

// move, difference, and circle as modules
color("orange")
    left(30)
    difference()
    {
        move([-8,-8,0]) circle(d=30);
        move([ 8, 8,0]) circle(d=30);
    }

