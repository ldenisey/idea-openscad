render()
color("red")
rotate([0, 45, 0])
translate([-a - 20, -20, 20])
cube([10, 10, 10]);

translate([10, 10, 10]) {
    color("green")
    sphere(r = 10);
}
