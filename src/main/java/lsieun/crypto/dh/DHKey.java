package lsieun.crypto.dh;

import java.math.BigInteger;

public class DHKey {
    public final BigInteger g;
    public final BigInteger p;

    public DHKey(BigInteger g, BigInteger p) {
        this.g = g;
        this.p = p;
    }
}
