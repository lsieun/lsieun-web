package lsieun.crypto.sha1;

import lsieun.crypto.utils.ByteUtils;

import java.nio.charset.StandardCharsets;

public class SHA1Test {
    public static void main(String[] args) {
        String example_str = "The quick brown fox jumps over the lazy dog";
        byte[] input = example_str.getBytes(StandardCharsets.UTF_8);
        byte[] digest = SHA1Utils.sha1_hash(input);

        String hex_str = ByteUtils.toHex(digest);
        System.out.println(hex_str); // 2FD4E1C67A2D28FCED849EE1BB76E7391B93EB12
    }
}
