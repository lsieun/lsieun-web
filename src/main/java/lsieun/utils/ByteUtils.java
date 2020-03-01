package lsieun.utils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class ByteUtils {
    public static String toStr(byte[] bytes) {
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public static String toStr(byte[] bytes, String charset) {
        return new String(bytes, Charset.forName(charset));
    }

    public static String toStr(byte[] bytes, Charset charset) {
        return new String(bytes, charset);
    }
}
