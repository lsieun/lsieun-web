package lsieun.net.http.bean;

public class HttpRawBean {
    public final byte[] first_line_bytes;
    public final byte[] header_bytes;
    public final byte[] payload_bytes;
    public final byte[] remaining_bytes;

    public HttpRawBean(byte[] first_line_bytes, byte[] header_bytes, byte[] payload_bytes, byte[] remaining_bytes) {
        this.first_line_bytes = first_line_bytes;
        this.header_bytes = header_bytes;
        this.payload_bytes = payload_bytes;
        this.remaining_bytes = remaining_bytes;
    }
}
