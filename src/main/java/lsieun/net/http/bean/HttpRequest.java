package lsieun.net.http.bean;

import lsieun.net.Request;
import lsieun.net.http.bean.KeyValuePair;
import lsieun.net.http.bean.RequestLine;

import java.util.Formatter;
import java.util.List;

import static lsieun.utils.LogUtils.*;

public class HttpRequest extends Request {
    public final RequestLine request_line;
    public final List<KeyValuePair> headers;
    public final byte[] payload;
    public final int length;

    public HttpRequest(final RequestLine request_line, final List<KeyValuePair> headers, final byte[] payload, int length) {
        this.request_line = request_line;
        this.headers = headers;
        this.payload = payload;
        this.length = length;

        audit.fine(() -> {
            StringBuilder sb = new StringBuilder();
            Formatter fm = new Formatter(sb);
            fm.format("%n%s%n", request_line);
            for (KeyValuePair item : headers) {
                fm.format("%s: %s%n", item.key, item.value);
            }
            return sb.toString();
        });
    }

    public String getHost() {
        String host = "unknown host";
        for (KeyValuePair item : headers) {
            if ("host".equalsIgnoreCase(item.key)) {
                host = item.value;
                break;
            }
        }
        return host;
    }
}
