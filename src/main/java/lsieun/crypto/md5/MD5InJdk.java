package lsieun.crypto.md5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5InJdk {
    public static byte[] md5(byte[] input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input);
            byte[] digest = md.digest();
            return digest;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("NoSuchAlgorithmException");
        }
    }
}
