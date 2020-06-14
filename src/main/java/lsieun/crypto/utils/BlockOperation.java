package lsieun.crypto.utils;

@FunctionalInterface
public interface BlockOperation {
    byte[] block_operate(byte[] input, byte[] key);
}
