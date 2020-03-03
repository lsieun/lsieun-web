package lsieun.utils;

import java.io.File;
import java.util.*;
import java.util.logging.Level;

import static lsieun.utils.LogUtils.err;

public class BlackListUtils {
    private static final int MAX_VALUE = 10;
    private static final Set<String> black_host_set = new HashSet<>();
    private static final Map<String, Integer> map = new HashMap<>();

    static {
        String user_dir = System.getProperty("user.home");
        String filepath = user_dir + "/blacklist.txt";

        File f = new File(filepath);
        if (f.exists() && f.isFile()) {
            List<String> lines = FileUtils.readLines(filepath);
            if (lines != null && lines.size() > 0) {
                for (String line : lines) {
                    black_host_set.add(line);
                }
            }
        }

        Runnable r = () -> {
            while (true) {
                try {
                    if (black_host_set.size() > 0) {
                        List<String> list = new ArrayList<>();
                        for (String host : black_host_set) {
                            list.add(host);
                        }
                        FileUtils.writeLines(list, filepath);
                    }
                    Thread.sleep(5 * 60 * 1000);
                } catch (Exception ex) {
                    err.log(Level.SEVERE, "unexpected error: " + ex.getMessage(), ex);
                }
            }
        };
        Thread t = new Thread(r);
        t.setDaemon(true);
        t.start();
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

    public static boolean isBlack(String host) {
        return black_host_set.contains(host);
    }
}
