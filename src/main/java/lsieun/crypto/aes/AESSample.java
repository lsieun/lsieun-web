package lsieun.crypto.aes;

import lsieun.crypto.utils.ByteUtils;

public class AESSample {
    public static final byte[] input = {
            (byte) 0x32, (byte) 0x43, (byte) 0xf6, (byte) 0xa8,
            (byte) 0x88, (byte) 0x5a, (byte) 0x30, (byte) 0x8d,
            (byte) 0x31, (byte) 0x31, (byte) 0x98, (byte) 0xa2,
            (byte) 0xe0, (byte) 0x37, (byte) 0x07, (byte) 0x34
    };

    public static final byte[] key_128_bit = {
            (byte) 0x2b, (byte) 0x7e, (byte) 0x15, (byte) 0x16,
            (byte) 0x28, (byte) 0xae, (byte) 0xd2, (byte) 0xa6,
            (byte) 0xab, (byte) 0xf7, (byte) 0x15, (byte) 0x88,
            (byte) 0x09, (byte) 0xcf, (byte) 0x4f, (byte) 0x3c
    };

    public static final byte[] key_192_bit = {
            (byte) 0x8e, (byte) 0x73, (byte) 0xb0, (byte) 0xf7,
            (byte) 0xda, (byte) 0x0e, (byte) 0x64, (byte) 0x52,
            (byte) 0xc8, (byte) 0x10, (byte) 0xf3, (byte) 0x2b,
            (byte) 0x80, (byte) 0x90, (byte) 0x79, (byte) 0xe5,
            (byte) 0x62, (byte) 0xf8, (byte) 0xea, (byte) 0xd2,
            (byte) 0x52, (byte) 0x2c, (byte) 0x6b, (byte) 0x7b
    };

    public static final byte[] key_256_bit = {
            (byte) 0x60, (byte) 0x3d, (byte) 0xeb, (byte) 0x10,
            (byte) 0x15, (byte) 0xca, (byte) 0x71, (byte) 0xbe,
            (byte) 0x2b, (byte) 0x73, (byte) 0xae, (byte) 0xf0,
            (byte) 0x85, (byte) 0x7d, (byte) 0x77, (byte) 0x81,
            (byte) 0x1f, (byte) 0x35, (byte) 0x2c, (byte) 0x07,
            (byte) 0x3b, (byte) 0x61, (byte) 0x08, (byte) 0xd7,
            (byte) 0x2d, (byte) 0x98, (byte) 0x10, (byte) 0xa3,
            (byte) 0x09, (byte) 0x14, (byte) 0xdf, (byte) 0xf4
    };

    public static void main(String[] args) {
        byte[] key_schedule = AESKeyUtils.compute_key_schedule(key_256_bit);
        System.out.println(ByteUtils.toHex(key_schedule));
    }
}
