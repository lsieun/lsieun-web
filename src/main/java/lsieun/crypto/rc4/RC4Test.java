package lsieun.crypto.rc4;

import lsieun.crypto.utils.ByteUtils;

import java.nio.charset.StandardCharsets;

public class RC4Test {
    public static void main(String[] args) {
        byte[] input = "The quick brown fox jumps over the lazy dog.".getBytes(StandardCharsets.UTF_8);
        byte[] key = "password".getBytes(StandardCharsets.UTF_8);
        System.out.println(" original bytes: " + ByteUtils.toHex(input));

        byte[] encrypted_bytes = RC4Utils.rc4_operate(input, key);
        System.out.println("encrypted bytes: " + ByteUtils.toHex(encrypted_bytes));

        byte[] decrypted_bytes = RC4Utils.rc4_operate(encrypted_bytes, key);
        System.out.println("decrypted bytes: " + ByteUtils.toHex(decrypted_bytes));
    }
}
