package lsieun.net.http;

import lsieun.net.Resource;
import lsieun.net.http.bean.HttpResource;
import lsieun.net.http.bean.KeyValuePair;
import lsieun.net.http.utils.HttpHeaderUtils;
import lsieun.net.http.utils.MIMEUtils;
import lsieun.utils.Const;
import lsieun.utils.DateUtils;
import lsieun.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.Optional;
import java.util.logging.Level;

import static lsieun.utils.LogUtils.err;

public class FileHandler extends ResourceHandler {
    private static final String ROOT_PATH = HttpHandler.class.getClassLoader().getResource(".").getPath();

    @Override
    public HttpResource getResource(String uri_path) {
        String filepath = getRealFilePath(uri_path);
        return getDiskResource(uri_path, filepath);
    }

    public HttpResource get404PageResource() {
        String uri_path = Const.PAGE_404_PATH;
        String filepath = get404FilePath();
        return getDiskResource(uri_path, filepath);
    }

    public HttpResource get404ImageResource() {
        String uri_path = Const.IMAGE_404_PATH;
        String filepath = get404ImagePath();
        return getDiskResource(uri_path, filepath);
    }

    public static HttpResource getDiskResource(final String uri_path, final String filepath) {
        try {
            File file = new File(filepath);

            if (file.exists() && file.isFile()) {
                byte[] file_bytes = FileUtils.readFile(filepath);
                String fileExtension = getFileExtension(file);
                String contentType = MIMEUtils.getContentType(fileExtension);
                long lastModified = file.lastModified();
                return new HttpResource(uri_path, contentType, lastModified, file_bytes);
            }
        } catch (IOException ex) {
            err.log(Level.SEVERE, "unexpected error: " + ex.getMessage(), ex);
        }
        return null;
    }

    public static String get404FilePath() {
        return getRealFilePath("/docs/404.html");
    }

    public static String get404ImagePath() {
        return getRealFilePath("/images/image_not_found.png");
    }

    public static String getRealFilePath(String uri_path) {
        if ("/".equals(uri_path) || "/index.html".equals(uri_path)) {
            return String.format("%s%s", ROOT_PATH, "static/index.html");
        } else if ("/favicon.ico".equals(uri_path)) {
            return String.format("%s%s", ROOT_PATH, "static/images/favicon.ico");
        } else if (uri_path.startsWith("/docs") ||
                uri_path.startsWith("/css") ||
                uri_path.startsWith("/images") ||
                uri_path.startsWith("/font") ||
                uri_path.startsWith("/js") ||
                uri_path.startsWith("/videos")
        ) {
            return ROOT_PATH + "static" + uri_path;
        } else {
            err.warning("unexpected uri: " + uri_path);
            return ROOT_PATH + "static" + uri_path;
        }
    }

    private static String getFileExtension(File file) {
        String name = file.getName();
        int lastIndex = name.lastIndexOf(".");
        if (lastIndex == -1) {
            return ""; // empty extension
        }
        return name.substring(lastIndex);
    }

    public static void main(String[] args) throws URISyntaxException {
        String filepath = "/home/liusen/abc.txt";
        File f = new File(filepath);
        String ext = getFileExtension(f);
        System.out.println(ext);
    }


}
