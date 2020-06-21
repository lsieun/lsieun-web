package lsieun.crypto.dsa;

import lsieun.crypto.sha256.SHA256Utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

public class DsaTest {
    public static final byte[] G = {
            (byte) 0x7d, (byte) 0xcd, (byte) 0x66, (byte) 0x81,
            (byte) 0x61, (byte) 0x52, (byte) 0x21, (byte) 0x10,
            (byte) 0xf7, (byte) 0xa0, (byte) 0x83, (byte) 0x4c,
            (byte) 0x5f, (byte) 0xc8, (byte) 0x84, (byte) 0xca,
            (byte) 0xe8, (byte) 0x8a, (byte) 0x9b, (byte) 0x9f,
            (byte) 0x19, (byte) 0x14, (byte) 0x8c, (byte) 0x7d,
            (byte) 0xd0, (byte) 0xee, (byte) 0x33, (byte) 0xce,
            (byte) 0xb4, (byte) 0x57, (byte) 0x2d, (byte) 0x5e,
            (byte) 0x78, (byte) 0x3f, (byte) 0x06, (byte) 0xd7,
            (byte) 0xb3, (byte) 0xd6, (byte) 0x40, (byte) 0x70,
            (byte) 0x2e, (byte) 0xb6, (byte) 0x12, (byte) 0x3f,
            (byte) 0x4a, (byte) 0x61, (byte) 0x38, (byte) 0xae,
            (byte) 0x72, (byte) 0x12, (byte) 0xfb, (byte) 0x77,
            (byte) 0xde, (byte) 0x53, (byte) 0xb3, (byte) 0xa1,
            (byte) 0x99, (byte) 0xd8, (byte) 0xa8, (byte) 0x19,
            (byte) 0x96, (byte) 0xf7, (byte) 0x7f, (byte) 0x99
    };

    public static final byte[] P = {
            (byte) 0x00, (byte) 0x9c, (byte) 0x4c, (byte) 0xaa,
            (byte) 0x76, (byte) 0x31, (byte) 0x2e, (byte) 0x71,
            (byte) 0x4d, (byte) 0x31, (byte) 0xd6, (byte) 0xe4,
            (byte) 0xd7, (byte) 0xe9, (byte) 0xa7, (byte) 0x29,
            (byte) 0x7b, (byte) 0x7f, (byte) 0x05, (byte) 0xee,
            (byte) 0xfd, (byte) 0xca, (byte) 0x35, (byte) 0x14,
            (byte) 0x1e, (byte) 0x9f, (byte) 0xe5, (byte) 0xc0,
            (byte) 0x2a, (byte) 0xe0, (byte) 0x12, (byte) 0xd9,
            (byte) 0xc4, (byte) 0xc0, (byte) 0xde, (byte) 0xcc,
            (byte) 0x66, (byte) 0x96, (byte) 0x2f, (byte) 0xf1,
            (byte) 0x8f, (byte) 0x1a, (byte) 0xe1, (byte) 0xe8,
            (byte) 0xbf, (byte) 0xc2, (byte) 0x29, (byte) 0x0d,
            (byte) 0x27, (byte) 0x07, (byte) 0x48, (byte) 0xb9,
            (byte) 0x71, (byte) 0x04, (byte) 0xec, (byte) 0xc7,
            (byte) 0xf4, (byte) 0x16, (byte) 0x2e, (byte) 0x50,
            (byte) 0x8d, (byte) 0x67, (byte) 0x14, (byte) 0x84,
            (byte) 0x7b
    };

    public static final byte[] Q = {
            (byte) 0x00, (byte) 0xac, (byte) 0x6f, (byte) 0xc1,
            (byte) 0x37, (byte) 0xef, (byte) 0x16, (byte) 0x74,
            (byte) 0x52, (byte) 0x6a, (byte) 0xeb, (byte) 0xc5,
            (byte) 0xf8, (byte) 0xf2, (byte) 0x1f, (byte) 0x53,
            (byte) 0xf4, (byte) 0x0f, (byte) 0xe0, (byte) 0x51,
            (byte) 0x5f
    };

    public static final byte[] private_key = {
            (byte) 0x53, (byte) 0x61, (byte) 0xae, (byte) 0x4f,
            (byte) 0x6f, (byte) 0x25, (byte) 0x98, (byte) 0xde,
            (byte) 0xc4, (byte) 0xbf, (byte) 0x0b, (byte) 0xbe,
            (byte) 0x09, (byte) 0x5f, (byte) 0xdf, (byte) 0x90,
            (byte) 0x2f, (byte) 0x4c, (byte) 0x8e, (byte) 0x09
    };

    public static final byte[] public_key = {
            (byte) 0x1b, (byte) 0x91, (byte) 0x4c, (byte) 0xa9,
            (byte) 0x73, (byte) 0xdc, (byte) 0x06, (byte) 0x0d,
            (byte) 0x21, (byte) 0xc6, (byte) 0xff, (byte) 0xab,
            (byte) 0xf6, (byte) 0xad, (byte) 0xf4, (byte) 0x11,
            (byte) 0x97, (byte) 0xaf, (byte) 0x23, (byte) 0x48,
            (byte) 0x50, (byte) 0xa8, (byte) 0xf3, (byte) 0xdb,
            (byte) 0x2e, (byte) 0xe6, (byte) 0x27, (byte) 0x8c,
            (byte) 0x40, (byte) 0x4c, (byte) 0xb3, (byte) 0xc8,
            (byte) 0xfe, (byte) 0x79, (byte) 0x7e, (byte) 0x89,
            (byte) 0x48, (byte) 0x90, (byte) 0x27, (byte) 0x92,
            (byte) 0x6f, (byte) 0x5b, (byte) 0xc5, (byte) 0xe6,
            (byte) 0x8f, (byte) 0x91, (byte) 0x4c, (byte) 0xe9,
            (byte) 0x4f, (byte) 0xed, (byte) 0x0d, (byte) 0x3c,
            (byte) 0x17, (byte) 0x09, (byte) 0xeb, (byte) 0x97,
            (byte) 0xac, (byte) 0x29, (byte) 0x77, (byte) 0xd5,
            (byte) 0x19, (byte) 0xe7, (byte) 0x4d, (byte) 0x17
    };


    public static void main(String[] args) {
        String msg = "the quick brown fox jumps over a lazy dog";
        byte[] input = msg.getBytes(StandardCharsets.UTF_8);
        byte[] hash_bytes = SHA256Utils.sha256_hash(input);


        BigInteger g = new BigInteger(1, G);
        BigInteger p = new BigInteger(1, P);
        BigInteger q = new BigInteger(1, Q);

        DsaParams params = new DsaParams(g, p, q);

        BigInteger x = new BigInteger(1, private_key);
        BigInteger y = new BigInteger(1, public_key);

        DsaSignature signature = DsaUtils.dsa_sign(params, x, hash_bytes);

        System.out.println(signature.r);
        System.out.println(signature.s);

        boolean flag = DsaUtils.dsa_verify(params, y, hash_bytes, signature);
        System.out.println(flag);
    }

}
