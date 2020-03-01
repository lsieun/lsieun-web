package lsieun.net.http;

import lsieun.net.Handler;
import lsieun.utils.Const;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import static lsieun.utils.LogUtils.audit;

public class HttpHandler extends Handler {
    public static HttpRequest newRequest(byte[] bytes) {
        return HttpByteParser.toHttpRequest(bytes);
    }

    public static HttpResponse getResponse(HttpRequest request) {
        return FileHandler.handle(request);
    }

    public static String getURIPath(HttpRequest request) {
        try {
            URL url = new URL(Const.DOMAIN_NAME + request.request_line.path);
            URI uri = url.toURI();
            String path = uri.getPath();
            return path;
        } catch (MalformedURLException | URISyntaxException ex) {
            audit.warning("Unexpected path: " + request.request_line.path + ", " + ex);
        }
        return request.request_line.path;
    }
}
