$(function () {
    test();
});
/**
 * 五个基本类型:字符串,数值,布尔,undefined,null
 * 三个引用类型:对象,函数,数组
 *
 * typeof 返回类型的字符串格式
 * instanceof 判断是不是引用类型的实例
 *
 *
 * ===可以判断undefined和null
 * typeof 可以判断 字符串,数值,布尔
 */
function test() {
    console.log("typeof 判断基本类型");
    console.log("typeof 判断基本类型");
    var a = "a";
    console.log(typeof a, typeof a === "string");
    a = 1;
    console.log(typeof a, typeof a === "number");
    a = true;
    console.log(typeof a, typeof a === "boolean");
    a = undefined;
    console.log(typeof a, typeof a === "undefined", a === undefined);
    a = null;
    console.log(typeof a, typeof a === "object", a === null);


    console.log("instanceof 判断引用类型");
    console.log("instanceof 判断引用类型");
    a = {};
    console.log(a instanceof Object, typeof a);
    a = [];
    console.log(a instanceof Array, typeof a);
    a = $.noop;
    console.log(a instanceof Function, typeof a);
}
