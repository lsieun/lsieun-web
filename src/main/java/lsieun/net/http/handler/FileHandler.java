package lsieun.net.http.handler;

import lsieun.net.http.bean.HttpHeader;
import lsieun.net.http.bean.HttpResource;
import lsieun.net.http.utils.MIMEUtils;
import lsieun.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import static lsieun.utils.LogUtils.audit;

public abstract class FileHandler extends ResourceHandler {
    public static final String ROOT_PATH = System.getProperty("user.dir") + "/static";

    @Override
    public HttpResource getResource(String uri_path, HttpHeader header) {
        String filepath = getRealFilePath(uri_path);
        return getDiskResource(uri_path, filepath);
    }

    public String getRealFilePath(final String uri_path) {
        return FileHandler.ROOT_PATH + uri_path;
    }

    public static HttpResource getDiskResource(final String uri_path, final String filepath) {
        try {
            File file = new File(filepath);
            if (!file.exists()) {
                file = new File(filepath.toLowerCase());
            }

            if (file.exists() && file.isFile()) {
                byte[] file_bytes = FileUtils.readFile(filepath);
                String fileExtension = FileUtils.getFileExtension(file);
                String contentType = MIMEUtils.getContentType(fileExtension);
                long lastModified = file.lastModified();
                return new HttpResource(uri_path, contentType, lastModified, file_bytes);
            }
        } catch (IOException ex) {
            audit.log(Level.SEVERE, "unexpected error: " + ex.getMessage(), ex);
        }
        return null;
    }

//    public String getRealFilePath(String uri_path) {
//        if ("/".equals(uri_path) || "/index.html".equals(uri_path)) {
//            return String.format("%s%s", ROOT_PATH, "static/index.html");
//        } else if ("/favicon.ico".equals(uri_path)) {
//            return String.format("%s%s", ROOT_PATH, "static/images/favicon.ico");
//        } else if (uri_path.startsWith("/docs") ||
//                uri_path.startsWith("/css") ||
//                uri_path.startsWith("/images") ||
//                uri_path.startsWith("/font") ||
//                uri_path.startsWith("/js") ||
//                uri_path.startsWith("/videos")
//        ) {
//            return ROOT_PATH + "static" + uri_path;
//        } else {
//            audit.warning("unexpected uri: " + uri_path);
//            return ROOT_PATH + "static" + uri_path;
//        }
//    }

}
