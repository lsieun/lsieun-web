package lsieun.net.http;

import lsieun.net.Connection;
import lsieun.utils.Const;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import static lsieun.utils.LogUtils.*;

public class HttpConnection extends Connection {

    private final ByteBuffer buffer = ByteBuffer.allocate(Const.BUFFER_SIZE);
    private final ByteArrayOutputStream bao = new ByteArrayOutputStream();
    private final ByteArrayOutputStream backup_bao = new ByteArrayOutputStream();
    private final HttpHandler handler = new HttpHandler();

    public HttpConnection(SocketChannel channel) {
        super(channel);
    }

    @Override
    public int read() throws IOException {
        bao.reset();
        if (backup_bao.size() > 0) {
            byte[] bytes = backup_bao.toByteArray();
            bao.write(bytes);
        }

        buffer.clear();
        int len = channel.read(buffer);
        if (len == -1) return -1;

        int total = 0;
        while (len != 0) {
            if (len == -1) {
                break;
            }
            total += len;
            buffer.flip();
            int limit = buffer.limit();
            for (int i = 0; i < limit; i++) {
                byte b = buffer.get();
                bao.write(b);
            }
            buffer.clear();
            len = channel.read(buffer);
        }
        return total;
    }

    @Override
    public void process() throws IOException {
        backup_bao.reset();
        if (bao.size() < 1) {
            audit.info("no bytes, just return");
            return;
        }
        byte[] bytes = bao.toByteArray();

        HttpRequest httpRequest = HttpHandler.newRequest(bytes);
        if (httpRequest == null) {
            backup_bao.write(bytes);
            return;
        }
        audit.info(httpRequest.request_line.toString());

        if (httpRequest.length < bytes.length) {
            backup_bao.write(bytes, httpRequest.length, bytes.length);
        }

        HttpResponse response = HttpHandler.getResponse(httpRequest);
        audit.info(response.status_line);
        if (response != null) {
            byte[] response_bytes = response.toBytes();
            ByteBuffer out_byte_buffer = ByteBuffer.wrap(response_bytes);
            while (out_byte_buffer.hasRemaining() && channel.write(out_byte_buffer) != -1){
                audit.fine(() ->
                    String.format("position=%s, limit=%s, capacity=%s",
                            out_byte_buffer.position(),
                            out_byte_buffer.limit(),
                            out_byte_buffer.capacity())
                );
            }
        }
    }

}
