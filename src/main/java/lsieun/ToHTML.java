package lsieun;

import lsieun.utils.HTMLUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ToHTML {
    public static void getAllFiles(File dir, List<File> fileList) {
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                getAllFiles(file, fileList);
            } else {
                if (file.getName().endsWith(".html")) {
                    fileList.add(file);
                }
            }
        }
    }

    public static void main(String[] args) {
        String root_path = HTMLUtils.class.getClassLoader().getResource("static/archive/content/").getPath();
        File dir = new File(root_path);
        List<File> fileList = new ArrayList<>();
        getAllFiles(dir, fileList);

        for (File f : fileList) {
            String relative_path = f.getPath().substring(root_path.length());
            HTMLUtils.generateContentHtml(relative_path);
        }
    }
}
