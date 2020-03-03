package lsieun.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.GZIPOutputStream;

import static lsieun.utils.LogUtils.err;

public class CompressUtils {
    public static byte[] gzip_compress(byte[] bytes) {
        if (bytes == null) return null;

        try {
            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            GZIPOutputStream out = new GZIPOutputStream(bao);
            out.write(bytes);
            out.flush();
            out.close();
            return bao.toByteArray();
        } catch (IOException ex) {
            err.log(Level.SEVERE, "unexpected error: " + ex.getMessage(), ex);
        }
        return null;
    }

    public static byte[] deflate_compress(byte[] bytes) {
        if (bytes == null) return null;

        try {
            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            DeflaterOutputStream out = new DeflaterOutputStream(bao);
            out.write(bytes);
            out.flush();
            out.close();
            return bao.toByteArray();
        } catch (IOException ex) {
            err.log(Level.SEVERE, "unexpected error: " + ex.getMessage(), ex);
        }
        return null;
    }
}
