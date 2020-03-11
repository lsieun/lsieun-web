package lsieun.net.http.bean;

import java.util.Formatter;
import java.util.List;

public class Page {
    public final int total_count;
    public final int page_num;
    public final int page_total;
    public final List<BlogEntry> data_list;

    public Page(int total_count, int page_num, int page_total, List<BlogEntry> data_list) {
        this.total_count = total_count;
        this.page_num = page_num;
        this.page_total = page_total;
        this.data_list = data_list;
    }

    public String toJson() {
        StringBuilder sb = new StringBuilder();
        Formatter fm = new Formatter(sb);
        fm.format("{");
        fm.format("\"%s\":%s,", "total_count", total_count);
        fm.format("\"%s\":%s,", "page_num", page_num);
        fm.format("\"%s\":%s,", "page_total", page_total);
        fm.format("\"%s\":%s", "data_list", data_list.toString());
        fm.format("}");
        return sb.toString();
    }
}
