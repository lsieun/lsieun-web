package lsieun.crypto.md5;

import lsieun.crypto.utils.ByteUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class JDK_MD5 {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        String example_str = "The quick brown fox jumps over the lazy dog";
        byte[] input = example_str.getBytes(StandardCharsets.UTF_8);

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(input);
        byte[] digest = md.digest();

        String hex_str = ByteUtils.toHex(digest);
        System.out.println(hex_str); // 9E107D9D372BB6826BD81D3542A419D6
    }
}
