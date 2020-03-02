package lsieun.utils;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HTMLUtils {
    private static final String TEMPLATE_PATH = HTMLUtils.class.getClassLoader().getResource("static/archive/template/").getPath();
    private static final String ROOT_PATH = HTMLUtils.class.getClassLoader().getResource("static/archive/content/").getPath();
    private static final String TARGET_PATH = HTMLUtils.class.getClassLoader().getResource("static/docs/").getPath();

    public static String getHead(String title) {
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append("");
        sb.append("");
        sb.append("");
        sb.append("");
        sb.append("");
        return sb.toString();
    }

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
            String from_path = ROOT_PATH + relative_path;
            String to_path = TARGET_PATH + relative_path;

            String head = getContentHead();
            String body = getContentBody();
            String nav_bar = getNavBar();

            String content = FileUtils.readHtml(from_path);
            String title = getTitle(content);

            String html = head.replace("__TITLE__", title) +
                    body.replace("__NAVIGATION_BAR__", nav_bar)
                    .replace("__PAGE_CONTENT__", content);

            FileUtils.writeHtml(html, to_path);
            System.out.println("file://" + to_path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        String filepath = TARGET_PATH + "life/reason_season_or_lifetime.html";
        String content = FileUtils.readHtml(filepath);
        String title = getTitle(content);
        System.out.println(title);
    }
}
