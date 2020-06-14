package lsieun.crypto.aes;

public class AESUtils {

    public static byte[] aes_block_encrypt(byte[] input_block, byte[] key) {
        // rounds = key size in 4-byte words + 6
        int key_length = key.length;
        int rounds = (key_length >> 2) + 6;

        // Key Schedule
        byte[] key_schedule = AESKeyUtils.compute_key_schedule(key);

        // The First Key
        byte[] round_key = AESKeyUtils.get_key(key_schedule, 0);
        byte[][] key_matrix = MatrixUtils.from_array_to_matrix(round_key);

        /** --- 此前都是在处理key，接下来就是将key和data一起处理 --- **/

        // First Process
        byte[][] state = MatrixUtils.from_array_to_matrix(input_block);
        MatrixUtils.add_round_key(state, key_matrix);


        // Round Process
        for (int i = 0; i < rounds; i++) {
            MatrixUtils.substitute(state);
            MatrixUtils.shift_rows(state);

            if (i < (rounds - 1)) {
                MatrixUtils.mix_columns(state);
            }

            round_key = AESKeyUtils.get_key(key_schedule, i + 1);
            key_matrix = MatrixUtils.from_array_to_matrix(round_key);
            MatrixUtils.add_round_key(state, key_matrix);
        }

        return MatrixUtils.from_matrix_to_array(state);
    }

    public static byte[] aes_block_decrypt(byte[] input_block, byte[] key) {
        // rounds = key size in 4-byte words + 6
        int key_length = key.length;
        int rounds = (key_length >> 2) + 6;

        // Key Schedule
        byte[] key_schedule = AESKeyUtils.compute_key_schedule(key);

        // The Last Key
        byte[] round_key = AESKeyUtils.get_key(key_schedule, rounds);
        byte[][] key_matrix = MatrixUtils.from_array_to_matrix(round_key);

        /** --- 此前都是在处理key，接下来就是将key和data一起处理 --- **/

        // First Process
        byte[][] state = MatrixUtils.from_array_to_matrix(input_block);

        MatrixUtils.add_round_key(state, key_matrix);

        // Round Process
        for (int round = rounds; round > 0; round--) {
            MatrixUtils.inv_shift_rows(state);
            MatrixUtils.inv_substitute(state);

            round_key = AESKeyUtils.get_key(key_schedule, round - 1);
            key_matrix = MatrixUtils.from_array_to_matrix(round_key);
            MatrixUtils.add_round_key(state, key_matrix);

            if (round > 1) {
                MatrixUtils.inv_mix_columns(state);
            }
        }

        return MatrixUtils.from_matrix_to_array(state);
    }

}
