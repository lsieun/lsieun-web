package lsieun.net.http.handler.sub;

import lsieun.net.http.HttpConst;
import lsieun.net.http.bean.HttpHeader;
import lsieun.net.http.bean.HttpResource;
import lsieun.net.http.handler.FileHandler;
import lsieun.net.http.utils.HTMLUtils;
import lsieun.net.http.utils.HttpUtils;
import lsieun.utils.DateUtils;
import lsieun.utils.FileUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.logging.Level;

import static lsieun.utils.LogUtils.audit;

public class ContentHandler extends FileHandler {

    @Override
    @SuppressWarnings("Duplicates")
    public HttpResource getResource(String uri_path, HttpHeader header) {
        HttpResource resource = getContentResource(uri_path);

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

    public HttpResource getContentResource(final String uri_path) {
        try {
            String filepath = getRealFilePath(uri_path);

            String head = HTMLUtils.getContentHead();
            String body = HTMLUtils.getContentBody();
            String nav_bar = HTMLUtils.getNavBar();

            String content = FileUtils.readHtml(filepath);
            String title = HTMLUtils.getTitle(content);
            if (title != null) {
                title = title.replaceAll("<br/>", "");
            }

            String html = head.replace(HttpConst.TITLE, title) +
                    body.replace(HttpConst.NAVIGATION_BAR, nav_bar)
                            .replace(HttpConst.PAGE_CONTENT, content);

            byte[] bytes = html.getBytes(StandardCharsets.UTF_8);
            return new HttpResource(uri_path, "text/html", 0, bytes);
        }
        catch (IOException ex) {
            audit.log(Level.WARNING, "unexpected error: " + ex.getMessage(), ex);
        }
        // TODO: 如果内容上缺失，则返回404
        return null;
    }


}
