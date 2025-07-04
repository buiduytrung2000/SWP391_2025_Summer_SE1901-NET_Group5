! function(t, e) {
    if ("object" == typeof exports && "object" == typeof module) module.exports = e(require("jQuery"));
    else if ("function" == typeof define && define.amd) define(["jQuery"], e);
    else {
        var o = "object" == typeof exports ? e(require("jQuery")) : e(t.jQuery);
        for (var n in o)("object" == typeof exports ? exports : t)[n] = o[n]
    }
}(self, (function(t) {
    return (() => {
        "use strict";
        var e = {
                9770: (t, e, o) => {
                    var n = o(1145),
                        i = o.n(n);
                    i().summernote = i().summernote || {
                        lang: {}
                    }, i().extend(i().summernote.lang, {
                        "en-US": {
                            font: {
                                bold: "Bold",
                                italic: "Italic",
                                underline: "Underline",
                                clear: "Remove Font Style",
                                height: "Line Height",
                                name: "Font Family",
                                strikethrough: "Strikethrough",
                                subscript: "Subscript",
                                superscript: "Superscript",
                                size: "Font Size",
                                sizeunit: "Font Size Unit"
                            },
                            image: {
                                image: "Picture",
                                insert: "Insert Image",
                                resizeFull: "Resize full",
                                resizeHalf: "Resize half",
                                resizeQuarter: "Resize quarter",
                                resizeNone: "Original size",
                                floatLeft: "Float Left",
                                floatRight: "Float Right",
                                floatNone: "Remove float",
                                shapeRounded: "Shape: Rounded",
                                shapeCircle: "Shape: Circle",
                                shapeThumbnail: "Shape: Thumbnail",
                                shapeNone: "Shape: None",
                                dragImageHere: "Drag image or text here",
                                dropImage: "Drop image or Text",
                                selectFromFiles: "Select from files",
                                maximumFileSize: "Maximum file size",
                                maximumFileSizeError: "Maximum file size exceeded.",
                                url: "Image URL",
                                remove: "Remove Image",
                                original: "Original"
                            },
                            video: {
                                video: "Video",
                                videoLink: "Video Link",
                                insert: "Insert Video",
                                url: "Video URL",
                                providers: "(YouTube, Google Drive, Vimeo, Vine, Instagram, DailyMotion, Youku, Peertube)"
                            },
                            link: {
                                link: "Link",
                                insert: "Insert Link",
                                unlink: "Unlink",
                                edit: "Edit",
                                textToDisplay: "Text to display",
                                url: "To what URL should this link go?",
                                openInNewWindow: "Open in new window",
                                useProtocol: "Use default protocol"
                            },
                            table: {
                                table: "Table",
                                addRowAbove: "Add row above",
                                addRowBelow: "Add row below",
                                addColLeft: "Add column left",
                                addColRight: "Add column right",
                                delRow: "Delete row",
                                delCol: "Delete column",
                                delTable: "Delete table"
                            },
                            hr: {
                                insert: "Insert Horizontal Rule"
                            },
                            style: {
                                style: "Style",
                                p: "Normal",
                                blockquote: "Quote",
                                pre: "Code",
                                h1: "Header 1",
                                h2: "Header 2",
                                h3: "Header 3",
                                h4: "Header 4",
                                h5: "Header 5",
                                h6: "Header 6"
                            },
                            lists: {
                                unordered: "Unordered list",
                                ordered: "Ordered list"
                            },
                            options: {
                                help: "Help",
                                fullscreen: "Full Screen",
                                codeview: "Code View"
                            },
                            paragraph: {
                                paragraph: "Paragraph",
                                outdent: "Outdent",
                                indent: "Indent",
                                left: "Align left",
                                center: "Align center",
                                right: "Align right",
                                justify: "Justify full"
                            },
                            color: {
                                recent: "Recent Color",
                                more: "More Color",
                                background: "Background Color",
                                foreground: "Text Color",
                                transparent: "Transparent",
                                setTransparent: "Set transparent",
                                reset: "Reset",
                                resetToDefault: "Reset to default",
                                cpSelect: "Select"
                            },
                            shortcut: {
                                shortcuts: "Keyboard shortcuts",
                                close: "Close",
                                textFormatting: "Text formatting",
                                action: "Action",
                                paragraphFormatting: "Paragraph formatting",
                                documentStyle: "Document Style",
                                extraKeys: "Extra keys"
                            },
                            help: {
                                escape: "Escape",
                                insertParagraph: "Insert Paragraph",
                                undo: "Undo the last command",
                                redo: "Redo the last command",
                                tab: "Tab",
                                untab: "Untab",
                                bold: "Set a bold style",
                                italic: "Set a italic style",
                                underline: "Set a underline style",
                                strikethrough: "Set a strikethrough style",
                                removeFormat: "Clean a style",
                                justifyLeft: "Set left align",
                                justifyCenter: "Set center align",
                                justifyRight: "Set right align",
                                justifyFull: "Set full align",
                                insertUnorderedList: "Toggle unordered list",
                                insertOrderedList: "Toggle ordered list",
                                outdent: "Outdent on current paragraph",
                                indent: "Indent on current paragraph",
                                formatPara: "Change current block's format as a paragraph(P tag)",
                                formatH1: "Change current block's format as H1",
                                formatH2: "Change current block's format as H2",
                                formatH3: "Change current block's format as H3",
                                formatH4: "Change current block's format as H4",
                                formatH5: "Change current block's format as H5",
                                formatH6: "Change current block's format as H6",
                                insertHorizontalRule: "Insert horizontal rule",
                                "linkDialog.show": "Show Link Dialog"
                            },
                            history: {
                                undo: "Undo",
                                redo: "Redo"
                            },
                            specialChar: {
                                specialChar: "SPECIAL CHARACTERS",
                                select: "Select Special characters"
                            },
                            output: {
                                noSelection: "No Selection Made!"
                            }
                        }
                    })
                },
                1145: e => {
                    e.exports = t
                }
            },
            o = {};

        function n(t) {
            var i = o[t];
            if (void 0 !== i) return i.exports;
            var r = o[t] = {
                exports: {}
            };
            return e[t](r, r.exports, n), r.exports
        }
        n.n = t => {
            var e = t && t.__esModule ? () => t.default : () => t;
            return n.d(e, {
                a: e
            }), e
        }, n.d = (t, e) => {
            for (var o in e) n.o(e, o) && !n.o(t, o) && Object.defineProperty(t, o, {
                enumerable: !0,
                get: e[o]
            })
        }, n.o = (t, e) => Object.prototype.hasOwnProperty.call(t, e), n.r = t => {
            "undefined" != typeof Symbol && Symbol.toStringTag && Object.defineProperty(t, Symbol.toStringTag, {
                value: "Module"
            }), Object.defineProperty(t, "__esModule", {
                value: !0
            })
        };
        var i = {};
        return (() => {
            n.r(i);
            var t = n(1145),
                e = n.n(t),
                o = (n(9770), ["sans-serif", "serif", "monospace", "cursive", "fantasy"]);

            function r(t) {
                return -1 === e().inArray(t.toLowerCase(), o) ? "'".concat(t, "'") : t
            }
            var a, s = navigator.userAgent,
                l = /MSIE|Trident/i.test(s);
            if (l) {
                var c = /MSIE (\d+[.]\d+)/.exec(s);
                c && (a = parseFloat(c[1])), (c = /Trident\/.*rv:([0-9]{1,}[.0-9]{0,})/.exec(s)) && (a = parseFloat(c[1]))
            }
            var u = /Edge\/\d+/.test(s),
                d = "ontouchstart" in window || navigator.MaxTouchPoints > 0 || navigator.msMaxTouchPoints > 0,
                h = l ? "DOMCharacterDataModified DOMSubtreeModified DOMNodeInserted" : "input";
            const f = {
                isMac: navigator.appVersion.indexOf("Mac") > -1,
                isMSIE: l,
                isEdge: u,
                isFF: !u && /firefox/i.test(s),
                isPhantom: /PhantomJS/i.test(s),
                isWebkit: !u && /webkit/i.test(s),
                isChrome: !u && /chrome/i.test(s),
                isSafari: !u && /safari/i.test(s) && !/chrome/i.test(s),
                browserVersion: a,
                isSupportTouch: d,
                isFontInstalled: function(t) {
                    var e = "Comic Sans MS" === t ? "Courier New" : "Comic Sans MS",
                        o = document.createElement("canvas").getContext("2d");
                    o.font = "200px '" + e + "'";
                    var n = o.measureText("mmmmmmmmmmwwwww").width;
                    return o.font = "200px " + r(t) + ', "' + e + '"', n !== o.measureText("mmmmmmmmmmwwwww").width
                },
                isW3CRangeSupport: !!document.createRange,
                inputEventName: h,
                genericFontFamilies: o,
                validFontName: r
            };
            var p = 0;
            const m = {
                eq: function(t) {
                    return function(e) {
                        return t === e
                    }
                },
                eq2: function(t, e) {
                    return t === e
                },
                peq2: function(t) {
                    return function(e, o) {
                        return e[t] === o[t]
                    }
                },
                ok: function() {
                    return !0
                },
                fail: function() {
                    return !1
                },
                self: function(t) {
                    return t
                },
                not: function(t) {
                    return function() {
                        return !t.apply(t, arguments)
                    }
                },
                and: function(t, e) {
                    return function(o) {
                        return t(o) && e(o)
                    }
                },
                invoke: function(t, e) {
                    return function() {
                        return t[e].apply(t, arguments)
                    }
                },
                resetUniqueId: function() {
                    p = 0
                },
                uniqueId: function(t) {
                    var e = ++p + "";
                    return t ? t + e : e
                },
                rect2bnd: function(t) {
                    var o = e()(document);
                    return {
                        top: t.top + o.scrollTop(),
                        left: t.left + o.scrollLeft(),
                        width: t.right - t.left,
                        height: t.bottom - t.top
                    }
                },
                invertObject: function(t) {
                    var e = {};
                    for (var o in t) Object.prototype.hasOwnProperty.call(t, o) && (e[t[o]] = o);
                    return e
                },
                namespaceToCamel: function(t, e) {
                    return (e = e || "") + t.split(".").map((function(t) {
                        return t.substring(0, 1).toUpperCase() + t.substring(1)
                    })).join("")
                },
                debounce: function(t, e, o) {
                    var n;
                    return function() {
                        var i = this,
                            r = arguments,
                            a = function() {
                                n = null, o || t.apply(i, r)
                            },
                            s = o && !n;
                        clearTimeout(n), n = setTimeout(a, e), s && t.apply(i, r)
                    }
                },
                isValidUrl: function(t) {
                    return /[-a-zA-Z0-9@:%._\+~#=]{2,256}\.[a-z]{2,6}\b([-a-zA-Z0-9@:%_\+.~#?&//=]*)/gi.test(t)
                }
            };

            function v(t) {
                return t[0]
            }

            function g(t) {
                return t[t.length - 1]
            }

            function b(t) {
                return t.slice(1)
            }

            function k(t, e) {
                if (t && t.length && e) {
                    if (t.indexOf) return -1 !== t.indexOf(e);
                    if (t.contains) return t.contains(e)
                }
                return !1
            }
            const y = {
                head: v,
                last: g,
                initial: function(t) {
                    return t.slice(0, t.length - 1)
                },
                tail: b,
                prev: function(t, e) {
                    if (t && t.length && e) {
                        var o = t.indexOf(e);
                        return -1 === o ? null : t[o - 1]
                    }
                    return null
                },
                next: function(t, e) {
                    if (t && t.length && e) {
                        var o = t.indexOf(e);
                        return -1 === o ? null : t[o + 1]
                    }
                    return null
                },
                find: function(t, e) {
                    for (var o = 0, n = t.length; o < n; o++) {
                        var i = t[o];
                        if (e(i)) return i
                    }
                },
                contains: k,
                all: function(t, e) {
                    for (var o = 0, n = t.length; o < n; o++)
                        if (!e(t[o])) return !1;
                    return !0
                },
                sum: function(t, e) {
                    return e = e || m.self, t.reduce((function(t, o) {
                        return t + e(o)
                    }), 0)
                },
                from: function(t) {
                    for (var e = [], o = t.length, n = -1; ++n < o;) e[n] = t[n];
                    return e
                },
                isEmpty: function(t) {
                    return !t || !t.length
                },
                clusterBy: function(t, e) {
                    return t.length ? b(t).reduce((function(t, o) {
                        var n = g(t);
                        return e(g(n), o) ? n[n.length] = o : t[t.length] = [o], t
                    }), [
                        [v(t)]
                    ]) : []
                },
                compact: function(t) {
                    for (var e = [], o = 0, n = t.length; o < n; o++) t[o] && e.push(t[o]);
                    return e
                },
                unique: function(t) {
                    for (var e = [], o = 0, n = t.length; o < n; o++) k(e, t[o]) || e.push(t[o]);
                    return e
                }
            };
            var w = String.fromCharCode(160);

            function C(t) {
                return t && e()(t).hasClass("note-editable")
            }

            function x(t) {
                return t = t.toUpperCase(),
                    function(e) {
                        return e && e.nodeName.toUpperCase() === t
                    }
            }

            function S(t) {
                return t && 3 === t.nodeType
            }

            function T(t) {
                return t && /^BR|^IMG|^HR|^IFRAME|^BUTTON|^INPUT|^AUDIO|^VIDEO|^EMBED/.test(t.nodeName.toUpperCase())
            }

            function E(t) {
                return !C(t) && (t && /^DIV|^P|^LI|^H[1-7]/.test(t.nodeName.toUpperCase()))
            }
            var $ = x("PRE"),
                N = x("LI");
            var I = x("TABLE"),
                P = x("DATA");

            function R(t) {
                return !(H(t) || L(t) || A(t) || E(t) || I(t) || D(t) || P(t))
            }

            function L(t) {
                return t && /^UL|^OL/.test(t.nodeName.toUpperCase())
            }
            var A = x("HR");

            function F(t) {
                return t && /^TD|^TH/.test(t.nodeName.toUpperCase())
            }
            var D = x("BLOCKQUOTE");

            function H(t) {
                return F(t) || D(t) || C(t)
            }
            var B = x("A");
            var z = x("BODY");
            var M = f.isMSIE && f.browserVersion < 11 ? "&nbsp;" : "<br>";

            function O(t) {
                return S(t) ? t.nodeValue.length : t ? t.childNodes.length : 0
            }

            function j(t) {
                var e = O(t);
                return 0 === e || (!S(t) && 1 === e && t.innerHTML === M || !(!y.all(t.childNodes, S) || "" !== t.innerHTML))
            }

            function U(t) {
                T(t) || O(t) || (t.innerHTML = M)
            }

            function W(t, e) {
                for (; t;) {
                    if (e(t)) return t;
                    if (C(t)) break;
                    t = t.parentNode
                }
                return null
            }

            function K(t, e) {
                e = e || m.fail;
                var o = [];
                return W(t, (function(t) {
                    return C(t) || o.push(t), e(t)
                })), o
            }

            function V(t, e) {
                e = e || m.fail;
                for (var o = []; t && !e(t);) o.push(t), t = t.nextSibling;
                return o
            }

            function q(t, e) {
                var o = e.nextSibling,
                    n = e.parentNode;
                return o ? n.insertBefore(t, o) : n.appendChild(t), t
            }

            function _(t, o) {
                return e().each(o, (function(e, o) {
                    t.appendChild(o)
                })), t
            }

            function G(t) {
                return 0 === t.offset
            }

            function Y(t) {
                return t.offset === O(t.node)
            }

            function Z(t) {
                return G(t) || Y(t)
            }

            function X(t, e) {
                for (; t && t !== e;) {
                    if (0 !== J(t)) return !1;
                    t = t.parentNode
                }
                return !0
            }

            function Q(t, e) {
                if (!e) return !1;
                for (; t && t !== e;) {
                    if (J(t) !== O(t.parentNode) - 1) return !1;
                    t = t.parentNode
                }
                return !0
            }

            function J(t) {
                for (var e = 0; t = t.previousSibling;) e += 1;
                return e
            }

            function tt(t) {
                return !!(t && t.childNodes && t.childNodes.length)
            }

            function et(t, e) {
                var o, n;
                if (0 === t.offset) {
                    if (C(t.node)) return null;
                    o = t.node.parentNode, n = J(t.node)
                } else tt(t.node) ? n = O(o = t.node.childNodes[t.offset - 1]) : (o = t.node, n = e ? 0 : t.offset - 1);
                return {
                    node: o,
                    offset: n
                }
            }

            function ot(t, e) {
                var o, n;
                if (O(t.node) === t.offset) {
                    if (C(t.node)) return null;
                    var i = function t(e) {
                        if (!e.nextSibling) return;
                        if (e.parent !== e.nextSibling.parent) return;
                        return S(e.nextSibling) ? e.nextSibling : t(e.nextSibling)
                    }(t.node);
                    i ? (o = i, n = 0) : (o = t.node.parentNode, n = J(t.node) + 1)
                } else tt(t.node) ? (o = t.node.childNodes[t.offset], n = 0) : (o = t.node, n = e ? O(t.node) : t.offset + 1);
                return {
                    node: o,
                    offset: n
                }
            }

            function nt(t, e) {
                var o, n = 0;
                if (j(t.node)) return null === t.node ? null : {
                    node: o = t.node.nextSibling,
                    offset: n = 0
                };
                if (O(t.node) === t.offset) {
                    if (C(t.node)) return null;
                    o = t.node.parentNode, n = J(t.node) + 1, C(o) && (o = t.node.nextSibling, n = 0)
                } else if (tt(t.node)) {
                    if (n = 0, j(o = t.node.childNodes[t.offset])) return j(t.node.nextSibling) ? null : {
                        node: t.node.nextSibling,
                        offset: n
                    }
                } else if (o = t.node, n = e ? O(t.node) : t.offset + 1, j(o)) return null;
                return {
                    node: o,
                    offset: n
                }
            }

            function it(t, e) {
                return t.node === e.node && t.offset === e.offset
            }

            function rt(t, e) {
                var o = e && e.isSkipPaddingBlankHTML,
                    n = e && e.isNotSplitEdgePoint,
                    i = e && e.isDiscardEmptySplits;
                if (i && (o = !0), Z(t) && (S(t.node) || n)) {
                    if (G(t)) return t.node;
                    if (Y(t)) return t.node.nextSibling
                }
                if (S(t.node)) return t.node.splitText(t.offset);
                var r = t.node.childNodes[t.offset],
                    a = q(t.node.cloneNode(!1), t.node);
                return _(a, V(r)), o || (U(t.node), U(a)), i && (j(t.node) && lt(t.node), j(a)) ? (lt(a), t.node.nextSibling) : a
            }

            function at(t, e, o) {
                var n = K(e.node, m.eq(t));
                return n.length ? 1 === n.length ? rt(e, o) : n.reduce((function(t, n) {
                    return t === e.node && (t = rt(e, o)), rt({
                        node: n,
                        offset: t ? J(t) : O(n)
                    }, o)
                })) : null
            }

            function st(t) {
                return document.createElement(t)
            }

            function lt(t, e) {
                if (t && t.parentNode) {
                    if (t.removeNode) return t.removeNode(e);
                    var o = t.parentNode;
                    if (!e) {
                        for (var n = [], i = 0, r = t.childNodes.length; i < r; i++) n.push(t.childNodes[i]);
                        for (var a = 0, s = n.length; a < s; a++) o.insertBefore(n[a], t)
                    }
                    o.removeChild(t)
                }
            }
            var ct = x("TEXTAREA");

            function ut(t, e) {
                var o = ct(t[0]) ? t.val() : t.html();
                return e ? o.replace(/[\n\r]/g, "") : o
            }
            const dt = {
                NBSP_CHAR: w,
                ZERO_WIDTH_NBSP_CHAR: "\ufeff",
                blank: M,
                emptyPara: "<p>".concat(M, "</p>"),
                makePredByNodeName: x,
                isEditable: C,
                isControlSizing: function(t) {
                    return t && e()(t).hasClass("note-control-sizing")
                },
                isText: S,
                isElement: function(t) {
                    return t && 1 === t.nodeType
                },
                isVoid: T,
                isPara: E,
                isPurePara: function(t) {
                    return E(t) && !N(t)
                },
                isHeading: function(t) {
                    return t && /^H[1-7]/.test(t.nodeName.toUpperCase())
                },
                isInline: R,
                isBlock: m.not(R),
                isBodyInline: function(t) {
                    return R(t) && !W(t, E)
                },
                isBody: z,
                isParaInline: function(t) {
                    return R(t) && !!W(t, E)
                },
                isPre: $,
                isList: L,
                isTable: I,
                isData: P,
                isCell: F,
                isBlockquote: D,
                isBodyContainer: H,
                isAnchor: B,
                isDiv: x("DIV"),
                isLi: N,
                isBR: x("BR"),
                isSpan: x("SPAN"),
                isB: x("B"),
                isU: x("U"),
                isS: x("S"),
                isI: x("I"),
                isImg: x("IMG"),
                isTextarea: ct,
                deepestChildIsEmpty: function(t) {
                    do {
                        if (null === t.firstElementChild || "" === t.firstElementChild.innerHTML) break
                    } while (t = t.firstElementChild);
                    return j(t)
                },
                isEmpty: j,
                isEmptyAnchor: m.and(B, j),
                isClosestSibling: function(t, e) {
                    return t.nextSibling === e || t.previousSibling === e
                },
                withClosestSiblings: function(t, e) {
                    e = e || m.ok;
                    var o = [];
                    return t.previousSibling && e(t.previousSibling) && o.push(t.previousSibling), o.push(t), t.nextSibling && e(t.nextSibling) && o.push(t.nextSibling), o
                },
                nodeLength: O,
                isLeftEdgePoint: G,
                isRightEdgePoint: Y,
                isEdgePoint: Z,
                isLeftEdgeOf: X,
                isRightEdgeOf: Q,
                isLeftEdgePointOf: function(t, e) {
                    return G(t) && X(t.node, e)
                },
                isRightEdgePointOf: function(t, e) {
                    return Y(t) && Q(t.node, e)
                },
                prevPoint: et,
                nextPoint: ot,
                nextPointWithEmptyNode: nt,
                isSamePoint: it,
                isVisiblePoint: function(t) {
                    if (S(t.node) || !tt(t.node) || j(t.node)) return !0;
                    var e = t.node.childNodes[t.offset - 1],
                        o = t.node.childNodes[t.offset];
                    return !((e && !T(e) || o && !T(o)) && !I(o))
                },
                prevPointUntil: function(t, e) {
                    for (; t;) {
                        if (e(t)) return t;
                        t = et(t)
                    }
                    return null
                },
                nextPointUntil: function(t, e) {
                    for (; t;) {
                        if (e(t)) return t;
                        t = ot(t)
                    }
                    return null
                },
                isCharPoint: function(t) {
                    if (!S(t.node)) return !1;
                    var e = t.node.nodeValue.charAt(t.offset - 1);
                    return e && " " !== e && e !== w
                },
                isSpacePoint: function(t) {
                    if (!S(t.node)) return !1;
                    var e = t.node.nodeValue.charAt(t.offset - 1);
                    return " " === e || e === w
                },
                walkPoint: function(t, e, o, n) {
                    for (var i = t; i && (o(i), !it(i, e));) {
                        i = nt(i, n && t.node !== i.node && e.node !== i.node)
                    }
                },
                ancestor: W,
                singleChildAncestor: function(t, e) {
                    for (t = t.parentNode; t && 1 === O(t);) {
                        if (e(t)) return t;
                        if (C(t)) break;
                        t = t.parentNode
                    }
                    return null
                },
                listAncestor: K,
                lastAncestor: function(t, e) {
                    var o = K(t);
                    return y.last(o.filter(e))
                },
                listNext: V,
                listPrev: function(t, e) {
                    e = e || m.fail;
                    for (var o = []; t && !e(t);) o.push(t), t = t.previousSibling;
                    return o
                },
                listDescendant: function(t, e) {
                    var o = [];
                    return e = e || m.ok,
                        function n(i) {
                            t !== i && e(i) && o.push(i);
                            for (var r = 0, a = i.childNodes.length; r < a; r++) n(i.childNodes[r])
                        }(t), o
                },
                commonAncestor: function(t, e) {
                    for (var o = K(t), n = e; n; n = n.parentNode)
                        if (o.indexOf(n) > -1) return n;
                    return null
                },
                wrap: function(t, o) {
                    var n = t.parentNode,
                        i = e()("<" + o + ">")[0];
                    return n.insertBefore(i, t), i.appendChild(t), i
                },
                insertAfter: q,
                appendChildNodes: _,
                position: J,
                hasChildren: tt,
                makeOffsetPath: function(t, e) {
                    return K(e, m.eq(t)).map(J).reverse()
                },
                fromOffsetPath: function(t, e) {
                    for (var o = t, n = 0, i = e.length; n < i; n++) o = o.childNodes.length <= e[n] ? o.childNodes[o.childNodes.length - 1] : o.childNodes[e[n]];
                    return o
                },
                splitTree: at,
                splitPoint: function(t, e) {
                    var o, n, i = e ? E : H,
                        r = K(t.node, i),
                        a = y.last(r) || t.node;
                    i(a) ? (o = r[r.length - 2], n = a) : n = (o = a).parentNode;
                    var s = o && at(o, t, {
                        isSkipPaddingBlankHTML: e,
                        isNotSplitEdgePoint: e
                    });
                    return s || n !== t.node || (s = t.node.childNodes[t.offset]), {
                        rightNode: s,
                        container: n
                    }
                },
                create: st,
                createText: function(t) {
                    return document.createTextNode(t)
                },
                remove: lt,
                removeWhile: function(t, e) {
                    for (; t && !C(t) && e(t);) {
                        var o = t.parentNode;
                        lt(t), t = o
                    }
                },
                replace: function(t, e) {
                    if (t.nodeName.toUpperCase() === e.toUpperCase()) return t;
                    var o = st(e);
                    return t.style.cssText && (o.style.cssText = t.style.cssText), _(o, y.from(t.childNodes)), q(o, t), lt(t), o
                },
                html: function(t, e) {
                    var o = ut(t);
                    if (e) {
                        o = (o = o.replace(/<(\/?)(\b(?!!)[^>\s]*)(.*?)(\s*\/?>)/g, (function(t, e, o) {
                            o = o.toUpperCase();
                            var n = /^DIV|^TD|^TH|^P|^LI|^H[1-7]/.test(o) && !!e,
                                i = /^BLOCKQUOTE|^TABLE|^TBODY|^TR|^HR|^UL|^OL/.test(o);
                            return t + (n || i ? "\n" : "")
                        }))).trim()
                    }
                    return o
                },
                value: ut,
                posFromPlaceholder: function(t) {
                    var o = e()(t),
                        n = o.offset(),
                        i = o.outerHeight(!0);
                    return {
                        left: n.left,
                        top: n.top + i
                    }
                },
                attachEvents: function(t, e) {
                    Object.keys(e).forEach((function(o) {
                        t.on(o, e[o])
                    }))
                },
                detachEvents: function(t, e) {
                    Object.keys(e).forEach((function(o) {
                        t.off(o, e[o])
                    }))
                },
                isCustomStyleTag: function(t) {
                    return t && !S(t) && y.contains(t.classList, "note-styletag")
                }
            };

            function ht(t, e) {
                for (var o = 0; o < e.length; o++) {
                    var n = e[o];
                    n.enumerable = n.enumerable || !1, n.configurable = !0, "value" in n && (n.writable = !0), Object.defineProperty(t, n.key, n)
                }
            }
            var ft = function() {
                function t(o, n) {
                    ! function(t, e) {
                        if (!(t instanceof e)) throw new TypeError("Cannot call a class as a function")
                    }(this, t), this.$note = o, this.memos = {}, this.modules = {}, this.layoutInfo = {}, this.options = e().extend(!0, {}, n), e().summernote.ui = e().summernote.ui_template(this.options), this.ui = e().summernote.ui, this.initialize()
                }
                var o, n, i;
                return o = t, (n = [{
                    key: "initialize",
                    value: function() {
                        return this.layoutInfo = this.ui.createLayout(this.$note), this._initialize(), this.$note.hide(), this
                    }
                }, {
                    key: "destroy",
                    value: function() {
                        this._destroy(), this.$note.removeData("summernote"), this.ui.removeLayout(this.$note, this.layoutInfo)
                    }
                }, {
                    key: "reset",
                    value: function() {
                        var t = this.isDisabled();
                        this.code(dt.emptyPara), this._destroy(), this._initialize(), t && this.disable()
                    }
                }, {
                    key: "_initialize",
                    value: function() {
                        var t = this;
                        this.options.id = m.uniqueId(e().now()), this.options.container = this.options.container || this.layoutInfo.editor;
                        var o = e().extend({}, this.options.buttons);
                        Object.keys(o).forEach((function(e) {
                            t.memo("button." + e, o[e])
                        }));
                        var n = e().extend({}, this.options.modules, e().summernote.plugins || {});
                        Object.keys(n).forEach((function(e) {
                            t.module(e, n[e], !0)
                        })), Object.keys(this.modules).forEach((function(e) {
                            t.initializeModule(e)
                        }))
                    }
                }, {
                    key: "_destroy",
                    value: function() {
                        var t = this;
                        Object.keys(this.modules).reverse().forEach((function(e) {
                            t.removeModule(e)
                        })), Object.keys(this.memos).forEach((function(e) {
                            t.removeMemo(e)
                        })), this.triggerEvent("destroy", this)
                    }
                }, {
                    key: "code",
                    value: function(t) {
                        var e = this.invoke("codeview.isActivated");
                        if (void 0 === t) return this.invoke("codeview.sync"), e ? this.layoutInfo.codable.val() : this.layoutInfo.editable.html();
                        e ? this.invoke("codeview.sync", t) : this.layoutInfo.editable.html(t), this.$note.val(t), this.triggerEvent("change", t, this.layoutInfo.editable)
                    }
                }, {
                    key: "isDisabled",
                    value: function() {
                        return "false" === this.layoutInfo.editable.attr("contenteditable")
                    }
                }, {
                    key: "enable",
                    value: function() {
                        this.layoutInfo.editable.attr("contenteditable", !0), this.invoke("toolbar.activate", !0), this.triggerEvent("disable", !1), this.options.editing = !0
                    }
                }, {
                    key: "disable",
                    value: function() {
                        this.invoke("codeview.isActivated") && this.invoke("codeview.deactivate"), this.layoutInfo.editable.attr("contenteditable", !1), this.options.editing = !1, this.invoke("toolbar.deactivate", !0), this.triggerEvent("disable", !0)
                    }
                }, {
                    key: "triggerEvent",
                    value: function() {
                        var t = y.head(arguments),
                            e = y.tail(y.from(arguments)),
                            o = this.options.callbacks[m.namespaceToCamel(t, "on")];
                        o && o.apply(this.$note[0], e), this.$note.trigger("summernote." + t, e)
                    }
                }, {
                    key: "initializeModule",
                    value: function(t) {
                        var e = this.modules[t];
                        e.shouldInitialize = e.shouldInitialize || m.ok, e.shouldInitialize() && (e.initialize && e.initialize(), e.events && dt.attachEvents(this.$note, e.events))
                    }
                }, {
                    key: "module",
                    value: function(t, e, o) {
                        if (1 === arguments.length) return this.modules[t];
                        this.modules[t] = new e(this), o || this.initializeModule(t)
                    }
                }, {
                    key: "removeModule",
                    value: function(t) {
                        var e = this.modules[t];
                        e.shouldInitialize() && (e.events && dt.detachEvents(this.$note, e.events), e.destroy && e.destroy()), delete this.modules[t]
                    }
                }, {
                    key: "memo",
                    value: function(t, e) {
                        if (1 === arguments.length) return this.memos[t];
                        this.memos[t] = e
                    }
                }, {
                    key: "removeMemo",
                    value: function(t) {
                        this.memos[t] && this.memos[t].destroy && this.memos[t].destroy(), delete this.memos[t]
                    }
                }, {
                    key: "createInvokeHandlerAndUpdateState",
                    value: function(t, e) {
                        var o = this;
                        return function(n) {
                            o.createInvokeHandler(t, e)(n), o.invoke("buttons.updateCurrentStyle")
                        }
                    }
                }, {
                    key: "createInvokeHandler",
                    value: function(t, o) {
                        var n = this;
                        return function(i) {
                            i.preventDefault();
                            var r = e()(i.target);
                            n.invoke(t, o || r.closest("[data-value]").data("value"), r)
                        }
                    }
                }, {
                    key: "invoke",
                    value: function() {
                        var t = y.head(arguments),
                            e = y.tail(y.from(arguments)),
                            o = t.split("."),
                            n = o.length > 1,
                            i = n && y.head(o),
                            r = n ? y.last(o) : y.head(o),
                            a = this.modules[i || "editor"];
                        return !i && this[r] ? this[r].apply(this, e) : a && a[r] && a.shouldInitialize() ? a[r].apply(a, e) : void 0
                    }
                }]) && ht(o.prototype, n), i && ht(o, i), t
            }();

            function pt(t, e) {
                for (var o = 0; o < e.length; o++) {
                    var n = e[o];
                    n.enumerable = n.enumerable || !1, n.configurable = !0, "value" in n && (n.writable = !0), Object.defineProperty(t, n.key, n)
                }
            }

            function mt(t, e) {
                var o, n, i = t.parentElement(),
                    r = document.body.createTextRange(),
                    a = y.from(i.childNodes);
                for (o = 0; o < a.length; o++)
                    if (!dt.isText(a[o])) {
                        if (r.moveToElementText(a[o]), r.compareEndPoints("StartToStart", t) >= 0) break;
                        n = a[o]
                    } if (0 !== o && dt.isText(a[o - 1])) {
                    var s = document.body.createTextRange(),
                        l = null;
                    s.moveToElementText(n || i), s.collapse(!n), l = n ? n.nextSibling : i.firstChild;
                    var c = t.duplicate();
                    c.setEndPoint("StartToStart", s);
                    for (var u = c.text.replace(/[\r\n]/g, "").length; u > l.nodeValue.length && l.nextSibling;) u -= l.nodeValue.length, l = l.nextSibling;
                    l.nodeValue;
                    e && l.nextSibling && dt.isText(l.nextSibling) && u === l.nodeValue.length && (u -= l.nodeValue.length, l = l.nextSibling), i = l, o = u
                }
                return {
                    cont: i,
                    offset: o
                }
            }

            function vt(t) {
                var e = document.body.createTextRange(),
                    o = function t(e, o) {
                        var n, i;
                        if (dt.isText(e)) {
                            var r = dt.listPrev(e, m.not(dt.isText)),
                                a = y.last(r).previousSibling;
                            n = a || e.parentNode, o += y.sum(y.tail(r), dt.nodeLength), i = !a
                        } else {
                            if (n = e.childNodes[o] || e, dt.isText(n)) return t(n, 0);
                            o = 0, i = !1
                        }
                        return {
                            node: n,
                            collapseToStart: i,
                            offset: o
                        }
                    }(t.node, t.offset);
                return e.moveToElementText(o.node), e.collapse(o.collapseToStart), e.moveStart("character", o.offset), e
            }
            e().fn.extend({
                summernote: function() {
                    var t = e().type(y.head(arguments)),
                        o = "string" === t,
                        n = "object" === t,
                        i = e().extend({}, e().summernote.options, n ? y.head(arguments) : {});
                    i.langInfo = e().extend(!0, {}, e().summernote.lang["en-US"], e().summernote.lang[i.lang]), i.icons = e().extend(!0, {}, e().summernote.options.icons, i.icons), i.tooltip = "auto" === i.tooltip ? !f.isSupportTouch : i.tooltip, this.each((function(t, o) {
                        var n = e()(o);
                        if (!n.data("summernote")) {
                            var r = new ft(n, i);
                            n.data("summernote", r), n.data("summernote").triggerEvent("init", r.layoutInfo)
                        }
                    }));
                    var r = this.first();
                    if (r.length) {
                        var a = r.data("summernote");
                        if (o) return a.invoke.apply(a, y.from(arguments));
                        i.focus && a.invoke("editor.focus")
                    }
                    return this
                }
            });
            var gt = function() {
                function t(e, o, n, i) {
                    ! function(t, e) {
                        if (!(t instanceof e)) throw new TypeError("Cannot call a class as a function")
                    }(this, t), this.sc = e, this.so = o, this.ec = n, this.eo = i, this.isOnEditable = this.makeIsOn(dt.isEditable), this.isOnList = this.makeIsOn(dt.isList), this.isOnAnchor = this.makeIsOn(dt.isAnchor), this.isOnCell = this.makeIsOn(dt.isCell), this.isOnData = this.makeIsOn(dt.isData)
                }
                var o, n, i;
                return o = t, (n = [{
                    key: "nativeRange",
                    value: function() {
                        if (f.isW3CRangeSupport) {
                            var t = document.createRange();
                            return t.setStart(this.sc, this.so), t.setEnd(this.ec, this.eo), t
                        }
                        var e = vt({
                            node: this.sc,
                            offset: this.so
                        });
                        return e.setEndPoint("EndToEnd", vt({
                            node: this.ec,
                            offset: this.eo
                        })), e
                    }
                }, {
                    key: "getPoints",
                    value: function() {
                        return {
                            sc: this.sc,
                            so: this.so,
                            ec: this.ec,
                            eo: this.eo
                        }
                    }
                }, {
                    key: "getStartPoint",
                    value: function() {
                        return {
                            node: this.sc,
                            offset: this.so
                        }
                    }
                }, {
                    key: "getEndPoint",
                    value: function() {
                        return {
                            node: this.ec,
                            offset: this.eo
                        }
                    }
                }, {
                    key: "select",
                    value: function() {
                        var t = this.nativeRange();
                        if (f.isW3CRangeSupport) {
                            var e = document.getSelection();
                            e.rangeCount > 0 && e.removeAllRanges(), e.addRange(t)
                        } else t.select();
                        return this
                    }
                }, {
                    key: "scrollIntoView",
                    value: function(t) {
                        var o = e()(t).height();
                        return t.scrollTop + o < this.sc.offsetTop && (t.scrollTop += Math.abs(t.scrollTop + o - this.sc.offsetTop)), this
                    }
                }, {
                    key: "normalize",
                    value: function() {
                        var e = function(t, e) {
                                if (!t) return t;
                                if (dt.isVisiblePoint(t) && (!dt.isEdgePoint(t) || dt.isRightEdgePoint(t) && !e || dt.isLeftEdgePoint(t) && e || dt.isRightEdgePoint(t) && e && dt.isVoid(t.node.nextSibling) || dt.isLeftEdgePoint(t) && !e && dt.isVoid(t.node.previousSibling) || dt.isBlock(t.node) && dt.isEmpty(t.node))) return t;
                                var o = dt.ancestor(t.node, dt.isBlock),
                                    n = !1;
                                if (!n) {
                                    var i = dt.prevPoint(t) || {
                                        node: null
                                    };
                                    n = (dt.isLeftEdgePointOf(t, o) || dt.isVoid(i.node)) && !e
                                }
                                var r = !1;
                                if (!r) {
                                    var a = dt.nextPoint(t) || {
                                        node: null
                                    };
                                    r = (dt.isRightEdgePointOf(t, o) || dt.isVoid(a.node)) && e
                                }
                                if (n || r) {
                                    if (dt.isVisiblePoint(t)) return t;
                                    e = !e
                                }
                                return (e ? dt.nextPointUntil(dt.nextPoint(t), dt.isVisiblePoint) : dt.prevPointUntil(dt.prevPoint(t), dt.isVisiblePoint)) || t
                            },
                            o = e(this.getEndPoint(), !1),
                            n = this.isCollapsed() ? o : e(this.getStartPoint(), !0);
                        return new t(n.node, n.offset, o.node, o.offset)
                    }
                }, {
                    key: "nodes",
                    value: function(t, e) {
                        t = t || m.ok;
                        var o = e && e.includeAncestor,
                            n = e && e.fullyContains,
                            i = this.getStartPoint(),
                            r = this.getEndPoint(),
                            a = [],
                            s = [];
                        return dt.walkPoint(i, r, (function(e) {
                            var i;
                            dt.isEditable(e.node) || (n ? (dt.isLeftEdgePoint(e) && s.push(e.node), dt.isRightEdgePoint(e) && y.contains(s, e.node) && (i = e.node)) : i = o ? dt.ancestor(e.node, t) : e.node, i && t(i) && a.push(i))
                        }), !0), y.unique(a)
                    }
                }, {
                    key: "commonAncestor",
                    value: function() {
                        return dt.commonAncestor(this.sc, this.ec)
                    }
                }, {
                    key: "expand",
                    value: function(e) {
                        var o = dt.ancestor(this.sc, e),
                            n = dt.ancestor(this.ec, e);
                        if (!o && !n) return new t(this.sc, this.so, this.ec, this.eo);
                        var i = this.getPoints();
                        return o && (i.sc = o, i.so = 0), n && (i.ec = n, i.eo = dt.nodeLength(n)), new t(i.sc, i.so, i.ec, i.eo)
                    }
                }, {
                    key: "collapse",
                    value: function(e) {
                        return e ? new t(this.sc, this.so, this.sc, this.so) : new t(this.ec, this.eo, this.ec, this.eo)
                    }
                }, {
                    key: "splitText",
                    value: function() {
                        var e = this.sc === this.ec,
                            o = this.getPoints();
                        return dt.isText(this.ec) && !dt.isEdgePoint(this.getEndPoint()) && this.ec.splitText(this.eo), dt.isText(this.sc) && !dt.isEdgePoint(this.getStartPoint()) && (o.sc = this.sc.splitText(this.so), o.so = 0, e && (o.ec = o.sc, o.eo = this.eo - this.so)), new t(o.sc, o.so, o.ec, o.eo)
                    }
                }, {
                    key: "deleteContents",
                    value: function() {
                        if (this.isCollapsed()) return this;
                        var o = this.splitText(),
                            n = o.nodes(null, {
                                fullyContains: !0
                            }),
                            i = dt.prevPointUntil(o.getStartPoint(), (function(t) {
                                return !y.contains(n, t.node)
                            })),
                            r = [];
                        return e().each(n, (function(t, e) {
                            var o = e.parentNode;
                            i.node !== o && 1 === dt.nodeLength(o) && r.push(o), dt.remove(e, !1)
                        })), e().each(r, (function(t, e) {
                            dt.remove(e, !1)
                        })), new t(i.node, i.offset, i.node, i.offset).normalize()
                    }
                }, {
                    key: "makeIsOn",
                    value: function(t) {
                        return function() {
                            var e = dt.ancestor(this.sc, t);
                            return !!e && e === dt.ancestor(this.ec, t)
                        }
                    }
                }, {
                    key: "isLeftEdgeOf",
                    value: function(t) {
                        if (!dt.isLeftEdgePoint(this.getStartPoint())) return !1;
                        var e = dt.ancestor(this.sc, t);
                        return e && dt.isLeftEdgeOf(this.sc, e)
                    }
                }, {
                    key: "isCollapsed",
                    value: function() {
                        return this.sc === this.ec && this.so === this.eo
                    }
                }, {
                    key: "wrapBodyInlineWithPara",
                    value: function() {
                        if (dt.isBodyContainer(this.sc) && dt.isEmpty(this.sc)) return this.sc.innerHTML = dt.emptyPara, new t(this.sc.firstChild, 0, this.sc.firstChild, 0);
                        var e, o = this.normalize();
                        if (dt.isParaInline(this.sc) || dt.isPara(this.sc)) return o;
                        if (dt.isInline(o.sc)) {
                            var n = dt.listAncestor(o.sc, m.not(dt.isInline));
                            e = y.last(n), dt.isInline(e) || (e = n[n.length - 2] || o.sc.childNodes[o.so])
                        } else e = o.sc.childNodes[o.so > 0 ? o.so - 1 : 0];
                        if (e) {
                            var i = dt.listPrev(e, dt.isParaInline).reverse();
                            if ((i = i.concat(dt.listNext(e.nextSibling, dt.isParaInline))).length) {
                                var r = dt.wrap(y.head(i), "p");
                                dt.appendChildNodes(r, y.tail(i))
                            }
                        }
                        return this.normalize()
                    }
                }, {
                    key: "insertNode",
                    value: function(t) {
                        var e = this;
                        (dt.isText(t) || dt.isInline(t)) && (e = this.wrapBodyInlineWithPara().deleteContents());
                        var o = dt.splitPoint(e.getStartPoint(), dt.isInline(t));
                        return o.rightNode ? (o.rightNode.parentNode.insertBefore(t, o.rightNode), dt.isEmpty(o.rightNode) && dt.isPara(t) && o.rightNode.parentNode.removeChild(o.rightNode)) : o.container.appendChild(t), t
                    }
                }, {
                    key: "pasteHTML",
                    value: function(t) {
                        t = e().trim(t);
                        var o = e()("<div></div>").html(t)[0],
                            n = y.from(o.childNodes),
                            i = this,
                            r = !1;
                        return i.so >= 0 && (n = n.reverse(), r = !0), n = n.map((function(t) {
                            return i.insertNode(t)
                        })), r && (n = n.reverse()), n
                    }
                }, {
                    key: "toString",
                    value: function() {
                        var t = this.nativeRange();
                        return f.isW3CRangeSupport ? t.toString() : t.text
                    }
                }, {
                    key: "getWordRange",
                    value: function(e) {
                        var o = this.getEndPoint();
                        if (!dt.isCharPoint(o)) return this;
                        var n = dt.prevPointUntil(o, (function(t) {
                            return !dt.isCharPoint(t)
                        }));
                        return e && (o = dt.nextPointUntil(o, (function(t) {
                            return !dt.isCharPoint(t)
                        }))), new t(n.node, n.offset, o.node, o.offset)
                    }
                }, {
                    key: "getWordsRange",
                    value: function(e) {
                        var o = this.getEndPoint(),
                            n = function(t) {
                                return !dt.isCharPoint(t) && !dt.isSpacePoint(t)
                            };
                        if (n(o)) return this;
                        var i = dt.prevPointUntil(o, n);
                        return e && (o = dt.nextPointUntil(o, n)), new t(i.node, i.offset, o.node, o.offset)
                    }
                }, {
                    key: "getWordsMatchRange",
                    value: function(e) {
                        var o = this.getEndPoint(),
                            n = dt.prevPointUntil(o, (function(n) {
                                if (!dt.isCharPoint(n) && !dt.isSpacePoint(n)) return !0;
                                var i = new t(n.node, n.offset, o.node, o.offset),
                                    r = e.exec(i.toString());
                                return r && 0 === r.index
                            })),
                            i = new t(n.node, n.offset, o.node, o.offset),
                            r = i.toString(),
                            a = e.exec(r);
                        return a && a[0].length === r.length ? i : null
                    }
                }, {
                    key: "bookmark",
                    value: function(t) {
                        return {
                            s: {
                                path: dt.makeOffsetPath(t, this.sc),
                                offset: this.so
                            },
                            e: {
                                path: dt.makeOffsetPath(t, this.ec),
                                offset: this.eo
                            }
                        }
                    }
                }, {
                    key: "paraBookmark",
                    value: function(t) {
                        return {
                            s: {
                                path: y.tail(dt.makeOffsetPath(y.head(t), this.sc)),
                                offset: this.so
                            },
                            e: {
                                path: y.tail(dt.makeOffsetPath(y.last(t), this.ec)),
                                offset: this.eo
                            }
                        }
                    }
                }, {
                    key: "getClientRects",
                    value: function() {
                        return this.nativeRange().getClientRects()
                    }
                }]) && pt(o.prototype, n), i && pt(o, i), t
            }();
            const bt = {
                create: function(t, e, o, n) {
                    if (4 === arguments.length) return new gt(t, e, o, n);
                    if (2 === arguments.length) return new gt(t, e, o = t, n = e);
                    var i = this.createFromSelection();
                    if (!i && 1 === arguments.length) {
                        var r = arguments[0];
                        return dt.isEditable(r) && (r = r.lastChild), this.createFromBodyElement(r, dt.emptyPara === arguments[0].innerHTML)
                    }
                    return i
                },
                createFromBodyElement: function(t) {
                    var e = arguments.length > 1 && void 0 !== arguments[1] && arguments[1],
                        o = this.createFromNode(t);
                    return o.collapse(e)
                },
                createFromSelection: function() {
                    var t, e, o, n;
                    if (f.isW3CRangeSupport) {
                        var i = document.getSelection();
                        if (!i || 0 === i.rangeCount) return null;
                        if (dt.isBody(i.anchorNode)) return null;
                        var r = i.getRangeAt(0);
                        t = r.startContainer, e = r.startOffset, o = r.endContainer, n = r.endOffset
                    } else {
                        var a = document.selection.createRange(),
                            s = a.duplicate();
                        s.collapse(!1);
                        var l = a;
                        l.collapse(!0);
                        var c = mt(l, !0),
                            u = mt(s, !1);
                        dt.isText(c.node) && dt.isLeftEdgePoint(c) && dt.isTextNode(u.node) && dt.isRightEdgePoint(u) && u.node.nextSibling === c.node && (c = u), t = c.cont, e = c.offset, o = u.cont, n = u.offset
                    }
                    return new gt(t, e, o, n)
                },
                createFromNode: function(t) {
                    var e = t,
                        o = 0,
                        n = t,
                        i = dt.nodeLength(n);
                    return dt.isVoid(e) && (o = dt.listPrev(e).length - 1, e = e.parentNode), dt.isBR(n) ? (i = dt.listPrev(n).length - 1, n = n.parentNode) : dt.isVoid(n) && (i = dt.listPrev(n).length, n = n.parentNode), this.create(e, o, n, i)
                },
                createFromNodeBefore: function(t) {
                    return this.createFromNode(t).collapse(!0)
                },
                createFromNodeAfter: function(t) {
                    return this.createFromNode(t).collapse()
                },
                createFromBookmark: function(t, e) {
                    var o = dt.fromOffsetPath(t, e.s.path),
                        n = e.s.offset,
                        i = dt.fromOffsetPath(t, e.e.path),
                        r = e.e.offset;
                    return new gt(o, n, i, r)
                },
                createFromParaBookmark: function(t, e) {
                    var o = t.s.offset,
                        n = t.e.offset,
                        i = dt.fromOffsetPath(y.head(e), t.s.path),
                        r = dt.fromOffsetPath(y.last(e), t.e.path);
                    return new gt(i, o, r, n)
                }
            };
            var kt = {
                BACKSPACE: 8,
                TAB: 9,
                ENTER: 13,
                ESCAPE: 27,
                SPACE: 32,
                DELETE: 46,
                LEFT: 37,
                UP: 38,
                RIGHT: 39,
                DOWN: 40,
                NUM0: 48,
                NUM1: 49,
                NUM2: 50,
                NUM3: 51,
                NUM4: 52,
                NUM5: 53,
                NUM6: 54,
                NUM7: 55,
                NUM8: 56,
                B: 66,
                E: 69,
                I: 73,
                J: 74,
                K: 75,
                L: 76,
                R: 82,
                S: 83,
                U: 85,
                V: 86,
                Y: 89,
                Z: 90,
                SLASH: 191,
                LEFTBRACKET: 219,
                BACKSLASH: 220,
                RIGHTBRACKET: 221,
                HOME: 36,
                END: 35,
                PAGEUP: 33,
                PAGEDOWN: 34
            };
            const yt = {
                isEdit: function(t) {
                    return y.contains([kt.BACKSPACE, kt.TAB, kt.ENTER, kt.SPACE, kt.DELETE], t)
                },
                isMove: function(t) {
                    return y.contains([kt.LEFT, kt.UP, kt.RIGHT, kt.DOWN], t)
                },
                isNavigation: function(t) {
                    return y.contains([kt.HOME, kt.END, kt.PAGEUP, kt.PAGEDOWN], t)
                },
                nameFromCode: m.invertObject(kt),
                code: kt
            };

            function wt(t, e) {
                for (var o = 0; o < e.length; o++) {
                    var n = e[o];
                    n.enumerable = n.enumerable || !1, n.configurable = !0, "value" in n && (n.writable = !0), Object.defineProperty(t, n.key, n)
                }
            }
            var Ct = function() {
                function t(e) {
                    ! function(t, e) {
                        if (!(t instanceof e)) throw new TypeError("Cannot call a class as a function")
                    }(this, t), this.stack = [], this.stackOffset = -1, this.context = e, this.$editable = e.layoutInfo.editable, this.editable = this.$editable[0]
                }
                var e, o, n;
                return e = t, (o = [{
                    key: "makeSnapshot",
                    value: function() {
                        var t = bt.create(this.editable);
                        return {
                            contents: this.$editable.html(),
                            bookmark: t && t.isOnEditable() ? t.bookmark(this.editable) : {
                                s: {
                                    path: [],
                                    offset: 0
                                },
                                e: {
                                    path: [],
                                    offset: 0
                                }
                            }
                        }
                    }
                }, {
                    key: "applySnapshot",
                    value: function(t) {
                        null !== t.contents && this.$editable.html(t.contents), null !== t.bookmark && bt.createFromBookmark(this.editable, t.bookmark).select()
                    }
                }, {
                    key: "rewind",
                    value: function() {
                        this.$editable.html() !== this.stack[this.stackOffset].contents && this.recordUndo(), this.stackOffset = 0, this.applySnapshot(this.stack[this.stackOffset])
                    }
                }, {
                    key: "commit",
                    value: function() {
                        this.stack = [], this.stackOffset = -1, this.recordUndo()
                    }
                }, {
                    key: "reset",
                    value: function() {
                        this.stack = [], this.stackOffset = -1, this.$editable.html(""), this.recordUndo()
                    }
                }, {
                    key: "undo",
                    value: function() {
                        this.$editable.html() !== this.stack[this.stackOffset].contents && this.recordUndo(), this.stackOffset > 0 && (this.stackOffset--, this.applySnapshot(this.stack[this.stackOffset]))
                    }
                }, {
                    key: "redo",
                    value: function() {
                        this.stack.length - 1 > this.stackOffset && (this.stackOffset++, this.applySnapshot(this.stack[this.stackOffset]))
                    }
                }, {
                    key: "recordUndo",
                    value: function() {
                        this.stackOffset++, this.stack.length > this.stackOffset && (this.stack = this.stack.slice(0, this.stackOffset)), this.stack.push(this.makeSnapshot()), this.stack.length > this.context.options.historyLimit && (this.stack.shift(), this.stackOffset -= 1)
                    }
                }]) && wt(e.prototype, o), n && wt(e, n), t
            }();

            function xt(t, e) {
                for (var o = 0; o < e.length; o++) {
                    var n = e[o];
                    n.enumerable = n.enumerable || !1, n.configurable = !0, "value" in n && (n.writable = !0), Object.defineProperty(t, n.key, n)
                }
            }
            var St = function() {
                function t() {
                    ! function(t, e) {
                        if (!(t instanceof e)) throw new TypeError("Cannot call a class as a function")
                    }(this, t)
                }
                var o, n, i;
                return o = t, (n = [{
                    key: "jQueryCSS",
                    value: function(t, o) {
                        var n = {};
                        return e().each(o, (function(e, o) {
                            n[o] = t.css(o)
                        })), n
                    }
                }, {
                    key: "fromNode",
                    value: function(t) {
                        var e = this.jQueryCSS(t, ["font-family", "font-size", "text-align", "list-style-type", "line-height"]) || {},
                            o = t[0].style.fontSize || e["font-size"];
                        return e["font-size"] = parseInt(o, 10), e["font-size-unit"] = o.match(/[a-z%]+$/), e
                    }
                }, {
                    key: "stylePara",
                    value: function(t, o) {
                        e().each(t.nodes(dt.isPara, {
                            includeAncestor: !0
                        }), (function(t, n) {
                            e()(n).css(o)
                        }))
                    }
                }, {
                    key: "styleNodes",
                    value: function(t, o) {
                        t = t.splitText();
                        var n = o && o.nodeName || "SPAN",
                            i = !(!o || !o.expandClosestSibling),
                            r = !(!o || !o.onlyPartialContains);
                        if (t.isCollapsed()) return [t.insertNode(dt.create(n))];
                        var a = dt.makePredByNodeName(n),
                            s = t.nodes(dt.isText, {
                                fullyContains: !0
                            }).map((function(t) {
                                return dt.singleChildAncestor(t, a) || dt.wrap(t, n)
                            }));
                        if (i) {
                            if (r) {
                                var l = t.nodes();
                                a = m.and(a, (function(t) {
                                    return y.contains(l, t)
                                }))
                            }
                            return s.map((function(t) {
                                var o = dt.withClosestSiblings(t, a),
                                    n = y.head(o),
                                    i = y.tail(o);
                                return e().each(i, (function(t, e) {
                                    dt.appendChildNodes(n, e.childNodes), dt.remove(e)
                                })), y.head(o)
                            }))
                        }
                        return s
                    }
                }, {
                    key: "current",
                    value: function(t) {
                        var o = e()(dt.isElement(t.sc) ? t.sc : t.sc.parentNode),
                            n = this.fromNode(o);
                        try {
                            n = e().extend(n, {
                                "font-bold": document.queryCommandState("bold") ? "bold" : "normal",
                                "font-italic": document.queryCommandState("italic") ? "italic" : "normal",
                                "font-underline": document.queryCommandState("underline") ? "underline" : "normal",
                                "font-subscript": document.queryCommandState("subscript") ? "subscript" : "normal",
                                "font-superscript": document.queryCommandState("superscript") ? "superscript" : "normal",
                                "font-strikethrough": document.queryCommandState("strikethrough") ? "strikethrough" : "normal",
                                "font-family": document.queryCommandValue("fontname") || n["font-family"]
                            })
                        } catch (t) {}
                        if (t.isOnList()) {
                            var i = ["circle", "disc", "disc-leading-zero", "square"].indexOf(n["list-style-type"]) > -1;
                            n["list-style"] = i ? "unordered" : "ordered"
                        } else n["list-style"] = "none";
                        var r = dt.ancestor(t.sc, dt.isPara);
                        if (r && r.style["line-height"]) n["line-height"] = r.style.lineHeight;
                        else {
                            var a = parseInt(n["line-height"], 10) / parseInt(n["font-size"], 10);
                            n["line-height"] = a.toFixed(1)
                        }
                        return n.anchor = t.isOnAnchor() && dt.ancestor(t.sc, dt.isAnchor), n.ancestors = dt.listAncestor(t.sc, dt.isEditable), n.range = t, n
                    }
                }]) && xt(o.prototype, n), i && xt(o, i), t
            }();

            function Tt(t, e) {
                for (var o = 0; o < e.length; o++) {
                    var n = e[o];
                    n.enumerable = n.enumerable || !1, n.configurable = !0, "value" in n && (n.writable = !0), Object.defineProperty(t, n.key, n)
                }
            }
            var Et = function() {
                function t() {
                    ! function(t, e) {
                        if (!(t instanceof e)) throw new TypeError("Cannot call a class as a function")
                    }(this, t)
                }
                var o, n, i;
                return o = t, (n = [{
                    key: "insertOrderedList",
                    value: function(t) {
                        this.toggleList("OL", t)
                    }
                }, {
                    key: "insertUnorderedList",
                    value: function(t) {
                        this.toggleList("UL", t)
                    }
                }, {
                    key: "indent",
                    value: function(t) {
                        var o = this,
                            n = bt.create(t).wrapBodyInlineWithPara(),
                            i = n.nodes(dt.isPara, {
                                includeAncestor: !0
                            }),
                            r = y.clusterBy(i, m.peq2("parentNode"));
                        e().each(r, (function(t, n) {
                            var i = y.head(n);
                            if (dt.isLi(i)) {
                                var r = o.findList(i.previousSibling);
                                r ? n.map((function(t) {
                                    return r.appendChild(t)
                                })) : (o.wrapList(n, i.parentNode.nodeName), n.map((function(t) {
                                    return t.parentNode
                                })).map((function(t) {
                                    return o.appendToPrevious(t)
                                })))
                            } else e().each(n, (function(t, o) {
                                e()(o).css("marginLeft", (function(t, e) {
                                    return (parseInt(e, 10) || 0) + 25
                                }))
                            }))
                        })), n.select()
                    }
                }, {
                    key: "outdent",
                    value: function(t) {
                        var o = this,
                            n = bt.create(t).wrapBodyInlineWithPara(),
                            i = n.nodes(dt.isPara, {
                                includeAncestor: !0
                            }),
                            r = y.clusterBy(i, m.peq2("parentNode"));
                        e().each(r, (function(t, n) {
                            var i = y.head(n);
                            dt.isLi(i) ? o.releaseList([n]) : e().each(n, (function(t, o) {
                                e()(o).css("marginLeft", (function(t, e) {
                                    return (e = parseInt(e, 10) || 0) > 25 ? e - 25 : ""
                                }))
                            }))
                        })), n.select()
                    }
                }, {
                    key: "toggleList",
                    value: function(t, o) {
                        var n = this,
                            i = bt.create(o).wrapBodyInlineWithPara(),
                            r = i.nodes(dt.isPara, {
                                includeAncestor: !0
                            }),
                            a = i.paraBookmark(r),
                            s = y.clusterBy(r, m.peq2("parentNode"));
                        if (y.find(r, dt.isPurePara)) {
                            var l = [];
                            e().each(s, (function(e, o) {
                                l = l.concat(n.wrapList(o, t))
                            })), r = l
                        } else {
                            var c = i.nodes(dt.isList, {
                                includeAncestor: !0
                            }).filter((function(o) {
                                return !e().nodeName(o, t)
                            }));
                            c.length ? e().each(c, (function(e, o) {
                                dt.replace(o, t)
                            })) : r = this.releaseList(s, !0)
                        }
                        bt.createFromParaBookmark(a, r).select()
                    }
                }, {
                    key: "wrapList",
                    value: function(t, e) {
                        var o = y.head(t),
                            n = y.last(t),
                            i = dt.isList(o.previousSibling) && o.previousSibling,
                            r = dt.isList(n.nextSibling) && n.nextSibling,
                            a = i || dt.insertAfter(dt.create(e || "UL"), n);
                        return t = t.map((function(t) {
                            return dt.isPurePara(t) ? dt.replace(t, "LI") : t
                        })), dt.appendChildNodes(a, t), r && (dt.appendChildNodes(a, y.from(r.childNodes)), dt.remove(r)), t
                    }
                }, {
                    key: "releaseList",
                    value: function(t, o) {
                        var n = this,
                            i = [];
                        return e().each(t, (function(t, r) {
                            var a = y.head(r),
                                s = y.last(r),
                                l = o ? dt.lastAncestor(a, dt.isList) : a.parentNode,
                                c = l.parentNode;
                            if ("LI" === l.parentNode.nodeName) r.map((function(t) {
                                var e = n.findNextSiblings(t);
                                c.nextSibling ? c.parentNode.insertBefore(t, c.nextSibling) : c.parentNode.appendChild(t), e.length && (n.wrapList(e, l.nodeName), t.appendChild(e[0].parentNode))
                            })), 0 === l.children.length && c.removeChild(l), 0 === c.childNodes.length && c.parentNode.removeChild(c);
                            else {
                                var u = l.childNodes.length > 1 ? dt.splitTree(l, {
                                        node: s.parentNode,
                                        offset: dt.position(s) + 1
                                    }, {
                                        isSkipPaddingBlankHTML: !0
                                    }) : null,
                                    d = dt.splitTree(l, {
                                        node: a.parentNode,
                                        offset: dt.position(a)
                                    }, {
                                        isSkipPaddingBlankHTML: !0
                                    });
                                r = o ? dt.listDescendant(d, dt.isLi) : y.from(d.childNodes).filter(dt.isLi), !o && dt.isList(l.parentNode) || (r = r.map((function(t) {
                                    return dt.replace(t, "P")
                                }))), e().each(y.from(r).reverse(), (function(t, e) {
                                    dt.insertAfter(e, l)
                                }));
                                var h = y.compact([l, d, u]);
                                e().each(h, (function(t, o) {
                                    var n = [o].concat(dt.listDescendant(o, dt.isList));
                                    e().each(n.reverse(), (function(t, e) {
                                        dt.nodeLength(e) || dt.remove(e, !0)
                                    }))
                                }))
                            }
                            i = i.concat(r)
                        })), i
                    }
                }, {
                    key: "appendToPrevious",
                    value: function(t) {
                        return t.previousSibling ? dt.appendChildNodes(t.previousSibling, [t]) : this.wrapList([t], "LI")
                    }
                }, {
                    key: "findList",
                    value: function(t) {
                        return t ? y.find(t.children, (function(t) {
                            return ["OL", "UL"].indexOf(t.nodeName) > -1
                        })) : null
                    }
                }, {
                    key: "findNextSiblings",
                    value: function(t) {
                        for (var e = []; t.nextSibling;) e.push(t.nextSibling), t = t.nextSibling;
                        return e
                    }
                }]) && Tt(o.prototype, n), i && Tt(o, i), t
            }();

            function $t(t, e) {
                for (var o = 0; o < e.length; o++) {
                    var n = e[o];
                    n.enumerable = n.enumerable || !1, n.configurable = !0, "value" in n && (n.writable = !0), Object.defineProperty(t, n.key, n)
                }
            }
            var Nt = function() {
                function t(e) {
                    ! function(t, e) {
                        if (!(t instanceof e)) throw new TypeError("Cannot call a class as a function")
                    }(this, t), this.bullet = new Et, this.options = e.options
                }
                var o, n, i;
                return o = t, (n = [{
                    key: "insertTab",
                    value: function(t, e) {
                        var o = dt.createText(new Array(e + 1).join(dt.NBSP_CHAR));
                        (t = t.deleteContents()).insertNode(o, !0), (t = bt.create(o, e)).select()
                    }
                }, {
                    key: "insertParagraph",
                    value: function(t, o) {
                        o = (o = (o = o || bt.create(t)).deleteContents()).wrapBodyInlineWithPara();
                        var n, i = dt.ancestor(o.sc, dt.isPara);
                        if (i) {
                            if (dt.isLi(i) && (dt.isEmpty(i) || dt.deepestChildIsEmpty(i))) return void this.bullet.toggleList(i.parentNode.nodeName);
                            var r = null;
                            if (1 === this.options.blockquoteBreakingLevel ? r = dt.ancestor(i, dt.isBlockquote) : 2 === this.options.blockquoteBreakingLevel && (r = dt.lastAncestor(i, dt.isBlockquote)), r) {
                                n = e()(dt.emptyPara)[0], dt.isRightEdgePoint(o.getStartPoint()) && dt.isBR(o.sc.nextSibling) && e()(o.sc.nextSibling).remove();
                                var a = dt.splitTree(r, o.getStartPoint(), {
                                    isDiscardEmptySplits: !0
                                });
                                a ? a.parentNode.insertBefore(n, a) : dt.insertAfter(n, r)
                            } else {
                                n = dt.splitTree(i, o.getStartPoint());
                                var s = dt.listDescendant(i, dt.isEmptyAnchor);
                                s = s.concat(dt.listDescendant(n, dt.isEmptyAnchor)), e().each(s, (function(t, e) {
                                    dt.remove(e)
                                })), (dt.isHeading(n) || dt.isPre(n) || dt.isCustomStyleTag(n)) && dt.isEmpty(n) && (n = dt.replace(n, "p"))
                            }
                        } else {
                            var l = o.sc.childNodes[o.so];
                            n = e()(dt.emptyPara)[0], l ? o.sc.insertBefore(n, l) : o.sc.appendChild(n)
                        }
                        bt.create(n, 0).normalize().select().scrollIntoView(t)
                    }
                }]) && $t(o.prototype, n), i && $t(o, i), t
            }();

            function It(t, e) {
                for (var o = 0; o < e.length; o++) {
                    var n = e[o];
                    n.enumerable = n.enumerable || !1, n.configurable = !0, "value" in n && (n.writable = !0), Object.defineProperty(t, n.key, n)
                }
            }
            var Pt = function t(e, o, n, i) {
                var r = {
                        colPos: 0,
                        rowPos: 0
                    },
                    a = [],
                    s = [];

                function l(t, e, o, n, i, r, s) {
                    var l = {
                        baseRow: o,
                        baseCell: n,
                        isRowSpan: i,
                        isColSpan: r,
                        isVirtual: s
                    };
                    a[t] || (a[t] = []), a[t][e] = l
                }

                function c(t, e, o, n) {
                    return {
                        baseCell: t.baseCell,
                        action: e,
                        virtualTable: {
                            rowIndex: o,
                            cellIndex: n
                        }
                    }
                }

                function u(t, e) {
                    if (!a[t]) return e;
                    if (!a[t][e]) return e;
                    for (var o = e; a[t][o];)
                        if (o++, !a[t][o]) return o
                }

                function d(t, e) {
                    var o = u(t.rowIndex, e.cellIndex),
                        n = e.colSpan > 1,
                        i = e.rowSpan > 1,
                        a = t.rowIndex === r.rowPos && e.cellIndex === r.colPos;
                    l(t.rowIndex, o, t, e, i, n, !1);
                    var s = e.attributes.rowSpan ? parseInt(e.attributes.rowSpan.value, 10) : 0;
                    if (s > 1)
                        for (var c = 1; c < s; c++) {
                            var d = t.rowIndex + c;
                            h(d, o, e, a), l(d, o, t, e, !0, n, !0)
                        }
                    var f = e.attributes.colSpan ? parseInt(e.attributes.colSpan.value, 10) : 0;
                    if (f > 1)
                        for (var p = 1; p < f; p++) {
                            var m = u(t.rowIndex, o + p);
                            h(t.rowIndex, m, e, a), l(t.rowIndex, m, t, e, i, !0, !0)
                        }
                }

                function h(t, e, o, n) {
                    t === r.rowPos && r.colPos >= o.cellIndex && o.cellIndex <= e && !n && r.colPos++
                }

                function f(e) {
                    switch (o) {
                        case t.where.Column:
                            if (e.isColSpan) return t.resultAction.SubtractSpanCount;
                            break;
                        case t.where.Row:
                            if (!e.isVirtual && e.isRowSpan) return t.resultAction.AddCell;
                            if (e.isRowSpan) return t.resultAction.SubtractSpanCount
                    }
                    return t.resultAction.RemoveCell
                }

                function p(e) {
                    switch (o) {
                        case t.where.Column:
                            if (e.isColSpan) return t.resultAction.SumSpanCount;
                            if (e.isRowSpan && e.isVirtual) return t.resultAction.Ignore;
                            break;
                        case t.where.Row:
                            if (e.isRowSpan) return t.resultAction.SumSpanCount;
                            if (e.isColSpan && e.isVirtual) return t.resultAction.Ignore
                    }
                    return t.resultAction.AddCell
                }
                this.getActionList = function() {
                        for (var e = o === t.where.Row ? r.rowPos : -1, i = o === t.where.Column ? r.colPos : -1, l = 0, u = !0; u;) {
                            var d = e >= 0 ? e : l,
                                h = i >= 0 ? i : l,
                                m = a[d];
                            if (!m) return u = !1, s;
                            var v = m[h];
                            if (!v) return u = !1, s;
                            var g = t.resultAction.Ignore;
                            switch (n) {
                                case t.requestAction.Add:
                                    g = p(v);
                                    break;
                                case t.requestAction.Delete:
                                    g = f(v)
                            }
                            s.push(c(v, g, d, h)), l++
                        }
                        return s
                    }, e && e.tagName && ("td" === e.tagName.toLowerCase() || "th" === e.tagName.toLowerCase()) && (r.colPos = e.cellIndex, e.parentElement && e.parentElement.tagName && "tr" === e.parentElement.tagName.toLowerCase() && (r.rowPos = e.parentElement.rowIndex)),
                    function() {
                        for (var t = i.rows, e = 0; e < t.length; e++)
                            for (var o = t[e].cells, n = 0; n < o.length; n++) d(t[e], o[n])
                    }()
            };
            Pt.where = {
                Row: 0,
                Column: 1
            }, Pt.requestAction = {
                Add: 0,
                Delete: 1
            }, Pt.resultAction = {
                Ignore: 0,
                SubtractSpanCount: 1,
                RemoveCell: 2,
                AddCell: 3,
                SumSpanCount: 4
            };
            var Rt = function() {
                function t() {
                    ! function(t, e) {
                        if (!(t instanceof e)) throw new TypeError("Cannot call a class as a function")
                    }(this, t)
                }
                var o, n, i;
                return o = t, (n = [{
                    key: "tab",
                    value: function(t, e) {
                        var o = dt.ancestor(t.commonAncestor(), dt.isCell),
                            n = dt.ancestor(o, dt.isTable),
                            i = dt.listDescendant(n, dt.isCell),
                            r = y[e ? "prev" : "next"](i, o);
                        r && bt.create(r, 0).select()
                    }
                }, {
                    key: "addRow",
                    value: function(t, o) {
                        for (var n = dt.ancestor(t.commonAncestor(), dt.isCell), i = e()(n).closest("tr"), r = this.recoverAttributes(i), a = e()("<tr" + r + "></tr>"), s = new Pt(n, Pt.where.Row, Pt.requestAction.Add, e()(i).closest("table")[0]).getActionList(), l = 0; l < s.length; l++) {
                            var c = s[l],
                                u = this.recoverAttributes(c.baseCell);
                            switch (c.action) {
                                case Pt.resultAction.AddCell:
                                    a.append("<td" + u + ">" + dt.blank + "</td>");
                                    break;
                                case Pt.resultAction.SumSpanCount:
                                    if ("top" === o && (c.baseCell.parent ? c.baseCell.closest("tr").rowIndex : 0) <= i[0].rowIndex) {
                                        var d = e()("<div></div>").append(e()("<td" + u + ">" + dt.blank + "</td>").removeAttr("rowspan")).html();
                                        a.append(d);
                                        break
                                    }
                                    var h = parseInt(c.baseCell.rowSpan, 10);
                                    h++, c.baseCell.setAttribute("rowSpan", h)
                            }
                        }
                        if ("top" === o) i.before(a);
                        else {
                            if (n.rowSpan > 1) {
                                var f = i[0].rowIndex + (n.rowSpan - 2);
                                return void e()(e()(i).parent().find("tr")[f]).after(e()(a))
                            }
                            i.after(a)
                        }
                    }
                }, {
                    key: "addCol",
                    value: function(t, o) {
                        var n = dt.ancestor(t.commonAncestor(), dt.isCell),
                            i = e()(n).closest("tr");
                        e()(i).siblings().push(i);
                        for (var r = new Pt(n, Pt.where.Column, Pt.requestAction.Add, e()(i).closest("table")[0]).getActionList(), a = 0; a < r.length; a++) {
                            var s = r[a],
                                l = this.recoverAttributes(s.baseCell);
                            switch (s.action) {
                                case Pt.resultAction.AddCell:
                                    "right" === o ? e()(s.baseCell).after("<td" + l + ">" + dt.blank + "</td>") : e()(s.baseCell).before("<td" + l + ">" + dt.blank + "</td>");
                                    break;
                                case Pt.resultAction.SumSpanCount:
                                    if ("right" === o) {
                                        var c = parseInt(s.baseCell.colSpan, 10);
                                        c++, s.baseCell.setAttribute("colSpan", c)
                                    } else e()(s.baseCell).before("<td" + l + ">" + dt.blank + "</td>")
                            }
                        }
                    }
                }, {
                    key: "recoverAttributes",
                    value: function(t) {
                        var e = "";
                        if (!t) return e;
                        for (var o = t.attributes || [], n = 0; n < o.length; n++) "id" !== o[n].name.toLowerCase() && o[n].specified && (e += " " + o[n].name + "='" + o[n].value + "'");
                        return e
                    }
                }, {
                    key: "deleteRow",
                    value: function(t) {
                        for (var o = dt.ancestor(t.commonAncestor(), dt.isCell), n = e()(o).closest("tr"), i = n.children("td, th").index(e()(o)), r = n[0].rowIndex, a = new Pt(o, Pt.where.Row, Pt.requestAction.Delete, e()(n).closest("table")[0]).getActionList(), s = 0; s < a.length; s++)
                            if (a[s]) {
                                var l = a[s].baseCell,
                                    c = a[s].virtualTable,
                                    u = l.rowSpan && l.rowSpan > 1,
                                    d = u ? parseInt(l.rowSpan, 10) : 0;
                                switch (a[s].action) {
                                    case Pt.resultAction.Ignore:
                                        continue;
                                    case Pt.resultAction.AddCell:
                                        var h = n.next("tr")[0];
                                        if (!h) continue;
                                        var f = n[0].cells[i];
                                        u && (d > 2 ? (d--, h.insertBefore(f, h.cells[i]), h.cells[i].setAttribute("rowSpan", d), h.cells[i].innerHTML = "") : 2 === d && (h.insertBefore(f, h.cells[i]), h.cells[i].removeAttribute("rowSpan"), h.cells[i].innerHTML = ""));
                                        continue;
                                    case Pt.resultAction.SubtractSpanCount:
                                        u && (d > 2 ? (d--, l.setAttribute("rowSpan", d), c.rowIndex !== r && l.cellIndex === i && (l.innerHTML = "")) : 2 === d && (l.removeAttribute("rowSpan"), c.rowIndex !== r && l.cellIndex === i && (l.innerHTML = "")));
                                        continue;
                                    case Pt.resultAction.RemoveCell:
                                        continue
                                }
                            } n.remove()
                    }
                }, {
                    key: "deleteCol",
                    value: function(t) {
                        for (var o = dt.ancestor(t.commonAncestor(), dt.isCell), n = e()(o).closest("tr"), i = n.children("td, th").index(e()(o)), r = new Pt(o, Pt.where.Column, Pt.requestAction.Delete, e()(n).closest("table")[0]).getActionList(), a = 0; a < r.length; a++)
                            if (r[a]) switch (r[a].action) {
                                case Pt.resultAction.Ignore:
                                    continue;
                                case Pt.resultAction.SubtractSpanCount:
                                    var s = r[a].baseCell;
                                    if (s.colSpan && s.colSpan > 1) {
                                        var l = s.colSpan ? parseInt(s.colSpan, 10) : 0;
                                        l > 2 ? (l--, s.setAttribute("colSpan", l), s.cellIndex === i && (s.innerHTML = "")) : 2 === l && (s.removeAttribute("colSpan"), s.cellIndex === i && (s.innerHTML = ""))
                                    }
                                    continue;
                                case Pt.resultAction.RemoveCell:
                                    dt.remove(r[a].baseCell, !0);
                                    continue
                            }
                    }
                }, {
                    key: "createTable",
                    value: function(t, o, n) {
                        for (var i, r = [], a = 0; a < t; a++) r.push("<td>" + dt.blank + "</td>");
                        i = r.join("");
                        for (var s, l = [], c = 0; c < o; c++) l.push("<tr>" + i + "</tr>");
                        s = l.join("");
                        var u = e()("<table>" + s + "</table>");
                        return n && n.tableClassName && u.addClass(n.tableClassName), u[0]
                    }
                }, {
                    key: "deleteTable",
                    value: function(t) {
                        var o = dt.ancestor(t.commonAncestor(), dt.isCell);
                        e()(o).closest("table").remove()
                    }
                }]) && It(o.prototype, n), i && It(o, i), t
            }();

            function Lt(t, e) {
                for (var o = 0; o < e.length; o++) {
                    var n = e[o];
                    n.enumerable = n.enumerable || !1, n.configurable = !0, "value" in n && (n.writable = !0), Object.defineProperty(t, n.key, n)
                }
            }
            var At = function() {
                function t(o) {
                    var n = this;
                    ! function(t, e) {
                        if (!(t instanceof e)) throw new TypeError("Cannot call a class as a function")
                    }(this, t), this.context = o, this.$note = o.layoutInfo.note, this.$editor = o.layoutInfo.editor, this.$editable = o.layoutInfo.editable, this.options = o.options, this.lang = this.options.langInfo, this.editable = this.$editable[0], this.lastRange = null, this.snapshot = null, this.style = new St, this.table = new Rt, this.typing = new Nt(o), this.bullet = new Et, this.history = new Ct(o), this.context.memo("help.escape", this.lang.help.escape), this.context.memo("help.undo", this.lang.help.undo), this.context.memo("help.redo", this.lang.help.redo), this.context.memo("help.tab", this.lang.help.tab), this.context.memo("help.untab", this.lang.help.untab), this.context.memo("help.insertParagraph", this.lang.help.insertParagraph), this.context.memo("help.insertOrderedList", this.lang.help.insertOrderedList), this.context.memo("help.insertUnorderedList", this.lang.help.insertUnorderedList), this.context.memo("help.indent", this.lang.help.indent), this.context.memo("help.outdent", this.lang.help.outdent), this.context.memo("help.formatPara", this.lang.help.formatPara), this.context.memo("help.insertHorizontalRule", this.lang.help.insertHorizontalRule), this.context.memo("help.fontName", this.lang.help.fontName);
                    for (var i = ["bold", "italic", "underline", "strikethrough", "superscript", "subscript", "justifyLeft", "justifyCenter", "justifyRight", "justifyFull", "formatBlock", "removeFormat", "backColor"], r = 0, a = i.length; r < a; r++) this[i[r]] = function(t) {
                        return function(e) {
                            n.beforeCommand(), document.execCommand(t, !1, e), n.afterCommand(!0)
                        }
                    }(i[r]), this.context.memo("help." + i[r], this.lang.help[i[r]]);
                    this.fontName = this.wrapCommand((function(t) {
                        return n.fontStyling("font-family", f.validFontName(t))
                    })), this.fontSize = this.wrapCommand((function(t) {
                        var e = n.currentStyle()["font-size-unit"];
                        return n.fontStyling("font-size", t + e)
                    })), this.fontSizeUnit = this.wrapCommand((function(t) {
                        var e = n.currentStyle()["font-size"];
                        return n.fontStyling("font-size", e + t)
                    }));
                    for (var s = 1; s <= 6; s++) this["formatH" + s] = function(t) {
                        return function() {
                            n.formatBlock("H" + t)
                        }
                    }(s), this.context.memo("help.formatH" + s, this.lang.help["formatH" + s]);
                    this.insertParagraph = this.wrapCommand((function() {
                        n.typing.insertParagraph(n.editable)
                    })), this.insertOrderedList = this.wrapCommand((function() {
                        n.bullet.insertOrderedList(n.editable)
                    })), this.insertUnorderedList = this.wrapCommand((function() {
                        n.bullet.insertUnorderedList(n.editable)
                    })), this.indent = this.wrapCommand((function() {
                        n.bullet.indent(n.editable)
                    })), this.outdent = this.wrapCommand((function() {
                        n.bullet.outdent(n.editable)
                    })), this.insertNode = this.wrapCommand((function(t) {
                        n.isLimited(e()(t).text().length) || (n.getLastRange().insertNode(t), n.setLastRange(bt.createFromNodeAfter(t).select()))
                    })), this.insertText = this.wrapCommand((function(t) {
                        if (!n.isLimited(t.length)) {
                            var e = n.getLastRange().insertNode(dt.createText(t));
                            n.setLastRange(bt.create(e, dt.nodeLength(e)).select())
                        }
                    })), this.pasteHTML = this.wrapCommand((function(t) {
                        if (!n.isLimited(t.length)) {
                            t = n.context.invoke("codeview.purify", t);
                            var e = n.getLastRange().pasteHTML(t);
                            n.setLastRange(bt.createFromNodeAfter(y.last(e)).select())
                        }
                    })), this.formatBlock = this.wrapCommand((function(t, e) {
                        var o = n.options.callbacks.onApplyCustomStyle;
                        o ? o.call(n, e, n.context, n.onFormatBlock) : n.onFormatBlock(t, e)
                    })), this.insertHorizontalRule = this.wrapCommand((function() {
                        var t = n.getLastRange().insertNode(dt.create("HR"));
                        t.nextSibling && n.setLastRange(bt.create(t.nextSibling, 0).normalize().select())
                    })), this.lineHeight = this.wrapCommand((function(t) {
                        n.style.stylePara(n.getLastRange(), {
                            lineHeight: t
                        })
                    })), this.createLink = this.wrapCommand((function(t) {
                        var o = t.url,
                            i = t.text,
                            r = t.isNewWindow,
                            a = t.checkProtocol,
                            s = t.range || n.getLastRange(),
                            l = i.length - s.toString().length;
                        if (!(l > 0 && n.isLimited(l))) {
                            var c = s.toString() !== i;
                            "string" == typeof o && (o = o.trim()), n.options.onCreateLink ? o = n.options.onCreateLink(o) : a && (o = /^([A-Za-z][A-Za-z0-9+-.]*\:|#|\/)/.test(o) ? o : n.options.defaultProtocol + o);
                            var u = [];
                            if (c) {
                                var d = (s = s.deleteContents()).insertNode(e()("<A>" + i + "</A>")[0]);
                                u.push(d)
                            } else u = n.style.styleNodes(s, {
                                nodeName: "A",
                                expandClosestSibling: !0,
                                onlyPartialContains: !0
                            });
                            e().each(u, (function(t, n) {
                                e()(n).attr("href", o), r ? e()(n).attr("target", "_blank") : e()(n).removeAttr("target")
                            })), n.setLastRange(n.createRangeFromList(u).select())
                        }
                    })), this.color = this.wrapCommand((function(t) {
                        var e = t.foreColor,
                            o = t.backColor;
                        e && document.execCommand("foreColor", !1, e), o && document.execCommand("backColor", !1, o)
                    })), this.foreColor = this.wrapCommand((function(t) {
                        document.execCommand("foreColor", !1, t)
                    })), this.insertTable = this.wrapCommand((function(t) {
                        var e = t.split("x");
                        n.getLastRange().deleteContents().insertNode(n.table.createTable(e[0], e[1], n.options))
                    })), this.removeMedia = this.wrapCommand((function() {
                        var t = e()(n.restoreTarget()).parent();
                        t.closest("figure").length ? t.closest("figure").remove() : t = e()(n.restoreTarget()).detach(), n.context.triggerEvent("media.delete", t, n.$editable)
                    })), this.floatMe = this.wrapCommand((function(t) {
                        var o = e()(n.restoreTarget());
                        o.toggleClass("note-float-left", "left" === t), o.toggleClass("note-float-right", "right" === t), o.css("float", "none" === t ? "" : t)
                    })), this.resize = this.wrapCommand((function(t) {
                        var o = e()(n.restoreTarget());
                        0 === (t = parseFloat(t)) ? o.css("width", "") : o.css({
                            width: 100 * t + "%",
                            height: ""
                        })
                    }))
                }
                var o, n, i;
                return o = t, (n = [{
                    key: "initialize",
                    value: function() {
                        var t = this;
                        this.$editable.on("keydown", (function(e) {
                            if (e.keyCode === yt.code.ENTER && t.context.triggerEvent("enter", e), t.context.triggerEvent("keydown", e), t.snapshot = t.history.makeSnapshot(), t.hasKeyShortCut = !1, e.isDefaultPrevented() || (t.options.shortcuts ? t.hasKeyShortCut = t.handleKeyMap(e) : t.preventDefaultEditableShortCuts(e)), t.isLimited(1, e)) {
                                var o = t.getLastRange();
                                if (o.eo - o.so == 0) return !1
                            }
                            t.setLastRange(), t.options.recordEveryKeystroke && !1 === t.hasKeyShortCut && t.history.recordUndo()
                        })).on("keyup", (function(e) {
                            t.setLastRange(), t.context.triggerEvent("keyup", e)
                        })).on("focus", (function(e) {
                            t.setLastRange(), t.context.triggerEvent("focus", e)
                        })).on("blur", (function(e) {
                            t.context.triggerEvent("blur", e)
                        })).on("mousedown", (function(e) {
                            t.context.triggerEvent("mousedown", e)
                        })).on("mouseup", (function(e) {
                            t.setLastRange(), t.history.recordUndo(), t.context.triggerEvent("mouseup", e)
                        })).on("scroll", (function(e) {
                            t.context.triggerEvent("scroll", e)
                        })).on("paste", (function(e) {
                            t.setLastRange(), t.context.triggerEvent("paste", e)
                        })).on("input", (function() {
                            t.isLimited(0) && t.snapshot && t.history.applySnapshot(t.snapshot)
                        })), this.$editable.attr("spellcheck", this.options.spellCheck), this.$editable.attr("autocorrect", this.options.spellCheck), this.options.disableGrammar && this.$editable.attr("data-gramm", !1), this.$editable.html(dt.html(this.$note) || dt.emptyPara), this.$editable.on(f.inputEventName, m.debounce((function() {
                            t.context.triggerEvent("change", t.$editable.html(), t.$editable)
                        }), 10)), this.$editable.on("focusin", (function(e) {
                            t.context.triggerEvent("focusin", e)
                        })).on("focusout", (function(e) {
                            t.context.triggerEvent("focusout", e)
                        })), this.options.airMode ? this.options.overrideContextMenu && this.$editor.on("contextmenu", (function(e) {
                            return t.context.triggerEvent("contextmenu", e), !1
                        })) : (this.options.width && this.$editor.outerWidth(this.options.width), this.options.height && this.$editable.outerHeight(this.options.height), this.options.maxHeight && this.$editable.css("max-height", this.options.maxHeight), this.options.minHeight && this.$editable.css("min-height", this.options.minHeight)), this.history.recordUndo(), this.setLastRange()
                    }
                }, {
                    key: "destroy",
                    value: function() {
                        this.$editable.off()
                    }
                }, {
                    key: "handleKeyMap",
                    value: function(t) {
                        var e = this.options.keyMap[f.isMac ? "mac" : "pc"],
                            o = [];
                        t.metaKey && o.push("CMD"), t.ctrlKey && !t.altKey && o.push("CTRL"), t.shiftKey && o.push("SHIFT");
                        var n = yt.nameFromCode[t.keyCode];
                        n && o.push(n);
                        var i = e[o.join("+")];
                        if ("TAB" !== n || this.options.tabDisable)
                            if (i) {
                                if (!1 !== this.context.invoke(i)) return t.preventDefault(), !0
                            } else yt.isEdit(t.keyCode) && this.afterCommand();
                        else this.afterCommand();
                        return !1
                    }
                }, {
                    key: "preventDefaultEditableShortCuts",
                    value: function(t) {
                        (t.ctrlKey || t.metaKey) && y.contains([66, 73, 85], t.keyCode) && t.preventDefault()
                    }
                }, {
                    key: "isLimited",
                    value: function(t, e) {
                        return t = t || 0, (void 0 === e || !(yt.isMove(e.keyCode) || yt.isNavigation(e.keyCode) || e.ctrlKey || e.metaKey || y.contains([yt.code.BACKSPACE, yt.code.DELETE], e.keyCode))) && this.options.maxTextLength > 0 && this.$editable.text().length + t > this.options.maxTextLength
                    }
                }, {
                    key: "createRange",
                    value: function() {
                        return this.focus(), this.setLastRange(), this.getLastRange()
                    }
                }, {
                    key: "createRangeFromList",
                    value: function(t) {
                        var e = bt.createFromNodeBefore(y.head(t)).getStartPoint(),
                            o = bt.createFromNodeAfter(y.last(t)).getEndPoint();
                        return bt.create(e.node, e.offset, o.node, o.offset)
                    }
                }, {
                    key: "setLastRange",
                    value: function(t) {
                        t ? this.lastRange = t : (this.lastRange = bt.create(this.editable), 0 === e()(this.lastRange.sc).closest(".note-editable").length && (this.lastRange = bt.createFromBodyElement(this.editable)))
                    }
                }, {
                    key: "getLastRange",
                    value: function() {
                        return this.lastRange || this.setLastRange(), this.lastRange
                    }
                }, {
                    key: "saveRange",
                    value: function(t) {
                        t && this.getLastRange().collapse().select()
                    }
                }, {
                    key: "restoreRange",
                    value: function() {
                        this.lastRange && (this.lastRange.select(), this.focus())
                    }
                }, {
                    key: "saveTarget",
                    value: function(t) {
                        this.$editable.data("target", t)
                    }
                }, {
                    key: "clearTarget",
                    value: function() {
                        this.$editable.removeData("target")
                    }
                }, {
                    key: "restoreTarget",
                    value: function() {
                        return this.$editable.data("target")
                    }
                }, {
                    key: "currentStyle",
                    value: function() {
                        var t = bt.create();
                        return t && (t = t.normalize()), t ? this.style.current(t) : this.style.fromNode(this.$editable)
                    }
                }, {
                    key: "styleFromNode",
                    value: function(t) {
                        return this.style.fromNode(t)
                    }
                }, {
                    key: "undo",
                    value: function() {
                        this.context.triggerEvent("before.command", this.$editable.html()), this.history.undo(), this.context.triggerEvent("change", this.$editable.html(), this.$editable)
                    }
                }, {
                    key: "commit",
                    value: function() {
                        this.context.triggerEvent("before.command", this.$editable.html()), this.history.commit(), this.context.triggerEvent("change", this.$editable.html(), this.$editable)
                    }
                }, {
                    key: "redo",
                    value: function() {
                        this.context.triggerEvent("before.command", this.$editable.html()), this.history.redo(), this.context.triggerEvent("change", this.$editable.html(), this.$editable)
                    }
                }, {
                    key: "beforeCommand",
                    value: function() {
                        this.context.triggerEvent("before.command", this.$editable.html()), document.execCommand("styleWithCSS", !1, this.options.styleWithCSS), this.focus()
                    }
                }, {
                    key: "afterCommand",
                    value: function(t) {
                        this.normalizeContent(), this.history.recordUndo(), t || this.context.triggerEvent("change", this.$editable.html(), this.$editable)
                    }
                }, {
                    key: "tab",
                    value: function() {
                        var t = this.getLastRange();
                        if (t.isCollapsed() && t.isOnCell()) this.table.tab(t);
                        else {
                            if (0 === this.options.tabSize) return !1;
                            this.isLimited(this.options.tabSize) || (this.beforeCommand(), this.typing.insertTab(t, this.options.tabSize), this.afterCommand())
                        }
                    }
                }, {
                    key: "untab",
                    value: function() {
                        var t = this.getLastRange();
                        if (t.isCollapsed() && t.isOnCell()) this.table.tab(t, !0);
                        else if (0 === this.options.tabSize) return !1
                    }
                }, {
                    key: "wrapCommand",
                    value: function(t) {
                        return function() {
                            this.beforeCommand(), t.apply(this, arguments), this.afterCommand()
                        }
                    }
                }, {
                    key: "insertImage",
                    value: function(t, o) {
                        var n, i = this;
                        return (n = t, e().Deferred((function(t) {
                            var o = e()("<img>");
                            o.one("load", (function() {
                                o.off("error abort"), t.resolve(o)
                            })).one("error abort", (function() {
                                o.off("load").detach(), t.reject(o)
                            })).css({
                                display: "none"
                            }).appendTo(document.body).attr("src", n)
                        })).promise()).then((function(t) {
                            i.beforeCommand(), "function" == typeof o ? o(t) : ("string" == typeof o && t.attr("data-filename", o), t.css("width", Math.min(i.$editable.width(), t.width()))), t.show(), i.getLastRange().insertNode(t[0]), i.setLastRange(bt.createFromNodeAfter(t[0]).select()), i.afterCommand()
                        })).fail((function(t) {
                            i.context.triggerEvent("image.upload.error", t)
                        }))
                    }
                }, {
                    key: "insertImagesAsDataURL",
                    value: function(t) {
                        var o = this;
                        e().each(t, (function(t, n) {
                            var i = n.name;
                            o.options.maximumImageFileSize && o.options.maximumImageFileSize < n.size ? o.context.triggerEvent("image.upload.error", o.lang.image.maximumFileSizeError) : function(t) {
                                return e().Deferred((function(o) {
                                    e().extend(new FileReader, {
                                        onload: function(t) {
                                            var e = t.target.result;
                                            o.resolve(e)
                                        },
                                        onerror: function(t) {
                                            o.reject(t)
                                        }
                                    }).readAsDataURL(t)
                                })).promise()
                            }(n).then((function(t) {
                                return o.insertImage(t, i)
                            })).fail((function() {
                                o.context.triggerEvent("image.upload.error")
                            }))
                        }))
                    }
                }, {
                    key: "insertImagesOrCallback",
                    value: function(t) {
                        this.options.callbacks.onImageUpload ? this.context.triggerEvent("image.upload", t) : this.insertImagesAsDataURL(t)
                    }
                }, {
                    key: "getSelectedText",
                    value: function() {
                        var t = this.getLastRange();
                        return t.isOnAnchor() && (t = bt.createFromNode(dt.ancestor(t.sc, dt.isAnchor))), t.toString()
                    }
                }, {
                    key: "onFormatBlock",
                    value: function(t, o) {
                        if (document.execCommand("FormatBlock", !1, f.isMSIE ? "<" + t + ">" : t), o && o.length && (o[0].tagName.toUpperCase() !== t.toUpperCase() && (o = o.find(t)), o && o.length)) {
                            var n = this.createRange(),
                                i = e()([n.sc, n.ec]).closest(t);
                            i.removeClass();
                            var r = o[0].className || "";
                            r && i.addClass(r)
                        }
                    }
                }, {
                    key: "formatPara",
                    value: function() {
                        this.formatBlock("P")
                    }
                }, {
                    key: "fontStyling",
                    value: function(t, o) {
                        var n = this.getLastRange();
                        if ("" !== n) {
                            var i = this.style.styleNodes(n);
                            if (this.$editor.find(".note-status-output").html(""), e()(i).css(t, o), n.isCollapsed()) {
                                var r = y.head(i);
                                r && !dt.nodeLength(r) && (r.innerHTML = dt.ZERO_WIDTH_NBSP_CHAR, bt.createFromNode(r.firstChild).select(), this.setLastRange(), this.$editable.data("bogus", r))
                            } else this.setLastRange(this.createRangeFromList(i).select())
                        } else {
                            var a = e().now();
                            this.$editor.find(".note-status-output").html('<div id="note-status-output-' + a + '" class="alert alert-info">' + this.lang.output.noSelection + "</div>"), setTimeout((function() {
                                e()("#note-status-output-" + a).remove()
                            }), 5e3)
                        }
                    }
                }, {
                    key: "unlink",
                    value: function() {
                        var t = this.getLastRange();
                        if (t.isOnAnchor()) {
                            var e = dt.ancestor(t.sc, dt.isAnchor);
                            (t = bt.createFromNode(e)).select(), this.setLastRange(), this.beforeCommand(), document.execCommand("unlink"), this.afterCommand()
                        }
                    }
                }, {
                    key: "getLinkInfo",
                    value: function() {
                        var t = this.getLastRange().expand(dt.isAnchor),
                            o = e()(y.head(t.nodes(dt.isAnchor))),
                            n = {
                                range: t,
                                text: t.toString(),
                                url: o.length ? o.attr("href") : ""
                            };
                        return o.length && (n.isNewWindow = "_blank" === o.attr("target")), n
                    }
                }, {
                    key: "addRow",
                    value: function(t) {
                        var e = this.getLastRange(this.$editable);
                        e.isCollapsed() && e.isOnCell() && (this.beforeCommand(), this.table.addRow(e, t), this.afterCommand())
                    }
                }, {
                    key: "addCol",
                    value: function(t) {
                        var e = this.getLastRange(this.$editable);
                        e.isCollapsed() && e.isOnCell() && (this.beforeCommand(), this.table.addCol(e, t), this.afterCommand())
                    }
                }, {
                    key: "deleteRow",
                    value: function() {
                        var t = this.getLastRange(this.$editable);
                        t.isCollapsed() && t.isOnCell() && (this.beforeCommand(), this.table.deleteRow(t), this.afterCommand())
                    }
                }, {
                    key: "deleteCol",
                    value: function() {
                        var t = this.getLastRange(this.$editable);
                        t.isCollapsed() && t.isOnCell() && (this.beforeCommand(), this.table.deleteCol(t), this.afterCommand())
                    }
                }, {
                    key: "deleteTable",
                    value: function() {
                        var t = this.getLastRange(this.$editable);
                        t.isCollapsed() && t.isOnCell() && (this.beforeCommand(), this.table.deleteTable(t), this.afterCommand())
                    }
                }, {
                    key: "resizeTo",
                    value: function(t, e, o) {
                        var n;
                        if (o) {
                            var i = t.y / t.x,
                                r = e.data("ratio");
                            n = {
                                width: r > i ? t.x : t.y / r,
                                height: r > i ? t.x * r : t.y
                            }
                        } else n = {
                            width: t.x,
                            height: t.y
                        };
                        e.css(n)
                    }
                }, {
                    key: "hasFocus",
                    value: function() {
                        return this.$editable.is(":focus")
                    }
                }, {
                    key: "focus",
                    value: function() {
                        this.hasFocus() || this.$editable.focus()
                    }
                }, {
                    key: "isEmpty",
                    value: function() {
                        return dt.isEmpty(this.$editable[0]) || dt.emptyPara === this.$editable.html()
                    }
                }, {
                    key: "empty",
                    value: function() {
                        this.context.invoke("code", dt.emptyPara)
                    }
                }, {
                    key: "normalizeContent",
                    value: function() {
                        this.$editable[0].normalize()
                    }
                }]) && Lt(o.prototype, n), i && Lt(o, i), t
            }();

            function Ft(t, e) {
                for (var o = 0; o < e.length; o++) {
                    var n = e[o];
                    n.enumerable = n.enumerable || !1, n.configurable = !0, "value" in n && (n.writable = !0), Object.defineProperty(t, n.key, n)
                }
            }
            var Dt = function() {
                function t(e) {
                    ! function(t, e) {
                        if (!(t instanceof e)) throw new TypeError("Cannot call a class as a function")
                    }(this, t), this.context = e, this.$editable = e.layoutInfo.editable
                }
                var e, o, n;
                return e = t, (o = [{
                    key: "initialize",
                    value: function() {
                        this.$editable.on("paste", this.pasteByEvent.bind(this))
                    }
                }, {
                    key: "pasteByEvent",
                    value: function(t) {
                        var e = this,
                            o = t.originalEvent.clipboardData;
                        if (o && o.items && o.items.length) {
                            var n = o.items.length > 1 ? o.items[1] : y.head(o.items);
                            "file" === n.kind && -1 !== n.type.indexOf("image/") ? (this.context.invoke("editor.insertImagesOrCallback", [n.getAsFile()]), t.preventDefault()) : "string" === n.kind && this.context.invoke("editor.isLimited", o.getData("Text").length) && t.preventDefault()
                        } else if (window.clipboardData) {
                            var i = window.clipboardData.getData("text");
                            this.context.invoke("editor.isLimited", i.length) && t.preventDefault()
                        }
                        setTimeout((function() {
                            e.context.invoke("editor.afterCommand")
                        }), 10)
                    }
                }]) && Ft(e.prototype, o), n && Ft(e, n), t
            }();

            function Ht(t, e) {
                for (var o = 0; o < e.length; o++) {
                    var n = e[o];
                    n.enumerable = n.enumerable || !1, n.configurable = !0, "value" in n && (n.writable = !0), Object.defineProperty(t, n.key, n)
                }
            }
            var Bt = function() {
                function t(o) {
                    ! function(t, e) {
                        if (!(t instanceof e)) throw new TypeError("Cannot call a class as a function")
                    }(this, t), this.context = o, this.$eventListener = e()(document), this.$editor = o.layoutInfo.editor, this.$editable = o.layoutInfo.editable, this.options = o.options, this.lang = this.options.langInfo, this.documentEventHandlers = {}, this.$dropzone = e()(['<div class="note-dropzone">', '<div class="note-dropzone-message"></div>', "</div>"].join("")).prependTo(this.$editor)
                }
                var o, n, i;
                return o = t, (n = [{
                    key: "initialize",
                    value: function() {
                        this.options.disableDragAndDrop ? (this.documentEventHandlers.onDrop = function(t) {
                            t.preventDefault()
                        }, this.$eventListener = this.$dropzone, this.$eventListener.on("drop", this.documentEventHandlers.onDrop)) : this.attachDragAndDropEvent()
                    }
                }, {
                    key: "attachDragAndDropEvent",
                    value: function() {
                        var t = this,
                            o = e()(),
                            n = this.$dropzone.find(".note-dropzone-message");
                        this.documentEventHandlers.onDragenter = function(e) {
                            var i = t.context.invoke("codeview.isActivated"),
                                r = t.$editor.width() > 0 && t.$editor.height() > 0;
                            i || o.length || !r || (t.$editor.addClass("dragover"), t.$dropzone.width(t.$editor.width()), t.$dropzone.height(t.$editor.height()), n.text(t.lang.image.dragImageHere)), o = o.add(e.target)
                        }, this.documentEventHandlers.onDragleave = function(n) {
                            (o = o.not(n.target)).length && "BODY" !== n.target.nodeName || (o = e()(), t.$editor.removeClass("dragover"))
                        }, this.documentEventHandlers.onDrop = function() {
                            o = e()(), t.$editor.removeClass("dragover")
                        }, this.$eventListener.on("dragenter", this.documentEventHandlers.onDragenter).on("dragleave", this.documentEventHandlers.onDragleave).on("drop", this.documentEventHandlers.onDrop), this.$dropzone.on("dragenter", (function() {
                            t.$dropzone.addClass("hover"), n.text(t.lang.image.dropImage)
                        })).on("dragleave", (function() {
                            t.$dropzone.removeClass("hover"), n.text(t.lang.image.dragImageHere)
                        })), this.$dropzone.on("drop", (function(o) {
                            var n = o.originalEvent.dataTransfer;
                            o.preventDefault(), n && n.files && n.files.length ? (t.$editable.focus(), t.context.invoke("editor.insertImagesOrCallback", n.files)) : e().each(n.types, (function(o, i) {
                                if (!(i.toLowerCase().indexOf("_moz_") > -1)) {
                                    var r = n.getData(i);
                                    i.toLowerCase().indexOf("text") > -1 ? t.context.invoke("editor.pasteHTML", r) : e()(r).each((function(e, o) {
                                        t.context.invoke("editor.insertNode", o)
                                    }))
                                }
                            }))
                        })).on("dragover", !1)
                    }
                }, {
                    key: "destroy",
                    value: function() {
                        var t = this;
                        Object.keys(this.documentEventHandlers).forEach((function(e) {
                            t.$eventListener.off(e.substr(2).toLowerCase(), t.documentEventHandlers[e])
                        })), this.documentEventHandlers = {}
                    }
                }]) && Ht(o.prototype, n), i && Ht(o, i), t
            }();

            function zt(t, e) {
                var o = "undefined" != typeof Symbol && t[Symbol.iterator] || t["@@iterator"];
                if (!o) {
                    if (Array.isArray(t) || (o = function(t, e) {
                            if (!t) return;
                            if ("string" == typeof t) return Mt(t, e);
                            var o = Object.prototype.toString.call(t).slice(8, -1);
                            "Object" === o && t.constructor && (o = t.constructor.name);
                            if ("Map" === o || "Set" === o) return Array.from(t);
                            if ("Arguments" === o || /^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(o)) return Mt(t, e)
                        }(t)) || e && t && "number" == typeof t.length) {
                        o && (t = o);
                        var n = 0,
                            i = function() {};
                        return {
                            s: i,
                            n: function() {
                                return n >= t.length ? {
                                    done: !0
                                } : {
                                    done: !1,
                                    value: t[n++]
                                }
                            },
                            e: function(t) {
                                throw t
                            },
                            f: i
                        }
                    }
                    throw new TypeError("Invalid attempt to iterate non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")
                }
                var r, a = !0,
                    s = !1;
                return {
                    s: function() {
                        o = o.call(t)
                    },
                    n: function() {
                        var t = o.next();
                        return a = t.done, t
                    },
                    e: function(t) {
                        s = !0, r = t
                    },
                    f: function() {
                        try {
                            a || null == o.return || o.return()
                        } finally {
                            if (s) throw r
                        }
                    }
                }
            }

            function Mt(t, e) {
                (null == e || e > t.length) && (e = t.length);
                for (var o = 0, n = new Array(e); o < e; o++) n[o] = t[o];
                return n
            }

            function Ot(t, e) {
                for (var o = 0; o < e.length; o++) {
                    var n = e[o];
                    n.enumerable = n.enumerable || !1, n.configurable = !0, "value" in n && (n.writable = !0), Object.defineProperty(t, n.key, n)
                }
            }
            var jt = function() {
                function t(e) {
                    ! function(t, e) {
                        if (!(t instanceof e)) throw new TypeError("Cannot call a class as a function")
                    }(this, t), this.context = e, this.$editor = e.layoutInfo.editor, this.$editable = e.layoutInfo.editable, this.$codable = e.layoutInfo.codable, this.options = e.options, this.CodeMirrorConstructor = window.CodeMirror, this.options.codemirror.CodeMirrorConstructor && (this.CodeMirrorConstructor = this.options.codemirror.CodeMirrorConstructor)
                }
                var e, o, n;
                return e = t, (o = [{
                    key: "sync",
                    value: function(t) {
                        var e = this.isActivated(),
                            o = this.CodeMirrorConstructor;
                        e && (t ? o ? this.$codable.data("cmEditor").getDoc().setValue(t) : this.$codable.val(t) : o && this.$codable.data("cmEditor").save())
                    }
                }, {
                    key: "initialize",
                    value: function() {
                        var t = this;
                        this.$codable.on("keyup", (function(e) {
                            e.keyCode === yt.code.ESCAPE && t.deactivate()
                        }))
                    }
                }, {
                    key: "isActivated",
                    value: function() {
                        return this.$editor.hasClass("codeview")
                    }
                }, {
                    key: "toggle",
                    value: function() {
                        this.isActivated() ? this.deactivate() : this.activate(), this.context.triggerEvent("codeview.toggled")
                    }
                }, {
                    key: "purify",
                    value: function(t) {
                        if (this.options.codeviewFilter && (t = t.replace(this.options.codeviewFilterRegex, ""), this.options.codeviewIframeFilter)) {
                            var e = this.options.codeviewIframeWhitelistSrc.concat(this.options.codeviewIframeWhitelistSrcBase);
                            t = t.replace(/(<iframe.*?>.*?(?:<\/iframe>)?)/gi, (function(t) {
                                if (/<.+src(?==?('|"|\s)?)[\s\S]+src(?=('|"|\s)?)[^>]*?>/i.test(t)) return "";
                                var o, n = zt(e);
                                try {
                                    for (n.s(); !(o = n.n()).done;) {
                                        var i = o.value;
                                        if (new RegExp('src="(https?:)?//' + i.replace(/[-\/\\^$*+?.()|[\]{}]/g, "\\$&") + '/(.+)"').test(t)) return t
                                    }
                                } catch (t) {
                                    n.e(t)
                                } finally {
                                    n.f()
                                }
                                return ""
                            }))
                        }
                        return t
                    }
                }, {
                    key: "activate",
                    value: function() {
                        var t = this,
                            e = this.CodeMirrorConstructor;
                        if (this.$codable.val(dt.html(this.$editable, this.options.prettifyHtml)), this.$codable.height(this.$editable.height()), this.context.invoke("toolbar.updateCodeview", !0), this.context.invoke("airPopover.updateCodeview", !0), this.$editor.addClass("codeview"), this.$codable.focus(), e) {
                            var o = e.fromTextArea(this.$codable[0], this.options.codemirror);
                            if (this.options.codemirror.tern) {
                                var n = new e.TernServer(this.options.codemirror.tern);
                                o.ternServer = n, o.on("cursorActivity", (function(t) {
                                    n.updateArgHints(t)
                                }))
                            }
                            o.on("blur", (function(e) {
                                t.context.triggerEvent("blur.codeview", o.getValue(), e)
                            })), o.on("change", (function() {
                                t.context.triggerEvent("change.codeview", o.getValue(), o)
                            })), o.setSize(null, this.$editable.outerHeight()), this.$codable.data("cmEditor", o)
                        } else this.$codable.on("blur", (function(e) {
                            t.context.triggerEvent("blur.codeview", t.$codable.val(), e)
                        })), this.$codable.on("input", (function() {
                            t.context.triggerEvent("change.codeview", t.$codable.val(), t.$codable)
                        }))
                    }
                }, {
                    key: "deactivate",
                    value: function() {
                        if (this.CodeMirrorConstructor) {
                            var t = this.$codable.data("cmEditor");
                            this.$codable.val(t.getValue()), t.toTextArea()
                        }
                        var e = this.purify(dt.value(this.$codable, this.options.prettifyHtml) || dt.emptyPara),
                            o = this.$editable.html() !== e;
                        this.$editable.html(e), this.$editable.height(this.options.height ? this.$codable.height() : "auto"), this.$editor.removeClass("codeview"), o && this.context.triggerEvent("change", this.$editable.html(), this.$editable), this.$editable.focus(), this.context.invoke("toolbar.updateCodeview", !1), this.context.invoke("airPopover.updateCodeview", !1)
                    }
                }, {
                    key: "destroy",
                    value: function() {
                        this.isActivated() && this.deactivate()
                    }
                }]) && Ot(e.prototype, o), n && Ot(e, n), t
            }();

            function Ut(t, e) {
                for (var o = 0; o < e.length; o++) {
                    var n = e[o];
                    n.enumerable = n.enumerable || !1, n.configurable = !0, "value" in n && (n.writable = !0), Object.defineProperty(t, n.key, n)
                }
            }
            var Wt = function() {
                function t(o) {
                    ! function(t, e) {
                        if (!(t instanceof e)) throw new TypeError("Cannot call a class as a function")
                    }(this, t), this.$document = e()(document), this.$statusbar = o.layoutInfo.statusbar, this.$editable = o.layoutInfo.editable, this.$codable = o.layoutInfo.codable, this.options = o.options
                }
                var o, n, i;
                return o = t, (n = [{
                    key: "initialize",
                    value: function() {
                        var t = this;
                        this.options.airMode || this.options.disableResizeEditor ? this.destroy() : this.$statusbar.on("mousedown", (function(e) {
                            e.preventDefault(), e.stopPropagation();
                            var o = t.$editable.offset().top - t.$document.scrollTop(),
                                n = t.$codable.offset().top - t.$document.scrollTop(),
                                i = function(e) {
                                    var i = e.clientY - (o + 24),
                                        r = e.clientY - (n + 24);
                                    i = t.options.minheight > 0 ? Math.max(i, t.options.minheight) : i, i = t.options.maxHeight > 0 ? Math.min(i, t.options.maxHeight) : i, r = t.options.minheight > 0 ? Math.max(r, t.options.minheight) : r, r = t.options.maxHeight > 0 ? Math.min(r, t.options.maxHeight) : r, t.$editable.height(i), t.$codable.height(r)
                                };
                            t.$document.on("mousemove", i).one("mouseup", (function() {
                                t.$document.off("mousemove", i)
                            }))
                        }))
                    }
                }, {
                    key: "destroy",
                    value: function() {
                        this.$statusbar.off(), this.$statusbar.addClass("locked")
                    }
                }]) && Ut(o.prototype, n), i && Ut(o, i), t
            }();

            function Kt(t, e) {
                for (var o = 0; o < e.length; o++) {
                    var n = e[o];
                    n.enumerable = n.enumerable || !1, n.configurable = !0, "value" in n && (n.writable = !0), Object.defineProperty(t, n.key, n)
                }
            }
            var Vt = function() {
                function t(o) {
                    var n = this;
                    ! function(t, e) {
                        if (!(t instanceof e)) throw new TypeError("Cannot call a class as a function")
                    }(this, t), this.context = o, this.$editor = o.layoutInfo.editor, this.$toolbar = o.layoutInfo.toolbar, this.$editable = o.layoutInfo.editable, this.$codable = o.layoutInfo.codable, this.$window = e()(window), this.$scrollbar = e()("html, body"), this.scrollbarClassName = "note-fullscreen-body", this.onResize = function() {
                        n.resizeTo({
                            h: n.$window.height() - n.$toolbar.outerHeight()
                        })
                    }
                }
                var o, n, i;
                return o = t, (n = [{
                    key: "resizeTo",
                    value: function(t) {
                        this.$editable.css("height", t.h), this.$codable.css("height", t.h), this.$codable.data("cmeditor") && this.$codable.data("cmeditor").setsize(null, t.h)
                    }
                }, {
                    key: "toggle",
                    value: function() {
                        this.$editor.toggleClass("fullscreen");
                        var t = this.isFullscreen();
                        this.$scrollbar.toggleClass(this.scrollbarClassName, t), t ? (this.$editable.data("orgHeight", this.$editable.css("height")), this.$editable.data("orgMaxHeight", this.$editable.css("maxHeight")), this.$editable.css("maxHeight", ""), this.$window.on("resize", this.onResize).trigger("resize")) : (this.$window.off("resize", this.onResize), this.resizeTo({
                            h: this.$editable.data("orgHeight")
                        }), this.$editable.css("maxHeight", this.$editable.css("orgMaxHeight"))), this.context.invoke("toolbar.updateFullscreen", t)
                    }
                }, {
                    key: "isFullscreen",
                    value: function() {
                        return this.$editor.hasClass("fullscreen")
                    }
                }, {
                    key: "destroy",
                    value: function() {
                        this.$scrollbar.removeClass(this.scrollbarClassName)
                    }
                }]) && Kt(o.prototype, n), i && Kt(o, i), t
            }();

            function qt(t, e) {
                for (var o = 0; o < e.length; o++) {
                    var n = e[o];
                    n.enumerable = n.enumerable || !1, n.configurable = !0, "value" in n && (n.writable = !0), Object.defineProperty(t, n.key, n)
                }
            }
            var _t = function() {
                function t(o) {
                    var n = this;
                    ! function(t, e) {
                        if (!(t instanceof e)) throw new TypeError("Cannot call a class as a function")
                    }(this, t), this.context = o, this.$document = e()(document), this.$editingArea = o.layoutInfo.editingArea, this.options = o.options, this.lang = this.options.langInfo, this.events = {
                        "summernote.mousedown": function(t, e) {
                            n.update(e.target, e) && e.preventDefault()
                        },
                        "summernote.keyup summernote.scroll summernote.change summernote.dialog.shown": function() {
                            n.update()
                        },
                        "summernote.disable summernote.blur": function() {
                            n.hide()
                        },
                        "summernote.codeview.toggled": function() {
                            n.update()
                        }
                    }
                }
                var o, n, i;
                return o = t, (n = [{
                    key: "initialize",
                    value: function() {
                        var t = this;
                        this.$handle = e()(['<div class="note-handle">', '<div class="note-control-selection">', '<div class="note-control-selection-bg"></div>', '<div class="note-control-holder note-control-nw"></div>', '<div class="note-control-holder note-control-ne"></div>', '<div class="note-control-holder note-control-sw"></div>', '<div class="', this.options.disableResizeImage ? "note-control-holder" : "note-control-sizing", ' note-control-se"></div>', this.options.disableResizeImage ? "" : '<div class="note-control-selection-info"></div>', "</div>", "</div>"].join("")).prependTo(this.$editingArea), this.$handle.on("mousedown", (function(e) {
                            if (dt.isControlSizing(e.target)) {
                                e.preventDefault(), e.stopPropagation();
                                var o = t.$handle.find(".note-control-selection").data("target"),
                                    n = o.offset(),
                                    i = t.$document.scrollTop(),
                                    r = function(e) {
                                        t.context.invoke("editor.resizeTo", {
                                            x: e.clientX - n.left,
                                            y: e.clientY - (n.top - i)
                                        }, o, !e.shiftKey), t.update(o[0], e)
                                    };
                                t.$document.on("mousemove", r).one("mouseup", (function(e) {
                                    e.preventDefault(), t.$document.off("mousemove", r), t.context.invoke("editor.afterCommand")
                                })), o.data("ratio") || o.data("ratio", o.height() / o.width())
                            }
                        })), this.$handle.on("wheel", (function(e) {
                            e.preventDefault(), t.update()
                        }))
                    }
                }, {
                    key: "destroy",
                    value: function() {
                        this.$handle.remove()
                    }
                }, {
                    key: "update",
                    value: function(t, o) {
                        if (this.context.isDisabled()) return !1;
                        var n = dt.isImg(t),
                            i = this.$handle.find(".note-control-selection");
                        if (this.context.invoke("imagePopover.update", t, o), n) {
                            var r = e()(t),
                                a = r.position(),
                                s = {
                                    left: a.left + parseInt(r.css("marginLeft"), 10),
                                    top: a.top + parseInt(r.css("marginTop"), 10)
                                },
                                l = {
                                    w: r.outerWidth(!1),
                                    h: r.outerHeight(!1)
                                };
                            i.css({
                                display: "block",
                                left: s.left,
                                top: s.top,
                                width: l.w,
                                height: l.h
                            }).data("target", r);
                            var c = new Image;
                            c.src = r.attr("src");
                            var u = l.w + "x" + l.h + " (" + this.lang.image.original + ": " + c.width + "x" + c.height + ")";
                            i.find(".note-control-selection-info").text(u), this.context.invoke("editor.saveTarget", t)
                        } else this.hide();
                        return n
                    }
                }, {
                    key: "hide",
                    value: function() {
                        this.context.invoke("editor.clearTarget"), this.$handle.children().hide()
                    }
                }]) && qt(o.prototype, n), i && qt(o, i), t
            }();

            function Gt(t, e) {
                for (var o = 0; o < e.length; o++) {
                    var n = e[o];
                    n.enumerable = n.enumerable || !1, n.configurable = !0, "value" in n && (n.writable = !0), Object.defineProperty(t, n.key, n)
                }
            }
            var Yt = /^([A-Za-z][A-Za-z0-9+-.]*\:[\/]{2}|tel:|mailto:[A-Z0-9._%+-]+@|xmpp:[A-Z0-9._%+-]+@)?(www\.)?(.+)$/i,
                Zt = function() {
                    function t(e) {
                        var o = this;
                        ! function(t, e) {
                            if (!(t instanceof e)) throw new TypeError("Cannot call a class as a function")
                        }(this, t), this.context = e, this.options = e.options, this.events = {
                            "summernote.keyup": function(t, e) {
                                e.isDefaultPrevented() || o.handleKeyup(e)
                            },
                            "summernote.keydown": function(t, e) {
                                o.handleKeydown(e)
                            }
                        }
                    }
                    var o, n, i;
                    return o = t, (n = [{
                        key: "initialize",
                        value: function() {
                            this.lastWordRange = null
                        }
                    }, {
                        key: "destroy",
                        value: function() {
                            this.lastWordRange = null
                        }
                    }, {
                        key: "replace",
                        value: function() {
                            if (this.lastWordRange) {
                                var t = this.lastWordRange.toString(),
                                    o = t.match(Yt);
                                if (o && (o[1] || o[2])) {
                                    var n = o[1] ? t : "http://" + t,
                                        i = this.options.showDomainOnlyForAutolink ? t.replace(/^(?:https?:\/\/)?(?:tel?:?)?(?:mailto?:?)?(?:xmpp?:?)?(?:www\.)?/i, "").split("/")[0] : t,
                                        r = e()("<a></a>").html(i).attr("href", n)[0];
                                    this.context.options.linkTargetBlank && e()(r).attr("target", "_blank"), this.lastWordRange.insertNode(r), this.lastWordRange = null, this.context.invoke("editor.focus")
                                }
                            }
                        }
                    }, {
                        key: "handleKeydown",
                        value: function(t) {
                            if (y.contains([yt.code.ENTER, yt.code.SPACE], t.keyCode)) {
                                var e = this.context.invoke("editor.createRange").getWordRange();
                                this.lastWordRange = e
                            }
                        }
                    }, {
                        key: "handleKeyup",
                        value: function(t) {
                            y.contains([yt.code.ENTER, yt.code.SPACE], t.keyCode) && this.replace()
                        }
                    }]) && Gt(o.prototype, n), i && Gt(o, i), t
                }();

            function Xt(t, e) {
                for (var o = 0; o < e.length; o++) {
                    var n = e[o];
                    n.enumerable = n.enumerable || !1, n.configurable = !0, "value" in n && (n.writable = !0), Object.defineProperty(t, n.key, n)
                }
            }
            var Qt = function() {
                function t(e) {
                    var o = this;
                    ! function(t, e) {
                        if (!(t instanceof e)) throw new TypeError("Cannot call a class as a function")
                    }(this, t), this.$note = e.layoutInfo.note, this.events = {
                        "summernote.change": function() {
                            o.$note.val(e.invoke("code"))
                        }
                    }
                }
                var e, o, n;
                return e = t, (o = [{
                    key: "shouldInitialize",
                    value: function() {
                        return dt.isTextarea(this.$note[0])
                    }
                }]) && Xt(e.prototype, o), n && Xt(e, n), t
            }();

            function Jt(t, e) {
                for (var o = 0; o < e.length; o++) {
                    var n = e[o];
                    n.enumerable = n.enumerable || !1, n.configurable = !0, "value" in n && (n.writable = !0), Object.defineProperty(t, n.key, n)
                }
            }
            var te = function() {
                function t(e) {
                    var o = this;
                    ! function(t, e) {
                        if (!(t instanceof e)) throw new TypeError("Cannot call a class as a function")
                    }(this, t), this.context = e, this.options = e.options.replace || {}, this.keys = [yt.code.ENTER, yt.code.SPACE, yt.code.PERIOD, yt.code.COMMA, yt.code.SEMICOLON, yt.code.SLASH], this.previousKeydownCode = null, this.events = {
                        "summernote.keyup": function(t, e) {
                            e.isDefaultPrevented() || o.handleKeyup(e)
                        },
                        "summernote.keydown": function(t, e) {
                            o.handleKeydown(e)
                        }
                    }
                }
                var e, o, n;
                return e = t, (o = [{
                    key: "shouldInitialize",
                    value: function() {
                        return !!this.options.match
                    }
                }, {
                    key: "initialize",
                    value: function() {
                        this.lastWord = null
                    }
                }, {
                    key: "destroy",
                    value: function() {
                        this.lastWord = null
                    }
                }, {
                    key: "replace",
                    value: function() {
                        if (this.lastWord) {
                            var t = this,
                                e = this.lastWord.toString();
                            this.options.match(e, (function(e) {
                                if (e) {
                                    var o = "";
                                    if ("string" == typeof e ? o = dt.createText(e) : e instanceof jQuery ? o = e[0] : e instanceof Node && (o = e), !o) return;
                                    t.lastWord.insertNode(o), t.lastWord = null, t.context.invoke("editor.focus")
                                }
                            }))
                        }
                    }
                }, {
                    key: "handleKeydown",
                    value: function(t) {
                        if (this.previousKeydownCode && y.contains(this.keys, this.previousKeydownCode)) this.previousKeydownCode = t.keyCode;
                        else {
                            if (y.contains(this.keys, t.keyCode)) {
                                var e = this.context.invoke("editor.createRange").getWordRange();
                                this.lastWord = e
                            }
                            this.previousKeydownCode = t.keyCode
                        }
                    }
                }, {
                    key: "handleKeyup",
                    value: function(t) {
                        y.contains(this.keys, t.keyCode) && this.replace()
                    }
                }]) && Jt(e.prototype, o), n && Jt(e, n), t
            }();

            function ee(t, e) {
                for (var o = 0; o < e.length; o++) {
                    var n = e[o];
                    n.enumerable = n.enumerable || !1, n.configurable = !0, "value" in n && (n.writable = !0), Object.defineProperty(t, n.key, n)
                }
            }
            var oe = function() {
                function t(e) {
                    var o = this;
                    ! function(t, e) {
                        if (!(t instanceof e)) throw new TypeError("Cannot call a class as a function")
                    }(this, t), this.context = e, this.$editingArea = e.layoutInfo.editingArea, this.options = e.options, !0 === this.options.inheritPlaceholder && (this.options.placeholder = this.context.$note.attr("placeholder") || this.options.placeholder), this.events = {
                        "summernote.init summernote.change": function() {
                            o.update()
                        },
                        "summernote.codeview.toggled": function() {
                            o.update()
                        }
                    }
                }
                var o, n, i;
                return o = t, (n = [{
                    key: "shouldInitialize",
                    value: function() {
                        return !!this.options.placeholder
                    }
                }, {
                    key: "initialize",
                    value: function() {
                        var t = this;
                        this.$placeholder = e()('<div class="note-placeholder"></div>'), this.$placeholder.on("click", (function() {
                            t.context.invoke("focus")
                        })).html(this.options.placeholder).prependTo(this.$editingArea), this.update()
                    }
                }, {
                    key: "destroy",
                    value: function() {
                        this.$placeholder.remove()
                    }
                }, {
                    key: "update",
                    value: function() {
                        var t = !this.context.invoke("codeview.isActivated") && this.context.invoke("editor.isEmpty");
                        this.$placeholder.toggle(t)
                    }
                }]) && ee(o.prototype, n), i && ee(o, i), t
            }();

            function ne(t, e) {
                for (var o = 0; o < e.length; o++) {
                    var n = e[o];
                    n.enumerable = n.enumerable || !1, n.configurable = !0, "value" in n && (n.writable = !0), Object.defineProperty(t, n.key, n)
                }
            }
            var ie = function() {
                function t(o) {
                    ! function(t, e) {
                        if (!(t instanceof e)) throw new TypeError("Cannot call a class as a function")
                    }(this, t), this.ui = e().summernote.ui, this.context = o, this.$toolbar = o.layoutInfo.toolbar, this.options = o.options, this.lang = this.options.langInfo, this.invertedKeyMap = m.invertObject(this.options.keyMap[f.isMac ? "mac" : "pc"])
                }
                var o, n, i;
                return o = t, (n = [{
                    key: "representShortcut",
                    value: function(t) {
                        var e = this.invertedKeyMap[t];
                        return this.options.shortcuts && e ? (f.isMac && (e = e.replace("CMD", "⌘").replace("SHIFT", "⇧")), " (" + (e = e.replace("BACKSLASH", "\\").replace("SLASH", "/").replace("LEFTBRACKET", "[").replace("RIGHTBRACKET", "]")) + ")") : ""
                    }
                }, {
                    key: "button",
                    value: function(t) {
                        return !this.options.tooltip && t.tooltip && delete t.tooltip, t.container = this.options.container, this.ui.button(t)
                    }
                }, {
                    key: "initialize",
                    value: function() {
                        this.addToolbarButtons(), this.addImagePopoverButtons(), this.addLinkPopoverButtons(), this.addTablePopoverButtons(), this.fontInstalledMap = {}
                    }
                }, {
                    key: "destroy",
                    value: function() {
                        delete this.fontInstalledMap
                    }
                }, {
                    key: "isFontInstalled",
                    value: function(t) {
                        return Object.prototype.hasOwnProperty.call(this.fontInstalledMap, t) || (this.fontInstalledMap[t] = f.isFontInstalled(t) || y.contains(this.options.fontNamesIgnoreCheck, t)), this.fontInstalledMap[t]
                    }
                }, {
                    key: "isFontDeservedToAdd",
                    value: function(t) {
                        return "" !== (t = t.toLowerCase()) && this.isFontInstalled(t) && -1 === f.genericFontFamilies.indexOf(t)
                    }
                }, {
                    key: "colorPalette",
                    value: function(t, o, n, i) {
                        var r = this;
                        return this.ui.buttonGroup({
                            className: "note-color " + t,
                            children: [this.button({
                                className: "note-current-color-button",
                                contents: this.ui.icon(this.options.icons.font + " note-recent-color"),
                                tooltip: o,
                                click: function(t) {
                                    var o = e()(t.currentTarget);
                                    n && i ? r.context.invoke("editor.color", {
                                        backColor: o.attr("data-backColor"),
                                        foreColor: o.attr("data-foreColor")
                                    }) : n ? r.context.invoke("editor.color", {
                                        backColor: o.attr("data-backColor")
                                    }) : i && r.context.invoke("editor.color", {
                                        foreColor: o.attr("data-foreColor")
                                    })
                                },
                                callback: function(t) {
                                    var e = t.find(".note-recent-color");
                                    n && (e.css("background-color", r.options.colorButton.backColor), t.attr("data-backColor", r.options.colorButton.backColor)), i ? (e.css("color", r.options.colorButton.foreColor), t.attr("data-foreColor", r.options.colorButton.foreColor)) : e.css("color", "transparent")
                                }
                            }), this.button({
                                className: "dropdown-toggle",
                                contents: this.ui.dropdownButtonContents("", this.options),
                                tooltip: this.lang.color.more,
                                data: {
                                    toggle: "dropdown"
                                }
                            }), this.ui.dropdown({
                                items: (n ? ['<div class="note-palette">', '<div class="note-palette-title">' + this.lang.color.background + "</div>", "<div>", '<button type="button" class="note-color-reset btn btn-light btn-default" data-event="backColor" data-value="transparent">', this.lang.color.transparent, "</button>", "</div>", '<div class="note-holder" data-event="backColor">\x3c!-- back colors --\x3e</div>', "<div>", '<button type="button" class="note-color-select btn btn-light btn-default" data-event="openPalette" data-value="backColorPicker-' + this.options.id + '">', this.lang.color.cpSelect, "</button>", '<input type="color" id="backColorPicker-' + this.options.id + '" class="note-btn note-color-select-btn" value="' + this.options.colorButton.backColor + '" data-event="backColorPalette-' + this.options.id + '">', "</div>", '<div class="note-holder-custom" id="backColorPalette-' + this.options.id + '" data-event="backColor"></div>', "</div>"].join("") : "") + (i ? ['<div class="note-palette">', '<div class="note-palette-title">' + this.lang.color.foreground + "</div>", "<div>", '<button type="button" class="note-color-reset btn btn-light btn-default" data-event="removeFormat" data-value="foreColor">', this.lang.color.resetToDefault, "</button>", "</div>", '<div class="note-holder" data-event="foreColor">\x3c!-- fore colors --\x3e</div>', "<div>", '<button type="button" class="note-color-select btn btn-light btn-default" data-event="openPalette" data-value="foreColorPicker-' + this.options.id + '">', this.lang.color.cpSelect, "</button>", '<input type="color" id="foreColorPicker-' + this.options.id + '" class="note-btn note-color-select-btn" value="' + this.options.colorButton.foreColor + '" data-event="foreColorPalette-' + this.options.id + '">', "</div>", '<div class="note-holder-custom" id="foreColorPalette-' + this.options.id + '" data-event="foreColor"></div>', "</div>"].join("") : ""),
                                callback: function(t) {
                                    t.find(".note-holder").each((function(t, o) {
                                        var n = e()(o);
                                        n.append(r.ui.palette({
                                            colors: r.options.colors,
                                            colorsName: r.options.colorsName,
                                            eventName: n.data("event"),
                                            container: r.options.container,
                                            tooltip: r.options.tooltip
                                        }).render())
                                    }));
                                    var o = [
                                        ["#FFFFFF", "#FFFFFF", "#FFFFFF", "#FFFFFF", "#FFFFFF", "#FFFFFF", "#FFFFFF", "#FFFFFF"]
                                    ];
                                    t.find(".note-holder-custom").each((function(t, n) {
                                        var i = e()(n);
                                        i.append(r.ui.palette({
                                            colors: o,
                                            colorsName: o,
                                            eventName: i.data("event"),
                                            container: r.options.container,
                                            tooltip: r.options.tooltip
                                        }).render())
                                    })), t.find("input[type=color]").each((function(o, n) {
                                        e()(n).change((function() {
                                            var o = t.find("#" + e()(this).data("event")).find(".note-color-btn").first(),
                                                n = this.value.toUpperCase();
                                            o.css("background-color", n).attr("aria-label", n).attr("data-value", n).attr("data-original-title", n), o.click()
                                        }))
                                    }))
                                },
                                click: function(o) {
                                    o.stopPropagation();
                                    var n = e()("." + t).find(".note-dropdown-menu"),
                                        i = e()(o.target),
                                        a = i.data("event"),
                                        s = i.attr("data-value");
                                    if ("openPalette" === a) {
                                        var l = n.find("#" + s),
                                            c = e()(n.find("#" + l.data("event")).find(".note-color-row")[0]),
                                            u = c.find(".note-color-btn").last().detach(),
                                            d = l.val();
                                        u.css("background-color", d).attr("aria-label", d).attr("data-value", d).attr("data-original-title", d), c.prepend(u), l.click()
                                    } else {
                                        if (y.contains(["backColor", "foreColor"], a)) {
                                            var h = "backColor" === a ? "background-color" : "color",
                                                f = i.closest(".note-color").find(".note-recent-color"),
                                                p = i.closest(".note-color").find(".note-current-color-button");
                                            f.css(h, s), p.attr("data-" + a, s)
                                        }
                                        r.context.invoke("editor." + a, s)
                                    }
                                }
                            })]
                        }).render()
                    }
                }, {
                    key: "addToolbarButtons",
                    value: function() {
                        var t = this;
                        this.context.memo("button.style", (function() {
                            return t.ui.buttonGroup([t.button({
                                className: "dropdown-toggle",
                                contents: t.ui.dropdownButtonContents(t.ui.icon(t.options.icons.magic), t.options),
                                tooltip: t.lang.style.style,
                                data: {
                                    toggle: "dropdown"
                                }
                            }), t.ui.dropdown({
                                className: "dropdown-style",
                                items: t.options.styleTags,
                                title: t.lang.style.style,
                                template: function(e) {
                                    "string" == typeof e && (e = {
                                        tag: e,
                                        title: Object.prototype.hasOwnProperty.call(t.lang.style, e) ? t.lang.style[e] : e
                                    });
                                    var o = e.tag,
                                        n = e.title;
                                    return "<" + o + (e.style ? ' style="' + e.style + '" ' : "") + (e.className ? ' class="' + e.className + '"' : "") + ">" + n + "</" + o + ">"
                                },
                                click: t.context.createInvokeHandler("editor.formatBlock")
                            })]).render()
                        }));
                        for (var o = function(e, o) {
                                var n = t.options.styleTags[e];
                                t.context.memo("button.style." + n, (function() {
                                    return t.button({
                                        className: "note-btn-style-" + n,
                                        contents: '<div data-value="' + n + '">' + n.toUpperCase() + "</div>",
                                        tooltip: t.lang.style[n],
                                        click: t.context.createInvokeHandler("editor.formatBlock")
                                    }).render()
                                }))
                            }, n = 0, i = this.options.styleTags.length; n < i; n++) o(n);
                        this.context.memo("button.bold", (function() {
                            return t.button({
                                className: "note-btn-bold",
                                contents: t.ui.icon(t.options.icons.bold),
                                tooltip: t.lang.font.bold + t.representShortcut("bold"),
                                click: t.context.createInvokeHandlerAndUpdateState("editor.bold")
                            }).render()
                        })), this.context.memo("button.italic", (function() {
                            return t.button({
                                className: "note-btn-italic",
                                contents: t.ui.icon(t.options.icons.italic),
                                tooltip: t.lang.font.italic + t.representShortcut("italic"),
                                click: t.context.createInvokeHandlerAndUpdateState("editor.italic")
                            }).render()
                        })), this.context.memo("button.underline", (function() {
                            return t.button({
                                className: "note-btn-underline",
                                contents: t.ui.icon(t.options.icons.underline),
                                tooltip: t.lang.font.underline + t.representShortcut("underline"),
                                click: t.context.createInvokeHandlerAndUpdateState("editor.underline")
                            }).render()
                        })), this.context.memo("button.clear", (function() {
                            return t.button({
                                contents: t.ui.icon(t.options.icons.eraser),
                                tooltip: t.lang.font.clear + t.representShortcut("removeFormat"),
                                click: t.context.createInvokeHandler("editor.removeFormat")
                            }).render()
                        })), this.context.memo("button.strikethrough", (function() {
                            return t.button({
                                className: "note-btn-strikethrough",
                                contents: t.ui.icon(t.options.icons.strikethrough),
                                tooltip: t.lang.font.strikethrough + t.representShortcut("strikethrough"),
                                click: t.context.createInvokeHandlerAndUpdateState("editor.strikethrough")
                            }).render()
                        })), this.context.memo("button.superscript", (function() {
                            return t.button({
                                className: "note-btn-superscript",
                                contents: t.ui.icon(t.options.icons.superscript),
                                tooltip: t.lang.font.superscript,
                                click: t.context.createInvokeHandlerAndUpdateState("editor.superscript")
                            }).render()
                        })), this.context.memo("button.subscript", (function() {
                            return t.button({
                                className: "note-btn-subscript",
                                contents: t.ui.icon(t.options.icons.subscript),
                                tooltip: t.lang.font.subscript,
                                click: t.context.createInvokeHandlerAndUpdateState("editor.subscript")
                            }).render()
                        })), this.context.memo("button.fontname", (function() {
                            var o = t.context.invoke("editor.currentStyle");
                            return t.options.addDefaultFonts && e().each(o["font-family"].split(","), (function(e, o) {
                                o = o.trim().replace(/['"]+/g, ""), t.isFontDeservedToAdd(o) && -1 === t.options.fontNames.indexOf(o) && t.options.fontNames.push(o)
                            })), t.ui.buttonGroup([t.button({
                                className: "dropdown-toggle",
                                contents: t.ui.dropdownButtonContents('<span class="note-current-fontname"></span>', t.options),
                                tooltip: t.lang.font.name,
                                data: {
                                    toggle: "dropdown"
                                }
                            }), t.ui.dropdownCheck({
                                className: "dropdown-fontname",
                                checkClassName: t.options.icons.menuCheck,
                                items: t.options.fontNames.filter(t.isFontInstalled.bind(t)),
                                title: t.lang.font.name,
                                template: function(t) {
                                    return '<span style="font-family: ' + f.validFontName(t) + '">' + t + "</span>"
                                },
                                click: t.context.createInvokeHandlerAndUpdateState("editor.fontName")
                            })]).render()
                        })), this.context.memo("button.fontsize", (function() {
                            return t.ui.buttonGroup([t.button({
                                className: "dropdown-toggle",
                                contents: t.ui.dropdownButtonContents('<span class="note-current-fontsize"></span>', t.options),
                                tooltip: t.lang.font.size,
                                data: {
                                    toggle: "dropdown"
                                }
                            }), t.ui.dropdownCheck({
                                className: "dropdown-fontsize",
                                checkClassName: t.options.icons.menuCheck,
                                items: t.options.fontSizes,
                                title: t.lang.font.size,
                                click: t.context.createInvokeHandlerAndUpdateState("editor.fontSize")
                            })]).render()
                        })), this.context.memo("button.fontsizeunit", (function() {
                            return t.ui.buttonGroup([t.button({
                                className: "dropdown-toggle",
                                contents: t.ui.dropdownButtonContents('<span class="note-current-fontsizeunit"></span>', t.options),
                                tooltip: t.lang.font.sizeunit,
                                data: {
                                    toggle: "dropdown"
                                }
                            }), t.ui.dropdownCheck({
                                className: "dropdown-fontsizeunit",
                                checkClassName: t.options.icons.menuCheck,
                                items: t.options.fontSizeUnits,
                                title: t.lang.font.sizeunit,
                                click: t.context.createInvokeHandlerAndUpdateState("editor.fontSizeUnit")
                            })]).render()
                        })), this.context.memo("button.color", (function() {
                            return t.colorPalette("note-color-all", t.lang.color.recent, !0, !0)
                        })), this.context.memo("button.forecolor", (function() {
                            return t.colorPalette("note-color-fore", t.lang.color.foreground, !1, !0)
                        })), this.context.memo("button.backcolor", (function() {
                            return t.colorPalette("note-color-back", t.lang.color.background, !0, !1)
                        })), this.context.memo("button.ul", (function() {
                            return t.button({
                                contents: t.ui.icon(t.options.icons.unorderedlist),
                                tooltip: t.lang.lists.unordered + t.representShortcut("insertUnorderedList"),
                                click: t.context.createInvokeHandler("editor.insertUnorderedList")
                            }).render()
                        })), this.context.memo("button.ol", (function() {
                            return t.button({
                                contents: t.ui.icon(t.options.icons.orderedlist),
                                tooltip: t.lang.lists.ordered + t.representShortcut("insertOrderedList"),
                                click: t.context.createInvokeHandler("editor.insertOrderedList")
                            }).render()
                        }));
                        var r = this.button({
                                contents: this.ui.icon(this.options.icons.alignLeft),
                                tooltip: this.lang.paragraph.left + this.representShortcut("justifyLeft"),
                                click: this.context.createInvokeHandler("editor.justifyLeft")
                            }),
                            a = this.button({
                                contents: this.ui.icon(this.options.icons.alignCenter),
                                tooltip: this.lang.paragraph.center + this.representShortcut("justifyCenter"),
                                click: this.context.createInvokeHandler("editor.justifyCenter")
                            }),
                            s = this.button({
                                contents: this.ui.icon(this.options.icons.alignRight),
                                tooltip: this.lang.paragraph.right + this.representShortcut("justifyRight"),
                                click: this.context.createInvokeHandler("editor.justifyRight")
                            }),
                            l = this.button({
                                contents: this.ui.icon(this.options.icons.alignJustify),
                                tooltip: this.lang.paragraph.justify + this.representShortcut("justifyFull"),
                                click: this.context.createInvokeHandler("editor.justifyFull")
                            }),
                            c = this.button({
                                contents: this.ui.icon(this.options.icons.outdent),
                                tooltip: this.lang.paragraph.outdent + this.representShortcut("outdent"),
                                click: this.context.createInvokeHandler("editor.outdent")
                            }),
                            u = this.button({
                                contents: this.ui.icon(this.options.icons.indent),
                                tooltip: this.lang.paragraph.indent + this.representShortcut("indent"),
                                click: this.context.createInvokeHandler("editor.indent")
                            });
                        this.context.memo("button.justifyLeft", m.invoke(r, "render")), this.context.memo("button.justifyCenter", m.invoke(a, "render")), this.context.memo("button.justifyRight", m.invoke(s, "render")), this.context.memo("button.justifyFull", m.invoke(l, "render")), this.context.memo("button.outdent", m.invoke(c, "render")), this.context.memo("button.indent", m.invoke(u, "render")), this.context.memo("button.paragraph", (function() {
                            return t.ui.buttonGroup([t.button({
                                className: "dropdown-toggle",
                                contents: t.ui.dropdownButtonContents(t.ui.icon(t.options.icons.alignLeft), t.options),
                                tooltip: t.lang.paragraph.paragraph,
                                data: {
                                    toggle: "dropdown"
                                }
                            }), t.ui.dropdown([t.ui.buttonGroup({
                                className: "note-align",
                                children: [r, a, s, l]
                            }), t.ui.buttonGroup({
                                className: "note-list",
                                children: [c, u]
                            })])]).render()
                        })), this.context.memo("button.height", (function() {
                            return t.ui.buttonGroup([t.button({
                                className: "dropdown-toggle",
                                contents: t.ui.dropdownButtonContents(t.ui.icon(t.options.icons.textHeight), t.options),
                                tooltip: t.lang.font.height,
                                data: {
                                    toggle: "dropdown"
                                }
                            }), t.ui.dropdownCheck({
                                items: t.options.lineHeights,
                                checkClassName: t.options.icons.menuCheck,
                                className: "dropdown-line-height",
                                title: t.lang.font.height,
                                click: t.context.createInvokeHandler("editor.lineHeight")
                            })]).render()
                        })), this.context.memo("button.table", (function() {
                            return t.ui.buttonGroup([t.button({
                                className: "dropdown-toggle",
                                contents: t.ui.dropdownButtonContents(t.ui.icon(t.options.icons.table), t.options),
                                tooltip: t.lang.table.table,
                                data: {
                                    toggle: "dropdown"
                                }
                            }), t.ui.dropdown({
                                title: t.lang.table.table,
                                className: "note-table",
                                items: ['<div class="note-dimension-picker">', '<div class="note-dimension-picker-mousecatcher" data-event="insertTable" data-value="1x1"></div>', '<div class="note-dimension-picker-highlighted"></div>', '<div class="note-dimension-picker-unhighlighted"></div>', "</div>", '<div class="note-dimension-display">1 x 1</div>'].join("")
                            })], {
                                callback: function(e) {
                                    e.find(".note-dimension-picker-mousecatcher").css({
                                        width: t.options.insertTableMaxSize.col + "em",
                                        height: t.options.insertTableMaxSize.row + "em"
                                    }).mouseup(t.context.createInvokeHandler("editor.insertTable")).on("mousemove", t.tableMoveHandler.bind(t))
                                }
                            }).render()
                        })), this.context.memo("button.link", (function() {
                            return t.button({
                                contents: t.ui.icon(t.options.icons.link),
                                tooltip: t.lang.link.link + t.representShortcut("linkDialog.show"),
                                click: t.context.createInvokeHandler("linkDialog.show")
                            }).render()
                        })), this.context.memo("button.picture", (function() {
                            return t.button({
                                contents: t.ui.icon(t.options.icons.picture),
                                tooltip: t.lang.image.image,
                                click: t.context.createInvokeHandler("imageDialog.show")
                            }).render()
                        })), this.context.memo("button.video", (function() {
                            return t.button({
                                contents: t.ui.icon(t.options.icons.video),
                                tooltip: t.lang.video.video,
                                click: t.context.createInvokeHandler("videoDialog.show")
                            }).render()
                        })), this.context.memo("button.hr", (function() {
                            return t.button({
                                contents: t.ui.icon(t.options.icons.minus),
                                tooltip: t.lang.hr.insert + t.representShortcut("insertHorizontalRule"),
                                click: t.context.createInvokeHandler("editor.insertHorizontalRule")
                            }).render()
                        })), this.context.memo("button.fullscreen", (function() {
                            return t.button({
                                className: "btn-fullscreen note-codeview-keep",
                                contents: t.ui.icon(t.options.icons.arrowsAlt),
                                tooltip: t.lang.options.fullscreen,
                                click: t.context.createInvokeHandler("fullscreen.toggle")
                            }).render()
                        })), this.context.memo("button.codeview", (function() {
                            return t.button({
                                className: "btn-codeview note-codeview-keep",
                                contents: t.ui.icon(t.options.icons.code),
                                tooltip: t.lang.options.codeview,
                                click: t.context.createInvokeHandler("codeview.toggle")
                            }).render()
                        })), this.context.memo("button.redo", (function() {
                            return t.button({
                                contents: t.ui.icon(t.options.icons.redo),
                                tooltip: t.lang.history.redo + t.representShortcut("redo"),
                                click: t.context.createInvokeHandler("editor.redo")
                            }).render()
                        })), this.context.memo("button.undo", (function() {
                            return t.button({
                                contents: t.ui.icon(t.options.icons.undo),
                                tooltip: t.lang.history.undo + t.representShortcut("undo"),
                                click: t.context.createInvokeHandler("editor.undo")
                            }).render()
                        })), this.context.memo("button.help", (function() {
                            return t.button({
                                contents: t.ui.icon(t.options.icons.question),
                                tooltip: t.lang.options.help,
                                click: t.context.createInvokeHandler("helpDialog.show")
                            }).render()
                        }))
                    }
                }, {
                    key: "addImagePopoverButtons",
                    value: function() {
                        var t = this;
                        this.context.memo("button.resizeFull", (function() {
                            return t.button({
                                contents: '<span class="note-fontsize-10">100%</span>',
                                tooltip: t.lang.image.resizeFull,
                                click: t.context.createInvokeHandler("editor.resize", "1")
                            }).render()
                        })), this.context.memo("button.resizeHalf", (function() {
                            return t.button({
                                contents: '<span class="note-fontsize-10">50%</span>',
                                tooltip: t.lang.image.resizeHalf,
                                click: t.context.createInvokeHandler("editor.resize", "0.5")
                            }).render()
                        })), this.context.memo("button.resizeQuarter", (function() {
                            return t.button({
                                contents: '<span class="note-fontsize-10">25%</span>',
                                tooltip: t.lang.image.resizeQuarter,
                                click: t.context.createInvokeHandler("editor.resize", "0.25")
                            }).render()
                        })), this.context.memo("button.resizeNone", (function() {
                            return t.button({
                                contents: t.ui.icon(t.options.icons.rollback),
                                tooltip: t.lang.image.resizeNone,
                                click: t.context.createInvokeHandler("editor.resize", "0")
                            }).render()
                        })), this.context.memo("button.floatLeft", (function() {
                            return t.button({
                                contents: t.ui.icon(t.options.icons.floatLeft),
                                tooltip: t.lang.image.floatLeft,
                                click: t.context.createInvokeHandler("editor.floatMe", "left")
                            }).render()
                        })), this.context.memo("button.floatRight", (function() {
                            return t.button({
                                contents: t.ui.icon(t.options.icons.floatRight),
                                tooltip: t.lang.image.floatRight,
                                click: t.context.createInvokeHandler("editor.floatMe", "right")
                            }).render()
                        })), this.context.memo("button.floatNone", (function() {
                            return t.button({
                                contents: t.ui.icon(t.options.icons.rollback),
                                tooltip: t.lang.image.floatNone,
                                click: t.context.createInvokeHandler("editor.floatMe", "none")
                            }).render()
                        })), this.context.memo("button.removeMedia", (function() {
                            return t.button({
                                contents: t.ui.icon(t.options.icons.trash),
                                tooltip: t.lang.image.remove,
                                click: t.context.createInvokeHandler("editor.removeMedia")
                            }).render()
                        }))
                    }
                }, {
                    key: "addLinkPopoverButtons",
                    value: function() {
                        var t = this;
                        this.context.memo("button.linkDialogShow", (function() {
                            return t.button({
                                contents: t.ui.icon(t.options.icons.link),
                                tooltip: t.lang.link.edit,
                                click: t.context.createInvokeHandler("linkDialog.show")
                            }).render()
                        })), this.context.memo("button.unlink", (function() {
                            return t.button({
                                contents: t.ui.icon(t.options.icons.unlink),
                                tooltip: t.lang.link.unlink,
                                click: t.context.createInvokeHandler("editor.unlink")
                            }).render()
                        }))
                    }
                }, {
                    key: "addTablePopoverButtons",
                    value: function() {
                        var t = this;
                        this.context.memo("button.addRowUp", (function() {
                            return t.button({
                                className: "btn-md",
                                contents: t.ui.icon(t.options.icons.rowAbove),
                                tooltip: t.lang.table.addRowAbove,
                                click: t.context.createInvokeHandler("editor.addRow", "top")
                            }).render()
                        })), this.context.memo("button.addRowDown", (function() {
                            return t.button({
                                className: "btn-md",
                                contents: t.ui.icon(t.options.icons.rowBelow),
                                tooltip: t.lang.table.addRowBelow,
                                click: t.context.createInvokeHandler("editor.addRow", "bottom")
                            }).render()
                        })), this.context.memo("button.addColLeft", (function() {
                            return t.button({
                                className: "btn-md",
                                contents: t.ui.icon(t.options.icons.colBefore),
                                tooltip: t.lang.table.addColLeft,
                                click: t.context.createInvokeHandler("editor.addCol", "left")
                            }).render()
                        })), this.context.memo("button.addColRight", (function() {
                            return t.button({
                                className: "btn-md",
                                contents: t.ui.icon(t.options.icons.colAfter),
                                tooltip: t.lang.table.addColRight,
                                click: t.context.createInvokeHandler("editor.addCol", "right")
                            }).render()
                        })), this.context.memo("button.deleteRow", (function() {
                            return t.button({
                                className: "btn-md",
                                contents: t.ui.icon(t.options.icons.rowRemove),
                                tooltip: t.lang.table.delRow,
                                click: t.context.createInvokeHandler("editor.deleteRow")
                            }).render()
                        })), this.context.memo("button.deleteCol", (function() {
                            return t.button({
                                className: "btn-md",
                                contents: t.ui.icon(t.options.icons.colRemove),
                                tooltip: t.lang.table.delCol,
                                click: t.context.createInvokeHandler("editor.deleteCol")
                            }).render()
                        })), this.context.memo("button.deleteTable", (function() {
                            return t.button({
                                className: "btn-md",
                                contents: t.ui.icon(t.options.icons.trash),
                                tooltip: t.lang.table.delTable,
                                click: t.context.createInvokeHandler("editor.deleteTable")
                            }).render()
                        }))
                    }
                }, {
                    key: "build",
                    value: function(t, e) {
                        for (var o = 0, n = e.length; o < n; o++) {
                            for (var i = e[o], r = Array.isArray(i) ? i[0] : i, a = Array.isArray(i) ? 1 === i.length ? [i[0]] : i[1] : [i], s = this.ui.buttonGroup({
                                    className: "note-" + r
                                }).render(), l = 0, c = a.length; l < c; l++) {
                                var u = this.context.memo("button." + a[l]);
                                u && s.append("function" == typeof u ? u(this.context) : u)
                            }
                            s.appendTo(t)
                        }
                    }
                }, {
                    key: "updateCurrentStyle",
                    value: function(t) {
                        var o = t || this.$toolbar,
                            n = this.context.invoke("editor.currentStyle");
                        if (this.updateBtnStates(o, {
                                ".note-btn-bold": function() {
                                    return "bold" === n["font-bold"]
                                },
                                ".note-btn-italic": function() {
                                    return "italic" === n["font-italic"]
                                },
                                ".note-btn-underline": function() {
                                    return "underline" === n["font-underline"]
                                },
                                ".note-btn-subscript": function() {
                                    return "subscript" === n["font-subscript"]
                                },
                                ".note-btn-superscript": function() {
                                    return "superscript" === n["font-superscript"]
                                },
                                ".note-btn-strikethrough": function() {
                                    return "strikethrough" === n["font-strikethrough"]
                                }
                            }), n["font-family"]) {
                            var i = n["font-family"].split(",").map((function(t) {
                                    return t.replace(/[\'\"]/g, "").replace(/\s+$/, "").replace(/^\s+/, "")
                                })),
                                r = y.find(i, this.isFontInstalled.bind(this));
                            o.find(".dropdown-fontname a").each((function(t, o) {
                                var n = e()(o),
                                    i = n.data("value") + "" == r + "";
                                n.toggleClass("checked", i)
                            })), o.find(".note-current-fontname").text(r).css("font-family", r)
                        }
                        if (n["font-size"]) {
                            var a = n["font-size"];
                            o.find(".dropdown-fontsize a").each((function(t, o) {
                                var n = e()(o),
                                    i = n.data("value") + "" == a + "";
                                n.toggleClass("checked", i)
                            })), o.find(".note-current-fontsize").text(a);
                            var s = n["font-size-unit"];
                            o.find(".dropdown-fontsizeunit a").each((function(t, o) {
                                var n = e()(o),
                                    i = n.data("value") + "" == s + "";
                                n.toggleClass("checked", i)
                            })), o.find(".note-current-fontsizeunit").text(s)
                        }
                        if (n["line-height"]) {
                            var l = n["line-height"];
                            o.find(".dropdown-line-height a").each((function(t, o) {
                                var n = e()(o),
                                    i = e()(o).data("value") + "" == l + "";
                                n.toggleClass("checked", i)
                            })), o.find(".note-current-line-height").text(l)
                        }
                    }
                }, {
                    key: "updateBtnStates",
                    value: function(t, o) {
                        var n = this;
                        e().each(o, (function(e, o) {
                            n.ui.toggleBtnActive(t.find(e), o())
                        }))
                    }
                }, {
                    key: "tableMoveHandler",
                    value: function(t) {
                        var o, n = e()(t.target.parentNode),
                            i = n.next(),
                            r = n.find(".note-dimension-picker-mousecatcher"),
                            a = n.find(".note-dimension-picker-highlighted"),
                            s = n.find(".note-dimension-picker-unhighlighted");
                        if (void 0 === t.offsetX) {
                            var l = e()(t.target).offset();
                            o = {
                                x: t.pageX - l.left,
                                y: t.pageY - l.top
                            }
                        } else o = {
                            x: t.offsetX,
                            y: t.offsetY
                        };
                        var c = Math.ceil(o.x / 18) || 1,
                            u = Math.ceil(o.y / 18) || 1;
                        a.css({
                            width: c + "em",
                            height: u + "em"
                        }), r.data("value", c + "x" + u), c > 3 && c < this.options.insertTableMaxSize.col && s.css({
                            width: c + 1 + "em"
                        }), u > 3 && u < this.options.insertTableMaxSize.row && s.css({
                            height: u + 1 + "em"
                        }), i.html(c + " x " + u)
                    }
                }]) && ne(o.prototype, n), i && ne(o, i), t
            }();

            function re(t, e) {
                for (var o = 0; o < e.length; o++) {
                    var n = e[o];
                    n.enumerable = n.enumerable || !1, n.configurable = !0, "value" in n && (n.writable = !0), Object.defineProperty(t, n.key, n)
                }
            }
            var ae = function() {
                function t(o) {
                    ! function(t, e) {
                        if (!(t instanceof e)) throw new TypeError("Cannot call a class as a function")
                    }(this, t), this.context = o, this.$window = e()(window), this.$document = e()(document), this.ui = e().summernote.ui, this.$note = o.layoutInfo.note, this.$editor = o.layoutInfo.editor, this.$toolbar = o.layoutInfo.toolbar, this.$editable = o.layoutInfo.editable, this.$statusbar = o.layoutInfo.statusbar, this.options = o.options, this.isFollowing = !1, this.followScroll = this.followScroll.bind(this)
                }
                var o, n, i;
                return o = t, (n = [{
                    key: "shouldInitialize",
                    value: function() {
                        return !this.options.airMode
                    }
                }, {
                    key: "initialize",
                    value: function() {
                        var t = this;
                        this.options.toolbar = this.options.toolbar || [], this.options.toolbar.length ? this.context.invoke("buttons.build", this.$toolbar, this.options.toolbar) : this.$toolbar.hide(), this.options.toolbarContainer && this.$toolbar.appendTo(this.options.toolbarContainer), this.changeContainer(!1), this.$note.on("summernote.keyup summernote.mouseup summernote.change", (function() {
                            t.context.invoke("buttons.updateCurrentStyle")
                        })), this.context.invoke("buttons.updateCurrentStyle"), this.options.followingToolbar && this.$window.on("scroll resize", this.followScroll)
                    }
                }, {
                    key: "destroy",
                    value: function() {
                        this.$toolbar.children().remove(), this.options.followingToolbar && this.$window.off("scroll resize", this.followScroll)
                    }
                }, {
                    key: "followScroll",
                    value: function() {
                        if (this.$editor.hasClass("fullscreen")) return !1;
                        var t = this.$editor.outerHeight(),
                            o = this.$editor.width(),
                            n = this.$toolbar.height(),
                            i = this.$statusbar.height(),
                            r = 0;
                        this.options.otherStaticBar && (r = e()(this.options.otherStaticBar).outerHeight());
                        var a = this.$document.scrollTop(),
                            s = this.$editor.offset().top,
                            l = s - r,
                            c = s + t - r - n - i;
                        !this.isFollowing && a > l && a < c - n ? (this.isFollowing = !0, this.$editable.css({
                            marginTop: this.$toolbar.outerHeight()
                        }), this.$toolbar.css({
                            position: "fixed",
                            top: r,
                            width: o,
                            zIndex: 1e3
                        })) : this.isFollowing && (a < l || a > c) && (this.isFollowing = !1, this.$toolbar.css({
                            position: "relative",
                            top: 0,
                            width: "100%",
                            zIndex: "auto"
                        }), this.$editable.css({
                            marginTop: ""
                        }))
                    }
                }, {
                    key: "changeContainer",
                    value: function(t) {
                        t ? this.$toolbar.prependTo(this.$editor) : this.options.toolbarContainer && this.$toolbar.appendTo(this.options.toolbarContainer), this.options.followingToolbar && this.followScroll()
                    }
                }, {
                    key: "updateFullscreen",
                    value: function(t) {
                        this.ui.toggleBtnActive(this.$toolbar.find(".btn-fullscreen"), t), this.changeContainer(t)
                    }
                }, {
                    key: "updateCodeview",
                    value: function(t) {
                        this.ui.toggleBtnActive(this.$toolbar.find(".btn-codeview"), t), t ? this.deactivate() : this.activate()
                    }
                }, {
                    key: "activate",
                    value: function(t) {
                        var e = this.$toolbar.find("button");
                        t || (e = e.not(".note-codeview-keep")), this.ui.toggleBtn(e, !0)
                    }
                }, {
                    key: "deactivate",
                    value: function(t) {
                        var e = this.$toolbar.find("button");
                        t || (e = e.not(".note-codeview-keep")), this.ui.toggleBtn(e, !1)
                    }
                }]) && re(o.prototype, n), i && re(o, i), t
            }();

            function se(t, e) {
                for (var o = 0; o < e.length; o++) {
                    var n = e[o];
                    n.enumerable = n.enumerable || !1, n.configurable = !0, "value" in n && (n.writable = !0), Object.defineProperty(t, n.key, n)
                }
            }
            var le = function() {
                function t(o) {
                    ! function(t, e) {
                        if (!(t instanceof e)) throw new TypeError("Cannot call a class as a function")
                    }(this, t), this.context = o, this.ui = e().summernote.ui, this.$body = e()(document.body), this.$editor = o.layoutInfo.editor, this.options = o.options, this.lang = this.options.langInfo, o.memo("help.linkDialog.show", this.options.langInfo.help["linkDialog.show"])
                }
                var o, n, i;
                return o = t, (n = [{
                    key: "initialize",
                    value: function() {
                        var t = this.options.dialogsInBody ? this.$body : this.options.container,
                            o = ['<div class="form-group note-form-group">', '<label for="note-dialog-link-txt-'.concat(this.options.id, '" class="note-form-label">').concat(this.lang.link.textToDisplay, "</label>"), '<input id="note-dialog-link-txt-'.concat(this.options.id, '" class="note-link-text form-control note-form-control note-input" type="text"/>'), "</div>", '<div class="form-group note-form-group">', '<label for="note-dialog-link-url-'.concat(this.options.id, '" class="note-form-label">').concat(this.lang.link.url, "</label>"), '<input id="note-dialog-link-url-'.concat(this.options.id, '" class="note-link-url form-control note-form-control note-input" type="text" value="http://"/>'), "</div>", this.options.disableLinkTarget ? "" : e()("<div></div>").append(this.ui.checkbox({
                                className: "sn-checkbox-open-in-new-window",
                                text: this.lang.link.openInNewWindow,
                                checked: !0
                            }).render()).html(), e()("<div></div>").append(this.ui.checkbox({
                                className: "sn-checkbox-use-protocol",
                                text: this.lang.link.useProtocol,
                                checked: !0
                            }).render()).html()].join(""),
                            n = '<input type="button" href="#" class="'.concat("btn btn-primary note-btn note-btn-primary note-link-btn", '" value="').concat(this.lang.link.insert, '" disabled>');
                        this.$dialog = this.ui.dialog({
                            className: "link-dialog",
                            title: this.lang.link.insert,
                            fade: this.options.dialogsFade,
                            body: o,
                            footer: n
                        }).render().appendTo(t)
                    }
                }, {
                    key: "destroy",
                    value: function() {
                        this.ui.hideDialog(this.$dialog), this.$dialog.remove()
                    }
                }, {
                    key: "bindEnterKey",
                    value: function(t, e) {
                        t.on("keypress", (function(t) {
                            t.keyCode === yt.code.ENTER && (t.preventDefault(), e.trigger("click"))
                        }))
                    }
                }, {
                    key: "toggleLinkBtn",
                    value: function(t, e, o) {
                        this.ui.toggleBtn(t, e.val() && o.val())
                    }
                }, {
                    key: "showLinkDialog",
                    value: function(t) {
                        var o = this;
                        return e().Deferred((function(e) {
                            var n = o.$dialog.find(".note-link-text"),
                                i = o.$dialog.find(".note-link-url"),
                                r = o.$dialog.find(".note-link-btn"),
                                a = o.$dialog.find(".sn-checkbox-open-in-new-window input[type=checkbox]"),
                                s = o.$dialog.find(".sn-checkbox-use-protocol input[type=checkbox]");
                            o.ui.onDialogShown(o.$dialog, (function() {
                                o.context.triggerEvent("dialog.shown"), !t.url && m.isValidUrl(t.text) && (t.url = t.text), n.on("input paste propertychange", (function() {
                                    t.text = n.val(), o.toggleLinkBtn(r, n, i)
                                })).val(t.text), i.on("input paste propertychange", (function() {
                                    t.text || n.val(i.val()), o.toggleLinkBtn(r, n, i)
                                })).val(t.url), f.isSupportTouch || i.trigger("focus"), o.toggleLinkBtn(r, n, i), o.bindEnterKey(i, r), o.bindEnterKey(n, r);
                                var l = void 0 !== t.isNewWindow ? t.isNewWindow : o.context.options.linkTargetBlank;
                                a.prop("checked", l);
                                var c = !t.url && o.context.options.useProtocol;
                                s.prop("checked", c), r.one("click", (function(r) {
                                    r.preventDefault(), e.resolve({
                                        range: t.range,
                                        url: i.val(),
                                        text: n.val(),
                                        isNewWindow: a.is(":checked"),
                                        checkProtocol: s.is(":checked")
                                    }), o.ui.hideDialog(o.$dialog)
                                }))
                            })), o.ui.onDialogHidden(o.$dialog, (function() {
                                n.off(), i.off(), r.off(), "pending" === e.state() && e.reject()
                            })), o.ui.showDialog(o.$dialog)
                        })).promise()
                    }
                }, {
                    key: "show",
                    value: function() {
                        var t = this,
                            e = this.context.invoke("editor.getLinkInfo");
                        this.context.invoke("editor.saveRange"), this.showLinkDialog(e).then((function(e) {
                            t.context.invoke("editor.restoreRange"), t.context.invoke("editor.createLink", e)
                        })).fail((function() {
                            t.context.invoke("editor.restoreRange")
                        }))
                    }
                }]) && se(o.prototype, n), i && se(o, i), t
            }();

            function ce(t, e) {
                for (var o = 0; o < e.length; o++) {
                    var n = e[o];
                    n.enumerable = n.enumerable || !1, n.configurable = !0, "value" in n && (n.writable = !0), Object.defineProperty(t, n.key, n)
                }
            }
            var ue = function() {
                function t(o) {
                    var n = this;
                    ! function(t, e) {
                        if (!(t instanceof e)) throw new TypeError("Cannot call a class as a function")
                    }(this, t), this.context = o, this.ui = e().summernote.ui, this.options = o.options, this.events = {
                        "summernote.keyup summernote.mouseup summernote.change summernote.scroll": function() {
                            n.update()
                        },
                        "summernote.disable summernote.dialog.shown": function() {
                            n.hide()
                        },
                        "summernote.blur": function(t, e) {
                            e.originalEvent && e.originalEvent.relatedTarget && n.$popover[0].contains(e.originalEvent.relatedTarget) || n.hide()
                        }
                    }
                }
                var o, n, i;
                return o = t, (n = [{
                    key: "shouldInitialize",
                    value: function() {
                        return !y.isEmpty(this.options.popover.link)
                    }
                }, {
                    key: "initialize",
                    value: function() {
                        this.$popover = this.ui.popover({
                            className: "note-link-popover",
                            callback: function(t) {
                                t.find(".popover-content,.note-popover-content").prepend('<span><a target="_blank"></a>&nbsp;</span>')
                            }
                        }).render().appendTo(this.options.container);
                        var t = this.$popover.find(".popover-content,.note-popover-content");
                        this.context.invoke("buttons.build", t, this.options.popover.link), this.$popover.on("mousedown", (function(t) {
                            t.preventDefault()
                        }))
                    }
                }, {
                    key: "destroy",
                    value: function() {
                        this.$popover.remove()
                    }
                }, {
                    key: "update",
                    value: function() {
                        if (this.context.invoke("editor.hasFocus")) {
                            var t = this.context.invoke("editor.getLastRange");
                            if (t.isCollapsed() && t.isOnAnchor()) {
                                var o = dt.ancestor(t.sc, dt.isAnchor),
                                    n = e()(o).attr("href");
                                this.$popover.find("a").attr("href", n).text(n);
                                var i = dt.posFromPlaceholder(o),
                                    r = e()(this.options.container).offset();
                                i.top -= r.top, i.left -= r.left, this.$popover.css({
                                    display: "block",
                                    left: i.left,
                                    top: i.top
                                })
                            } else this.hide()
                        } else this.hide()
                    }
                }, {
                    key: "hide",
                    value: function() {
                        this.$popover.hide()
                    }
                }]) && ce(o.prototype, n), i && ce(o, i), t
            }();

            function de(t, e) {
                for (var o = 0; o < e.length; o++) {
                    var n = e[o];
                    n.enumerable = n.enumerable || !1, n.configurable = !0, "value" in n && (n.writable = !0), Object.defineProperty(t, n.key, n)
                }
            }
            var he = function() {
                function t(o) {
                    ! function(t, e) {
                        if (!(t instanceof e)) throw new TypeError("Cannot call a class as a function")
                    }(this, t), this.context = o, this.ui = e().summernote.ui, this.$body = e()(document.body), this.$editor = o.layoutInfo.editor, this.options = o.options, this.lang = this.options.langInfo
                }
                var o, n, i;
                return o = t, (n = [{
                    key: "initialize",
                    value: function() {
                        var t = "";
                        if (this.options.maximumImageFileSize) {
                            var e = Math.floor(Math.log(this.options.maximumImageFileSize) / Math.log(1024)),
                                o = 1 * (this.options.maximumImageFileSize / Math.pow(1024, e)).toFixed(2) + " " + " KMGTP" [e] + "B";
                            t = "<small>".concat(this.lang.image.maximumFileSize + " : " + o, "</small>")
                        }
                        var n = this.options.dialogsInBody ? this.$body : this.options.container,
                            i = ['<div class="form-group note-form-group note-group-select-from-files">', '<label for="note-dialog-image-file-' + this.options.id + '" class="note-form-label">' + this.lang.image.selectFromFiles + "</label>", '<input id="note-dialog-image-file-' + this.options.id + '" class="note-image-input form-control-file note-form-control note-input" ', ' type="file" name="files" accept="' + this.options.acceptImageFileTypes + '" multiple="multiple"/>', t, "</div>", '<div class="form-group note-group-image-url">', '<label for="note-dialog-image-url-' + this.options.id + '" class="note-form-label">' + this.lang.image.url + "</label>", '<input id="note-dialog-image-url-' + this.options.id + '" class="note-image-url form-control note-form-control note-input" type="text"/>', "</div>"].join(""),
                            r = '<input type="button" href="#" class="'.concat("btn btn-primary note-btn note-btn-primary note-image-btn", '" value="').concat(this.lang.image.insert, '" disabled>');
                        this.$dialog = this.ui.dialog({
                            title: this.lang.image.insert,
                            fade: this.options.dialogsFade,
                            body: i,
                            footer: r
                        }).render().appendTo(n)
                    }
                }, {
                    key: "destroy",
                    value: function() {
                        this.ui.hideDialog(this.$dialog), this.$dialog.remove()
                    }
                }, {
                    key: "bindEnterKey",
                    value: function(t, e) {
                        t.on("keypress", (function(t) {
                            t.keyCode === yt.code.ENTER && (t.preventDefault(), e.trigger("click"))
                        }))
                    }
                }, {
                    key: "show",
                    value: function() {
                        var t = this;
                        this.context.invoke("editor.saveRange"), this.showImageDialog().then((function(e) {
                            t.ui.hideDialog(t.$dialog), t.context.invoke("editor.restoreRange"), "string" == typeof e ? t.options.callbacks.onImageLinkInsert ? t.context.triggerEvent("image.link.insert", e) : t.context.invoke("editor.insertImage", e) : t.context.invoke("editor.insertImagesOrCallback", e)
                        })).fail((function() {
                            t.context.invoke("editor.restoreRange")
                        }))
                    }
                }, {
                    key: "showImageDialog",
                    value: function() {
                        var t = this;
                        return e().Deferred((function(e) {
                            var o = t.$dialog.find(".note-image-input"),
                                n = t.$dialog.find(".note-image-url"),
                                i = t.$dialog.find(".note-image-btn");
                            t.ui.onDialogShown(t.$dialog, (function() {
                                t.context.triggerEvent("dialog.shown"), o.replaceWith(o.clone().on("change", (function(t) {
                                    e.resolve(t.target.files || t.target.value)
                                })).val("")), n.on("input paste propertychange", (function() {
                                    t.ui.toggleBtn(i, n.val())
                                })).val(""), f.isSupportTouch || n.trigger("focus"), i.click((function(t) {
                                    t.preventDefault(), e.resolve(n.val())
                                })), t.bindEnterKey(n, i)
                            })), t.ui.onDialogHidden(t.$dialog, (function() {
                                o.off(), n.off(), i.off(), "pending" === e.state() && e.reject()
                            })), t.ui.showDialog(t.$dialog)
                        }))
                    }
                }]) && de(o.prototype, n), i && de(o, i), t
            }();

            function fe(t, e) {
                for (var o = 0; o < e.length; o++) {
                    var n = e[o];
                    n.enumerable = n.enumerable || !1, n.configurable = !0, "value" in n && (n.writable = !0), Object.defineProperty(t, n.key, n)
                }
            }
            var pe = function() {
                function t(o) {
                    var n = this;
                    ! function(t, e) {
                        if (!(t instanceof e)) throw new TypeError("Cannot call a class as a function")
                    }(this, t), this.context = o, this.ui = e().summernote.ui, this.editable = o.layoutInfo.editable[0], this.options = o.options, this.events = {
                        "summernote.disable summernote.dialog.shown": function() {
                            n.hide()
                        },
                        "summernote.blur": function(t, e) {
                            e.originalEvent && e.originalEvent.relatedTarget && n.$popover[0].contains(e.originalEvent.relatedTarget) || n.hide()
                        }
                    }
                }
                var o, n, i;
                return o = t, (n = [{
                    key: "shouldInitialize",
                    value: function() {
                        return !y.isEmpty(this.options.popover.image)
                    }
                }, {
                    key: "initialize",
                    value: function() {
                        this.$popover = this.ui.popover({
                            className: "note-image-popover"
                        }).render().appendTo(this.options.container);
                        var t = this.$popover.find(".popover-content,.note-popover-content");
                        this.context.invoke("buttons.build", t, this.options.popover.image), this.$popover.on("mousedown", (function(t) {
                            t.preventDefault()
                        }))
                    }
                }, {
                    key: "destroy",
                    value: function() {
                        this.$popover.remove()
                    }
                }, {
                    key: "update",
                    value: function(t, o) {
                        if (dt.isImg(t)) {
                            var n = e()(t).offset(),
                                i = e()(this.options.container).offset(),
                                r = {};
                            this.options.popatmouse ? (r.left = o.pageX - 20, r.top = o.pageY) : r = n, r.top -= i.top, r.left -= i.left, this.$popover.css({
                                display: "block",
                                left: r.left,
                                top: r.top
                            })
                        } else this.hide()
                    }
                }, {
                    key: "hide",
                    value: function() {
                        this.$popover.hide()
                    }
                }]) && fe(o.prototype, n), i && fe(o, i), t
            }();

            function me(t, e) {
                for (var o = 0; o < e.length; o++) {
                    var n = e[o];
                    n.enumerable = n.enumerable || !1, n.configurable = !0, "value" in n && (n.writable = !0), Object.defineProperty(t, n.key, n)
                }
            }
            var ve = function() {
                function t(o) {
                    var n = this;
                    ! function(t, e) {
                        if (!(t instanceof e)) throw new TypeError("Cannot call a class as a function")
                    }(this, t), this.context = o, this.ui = e().summernote.ui, this.options = o.options, this.events = {
                        "summernote.mousedown": function(t, e) {
                            n.update(e.target)
                        },
                        "summernote.keyup summernote.scroll summernote.change": function() {
                            n.update()
                        },
                        "summernote.disable summernote.dialog.shown": function() {
                            n.hide()
                        },
                        "summernote.blur": function(t, e) {
                            e.originalEvent && e.originalEvent.relatedTarget && n.$popover[0].contains(e.originalEvent.relatedTarget) || n.hide()
                        }
                    }
                }
                var o, n, i;
                return o = t, (n = [{
                    key: "shouldInitialize",
                    value: function() {
                        return !y.isEmpty(this.options.popover.table)
                    }
                }, {
                    key: "initialize",
                    value: function() {
                        this.$popover = this.ui.popover({
                            className: "note-table-popover"
                        }).render().appendTo(this.options.container);
                        var t = this.$popover.find(".popover-content,.note-popover-content");
                        this.context.invoke("buttons.build", t, this.options.popover.table), f.isFF && document.execCommand("enableInlineTableEditing", !1, !1), this.$popover.on("mousedown", (function(t) {
                            t.preventDefault()
                        }))
                    }
                }, {
                    key: "destroy",
                    value: function() {
                        this.$popover.remove()
                    }
                }, {
                    key: "update",
                    value: function(t) {
                        if (this.context.isDisabled()) return !1;
                        var o = dt.isCell(t) || dt.isCell(null == t ? void 0 : t.parentElement);
                        if (o) {
                            var n = dt.posFromPlaceholder(t),
                                i = e()(this.options.container).offset();
                            n.top -= i.top, n.left -= i.left, this.$popover.css({
                                display: "block",
                                left: n.left,
                                top: n.top
                            })
                        } else this.hide();
                        return o
                    }
                }, {
                    key: "hide",
                    value: function() {
                        this.$popover.hide()
                    }
                }]) && me(o.prototype, n), i && me(o, i), t
            }();

            function ge(t, e) {
                for (var o = 0; o < e.length; o++) {
                    var n = e[o];
                    n.enumerable = n.enumerable || !1, n.configurable = !0, "value" in n && (n.writable = !0), Object.defineProperty(t, n.key, n)
                }
            }
            var be = function() {
                function t(o) {
                    ! function(t, e) {
                        if (!(t instanceof e)) throw new TypeError("Cannot call a class as a function")
                    }(this, t), this.context = o, this.ui = e().summernote.ui, this.$body = e()(document.body), this.$editor = o.layoutInfo.editor, this.options = o.options, this.lang = this.options.langInfo
                }
                var o, n, i;
                return o = t, (n = [{
                    key: "initialize",
                    value: function() {
                        var t = this.options.dialogsInBody ? this.$body : this.options.container,
                            e = ['<div class="form-group note-form-group row-fluid">', '<label for="note-dialog-video-url-'.concat(this.options.id, '" class="note-form-label">').concat(this.lang.video.url, ' <small class="text-muted">').concat(this.lang.video.providers, "</small></label>"), '<input id="note-dialog-video-url-'.concat(this.options.id, '" class="note-video-url form-control note-form-control note-input" type="text"/>'), "</div>"].join(""),
                            o = '<input type="button" href="#" class="'.concat("btn btn-primary note-btn note-btn-primary note-video-btn", '" value="').concat(this.lang.video.insert, '" disabled>');
                        this.$dialog = this.ui.dialog({
                            title: this.lang.video.insert,
                            fade: this.options.dialogsFade,
                            body: e,
                            footer: o
                        }).render().appendTo(t)
                    }
                }, {
                    key: "destroy",
                    value: function() {
                        this.ui.hideDialog(this.$dialog), this.$dialog.remove()
                    }
                }, {
                    key: "bindEnterKey",
                    value: function(t, e) {
                        t.on("keypress", (function(t) {
                            t.keyCode === yt.code.ENTER && (t.preventDefault(), e.trigger("click"))
                        }))
                    }
                }, {
                    key: "createVideoNode",
                    value: function(t) {
                        var o, n = t.match(/\/\/(?:(?:www|m)\.)?(?:youtu\.be\/|youtube\.com\/(?:embed\/|v\/|watch\?v=|watch\?.+&v=))([\w|-]{11})(?:(?:[\?&]t=)(\S+))?$/),
                            i = t.match(/(?:\.|\/\/)drive\.google\.com\/file\/d\/(.[a-zA-Z0-9_-]*)\/view/),
                            r = t.match(/(?:www\.|\/\/)instagram\.com\/p\/(.[a-zA-Z0-9_-]*)/),
                            a = t.match(/\/\/vine\.co\/v\/([a-zA-Z0-9]+)/),
                            s = t.match(/\/\/(player\.)?vimeo\.com\/([a-z]*\/)*(\d+)[?]?.*/),
                            l = t.match(/.+dailymotion.com\/(video|hub)\/([^_]+)[^#]*(#video=([^_&]+))?/),
                            c = t.match(/\/\/v\.youku\.com\/v_show\/id_(\w+)=*\.html/),
                            u = t.match(/\/\/(.*)\/videos\/watch\/([^?]*)(?:\?(?:start=(\w*))?(?:&stop=(\w*))?(?:&loop=([10]))?(?:&autoplay=([10]))?(?:&muted=([10]))?)?/),
                            d = t.match(/\/\/v\.qq\.com.*?vid=(.+)/),
                            h = t.match(/\/\/v\.qq\.com\/x?\/?(page|cover).*?\/([^\/]+)\.html\??.*/),
                            f = t.match(/^.+.(mp4|m4v)$/),
                            p = t.match(/^.+.(ogg|ogv)$/),
                            m = t.match(/^.+.(webm)$/),
                            v = t.match(/(?:www\.|\/\/)facebook\.com\/([^\/]+)\/videos\/([0-9]+)/);
                        if (n && 11 === n[1].length) {
                            var g = n[1],
                                b = 0;
                            if (void 0 !== n[2]) {
                                var k = n[2].match(/^(?:(\d+)h)?(?:(\d+)m)?(?:(\d+)s)?$/);
                                if (k)
                                    for (var y = [3600, 60, 1], w = 0, C = y.length; w < C; w++) b += void 0 !== k[w + 1] ? y[w] * parseInt(k[w + 1], 10) : 0
                            }
                            o = e()("<iframe>").attr("frameborder", 0).attr("src", "//www.youtube.com/embed/" + g + (b > 0 ? "?start=" + b : "")).attr("width", "640").attr("height", "360")
                        } else if (i && i[0].length) o = e()("<iframe>").attr("frameborder", 0).attr("src", "https://drive.google.com/file/d/" + i[1] + "/preview").attr("width", "640").attr("height", "480");
                        else if (r && r[0].length) o = e()("<iframe>").attr("frameborder", 0).attr("src", "https://instagram.com/p/" + r[1] + "/embed/").attr("width", "612").attr("height", "710").attr("scrolling", "no").attr("allowtransparency", "true");
                        else if (a && a[0].length) o = e()("<iframe>").attr("frameborder", 0).attr("src", a[0] + "/embed/simple").attr("width", "600").attr("height", "600").attr("class", "vine-embed");
                        else if (s && s[3].length) o = e()("<iframe webkitallowfullscreen mozallowfullscreen allowfullscreen>").attr("frameborder", 0).attr("src", "//player.vimeo.com/video/" + s[3]).attr("width", "640").attr("height", "360");
                        else if (l && l[2].length) o = e()("<iframe>").attr("frameborder", 0).attr("src", "//www.dailymotion.com/embed/video/" + l[2]).attr("width", "640").attr("height", "360");
                        else if (c && c[1].length) o = e()("<iframe webkitallowfullscreen mozallowfullscreen allowfullscreen>").attr("frameborder", 0).attr("height", "498").attr("width", "510").attr("src", "//player.youku.com/embed/" + c[1]);
                        else if (u && u[0].length) {
                            var x = 0;
                            "undefined" !== u[2] && (x = u[2]);
                            var S = 0;
                            "undefined" !== u[3] && (S = u[3]);
                            var T = 0;
                            "undefined" !== u[4] && (T = u[4]);
                            var E = 0;
                            "undefined" !== u[5] && (E = u[5]);
                            var $ = 0;
                            "undefined" !== u[6] && ($ = u[6]), o = e()('<iframe allowfullscreen sandbox="allow-same-origin allow-scripts allow-popups">').attr("frameborder", 0).attr("src", "//" + u[1] + "/videos/embed/" + u[2] + "?loop=" + T + "&autoplay=" + E + "&muted=" + $ + (x > 0 ? "&start=" + x : "") + (S > 0 ? "&end=" + b : "")).attr("width", "560").attr("height", "315")
                        } else if (d && d[1].length || h && h[2].length) {
                            var N = d && d[1].length ? d[1] : h[2];
                            o = e()("<iframe webkitallowfullscreen mozallowfullscreen allowfullscreen>").attr("frameborder", 0).attr("height", "310").attr("width", "500").attr("src", "https://v.qq.com/txp/iframe/player.html?vid=" + N + "&amp;auto=0")
                        } else if (f || p || m) o = e()("<video controls>").attr("src", t).attr("width", "640").attr("height", "360");
                        else {
                            if (!v || !v[0].length) return !1;
                            o = e()("<iframe>").attr("frameborder", 0).attr("src", "https://www.facebook.com/plugins/video.php?href=" + encodeURIComponent(v[0]) + "&show_text=0&width=560").attr("width", "560").attr("height", "301").attr("scrolling", "no").attr("allowtransparency", "true")
                        }
                        return o.addClass("note-video-clip"), o[0]
                    }
                }, {
                    key: "show",
                    value: function() {
                        var t = this,
                            e = this.context.invoke("editor.getSelectedText");
                        this.context.invoke("editor.saveRange"), this.showVideoDialog(e).then((function(e) {
                            t.ui.hideDialog(t.$dialog), t.context.invoke("editor.restoreRange");
                            var o = t.createVideoNode(e);
                            o && t.context.invoke("editor.insertNode", o)
                        })).fail((function() {
                            t.context.invoke("editor.restoreRange")
                        }))
                    }
                }, {
                    key: "showVideoDialog",
                    value: function() {
                        var t = this;
                        return e().Deferred((function(e) {
                            var o = t.$dialog.find(".note-video-url"),
                                n = t.$dialog.find(".note-video-btn");
                            t.ui.onDialogShown(t.$dialog, (function() {
                                t.context.triggerEvent("dialog.shown"), o.on("input paste propertychange", (function() {
                                    t.ui.toggleBtn(n, o.val())
                                })), f.isSupportTouch || o.trigger("focus"), n.click((function(t) {
                                    t.preventDefault(), e.resolve(o.val())
                                })), t.bindEnterKey(o, n)
                            })), t.ui.onDialogHidden(t.$dialog, (function() {
                                o.off(), n.off(), "pending" === e.state() && e.reject()
                            })), t.ui.showDialog(t.$dialog)
                        }))
                    }
                }]) && ge(o.prototype, n), i && ge(o, i), t
            }();

            function ke(t, e) {
                for (var o = 0; o < e.length; o++) {
                    var n = e[o];
                    n.enumerable = n.enumerable || !1, n.configurable = !0, "value" in n && (n.writable = !0), Object.defineProperty(t, n.key, n)
                }
            }
            var ye = function() {
                function t(o) {
                    ! function(t, e) {
                        if (!(t instanceof e)) throw new TypeError("Cannot call a class as a function")
                    }(this, t), this.context = o, this.ui = e().summernote.ui, this.$body = e()(document.body), this.$editor = o.layoutInfo.editor, this.options = o.options, this.lang = this.options.langInfo
                }
                var o, n, i;
                return o = t, (n = [{
                    key: "initialize",
                    value: function() {
                        var t = this.options.dialogsInBody ? this.$body : this.options.container,
                            e = ['<p class="text-center">', '<a href="http://summernote.org/" target="_blank" rel="noopener noreferrer">Summernote 0.8.20</a> · ', '<a href="https://github.com/summernote/summernote" target="_blank" rel="noopener noreferrer">Project</a> · ', '<a href="https://github.com/summernote/summernote/issues" target="_blank" rel="noopener noreferrer">Issues</a>', "</p>"].join("");
                        this.$dialog = this.ui.dialog({
                            title: this.lang.options.help,
                            fade: this.options.dialogsFade,
                            body: this.createShortcutList(),
                            footer: e,
                            callback: function(t) {
                                t.find(".modal-body,.note-modal-body").css({
                                    "max-height": 300,
                                    overflow: "scroll"
                                })
                            }
                        }).render().appendTo(t)
                    }
                }, {
                    key: "destroy",
                    value: function() {
                        this.ui.hideDialog(this.$dialog), this.$dialog.remove()
                    }
                }, {
                    key: "createShortcutList",
                    value: function() {
                        var t = this,
                            o = this.options.keyMap[f.isMac ? "mac" : "pc"];
                        return Object.keys(o).map((function(n) {
                            var i = o[n],
                                r = e()('<div><div class="help-list-item"></div></div>');
                            return r.append(e()("<label><kbd>" + n + "</kdb></label>").css({
                                width: 180,
                                "margin-right": 10
                            })).append(e()("<span></span>").html(t.context.memo("help." + i) || i)), r.html()
                        })).join("")
                    }
                }, {
                    key: "showHelpDialog",
                    value: function() {
                        var t = this;
                        return e().Deferred((function(e) {
                            t.ui.onDialogShown(t.$dialog, (function() {
                                t.context.triggerEvent("dialog.shown"), e.resolve()
                            })), t.ui.showDialog(t.$dialog)
                        })).promise()
                    }
                }, {
                    key: "show",
                    value: function() {
                        var t = this;
                        this.context.invoke("editor.saveRange"), this.showHelpDialog().then((function() {
                            t.context.invoke("editor.restoreRange")
                        }))
                    }
                }]) && ke(o.prototype, n), i && ke(o, i), t
            }();

            function we(t, e) {
                for (var o = 0; o < e.length; o++) {
                    var n = e[o];
                    n.enumerable = n.enumerable || !1, n.configurable = !0, "value" in n && (n.writable = !0), Object.defineProperty(t, n.key, n)
                }
            }
            var Ce = function() {
                function t(o) {
                    var n = this;
                    ! function(t, e) {
                        if (!(t instanceof e)) throw new TypeError("Cannot call a class as a function")
                    }(this, t), this.context = o, this.ui = e().summernote.ui, this.options = o.options, this.hidable = !0, this.onContextmenu = !1, this.pageX = null, this.pageY = null, this.events = {
                        "summernote.contextmenu": function(t) {
                            n.options.editing && (t.preventDefault(), t.stopPropagation(), n.onContextmenu = !0, n.update(!0))
                        },
                        "summernote.mousedown": function(t, e) {
                            n.pageX = e.pageX, n.pageY = e.pageY
                        },
                        "summernote.keyup summernote.mouseup summernote.scroll": function(t, e) {
                            n.options.editing && !n.onContextmenu && (n.pageX = e.pageX, n.pageY = e.pageY, n.update()), n.onContextmenu = !1
                        },
                        "summernote.disable summernote.change summernote.dialog.shown summernote.blur": function() {
                            n.hide()
                        },
                        "summernote.focusout": function() {
                            n.$popover.is(":active,:focus") || n.hide()
                        }
                    }
                }
                var o, n, i;
                return o = t, (n = [{
                    key: "shouldInitialize",
                    value: function() {
                        return this.options.airMode && !y.isEmpty(this.options.popover.air)
                    }
                }, {
                    key: "initialize",
                    value: function() {
                        var t = this;
                        this.$popover = this.ui.popover({
                            className: "note-air-popover"
                        }).render().appendTo(this.options.container);
                        var e = this.$popover.find(".popover-content");
                        this.context.invoke("buttons.build", e, this.options.popover.air), this.$popover.on("mousedown", (function() {
                            t.hidable = !1
                        })), this.$popover.on("mouseup", (function() {
                            t.hidable = !0
                        }))
                    }
                }, {
                    key: "destroy",
                    value: function() {
                        this.$popover.remove()
                    }
                }, {
                    key: "update",
                    value: function(t) {
                        var o = this.context.invoke("editor.currentStyle");
                        if (!o.range || o.range.isCollapsed() && !t) this.hide();
                        else {
                            var n = {
                                    left: this.pageX,
                                    top: this.pageY
                                },
                                i = e()(this.options.container).offset();
                            n.top -= i.top, n.left -= i.left, this.$popover.css({
                                display: "block",
                                left: Math.max(n.left, 0) + -5,
                                top: n.top + 5
                            }), this.context.invoke("buttons.updateCurrentStyle", this.$popover)
                        }
                    }
                }, {
                    key: "updateCodeview",
                    value: function(t) {
                        this.ui.toggleBtnActive(this.$popover.find(".btn-codeview"), t), t && this.hide()
                    }
                }, {
                    key: "hide",
                    value: function() {
                        this.hidable && this.$popover.hide()
                    }
                }]) && we(o.prototype, n), i && we(o, i), t
            }();

            function xe(t, e) {
                for (var o = 0; o < e.length; o++) {
                    var n = e[o];
                    n.enumerable = n.enumerable || !1, n.configurable = !0, "value" in n && (n.writable = !0), Object.defineProperty(t, n.key, n)
                }
            }
            var Se = function() {
                function t(o) {
                    var n = this;
                    ! function(t, e) {
                        if (!(t instanceof e)) throw new TypeError("Cannot call a class as a function")
                    }(this, t), this.context = o, this.ui = e().summernote.ui, this.$editable = o.layoutInfo.editable, this.options = o.options, this.hint = this.options.hint || [], this.direction = this.options.hintDirection || "bottom", this.hints = Array.isArray(this.hint) ? this.hint : [this.hint], this.events = {
                        "summernote.keyup": function(t, e) {
                            e.isDefaultPrevented() || n.handleKeyup(e)
                        },
                        "summernote.keydown": function(t, e) {
                            n.handleKeydown(e)
                        },
                        "summernote.disable summernote.dialog.shown summernote.blur": function() {
                            n.hide()
                        }
                    }
                }
                var o, n, i;
                return o = t, (n = [{
                    key: "shouldInitialize",
                    value: function() {
                        return this.hints.length > 0
                    }
                }, {
                    key: "initialize",
                    value: function() {
                        var t = this;
                        this.lastWordRange = null, this.matchingWord = null, this.$popover = this.ui.popover({
                            className: "note-hint-popover",
                            hideArrow: !0,
                            direction: ""
                        }).render().appendTo(this.options.container), this.$popover.hide(), this.$content = this.$popover.find(".popover-content,.note-popover-content"), this.$content.on("click", ".note-hint-item", (function(o) {
                            t.$content.find(".active").removeClass("active"), e()(o.currentTarget).addClass("active"), t.replace()
                        })), this.$popover.on("mousedown", (function(t) {
                            t.preventDefault()
                        }))
                    }
                }, {
                    key: "destroy",
                    value: function() {
                        this.$popover.remove()
                    }
                }, {
                    key: "selectItem",
                    value: function(t) {
                        this.$content.find(".active").removeClass("active"), t.addClass("active"), this.$content[0].scrollTop = t[0].offsetTop - this.$content.innerHeight() / 2
                    }
                }, {
                    key: "moveDown",
                    value: function() {
                        var t = this.$content.find(".note-hint-item.active"),
                            e = t.next();
                        if (e.length) this.selectItem(e);
                        else {
                            var o = t.parent().next();
                            o.length || (o = this.$content.find(".note-hint-group").first()), this.selectItem(o.find(".note-hint-item").first())
                        }
                    }
                }, {
                    key: "moveUp",
                    value: function() {
                        var t = this.$content.find(".note-hint-item.active"),
                            e = t.prev();
                        if (e.length) this.selectItem(e);
                        else {
                            var o = t.parent().prev();
                            o.length || (o = this.$content.find(".note-hint-group").last()), this.selectItem(o.find(".note-hint-item").last())
                        }
                    }
                }, {
                    key: "replace",
                    value: function() {
                        var t = this.$content.find(".note-hint-item.active");
                        if (t.length) {
                            var o = this.nodeFromItem(t);
                            if (null !== this.matchingWord && 0 === this.matchingWord.length) this.lastWordRange.so = this.lastWordRange.eo;
                            else if (null !== this.matchingWord && this.matchingWord.length > 0 && !this.lastWordRange.isCollapsed()) {
                                var n = this.lastWordRange.eo - this.lastWordRange.so - this.matchingWord.length;
                                n > 0 && (this.lastWordRange.so += n)
                            }
                            if (this.lastWordRange.insertNode(o), "next" === this.options.hintSelect) {
                                var i = document.createTextNode("");
                                e()(o).after(i), bt.createFromNodeBefore(i).select()
                            } else bt.createFromNodeAfter(o).select();
                            this.lastWordRange = null, this.hide(), this.context.invoke("editor.focus"), this.context.triggerEvent("change", this.$editable.html(), this.$editable)
                        }
                    }
                }, {
                    key: "nodeFromItem",
                    value: function(t) {
                        var e = this.hints[t.data("index")],
                            o = t.data("item"),
                            n = e.content ? e.content(o) : o;
                        return "string" == typeof n && (n = dt.createText(n)), n
                    }
                }, {
                    key: "createItemTemplates",
                    value: function(t, o) {
                        var n = this.hints[t];
                        return o.map((function(o) {
                            var i = e()('<div class="note-hint-item"></div>');
                            return i.append(n.template ? n.template(o) : o + ""), i.data({
                                index: t,
                                item: o
                            }), i
                        }))
                    }
                }, {
                    key: "handleKeydown",
                    value: function(t) {
                        this.$popover.is(":visible") && (t.keyCode === yt.code.ENTER ? (t.preventDefault(), this.replace()) : t.keyCode === yt.code.UP ? (t.preventDefault(), this.moveUp()) : t.keyCode === yt.code.DOWN && (t.preventDefault(), this.moveDown()))
                    }
                }, {
                    key: "searchKeyword",
                    value: function(t, e, o) {
                        var n = this.hints[t];
                        if (n && n.match.test(e) && n.search) {
                            var i = n.match.exec(e);
                            this.matchingWord = i[0], n.search(i[1], o)
                        } else o()
                    }
                }, {
                    key: "createGroup",
                    value: function(t, o) {
                        var n = this,
                            i = e()('<div class="note-hint-group note-hint-group-' + t + '"></div>');
                        return this.searchKeyword(t, o, (function(e) {
                            (e = e || []).length && (i.html(n.createItemTemplates(t, e)), n.show())
                        })), i
                    }
                }, {
                    key: "handleKeyup",
                    value: function(t) {
                        var o = this;
                        if (!y.contains([yt.code.ENTER, yt.code.UP, yt.code.DOWN], t.keyCode)) {
                            var n, i, r = this.context.invoke("editor.getLastRange");
                            if ("words" === this.options.hintMode) {
                                if (n = r.getWordsRange(r), i = n.toString(), this.hints.forEach((function(t) {
                                        if (t.match.test(i)) return n = r.getWordsMatchRange(t.match), !1
                                    })), !n) return void this.hide();
                                i = n.toString()
                            } else n = r.getWordRange(), i = n.toString();
                            if (this.hints.length && i) {
                                this.$content.empty();
                                var a = m.rect2bnd(y.last(n.getClientRects())),
                                    s = e()(this.options.container).offset();
                                a && (a.top -= s.top, a.left -= s.left, this.$popover.hide(), this.lastWordRange = n, this.hints.forEach((function(t, e) {
                                    t.match.test(i) && o.createGroup(e, i).appendTo(o.$content)
                                })), this.$content.find(".note-hint-item:first").addClass("active"), "top" === this.direction ? this.$popover.css({
                                    left: a.left,
                                    top: a.top - this.$popover.outerHeight() - 5
                                }) : this.$popover.css({
                                    left: a.left,
                                    top: a.top + a.height + 5
                                }))
                            } else this.hide()
                        }
                    }
                }, {
                    key: "show",
                    value: function() {
                        this.$popover.show()
                    }
                }, {
                    key: "hide",
                    value: function() {
                        this.$popover.hide()
                    }
                }]) && xe(o.prototype, n), i && xe(o, i), t
            }();

            function Te(t) {
                return (Te = "function" == typeof Symbol && "symbol" == typeof Symbol.iterator ? function(t) {
                    return typeof t
                } : function(t) {
                    return t && "function" == typeof Symbol && t.constructor === Symbol && t !== Symbol.prototype ? "symbol" : typeof t
                })(t)
            }

            function Ee(t, e) {
                for (var o = 0; o < e.length; o++) {
                    var n = e[o];
                    n.enumerable = n.enumerable || !1, n.configurable = !0, "value" in n && (n.writable = !0), Object.defineProperty(t, n.key, n)
                }
            }
            e().summernote = e().extend(e().summernote, {
                version: "0.8.20",
                plugins: {},
                dom: dt,
                range: bt,
                lists: y,
                options: {
                    langInfo: e().summernote.lang["en-US"],
                    editing: !0,
                    modules: {
                        editor: At,
                        clipboard: Dt,
                        dropzone: Bt,
                        codeview: jt,
                        statusbar: Wt,
                        fullscreen: Vt,
                        handle: _t,
                        hintPopover: Se,
                        autoLink: Zt,
                        autoSync: Qt,
                        autoReplace: te,
                        placeholder: oe,
                        buttons: ie,
                        toolbar: ae,
                        linkDialog: le,
                        linkPopover: ue,
                        imageDialog: he,
                        imagePopover: pe,
                        tablePopover: ve,
                        videoDialog: be,
                        helpDialog: ye,
                        airPopover: Ce
                    },
                    buttons: {},
                    lang: "en-US",
                    followingToolbar: !1,
                    toolbarPosition: "top",
                    otherStaticBar: "",
                    codeviewKeepButton: !1,
                    toolbar: [
                        ["style", ["style"]],
                        ["font", ["bold", "underline", "clear"]],
                        ["fontname", ["fontname"]],
                        ["color", ["color"]],
                        ["para", ["ul", "ol", "paragraph"]],
                        ["table", ["table"]],
                        ["insert", ["link", "picture", "video"]],
                        ["view", ["fullscreen", "codeview", "help"]]
                    ],
                    popatmouse: !0,
                    popover: {
                        image: [
                            ["resize", ["resizeFull", "resizeHalf", "resizeQuarter", "resizeNone"]],
                            ["float", ["floatLeft", "floatRight", "floatNone"]],
                            ["remove", ["removeMedia"]]
                        ],
                        link: [
                            ["link", ["linkDialogShow", "unlink"]]
                        ],
                        table: [
                            ["add", ["addRowDown", "addRowUp", "addColLeft", "addColRight"]],
                            ["delete", ["deleteRow", "deleteCol", "deleteTable"]]
                        ],
                        air: [
                            ["color", ["color"]],
                            ["font", ["bold", "underline", "clear"]],
                            ["para", ["ul", "paragraph"]],
                            ["table", ["table"]],
                            ["insert", ["link", "picture"]],
                            ["view", ["fullscreen", "codeview"]]
                        ]
                    },
                    airMode: !1,
                    overrideContextMenu: !1,
                    width: null,
                    height: null,
                    linkTargetBlank: !0,
                    useProtocol: !0,
                    defaultProtocol: "http://",
                    focus: !1,
                    tabDisabled: !1,
                    tabSize: 4,
                    styleWithCSS: !1,
                    shortcuts: !0,
                    textareaAutoSync: !0,
                    tooltip: "auto",
                    container: null,
                    maxTextLength: 0,
                    blockquoteBreakingLevel: 2,
                    spellCheck: !0,
                    disableGrammar: !1,
                    placeholder: null,
                    inheritPlaceholder: !1,
                    recordEveryKeystroke: !1,
                    historyLimit: 200,
                    showDomainOnlyForAutolink: !1,
                    hintMode: "word",
                    hintSelect: "after",
                    hintDirection: "bottom",
                    styleTags: ["p", "blockquote", "pre", "h1", "h2", "h3", "h4", "h5", "h6"],
                    fontNames: ["Arial", "Arial Black", "Comic Sans MS", "Courier New", "Helvetica Neue", "Helvetica", "Impact", "Lucida Grande", "Tahoma", "Times New Roman", "Verdana"],
                    fontNamesIgnoreCheck: [],
                    addDefaultFonts: !0,
                    fontSizes: ["8", "9", "10", "11", "12", "14", "18", "24", "36"],
                    fontSizeUnits: ["px", "pt"],
                    colors: [
                        ["#000000", "#424242", "#636363", "#9C9C94", "#CEC6CE", "#EFEFEF", "#F7F7F7", "#FFFFFF"],
                        ["#FF0000", "#FF9C00", "#FFFF00", "#00FF00", "#00FFFF", "#0000FF", "#9C00FF", "#FF00FF"],
                        ["#F7C6CE", "#FFE7CE", "#FFEFC6", "#D6EFD6", "#CEDEE7", "#CEE7F7", "#D6D6E7", "#E7D6DE"],
                        ["#E79C9C", "#FFC69C", "#FFE79C", "#B5D6A5", "#A5C6CE", "#9CC6EF", "#B5A5D6", "#D6A5BD"],
                        ["#E76363", "#F7AD6B", "#FFD663", "#94BD7B", "#73A5AD", "#6BADDE", "#8C7BC6", "#C67BA5"],
                        ["#CE0000", "#E79439", "#EFC631", "#6BA54A", "#4A7B8C", "#3984C6", "#634AA5", "#A54A7B"],
                        ["#9C0000", "#B56308", "#BD9400", "#397B21", "#104A5A", "#085294", "#311873", "#731842"],
                        ["#630000", "#7B3900", "#846300", "#295218", "#083139", "#003163", "#21104A", "#4A1031"]
                    ],
                    colorsName: [
                        ["Black", "Tundora", "Dove Gray", "Star Dust", "Pale Slate", "Gallery", "Alabaster", "White"],
                        ["Red", "Orange Peel", "Yellow", "Green", "Cyan", "Blue", "Electric Violet", "Magenta"],
                        ["Azalea", "Karry", "Egg White", "Zanah", "Botticelli", "Tropical Blue", "Mischka", "Twilight"],
                        ["Tonys Pink", "Peach Orange", "Cream Brulee", "Sprout", "Casper", "Perano", "Cold Purple", "Careys Pink"],
                        ["Mandy", "Rajah", "Dandelion", "Olivine", "Gulf Stream", "Viking", "Blue Marguerite", "Puce"],
                        ["Guardsman Red", "Fire Bush", "Golden Dream", "Chelsea Cucumber", "Smalt Blue", "Boston Blue", "Butterfly Bush", "Cadillac"],
                        ["Sangria", "Mai Tai", "Buddha Gold", "Forest Green", "Eden", "Venice Blue", "Meteorite", "Claret"],
                        ["Rosewood", "Cinnamon", "Olive", "Parsley", "Tiber", "Midnight Blue", "Valentino", "Loulou"]
                    ],
                    colorButton: {
                        foreColor: "#000000",
                        backColor: "#FFFF00"
                    },
                    lineHeights: ["1.0", "1.2", "1.4", "1.5", "1.6", "1.8", "2.0", "3.0"],
                    tableClassName: "table table-bordered",
                    insertTableMaxSize: {
                        col: 10,
                        row: 10
                    },
                    dialogsInBody: !1,
                    dialogsFade: !1,
                    maximumImageFileSize: null,
                    acceptImageFileTypes: "image/*",
                    callbacks: {
                        onBeforeCommand: null,
                        onBlur: null,
                        onBlurCodeview: null,
                        onChange: null,
                        onChangeCodeview: null,
                        onDialogShown: null,
                        onEnter: null,
                        onFocus: null,
                        onImageLinkInsert: null,
                        onImageUpload: null,
                        onImageUploadError: null,
                        onInit: null,
                        onKeydown: null,
                        onKeyup: null,
                        onMousedown: null,
                        onMouseup: null,
                        onPaste: null,
                        onScroll: null
                    },
                    codemirror: {
                        mode: "text/html",
                        htmlMode: !0,
                        lineNumbers: !0
                    },
                    codeviewFilter: !0,
                    codeviewFilterRegex: /<\/*(?:applet|b(?:ase|gsound|link)|embed|frame(?:set)?|ilayer|l(?:ayer|ink)|meta|object|s(?:cript|tyle)|t(?:itle|extarea)|xml)[^>]*?>/gi,
                    codeviewIframeFilter: !0,
                    codeviewIframeWhitelistSrc: [],
                    codeviewIframeWhitelistSrcBase: ["www.youtube.com", "www.youtube-nocookie.com", "www.facebook.com", "vine.co", "instagram.com", "player.vimeo.com", "www.dailymotion.com", "player.youku.com", "jumpingbean.tv", "v.qq.com"],
                    keyMap: {
                        pc: {
                            ESC: "escape",
                            ENTER: "insertParagraph",
                            "CTRL+Z": "undo",
                            "CTRL+Y": "redo",
                            TAB: "tab",
                            "SHIFT+TAB": "untab",
                            "CTRL+B": "bold",
                            "CTRL+I": "italic",
                            "CTRL+U": "underline",
                            "CTRL+SHIFT+S": "strikethrough",
                            "CTRL+BACKSLASH": "removeFormat",
                            "CTRL+SHIFT+L": "justifyLeft",
                            "CTRL+SHIFT+E": "justifyCenter",
                            "CTRL+SHIFT+R": "justifyRight",
                            "CTRL+SHIFT+J": "justifyFull",
                            "CTRL+SHIFT+NUM7": "insertUnorderedList",
                            "CTRL+SHIFT+NUM8": "insertOrderedList",
                            "CTRL+LEFTBRACKET": "outdent",
                            "CTRL+RIGHTBRACKET": "indent",
                            "CTRL+NUM0": "formatPara",
                            "CTRL+NUM1": "formatH1",
                            "CTRL+NUM2": "formatH2",
                            "CTRL+NUM3": "formatH3",
                            "CTRL+NUM4": "formatH4",
                            "CTRL+NUM5": "formatH5",
                            "CTRL+NUM6": "formatH6",
                            "CTRL+ENTER": "insertHorizontalRule",
                            "CTRL+K": "linkDialog.show"
                        },
                        mac: {
                            ESC: "escape",
                            ENTER: "insertParagraph",
                            "CMD+Z": "undo",
                            "CMD+SHIFT+Z": "redo",
                            TAB: "tab",
                            "SHIFT+TAB": "untab",
                            "CMD+B": "bold",
                            "CMD+I": "italic",
                            "CMD+U": "underline",
                            "CMD+SHIFT+S": "strikethrough",
                            "CMD+BACKSLASH": "removeFormat",
                            "CMD+SHIFT+L": "justifyLeft",
                            "CMD+SHIFT+E": "justifyCenter",
                            "CMD+SHIFT+R": "justifyRight",
                            "CMD+SHIFT+J": "justifyFull",
                            "CMD+SHIFT+NUM7": "insertUnorderedList",
                            "CMD+SHIFT+NUM8": "insertOrderedList",
                            "CMD+LEFTBRACKET": "outdent",
                            "CMD+RIGHTBRACKET": "indent",
                            "CMD+NUM0": "formatPara",
                            "CMD+NUM1": "formatH1",
                            "CMD+NUM2": "formatH2",
                            "CMD+NUM3": "formatH3",
                            "CMD+NUM4": "formatH4",
                            "CMD+NUM5": "formatH5",
                            "CMD+NUM6": "formatH6",
                            "CMD+ENTER": "insertHorizontalRule",
                            "CMD+K": "linkDialog.show"
                        }
                    },
                    icons: {
                        align: "note-icon-align",
                        alignCenter: "note-icon-align-center",
                        alignJustify: "note-icon-align-justify",
                        alignLeft: "note-icon-align-left",
                        alignRight: "note-icon-align-right",
                        rowBelow: "note-icon-row-below",
                        colBefore: "note-icon-col-before",
                        colAfter: "note-icon-col-after",
                        rowAbove: "note-icon-row-above",
                        rowRemove: "note-icon-row-remove",
                        colRemove: "note-icon-col-remove",
                        indent: "note-icon-align-indent",
                        outdent: "note-icon-align-outdent",
                        arrowsAlt: "note-icon-arrows-alt",
                        bold: "note-icon-bold",
                        caret: "note-icon-caret",
                        circle: "note-icon-circle",
                        close: "note-icon-close",
                        code: "note-icon-code",
                        eraser: "note-icon-eraser",
                        floatLeft: "note-icon-float-left",
                        floatRight: "note-icon-float-right",
                        font: "note-icon-font",
                        frame: "note-icon-frame",
                        italic: "note-icon-italic",
                        link: "note-icon-link",
                        unlink: "note-icon-chain-broken",
                        magic: "note-icon-magic",
                        menuCheck: "note-icon-menu-check",
                        minus: "note-icon-minus",
                        orderedlist: "note-icon-orderedlist",
                        pencil: "note-icon-pencil",
                        picture: "note-icon-picture",
                        question: "note-icon-question",
                        redo: "note-icon-redo",
                        rollback: "note-icon-rollback",
                        square: "note-icon-square",
                        strikethrough: "note-icon-strikethrough",
                        subscript: "note-icon-subscript",
                        superscript: "note-icon-superscript",
                        table: "note-icon-table",
                        textHeight: "note-icon-text-height",
                        trash: "note-icon-trash",
                        underline: "note-icon-underline",
                        undo: "note-icon-undo",
                        unorderedlist: "note-icon-unorderedlist",
                        video: "note-icon-video"
                    }
                }
            });
            var $e = function() {
                function t(e, o, n, i) {
                    ! function(t, e) {
                        if (!(t instanceof e)) throw new TypeError("Cannot call a class as a function")
                    }(this, t), this.markup = e, this.children = o, this.options = n, this.callback = i
                }
                var o, n, i;
                return o = t, (n = [{
                    key: "render",
                    value: function(t) {
                        var o = e()(this.markup);
                        if (this.options && this.options.contents && o.html(this.options.contents), this.options && this.options.className && o.addClass(this.options.className), this.options && this.options.data && e().each(this.options.data, (function(t, e) {
                                o.attr("data-" + t, e)
                            })), this.options && this.options.click && o.on("click", this.options.click), this.children) {
                            var n = o.find(".note-children-container");
                            this.children.forEach((function(t) {
                                t.render(n.length ? n : o)
                            }))
                        }
                        return this.callback && this.callback(o, this.options), this.options && this.options.callback && this.options.callback(o), t && t.append(o), o
                    }
                }]) && Ee(o.prototype, n), i && Ee(o, i), t
            }();
            const Ne = function(t, e) {
                return function() {
                    var o = "object" === Te(arguments[1]) ? arguments[1] : arguments[0],
                        n = Array.isArray(arguments[0]) ? arguments[0] : [];
                    return o && o.children && (n = o.children), new $e(t, n, o, e)
                }
            };

            function Ie(t, e) {
                for (var o = 0; o < e.length; o++) {
                    var n = e[o];
                    n.enumerable = n.enumerable || !1, n.configurable = !0, "value" in n && (n.writable = !0), Object.defineProperty(t, n.key, n)
                }
            }
            const Pe = function() {
                function t(o, n) {
                    if (function(t, e) {
                            if (!(t instanceof e)) throw new TypeError("Cannot call a class as a function")
                        }(this, t), this.$node = o, this.options = e().extend({}, {
                            title: "",
                            target: n.container,
                            trigger: "hover focus",
                            placement: "bottom"
                        }, n), this.$tooltip = e()(['<div class="note-tooltip">', '<div class="note-tooltip-arrow"></div>', '<div class="note-tooltip-content"></div>', "</div>"].join("")), "manual" !== this.options.trigger) {
                        var i = this.show.bind(this),
                            r = this.hide.bind(this),
                            a = this.toggle.bind(this);
                        this.options.trigger.split(" ").forEach((function(t) {
                            "hover" === t ? (o.off("mouseenter mouseleave"), o.on("mouseenter", i).on("mouseleave", r)) : "click" === t ? o.on("click", a) : "focus" === t && o.on("focus", i).on("blur", r)
                        }))
                    }
                }
                var o, n, i;
                return o = t, (n = [{
                    key: "show",
                    value: function() {
                        var t = this.$node,
                            o = t.offset(),
                            n = e()(this.options.target).offset();
                        o.top -= n.top, o.left -= n.left;
                        var i = this.$tooltip,
                            r = this.options.title || t.attr("title") || t.data("title"),
                            a = this.options.placement || t.data("placement");
                        i.addClass(a), i.find(".note-tooltip-content").text(r), i.appendTo(this.options.target);
                        var s = t.outerWidth(),
                            l = t.outerHeight(),
                            c = i.outerWidth(),
                            u = i.outerHeight();
                        "bottom" === a ? i.css({
                            top: o.top + l,
                            left: o.left + (s / 2 - c / 2)
                        }) : "top" === a ? i.css({
                            top: o.top - u,
                            left: o.left + (s / 2 - c / 2)
                        }) : "left" === a ? i.css({
                            top: o.top + (l / 2 - u / 2),
                            left: o.left - c
                        }) : "right" === a && i.css({
                            top: o.top + (l / 2 - u / 2),
                            left: o.left + s
                        }), i.addClass("in")
                    }
                }, {
                    key: "hide",
                    value: function() {
                        var t = this;
                        this.$tooltip.removeClass("in"), setTimeout((function() {
                            t.$tooltip.remove()
                        }), 200)
                    }
                }, {
                    key: "toggle",
                    value: function() {
                        this.$tooltip.hasClass("in") ? this.hide() : this.show()
                    }
                }]) && Ie(o.prototype, n), i && Ie(o, i), t
            }();

            function Re(t, e) {
                for (var o = 0; o < e.length; o++) {
                    var n = e[o];
                    n.enumerable = n.enumerable || !1, n.configurable = !0, "value" in n && (n.writable = !0), Object.defineProperty(t, n.key, n)
                }
            }
            var Le = function() {
                function t(o, n) {
                    ! function(t, e) {
                        if (!(t instanceof e)) throw new TypeError("Cannot call a class as a function")
                    }(this, t), this.$button = o, this.options = e().extend({}, {
                        target: n.container
                    }, n), this.setEvent()
                }
                var o, n, i;
                return o = t, (n = [{
                    key: "setEvent",
                    value: function() {
                        var t = this;
                        this.$button.on("click", (function(e) {
                            t.toggle(), e.stopImmediatePropagation()
                        }))
                    }
                }, {
                    key: "clear",
                    value: function() {
                        var t = e()(".note-btn-group.open");
                        t.find(".note-btn.active").removeClass("active"), t.removeClass("open")
                    }
                }, {
                    key: "show",
                    value: function() {
                        this.$button.addClass("active"), this.$button.parent().addClass("open");
                        var t = this.$button.next(),
                            o = t.offset(),
                            n = t.outerWidth(),
                            i = e()(window).width(),
                            r = parseFloat(e()(this.options.target).css("margin-right"));
                        o.left + n > i - r ? t.css("margin-left", i - r - (o.left + n)) : t.css("margin-left", "")
                    }
                }, {
                    key: "hide",
                    value: function() {
                        this.$button.removeClass("active"), this.$button.parent().removeClass("open")
                    }
                }, {
                    key: "toggle",
                    value: function() {
                        var t = this.$button.parent().hasClass("open");
                        this.clear(), t ? this.hide() : this.show()
                    }
                }]) && Re(o.prototype, n), i && Re(o, i), t
            }();
            e()(document).on("click", (function(t) {
                e()(t.target).closest(".note-btn-group").length || (e()(".note-btn-group.open").removeClass("open"), e()(".note-btn-group .note-btn.active").removeClass("active"))
            })), e()(document).on("click.note-dropdown-menu", (function(t) {
                e()(t.target).closest(".note-dropdown-menu").parent().removeClass("open"), e()(t.target).closest(".note-dropdown-menu").parent().find(".note-btn.active").removeClass("active")
            }));
            const Ae = Le;

            function Fe(t, e) {
                for (var o = 0; o < e.length; o++) {
                    var n = e[o];
                    n.enumerable = n.enumerable || !1, n.configurable = !0, "value" in n && (n.writable = !0), Object.defineProperty(t, n.key, n)
                }
            }
            const De = function() {
                function t(o) {
                    ! function(t, e) {
                        if (!(t instanceof e)) throw new TypeError("Cannot call a class as a function")
                    }(this, t), this.$modal = o, this.$backdrop = e()('<div class="note-modal-backdrop"></div>')
                }
                var o, n, i;
                return o = t, (n = [{
                    key: "show",
                    value: function() {
                        var t = this;
                        this.$backdrop.appendTo(document.body).show(), this.$modal.addClass("open").show(), this.$modal.trigger("note.modal.show"), this.$modal.off("click", ".close").on("click", ".close", this.hide.bind(this)), this.$modal.on("keydown", (function(e) {
                            27 === e.which && (e.preventDefault(), t.hide())
                        }))
                    }
                }, {
                    key: "hide",
                    value: function() {
                        this.$modal.removeClass("open").hide(), this.$backdrop.hide(), this.$modal.trigger("note.modal.hide"), this.$modal.off("keydown")
                    }
                }]) && Fe(o.prototype, n), i && Fe(o, i), t
            }();
            var He = Ne('<div class="note-editor note-frame"></div>'),
                Be = Ne('<div class="note-toolbar" role="toolbar"></div>'),
                ze = Ne('<div class="note-editing-area"></div>'),
                Me = Ne('<textarea class="note-codable" aria-multiline="true"></textarea>'),
                Oe = Ne('<div class="note-editable" contentEditable="true" role="textbox" aria-multiline="true"></div>'),
                je = Ne(['<output class="note-status-output" role="status" aria-live="polite"></output>', '<div class="note-statusbar" role="status">', '<div class="note-resizebar" aria-label="resize">', '<div class="note-icon-bar"></div>', '<div class="note-icon-bar"></div>', '<div class="note-icon-bar"></div>', "</div>", "</div>"].join("")),
                Ue = Ne('<div class="note-editor note-airframe"></div>'),
                We = Ne(['<div class="note-editable" contentEditable="true" role="textbox" aria-multiline="true"></div>', '<output class="note-status-output" role="status" aria-live="polite"></output>'].join("")),
                Ke = Ne('<div class="note-btn-group"></div>'),
                Ve = Ne('<button type="button" class="note-btn" tabindex="-1"></button>', (function(t, o) {
                    o && o.tooltip && (t.attr({
                        "aria-label": o.tooltip
                    }), t.data("_lite_tooltip", new Pe(t, {
                        title: o.tooltip,
                        container: o.container
                    })).on("click", (function(t) {
                        e()(t.currentTarget).data("_lite_tooltip").hide()
                    }))), o.contents && t.html(o.contents), o && o.data && "dropdown" === o.data.toggle && t.data("_lite_dropdown", new Ae(t, {
                        container: o.container
                    })), o && o.codeviewKeepButton && t.addClass("note-codeview-keep")
                })),
                qe = Ne('<div class="note-dropdown-menu" role="list"></div>', (function(t, o) {
                    var n = Array.isArray(o.items) ? o.items.map((function(t) {
                        var n = "string" == typeof t ? t : t.value || "",
                            i = o.template ? o.template(t) : t,
                            r = e()('<a class="note-dropdown-item" href="#" data-value="' + n + '" role="listitem" aria-label="' + n + '"></a>');
                        return r.html(i).data("item", t), r
                    })) : o.items;
                    t.html(n).attr({
                        "aria-label": o.title
                    }), t.on("click", "> .note-dropdown-item", (function(t) {
                        var n = e()(this),
                            i = n.data("item"),
                            r = n.data("value");
                        i.click ? i.click(n) : o.itemClick && o.itemClick(t, i, r)
                    })), o && o.codeviewKeepButton && t.addClass("note-codeview-keep")
                })),
                _e = Ne('<div class="note-dropdown-menu note-check" role="list"></div>', (function(t, o) {
                    var n = Array.isArray(o.items) ? o.items.map((function(t) {
                        var n = "string" == typeof t ? t : t.value || "",
                            i = o.template ? o.template(t) : t,
                            r = e()('<a class="note-dropdown-item" href="#" data-value="' + n + '" role="listitem" aria-label="' + t + '"></a>');
                        return r.html([so(o.checkClassName), " ", i]).data("item", t), r
                    })) : o.items;
                    t.html(n).attr({
                        "aria-label": o.title
                    }), t.on("click", "> .note-dropdown-item", (function(t) {
                        var n = e()(this),
                            i = n.data("item"),
                            r = n.data("value");
                        i.click ? i.click(n) : o.itemClick && o.itemClick(t, i, r)
                    })), o && o.codeviewKeepButton && t.addClass("note-codeview-keep")
                })),
                Ge = function(t, e) {
                    return t + " " + so(e.icons.caret, "span")
                },
                Ye = function(t, e) {
                    return Ke([Ve({
                        className: "dropdown-toggle",
                        contents: t.title + " " + so("note-icon-caret"),
                        tooltip: t.tooltip,
                        data: {
                            toggle: "dropdown"
                        }
                    }), qe({
                        className: t.className,
                        items: t.items,
                        template: t.template,
                        itemClick: t.itemClick
                    })], {
                        callback: e
                    }).render()
                },
                Ze = function(t, e) {
                    return Ke([Ve({
                        className: "dropdown-toggle",
                        contents: t.title + " " + so("note-icon-caret"),
                        tooltip: t.tooltip,
                        data: {
                            toggle: "dropdown"
                        }
                    }), _e({
                        className: t.className,
                        checkClassName: t.checkClassName,
                        items: t.items,
                        template: t.template,
                        itemClick: t.itemClick
                    })], {
                        callback: e
                    }).render()
                },
                Xe = function(t) {
                    return Ke([Ve({
                        className: "dropdown-toggle",
                        contents: t.title + " " + so("note-icon-caret"),
                        tooltip: t.tooltip,
                        data: {
                            toggle: "dropdown"
                        }
                    }), qe([Ke({
                        className: "note-align",
                        children: t.items[0]
                    }), Ke({
                        className: "note-list",
                        children: t.items[1]
                    })])]).render()
                },
                Qe = function(t) {
                    return Ke([Ve({
                        className: "dropdown-toggle",
                        contents: t.title + " " + so("note-icon-caret"),
                        tooltip: t.tooltip,
                        data: {
                            toggle: "dropdown"
                        }
                    }), qe({
                        className: "note-table",
                        items: ['<div class="note-dimension-picker">', '<div class="note-dimension-picker-mousecatcher" data-event="insertTable" data-value="1x1"></div>', '<div class="note-dimension-picker-highlighted"></div>', '<div class="note-dimension-picker-unhighlighted"></div>', "</div>", '<div class="note-dimension-display">1 x 1</div>'].join("")
                    })], {
                        callback: function(o) {
                            o.find(".note-dimension-picker-mousecatcher").css({
                                width: t.col + "em",
                                height: t.row + "em"
                            }).mouseup(t.itemClick).mousemove((function(o) {
                                ! function(t, o, n) {
                                    var i, r = e()(t.target.parentNode),
                                        a = r.next(),
                                        s = r.find(".note-dimension-picker-mousecatcher"),
                                        l = r.find(".note-dimension-picker-highlighted"),
                                        c = r.find(".note-dimension-picker-unhighlighted");
                                    if (void 0 === t.offsetX) {
                                        var u = e()(t.target).offset();
                                        i = {
                                            x: t.pageX - u.left,
                                            y: t.pageY - u.top
                                        }
                                    } else i = {
                                        x: t.offsetX,
                                        y: t.offsetY
                                    };
                                    var d = Math.ceil(i.x / 18) || 1,
                                        h = Math.ceil(i.y / 18) || 1;
                                    l.css({
                                        width: d + "em",
                                        height: h + "em"
                                    }), s.data("value", d + "x" + h), d > 3 && d < o && c.css({
                                        width: d + 1 + "em"
                                    }), h > 3 && h < n && c.css({
                                        height: h + 1 + "em"
                                    }), a.html(d + " x " + h)
                                }(o, t.col, t.row)
                            }))
                        }
                    }).render()
                },
                Je = Ne('<div class="note-color-palette"></div>', (function(t, o) {
                    for (var n = [], i = 0, r = o.colors.length; i < r; i++) {
                        for (var a = o.eventName, s = o.colors[i], l = o.colorsName[i], c = [], u = 0, d = s.length; u < d; u++) {
                            var h = s[u],
                                f = l[u];
                            c.push(['<button type="button" class="note-btn note-color-btn"', 'style="background-color:', h, '" ', 'data-event="', a, '" ', 'data-value="', h, '" ', 'data-title="', f, '" ', 'aria-label="', f, '" ', 'data-toggle="button" tabindex="-1"></button>'].join(""))
                        }
                        n.push('<div class="note-color-row">' + c.join("") + "</div>")
                    }
                    t.html(n.join("")), t.find(".note-color-btn").each((function() {
                        e()(this).data("_lite_tooltip", new Pe(e()(this), {
                            container: o.container
                        }))
                    }))
                })),
                to = function(t, o) {
                    return Ke({
                        className: "note-color",
                        children: [Ve({
                            className: "note-current-color-button",
                            contents: t.title,
                            tooltip: t.lang.color.recent,
                            click: t.currentClick,
                            callback: function(t) {
                                var e = t.find(".note-recent-color");
                                "foreColor" !== o && (e.css("background-color", "#FFFF00"), t.attr("data-backColor", "#FFFF00"))
                            }
                        }), Ve({
                            className: "dropdown-toggle",
                            contents: so("note-icon-caret"),
                            tooltip: t.lang.color.more,
                            data: {
                                toggle: "dropdown"
                            }
                        }), qe({
                            items: ["<div>", '<div class="note-btn-group btn-background-color">', '<div class="note-palette-title">' + t.lang.color.background + "</div>", "<div>", '<button type="button" class="note-color-reset note-btn note-btn-block" data-event="backColor" data-value="transparent">', t.lang.color.transparent, "</button>", "</div>", '<div class="note-holder" data-event="backColor"></div>', '<div class="btn-sm">', '<input type="color" id="html5bcp" class="note-btn btn-default" value="#21104A" style="width:100%;" data-value="cp">', '<button type="button" class="note-color-reset btn" data-event="backColor" data-value="cpbackColor">', t.lang.color.cpSelect, "</button>", "</div>", "</div>", '<div class="note-btn-group btn-foreground-color">', '<div class="note-palette-title">' + t.lang.color.foreground + "</div>", "<div>", '<button type="button" class="note-color-reset note-btn note-btn-block" data-event="removeFormat" data-value="foreColor">', t.lang.color.resetToDefault, "</button>", "</div>", '<div class="note-holder" data-event="foreColor"></div>', '<div class="btn-sm">', '<input type="color" id="html5fcp" class="note-btn btn-default" value="#21104A" style="width:100%;" data-value="cp">', '<button type="button" class="note-color-reset btn" data-event="foreColor" data-value="cpforeColor">', t.lang.color.cpSelect, "</button>", "</div>", "</div>", "</div>"].join(""),
                            callback: function(n) {
                                n.find(".note-holder").each((function() {
                                    var o = e()(this);
                                    o.append(Je({
                                        colors: t.colors,
                                        eventName: o.data("event")
                                    }).render())
                                })), "fore" === o ? (n.find(".btn-background-color").hide(), n.css({
                                    "min-width": "210px"
                                })) : "back" === o && (n.find(".btn-foreground-color").hide(), n.css({
                                    "min-width": "210px"
                                }))
                            },
                            click: function(n) {
                                var i = e()(n.target),
                                    r = i.data("event"),
                                    a = i.data("value"),
                                    s = document.getElementById("html5fcp").value,
                                    l = document.getElementById("html5bcp").value;
                                if ("cp" === a ? n.stopPropagation() : "cpbackColor" === a ? a = l : "cpforeColor" === a && (a = s), r && a) {
                                    var c = "backColor" === r ? "background-color" : "color",
                                        u = i.closest(".note-color").find(".note-recent-color"),
                                        d = i.closest(".note-color").find(".note-current-color-button");
                                    u.css(c, a), d.attr("data-" + r, a), "fore" === o ? t.itemClick("foreColor", a) : "back" === o ? t.itemClick("backColor", a) : t.itemClick(r, a)
                                }
                            }
                        })]
                    }).render()
                },
                eo = Ne('<div class="note-modal" aria-hidden="false" tabindex="-1" role="dialog"></div>', (function(t, e) {
                    e.fade && t.addClass("fade"), t.attr({
                        "aria-label": e.title
                    }), t.html(['<div class="note-modal-content">', e.title ? '<div class="note-modal-header"><button type="button" class="close" aria-label="Close" aria-hidden="true"><i class="note-icon-close"></i></button><h4 class="note-modal-title">' + e.title + "</h4></div>" : "", '<div class="note-modal-body">' + e.body + "</div>", e.footer ? '<div class="note-modal-footer">' + e.footer + "</div>" : "", "</div>"].join("")), t.data("modal", new De(t, e))
                })),
                oo = function(t) {
                    var e = '<div class="note-form-group"><label for="note-dialog-video-url-' + t.id + '" class="note-form-label">' + t.lang.video.url + ' <small class="text-muted">' + t.lang.video.providers + '</small></label><input id="note-dialog-video-url-' + t.id + '" class="note-video-url note-input" type="text"/></div>',
                        o = ['<button type="button" href="#" class="note-btn note-btn-primary note-video-btn disabled" disabled>', t.lang.video.insert, "</button>"].join("");
                    return eo({
                        title: t.lang.video.insert,
                        fade: t.fade,
                        body: e,
                        footer: o
                    }).render()
                },
                no = function(t) {
                    var e = '<div class="note-form-group note-group-select-from-files"><label for="note-dialog-image-file-' + t.id + '" class="note-form-label">' + t.lang.image.selectFromFiles + '</label><input id="note-dialog-image-file-' + t.id + '" class="note-note-image-input note-input" type="file" name="files" accept="image/*" multiple="multiple"/>' + t.imageLimitation + '</div><div class="note-form-group"><label for="note-dialog-image-url-' + t.id + '" class="note-form-label">' + t.lang.image.url + '</label><input id="note-dialog-image-url-' + t.id + '" class="note-image-url note-input" type="text"/></div>',
                        o = ['<button href="#" type="button" class="note-btn note-btn-primary note-btn-large note-image-btn disabled" disabled>', t.lang.image.insert, "</button>"].join("");
                    return eo({
                        title: t.lang.image.insert,
                        fade: t.fade,
                        body: e,
                        footer: o
                    }).render()
                },
                io = function(t) {
                    var e = '<div class="note-form-group"><label for="note-dialog-link-txt-' + t.id + '" class="note-form-label">' + t.lang.link.textToDisplay + '</label><input id="note-dialog-link-txt-' + t.id + '" class="note-link-text note-input" type="text"/></div><div class="note-form-group"><label for="note-dialog-link-url-' + t.id + '" class="note-form-label">' + t.lang.link.url + '</label><input id="note-dialog-link-url-' + t.id + '" class="note-link-url note-input" type="text" value="http://"/></div>' + (t.disableLinkTarget ? "" : '<div class="checkbox"><label for="note-dialog-link-nw-' + t.id + '"><input id="note-dialog-link-nw-' + t.id + '" type="checkbox" checked> ' + t.lang.link.openInNewWindow + "</label></div>") + '<div class="checkbox"><label for="note-dialog-link-up-' + t.id + '"><input id="note-dialog-link-up-' + t.id + '" type="checkbox" checked> ' + t.lang.link.useProtocol + "</label></div>",
                        o = ['<button href="#" type="button" class="note-btn note-btn-primary note-link-btn disabled" disabled>', t.lang.link.insert, "</button>"].join("");
                    return eo({
                        className: "link-dialog",
                        title: t.lang.link.insert,
                        fade: t.fade,
                        body: e,
                        footer: o
                    }).render()
                },
                ro = Ne(['<div class="note-popover bottom">', '<div class="note-popover-arrow"></div>', '<div class="popover-content note-children-container"></div>', "</div>"].join(""), (function(t, e) {
                    var o = void 0 !== e.direction ? e.direction : "bottom";
                    t.addClass(o).hide(), e.hideArrow && t.find(".note-popover-arrow").hide()
                })),
                ao = Ne('<div class="checkbox"></div>', (function(t, e) {
                    t.html(["<label" + (e.id ? ' for="note-' + e.id + '"' : "") + ">", '<input role="checkbox" type="checkbox"' + (e.id ? ' id="note-' + e.id + '"' : ""), e.checked ? " checked" : "", ' aria-checked="' + (e.checked ? "true" : "false") + '"/>', e.text ? e.text : "", "</label>"].join(""))
                })),
                so = function(t, e) {
                    return t.match(/^</) ? t : "<" + (e = e || "i") + ' class="' + t + '"></' + e + ">"
                };
            e().summernote = e().extend(e().summernote, {
                ui_template: function(t) {
                    return {
                        editor: He,
                        toolbar: Be,
                        editingArea: ze,
                        codable: Me,
                        editable: Oe,
                        statusbar: je,
                        airEditor: Ue,
                        airEditable: We,
                        buttonGroup: Ke,
                        button: Ve,
                        dropdown: qe,
                        dropdownCheck: _e,
                        dropdownButton: Ye,
                        dropdownButtonContents: Ge,
                        dropdownCheckButton: Ze,
                        paragraphDropdownButton: Xe,
                        tableDropdownButton: Qe,
                        colorDropdownButton: to,
                        palette: Je,
                        dialog: eo,
                        videoDialog: oo,
                        imageDialog: no,
                        linkDialog: io,
                        popover: ro,
                        checkbox: ao,
                        icon: so,
                        options: t,
                        toggleBtn: function(t, e) {
                            t.toggleClass("disabled", !e), t.attr("disabled", !e)
                        },
                        toggleBtnActive: function(t, e) {
                            t.toggleClass("active", e)
                        },
                        check: function(t, e) {
                            t.find(".checked").removeClass("checked"), t.find('[data-value="' + e + '"]').addClass("checked")
                        },
                        onDialogShown: function(t, e) {
                            t.one("note.modal.show", e)
                        },
                        onDialogHidden: function(t, e) {
                            t.one("note.modal.hide", e)
                        },
                        showDialog: function(t) {
                            t.data("modal").show()
                        },
                        hideDialog: function(t) {
                            t.data("modal").hide()
                        },
                        getPopoverContent: function(t) {
                            return t.find(".note-popover-content")
                        },
                        getDialogBody: function(t) {
                            return t.find(".note-modal-body")
                        },
                        createLayout: function(e) {
                            var o = (t.airMode ? Ue([ze([Me(), We()])]) : "bottom" === t.toolbarPosition ? He([ze([Me(), Oe()]), Be(), je()]) : He([Be(), ze([Me(), Oe()]), je()])).render();
                            return o.insertAfter(e), {
                                note: e,
                                editor: o,
                                toolbar: o.find(".note-toolbar"),
                                editingArea: o.find(".note-editing-area"),
                                editable: o.find(".note-editable"),
                                codable: o.find(".note-codable"),
                                statusbar: o.find(".note-statusbar")
                            }
                        },
                        removeLayout: function(t, e) {
                            t.html(e.editable.html()), e.editor.remove(), t.off("summernote"), t.show()
                        }
                    }
                },
                interface: "lite"
            })
        })(), i
    })()
}));