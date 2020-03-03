package lsieun.net.http;

import lsieun.utils.AddressUtils;
import lsieun.utils.PropertyUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

import static lsieun.utils.LogUtils.audit;

public class HttpConnection {
    private static final long time_out_milliseconds = PropertyUtils.getInt("http.connection.timeout.seconds") * 1000;

    private final ByteArrayOutputStream byteTank = new ByteArrayOutputStream();
    private ByteBuffer current_response;
    public final SocketChannel socketChannel;
    public final SelectionKey selectionKey;
    public final String addr;
    public final long initial_time;
    private long last_read_time;
    private long last_write_time;

    public HttpConnection(SocketChannel socketChannel, SelectionKey selectionKey) {
        this.socketChannel = socketChannel;
        this.selectionKey = selectionKey;
        this.addr = AddressUtils.getAddress(socketChannel);
        this.initial_time = System.currentTimeMillis();
        this.last_read_time = initial_time;
        this.last_write_time = initial_time;
    }

    public void read(ByteBuffer byteBuffer) {
        while (byteBuffer.hasRemaining()) {
            byte b = byteBuffer.get();
            byteTank.write(b);
        }
    }

    public void updateReadTime() {
        this.last_read_time = System.currentTimeMillis();
    }

    public void updateWriteTime() {
        this.last_write_time = System.currentTimeMillis();
    }

    public boolean isTimeOut() {
        long last_time = Math.max(last_read_time, last_write_time);
        long diff = System.currentTimeMillis() - last_time;
        if (diff > time_out_milliseconds) return true;
        return false;
    }

    public void close() {
        try {
            selectionKey.cancel();
            socketChannel.close();
        } catch (IOException ex) {
            audit.warning(() -> "Client SocketChannel close Exception: " + ex);
        }
    }

    public byte[] process() {
        byte[] bytes = byteTank.toByteArray();
        HttpRequest httpRequest = HttpHandler.newRequest(bytes);
        if (httpRequest == null) {
            return null;
        }
        audit.info(() -> httpRequest.request_line.toString());

        byteTank.reset();
        if (httpRequest.length < bytes.length) {
            for (int i=httpRequest.length;i<bytes.length;i++) {
                byteTank.write(bytes[i]);
            }
        }

        HttpResponse response = HttpHandler.getResponse(httpRequest);
        audit.info(() -> response.status_line);
        return response.toBytes();
    }

    public ByteBuffer getResponse() {
        if (current_response != null && current_response.hasRemaining()) {
            return current_response;
        }

        byte[] bytes = process();
        if (bytes == null) {
            current_response = null;
        }
        else {
            current_response = ByteBuffer.wrap(bytes);
        }
        return current_response;
    }
}
