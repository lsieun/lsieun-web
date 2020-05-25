package lsieun.crypto.rsa;

import java.math.BigInteger;

public class RSAMath {
    public static BigInteger rsa_compute(BigInteger m, RSAKey rsa_key) {
        return m.modPow(rsa_key.exponent, rsa_key.modulus);
    }

    public static void main(String[] args) {
        BigInteger e = new BigInteger("79");
        BigInteger d = new BigInteger("1019");
        BigInteger n = new BigInteger("3337");

        RSAKey public_key = new RSAKey(n, e);
        RSAKey private_key = new RSAKey(n, d);

        BigInteger m = new BigInteger("688");
        BigInteger c = rsa_compute(m, public_key);
        System.out.println(c); // 1570

        BigInteger m2 = rsa_compute(c, private_key);
        System.out.println(m2); // 688
    }
}
