/**
 * Created by heyanjing on 2018/1/24 14:00.
 */
onmessage = function (e) {
    console.log(e);
    postMessage({
        xx: "xxxxxxx"
    });
}
console.log(this);