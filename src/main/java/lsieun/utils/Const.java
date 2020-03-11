package lsieun.utils;

public class Const {
    // Http Related
    public static final String DOMAIN_NAME = "http://lsieun.cn";
    public static final int HTTP_PORT = 80;
    public static final int FAKE_HTTP_PORT = 8080;



    // IO Related
    public static final int BUFFER_SIZE = 1024 * 32;

    // Time Related
    public static final int TIME_SECOND = 1000;
    public static final int TIME_MINUTE = 60 * TIME_SECOND;
    public static final int TIME_HOUR = 60 * TIME_MINUTE;
    public static final int DEFAULT_SLEEP_TIME = 25 * TIME_MINUTE;

    // Log Related
    public static final String ERROR_LOG_LOC = "/tmp/web_error.log";
    public static final int LOG_FILE_SIZE = 1024 * 1024 * 1;
    public static final int ERROR_LOG_NUM = 2;
}
