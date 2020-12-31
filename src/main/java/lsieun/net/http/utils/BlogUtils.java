package lsieun.net.http.utils;

import lsieun.net.http.bean.BlogEntry;
import lsieun.utils.FileUtils;
import lsieun.utils.PropertyUtils;

import java.io.File;
import java.util.*;

public class BlogUtils {
    public static final String ROOT_PATH = System.getProperty("user.dir") + "/static/json/";
    private static final Map<String, Long> lastModifiedMap = new HashMap<>();
    private static final Map<String, List<BlogEntry>> cacheEntryMap = new HashMap<>();

    public static List<BlogEntry> getHomeList() {
//        List<BlogEntry> list = new ArrayList<>();
//        list.addAll(getExcerptList());
//        list.addAll(getLifeList());
//        list.addAll(getCodeList());
//        list.sort(Comparator.comparing(BlogEntry::getTimestamp).reversed());
//        return list;
        return getEntryList("home_list.txt");
    }

    public static List<BlogEntry> getBookList() {
        return getEntryList("book_list.txt");
    }

    public static List<BlogEntry> getCodeList() {
        return getEntryList("code_list.txt");
    }

    public static List<BlogEntry> getEnglishList() {
        return getEntryList("english_list.txt");
    }

    public static List<BlogEntry> getExcerptList() {
        return getEntryList("excerpt_list.txt");
    }

    public static List<BlogEntry> getFrontList() {
        return getEntryList("front_list.txt");
    }

    public static List<BlogEntry> getLifeList() {
        return getEntryList("life_list.txt");
    }

    public static List<BlogEntry> getOSList() {
        return getEntryList("os_list.txt");
    }

    public static List<BlogEntry> getPopularList() {
        return getEntryList("popular_list.txt");
    }

    public static List<BlogEntry> getToolsList() {
        return getEntryList("tools_list.txt");
    }

    public static List<BlogEntry> getEntryList(final String filename) {
        String filepath = ROOT_PATH + filename;
        File file = new File(filepath);

        // 如果file不存在，或者file不是一个具体的文件，就返empty list。
        if (!file.exists() || !file.isFile()) {
            return Collections.emptyList();
        }

        long lastModified = file.lastModified();
        Long value = lastModifiedMap.get(filename);
        if (value == null || lastModified > value) {
            lastModifiedMap.put(filename, lastModified);

            List<BlogEntry> list = getEntryListFromDisk(filepath);
            cacheEntryMap.put(filename, list);
            return list;
        }
        else {
            return cacheEntryMap.get(filename);
        }
    }

    private static List<BlogEntry> getEntryListFromDisk(final String filepath) {
        List<String> lines = FileUtils.readLines(filepath);

        List<BlogEntry> list = new ArrayList<>();
        for (String line : lines) {
            BlogEntry entry = BlogEntry.parse(line);
            if (entry == null) continue;
            if (PropertyUtils.isProduction() && entry.title.endsWith("Beta")) {
                continue;
            }
            list.add(entry);
        }
        return list;
    }
}
