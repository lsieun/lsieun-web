package lsieun.crypto.des;

import lsieun.utils.ByteUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;

public class DESInJDK {
    public static byte[] encrypt(byte[] plan_text_bytes, byte[] key_bytes) {
        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(key_bytes);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = keyFactory.generateSecret(desKey);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, random);
            return cipher.doFinal(plan_text_bytes);
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static byte[] decrypt(byte[] encrypted_bytes, byte[] key_bytes) {
        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(key_bytes);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = keyFactory.generateSecret(desKey);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, random);
            return cipher.doFinal(encrypted_bytes);
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void main(String[] args) {
        byte[] encrypted_msg = encrypt(DESSample.plain_text_bytes, DESSample.key_bytes);
        System.out.println(ByteUtils.toHex(encrypted_msg));
    }
}
