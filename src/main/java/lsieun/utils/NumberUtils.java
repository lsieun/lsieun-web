package lsieun.utils;

import java.nio.ByteBuffer;

public class NumberUtils {

    public static byte[] fromInt(int x) {
        ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES);
        buffer.putInt(x);
        return buffer.array();
    }

    public static int toInt(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES);
        buffer.put(bytes);
        buffer.flip();//need flip
        return buffer.getInt();
    }

    public static byte[] fromLong(long x) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(x);
        return buffer.array();
    }

    public static long toLong(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.put(bytes);
        buffer.flip();//need flip
        return buffer.getLong();
    }

}
