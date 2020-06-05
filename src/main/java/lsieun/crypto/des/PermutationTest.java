package lsieun.crypto.des;

import lsieun.utils.ByteUtils;

public class PermutationTest {
    public static void main(String[] args) {
        byte[] content_64_bit_bytes = {'c', 'a', 'f', 'e', 'b', 'a', 'b', 'e'};
        byte[] ip_bytes = PermutationUtils.permute(content_64_bit_bytes, DESConst.ip_table);
        byte[] fp_bytes = PermutationUtils.permute(ip_bytes, DESConst.fp_table);

        System.out.println(ByteUtils.toHex(content_64_bit_bytes));
        System.out.println(ByteUtils.toHex(ip_bytes));
        System.out.println(ByteUtils.toHex(fp_bytes));
    }
}
