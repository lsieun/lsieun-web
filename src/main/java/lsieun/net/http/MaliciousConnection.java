package lsieun.net.http;

import lsieun.net.Connection;
import lsieun.net.Request;
import lsieun.net.Response;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public class MaliciousConnection extends Connection {

    public MaliciousConnection(SocketChannel socketChannel, SelectionKey selectionKey) {
        super(socketChannel, selectionKey);
    }

    @Override
    public void data(ByteBuffer buff) {

    }

    @Override
    public ByteBuffer data() {
        return null;
    }

    @Override
    public boolean isTimeOut() {
        return false;
    }

    @Override
    public Request getRequest() {
        return null;
    }

    @Override
    public Response getResponse() {
        return null;
    }
}
