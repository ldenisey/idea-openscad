vector1 = [1, 2, 3]; vector2 = [4]; vector3 = [5, 6];
new_vector = concat(vector1, vector2, vector3); // [1,2,3,4,5,6]

string_vector = concat("abc", "def");                 // ["abc", "def"]
one_string = str(string_vector[0], string_vector[1]); // "abcdef"