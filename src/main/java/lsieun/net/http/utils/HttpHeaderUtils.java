package lsieun.net.http.utils;

import lsieun.net.http.bean.HttpHeader;
import lsieun.net.http.bean.KeyValuePair;
import lsieun.utils.DateUtils;

import java.util.Date;
import java.util.List;

public class HttpHeaderUtils {

    public static void fillDefaultHeaders(final HttpHeader header, final Date date) {
        header.add("Date", DateUtils.getGMTFormat(date));
        header.add("Server", "Chaos Server");
    }

    public static void fillExpireHeaders(final HttpHeader header, final Date date, final Date expireDate) {
        long seconds = (expireDate.getTime() - date.getTime()) / 1000;
        String max_age = String.format("max-age=%s", seconds);
        header.add("Cache-Control", max_age);
        header.add("Expires", DateUtils.getGMTFormat(expireDate));
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
