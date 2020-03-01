package lsieun.net.http.bean;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class HttpBean {
    public final String method;
    public final String path;
    public final String version;
    public final List<KeyValuePair> headers = new ArrayList<>();

    public HttpBean(HttpRawBean rawBean) {
        String first_line = new String(rawBean.first_line_bytes, StandardCharsets.US_ASCII).trim();
        String[] first_line_array = first_line.split("\\s", 3);
        this.method = first_line_array[0];
        this.path = first_line_array[1];
        this.version = first_line_array[2];
    }
}
