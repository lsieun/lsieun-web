package lsieun.crypto.des;

import lsieun.crypto.utils.ECBUtils;

import java.util.Arrays;

// https://csrc.nist.gov/CSRC/media/Projects/Cryptographic-Standards-and-Guidelines/documents/examples/TDES_Core.pdf
public class TripleDESSample_NIST {
    public static final byte[] key = {
            (byte) 0x01, (byte) 0x23, (byte) 0x45, (byte) 0x67, (byte) 0x89, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF,
            (byte) 0x23, (byte) 0x45, (byte) 0x67, (byte) 0x89, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF, (byte) 0x01,
            (byte) 0x01, (byte) 0x23, (byte) 0x45, (byte) 0x67, (byte) 0x89, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF
    };

    public static final byte[] plain_text = {
            (byte) 0x6B, (byte) 0xC1, (byte) 0xBE, (byte) 0xE2,
            (byte) 0x2E, (byte) 0x40, (byte) 0x9F, (byte) 0x96,
            (byte) 0xE9, (byte) 0x3D, (byte) 0x7E, (byte) 0x11,
            (byte) 0x73, (byte) 0x93, (byte) 0x17, (byte) 0x2A,
            (byte) 0xAE, (byte) 0x2D, (byte) 0x8A, (byte) 0x57,
            (byte) 0x1E, (byte) 0x03, (byte) 0xAC, (byte) 0x9C,
            (byte) 0x9E, (byte) 0xB7, (byte) 0x6F, (byte) 0xAC,
            (byte) 0x45, (byte) 0xAF, (byte) 0x8E, (byte) 0x51
    };

    public static final byte[] cipher_text = {
            (byte) 0x06, (byte) 0xED, (byte) 0xE3, (byte) 0xD8,
            (byte) 0x28, (byte) 0x84, (byte) 0x09, (byte) 0x0A,
            (byte) 0xFF, (byte) 0x32, (byte) 0x2C, (byte) 0x19,
            (byte) 0xF0, (byte) 0x51, (byte) 0x84, (byte) 0x86,
            (byte) 0x73, (byte) 0x05, (byte) 0x76, (byte) 0x97,
            (byte) 0x2A, (byte) 0x66, (byte) 0x6E, (byte) 0x58,
            (byte) 0xB6, (byte) 0xC8, (byte) 0x8C, (byte) 0xF1,
            (byte) 0x07, (byte) 0x34, (byte) 0x0D, (byte) 0x3D
    };

    @SuppressWarnings("Duplicates")
    public static void main(String[] args) {
        byte[] encrypted_bytes = ECBUtils.ecb_encrypt(plain_text, key, 8, TripleDESUtils::des_block_encrypt);
        byte[] decrypted_bytes = ECBUtils.ecb_decrypt(cipher_text, key, 8, TripleDESUtils::des_block_decrypt);

        System.out.println(Arrays.equals(plain_text, decrypted_bytes));
        System.out.println(Arrays.equals(cipher_text, encrypted_bytes));
    }

}
