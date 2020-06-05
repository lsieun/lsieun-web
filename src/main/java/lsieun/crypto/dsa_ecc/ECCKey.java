package lsieun.crypto.dsa_ecc;

import lsieun.crypto.ecc.Point;

import java.math.BigInteger;

public class ECCKey {
    public BigInteger d; // random integer < n; this is the private key
    public Point Q; // Q = d * G; this is the public key
}
