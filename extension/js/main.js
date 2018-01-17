$(function () {
    tb();
});
function tb() {
    var imgs = document.getElementsByTagName('img');
    console.log(imgs);
    var img=imgs[0];
    var canvas1 = document.createElement("canvas");
    canvas1.style.backgroundColor = "cornsilk";
    canvas1.height = img.height;
    canvas1.width = img.width;
    var ctx1 = canvas1.getContext("2d");
    ctx1.drawImage(img, 0, 0, img.width, img.height);
    console.log(canvas1.toDataURL());
    if (canvas1.toDataURL()) {
        $.ajax({
            type: 'post',
            dataType: 'json',
            url: "http://www.he.com:8080/ssh/code/base64Img",
            data: {
                base64Img: canvas1.toDataURL()
            },
            success: function (result) {

            }
            ,
            error: function () {

            }
        });
    }
}

function self() {
    var container = document.getElementById('container');
    var img = document.getElementById('img1');
    var canvas1 = document.createElement("canvas");
    canvas1.style.backgroundColor = "cornsilk";
    canvas1.height = img.height;
    canvas1.width = img.width;
    var ctx1 = canvas1.getContext("2d");
    ctx1.drawImage(img, 0, 0, img.width, img.height);
    console.log(canvas1.toDataURL());
    if (canvas1.toDataURL()) {
        $.ajax({
            type: 'post',
            dataType: 'json',
            url: "http://www.he.com:8080/ssh/code/base64Img",
            data: {
                base64Img: canvas1.toDataURL()
            },
            success: function (result) {

            }
            ,
            error: function () {

            }
        });
    }
}