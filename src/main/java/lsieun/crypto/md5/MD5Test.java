package lsieun.crypto.md5;

import lsieun.crypto.utils.ByteUtils;

import java.nio.charset.StandardCharsets;

public class MD5Test {
    public static void main(String[] args) {
        String str = "The quick brown fox jumps over the lazy dog";
        byte[] input = str.getBytes(StandardCharsets.UTF_8);
        byte[] digest = MD5Utils.md5_hash(input);

        String hex_str = ByteUtils.toHex(digest);
        System.out.println(hex_str); // 9E107D9D372BB6826BD81D3542A419D6
    }
}
