package lsieun.crypto.hmac;

import lsieun.crypto.utils.ByteUtils;

import java.util.Arrays;

public class HMACUtils {
    public static byte[] hmac(byte[] key_bytes, byte[] message_bytes, HashAlgorithm hash_algorithm) {
        int block_size = 64;
        byte[] standard_key_bytes = new byte[block_size];

        int key_length = key_bytes.length;

        if (key_length > block_size) {
            byte[] key_hash_bytes = hash_algorithm.apply(key_bytes);
            System.arraycopy(key_hash_bytes, 0, standard_key_bytes, 0, key_hash_bytes.length);
        }
        else {
            System.arraycopy(key_bytes, 0, standard_key_bytes, 0, key_length);
        }

        byte[] inner_key_pad = new byte[block_size];
        Arrays.fill(inner_key_pad, (byte) 0x36);
        byte[] first_xor_bytes = ByteUtils.xor(inner_key_pad, standard_key_bytes, block_size);

        byte[] merge_bytes1 = ByteUtils.concatenate(first_xor_bytes, message_bytes);
        byte[] digest_bytes1 = hash_algorithm.apply(merge_bytes1);

        byte[] outer_key_pad = new byte[block_size];
        Arrays.fill(outer_key_pad, (byte) 0x5c);
        byte[] second_xor_bytes = ByteUtils.xor(outer_key_pad, standard_key_bytes, block_size);

        byte[] merge_bytes2 = ByteUtils.concatenate(second_xor_bytes, digest_bytes1);
        byte[] digest_bytes2 = hash_algorithm.apply(merge_bytes2);
        return digest_bytes2;
    }
}
