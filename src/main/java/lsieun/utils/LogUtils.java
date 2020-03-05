package lsieun.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class LogUtils {
    public final static Logger audit = Logger.getLogger("audit");
    public final static Logger err = Logger.getLogger("error");

    static {
        try {
            InputStream stream = LogUtils.class.getClassLoader().getResourceAsStream("logging.properties");
            LogManager.getLogManager().readConfiguration(stream);

            String user_dir = System.getProperty("user.dir");
            String audit_filepath = user_dir + "/web_audit_%d.%u.log";
            Handler handler = new DailyRollingFileHandler(audit_filepath);
            handler.setLevel(Level.ALL);

            audit.setUseParentHandlers(false);
            audit.addHandler(handler);
            err.setUseParentHandlers(false);
            err.addHandler(handler);
        } catch (IOException ex) {
            err.log(Level.SEVERE, "unexpected error: " + ex.getMessage(), ex);
            System.exit(1);
        }
    }
}
