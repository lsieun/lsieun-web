package lsieun.crypto.aes;

import java.util.Formatter;

public class MatrixUtils {
    // region xor
    public static void add_round_key(byte[][] state, byte[][] key_matrix) {
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                state[r][c] = (byte) (state[r][c] ^ key_matrix[r][c]);
            }
        }
    }
    // endregion

    // region substitution
    public static void substitute(byte[][] state) {
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                state[r][c] = (byte) AESConst.sbox[(state[r][c] & 0xF0) >> 4][state[r][c] & 0x0F];
            }
        }
    }

    public static void inv_substitute(byte[][] state) {
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                state[r][c] = (byte) AESConst.inv_sbox[(state[r][c] & 0xF0) >> 4][state[r][c] & 0x0F];
            }
        }
    }
    // endregion

    // region shift
    public static void shift_rows(byte[][] state) {
        byte tmp;
        tmp = state[1][0];
        state[1][0] = state[1][1];
        state[1][1] = state[1][2];
        state[1][2] = state[1][3];
        state[1][3] = tmp;

        tmp = state[2][0];
        state[2][0] = state[2][2];
        state[2][2] = tmp;
        tmp = state[2][1];
        state[2][1] = state[2][3];
        state[2][3] = tmp;

        tmp = state[3][3];
        state[3][3] = state[3][2];
        state[3][2] = state[3][1];
        state[3][1] = state[3][0];
        state[3][0] = tmp;
    }

    public static void inv_shift_rows(byte[][] state) {
        byte tmp = state[1][2];
        state[1][2] = state[1][1];
        state[1][1] = state[1][0];
        state[1][0] = state[1][3];
        state[1][3] = tmp;

        tmp = state[2][0];
        state[2][0] = state[2][2];
        state[2][2] = tmp;
        tmp = state[2][1];
        state[2][1] = state[2][3];
        state[2][3] = tmp;

        tmp = state[3][0];
        state[3][0] = state[3][1];
        state[3][1] = state[3][2];
        state[3][2] = state[3][3];
        state[3][3] = tmp;
    }
    // endregion

    // region column-mixing
    public static byte xtime(byte x) {
        return (byte) (((x & 0xFF) << 1) ^ (((x & 0x80) != 0) ? 0x1b : 0x00));
    }

    public static byte dot(byte x, byte y) {
        int product = 0;

        for (byte mask = 0x01; mask != 0; mask <<= 1) {
            if ((y & mask) != 0) {
                product ^= (x & 0xFF);
            }
            x = xtime(x);
        }

        return (byte) product;
    }

    public static void mix_columns(byte[][] s) {
        int[] t = new int[4];
        for (int c = 0; c < 4; c++) {
            t[0] = dot((byte) 2, s[0][c]) ^ dot((byte) 3, s[1][c]) ^ s[2][c] ^ s[3][c];
            t[1] = s[0][c] ^ dot((byte) 2, s[1][c]) ^ dot((byte) 3, s[2][c]) ^ s[3][c];
            t[2] = s[0][c] ^ s[1][c] ^ dot((byte) 2, s[2][c]) ^ dot((byte) 3, s[3][c]);
            t[3] = dot((byte) 3, s[0][c]) ^ s[1][c] ^ s[2][c] ^ dot((byte) 2, s[3][c]);

            s[0][c] = (byte) t[0];
            s[1][c] = (byte) t[1];
            s[2][c] = (byte) t[2];
            s[3][c] = (byte) t[3];
        }
    }

    public static void inv_mix_columns(byte[][] s) {
        int[] t = new int[4];
        for (int c = 0; c < 4; c++) {
            t[0] = dot((byte) 0x0e, s[0][c]) ^ dot((byte) 0x0b, s[1][c]) ^
                    dot((byte) 0x0d, s[2][c]) ^ dot((byte) 0x09, s[3][c]);
            t[1] = dot((byte) 0x09, s[0][c]) ^ dot((byte) 0x0e, s[1][c]) ^
                    dot((byte) 0x0b, s[2][c]) ^ dot((byte) 0x0d, s[3][c]);
            t[2] = dot((byte) 0x0d, s[0][c]) ^ dot((byte) 0x09, s[1][c]) ^
                    dot((byte) 0x0e, s[2][c]) ^ dot((byte) 0x0b, s[3][c]);
            t[3] = dot((byte) 0x0b, s[0][c]) ^ dot((byte) 0x0d, s[1][c]) ^
                    dot((byte) 0x09, s[2][c]) ^ dot((byte) 0x0e, s[3][c]);
            s[0][c] = (byte) t[0];
            s[1][c] = (byte) t[1];
            s[2][c] = (byte) t[2];
            s[3][c] = (byte) t[3];
        }
    }
    // endregion

    // region convert
    public static byte[][] from_array_to_matrix(byte[] input_block) {
        byte[][] state = new byte[4][4];

        for (int i = 0; i < 16; i++) {
            int c = i / 4;
            int r = i % 4;
            state[r][c] = input_block[i];
        }
        return state;
    }

    public static byte[] from_matrix_to_array(byte[][] state) {
        byte[] output_block = new byte[16];

        for (int i = 0; i < 16; i++) {
            int c = i / 4;
            int r = i % 4;
            output_block[i] = state[r][c];
        }
        return output_block;
    }
    // endregion

    public static void print(byte[][] matrix) {
        StringBuilder sb = new StringBuilder();
        Formatter fm = new Formatter(sb);

        for (int i=0;i<4;i++) {
            for (int j=0;j<4;j++) {
                fm.format("%02X ", (matrix[i][j] & 0xFF));
            }
            fm.format("%n");
        }
        System.out.println(sb);
    }
}
