package lsieun.net.http.utils;

import java.util.HashMap;
import java.util.Map;

public class MIMEUtils {
    private static Map<String, String> mime_map = new HashMap<>();

    static {
        mime_map.put(".html", "text/html");
        mime_map.put(".xml", "text/xml");
        mime_map.put(".txt", "text/plain");
        mime_map.put(".css", "text/css");
        mime_map.put(".js", "application/javascript");
        mime_map.put(".ico", "image/x-icon");
        mime_map.put(".gif", "image/gif");
        mime_map.put(".jpg", "image/jpeg");
        mime_map.put(".png", "image/png");
        mime_map.put(".svg", "image/svg+xml");
        mime_map.put(".woff2", "font/woff2");
        mime_map.put(".woff", "font/woff");
        mime_map.put(".mp4", "video/mp4");
//        mime_map.put("", "");
    }

    public static String getContentType(String ext) {
        String contentType = mime_map.get(ext);
        if (contentType == null) return "application/octet-stream";
        return contentType;
    }
}
