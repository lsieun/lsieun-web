package lsieun.crypto.dh_ecc;

import lsieun.crypto.ecc.Point;

import java.math.BigInteger;

public class DHECCTest {
    public static void main(String[] args) {
        DomainParameters T = new DomainParameters();
        T.a = BigInteger.valueOf(1);
        T.b = BigInteger.valueOf(1);
        T.p = BigInteger.valueOf(23);
        T.G = new Point(BigInteger.valueOf(5), BigInteger.valueOf(19));

        KeyPair A = new KeyPair();
        A.private_key = BigInteger.valueOf(4);
        A.public_key = DHECCUtils.dh_agree(T, A.private_key);

        KeyPair B = new KeyPair();
        B.private_key = BigInteger.valueOf(2);
        B.public_key = DHECCUtils.dh_agree(T, B.private_key);

        Point Za = DHECCUtils.dh_finalize(T, B.public_key, A.private_key);
        System.out.println(Za); // Point {x=5, y=19}

        Point Zb = DHECCUtils.dh_finalize(T, A.public_key, B.private_key);
        System.out.println(Zb); // Point {x=5, y=19}
    }
}
