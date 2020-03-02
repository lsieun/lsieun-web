package lsieun.net;

import lsieun.utils.ByteDashboard;
import lsieun.utils.ByteUtils;
import lsieun.utils.FileUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Resource {
    private static final int ETAG_BYTES = 4;

    public final String path;
    public final String etag;
    public final long lastModified;
    public final byte[] content;

    public Resource(String path, long lastModified, byte[] content) {
        this.path = path;
        this.lastModified = lastModified;
        this.content = content;
        this.etag = calculateEtag(content);
    }

    @Override
    public String toString() {
        return "Resource {" +
                "path='" + path + "'" +
                ", etag='" + etag + "'" +
                ", lastModified=" + lastModified +
                '}';
    }

    public static String calculateEtag(byte[] bytes) {
        byte[] result_bytes = new byte[ETAG_BYTES];
        for (int i = 0; i < bytes.length; i++) {
            byte b = bytes[i];
            int remainder = i % ETAG_BYTES;
            result_bytes[remainder] = (byte) (result_bytes[remainder] ^ b);
        }
        return ByteUtils.toHex(result_bytes);
    }

    public static byte[] toBytes(Resource r) {
        byte[] path_bytes = r.path.getBytes(StandardCharsets.UTF_8);
        int path_length = path_bytes.length;
        long lastModified = r.lastModified;
        byte[] content_bytes = r.content;
        int content_length = content_bytes.length;

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            out.write(ByteUtils.fromInt(path_length));
            out.write(path_bytes);
            out.write(ByteUtils.fromLong(lastModified));
            out.write(ByteUtils.fromInt(content_length));
            out.write(content_bytes);
            return out.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Resource fromBytes(byte[] bytes) {
        ByteDashboard dashboard = new ByteDashboard(bytes);
        int path_length = dashboard.readInt();
        String path = ByteUtils.toStr(dashboard.nextN(path_length));
        long lastModified = dashboard.readLong();
        int content_length = dashboard.readInt();
        byte[] content_bytes = dashboard.nextN(content_length);
        return new Resource(path, lastModified, content_bytes);
    }

    public static void main(String[] args) throws IOException {
        String filepath = Resource.class.getResource(Resource.class.getSimpleName() + ".class").getPath();
        byte[] bytes = FileUtils.readFile(filepath);
        String etag = Resource.calculateEtag(bytes);
        System.out.println(etag);

        File f = new File(filepath);
        long lastModified = f.lastModified();
        Resource r1 = new Resource(filepath, lastModified, bytes);

        byte[] data_bytes = Resource.toBytes(r1);
        Resource r2 = Resource.fromBytes(data_bytes);
        System.out.println(r1);
        System.out.println(r2);
        System.out.println(data_bytes.length);
        System.out.println(ByteUtils.toHex(data_bytes));
    }
}
