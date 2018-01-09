/**
 * Created by heyanjing on 2018/1/8 16:52.
 */
var Globle = {
    keyValueStr: function (key, valueOrFunction) {
        var value = jQuery.isFunction(valueOrFunction) ? valueOrFunction() : valueOrFunction;
        return key + "=" + value;
    },
    buildParams: function (prefix, obj, result) {
        var me = this;
        if (Array.isArray(obj)) {
            $.each(obj, function (i, v) {
                me.buildParams(prefix + "[" + ( typeof v === "object" && v != null ? i : "" ) + "]", v, result);
            });
        } else if (jQuery.type(obj) === "object") {

            for (name in obj) {
                me.buildParams(prefix + "[" + name + "]", obj[name], result);
            }
        } else {
            var temp = me.keyValueStr(prefix, obj);
            result.push(temp.replace(/\[(\D+)]/, '.$1'));
        }
    }
}
