package lsieun.net.http.bean;

import java.util.List;

// TODO: 使用HttpHeader类
public class HttpHeader {
    public final List<KeyValuePair> items;

    public HttpHeader(List<KeyValuePair> items) {
        this.items = items;
    }
}
