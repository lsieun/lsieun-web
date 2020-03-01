package lsieun.net.http;

import lsieun.net.Request;
import lsieun.net.http.bean.KeyValuePair;
import lsieun.net.http.bean.RequestLine;

import java.util.List;

public class HttpRequest extends Request {
    public final RequestLine request_line;
    public final List<KeyValuePair> headers;
    public final byte[] payload;
    public final int length;

    public HttpRequest(RequestLine request_line, List<KeyValuePair> headers, byte[] payload, int length) {
        this.request_line = request_line;
        this.headers = headers;
        this.payload = payload;
        this.length = length;
    }
}
