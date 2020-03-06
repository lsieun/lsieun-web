package lsieun.net.http;

import lsieun.net.Connection;
import lsieun.net.http.bean.HttpRequest;
import lsieun.net.http.bean.HttpResponse;
import lsieun.net.http.utils.HttpRequestUtils;
import lsieun.net.utils.BlackListUtils;
import lsieun.utils.PropertyUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.Formatter;

import static lsieun.utils.LogUtils.audit;

public class HttpConnection extends Connection {
    public static final long TIME_OUT_MILLISECONDS = PropertyUtils.getInt("http.connection.timeout.seconds") * 1000;

    private final ByteArrayOutputStream byteTank = new ByteArrayOutputStream();

    private ByteBuffer current_in_data;
    private ByteBuffer current_out_data;
    private HttpRequest current_request;
    private HttpResponse current_response;


    public HttpConnection(SocketChannel socketChannel, SelectionKey selectionKey) {
        super(socketChannel, selectionKey);

    }

    @Override
    public void data(ByteBuffer buff) {
        while (buff.hasRemaining()) {
            byte b = buff.get();
            byteTank.write(b);
        }

        // 构建HttpRequest和HttpResponse
        byte[] bytes = byteTank.toByteArray();
        try {
            current_request = HttpHandler.newRequest(bytes);
        }
        catch (Exception ex) {
            StringBuilder sb = new StringBuilder();
            Formatter fm = new Formatter(sb);
            for (int i=0;i<bytes.length;i++) {
                byte b = bytes[i];
                fm.format("%c", b);
            }
            audit.fine(() -> "It seems that data format is not correct: " + sb.toString());
            audit.info(() -> "add malicious host: " + host);
            BlackListUtils.addBlack(host);
        }
        if (current_request == null) {
            current_response = null;
        } else {
            byteTank.reset();
            if (current_request.length < bytes.length) {
                for (int i = current_request.length; i < bytes.length; i++) {
                    byteTank.write(bytes[i]);
                }
            }
            current_response = HttpHandler.getResponse(current_request);

            audit.info(() -> String.format("%s from %s", current_request.request_line.toString(), addr));
            audit.info(() -> String.format("%s to %s", current_response.status_line, addr));

            if (current_request != null) {

                if (
                        HttpRequestUtils.isMalicious(current_request)
                ) {
                    audit.info(() -> "add malicious host: " + host);
                    BlackListUtils.addBlack(host);
                }

            }

            if (current_response.status_line.contains("404")) {
                BlackListUtils.add(host);
            }
        }
    }

    @Override
    public ByteBuffer data() {
        if (current_out_data != null && current_out_data.hasRemaining()) {
            return current_out_data;
        }

        if (current_response == null) {
            current_out_data = null;
        } else {
            byte[] bytes = current_response.toBytes();
            current_out_data = ByteBuffer.wrap(bytes);
        }

        return current_out_data;
    }

    public boolean isTimeOut() {
        long last_time = Math.max(getLastReadTime(), getLastWriteTime());
        long diff = System.currentTimeMillis() - last_time;
        if (diff > TIME_OUT_MILLISECONDS) return true;
        return false;
    }

    @Override
    public HttpRequest getRequest() {
        return current_request;
    }

    @Override
    public HttpResponse getResponse() {
        return current_response;
    }

    public void close() {
        try {
            selectionKey.cancel();
            socketChannel.close();
        } catch (IOException ex) {
            audit.warning(() -> "Client SocketChannel close Exception: " + ex);
        }
    }

}
