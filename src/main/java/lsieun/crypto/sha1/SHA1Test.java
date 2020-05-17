package lsieun.crypto.sha1;

import java.nio.charset.StandardCharsets;
import java.util.Formatter;

public class SHA1Test {
    public static void main(String[] args) {
        String example_str = "The quick brown fox jumps over the lazy dog";
        byte[] input = example_str.getBytes(StandardCharsets.UTF_8);
        byte[] digest = SHA1Utils.sha1_hash(input, input.length);

        String hex_str = toHex(digest);
        System.out.println(hex_str);
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
