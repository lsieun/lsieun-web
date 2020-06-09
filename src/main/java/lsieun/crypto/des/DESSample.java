package lsieun.crypto.des;

public class DESSample {
    public static final byte[] plain_text_bytes = {
            (byte) 0x87, (byte) 0x87, (byte) 0x87, (byte) 0x87,
            (byte) 0x87, (byte) 0x87, (byte) 0x87, (byte) 0x87
    };

    public static final byte[] key_bytes = {
            (byte) 0x0E, (byte) 0x32, (byte) 0x92, (byte) 0x32,
            (byte) 0xEA, (byte) 0x6D, (byte) 0x0D, (byte) 0x73
    };

    public static final byte[] cipher_text_bytes = {
            0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00
    };
}
