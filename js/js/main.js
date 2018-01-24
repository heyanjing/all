$(function () {
    // 基本类型理解();
    // 函数Call方法();
    // 原型prototype();

    // var module = window.闭包({
    //     opt1: "opt11",
    //     opt2: "opt22",
    //     opt3: "opt33"
    // });
    // module.fun1()
    // module.fun2()
    // module.fun3()

    // 原型链继承()

    多线程();


});

function 多线程() {
    var worker = new Worker("../work.js");
    worker.onmessage = function (e) {
        console.log(e);
    }
    worker.postMessage({
        a: 1,
        b: "xx"
    });
}


function 原型链继承() {
    function Supper(name, age) {
        this.name = name;
        this.age = age;
    }

    Supper.prototype.setName = function (name) {
        this.name = name;
    }
    Supper.prototype.setAge = function (age) {
        this.age = age;
    }
    function Sub(name, age, address) {
        Supper.call(this, name, age);//为了得到属性
        this.address = address;
    }

    Sub.prototype = new Supper();//为了得到supper的方法
    Sub.prototype.constructor = Sub;//对constructor的修正
    Sub.prototype.setAddress = function (address) {
        this.address = address;
    }
    var sub = new Sub("sub", 11, "sub address");
    console.log(sub);
    sub.setName("new sub")
    sub.setAge(22)
    sub.setAddress("new sub address")
    console.log(sub);
}

/**
 *
 * 内部函数引用外部函数的变量，变量就会形成闭包
 */
function 闭包(options) {
    var defaultOptions = {
        opt1: "opt1",
        opt2: "opt2",
        opt3: "opt3"
    }

    function fun1() {
        console.log(options.opt1);
    }

    function fun2() {
        console.log(options.opt1);
    }

    function fun3() {
        console.log(options.opt3);
    }

    return {
        fun1: fun1,
        fun2: fun2,
        fun3: fun3
    }
}
/**
 * 每个函数都有一个prototype属性,他默认是一个Object实例,称之为显示原型对象
 * 显示原型对象中有一个constructor属性,他指向构造函数对象本身
 * 函数的每个实例对象都有一个__proto__属性,称之为隐式原型
 * 实例对象的隐式原型值等于构造函数的显示原型
 */
function 原型prototype() {
    function Fun() {

    }

    console.log(Fun.prototype);
    console.log(Fun.prototype.constructor, Fun.prototype.constructor === Fun);
    //给原型添加属性
    Fun.prototype.方法1 = function () {
        console.log("执行方法1");
    }
    console.log(Fun.prototype);
    var fun = new Fun();
    fun.方法1();
    console.log(fun);
    console.log(fun.__proto__, fun.__proto__ === Fun.prototype);

    console.log(Function.prototype === Function.__proto__);
    console.log(Object.prototype.__proto__ === null);
}

/**
 * iife 立即执行函数表达式
 */
(function () {
    console.log("....");
})();

/**
 *函数中的this，指的是第一个参数对象
 */
function 函数Call方法() {
    function fn(a, b) {
        this.a = a;
        this.b = b;
    }

    var obj = {};
    fn.call(obj, "name", 10);//相当于obj.fn("name",10);  fn中的this表示为obj
    console.log(obj);
}


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
function 基本类型理解() {
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
