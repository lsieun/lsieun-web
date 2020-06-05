package lsieun.crypto.des;

public class PermutationUtils {
    /**
     * This does not return a 1 for a 1 bit; it just returns non-zero
     */
    public static int get_bit(byte[] array, int bit) {
        return (array[bit / 8] & 0xFF) & (0x80 >> (bit % 8));
    }

    public static void set_bit(byte[] array, int bit) {
        int val = (array[bit / 8] & 0xFF) | (0x80 >> (bit % 8));
        array[bit / 8] = (byte) val;
    }

    public static void clear_bit(byte[] array, int bit) {
        int val = (array[bit / 8] & 0xFF) & ~(0x80 >> (bit % 8));
        array[bit / 8] = (byte) val;
    }

    /**
     * <p>Implement the permutation functions.</p>
     * NOTE: this assumes that the permutation tables are defined as one-based
     * rather than 0-based arrays, since they’re given that way in the
     * specification.
     */
    public static byte[] permute(byte[] src, int[] permute_table) {
        int bit_size = permute_table.length;
        int byte_size = bit_size / 8;
        byte[] target = new byte[byte_size];

        for (int i = 0; i < bit_size; i++) {
            int pos = permute_table[i] - 1;

            if (get_bit(src, pos) == 0) {
                clear_bit(target, i);
            } else {
                set_bit(target, i);
            }
        }
        return target;
    }
}
