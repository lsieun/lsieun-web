package lsieun.net.http.handler.sub;

import lsieun.net.http.HttpConst;
import lsieun.net.http.bean.HttpHeader;
import lsieun.net.http.bean.HttpResource;
import lsieun.net.http.handler.FileHandler;
import lsieun.net.http.utils.MIMEUtils;
import lsieun.utils.DateUtils;
import lsieun.utils.FileUtils;

import java.io.File;
import java.util.Date;

public class ImageHandler extends FileHandler {
    @Override
    public HttpResource getResource(String uri_path, HttpHeader header) {
        String filepath = getRealFilePath(uri_path);
        HttpResource resource = getDiskResource(uri_path, filepath);
        final String ext = FileUtils.getFileExtension(new File(filepath));

        Date now = new Date();
        Date expireDate = DateUtils.addYears(now, 3);

        header.add("Date", DateUtils.getGMTFormat(now));
        header.add("Last-Modified", DateUtils.getGMTFormat(resource.lastModified));
        header.add("Server", "Chaos Server");
        String content_type = MIMEUtils.getContentType(ext);
        header.add("Content-Type", content_type);

        // Expire
        String max_age = String.format("max-age=%s", DateUtils.diff(now, expireDate));
        header.add("Cache-Control", max_age);
        header.add("Expires", DateUtils.getGMTFormat(expireDate));

        return resource;
    }

    public HttpResource get404ImageResource() {
        String uri_path = HttpConst.IMAGE_404;
        String filepath = get404ImagePath();
        return getDiskResource(uri_path, filepath);
    }

    public String get404ImagePath() {
        return getRealFilePath("/images/image_not_found.png");
    }
}
