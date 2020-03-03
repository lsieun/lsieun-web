package lsieun.net.http.bean;

import lsieun.net.Resource;
import lsieun.utils.ByteDashboard;
import lsieun.utils.ByteUtils;
import lsieun.utils.FileUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Formatter;

public class HttpResource extends Resource {
    public final String path;
    public final String content_type;
    public final long lastModified;
    public final byte[] content;
    public final String etag;

    public HttpResource(String path, String content_type, long lastModified, byte[] content) {
        this.path = path;
        this.content_type = content_type;
        this.lastModified = lastModified;
        this.content = content;
        this.etag = calculateEtag(content);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Formatter fm = new Formatter(sb);
        fm.format("%s {", HttpResource.class.getSimpleName());
        fm.format("%s='%s', ", "path", path);
        fm.format("%s='%s', ", "content_type", content_type);
        fm.format("%s='%s', ", "lastModified", lastModified);
        fm.format("%s='%s'}", "etag", etag);
        return sb.toString();
    }

    public static byte[] toBytes(HttpResource r) {
        byte[] path_bytes = r.path.getBytes(StandardCharsets.UTF_8);
        int path_length = path_bytes.length;
        byte[] content_type_bytes = r.content_type.getBytes(StandardCharsets.UTF_8);
        int content_type_length = content_type_bytes.length;
        long lastModified = r.lastModified;
        byte[] content_bytes = r.content;
        int content_length = content_bytes.length;

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            // path
            out.write(ByteUtils.fromInt(path_length));
            out.write(path_bytes);

            // content_type
            out.write(ByteUtils.fromInt(content_type_length));
            out.write(content_type_bytes);

            // lastModified
            out.write(ByteUtils.fromLong(lastModified));

            // content
            out.write(ByteUtils.fromInt(content_length));
            out.write(content_bytes);
            return out.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static HttpResource fromBytes(byte[] bytes) {
        ByteDashboard dashboard = new ByteDashboard(bytes);

        // path
        int path_length = dashboard.readInt();
        String path = ByteUtils.toStr(dashboard.nextN(path_length));

        int content_type_length = dashboard.readInt();
        String content_type = ByteUtils.toStr(dashboard.nextN(content_type_length));


        // lastModified
        long lastModified = dashboard.readLong();

        // content
        int content_length = dashboard.readInt();
        byte[] content_bytes = dashboard.nextN(content_length);
        return new HttpResource(path, content_type, lastModified, content_bytes);
    }

    public static void main(String[] args) throws IOException {
        String filepath = HttpResource.class.getResource(HttpResource.class.getSimpleName() + ".class").getPath();
        String content_type = "application/octet-stream";
        byte[] bytes = FileUtils.readFile(filepath);
        String etag = HttpResource.calculateEtag(bytes);
        System.out.println(etag);

        File f = new File(filepath);
        long lastModified = f.lastModified();
        HttpResource r1 = new HttpResource(filepath, content_type, lastModified, bytes);

        byte[] data_bytes = HttpResource.toBytes(r1);
        HttpResource r2 = HttpResource.fromBytes(data_bytes);
        System.out.println(r1);
        System.out.println(r2);
        System.out.println(data_bytes.length);
        System.out.println(ByteUtils.toHex(data_bytes));
    }
}
