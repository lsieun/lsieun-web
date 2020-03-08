package lsieun.net.utils;

import lsieun.utils.FileUtils;

import java.io.File;
import java.util.*;
import java.util.logging.Level;

import static lsieun.utils.LogUtils.audit;


public class BlackListUtils {
    private static final int MAX_VALUE = 10;
    private static final Set<String> black_host_set = new HashSet<>();
    private static final Map<String, Integer> map = new HashMap<>();

    static {
        String filepath = System.getProperty("user.home") + "/blacklist.txt";

        Runnable r = () -> {
            while (true) {
                try {
                    readBlacklist(filepath);
                    writeBlacklist(filepath);
                    Thread.sleep(5 * 60 * 1000);
                } catch (Exception ex) {
                    audit.log(Level.SEVERE, "unexpected error: " + ex.getMessage(), ex);
                }
            }
        };

        Thread t = new Thread(r);
        t.setDaemon(true);
        t.start();
    }

    public static void readBlacklist(final String filepath) {
        File file = new File(filepath);
        if (file.exists() && file.isFile()) {
            List<String> lines = FileUtils.readLines(filepath);
            black_host_set.addAll(lines);
        }
    }

    public static void writeBlacklist(final String filepath) {
        if (black_host_set.size() > 0) {
            List<String> list = new ArrayList<>(black_host_set);
            FileUtils.writeLines(list, filepath);
        }
    }

    public static void main(String[] args) {
        System.out.println(black_host_set);
    }

    public static void add(String host) {
        if (map.containsKey(host)) {
            int value = map.get(host);
            map.put(host, value + 1);
        } else {
            map.put(host, 1);
        }

        int count = map.get(host);
        if (count > MAX_VALUE) {
            black_host_set.add(host);
        }
    }

    public static void addBlack(String host) {
        black_host_set.add(host);
    }

    public static boolean isBlack(String host) {
        return black_host_set.contains(host);
    }
}
