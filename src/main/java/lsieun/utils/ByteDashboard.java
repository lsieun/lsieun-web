package lsieun.utils;

public class ByteDashboard {
    // 存储“数据”的部分
    private final byte[] bytes;

    //对“数据”进行记录的三个变量
    private int start;
    private int stop;
    private int index;

    public ByteDashboard(byte[] bytes) {
        this.bytes = bytes;
        this.start = 0;
        this.stop = bytes.length;
        this.index = this.start;
    }

    // region getter & setter
    public byte[] getBytes() {
        return bytes;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getStop() {
        return stop;
    }

    public void setStop(int stop) {
        this.stop = stop;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
    // endregion

    public boolean hasNext() {
        if (index >= start && index < stop) return true;
        return false;
    }

    public byte next() {
        byte b = this.bytes[index++];
        return b;
    }

    public byte[] nextN(int n) {
        byte[] array = new byte[n];
        for (int i = 0; i < n; i++) {
            byte b = this.bytes[index++];
            array[i] = b;
        }
        return array;
    }

    public byte[] nextN(int offset, int n) {
        index = index + offset;
        return nextN(n);
    }

    public byte peek() {
        byte b = this.bytes[index];
        return b;
    }

    public byte[] peekN(int n) {
        byte[] array = new byte[n];
        for (int i = 0; i < n; i++) {
            byte b = this.bytes[index + i];
            array[i] = b;
        }
        return array;
    }

    public byte[] peekN(int offset, int n) {
        byte[] bytes = new byte[n];
        for (int i = 0; i < n; i++) {
            byte b = this.bytes[index + offset + i];
            bytes[i] = b;
        }
        return bytes;
    }

    public void skip(int n) {
        index = index + n;
    }

    // region readXXX
    public byte readByte() {
        byte b = next();
        return b;
    }

    public int readInt() {
        byte[] bytes = nextN(4);
        return ByteUtils.toInt(bytes);
    }

    public long readLong() {
        byte[] bytes = nextN(8);
        return ByteUtils.toLong(bytes);
    }
    // endregion

    public void reset() {
        index = this.start;
    }

    @Override
    public String toString() {
        return "ByteDashboard {" +
                "start=" + start +
                ", stop=" + stop +
                ", index=" + index +
                '}';
    }


    public static byte[] readBytes(byte[] code_bytes, int offset, int n) {
        byte[] array = new byte[n];
        for (int i = 0; i < n; i++) {
            byte b = code_bytes[offset + i];
            array[i] = b;
        }
        return array;
    }
}