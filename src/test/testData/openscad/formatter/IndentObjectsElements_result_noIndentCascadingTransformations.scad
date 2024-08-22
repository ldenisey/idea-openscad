part_to_show = "a"; // ["a","b","c"]
/*[tab 1]*/
part_to_show2 = "a"; // ["a","b","c","long name 1","long name 2","long name 3","long name 4","long name 5","long name 6","long name 7","long name 8"]

module __end_of_customizer_variables() {}

a = 1;
render()
color("red")
rotate([0, 45, 0])
translate([-a - 20, -20, 20])
cube([10, 10, 10]);

translate([10, 10, 10]) {
    color("green")
    sphere(r=10);
}

module do_action() {}
module do_another_action() {}
condition_is_true = true;

if (condition_is_true)
    // Action
    do_action();
else
    // Another action
    do_another_action();

if (condition_is_true)
    // Action
    translate([1, 2, 3]) cube([10, 10, 10]);
else
    // Another action
    color("red") sphere(r=10);

for (i = [0:3])
    translate([i * 2, 0, 0])
    cube([i, i, i]);

for (i = [0:3])
    if (condition_is_true)
        translate([i * 2, 0, 0])
        cube([i, i, i]);

if (condition_is_true)
    /* Action*/
    translate([1, 2, 3]) cube([10, 10, 10]);

module some_module_with_a_lot_of_parameters(p1=3, p2=[1, 2, 3, 4, 5, 6, 7, 8, 9, 0],
                                            p3=[1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 11, 1, 12],
                                            p4=[1, 2, 3, 4, 5, 6, 7, 8, 9, 0], p5="default")
{
}

some_module_with_a_lot_of_parameters(p1=3, p2=[1, 2, 3, 4, 5, 6, 7, 8, 9, 0], p3=[1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 11, 22],
                                     p4=[1, 2, 3, 4, 5, 6, 7, 8, 9, 0], p5="testing");

long_vector = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
               0, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20,];