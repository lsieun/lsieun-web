package lsieun.crypto.hmac;

import lsieun.crypto.md5.MD5Utils;
import lsieun.crypto.utils.ByteUtils;

import java.nio.charset.StandardCharsets;

public class HMAC_Test_MD5 {
    public static void main(String[] args) {
        String example_str = "The quick brown fox jumps over the lazy dog";
        byte[] input = example_str.getBytes(StandardCharsets.UTF_8);
        byte[] key_bytes = "shared secret".getBytes(StandardCharsets.UTF_8);

        byte[] hmac_bytes = HMACUtils.hmac(key_bytes, input, MD5Utils::md5_hash);
        String hex_str = ByteUtils.toHex(hmac_bytes);
        System.out.println(hex_str); // 0EBDB48B207D3779FCF43AE18DDCF7E6
    }
}
