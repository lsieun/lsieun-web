package lsieun.crypto.dsa_ecc;

import lsieun.crypto.dsa.DsaSignature;
import lsieun.crypto.ecc.ECCUtils;
import lsieun.crypto.ecc.Point;

import java.math.BigInteger;
import java.util.Arrays;

public class ECDSAUtils {
    public static DsaSignature ecdsa_sign(EllipticCurve params,
                                          BigInteger private_key,
                                          byte[] hash_bytes) {
        // This should be a random number between 0 and n-1
        BigInteger k = new BigInteger(1, ECDSASample.K);

        Point X = ECCUtils.multiply_point(params.G, k, params.a, params.p);
        BigInteger r = X.x.mod(params.n);

        // z is the L_n leftmost bits of hash - cannot be longer than n
        int nBytes = params.n.bitLength() / 8;
        if (nBytes < hash_bytes.length) {
            hash_bytes = Arrays.copyOfRange(hash_bytes, 0, nBytes);
        }
        BigInteger z = new BigInteger(1, hash_bytes);

        // s = k^-1 ( z + r d_a ) % n
        BigInteger inv_k = k.modInverse(params.n);
        BigInteger s = private_key.multiply(r).add(z).multiply(inv_k).mod(params.n);

        DsaSignature signature = new DsaSignature(r, s);
        return signature;
    }

    public static boolean ecdsa_verify(EllipticCurve params,
                                       Point public_key,
                                       byte[] hash_bytes,
                                       DsaSignature signature) {

        BigInteger r = signature.r;
        BigInteger s = signature.s;

        // w = s^-1 % n
        BigInteger w = s.modInverse(params.n);

        // z is the L_n leftmost bits of hash - cannot be longer than n
        int nBytes = params.n.bitLength() / 8;
        if (nBytes < hash_bytes.length) {
            hash_bytes = Arrays.copyOfRange(hash_bytes, 0, nBytes);
        }
        BigInteger z = new BigInteger(1, hash_bytes);

        // u1 = zw % n
        BigInteger u1 = z.multiply(w).mod(params.n);

        // u2 = (rw) % q
        BigInteger u2 = r.multiply(w).mod(params.n);

        // (x1,y1) = u1 * G + u2 * Q
        Point G = params.G;
        Point Q = new Point(public_key.x, public_key.y);

        Point p1 = ECCUtils.multiply_point(G, u1, params.a, params.p);
        Point p2 = ECCUtils.multiply_point(Q, u2, params.a, params.p);
        Point p3 = ECCUtils.add_points(p1, p2, params.p);

        // r = x1 % n
        BigInteger v = p3.x.mod(params.n);
        return r.equals(v);
    }
}
