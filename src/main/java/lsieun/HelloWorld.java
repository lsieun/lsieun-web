package lsieun;

import lsieun.utils.FileUtils;

import java.io.File;

public class HelloWorld {
    public static void main(String[] args) {
        String filepath = "/root/liusen/abc.txt";
        final String ext = FileUtils.getFileExtension(new File(filepath));
        System.out.println(ext);
    }
}
