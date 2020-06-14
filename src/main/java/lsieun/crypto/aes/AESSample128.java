package lsieun.crypto.aes;

import lsieun.crypto.utils.ECBUtils;

import java.util.Arrays;

// https://csrc.nist.gov/CSRC/media/Projects/Cryptographic-Standards-and-Guidelines/documents/examples/AES_Core128.pdf
public class AESSample128 {
    public static final byte[] key = {
            (byte) 0x2B, (byte) 0x7E, (byte) 0x15, (byte) 0x16,
            (byte) 0x28, (byte) 0xAE, (byte) 0xD2, (byte) 0xA6,
            (byte) 0xAB, (byte) 0xF7, (byte) 0x15, (byte) 0x88,
            (byte) 0x09, (byte) 0xCF, (byte) 0x4F, (byte) 0x3C
    };

    public static final byte[] plain_text = {
            (byte) 0x6B, (byte) 0xC1, (byte) 0xBE, (byte) 0xE2,
            (byte) 0x2E, (byte) 0x40, (byte) 0x9F, (byte) 0x96,
            (byte) 0xE9, (byte) 0x3D, (byte) 0x7E, (byte) 0x11,
            (byte) 0x73, (byte) 0x93, (byte) 0x17, (byte) 0x2A,
            (byte) 0xAE, (byte) 0x2D, (byte) 0x8A, (byte) 0x57,
            (byte) 0x1E, (byte) 0x03, (byte) 0xAC, (byte) 0x9C,
            (byte) 0x9E, (byte) 0xB7, (byte) 0x6F, (byte) 0xAC,
            (byte) 0x45, (byte) 0xAF, (byte) 0x8E, (byte) 0x51,
            (byte) 0x30, (byte) 0xC8, (byte) 0x1C, (byte) 0x46,
            (byte) 0xA3, (byte) 0x5C, (byte) 0xE4, (byte) 0x11,
            (byte) 0xE5, (byte) 0xFB, (byte) 0xC1, (byte) 0x19,
            (byte) 0x1A, (byte) 0x0A, (byte) 0x52, (byte) 0xEF,
            (byte) 0xF6, (byte) 0x9F, (byte) 0x24, (byte) 0x45,
            (byte) 0xDF, (byte) 0x4F, (byte) 0x9B, (byte) 0x17,
            (byte) 0xAD, (byte) 0x2B, (byte) 0x41, (byte) 0x7B,
            (byte) 0xE6, (byte) 0x6C, (byte) 0x37, (byte) 0x10
    };

    public static final byte[] cipher_text = {
            (byte) 0x3A, (byte) 0xD7, (byte) 0x7B, (byte) 0xB4,
            (byte) 0x0D, (byte) 0x7A, (byte) 0x36, (byte) 0x60,
            (byte) 0xA8, (byte) 0x9E, (byte) 0xCA, (byte) 0xF3,
            (byte) 0x24, (byte) 0x66, (byte) 0xEF, (byte) 0x97,
            (byte) 0xF5, (byte) 0xD3, (byte) 0xD5, (byte) 0x85,
            (byte) 0x03, (byte) 0xB9, (byte) 0x69, (byte) 0x9D,
            (byte) 0xE7, (byte) 0x85, (byte) 0x89, (byte) 0x5A,
            (byte) 0x96, (byte) 0xFD, (byte) 0xBA, (byte) 0xAF,
            (byte) 0x43, (byte) 0xB1, (byte) 0xCD, (byte) 0x7F,
            (byte) 0x59, (byte) 0x8E, (byte) 0xCE, (byte) 0x23,
            (byte) 0x88, (byte) 0x1B, (byte) 0x00, (byte) 0xE3,
            (byte) 0xED, (byte) 0x03, (byte) 0x06, (byte) 0x88,
            (byte) 0x7B, (byte) 0x0C, (byte) 0x78, (byte) 0x5E,
            (byte) 0x27, (byte) 0xE8, (byte) 0xAD, (byte) 0x3F,
            (byte) 0x82, (byte) 0x23, (byte) 0x20, (byte) 0x71,
            (byte) 0x04, (byte) 0x72, (byte) 0x5D, (byte) 0xD4
    };

    @SuppressWarnings("Duplicates")
    public static void main(String[] args) {
        byte[] encrypted_bytes = ECBUtils.ecb_encrypt(plain_text, key, 16, AESUtils::aes_block_encrypt);
        byte[] decrypted_bytes = ECBUtils.ecb_decrypt(cipher_text, key, 16, AESUtils::aes_block_decrypt);

        System.out.println(Arrays.equals(plain_text, decrypted_bytes));
        System.out.println(Arrays.equals(cipher_text, encrypted_bytes));
    }
}
