package lsieun.crypto.dsa_ecc;

import lsieun.crypto.ecc.Point;

import java.math.BigInteger;

public class EllipticCurve {
    public final BigInteger p;
    public final BigInteger a;
    public final BigInteger b;
    public final Point G;
    public final BigInteger n;

    public EllipticCurve(BigInteger p, BigInteger a, BigInteger b, Point g, BigInteger n) {
        this.p = p;
        this.a = a;
        this.b = b;
        this.G = g;
        this.n = n;
    }
}
