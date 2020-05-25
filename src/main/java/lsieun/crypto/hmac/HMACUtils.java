package lsieun.crypto.hmac;

import java.util.Arrays;

public class HMACUtils {
    public static byte[] hmac(byte[] key_bytes, byte[] message_bytes, HashAlgorithm hash_algorithm) {
        int block_size = 64;
        byte[] standard_key_bytes = new byte[block_size];

        int key_length = key_bytes.length;

        if (key_length > block_size) {
            byte[] key_hash_bytes = hash_algorithm.apply(key_bytes, key_length);
            System.arraycopy(key_hash_bytes, 0, standard_key_bytes, 0, key_hash_bytes.length);
        }
        else if (key_length < block_size) {
            System.arraycopy(key_bytes, 0, standard_key_bytes, 0, key_length);
        }
        else {
            System.arraycopy(key_bytes, 0, standard_key_bytes, 0, block_size);
        }

        byte[] inner_key_pad = new byte[block_size];
        Arrays.fill(inner_key_pad, (byte) 0x36);
        xor(inner_key_pad, standard_key_bytes, block_size);

        byte[] merge_bytes1 = merge_bytes(inner_key_pad, message_bytes);
        byte[] digest_bytes1 = hash_algorithm.apply(merge_bytes1, merge_bytes1.length);

        byte[] outer_key_pad = new byte[block_size];
        Arrays.fill(outer_key_pad, (byte) 0x5c);
        xor(outer_key_pad, standard_key_bytes, block_size);

        byte[] merge_bytes2 = merge_bytes(outer_key_pad, digest_bytes1);
        byte[] digest_bytes2 = hash_algorithm.apply(merge_bytes2, merge_bytes2.length);
        return digest_bytes2;
    }

    public static void xor(byte[] dest_bytes, byte[] src_bytes, int len) {
        for (int i=0;i<len;i++) {
            dest_bytes[i] = (byte)((dest_bytes[i] & 0xFF) ^ (src_bytes[i] & 0xFF));
        }
    }

    public static byte[] merge_bytes(byte[] bytes1, byte[] bytes2) {
        int byte1_length = bytes1.length;
        int byte2_length = bytes2.length;
        int total_length = byte1_length + byte2_length;
        byte[] result_bytes = new byte[total_length];
        System.arraycopy(bytes1, 0, result_bytes, 0, byte1_length);
        System.arraycopy(bytes2, 0, result_bytes, byte1_length, byte2_length);
        return result_bytes;
    }
}
