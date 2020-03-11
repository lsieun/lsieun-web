package lsieun.net.http.handler.sub;

import lsieun.net.http.bean.HttpHeader;
import lsieun.net.http.bean.HttpResource;
import lsieun.net.http.handler.FileHandler;
import lsieun.utils.DateUtils;

import java.util.Date;

public class JSHandler extends FileHandler {
    @Override
    public HttpResource getResource(String uri_path, HttpHeader header) {
        String filepath = getRealFilePath(uri_path);
        HttpResource resource = getDiskResource(uri_path, filepath);

        Date now = new Date();
        Date expireDate = DateUtils.addYears(now, 3);

        header.add("Date", DateUtils.getGMTFormat(now));
        header.add("Last-Modified", DateUtils.getGMTFormat(resource.lastModified));
        header.add("Server", "Chaos Server");
        header.add("Content-Type", "application/javascript");

        // Expire
        String max_age = String.format("max-age=%s", DateUtils.diff(now, expireDate));
        header.add("Cache-Control", max_age);
        header.add("Expires", DateUtils.getGMTFormat(expireDate));

        return resource;
    }
}
