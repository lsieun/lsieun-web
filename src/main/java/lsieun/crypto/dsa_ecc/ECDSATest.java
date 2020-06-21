package lsieun.crypto.dsa_ecc;

import lsieun.crypto.dsa.DsaSignature;
import lsieun.crypto.ecc.ECCUtils;
import lsieun.crypto.ecc.Point;
import lsieun.crypto.sha256.SHA256Utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

public class ECDSATest {
    public static void main(String[] args) {
        BigInteger p = new BigInteger(1, ECDSASample.P);
        BigInteger a = new BigInteger("-3");
        BigInteger b = new BigInteger(1, ECDSASample.b);
        BigInteger q = new BigInteger(1, ECDSASample.q);
        BigInteger gx = new BigInteger(1, ECDSASample.gx);
        BigInteger gy = new BigInteger(1, ECDSASample.gy);

        Point G = new Point(gx, gy);
        EllipticCurve curve = new EllipticCurve(p, a, b, G, q);

        // Generate new public key from private key “w” and point “G”
        ECCKey ecc_key = new ECCKey();
        ecc_key.d = new BigInteger(1, ECDSASample.w);
        ecc_key.Q = ECCUtils.multiply_point(G, ecc_key.d, a, p);


        String msg = "abc";
        byte[] input = msg.getBytes(StandardCharsets.UTF_8);
        byte[] hash_bytes = SHA256Utils.sha256_hash(input);

        DsaSignature signature = ECDSAUtils.ecdsa_sign(curve, ecc_key.d, hash_bytes);
        System.out.println("R: " + signature.r.toString(16));
        System.out.println("S: " + signature.s.toString(16));

        boolean flag = ECDSAUtils.ecdsa_verify(curve, ecc_key.Q, hash_bytes, signature);
        if (flag) {
            System.out.println("Signature verified.");
        } else {
            System.out.println("Signature Not verified.");
        }
    }
}
