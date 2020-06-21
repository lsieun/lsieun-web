package lsieun.crypto.hmac;

import lsieun.crypto.sha1.SHA1Utils;
import lsieun.crypto.utils.ByteUtils;

import java.nio.charset.StandardCharsets;

public class HMAC_Test_SHA1 {
    public static void main(String[] args) {
        String example_str = "The quick brown fox jumps over the lazy dog";
        byte[] input = example_str.getBytes(StandardCharsets.UTF_8);
        byte[] key_bytes = "shared secret".getBytes(StandardCharsets.UTF_8);

        byte[] hmac_bytes = HMACUtils.hmac(key_bytes, input, SHA1Utils::sha1_hash);
        String hex_str = ByteUtils.toHex(hmac_bytes);
        System.out.println(hex_str); // BE15352DD80B1EC0CE93898F22EA6ACBEBB0FD81
    }
}
