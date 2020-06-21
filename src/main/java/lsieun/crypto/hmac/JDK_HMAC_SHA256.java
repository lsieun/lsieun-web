package lsieun.crypto.hmac;

import lsieun.crypto.utils.ByteUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class JDK_HMAC_SHA256 {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {
        String example_str = "The quick brown fox jumps over the lazy dog";
        byte[] input = example_str.getBytes(StandardCharsets.UTF_8);
        byte[] key_bytes = "shared secret".getBytes(StandardCharsets.UTF_8);

        Mac mac = Mac.getInstance("HmacSHA256");

        String algorithm  = "RawBytes";
        SecretKeySpec key = new SecretKeySpec(key_bytes, algorithm);
        mac.init(key);

        byte[] mac_bytes = mac.doFinal(input);
        String hex_str = ByteUtils.toHex(mac_bytes);
        // E97E97C3FA49782F3FC2CA0C1497DFE948FB029FDC21613E0777E0FF9CDD891B
        System.out.println(hex_str);
    }
}
