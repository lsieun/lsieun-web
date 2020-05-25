package lsieun.crypto.hmac;

@FunctionalInterface
public interface HashAlgorithm {
    byte[] apply(byte[] input, int len);
}
