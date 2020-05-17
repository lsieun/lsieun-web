package lsieun.crypto.md5;

import java.util.Arrays;
import java.util.Random;

public class MD5Test {
    public static void main(String[] args) {
        long timestamp = System.currentTimeMillis();
        Random rand = new Random(timestamp);
        int size = 200 + rand.nextInt(100);
        byte[] input = new byte[size];
        for (int i = 0; i < size; i++) {
            input[i] = (byte) rand.nextInt();
        }

        byte[] md5_bytes = MD5Utils.md5_hash(input, input.length);
        byte[] digest = MD5InJdk.md5(input);
        System.out.println(Arrays.equals(md5_bytes, digest));
    }
}
