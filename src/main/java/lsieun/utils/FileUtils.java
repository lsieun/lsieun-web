package lsieun.utils;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static lsieun.utils.LogUtils.*;

public class FileUtils {

    public static void makeFileDir(final String filepath) {
        File f = new File(filepath);
        File dirFile = f.getParentFile();
        if (dirFile.exists()) return;
        boolean flag = dirFile.mkdirs();
        if (!flag) {
            throw new RuntimeException("Cant not create dir: " + dirFile);
        }
    }

    public static void writeFile(final byte[] bytes, final String filepath) throws IOException {
        makeFileDir(filepath);
        try (
                FileOutputStream fout = new FileOutputStream(filepath);
                BufferedOutputStream out = new BufferedOutputStream(fout);
        ) {
            out.write(bytes);
            out.flush();
            audit.info("file://" + filepath);
        }
    }

    public static byte[] readFile(final String filepath) throws IOException {
        File file = new File(filepath);
        if (!file.exists()) return null;

        try (
                FileInputStream fin = new FileInputStream(file);
                BufferedInputStream in = new BufferedInputStream(fin)
        ) {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buff = new byte[Const.BUFFER_SIZE];
            for (int len = in.read(buff); len != -1; len = in.read(buff)) {
                out.write(buff, 0, len);
            }
            return out.toByteArray();
        }
    }

    public static void writeHtml(final String html, final String basic_name) throws IOException {
        byte[] bytes = html.getBytes(StandardCharsets.UTF_8);
        String filepath = getTmpFilePath(basic_name);
        writeFile(bytes, filepath);
    }

    public static String readHtml(final String basic_name) throws IOException {
        String filepath = getTmpFilePath(basic_name);
        byte[] bytes = readFile(filepath);
        return ByteUtils.toStr(bytes);
    }

    public static String getTmpFilePath(final String filename) {
        String user_dir = System.getProperty("user.dir");
        String filepath = String.format("%s/target/tmp/%s", user_dir, filename);
        return filepath;
    }


}
