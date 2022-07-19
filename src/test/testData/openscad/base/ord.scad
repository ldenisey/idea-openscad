echo(ord("a"));
// ECHO: 97

echo(ord("BCD"));
// ECHO: 66

echo([for (c = "Hello! 🙂") ord(c)]);
// ECHO: [72, 101, 108, 108, 111, 33, 32, 128578]

txt="1";
echo(ord(txt)-48,txt);
// ECHO: 1,"1" // only converts 1 character

echo(ord(str("txt : ", txt)));