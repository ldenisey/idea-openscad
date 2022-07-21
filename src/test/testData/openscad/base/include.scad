include <assign.scad>

module vase1() {
    include <modules/vase.scad>
    vase_h = 20;
    vase_h = 15;

    vase();
}

module vase2() {
    include <modules/vase.scad>
    vase_h = 10;
    vase_h = 15;

    translate([30, 0, 0])
    vase();
}

vase1();
vase2();