(window["webpackJsonp"] = window["webpackJsonp"] || []).push([
    ["chunk-374b6c80"], {
        "503f": function (t, n, e) {},
        "5d18": function (t, n, e) {
            // t.exports = e.p + "static/img/welcome.c39845a1.png"
            t.exports = e.p + "static/img/s60d01ed1bd0d3.jpg"
        },
        "88fe": function (t, n, e) {
            "use strict";
            e("503f")
        },
        9406: function (t, n, e) {
            "use strict";
            e.r(n);
            var c = function () {
                    var t = this,
                        n = t.$createElement;
                    t._self._c;
                    return t._m(0)
                },
                i = [function () {
                    var t = this,
                        n = t.$createElement,
                        c = t._self._c || n;
                    return c("div", {
                        staticClass: "dashboard-container"
                    }, [c("img", {
                        attrs: {
                            src: e("5d18"),
                            width: "100%",
                            height: "100%"
                        }
                    })])
                }],
                o = e("b775");

            function a() {
                return Object(o["a"])({
                    url: "/index",
                    method: "get"
                })
            }
            var r = {
                    name: "Dashboard",
                    mounted: function () {
                        a().then((function (t) {
                            console.log(t)
                        }))
                    }
                },
                s = r,
                u = (e("88fe"), e("2877")),
                f = Object(u["a"])(s, c, i, !1, null, "6efedfaf", null);
            n["default"] = f.exports
        }
    }
]);