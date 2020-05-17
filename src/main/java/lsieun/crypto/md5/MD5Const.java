package lsieun.crypto.md5;

public class MD5Const {
    public static final int MD5_BLOCK_SIZE = 64;
    public static final int MD5_OUTPUT_SIZE = 16;
    public static final int MD5_RESULT_SIZE = 4;
    public static final int MD5_PADDING_THRESHOLD = 56;

    public static final int[] MD5_INITIAL_HASH = {
            0x67452301,
            0xefcdab89,
            0x98badcfe,
            0x10325476
    };
}
