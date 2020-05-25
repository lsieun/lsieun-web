package lsieun.crypto.dsa;

import java.math.BigInteger;

public class DsaSignature {
    public BigInteger r;
    public BigInteger s;

    public DsaSignature(BigInteger r, BigInteger s) {
        this.r = r;
        this.s = s;
    }
}
