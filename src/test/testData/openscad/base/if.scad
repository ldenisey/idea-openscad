if (b==a)  cube(4);
if (b<a)  {cube(4); cylinder(6);}
if (b&&a) {cube(4); cylinder(6);}
if (b!=a)  cube(4); else cylinder(3);
if (b)    {cube(4); cylinder(6);} else {cylinder(10,5,5);}
if (!true){cube(4); cylinder(6);} else  cylinder(10,5,5);
if (x>y)   cube(1, center=false); else {cube(size = 2, center = true);}
if (a==4) {}                      else  echo("a is not 4");
if ((b<5)&&(a>8))  {cube(4);}     else {cylinder(3);}
if (b<5&&a>8)       cube(4);      else  cylinder(3);

if((k<8)&&(m>1)) cube(10);
else if(y==6)   {sphere(6);cube(10);}
else if(y==7)    color("blue")sphere(5);
else if(k+m!=8) {cylinder(15,5,0);sphere(8);}
else             color("green"){cylinder(12,5,0);sphere(8);}

