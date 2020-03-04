package lsieun.net.http.bean;

import lsieun.net.Request;

import java.util.Formatter;

import static lsieun.utils.LogUtils.*;

public class HttpRequest extends Request {
    public final RequestLine request_line;
    public final HttpHeader header;
    public final byte[] payload;
    public final int length;

    public HttpRequest(final RequestLine request_line, final HttpHeader header, final byte[] payload, int length) {
        this.request_line = request_line;
        this.header = header;
        this.payload = payload;
        this.length = length;

        audit.fine(() -> {
            StringBuilder sb = new StringBuilder();
            Formatter fm = new Formatter(sb);
            fm.format("%n%s%n", request_line);
            for (KeyValuePair item : header.items) {
                fm.format("%s: %s%n", item.key, item.value);
            }
            return sb.toString();
        });
    }
}
