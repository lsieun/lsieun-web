function trim(str) {
    return str.replace(/^\s+|\s+$/g, "");
}

function html_escape(currentChar) {
    if (currentChar === '<') {
        return "&lt;"
    } else if (currentChar === '>') {
        return "&gt;"
    } else {
        return "escape_error_error";
    }
}

function starts_with(sub_str, src_text) {
    return src_text.substr(0, sub_str.length) === sub_str;
}

const java_keywords = ["abstract", "assert", "boolean", "break", "byte", "case", "catch", "char", "class", "const", "continue", "default", "do", "double", "else", "enum", "extends", "final", "finally", "float", "for", "goto", "if", "implements", "import", "instanceof", "int", "interface", "long", "native", "new", "package", "private", "protected", "public", "return", "short", "static", "strictfp", "super", "switch", "synchronized", "this", "throw", "throws", "transient", "try", "void", "volatile", "while", "true", "false", "null"];
const javascript_keywords = [
    "break", "case", "catch", "continue", "debugger", "default", "delete", "do",
    "else", "finally", "for", "function", "if", "in", "instanceof", "let", "new",
    "return", "switch", "this", "throw", "try", "typeof", "var", "void", "while", "with"
];

const keyword_start_tag = '<span style="font-weight:bold;color:#7B0052;">';
const keyword_end_tag = '</span>';

function is_keyword(keyword_array, token) {
    return keyword_array.indexOf(token) !== -1;
}

function process_keyword(token) {
    return keyword_start_tag + token + keyword_end_tag;
}

const flower_braces = ["}", "{"];
const flower_brace_start_tag = '<span style="font-weight:bold;color:#D3171B">';
const flower_brace_end_tag = '</span>';

function is_flower_braces(token) {
    return flower_braces.indexOf(token) !== -1;
}

function process_flower_braces(token) {
    return flower_brace_start_tag + token + flower_brace_end_tag;
}

const string_start_tag = '<span style="color:#2A00FF">';
const string_end_tag = '</span>';

function process_double_quotes(token) {
    return string_start_tag + token + string_end_tag;
}

function get_double_quotes_stop(src_text, start) {
    for (let i = start; i < src_text.length; i++) {
        let ch = src_text.charAt(i);
        if (ch === '"') {
            return i;
        }
    }
    return -1;
}

function get_double_quotes(src_text, start, stop) {
    let str = '';

    // read everything till the next "
    // be CAREFUL about the escape sequence
    for (let i = start; i <= stop; i++) {
        let ch = src_text.charAt(i);

        if (ch === '<' || ch === '>') {
            str = str + html_escape(ch);
        } else if (ch === '\\') {
            // escape sequenced \\ or \" found , don't end parse here
            switch (src_text.charAt(i + 1)) {
                case '"':
                    str = str + "\\\"";
                    i++;
                    break;
                case '\\':
                    str = str + "\\\\";
                    i++;
                    break;
                default:
                    str = str + ch;
                    break;

            }//end of switch
        } else {
            str = str + ch;
        }
    }

    return str;
}

function process_single_quotes(token) {
    return string_start_tag + token + string_end_tag;
}

function get_single_quotes_stop(src_text, start) {
    for (let i = start; i < src_text.length; i++) {
        let ch = src_text.charAt(i);
        if (ch === "'") {
            return i;
        }
    }
    return -1;
}

function get_single_quotes(src_text, start, stop) {
    let str = "";

    // read everything till the next
    // be CAREFUL about the escape sequence
    for (let i = start; i <= stop; i++) {
        let ch = src_text.charAt(i);
        if (ch === '<' || ch === '>') {
            str = str + html_escape(ch);
        } else if (ch === '\\') {
            // escape sequenced \\ or \' found , don't end parse here
            switch (src_text.charAt(i + 1)) {
                case "'":
                    str = str + "\\\'";
                    i++;
                    break;
                case "\\":
                    str = str + "\\\\";
                    i++;
                    break;
                default:
                    str = str + ch;
                    break;
            }
        } else {
            str = str + ch;
        }
    }

    return str;
}

const comment_start_tag = '<span style="color:#3F7F5F">';
const comment_end_tag = '</span>';

const java_doc_start_tag = '<span style="color:#3F5FBF">';
const java_doc_end_tag = '</span>';

function get_multiline_comment_stop(src_text, start) {
    for (let i = start; i < src_text.length; i++) {
        let ch = src_text.charAt(i);
        if (ch === '*' && src_text.charAt(i + 1) === '/') {
            return i + 1;
        }
    }
    return -1;
}

function get_multiline_comment(src_text, start, stop) {
    let multiline_comment = "";
    /*The current index points at /, we will increment it by 1
     because stringLiteral has already been filled with 2 chars
     Why increment by 1?
     Because we start the loop below by incrementing the index
     */

    //read everything until */ is found
    for (let i = start; i <= stop; i++) {
        let ch = src_text.charAt(i);
        if (ch === '<' || ch === '>') {
            multiline_comment = multiline_comment + html_escape(ch);
        } else {
            multiline_comment = multiline_comment + ch;
        }
    }

    return multiline_comment;
}

function process_multiline_comment(token) {
    if (starts_with("/** ", token) || starts_with("/**\t", token) || starts_with("/**\n", token)) {
        return java_doc_start_tag + token + java_doc_end_tag;
    } else {
        return comment_start_tag + token + comment_end_tag;
    }
}

function get_single_line_comment_stop(src_text, start) {
    for (let i = start; i < src_text.length; i++) {
        let ch = src_text.charAt(i);
        if (ch === '\n') {
            return i;
        }
    }
    return -1;
}

function get_single_line_comment(src_text, start, stop) {
    let single_line_comment = "";
    /*The current index points at /, we will increment it by 1
     because stringLiteral has already been filled with 2 chars
     Why increment by 1?
     Because we start the loop below by incrementing the index
     */

    //read everything until newline --&gt; \n is found
    for (let i = start; i < stop; i++) {
        let ch = src_text.charAt(i);
        if (ch === '<' || ch === '>') {
            single_line_comment += html_escape(ch);
        } else {
            single_line_comment += ch;
        }
    }

    return single_line_comment;
}

function process_single_line_comment(token) {
    return comment_start_tag + token + comment_end_tag + "\n";
}

const annotation_start_tag = '<span style="color:#3300ff">';
const annotation_end_tag = '</span>';

function get_annotation_stop(src_text, start) {
    for (let i = start; i < src_text.length; i++) {
        let ch = src_text.charAt(i + 1);
        if (ch === '(' || ch === '\n' || ch === ' ') {
            return i;
        }
    }
    return -1;
}

function get_annotation(src_text, start, stop) {
    let annotation = "";
    for (let i = start; i <= stop; i++) {
        let ch = src_text.charAt(i);
        annotation += ch;
    }
    return annotation;
}

function process_annotation(token) {
    return annotation_start_tag + token + annotation_end_tag;
}

const separators = [
    '+', '-', '*', '/', '=', // 加减乘除
    '{', '}', '[', ']', '(', ')', '<', '>', // 括号
    '`', '~', '!', '@', '#', '$', '%', '^', '&', '_', // 数字键上的符号
    '"', "'", ',', '.', ':', ';', '?', // 标点符号
    ' ', '\t', '\n', '\r' // 空白字符
];

const w3_panel_start_tag = '<div class="w3-panel w3-card w3-light-grey">';
const w3_panel_end_tag = '</div>';

const title_start_tag = '<p onclick="$(this).next().toggle()">';
const title_end_tag = '</p>';

const w3_code_start_show_tag = '<div class="w3-code notranslate" style="display: block;">';
const w3_code_start_hide_tag = '<div class="w3-code notranslate" style="display: none;">';
const w3_code_end_tag = '</div>';

const pre_start_tag = '<pre style="text-align: left; line-height: 18px; font-size: 13px; font-family:\'Courier New\', Courier, monospace; overflow: auto;">';
const pre_end_tag = '</pre>';

function toCode(src_text, title, keywords, display) {
    let result = "";
    let token = "";

    result += w3_panel_start_tag;
    result += title_start_tag + title + title_end_tag;
    if (display) {
        result += w3_code_start_show_tag;
    } else {
        result += w3_code_start_hide_tag;
    }
    result += pre_start_tag;

    for (let i = 0; i < src_text.length; i++) {
        let ch = src_text.charAt(i);

        if (separators.indexOf(ch) !== -1) {
            if (is_keyword(keywords, token)) {
                result += process_keyword(token);
            } else {
                result += token;
            }

            token = "";
        }

        if (ch === ';' || ch === '\t' || ch === ' ' || ch === '\n' ||
            ch === '(' || ch === ')' ||
            ch === '[' || ch === ']') {
            result += ch;
        } else if (ch === "+" || ch === '-' || ch === '*' || ch === '=') {
            result += ch;
        } else if (ch === '}' || ch === '{') {
            result += process_flower_braces(ch);
        } else if (ch === '"') {
            let stop = get_double_quotes_stop(src_text, i + 1);
            let str = get_double_quotes(src_text, i, stop);
            result += process_double_quotes(str);

            i = stop;
        } else if (ch === "'") {
            let stop = get_single_quotes_stop(src_text, i + 1);
            let str = get_single_quotes(src_text, i, stop);
            result += process_single_quotes(str);

            i = stop;
        } else if (ch === '/' && src_text.charAt(i + 1) === '*') {
            let stop = get_multiline_comment_stop(src_text, i + 1);
            let str = get_multiline_comment(src_text, i, stop);
            result += process_multiline_comment(str);

            i = stop;
        } else if (ch === '/' && src_text.charAt(i + 1) === '/') {
            let stop = get_single_line_comment_stop(src_text, i + 1);
            let str = get_single_line_comment(src_text, i, stop);
            result += process_single_line_comment(str);

            i = stop;
        } else if (ch === '<' || ch === '>') {
            result += html_escape(ch);
        } else if (ch === '@') {
            let stop = get_annotation_stop(src_text, i + 1);
            let str = get_annotation(src_text, i, stop);
            result += process_annotation(str);

            i = stop;
        } else {
            token += ch;
        }
    }

    result += pre_end_tag;
    result += w3_code_end_tag;
    result += w3_panel_end_tag

    return result;
}

(function ($) {
    $.fn.autoCode = function () {
        return this.each(function () {
            let title = $(this).attr("title");
            if (title == undefined || title == null || title === "") {
                title = "Source Code";
            }

            let display = true;
            let hidden = $(this).attr("hidden");
            if (hidden !== undefined && hidden == "hidden") {
                display = false;
            }
            console.log("display: " + display);

            let src_text = $(this).text();
            let content = "";
            if ($(this).hasClass("javacode")) {
                content = toCode(src_text, title, java_keywords, display);
            } else if ($(this).hasClass("jscode")) {
                content = toCode(src_text, title, javascript_keywords, display);
            } else {
                content = toCode(src_text, title, [], display);
            }


            let $div = $("<div></div>")
            $div.html(content);
            $div.insertAfter(this);
        });
    };
}(jQuery));