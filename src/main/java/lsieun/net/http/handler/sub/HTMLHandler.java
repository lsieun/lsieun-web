package lsieun.net.http.handler.sub;

import lsieun.net.http.bean.HttpHeader;
import lsieun.net.http.bean.HttpResource;
import lsieun.net.http.handler.FileHandler;
import lsieun.net.http.utils.MIMEUtils;
import lsieun.utils.DateUtils;
import lsieun.utils.FileUtils;

import java.io.File;
import java.util.Date;

public class HTMLHandler extends FileHandler {
    public HttpResource getResource(String uri_path, HttpHeader header) {
        String filepath = super.getRealFilePath(uri_path);
        File file = new File(filepath);
        if (!file.exists()) {
            return null;
        }

        HttpResource resource = super.getResource(uri_path, header);

        Date now = new Date();
        Date expireDate = DateUtils.addMinutes(now, 5);

        header.add("Date", DateUtils.getGMTFormat(now));
        header.add("Server", "Chaos Server");

        String ext = FileUtils.getFileExtension(file);
        String contentType = MIMEUtils.getContentType(ext);
        header.add("Content-Type", contentType);

        // Expire
        String max_age = String.format("max-age=%s", DateUtils.diff(now, expireDate));
        header.add("Cache-Control", max_age);
        header.add("Expires", DateUtils.getGMTFormat(expireDate));

        return resource;
    }
}
