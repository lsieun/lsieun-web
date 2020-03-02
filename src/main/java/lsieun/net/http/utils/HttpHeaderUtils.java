package lsieun.net.http.utils;

import lsieun.net.http.bean.KeyValuePair;
import lsieun.utils.DateUtils;

import java.util.Date;
import java.util.List;

public class HttpHeaderUtils {

    public static void fillDefaultHeaders(final List<KeyValuePair> headers, final Date date) {
        headers.add(new KeyValuePair("Date", DateUtils.getGMTFormat(date)));
        headers.add(new KeyValuePair("Server", "Chaos Server"));
    }

    public static void fillExpireHeaders(final List<KeyValuePair> headers, final Date date, final Date expireDate) {
        long seconds = (expireDate.getTime() - date.getTime()) / 1000;
        String max_age = String.format("max-age=%s", seconds);
        headers.add(new KeyValuePair("Cache-Control", max_age));
        headers.add(new KeyValuePair("Expires", DateUtils.getGMTFormat(expireDate)));
    }

    public static int getContentLength(List<KeyValuePair> headers) {
        for (KeyValuePair item : headers) {
            if ("Content-Length".equalsIgnoreCase(item.key)) {
                return Integer.parseInt(item.value);
            }
        }
        return 0;
    }

    public static String getConnection(List<KeyValuePair> headers) {
        for (KeyValuePair item : headers) {
            if ("Connection".equalsIgnoreCase(item.key)) {
                return item.value;
            }
        }
        return "close";
    }

    public static boolean containGZIP(final List<KeyValuePair> headers) {
        return contain_encoding(headers, "gzip");
    }

    public static boolean containDeflate(final List<KeyValuePair> headers) {
        return contain_encoding(headers, "deflate");
    }

    public static boolean contain_encoding(final List<KeyValuePair> headers, final String encoding) {
        String accept_encoding = null;
        for (KeyValuePair item : headers) {
            if ("Accept-Encoding".equalsIgnoreCase(item.key)) {
                accept_encoding = item.value;
                break;
            }
        }
        if (accept_encoding == null) return false;

        String[] array = accept_encoding.split(",");
        for (String item : array) {
            if (encoding.equalsIgnoreCase(item.trim())) {
                return true;
            }
        }
        return false;
    }

}
