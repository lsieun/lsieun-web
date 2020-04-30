package lsieun.net.http.utils;

import lsieun.net.http.bean.HttpRequest;
import lsieun.utils.PropertyUtils;
import lsieun.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static lsieun.utils.LogUtils.*;

public class HttpRequestUtils {
    private static final String ip = PropertyUtils.getProperty("http.ip");
    private static final String domain_core = PropertyUtils.getProperty("http.domain.core");
    private static final String path_blacklist_keyword = PropertyUtils.getProperty("http.blacklist.request.path.keyword");
    private static final String path_blacklist_ext = PropertyUtils.getProperty("http.blacklist.request.path.ext");
    private static final String user_agent_blacklist_keyword = PropertyUtils.getProperty("http.blacklist.request.user_agent.keyword");
    private static final List<String> malicious_path_list = new ArrayList<>();
    private static final List<String> malicious_host_list = new ArrayList<>();
    private static final List<String> malicious_user_agent_list = new ArrayList<>();

    static {
        // 有些服务器进行攻击，在path当中带有IP地址或域名，按HTTP规范来说，是不合法的
        malicious_path_list.add(ip);
        malicious_path_list.add(domain_core);
        // 有些恶意服务器，想通过访问某些路径或后缀，来达成攻击或爬取数据
        malicious_path_list.addAll(getItemList(path_blacklist_keyword, ","));
        malicious_path_list.addAll(getItemList(path_blacklist_ext, ","));

        // 如果Host是IP地址，而不是域名，就说明不是人类的行为，而是机器行为
        malicious_host_list.add(ip);

        // User Agent不合法
        malicious_user_agent_list.addAll(getItemList(user_agent_blacklist_keyword, ","));

        // Log
        audit.info(() -> "Path blacklist keyword: " + malicious_path_list.toString());
        audit.info(() -> "Host blacklist keyword: " + malicious_host_list.toString());
        audit.info(() -> "User-Agent blacklist keyword: " + malicious_user_agent_list.toString());
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
        // Path
        String request_line = request.request_line.toString().toLowerCase();
        for (String item : malicious_path_list) {
            if (request_line.contains(item)) {
                audit.warning(() -> "malicious path: " + item);
                return true;
            }
        }

        // Host
        String host = request.header.getHost();
        if (StringUtils.isBlank(host)) {
            audit.warning(() -> "malicious host: host is null");
            return true;
        }
        host = host.toLowerCase();
        if (!host.contains(domain_core)) {
            audit.warning(() -> "malicious host: do not contain my domain name");
            return true;
        }
        for (String item : malicious_host_list) {
            if (host.contains(item)) {
                audit.warning(() -> "malicious host: " + item);
                return true;
            }
        }

        // Accept
//        String accept = request.header.getAccept();
//        if (StringUtils.isBlank(accept)) {
//            audit.info(() -> "malicious accept: " + accept);
//            return true;
//        }

        // Connection
        String connection = request.header.getConnection();
        if (StringUtils.isBlank(connection)) {
            audit.warning(() -> "malicious connection: " + connection);
            return true;
        }

        // User-Agent
        String user_agent = request.header.getUserAgent();
        if (StringUtils.isBlank(user_agent)) {
            audit.warning(() -> "malicious user-agent: user-agent is null");
            return true;
        }
        user_agent = user_agent.toLowerCase();
        for (String item : malicious_user_agent_list) {
            if (user_agent.contains(item)) {
                audit.warning(() -> "malicious user-agent: " + item);
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        audit.info("Just wanna see blacklist keyword");
    }
}
