package lsieun.crypto.hmac;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

public class JDK_MAC_SHA1 {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {
        String example_str = "The quick brown fox jumps over the lazy dog";
        byte[] input = example_str.getBytes(StandardCharsets.UTF_8);
        byte[] key_bytes = "shared secret".getBytes(StandardCharsets.UTF_8);

        Mac mac = Mac.getInstance("HmacSHA1");

        String algorithm  = "RawBytes";
        SecretKeySpec key = new SecretKeySpec(key_bytes, algorithm);
        mac.init(key);

        byte[] mac_bytes = mac.doFinal(input);
        String hex_str = toHex(mac_bytes);
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
