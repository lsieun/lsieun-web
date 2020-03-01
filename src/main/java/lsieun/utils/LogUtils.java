package lsieun.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class LogUtils {
    public final static Logger audit = Logger.getLogger("audit");
    public final static Logger err = Logger.getLogger("error");

    static {
        try {
            InputStream stream = LogUtils.class.getClassLoader().getResourceAsStream("logging.properties");
            LogManager.getLogManager().readConfiguration(stream);
        } catch (IOException ex) {
            err.severe(() -> "unexpected error: " + ex);
            System.exit(1);
        }
    }
}
