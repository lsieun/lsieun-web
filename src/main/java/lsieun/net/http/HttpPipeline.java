package lsieun.net.http;

import lsieun.net.http.bean.*;
import lsieun.net.http.utils.HttpHeaderUtils;
import lsieun.utils.CompressUtils;

import java.util.logging.Level;

import static lsieun.utils.LogUtils.audit;

public class HttpPipeline {

    // block blacklist

    // 认证authenticate

    // 授权authorize

    // request method, path

    // request header

    // request body

    // response status code

    // response header
    public void response_header() {
        HttpHeader header = new HttpHeader();

    }

    public static HttpResponse doWork(HttpRequest request) {
        try {
            String http_version = request.request_line.version;
            // TODO: 这里可能还有QueryString，例如abc.html?name=tom&password=123
            String uri_path = HttpRouter.getURIPath(request).toLowerCase();

            String status_line = String.format("%s 200 OK", http_version);
            HttpHeader header = new HttpHeader();
            HttpResource resource = HttpRouter.getResource(uri_path, header);

            String connection = header.getConnection();
            header.add("Connection", connection != null ? connection : "close");

            byte[] content_bytes = resource.content;
            byte[] payload_bytes = null;
            if (HttpHeaderUtils.containGZIP(header.items)) {
                header.add("Content-Encoding", "gzip");
                payload_bytes = CompressUtils.gzip_compress(content_bytes);
            } else if (HttpHeaderUtils.containDeflate(header.items)) {
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

    public void after_resource() {
        HttpHeader header = new HttpHeader();
        HttpResource resource = null;


    }

    // response content

}
