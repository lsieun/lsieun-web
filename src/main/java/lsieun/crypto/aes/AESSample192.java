package lsieun.crypto.aes;

import lsieun.crypto.utils.ECBUtils;

import java.util.Arrays;

// https://csrc.nist.gov/CSRC/media/Projects/Cryptographic-Standards-and-Guidelines/documents/examples/AES_Core192.pdf
public class AESSample192 {
    public static final byte[] key = {
            (byte) 0x8E, (byte) 0x73, (byte) 0xB0, (byte) 0xF7,
            (byte) 0xDA, (byte) 0x0E, (byte) 0x64, (byte) 0x52,
            (byte) 0xC8, (byte) 0x10, (byte) 0xF3, (byte) 0x2B,
            (byte) 0x80, (byte) 0x90, (byte) 0x79, (byte) 0xE5,
            (byte) 0x62, (byte) 0xF8, (byte) 0xEA, (byte) 0xD2,
            (byte) 0x52, (byte) 0x2C, (byte) 0x6B, (byte) 0x7B
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
            (byte) 0xBD, (byte) 0x33, (byte) 0x4F, (byte) 0x1D,
            (byte) 0x6E, (byte) 0x45, (byte) 0xF2, (byte) 0x5F,
            (byte) 0xF7, (byte) 0x12, (byte) 0xA2, (byte) 0x14,
            (byte) 0x57, (byte) 0x1F, (byte) 0xA5, (byte) 0xCC,
            (byte) 0x97, (byte) 0x41, (byte) 0x04, (byte) 0x84,
            (byte) 0x6D, (byte) 0x0A, (byte) 0xD3, (byte) 0xAD,
            (byte) 0x77, (byte) 0x34, (byte) 0xEC, (byte) 0xB3,
            (byte) 0xEC, (byte) 0xEE, (byte) 0x4E, (byte) 0xEF,
            (byte) 0xEF, (byte) 0x7A, (byte) 0xFD, (byte) 0x22,
            (byte) 0x70, (byte) 0xE2, (byte) 0xE6, (byte) 0x0A,
            (byte) 0xDC, (byte) 0xE0, (byte) 0xBA, (byte) 0x2F,
            (byte) 0xAC, (byte) 0xE6, (byte) 0x44, (byte) 0x4E,
            (byte) 0x9A, (byte) 0x4B, (byte) 0x41, (byte) 0xBA,
            (byte) 0x73, (byte) 0x8D, (byte) 0x6C, (byte) 0x72,
            (byte) 0xFB, (byte) 0x16, (byte) 0x69, (byte) 0x16,
            (byte) 0x03, (byte) 0xC1, (byte) 0x8E, (byte) 0x0E
    };

    @SuppressWarnings("Duplicates")
    public static void main(String[] args) {
        byte[] encrypted_bytes = ECBUtils.ecb_encrypt(plain_text, key, 16, AESUtils::aes_block_encrypt);
        byte[] decrypted_bytes = ECBUtils.ecb_decrypt(cipher_text, key, 16, AESUtils::aes_block_decrypt);

        System.out.println(Arrays.equals(plain_text, decrypted_bytes));
        System.out.println(Arrays.equals(cipher_text, encrypted_bytes));
    }
}
