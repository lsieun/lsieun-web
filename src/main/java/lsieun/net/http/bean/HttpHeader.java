package lsieun.net.http.bean;

import lsieun.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

// TODO: 使用HttpHeader类
public class HttpHeader {
    public final List<KeyValuePair> items = new ArrayList<>();

    public List<KeyValuePair> getItems() {
        return items;
    }

    public void add(final String field, final String value) {
        if (StringUtils.isBlank(field)) return;
        if (StringUtils.isBlank(value)) return;
        items.add(new KeyValuePair(field, value));
    }

    public String getHost() {
        return getFieldValue("Host", null);
    }

    public String getAccept() {
        return getFieldValue("Accept", null);
    }

    public String getUserAgent() {
        return getFieldValue("User-Agent", null);
    }

    public int getContentLength() {
        String value = getFieldValue("Content-Length", "0");
        return Integer.parseInt(value);
    }

    public String getConnection() {
        return getFieldValue("Connection", "close");
    }

    public String getFieldValue(final String field, final String defaultValue) {
        if (StringUtils.isBlank(field)) return defaultValue;
        for (KeyValuePair item : items) {
            if (field.equalsIgnoreCase(item.key)) {
                return item.value;
            }
        }
        return defaultValue;
    }
}
