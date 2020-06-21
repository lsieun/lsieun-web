package lsieun.crypto.hmac;

import lsieun.crypto.utils.ByteUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class JDK_HMAC_MD5 {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {
        String example_str = "The quick brown fox jumps over the lazy dog";
        byte[] input = example_str.getBytes(StandardCharsets.UTF_8);
        byte[] key_bytes = "shared secret".getBytes(StandardCharsets.UTF_8);

        Mac mac = Mac.getInstance("HmacMD5");

        String algorithm  = "RawBytes";
        SecretKeySpec key = new SecretKeySpec(key_bytes, algorithm);
        mac.init(key);

        byte[] mac_bytes = mac.doFinal(input);
        String hex_str = ByteUtils.toHex(mac_bytes);
        System.out.println(hex_str); // 0EBDB48B207D3779FCF43AE18DDCF7E6
    }
}
