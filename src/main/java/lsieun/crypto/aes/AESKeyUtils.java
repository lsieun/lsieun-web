package lsieun.crypto.aes;

import lsieun.crypto.utils.ByteUtils;

public class AESKeyUtils {
    // region rotation
    public static void rotate_word(byte[] word) {
        byte tmp = word[0];
        word[0] = word[1];
        word[1] = word[2];
        word[2] = word[3];
        word[3] = tmp;
    }

    public static byte shift_left(byte x) {
        return (byte) (((x & 0xFF) << 1) ^ (((x & 0x80) != 0) ? 0x1b : 0x00));
    }
    // endregion

    // region substitution
    public static void substitute_word(byte[] word) {
        for (int i = 0; i < 4; i++) {
            word[i] = (byte) AESConst.sbox[(word[i] & 0xF0) >> 4][word[i] & 0x0F];
        }
    }
    // endregion

    public static byte[] get_bytes(byte[] key_schedule, int pos, int length) {
        byte[] word = new byte[length];
        for (int i = 0; i < length; i++) {
            word[i] = key_schedule[pos + i];
        }
        return word;
    }

    public static byte[] get_word(byte[] key_schedule, int index) {
        int size = AESConst.AES_WORD_SIZE;
        return get_bytes(key_schedule, index * size, size);
    }

    public static byte[] get_key(byte[] key_schedule, int index) {
        int size = AESConst.AES_BLOCK_SIZE;
        return get_bytes(key_schedule, index * size, size);
    }

    // 核心方法
    public static byte[] compute_key_schedule(byte[] key) {
        int key_length = key.length;
        int word_size = AESConst.AES_WORD_SIZE;
        int word_count = key_length / word_size;

        int rounds = word_count + 6;
        int size = (rounds + 1) * AESConst.AES_BLOCK_SIZE;

        // copy original key to key_schedule
        byte[] key_schedule = new byte[size];
        System.arraycopy(key, 0, key_schedule, 0, key_length);

        // calculate remaining values in key_schedule
        byte round_constant = 0x01;
        int total_count = size / word_size;
        for (int i = word_count; i < total_count; i++) {
            byte[] last_word = get_word(key_schedule, i - 1);

            if (i % word_count == 0) {
                rotate_word(last_word);
                substitute_word(last_word);
                last_word[0] ^= round_constant;

                round_constant = shift_left(round_constant);
            }
            else if ((word_count > 6) && (i % word_count == 4)) {
                substitute_word(last_word);
            }

            byte[] first_word = get_word(key_schedule, i - word_count);

            byte[] computed_bytes = ByteUtils.xor(first_word, last_word, word_size);

            System.arraycopy(computed_bytes, 0, key_schedule, i * word_size, word_size);
        }

        return key_schedule;
    }

}
