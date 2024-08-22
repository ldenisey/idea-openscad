function f2(x) =
    false ? false : assert(x,str("Failed2")) "Not okay";
echo(f2(true));

function fa2(x) =
assert(assert(true, x) true, str("in test")) false ? assert(x,str("in first part")) false : assert(x,str("in second part")) "Okay";
echo(fa2(true));

function unit(v, error=[[["ASSERT"]]]) =
assert(is_vector(v), "Invalid vector")
    norm(v)<EPSILON? (error==[[["ASSERT"]]]? assert(norm(v)>=EPSILON,"Cannot normalize a zero vector") : error) :
            v/norm(v);

echo(str("First echo"));
assert(true,str("Success"));

function fe(x) =
echo(x,str("in test")) false ? echo(x,str("in first part")) false : echo(x,str("in second part")) "Okay";
echo(f(true));


function f(x) =
    false ? false : assert(x,str("Failed")) "Okay";
echo(f(true));



function f3(x) =
    false ? false : assert(x,str("Failed3")) assert(!x,str("Failed4")) "None of the above";
echo(f3(true));
echo(f3(false));


result = false ? 1
    :  false ? 2
            :  false ? 3
                    :  assert(false, "With one expression", "and another");

assert(all_nonnegative([endcap_extent1]))
assert(all_nonnegative([endcap_extent2]))
assert(all_nonnegative([joint_extent]));
