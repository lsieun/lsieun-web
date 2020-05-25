package lsieun.crypto.hmac;

import lsieun.crypto.sha1.SHA1Utils;

import java.nio.charset.StandardCharsets;
import java.util.Formatter;

public class HMAC_Test_SHA1 {
    public static void main(String[] args) {
        String example_str = "The quick brown fox jumps over the lazy dog";
        byte[] input = example_str.getBytes(StandardCharsets.UTF_8);
        byte[] key_bytes = "shared secret".getBytes(StandardCharsets.UTF_8);

        byte[] hmac_bytes = HMACUtils.hmac(key_bytes, input, SHA1Utils::sha1_hash);
        String hex_str = toHex(hmac_bytes);
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
