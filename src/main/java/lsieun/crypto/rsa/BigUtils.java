package lsieun.crypto.rsa;

import java.math.BigInteger;

public class BigUtils {
    public static byte[] toByteArray(BigInteger val) {
        byte[] bytes = val.toByteArray();

        int length = val.bitLength();
        int quotient = length / 8;
        int remainder = length % 8;

        if (remainder == 0) {
            byte[] reduced_bytes = new byte[quotient];
            System.arraycopy(bytes, 1, reduced_bytes, 0, bytes.length - 1);
            return reduced_bytes;
        }
        return bytes;
    }

    public static int toByteSize(BigInteger val) {
        int length = val.bitLength();
        int quotient = length / 8;
        int remainder = length % 8;

        return remainder == 0 ? quotient : quotient + 1;
    }
}
