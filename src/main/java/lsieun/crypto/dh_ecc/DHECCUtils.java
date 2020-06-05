package lsieun.crypto.dh_ecc;

import lsieun.crypto.ecc.ECCUtils;
import lsieun.crypto.ecc.Point;

import java.math.BigInteger;

public class DHECCUtils {
    public static Point dh_agree(DomainParameters domain_parameters,
                                 BigInteger private_key) {
        Point G = domain_parameters.G;
        BigInteger a = domain_parameters.a;
        BigInteger p = domain_parameters.p;

        return ECCUtils.multiply_point(G, private_key, a, p);
    }

    public static Point dh_finalize(DomainParameters domain_parameters,
                                    Point public_key, BigInteger private_key) {
        BigInteger a = domain_parameters.a;
        BigInteger p = domain_parameters.p;

        return ECCUtils.multiply_point(public_key, private_key, a, p);
    }
}
