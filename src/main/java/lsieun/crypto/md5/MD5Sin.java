package lsieun.crypto.md5;

public class MD5Sin {
    public static void main(String[] args) {
        String format = "%03d: %08x";
        long total = 1L << 32;
        for (int i = 1; i <= 64; i++) {
            long val = (long) (total * Math.abs(Math.sin(i)));
            String line = String.format(format, i, val);
            System.out.println(line);
        }
    }
}
