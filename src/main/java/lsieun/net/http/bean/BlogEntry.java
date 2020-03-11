package lsieun.net.http.bean;

import lsieun.net.http.HttpConst;
import lsieun.utils.DateUtils;
import lsieun.utils.FileUtils;

import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.logging.Level;

import static lsieun.utils.LogUtils.audit;

public class BlogEntry {
    public final long timestamp;
    public final String title;
    public final String author;
    public final String img_url;
    public final String doc_url;
    public final String doc_intro;
    public int read_num;

    public BlogEntry(long timestamp, String title, String author, String img_url, String doc_url, String doc_intro) {
        this.timestamp = timestamp;
        this.title = title;
        this.author = author;
        this.img_url = img_url;
        this.doc_url = doc_url;
        this.doc_intro = doc_intro;
        this.read_num = 0;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        Formatter fm = new Formatter(sb);
//        fm.format("Article {%n");
//        fm.format("    %s: %s%n", "date", DateUtils.getHumanFormat(timestamp));
//        fm.format("    %s: %s%n", "title", title);
//        fm.format("    %s: %s%n", "author", author);
//        fm.format("    %s: %s%n", "img_url", img_url);
//        fm.format("    %s: %s%n", "doc_url", doc_url);
//        fm.format("    %s: %s%n", "doc_intro", doc_intro);
//        fm.format("}%n");

        return toJson();
    }

    public String toJson() {
        StringBuilder sb = new StringBuilder();
        Formatter fm = new Formatter(sb);
        fm.format("{");
        fm.format("\"%s\": \"%s\",", "date", DateUtils.getHumanFormat(timestamp));
        fm.format("\"%s\": \"%s\",", "title", title);
        fm.format("\"%s\": \"%s\",", "author", author);
        fm.format("\"%s\": \"%s\",", "img_url", img_url);
        fm.format("\"%s\": \"%s\",", "doc_url", doc_url);
        fm.format("\"%s\": \"%s\",", "doc_intro", doc_intro);
        fm.format("\"%s\": \"%s\"", "read_num", read_num);
        fm.format("}");

        return sb.toString();
    }

    public static BlogEntry parse(final String line) {
        if (line == null) return null;
        try {
            String[] array = line.split("#");
            long timestamp = DateUtils.HUMAN_FORMAT.parse(array[0]).getTime();
            String title = array[1];
            String author = array[2];
            String img_url = array[3];
//            if (img_url == null || "".equalsIgnoreCase(img_url)) {
//                img_url = HttpConst.IMAGE_404;
//            }
            String doc_url = array[4];
            String doc_intro = array[5];
            return new BlogEntry(timestamp, title, author, img_url, doc_url, doc_intro);
        }
        catch (Exception ex) {
            audit.log(Level.WARNING, "entry is not valid: " + line);
        }
        return null;
    }

    public static void main(String[] args) {
        String user_dir = System.getProperty("user.dir");
        String filepath = user_dir + "/static/json/home_list.txt";
        System.out.println(filepath);
        List<String> lines = FileUtils.readLines(filepath);
        for (String line : lines) {
            BlogEntry entry = BlogEntry.parse(line);
            System.out.println(entry);
        }
    }
}
