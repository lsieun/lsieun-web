package lsieun.net.http.bean;

import lsieun.utils.FileUtils;

import java.io.File;
import java.util.List;
import java.util.logging.Level;

import static lsieun.utils.LogUtils.audit;

public class FileContent {
    public static final String ROOT_PATH = System.getProperty("user.dir") + "/static/json/";

    public final String filename;
    public final long lastModified;
    public final List<String> lines;

    public FileContent(final String filename) {
        String filepath = ROOT_PATH + filename;
        File file = new File(filepath);
        if (file.exists() && file.isFile()){
            this.filename = filename;
            this.lastModified = file.lastModified();
            this.lines = FileUtils.readLines(filepath);
        }
        else {
            audit.log(Level.WARNING, "File Not Exist: " + filepath);
            throw new IllegalArgumentException("filename is not valid: " + filename);
        }
    }

}
