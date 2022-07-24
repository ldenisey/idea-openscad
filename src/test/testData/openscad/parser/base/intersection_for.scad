intersection_for(n = [1 : 6])
{
    rotate([0, 0, n * 60])
        {
            translate([5, 0, 0])
                sphere(r = 12);
        }
}

intersection_for(i = [[0, 0, 0],
        [10, 20, 300],
        [200, 40, 57],
        [20, 88, 57]])
{
    rotate(i)
        cube([100, 20, 20], center = true);
}

