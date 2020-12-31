package lsieun.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;

import static lsieun.utils.LogUtils.audit;

public class PropertyUtils {
    private static final String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    private static final Properties props = new Properties();
    public static boolean production;

    static {
        try {
            audit.config(() -> "Root Path: " + rootPath);
            String configPath = rootPath + "application.properties";
            audit.config(() -> "READ File: " + configPath);

            props.load(new FileInputStream(configPath));
            production = true;

            String dev_filepath = rootPath + "/dev";
            File dev_file = new File(dev_filepath);
            if (dev_file.exists() && dev_file.isFile()) {
                production = false;
                String dev_config_filepath = rootPath + "/application-dev.properties";
                File dev_config_file = new File(dev_config_filepath);
                if (dev_config_file.exists() && dev_config_file.isFile()) {
                    props.load(new FileInputStream(dev_config_file));
                }
            }

        } catch (IOException ex) {
            audit.log(Level.SEVERE, "unexpected error: " + ex.getMessage(), ex);
            System.exit(1);
        }
    }

    public static boolean isProduction() {
        return production;
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

    public static void main(String[] args) {
        String value = PropertyUtils.getProperty("http.ip");
        System.out.println(value);
    }
}
