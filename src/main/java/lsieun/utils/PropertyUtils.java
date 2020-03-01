package lsieun.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;

import static lsieun.utils.LogUtils.err;
import static lsieun.utils.LogUtils.audit;

public class PropertyUtils {
    private static final String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    private static final Properties props = new Properties();

    static {
        String configPath = rootPath + "config.properties";
        audit.config(() -> "Root Path: " + rootPath);
        audit.config(() -> "READ File: " + configPath);

        try {
            props.load(new FileInputStream(configPath));
        } catch (IOException ex) {
            err.severe(() -> "unexpected error: " + ex);
            System.exit(1);
        }
    }

    public static String getRootPath() {
        return rootPath;
    }

    public static String getProperty(String key) {
        return props.getProperty(key);
    }

    public static int getInt(String key) {
        String value = props.getProperty(key);
        return Integer.parseInt(value);
    }

    public static void main(String[] args) throws IOException {
        String value = PropertyUtils.getProperty("email.from");
        System.out.println(value);
    }
}
