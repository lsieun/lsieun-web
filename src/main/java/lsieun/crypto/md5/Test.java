package lsieun.crypto.md5;

import java.nio.charset.StandardCharsets;
import java.util.Formatter;

public class Test {
    public static void main(String[] args) {
        String str = "Hello World";
        byte[] input = str.getBytes(StandardCharsets.UTF_8);
        byte[] digest = MD5Utils.md5_hash(input, input.length);

        String hex_str = toHex(digest);
        System.out.println(hex_str); // B1 0A 8D B1 64 E0 75 41 05 B7 A9 9B E7 2E 3F E5
    }

    public static String toHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        Formatter fm = new Formatter(sb);
        for (byte b : bytes) {
            fm.format("%02X ", (b & 0xFF));
        }
        return sb.toString();
    }
}
