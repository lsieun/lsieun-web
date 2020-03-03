package lsieun.net.http;

import lsieun.net.http.bean.KeyValuePair;
import lsieun.net.http.utils.HttpHeaderUtils;
import lsieun.net.http.utils.MIMEUtils;
import lsieun.utils.DateUtils;
import lsieun.utils.FileUtils;
import lsieun.utils.CompressUtils;
import lsieun.utils.LogUtils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import static lsieun.utils.LogUtils.err;

public class FileHandler {
    private static final String ROOT_PATH = HttpHandler.class.getClassLoader().getResource(".").getPath();

    public static HttpResponse handle(HttpRequest request) {

        try {
            String http_version = request.request_line.version;
            // TODO: 这里可能还有QueryString，例如abc.html?name=tom&password=123
            String uri_path = HttpHandler.getURIPath(request);
            List<KeyValuePair> requestHeaders = request.headers;


            String filepath = getRealFilePath(uri_path);
            File file = new File(filepath);
            String fileExtension = getFileExtension(file);
            String contentType = MIMEUtils.getContentType(fileExtension);

            String status_line = null;
            List<KeyValuePair> headers = new ArrayList<>();
            byte[] file_bytes = null;

            // 默认Headers
            Date now = new Date();
            HttpHeaderUtils.fillDefaultHeaders(headers, now);

            if (file.exists()) {

                status_line = String.format("%s 200 OK", http_version);

                // 内容类型
                headers.add(new KeyValuePair("Content-Type", contentType));

                //缓存时间
                Date expireDate = null;
                if (".html".equals(fileExtension)) {
                    expireDate = DateUtils.addMinutes(now, 5);
                } else {
                    expireDate = DateUtils.addYears(now, 3);
                }
                HttpHeaderUtils.fillExpireHeaders(headers, now, expireDate);

                file_bytes = FileUtils.readFile(filepath);
            } else {
                // status line
                status_line = String.format("%s 404 Not Found", http_version);

                if (contentType.startsWith("image/")) {
                    headers.add(new KeyValuePair("Content-Type", "image/png"));
                    String not_found_resource = get404ImagePath();
                    file_bytes = FileUtils.readFile(not_found_resource);
                }
                else {
                    headers.add(new KeyValuePair("Content-Type", "text/html"));
                    String not_found_resource = get404FilePath();
                    file_bytes = FileUtils.readFile(not_found_resource);
                }

                Date expireDate = DateUtils.addDays(now, 7);
                HttpHeaderUtils.fillExpireHeaders(headers, now, expireDate);
            }

            String connection = HttpHeaderUtils.getConnection(requestHeaders);
            headers.add(new KeyValuePair("Connection", connection));

            byte[] payload_bytes = null;
            if (HttpHeaderUtils.containGZIP(requestHeaders)) {
                headers.add(new KeyValuePair("Content-Encoding", "gzip"));
                payload_bytes = CompressUtils.gzip_compress(file_bytes);
            } else if (HttpHeaderUtils.containDeflate(requestHeaders)) {
                headers.add(new KeyValuePair("Content-Encoding", "deflate"));
                payload_bytes = CompressUtils.deflate_compress(file_bytes);
            } else {
                payload_bytes = file_bytes;
            }

            if (payload_bytes != null) {
                headers.add(new KeyValuePair("Content-Length", String.valueOf(payload_bytes.length)));
            }
            else {
                headers.add(new KeyValuePair("Content-Length", "0"));
            }

            // 构造Response
            HttpResponse response = new HttpResponse(status_line, headers, payload_bytes);
            return response;

        } catch (IOException ex) {
            err.log(Level.SEVERE, "unexpected error: " + ex.getMessage(), ex);
        }
        return null;
    }

    public static String get404FilePath() {
        return getRealFilePath("/docs/404.html");
    }

    public static String get404ImagePath() {
        return getRealFilePath("/images/image_404.png");
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
