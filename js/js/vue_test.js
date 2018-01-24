$(function () {
    var app = new Vue({
        el: '#app',
        data: {
            message: 'Hello Vue.js!'
        }
    })
    setTimeout(function () {
        app.message="sss";
    },10000)

});

