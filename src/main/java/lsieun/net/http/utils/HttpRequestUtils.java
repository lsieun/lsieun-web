package lsieun.net.http.utils;

import lsieun.net.http.bean.HttpRequest;
import lsieun.utils.PropertyUtils;

import java.util.ArrayList;
import java.util.List;

import static lsieun.utils.LogUtils.*;

public class HttpRequestUtils {
    private static final String ip = PropertyUtils.getProperty("http.ip");
    private static final String domain_core = PropertyUtils.getProperty("http.domain.core");
    private static final String path_block_keyword = PropertyUtils.getProperty("http.blacklist.request.path.keyword");
    private static final String path_block_ext = PropertyUtils.getProperty("http.blacklist.request.path.ext");
    private static final List<String> malicious_path_list = new ArrayList<>();
    private static final List<String> malicious_host_list = new ArrayList<>();

    static {
        // 有些服务器进行攻击，在path当中带有IP地址或域名，按HTTP规范来说，是不合法的
        malicious_path_list.add(ip);
        malicious_path_list.add(domain_core);
        // 有些恶意服务器，想通过访问某些路径或后缀，来达成攻击或爬取数据
        malicious_path_list.addAll(getItemList(path_block_keyword, ","));
        malicious_path_list.addAll(getItemList(path_block_ext, ","));

        // 如果Host是IP地址，而不是域名，就说明不是人类的行为，而是机器行为
        malicious_host_list.add(ip);

        // Log
        audit.info(() -> "Path blacklist keyword: " + malicious_path_list.toString());
        audit.info(() -> "Host blacklist keyword: " + malicious_host_list.toString());
    }

    public static List<String> getItemList(final String line, final String separator) {
        List<String> list = new ArrayList<>();
        if (line == null) return list;
        String[] array = line.split(separator);
        for (String item : array) {
            list.add(item.trim().toLowerCase());
        }
        return list;
    }

    public static boolean isMalicious(final HttpRequest request) {
        String request_line = request.request_line.toString().toLowerCase();
        String host = request.getHost();

        for (String item : malicious_path_list) {
            if (request_line.contains(item)) {
                return true;
            }
        }

        for (String item : malicious_host_list) {
            if (host.contains(item)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        audit.info("Just wanna see blacklist keyword");
    }
}
