package lsieun.net.http.bean;

public class RequestLine {
    public final String method;
    public final String path;
    public final String version;

    public RequestLine(String method, String path, String version) {
        this.method = method;
        this.path = path;
        this.version = version;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", method, path, version);
    }
}
