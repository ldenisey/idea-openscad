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