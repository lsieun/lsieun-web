/*! ORA_DOCS.JS - BUILD: 11th December 2017 */
var ora_local = "ora_code_docs.js";
var ora_global = "ora_code.js";
var ora_path = "/us/assets/metrics/";
var isTest = (location.host.indexOf("stage") != -1 || location.host.indexOf("us.oracle.com") != -1 || location.host.indexOf("-content") != -1 || location.host.indexOf("localhost") != -1 || location.host.indexOf("127.0.0.1") != -1 || location.host.indexOf("docs-uat") != -1 || location.host.indexOf("-dev") != -1);
var ora_root = (isTest == true) ? "://www-portal-stage.oracle.com" : "://www.oracleimg.com";
var host_type = (window.location.protocol.toLowerCase().indexOf("https") != -1) ? "https" : "http";
var scFiles = [host_type + ora_root + ora_path + ora_local, host_type + ora_root + ora_path + ora_global];
scFiles.forEach(function (src) {
    var scScript = document.createElement("script");
    scScript.src = src;
    scScript.async = false;
    document.body.appendChild(scScript);
});