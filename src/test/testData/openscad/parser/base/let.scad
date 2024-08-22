echo(let(a = 135, s = sin(a), c = cos(a)) [s, c]); // ECHO: [0.707107, -0.707107]

for (i = [10:50])
{
    let (angle = i * 360 / 20, r = i * 2, distance = r * 5)
    {
        rotate(angle, [1, 0, 0])
            translate([0, distance, 0])
            sphere(r = r);
    }
}

list = [for (a = [1 : 4]) let (b = a * a, c = 2 * b) [a, b, c]];
echo(list); // ECHO: [[1, 1, 2], [2, 4, 8], [3, 9, 18], [4, 16, 32]]

function quicksort(arr) = !(len(arr) > 0) ? [] : let(
    pivot = arr[floor(len(arr) / 2)],
    lesser = [for (y = arr) if (y < pivot) y],
    equal = [for (y = arr) if (y == pivot) y],
    greater = [for (y = arr) if (y > pivot) y]
) concat(quicksort(lesser), equal, quicksort(greater));

geom = [0, 1, 2, [0, 1], [0, 1, 2], quicksort];
for (i = [10:50])
{
    let(
        size=geom[1], size2=geom[2],
        shift=point2d(geom[3]), axis=point3d(geom[4]),
        override = geom[5]([2, 3, 4])
    )
    cylinder(h=size, r=size2);
}
