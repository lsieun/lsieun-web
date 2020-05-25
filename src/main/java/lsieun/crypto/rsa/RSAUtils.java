package lsieun.crypto.rsa;

import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.util.Arrays;

public class RSAUtils {
    public static byte[] rsa_encrypt(byte[] input, RSAKey rsa_key) {
        int modulus_length = BigUtils.toByteSize(rsa_key.modulus);
        byte[] padded_block = new byte[modulus_length];

        int index = 0;
        int remaining = input.length;

        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        while (remaining > 0) {
            // 第一步：添加header和padding
            int block_size = Math.min(remaining, modulus_length - 11);

            Arrays.fill(padded_block, (byte) 0);
            System.arraycopy(input, index, padded_block, modulus_length - block_size, block_size);
            padded_block[1] = 0x02; // set block type

            int first_zero_index = modulus_length - block_size - 1;
            for (int i = 2; i < first_zero_index; i++) {
                // TODO make these random
                padded_block[i] = (byte) i;
            }

            // 第二步：核心计算
            BigInteger m = new BigInteger(1, padded_block);
            BigInteger c = m.modPow(rsa_key.exponent, rsa_key.modulus);
            byte[] bytes = BigUtils.toByteArray(c);

            // 第三步：保存临时结果
            Arrays.fill(padded_block, (byte) 0);
            copy_to_right(padded_block, bytes, bytes.length);
            for (byte b : padded_block) {
                bao.write(b);
            }

            index += block_size;
            remaining -= block_size;
        }

        return bao.toByteArray();
    }

    public static byte[] rsa_decrypt(byte[] input, RSAKey rsa_key) {
        int modulus_length = BigUtils.toByteSize(rsa_key.modulus);
        byte[] padded_block = new byte[modulus_length];

        int length = input.length;
        int remainder = length % modulus_length;
        if (remainder != 0) {
            throw new RuntimeException("Error - input must be an even multiple of key modulus");
        }

        int index = 0;
        int remaining = input.length;

        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        while (remaining > 0) {
            // 第一步：准备数据
            System.arraycopy(input, index, padded_block, 0, modulus_length);

            // 第二步：核心计算
            BigInteger c = new BigInteger(1, padded_block);
            BigInteger m = c.modPow(rsa_key.exponent, rsa_key.modulus);
            byte[] bytes = BigUtils.toByteArray(m);

            // 第三步：保存临时结果
            Arrays.fill(padded_block, (byte) 0);
            copy_to_right(padded_block, bytes, bytes.length);

            if (padded_block[1] > 0x02) {
                throw new RuntimeException("Decryption error or unrecognized block type: " + padded_block[1]);
            }
            int i = 2;
            for (; padded_block[i] != 0; i++){
                // skip padding bytes
            }
            i++; // skip the first zero byte
            for (; i < modulus_length; i++) {
                bao.write(padded_block[i]);
            }

            index += modulus_length;
            remaining -= modulus_length;
        }

        return bao.toByteArray();
    }

    public static void copy_to_right(byte[] dest_bytes, byte[] src_bytes, int count) {
        int dest_length = dest_bytes.length;
        int src_length = src_bytes.length;

        for (int i = 1; i <= count; i++) {
            dest_bytes[dest_length - i] = src_bytes[src_length - i];
        }
    }
}
