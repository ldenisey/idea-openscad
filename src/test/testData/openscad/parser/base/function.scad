func = function (x) x * x;
echo(func(5)); // ECHO: 25

a = 1;
selector = function (which)
    which == "add"
    ? function (x) x + x + a
    : function (x) x * x + a;

echo(selector("add"));     // ECHO: function(x) ((x + x) + a)
echo(selector("add")(5));  // ECHO: 11
echo(selector("mul"));     // ECHO: function(x) ((x * x) + a)
echo(selector("mul")(5));  // ECHO: 26

function select(vector, indices) = [for (index = indices) vector[index]];
vector1 = [[0, 0], [1, 1], [2, 2], [3, 3], [4, 4]];
selector1 = [4, 0, 3];
vector2 = select(vector1, selector1);    // [[4, 4], [0, 0], [3, 3]]
vector3 = select(vector1, [0, 2, 4, 4, 2, 0]);// [[0, 0], [2, 2], [4, 4],[4, 4], [2, 2], [0, 0]]
// range also works as indices
vector4 = select(vector1, [4:- 1:0]);    // [[4, 4], [3, 3], [2, 2], [1, 1], [0, 0]]

function cat(L1, L2) = [for (i = [0:len(L1) + len(L2) - 1])
        i < len(L1)? L1[i] : L2[i - len(L1)]] ;
echo(cat([1, 2, 3], [4, 5])); //concatenates two OpenSCAD lists [1,2,3] and [4,5], giving [1, 2, 3, 4, 5]
function cat(L1, L2) = [for (L = [L1, L2], a = L) a];