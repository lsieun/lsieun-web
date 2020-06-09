package lsieun.crypto.des;

import lsieun.crypto.utils.ByteUtils;

import java.util.Arrays;

public class DESTest {
    public static void main(String[] args) {
        byte[] input = DESSample.plain_text_bytes;
        byte[] key = DESSample.key_bytes;

        byte[] encrypted_bytes = DESUtils.des_operate(input, key, CipherType.ENCRYPT);
        byte[] decrypted_bytes = DESUtils.des_operate(encrypted_bytes, key, CipherType.DECRYPT);

        System.out.println("          message: " + ByteUtils.toHex(input));
        System.out.println("              key: " + ByteUtils.toHex(key));
        System.out.println("encrypted message: " + ByteUtils.toHex(encrypted_bytes));
        System.out.println("decrypted message: " + ByteUtils.toHex(decrypted_bytes));

        System.out.println(Arrays.equals(DESSample.cipher_text_bytes, encrypted_bytes));
        System.out.println(Arrays.equals(input, decrypted_bytes));
    }
}
