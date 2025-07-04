"use strict";

function _typeof(e) {
    return (_typeof = "function" == typeof Symbol && "symbol" == typeof Symbol.iterator ? function(e) {
        return typeof e
    } : function(e) {
        return e && "function" == typeof Symbol && e.constructor === Symbol && e !== Symbol.prototype ? "symbol" : typeof e
    })(e)
}! function(e) {
    var t, a, o;
    "function" == typeof define && define.amd && (define(e), t = !0), "object" === ("undefined" == typeof exports ? "undefined" : _typeof(exports)) && (module.exports = e(), t = !0), t || (a = window.Cookies, (o = window.Cookies = e()).noConflict = function() {
        return window.Cookies = a, o
    })
}(function() {
    function d() {
        for (var e = 0, t = {}; e < arguments.length; e++) {
            var a, o = arguments[e];
            for (a in o) t[a] = o[a]
        }
        return t
    }

    function l(e) {
        return e.replace(/(%[0-9A-Z]{2})+/g, decodeURIComponent)
    }
    return function e(r) {
        function s() {}

        function a(e, t, a) {
            if ("undefined" != typeof document) {
                "number" == typeof(a = d({
                    path: "/"
                }, s.defaults, a)).expires && (a.expires = new Date(+new Date + 864e5 * a.expires)), a.expires = a.expires ? a.expires.toUTCString() : "";
                try {
                    var o = JSON.stringify(t);
                    /^[\{\[]/.test(o) && (t = o)
                } catch (e) {}
                t = r.write ? r.write(t, e) : encodeURIComponent(String(t)).replace(/%(23|24|26|2B|3A|3C|3E|3D|2F|3F|40|5B|5D|5E|60|7B|7D|7C)/g, decodeURIComponent), e = encodeURIComponent(String(e)).replace(/%(23|24|26|2B|5E|60|7C)/g, decodeURIComponent).replace(/[\(\)]/g, escape);
                var i, n = "";
                for (i in a) a[i] && (n += "; " + i, !0 !== a[i]) && (n += "=" + a[i].split(";")[0]);
                return document.cookie = e + "=" + t + n
            }
        }

        function t(e, t) {
            if ("undefined" != typeof document) {
                for (var a = {}, o = document.cookie ? document.cookie.split("; ") : [], i = 0; i < o.length; i++) {
                    var n = o[i].split("="),
                        s = n.slice(1).join("=");
                    t || '"' !== s.charAt(0) || (s = s.slice(1, -1));
                    try {
                        var d = l(n[0]),
                            s = (r.read || r)(s, d) || l(s);
                        if (t) try {
                            s = JSON.parse(s)
                        } catch (e) {}
                        if (a[d] = s, e === d) break
                    } catch (e) {}
                }
                return e ? a[e] : a
            }
        }
        return s.set = a, s.get = function(e) {
            return t(e, !1)
        }, s.getJSON = function(e) {
            return t(e, !0)
        }, s.remove = function(e, t) {
            a(e, "", d(t, {
                expires: -1
            }))
        }, s.defaults = {}, s.withConverter = e, s
    }(function() {})
}),
function(s, d) {
    var r = d("body"),
        l = d("head"),
        u = "#skin-theme",
        m = ".nk-sidebar",
        p = ".nk-apps-sidebar",
        c = ".nk-header",
        f = ["demo3", "non"],
        n = ["style", "aside", "header", "appside", "skin", "mode"],
        i = "light-mode",
        h = "dark-mode",
        v = ".nk-opt-item",
        y = ".nk-opt-list",
        k = {
            demo3: {
                aside: "is-light",
                header: "is-light",
                appside: "is-theme",
                style: "ui-default"
            },
            non: {
                aside: "is-light",
                header: "is-light",
                appside: "is-theme",
                style: "ui-default"
            }
        };
    s.Demo = {
        save: function(e, t) {
            Cookies.set(s.Demo.apps(e), t)
        },
        remove: function(e) {
            Cookies.remove(s.Demo.apps(e))
        },
        current: function(e) {
            return Cookies.get(s.Demo.apps(e))
        },
        apps: function(e) {
            for (var t, a = window.location.pathname.split("/").map(function(e, t, a) {
                    return e.replace("-", "")
                }), o = 0, i = f; o < i.length; o++) {
                var n = i[o];
                0 <= a.indexOf(n) && (t = n)
            }
            return e ? e + "_" + t : t
        },
        style: function(e, t) {
            var a = {
                mode: i + " " + h,
                style: "ui-default ui-clean ui-shady",
                aside: "is-light is-default is-theme is-dark",
                header: "is-light is-default is-theme is-dark",
                appside: "is-light is-default is-theme is-dark"
            };
            return "all" === e ? a[t] || "" : "any" === e ? a.mode + " " + a.style + " " + a.aside + " " + a.header + " " + appside.header : "body" === e ? a.mode + " " + a.style : "is-default" === e || "ui-default" === e || "is-regular" === e ? "" : e
        },
        skins: function(e) {
            return !e || "default" === e ? "theme" : "theme-" + e
        },
        defs: function(e) {
            var t = s.Demo.apps(),
                t = "";
            return s.Demo.current(e) ? s.Demo.current(e) : t
        },
        apply: function() {
            s.Demo.apps();
            for (var e = 0, t = n; e < t.length; e++) {
                var a, o, i = t[e];
                "aside" !== i && "header" !== i && "appside" !== i && "style" !== i || (a = s.Demo.defs(i), d(o = "aside" === i ? m : "appside" === i ? p : "header" === i ? c : r).removeClass(s.Demo.style("all", i)), "ui-default" !== a && "is-default" !== a && d(o).addClass(a)), "mode" === i && s.Demo.update(i, s.Demo.current("mode")), "skin" === i && s.Demo.update(i, s.Demo.current("skin"))
            }
            s.Demo.update("dir", s.Demo.current("dir"))
        },
        locked: function(e) {
            !0 === e ? (d(v + "[data-key=aside]").add(v + "[data-key=header]").add(v + "[data-key=appside]").addClass("disabled"), s.Demo.update("skin", "default", !0), d(v + "[data-key=skin]").removeClass("active"), d(v + "[data-key=skin][data-update=default]").addClass("active")) : d(v + "[data-key=aside]").add(v + "[data-key=header]").add(v + "[data-key=appside]").removeClass("disabled")
        },
        update: function(e, t, a) {
            var o, i = s.Demo.style(t, e),
                n = s.Demo.style("all", e);
            s.Demo.apps();
            "aside" !== e && "header" !== e && "appside" !== e || (d(o = "header" == e ? c : "appside" === e ? p : m).removeClass(n), d(o).addClass(i)), "mode" === e && (r.removeClass(n).removeAttr("theme"), i === h ? (r.addClass(i).attr("theme", "dark"), s.Demo.locked(!0)) : (r.addClass(i).removeAttr("theme"), s.Demo.locked(!1))), "style" === e && (r.removeClass(n), r.addClass(i)), "skin" === e && (o = s.Demo.skins(i), n = d("#skin-default").attr("href").replace("theme", "skins/" + o), "theme" === o ? d(u).remove() : 0 < d(u).length ? d(u).attr("href", n) : l.append('<link id="skin-theme" rel="stylesheet" href="' + n + '">')), !0 === a && s.Demo.save(e, t)
        },
        reset: function() {
            var t = s.Demo.apps();
            r.removeClass(s.Demo.style("body")).removeAttr("theme"), d(v).removeClass("active"), d(u).remove(), d(m).removeClass(s.Demo.style("all", "aside")), d(c).removeClass(s.Demo.style("all", "header"));
            for (var e = 0, a = n; e < a.length; e++) {
                var o = a[e];
                d("[data-key='" + o + "']").each(function() {
                    var e = d(this).data("update");
                    "aside" !== o && "header" !== o && "appside" !== o && "style" !== o || e === k[t][o] && d(this).addClass("active"), "mode" !== o && "skin" !== o || e !== i && "default" !== e || d(this).addClass("active")
                }), s.Demo.remove(o)
            }
            d("[data-key='dir']").each(function() {
                d(this).data("update") === s.Demo.current("dir") && d(this).addClass("active")
            }), s.Demo.apply()
        },
        load: function() {
            s.Demo.apply(), 0 < d(v).length && d(v).each(function() {
                var e = d(this).data("update"),
                    t = d(this).data("key");
                "aside" !== t && "header" !== t && "appside" !== t && "style" !== t || e === s.Demo.defs(t) && (d(this).parent(y).find(v).removeClass("active"), d(this).addClass("active")), "mode" !== t && "skin" !== t && "dir" !== t || e != s.Demo.current("skin") && e != s.Demo.current("mode") && e != s.Demo.current("dir") || (d(this).parent(y).find(v).removeClass("active"), d(this).addClass("active"))
            })
        },
        trigger: function() {
            d(v).on("click", function(e) {
                e.preventDefault();
                var e = d(this),
                    t = e.parent(y),
                    a = e.data("update"),
                    o = e.data("key");
                s.Demo.update(o, a, !0), t.find(v).removeClass("active"), e.addClass("active"), "dir" == o && setTimeout(function() {
                    window.location.reload()
                }, 100)
            }), d(".nk-opt-reset > a").on("click", function(e) {
                e.preventDefault(), s.Demo.reset()
            })
        },
        init: function(e) {
            s.Demo.load(), s.Demo.trigger()
        }
    }, s.coms.docReady.push(s.Demo.init), s.Promo = function() {
        var t = d(".pmo-st"),
            a = d(".pmo-lv"),
            e = d(".pmo-close");
        0 < e.length && e.on("click", function() {
            var e = Cookies.get("intm-offer");
            return a.removeClass("active"), t.addClass("active"), null == e && Cookies.set("intm-offer", "true", {
                expires: 1,
                path: ""
            }), !1
        }), d(window).on("load", function() {
            (null == Cookies.get("intm-offer") ? a : t).addClass("active")
        })
    }, s.coms.docReady.push(s.Promo)
}(NioApp, jQuery);