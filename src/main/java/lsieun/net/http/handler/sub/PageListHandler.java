package lsieun.net.http.handler.sub;

import lsieun.net.http.HttpConst;
import lsieun.net.http.bean.BlogEntry;
import lsieun.net.http.bean.HttpHeader;
import lsieun.net.http.bean.HttpResource;
import lsieun.net.http.handler.FileHandler;
import lsieun.net.http.utils.BlogUtils;
import lsieun.net.http.utils.HTMLUtils;
import lsieun.utils.DateUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.logging.Level;

import static lsieun.utils.LogUtils.audit;

public class PageListHandler extends FileHandler {
    private final Map<String, HttpResource> cacheMap = new HashMap<>();

    @Override
    public HttpResource getResource(String uri_path, HttpHeader header) {
        String filepath = getRealFilePath(uri_path);

        // TODO: 这里写的不对，要进行拼接HTML才行
        HttpResource resource = null;
        if ("/".equals(uri_path)) {
            resource = getPageList("In My Humble Opinion", "home_list.txt");
        }
        else if ("/excerpt/".equals(uri_path) || "/excerpt".equals(uri_path)) {
            resource = getPageList("Excerpt", "excerpt_list.txt");
        }
        else if ("/whim/".equals(uri_path) || "/whim".equals(uri_path)) {
            resource = getPageList("Whim", "whim_list.txt");
        }
        else if ("/life/".equals(uri_path) || "/life".equals(uri_path)) {
            resource = getPageList("Life", "life_list.txt");
        }
        else if ("/code/".equals(uri_path) || "/code".equals(uri_path)) {
            resource = getPageList("Code", "code_list.txt");
        }
        else if ("/front/".equals(uri_path) || "/front".equals(uri_path)) {
            resource = getPageList("Front End", "front_list.txt");
        }
        else if ("/os/".equals(uri_path) || "/os".equals(uri_path)) {
            resource = getPageList("OS", "os_list.txt");
        }
        else if ("/tools/".equals(uri_path) || "/tools".equals(uri_path)) {
            resource = getPageList("Tools", "tools_list.txt");
        }
        else if ("/books/".equals(uri_path) || "/books".equals(uri_path)) {
            resource = getPageList("Tools", "books_list.txt");
        }
        else if ("/english/".equals(uri_path) || "/english".equals(uri_path)) {
            resource = getPageList("English", "english_list.txt");
        }
        else if ("/about/".equals(uri_path) || "/about".equals(uri_path)) {
            resource = super.getResource("/about.html", header);
        }
        else {
            // TODO: 这里应该怎么处理呢？
        }


        Date now = new Date();
        Date expireDate = DateUtils.addMinutes(now, 5);

        header.add("Date", DateUtils.getGMTFormat(now));
        header.add("Server", "Chaos Server");
        header.add("Content-Type", "text/html");

        // Expire
        String max_age = String.format("max-age=%s", DateUtils.diff(now, expireDate));
        header.add("Cache-Control", max_age);
        header.add("Expires", DateUtils.getGMTFormat(expireDate));

        return resource;
    }

    public HttpResource getPageList(final String title, final String filename) {
        try {
            String head = HTMLUtils.getListHead().replace(HttpConst.TITLE, title);

            String navigation_bar = HTMLUtils.getNavBar();
            List<BlogEntry> list = getBlogEntryList(filename);
            int size = list.size();

            int quotient = size / HttpConst.PAGE_SIZE;
            int remainder = size % HttpConst.PAGE_SIZE;
            int totalPages = remainder == 0 ? quotient : quotient + 1;

            String base_url;
            if ("excerpt_list.txt".equals(filename)) {
                base_url = "/json/excerpt/list/";
            }
            else if ("whim_list.txt".equals(filename)) {
                base_url = "/json/whim/list/";
            }
            else if ("life_list.txt".equals(filename)) {
                base_url = "/json/life/list/";
            }
            else if ("code_list.txt".equals(filename)) {
                base_url = "/json/code/list/";
            }
            else if ("front_list.txt".equals(filename)) {
                base_url = "/json/front/list/";
            }
            else if ("os_list.txt".equals(filename)) {
                base_url = "/json/os/list/";
            }
            else if ("tools_list.txt".equals(filename)) {
                base_url = "/json/tools/list/";
            }
            else if ("books_list.txt".equals(filename)) {
                base_url = "/json/books/list/";
            }
            else if ("english_list.txt".equals(filename)) {
                base_url = "/json/english/list/";
            }
            else {
                base_url = "/json/home/list/";
            }

            String blog_entry_str = getBlogEntryHTML(list);
            String introduction_menu = getIntroductionMenu();
            String body = HTMLUtils.getListBody()
                    .replace(HttpConst.NAVIGATION_BAR, navigation_bar)
                    .replace(HttpConst.BLOG_ENTRY_LIST, blog_entry_str)
                    .replace(HttpConst.PAGE_NUM, "1")
                    .replace(HttpConst.PAGE_TOTAL, String.valueOf(totalPages))
                    .replace(HttpConst.PAGE_TOTAL, String.valueOf(totalPages))
                    .replace(HttpConst.INTRODUCTION_MENU, introduction_menu)
                    .replace(HttpConst.BASE_URL, base_url);
            String html = head + body;
            return new HttpResource("/", "text/html", 0, html.getBytes(StandardCharsets.UTF_8));
        } catch (IOException ex) {
            audit.log(Level.WARNING, "unexpected error: " + ex.getMessage(), ex);
        }
        return null;
    }

    public String getBlogEntryHTML(List<BlogEntry> list) {
        try {
            List<BlogEntry> entryList = new ArrayList<>();
            for (int i = 0; i < list.size() && i < HttpConst.PAGE_SIZE; i++) {
                entryList.add(list.get(i));
            }

            String result = "";
            String template = HTMLUtils.getListEntry();


            for (BlogEntry entry : entryList) {
                result += template
                        .replace(HttpConst.TITLE, entry.title)
                        .replace(HttpConst.DATE, DateUtils.getHumanFormat(entry.timestamp))
                        .replace(HttpConst.AUTHOR, entry.author)
                        .replace(HttpConst.IMAGE_URL, entry.img_url)
                        .replace(HttpConst.IMAGE_ALT, "")
                        .replace(HttpConst.DOC_URL, entry.doc_url)
                        .replace(HttpConst.BRIEF_INTRO, entry.doc_intro)
                        .replace(HttpConst.VIEW_NUM, "" + entry.read_num);

            }
            return result;
        } catch (IOException ex) {
            audit.log(Level.WARNING, "unexpected error: " + ex.getMessage(), ex);
        }
        return "";
    }

    public static List<BlogEntry> getBlogEntryList(final String filename) {
        List<BlogEntry> entryList;
        if ("home_list.txt".equals(filename)) {
            entryList = BlogUtils.getHomeList();
        }
        else {
            entryList = BlogUtils.getEntryList(filename);
        }
        return entryList;
    }


    public String getIntroductionMenu() {
        try {
            String entry_template = HTMLUtils.getPopularPostEntry();
            List<BlogEntry> entryList = BlogUtils.getEntryList("popular_list.txt");

            String popular_post_str = "";
            for (BlogEntry entry : entryList) {
                popular_post_str += entry_template
                        .replace(HttpConst.DATE, DateUtils.getHumanFormat(entry.timestamp))
                        .replace(HttpConst.TITLE, entry.title)
                        .replace(HttpConst.IMAGE_URL, "/images/popular-post.jpg")
                        .replace(HttpConst.IMAGE_ALT, "")
                        .replace(HttpConst.DOC_URL, entry.doc_url);
            }
            String introduction_menu_template = HTMLUtils.getIntroductionMenu();

            String result = introduction_menu_template.replace(HttpConst.POPULAR_POST_LIST, popular_post_str);
            return result;
        } catch (IOException ex) {
            audit.log(Level.WARNING, "unexpected error: " + ex.getMessage(), ex);
        }
        // TODO: 如果内容上缺失，则返回404
        return null;
    }

    public HttpResource get404Resource() {
        String uri_path = HttpConst.PAGE_404;
        String filepath = get404FilePath();
        return getDiskResource(uri_path, filepath);
    }

    public String get404FilePath() {
        return getRealFilePath("/archive/404.html");
    }

}
