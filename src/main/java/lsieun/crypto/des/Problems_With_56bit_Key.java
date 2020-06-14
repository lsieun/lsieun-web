package lsieun.crypto.des;

import lsieun.crypto.utils.CipherType;
import lsieun.crypto.utils.ByteUtils;

public class Problems_With_56bit_Key {
    public static void main(String[] args) {
        byte[] msg_64_bit_bytes = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};

        // 两个密码的最后1-bit数据不同
        byte[] key_64_bit_bytes1 = {'p', 'a', 's', 's', 'w', 'o', 'r', 'd'};
        byte[] key_64_bit_bytes2 = {'q', '`', 'r', 'r', 'v', 'n', 's', 'e'};

        // 注意：使用key1进行加密，使用了key2进行解密
        byte[] encrypted_bytes = DESUtils.des_block_operate(msg_64_bit_bytes, key_64_bit_bytes1, CipherType.ENCRYPT);
        byte[] decrypted_bytes = DESUtils.des_block_operate(encrypted_bytes, key_64_bit_bytes2, CipherType.DECRYPT);

        // 验证：由key1加密的数据，可以由key2进行解密
        System.out.println("          message: " + ByteUtils.toHex(msg_64_bit_bytes));
        System.out.println("             key1: " + ByteUtils.toHex(key_64_bit_bytes1));
        System.out.println("             key2: " + ByteUtils.toHex(key_64_bit_bytes2));
        System.out.println("encrypted message: " + ByteUtils.toHex(encrypted_bytes));
        System.out.println("decrypted message: " + ByteUtils.toHex(decrypted_bytes));

    }
}
