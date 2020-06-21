package lsieun.crypto.sha256;

import lsieun.crypto.utils.ByteUtils;

import java.nio.charset.StandardCharsets;

public class SHA256Test {
    public static void main(String[] args) {
        String example_str = "The quick brown fox jumps over the lazy dog";
        byte[] input = example_str.getBytes(StandardCharsets.UTF_8);
        byte[] digest = SHA256Utils.sha256_hash(input);

        String hex_str = ByteUtils.toHex(digest);
        // D7A8FBB307D7809469CA9ABCB0082E4F8D5651E46D3CDB762D02D0BF37C9E592
        System.out.println(hex_str);
    }
}
