package lsieun.net.http;

import lsieun.net.http.bean.*;
import lsieun.net.http.utils.HttpHeaderUtils;
import lsieun.utils.CompressUtils;
import lsieun.utils.Const;
import lsieun.utils.DateUtils;

import java.util.*;
import java.util.logging.Level;

import static lsieun.utils.LogUtils.audit;

public class HttpRouter {

    private static final Map<String, HttpResource> cacheMap = new HashMap<>();
    private static final FileHandler fileHandler = new FileHandler();

    static {
        HttpResource page_404_resource = fileHandler.get404PageResource();
        HttpResource image_404_resource = fileHandler.get404ImageResource();

        cacheMap.put(Const.PAGE_404_PATH, page_404_resource);
        cacheMap.put(Const.IMAGE_404_PATH, image_404_resource);
    }

    public static HttpResponse doWork(HttpRequest request) {
        try {
            String http_version = request.request_line.version;
            // TODO: 这里可能还有QueryString，例如abc.html?name=tom&password=123
            String uri_path = HttpHandler.getURIPath(request).toLowerCase();

            String status_line = String.format("%s 200 OK", http_version);
            HttpResource resource = null;

            if (cacheMap.containsKey(uri_path)) {
                resource = cacheMap.get(uri_path);
                audit.fine(() -> "Hit " + uri_path + " from cache");
            }

            if (resource == null) {
                resource = fileHandler.getResource(uri_path);

                if (
                        resource != null &&
                        resource.content_type != null && (
                                resource.content_type.startsWith("text/") ||
                                        resource.content_type.startsWith("image/") ||
                                        resource.content_type.startsWith("font/") ||
                                        resource.content_type.equals("application/javascript")
                        )
                ) {
                    cacheMap.put(uri_path, resource);
                }
            }

            if (resource == null) {
                status_line = String.format("%s 404 Not Found", http_version);
                if (uri_path.endsWith(".png") ||
                        uri_path.endsWith(".jpg") ||
                        uri_path.endsWith(".gif")
                ) {
                    resource = cacheMap.get(Const.IMAGE_404_PATH);
                } else {
                    resource = cacheMap.get(Const.PAGE_404_PATH);
                }
            }

            List<KeyValuePair> requestHeaders = request.header.items;

            HttpHeader header = new HttpHeader();
            List<KeyValuePair> header_items = header.items;


            // 默认Headers
            Date now = new Date();
            HttpHeaderUtils.fillDefaultHeaders(header_items, now);

            long lastModified = resource.lastModified;
            if (lastModified > 0) {
                Date date = new Date(lastModified);
                header.add("Last-Modified", DateUtils.getGMTFormat(date));
            }

            // 内容类型
            String content_type = resource.content_type;
            header.add("Content-Type", content_type);

            //缓存时间
            Date expireDate = null;
            if ("text/html".equalsIgnoreCase(content_type)) {
                expireDate = DateUtils.addMinutes(now, 30);
            } else {
                expireDate = DateUtils.addYears(now, 3);
            }
            HttpHeaderUtils.fillExpireHeaders(header_items, now, expireDate);


            String connection = header.getConnection();
            header.add("Connection", connection);

            byte[] content_bytes = resource.content;
            byte[] payload_bytes = null;
            if (HttpHeaderUtils.containGZIP(requestHeaders)) {
                header.add("Content-Encoding", "gzip");
                payload_bytes = CompressUtils.gzip_compress(content_bytes);
            } else if (HttpHeaderUtils.containDeflate(requestHeaders)) {
                header.add("Content-Encoding", "deflate");
                payload_bytes = CompressUtils.deflate_compress(content_bytes);
            } else {
                payload_bytes = content_bytes;
            }

            if (payload_bytes != null) {
                header.add("Content-Length", String.valueOf(payload_bytes.length));
            } else {
                header.add("Content-Length", "0");
            }

            // 构造Response
            HttpResponse response = new HttpResponse(status_line, header, payload_bytes);
            return response;

        } catch (Exception ex) {
            audit.log(Level.SEVERE, "unexpected error: " + ex.getMessage(), ex);
        }
        return null;
    }
}
