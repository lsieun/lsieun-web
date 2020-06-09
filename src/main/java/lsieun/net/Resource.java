package lsieun.net;

import lsieun.utils.StringUtils;

public class Resource {
    private static final int ETAG_BYTES = 4;

    public static String calculateEtag(byte[] bytes) {
        byte[] result_bytes = new byte[ETAG_BYTES];
        for (int i = 0; i < bytes.length; i++) {
            byte b = bytes[i];
            int remainder = i % ETAG_BYTES;
            result_bytes[remainder] = (byte) (result_bytes[remainder] ^ b);
        }
        return StringUtils.toHex(result_bytes);
    }
}
