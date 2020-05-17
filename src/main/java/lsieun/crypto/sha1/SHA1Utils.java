package lsieun.crypto.sha1;

import java.util.Arrays;

public class SHA1Utils {
    public static byte[] sha1_padding(long input_length) {
        long length_in_bits = input_length * 8;
        int remainder = (int) (input_length % SHA1Const.SHA1_BLOCK_SIZE);

        int size = (remainder >= SHA1Const.SHA1_PADDING_THRESHOLD) ?
                (2 * SHA1Const.SHA1_BLOCK_SIZE - remainder) :
                (SHA1Const.SHA1_BLOCK_SIZE - remainder);


        byte[] padding_bytes = new byte[size];
        // (1) 设置第1个bit为1
        padding_bytes[0] = (byte) 0x80;

        // (2) 中间的bit默认为0,不需要设置

        // (3)设置最后64 bit为原始输入数据的长度 big-endian
        padding_bytes[size - 8] = (byte) ((length_in_bits >>> 56) & 0xFF);
        padding_bytes[size - 7] = (byte) ((length_in_bits >>> 48) & 0xFF);
        padding_bytes[size - 6] = (byte) ((length_in_bits >>> 40) & 0xFF);
        padding_bytes[size - 5] = (byte) ((length_in_bits >>> 32) & 0xFF);
        padding_bytes[size - 4] = (byte) ((length_in_bits >>> 24) & 0xFF);
        padding_bytes[size - 3] = (byte) ((length_in_bits >>> 16) & 0xFF);
        padding_bytes[size - 2] = (byte) ((length_in_bits >>> 8) & 0xFF);
        padding_bytes[size - 1] = (byte) (length_in_bits & 0xFF);

        return padding_bytes;
    }

    public static byte[] sha1_hash(byte[] input, int input_length) {
        // (1) 初始化SHA1的Hash值
        int[] hash = Arrays.copyOf(SHA1Const.SHA1_INITIAL_HASH, SHA1Const.SHA1_RESULT_SIZE);

        // (2) 对input进行padding
        byte[] padding_bytes = sha1_padding(input_length);
        byte[] all_bytes = concatenate(input, padding_bytes);

        // (3) 对于512-bit(64-byte)的数据进行操作
        byte[] input_block = new byte[SHA1Const.SHA1_BLOCK_SIZE];
        int quotient = all_bytes.length / SHA1Const.SHA1_BLOCK_SIZE;

        for (int i = 0; i < quotient; i++) {
            Arrays.fill(input_block, (byte) 0);
            System.arraycopy(all_bytes, i * SHA1Const.SHA1_BLOCK_SIZE, input_block, 0, SHA1Const.SHA1_BLOCK_SIZE);
            sha1_block_operate(input_block, hash);
        }

        // (4) 返回结果
        return encode(hash);
    }

    public static byte[] concatenate(byte[] bytes1, byte[] bytes2) {
        int len1 = bytes1.length;
        int len2 = bytes2.length;

        byte[] result_bytes = new byte[len1 + len2];

        System.arraycopy(bytes1, 0, result_bytes, 0, len1);
        System.arraycopy(bytes2, 0, result_bytes, len1, len2);

        return result_bytes;
    }

    public static byte[] encode(int[] hash) {
        byte[] bytes = new byte[SHA1Const.SHA1_OUTPUT_SIZE];

        bytes[0] = (byte) ((hash[0] >>> 24) & 0xFF);
        bytes[1] = (byte) ((hash[0] >>> 16) & 0xFF);
        bytes[2] = (byte) ((hash[0] >>> 8) & 0xFF);
        bytes[3] = (byte) (hash[0] & 0xFF);

        bytes[4] = (byte) ((hash[1] >>> 24) & 0xFF);
        bytes[5] = (byte) ((hash[1] >>> 16) & 0xFF);
        bytes[6] = (byte) ((hash[1] >>> 8) & 0xFF);
        bytes[7] = (byte) (hash[1] & 0xFF);

        bytes[8] = (byte) ((hash[2] >>> 24) & 0xFF);
        bytes[9] = (byte) ((hash[2] >>> 16) & 0xFF);
        bytes[10] = (byte) ((hash[2] >>> 8) & 0xFF);
        bytes[11] = (byte) (hash[2] & 0xFF);

        bytes[12] = (byte) ((hash[3] >>> 24) & 0xFF);
        bytes[13] = (byte) ((hash[3] >>> 16) & 0xFF);
        bytes[14] = (byte) ((hash[3] >>> 8) & 0xFF);
        bytes[15] = (byte) (hash[3] & 0xFF);

        bytes[16] = (byte) ((hash[4] >>> 24) & 0xFF);
        bytes[17] = (byte) ((hash[4] >>> 16) & 0xFF);
        bytes[18] = (byte) ((hash[4] >>> 8) & 0xFF);
        bytes[19] = (byte) (hash[4] & 0xFF);

        return bytes;
    }

    // ch is functions 0 - 19
    public static int ch(int x, int y, int z) {
        return (x & y) ^ (~x & z);
    }

    // parity is functions 20 - 39 & 60 - 79
    public static int parity(int x, int y, int z) {
        return x ^ y ^ z;
    }

    // maj is functions 40 - 59
    public static int maj(int x, int y, int z) {
        return (x & y) ^ (x & z) ^ (y & z);
    }

    public static void sha1_block_operate(byte[] input_block, int[] hash) {
        int[] x = new int[16];
        for (int i = 0; i < 16; i++) {
            x[i] =  ((input_block[(i * 4) + 0] & 0xFF) << 24) |
                    ((input_block[(i * 4) + 1] & 0xFF) << 16) |
                    ((input_block[(i * 4) + 2] & 0xFF) << 8) |
                    ((input_block[(i * 4) + 3] & 0xFF) << 0);
        }

        int[] W = new int[80];
        // First 16 blocks of W are the original 16 blocks of the input
        for (int i = 0; i < 80; i++) {
            if (i < 16) {
                W[i] = x[i];
            } else {
                W[i] = W[i - 3] ^ W[i - 8] ^ W[i - 14] ^ W[i - 16];
                // Rotate left operation
                W[i] = (W[i] << 1) | ((W[i] >>> 31) & 0x01);
            }
        }

        int a = hash[0];
        int b = hash[1];
        int c = hash[2];
        int d = hash[3];
        int e = hash[4];

        for (int t = 0; t < 80; t++) {
            // NOTE: 这里我曾经遇到过个一个问题，之前我使用"a >> 27"，结果总是不对，当我换成"a >>> 27"之后，结果才正确
            // 这个问题，就很好的说明了>> 和 >>> 两者之间的区别
            int rotation = ((a << 5) | (a >>> 27));
            int constant = SHA1Const.k[(t / 20)];
            int w = W[t];

            int function_value;
            if (t <= 19) {
                function_value = ch(b, c, d);
            } else if (t <= 39) {
                function_value = parity(b, c, d);
            } else if (t <= 59) {
                function_value = maj(b, c, d);
            } else {
                function_value = parity(b, c, d);
            }

            int temp = rotation + e + constant + w + function_value;

            e = d;
            d = c;
            c = ((b << 30) | (b >>> 2));
            b = a;
            a = temp;
        }

        hash[0] += a;
        hash[1] += b;
        hash[2] += c;
        hash[3] += d;
        hash[4] += e;
    }
}
