package lsieun.net.http.bean;

import lsieun.net.Response;
import lsieun.net.http.bean.KeyValuePair;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Formatter;
import java.util.List;

import static lsieun.utils.LogUtils.audit;

public class HttpResponse extends Response {
    public final String status_line;
    public final List<KeyValuePair> headers;
    public final byte[] payload;

    public HttpResponse(String status_line, List<KeyValuePair> headers, byte[] payload) {
        this.status_line = status_line;
        this.headers = headers;
        this.payload = payload;

        audit.fine(() -> {
            StringBuilder sb = new StringBuilder();
            Formatter fm = new Formatter(sb);
            fm.format("%n%s%n", status_line);
            for (KeyValuePair item : headers) {
                fm.format("%s: %s%n", item.key, item.value);
            }
            return sb.toString();
        });
    }

    public byte[] toBytes() {
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try {
            write(bao, status_line + "\r\n");
            for (KeyValuePair item : headers) {
                String line = String.format("%s: %s\r\n", item.key, item.value);
                write(bao, line);
            }
            write(bao, "\r\n");
            if (payload != null) {
                bao.write(payload);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bao.toByteArray();
    }

    public static void write(OutputStream out, String str) throws IOException {
        out.write(str.getBytes(StandardCharsets.US_ASCII));
    }

}
