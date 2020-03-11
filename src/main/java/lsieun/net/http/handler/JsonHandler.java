package lsieun.net.http.handler;

import lsieun.net.http.HttpConst;
import lsieun.net.http.bean.BlogEntry;
import lsieun.net.http.bean.HttpHeader;
import lsieun.net.http.bean.HttpResource;
import lsieun.net.http.bean.Page;
import lsieun.net.http.utils.BlogUtils;
import lsieun.utils.DateUtils;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import static lsieun.utils.LogUtils.audit;

public class JsonHandler extends ResourceHandler {
    @Override
    public HttpResource getResource(String uri_path, HttpHeader header) {
        HttpResource resource = getHomeList(uri_path);

        Date now = new Date();
        header.add("Date", DateUtils.getGMTFormat(now));
        header.add("Server", "Chaos Server");
        header.add("Content-Type", "application/json");
        header.add("Cache-Control", "no-cache");
        header.add("Expires", DateUtils.getGMTFormat(now));

        return resource;
    }

    public HttpResource getHomeList(String uri_path) {
        // TODO: 这里很可能路径不正确
        int slash_index = uri_path.lastIndexOf("/");
        int page_num = 1;
        if (slash_index != -1) {
            String page_num_str = uri_path.substring(slash_index + 1);
            try {
                page_num = Integer.parseInt(page_num_str);
            }
            catch (NumberFormatException ex) {
                audit.log(Level.WARNING, "illegal uri path: " + uri_path);
                page_num = 1;
            }
        }


        List<BlogEntry> list;
        if (uri_path.startsWith("/json/home/list/")) {
            list = BlogUtils.getHomeList();
        }
        else if (uri_path.startsWith("/json/excerpt/list/")) {
            list = BlogUtils.getExcerptList();
        }
        else if (uri_path.startsWith("/json/whim/list/")) {
            list = BlogUtils.getWhimList();
        }
        else if (uri_path.startsWith("/json/life/list/")) {
            list = BlogUtils.getLifeList();
        }
        else if (uri_path.startsWith("/json/code/list/")) {
            list = BlogUtils.getCodeList();
        }
        else {
            list = Collections.emptyList();
        }

        int size = list.size();

        int quotient = size / HttpConst.PAGE_SIZE;
        int remainder = size % HttpConst.PAGE_SIZE;
        int page_total = remainder == 0 ? quotient : quotient + 1;
        if (page_num < 1) {
            page_num = 1;
        }
        if (page_num > page_total) {
            page_num = page_total;
        }

        List<BlogEntry> data_list = new ArrayList<>();
        int start_index = (page_num - 1) * HttpConst.PAGE_SIZE;
        for (int i=0; i<HttpConst.PAGE_SIZE;i++) {
            int index = start_index + i;
            if (index >= size) break;
            data_list.add(list.get(index));
        }

        Page page = new Page(size, page_num, page_total, data_list);
        String json = page.toJson();
        audit.info(json);
        byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
        HttpResource resource = new HttpResource(uri_path, "application/json", 0, bytes);
        return resource;
    }
}
