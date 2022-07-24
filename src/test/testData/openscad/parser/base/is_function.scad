echo(is_function(function(x) x * x)); // ECHO: true

func = function(x) x + x;
echo(is_function(func)); // ECHO: true

function f(x) = x;
echo(is_function(f)); // WARNING: Ignoring unknown variable 'f' / ECHO: false