package lsieun.net.http;

import lsieun.net.Resource;
import lsieun.net.http.bean.HttpRequest;
import lsieun.net.http.bean.HttpResource;
import lsieun.net.http.bean.HttpResponse;
import lsieun.net.http.bean.KeyValuePair;
import lsieun.net.http.utils.HttpHeaderUtils;
import lsieun.utils.CompressUtils;
import lsieun.utils.Const;
import lsieun.utils.DateUtils;
import lsieun.utils.FileUtils;

import java.io.IOException;
import java.util.*;
import java.util.logging.Level;

import static lsieun.utils.LogUtils.audit;
import static lsieun.utils.LogUtils.err;

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

            List<KeyValuePair> requestHeaders = request.headers;


            List<KeyValuePair> headers = new ArrayList<>();


            // 默认Headers
            Date now = new Date();
            HttpHeaderUtils.fillDefaultHeaders(headers, now);

            // 内容类型
            String content_type = resource.content_type;
            headers.add(new KeyValuePair("Content-Type", content_type));

            //缓存时间
            Date expireDate = null;
            if ("text/html".equalsIgnoreCase(content_type)) {
                expireDate = DateUtils.addMinutes(now, 30);
            } else {
                expireDate = DateUtils.addYears(now, 3);
            }
            HttpHeaderUtils.fillExpireHeaders(headers, now, expireDate);


            String connection = HttpHeaderUtils.getConnection(requestHeaders);
            headers.add(new KeyValuePair("Connection", connection));

            byte[] content_bytes = resource.content;
            byte[] payload_bytes = null;
            if (HttpHeaderUtils.containGZIP(requestHeaders)) {
                headers.add(new KeyValuePair("Content-Encoding", "gzip"));
                payload_bytes = CompressUtils.gzip_compress(content_bytes);
            } else if (HttpHeaderUtils.containDeflate(requestHeaders)) {
                headers.add(new KeyValuePair("Content-Encoding", "deflate"));
                payload_bytes = CompressUtils.deflate_compress(content_bytes);
            } else {
                payload_bytes = content_bytes;
            }

            if (payload_bytes != null) {
                headers.add(new KeyValuePair("Content-Length", String.valueOf(payload_bytes.length)));
            } else {
                headers.add(new KeyValuePair("Content-Length", "0"));
            }

            // 构造Response
            HttpResponse response = new HttpResponse(status_line, headers, payload_bytes);
            return response;

        } catch (Exception ex) {
            err.log(Level.SEVERE, "unexpected error: " + ex.getMessage(), ex);
        }
        return null;
    }
}
