package lsieun.crypto.dh_ecc;

import lsieun.crypto.ecc.Point;

import java.math.BigInteger;

/**
 * Describe y^2 = (x^3 + ax + b) % p
 */
public class DomainParameters {
    public BigInteger p;
    public BigInteger a;
    public BigInteger b;
    public Point G; // base point
}
