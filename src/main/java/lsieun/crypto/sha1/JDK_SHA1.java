package lsieun.crypto.sha1;

import lsieun.crypto.utils.ByteUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class JDK_SHA1 {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        String example_str = "The quick brown fox jumps over the lazy dog";
        byte[] input = example_str.getBytes(StandardCharsets.UTF_8);

        MessageDigest md = MessageDigest.getInstance("SHA-1");
        md.update(input);
        byte[] digest = md.digest();

        String hex_str = ByteUtils.toHex(digest);
        System.out.println(hex_str); // 2FD4E1C67A2D28FCED849EE1BB76E7391B93EB12
    }
}
