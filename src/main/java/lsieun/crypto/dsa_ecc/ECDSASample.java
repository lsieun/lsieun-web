package lsieun.crypto.dsa_ecc;

/**
 * <p>ECC parameters</p>
 * <p>数据URL：https://tools.ietf.org/html/rfc4754#section-8.1</p>
 */
public class ECDSASample {
    /**
     * FFFFFFFF 00000001 00000000 00000000
     * 00000000 FFFFFFFF FFFFFFFF FFFFFFFF
     */
    public static final byte[] P = {
            (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
            (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x01,
            (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
            (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
            (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF
    };

    /**
     * 5AC635D8 AA3A93E7 B3EBBD55 769886BC
     * 651D06B0 CC53B0F6 3BCE3C3E 27D2604B
     */
    public static final byte[] b = {
            (byte)0x5A, (byte)0xC6, (byte)0x35, (byte)0xD8,
            (byte)0xAA, (byte)0x3A, (byte)0x93, (byte)0xE7,
            (byte)0xB3, (byte)0xEB, (byte)0xBD, (byte)0x55,
            (byte)0x76, (byte)0x98, (byte)0x86, (byte)0xBC,
            (byte)0x65, (byte)0x1D, (byte)0x06, (byte)0xB0,
            (byte)0xCC, (byte)0x53, (byte)0xB0, (byte)0xF6,
            (byte)0x3B, (byte)0xCE, (byte)0x3C, (byte)0x3E,
            (byte)0x27, (byte)0xD2, (byte)0x60, (byte)0x4B
    };

    /**
     * FFFFFFFF 00000000 FFFFFFFF FFFFFFFF
     * BCE6FAAD A7179E84 F3B9CAC2 FC632551
     */
    public static final byte[] q = {
            (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
            (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
            (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
            (byte)0xBC, (byte)0xE6, (byte)0xFA, (byte)0xAD,
            (byte)0xA7, (byte)0x17, (byte)0x9E, (byte)0x84,
            (byte)0xF3, (byte)0xB9, (byte)0xCA, (byte)0xC2,
            (byte)0xFC, (byte)0x63, (byte)0x25, (byte)0x51
    };

    /**
     * 6B17D1F2 E12C4247 F8BCE6E5 63A440F2
     * 77037D81 2DEB33A0 F4A13945 D898C296
     */
    public static final byte[] gx = {
            (byte)0x6B, (byte)0x17, (byte)0xD1, (byte)0xF2,
            (byte)0xE1, (byte)0x2C, (byte)0x42, (byte)0x47,
            (byte)0xF8, (byte)0xBC, (byte)0xE6, (byte)0xE5,
            (byte)0x63, (byte)0xA4, (byte)0x40, (byte)0xF2,
            (byte)0x77, (byte)0x03, (byte)0x7D, (byte)0x81,
            (byte)0x2D, (byte)0xEB, (byte)0x33, (byte)0xA0,
            (byte)0xF4, (byte)0xA1, (byte)0x39, (byte)0x45,
            (byte)0xD8, (byte)0x98, (byte)0xC2, (byte)0x96
    };

    /**
     * 4FE342E2 FE1A7F9B 8EE7EB4A 7C0F9E16
     * 2BCE3357 6B315ECE CBB64068 37BF51F5
     */
    public static final byte[] gy = {
            (byte)0x4F, (byte)0xE3, (byte)0x42, (byte)0xE2,
            (byte)0xFE, (byte)0x1A, (byte)0x7F, (byte)0x9B,
            (byte)0x8E, (byte)0xE7, (byte)0xEB, (byte)0x4A,
            (byte)0x7C, (byte)0x0F, (byte)0x9E, (byte)0x16,
            (byte)0x2B, (byte)0xCE, (byte)0x33, (byte)0x57,
            (byte)0x6B, (byte)0x31, (byte)0x5E, (byte)0xCE,
            (byte)0xCB, (byte)0xB6, (byte)0x40, (byte)0x68,
            (byte)0x37, (byte)0xBF, (byte)0x51, (byte)0xF5
    };

    /**
     * <p>key</p>
     * DC51D386 6A15BACD E33D96F9 92FCA99D
     * A7E6EF09 34E70975 59C27F16 14C88A7F
     */
    public static final byte[] w = {
            (byte)0xDC, (byte)0x51, (byte)0xD3, (byte)0x86,
            (byte)0x6A, (byte)0x15, (byte)0xBA, (byte)0xCD,
            (byte)0xE3, (byte)0x3D, (byte)0x96, (byte)0xF9,
            (byte)0x92, (byte)0xFC, (byte)0xA9, (byte)0x9D,
            (byte)0xA7, (byte)0xE6, (byte)0xEF, (byte)0x09,
            (byte)0x34, (byte)0xE7, (byte)0x09, (byte)0x75,
            (byte)0x59, (byte)0xC2, (byte)0x7F, (byte)0x16,
            (byte)0x14, (byte)0xC8, (byte)0x8A, (byte)0x7F
    };

    public static final byte[] K = {
            (byte)0x9E, (byte)0x56, (byte)0xF5, (byte)0x09,
            (byte)0x19, (byte)0x67, (byte)0x84, (byte)0xD9,
            (byte)0x63, (byte)0xD1, (byte)0xC0, (byte)0xA4,
            (byte)0x01, (byte)0x51, (byte)0x0E, (byte)0xE7,
            (byte)0xAD, (byte)0xA3, (byte)0xDC, (byte)0xC5,
            (byte)0xDE, (byte)0xE0, (byte)0x4B, (byte)0x15,
            (byte)0x4B, (byte)0xF6, (byte)0x1A, (byte)0xF1,
            (byte)0xD5, (byte)0xA6, (byte)0xDE, (byte)0xCE
    };
}