package lsieun;

import lsieun.utils.FileUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class HelloWorld {
    public static void main(String[] args) {
        String filepath = "/root/liusen/abc.txt";
        final String ext = FileUtils.getFileExtension(new File(filepath));
        System.out.println(ext);
    }
}
