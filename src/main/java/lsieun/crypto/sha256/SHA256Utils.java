package lsieun.crypto.sha256;

import lsieun.crypto.utils.ByteUtils;

import java.util.Arrays;

public class SHA256Utils {
    public static byte[] sha256_padding(long input_length) {
        long length_in_bits = input_length * 8;
        int remainder = (int) (input_length % SHA256Const.SHA256_BLOCK_SIZE);

        int size = (remainder >= SHA256Const.SHA256_PADDING_THRESHOLD) ?
                (2 * SHA256Const.SHA256_BLOCK_SIZE - remainder) :
                (SHA256Const.SHA256_BLOCK_SIZE - remainder);


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

    public static byte[] sha256_hash(byte[] input, int input_length) {
        // (1) 初始化SHA256的Hash值
        int[] hash = Arrays.copyOf(SHA256Const.SHA256_INITIAL_HASH, SHA256Const.SHA256_RESULT_SIZE);

        // (2) 对input进行padding
        byte[] padding_bytes = sha256_padding(input_length);
        byte[] all_bytes = ByteUtils.concatenate(input, padding_bytes);

        // (3) 对于512-bit(64-byte)的数据进行操作
        byte[] input_block = new byte[SHA256Const.SHA256_BLOCK_SIZE];
        int quotient = all_bytes.length / SHA256Const.SHA256_BLOCK_SIZE;

        for (int i = 0; i < quotient; i++) {
            Arrays.fill(input_block, (byte) 0);
            System.arraycopy(all_bytes, i * SHA256Const.SHA256_BLOCK_SIZE, input_block, 0, SHA256Const.SHA256_BLOCK_SIZE);
            sha256_block_operate(input_block, hash);
        }

        // (4) 返回结果
        return encode(hash);
    }

    public static byte[] encode(int[] hash) {
        byte[] bytes = new byte[SHA256Const.SHA256_OUTPUT_SIZE];

        for (int i = 0; i < SHA256Const.SHA256_OUTPUT_SIZE; i+=4) {
            int quotient = i / 4;
            bytes[i + 0] = (byte) ((hash[quotient] >>> 24) & 0xFF);
            bytes[i + 1] = (byte) ((hash[quotient] >>> 16) & 0xFF);
            bytes[i + 2] = (byte) ((hash[quotient] >>> 8) & 0xFF);
            bytes[i + 3] = (byte) (hash[quotient] & 0xFF);
        }

        return bytes;
    }

    public static void sha256_block_operate(byte[] input_block, int[] hash) {
        int[] W = new int[64];
        for (int t = 0; t < 64; t++) {
            if (t < 16) {
                W[t] = ((input_block[(t * 4)] & 0xFF) << 24) |
                        ((input_block[(t * 4) + 1] & 0xFF) << 16) |
                        ((input_block[(t * 4) + 2] & 0xFF) << 8) |
                        (input_block[(t * 4) + 3] & 0xFF);
            } else {
                W[t] = sigma_shr(W[t - 2], 1) +
                        W[t - 7] +
                        sigma_shr(W[t - 15], 0) +
                        W[t - 16];
            }
        }

        int a = hash[0];
        int b = hash[1];
        int c = hash[2];
        int d = hash[3];
        int e = hash[4];
        int f = hash[5];
        int g = hash[6];
        int h = hash[7];

        for (int t = 0; t < 64; t++) {
            int temp1 = h + sigma_rot(e, 1) + ch(e, f, g) + SHA256Const.k[t] + W[t];
            int temp2 = sigma_rot(a, 0) + maj(a, b, c);

            h = g;
            g = f;
            f = e;
            e = d + temp1;
            d = c;
            c = b;
            b = a;
            a = temp1 + temp2;

        }

        hash[0] += a;
        hash[1] += b;
        hash[2] += c;
        hash[3] += d;
        hash[4] += e;
        hash[5] += f;
        hash[6] += g;
        hash[7] += h;
    }

    public static int ch(int x, int y, int z) {
        return (x & y) ^ (~x & z);
    }

    public static int maj(int x, int y, int z) {
        return (x & y) ^ (x & z) ^ (y & z);
    }

    public static int rotr(int x, int n) {
        return (x >>> n) | ((x) << (32 - n));
    }

    public static int shr(int x, int n) {
        return x >>> n;
    }

    public static int sigma_rot(int x, int i) {
        return rotr(x, i != 0 ? 6 : 2) ^ rotr(x, i != 0 ? 11 : 13) ^ rotr(x, i != 0 ? 25 : 22);
    }

    public static int sigma_shr(int x, int i) {
        return rotr(x, i != 0 ? 17 : 7) ^ rotr(x, i != 0 ? 19 : 18) ^ shr(x, i != 0 ? 10 : 3);
    }
}
