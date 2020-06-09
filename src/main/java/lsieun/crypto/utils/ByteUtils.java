package lsieun.crypto.utils;

import java.util.Formatter;

public class ByteUtils {
    public static byte[] xor(byte[] bytes1, byte[] bytes2, int num) {
        byte[] result_bytes = new byte[num];
        for (int i = 0; i < num; i++) {
            result_bytes[i] = (byte) (bytes1[i] ^ bytes2[i]);
        }
        return result_bytes;
    }

    public static byte[] concatenate(byte[] bytes1, byte[] bytes2) {
        int len1 = bytes1.length;
        int len2 = bytes2.length;

        byte[] result_bytes = new byte[len1 + len2];

        System.arraycopy(bytes1, 0, result_bytes, 0, len1);
        System.arraycopy(bytes2, 0, result_bytes, len1, len2);

        return result_bytes;
    }

    public static String toHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        Formatter fm = new Formatter(sb);
        for (byte b : bytes) {
            fm.format("%02X", (b & 0xFF));
        }
        return sb.toString();
    }
}
