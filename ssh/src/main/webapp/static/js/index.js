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