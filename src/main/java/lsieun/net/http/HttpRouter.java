package lsieun.net.http;

import lsieun.net.http.bean.HttpHeader;
import lsieun.net.http.bean.HttpRequest;
import lsieun.net.http.bean.HttpResource;
import lsieun.net.http.handler.JsonHandler;
import lsieun.net.http.handler.sub.*;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;

import static lsieun.utils.LogUtils.audit;

public class HttpRouter {
    /**
     * 所有的URI都注册到uri_set当中
     * FIXME:1,是不是应该考虑pattern模式，例如/json/*
     * FIXME：2,是不是应该考虑GET和PUT和POST的访问权限呢
     */
    private static final Set<String> uri_set = new HashSet<>();

    private static final Map<String, HttpResource> cacheMap = new HashMap<>();
    private static final PageListHandler page_list_handler = new PageListHandler();
    private static final CSSHandler css_handler = new CSSHandler();
    private static final JSHandler js_handler = new JSHandler();
    private static final ImageHandler image_handler = new ImageHandler();
    private static final FontHandler font_handler = new FontHandler();
    private static final ContentHandler content_handler = new ContentHandler();
    private static final JsonHandler json_handler = new JsonHandler();
    private static final HTMLHandler html_handler = new HTMLHandler();


    static {
//        HttpResource page_404_resource = fileHandler.get404PageResource();
//        HttpResource image_404_resource = fileHandler.get404ImageResource();
//
//        cacheMap.put(Const.PAGE_404_PATH, page_404_resource);
//        cacheMap.put(Const.IMAGE_404_PATH, image_404_resource);
    }

    public static void register(final String uri) {
        if (uri_set.contains(uri)) {
            audit.log(Level.WARNING, "already contains " + uri);
        }
        else {
            uri_set.add(uri);
        }
    }

    public static HttpResource getResource(final String uri_path, final HttpHeader header) {
        if (
            // TODO: 这里的路径匹配，要不断添加才对
                "/".equals(uri_path) ||
                        "/excerpt/".equals(uri_path) || "/excerpt".equals(uri_path) ||
                        "/life/".equals(uri_path) || "/life".equals(uri_path) ||
                        "/code/".equals(uri_path) || "/code".equals(uri_path) ||
                        "/front/".equals(uri_path) || "/front".equals(uri_path) ||
                        "/os/".equals(uri_path) || "/os".equals(uri_path) ||
                        "/tools/".equals(uri_path) || "/tools".equals(uri_path) ||
                        "/books/".equals(uri_path) || "/books".equals(uri_path) ||
                        "/english/".equals(uri_path) || "/english".equals(uri_path) ||
                        "/about/".equals(uri_path) || "/about".equals(uri_path)
        ) {
            return page_list_handler.getResource(uri_path, header);
        }
        else if ("/favicon.ico".equals(uri_path)) {
            return image_handler.getResource("/images/favicon.ico", header);
        }
        else if ("/images/404.png".equals(uri_path)) {
            return image_handler.getResource("/images/image_not_found.png", header);
        }
        else if (uri_path.startsWith("/archive/")) {
            return content_handler.getResource(uri_path, header);
        }
        else if (uri_path.startsWith("/css/")) {
            return css_handler.getResource(uri_path, header);
        }
        else if (uri_path.startsWith("/js/")) {
            return js_handler.getResource(uri_path, header);
        }
        else if (uri_path.startsWith("/fonts/")) {
            return font_handler.getResource(uri_path, header);
        }
        else if (uri_path.startsWith("/images/")) {
            return image_handler.getResource(uri_path, header);
        }
        else if (uri_path.startsWith("/doc/")) {
            return html_handler.getResource(uri_path, header);
        }
        else if (uri_path.startsWith("/books/")) {
            return html_handler.getResource(uri_path, header);
        }
        else if (uri_path.startsWith("/lab/")) {
            return html_handler.getResource(uri_path, header);
        }
        else if (uri_path.startsWith("/robots.txt")) {
            return html_handler.getResource(uri_path, header);
        }
        else if (uri_path.startsWith("/sitemap.xml")) {
            return html_handler.getResource(uri_path, header);
        }
        else if (uri_path.startsWith("/json/")) {
            return json_handler.getResource(uri_path, header);
        }
        // TODO: 还需要处理Json数据
        else {
            // TODO: 这里可能将来不太对
            return page_list_handler.get404Resource();
        }
    }

    public static String getURIPath(HttpRequest request) {
        try {
            URL url = new URL(HttpConst.FAKE_DOMAIN_NAME + request.request_line.path);
            URI uri = url.toURI();
            return uri.getPath();
        } catch (MalformedURLException | URISyntaxException ex) {
            audit.warning("Unexpected path: " + request.request_line.path + ", " + ex);
        }
        return request.request_line.path;
    }

}
