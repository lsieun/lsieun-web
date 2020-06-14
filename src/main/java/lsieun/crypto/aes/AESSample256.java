package lsieun.crypto.aes;

import lsieun.crypto.utils.ECBUtils;

import java.util.Arrays;

public class AESSample256 {
    public static final byte[] key = {
            (byte) 0x60, (byte) 0x3D, (byte) 0xEB, (byte) 0x10,
            (byte) 0x15, (byte) 0xCA, (byte) 0x71, (byte) 0xBE,
            (byte) 0x2B, (byte) 0x73, (byte) 0xAE, (byte) 0xF0,
            (byte) 0x85, (byte) 0x7D, (byte) 0x77, (byte) 0x81,
            (byte) 0x1F, (byte) 0x35, (byte) 0x2C, (byte) 0x07,
            (byte) 0x3B, (byte) 0x61, (byte) 0x08, (byte) 0xD7,
            (byte) 0x2D, (byte) 0x98, (byte) 0x10, (byte) 0xA3,
            (byte) 0x09, (byte) 0x14, (byte) 0xDF, (byte) 0xF4
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
            (byte) 0xF3, (byte) 0xEE, (byte) 0xD1, (byte) 0xBD,
            (byte) 0xB5, (byte) 0xD2, (byte) 0xA0, (byte) 0x3C,
            (byte) 0x06, (byte) 0x4B, (byte) 0x5A, (byte) 0x7E,
            (byte) 0x3D, (byte) 0xB1, (byte) 0x81, (byte) 0xF8,
            (byte) 0x59, (byte) 0x1C, (byte) 0xCB, (byte) 0x10,
            (byte) 0xD4, (byte) 0x10, (byte) 0xED, (byte) 0x26,
            (byte) 0xDC, (byte) 0x5B, (byte) 0xA7, (byte) 0x4A,
            (byte) 0x31, (byte) 0x36, (byte) 0x28, (byte) 0x70,
            (byte) 0xB6, (byte) 0xED, (byte) 0x21, (byte) 0xB9,
            (byte) 0x9C, (byte) 0xA6, (byte) 0xF4, (byte) 0xF9,
            (byte) 0xF1, (byte) 0x53, (byte) 0xE7, (byte) 0xB1,
            (byte) 0xBE, (byte) 0xAF, (byte) 0xED, (byte) 0x1D,
            (byte) 0x23, (byte) 0x30, (byte) 0x4B, (byte) 0x7A,
            (byte) 0x39, (byte) 0xF9, (byte) 0xF3, (byte) 0xFF,
            (byte) 0x06, (byte) 0x7D, (byte) 0x8D, (byte) 0x8F,
            (byte) 0x9E, (byte) 0x24, (byte) 0xEC, (byte) 0xC7
    };

    @SuppressWarnings("Duplicates")
    public static void main(String[] args) {
        byte[] encrypted_bytes = ECBUtils.ecb_encrypt(plain_text, key, 16, AESUtils::aes_block_encrypt);
        byte[] decrypted_bytes = ECBUtils.ecb_decrypt(cipher_text, key, 16, AESUtils::aes_block_decrypt);

        System.out.println(Arrays.equals(plain_text, decrypted_bytes));
        System.out.println(Arrays.equals(cipher_text, encrypted_bytes));
    }
}
