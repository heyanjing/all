/**
 * Created by heyanjing on 2017/12/18 8:38.
 */
var arr = [];
arr.push("a");
arr.push("b");
arr.push("c");
$.ajax({
    type: 'post',
    dataType: 'json',
    url: CTX + "/list",
    data: {
        params: arr
    },
    success: function (result) {

    },
    error: function () {

    }
});
var persons = [], result = [];
for (var i = 0; i < 3; i++) {
    var obj = {};
    obj.name = "name" + i;
    obj.age = i;
    obj.birthday="1989-09-19";
    persons.push(obj);
}
Globle.buildParams("persons", persons, result);
$.ajax({
    type: 'post',
    dataType: 'json',
    url: CTX + "/persons",
    data: result.join("&"),
    success: function (result) {

    },
    error: function () {

    }
});
var map = {};
map.a = "a";
map.b = "b";
map.c = "c";
$.ajax({
    type: 'post',
    dataType: 'json',
    url: CTX + "/map",
    data: map,
    success: function (result) {

    },
    error: function () {

    }
});
var str="persons[0][name]=何彦静";

console.log(str);
console.log(str.replace(/\[([a-zA-Z]+)]/gi, function (str,a,b,c,e,f,g) {
    // console.log(str)//[name]
    // console.log(a)// name
    // console.log(b)// 10
    // console.log(c)// persons[0][name][age]=何彦静
    return "."+a;
}));
console.log(str.replace(/\[([a-zA-Z]+)]/gi,".$1"));