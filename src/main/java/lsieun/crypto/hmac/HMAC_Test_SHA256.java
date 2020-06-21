package lsieun.crypto.hmac;

import lsieun.crypto.sha256.SHA256Utils;
import lsieun.crypto.utils.ByteUtils;

import java.nio.charset.StandardCharsets;

public class HMAC_Test_SHA256 {
    public static void main(String[] args) {
        String example_str = "The quick brown fox jumps over the lazy dog";
        byte[] input = example_str.getBytes(StandardCharsets.UTF_8);
        byte[] key_bytes = "shared secret".getBytes(StandardCharsets.UTF_8);

        byte[] hmac_bytes = HMACUtils.hmac(key_bytes, input, SHA256Utils::sha256_hash);
        String hex_str = ByteUtils.toHex(hmac_bytes);
        // E97E97C3FA49782F3FC2CA0C1497DFE948FB029FDC21613E0777E0FF9CDD891B
        System.out.println(hex_str);
    }
}
