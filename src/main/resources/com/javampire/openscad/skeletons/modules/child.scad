/**
 * Use of children() allows modules to act as operators applied to any or
 * all of the objects within this module instantiation. In use, operator
 * modules do not end with a semi-colon.
 *
 * Up to release 2013.06 the now deprecated child() module was used instead.
 * This can be translated to the new children() according to the table:
 *
 * +--------------------------------------+-----------------------------+
 * |             up to 2013.06            |       2014.03 and later     |
 * +--------------------------------------+-----------------------------+
 * |  child()                             |  children(0)                |
 * +--------------------------------------+-----------------------------+
 * |  child(x)                            |  children(x)                |
 * +--------------------------------------+-----------------------------+
 * |  for (a = [0:$children-1]) child(a)  |  children([0:$children-1])  |
 * +--------------------------------------+-----------------------------+
 *
 * @deprecated Please use children() module as of release 2014.03 and later.
 */
module child(){}