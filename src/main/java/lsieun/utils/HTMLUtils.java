package lsieun.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HTMLUtils {
    private static final String TEMPLATE_PATH = HTMLUtils.class.getClassLoader().getResource("static/archive/template/").getPath();
    private static final String SRC_PATH = HTMLUtils.class.getClassLoader().getResource("static/archive/content/").getPath();
    private static final String DEST_PATH = HTMLUtils.class.getClassLoader().getResource("static/docs/").getPath();

    public static String getContentHead() throws IOException {
        String filepath = TEMPLATE_PATH + "content_head.html";
        return FileUtils.readHtml(filepath);
    }

    public static String getContentBody() throws IOException {
        String filepath = TEMPLATE_PATH + "content_body.html";
        return FileUtils.readHtml(filepath);
    }

    public static String getNavBar() throws IOException {
        String filepath = TEMPLATE_PATH + "navbar.html";
        return FileUtils.readHtml(filepath);
    }

    public static String getTitle(String content) {
        String reg = "<h5 .+><span .+>(.+)</span></h5>";
        Pattern p = Pattern.compile(reg);
        Matcher matcher = p.matcher(content);

        if(matcher.find()){
            String title = matcher.group(1);
            return title;
        }

        return "峰峦如聚 晨光熹微";
    }

    public static void generateContentHtml(String relative_path) {
        try {
            String from_path = SRC_PATH + relative_path;
            String to_path = DEST_PATH + relative_path;

            String head = getContentHead();
            String body = getContentBody();
            String nav_bar = getNavBar();

            String content = FileUtils.readHtml(from_path);
            String title = getTitle(content);

            String html = head.replace("__TITLE__", title) +
                    body.replace("__NAVIGATION_BAR__", nav_bar)
                    .replace("__PAGE_CONTENT__", content);

            FileUtils.writeHtml(html, to_path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getAllFiles(File dir, List<File> fileList) {
        File[] files = dir.listFiles();
        if (files == null || files.length < 1) return;

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

    public static void generateStaticHTML() {
        File dir = new File(SRC_PATH);
        List<File> fileList = new ArrayList<>();
        getAllFiles(dir, fileList);

        for (File f : fileList) {
            String relative_path = f.getPath().substring(SRC_PATH.length());
            HTMLUtils.generateContentHtml(relative_path);
        }
    }

    public static void main(String[] args) throws IOException {
        generateStaticHTML();
    }
}
