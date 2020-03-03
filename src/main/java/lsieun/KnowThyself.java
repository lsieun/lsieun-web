package lsieun;

import lsieun.net.http.HttpConnection;
import lsieun.utils.Const;
import lsieun.utils.HTMLUtils;
import lsieun.utils.PropertyUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.*;
import java.util.logging.Level;

import static lsieun.utils.LogUtils.audit;
import static lsieun.utils.LogUtils.err;

public class KnowThyself {

    private static Map<SocketChannel, HttpConnection> dataMap = new HashMap<>();

    public static void main(String[] args) {
        HTMLUtils.generateStaticHTML();

        int port = PropertyUtils.getInt("http.port");

        audit.info(() -> "Listening for connections on port " + port);

        ServerSocketChannel serverChannel;
        Selector selector;
        try {
            serverChannel = ServerSocketChannel.open();
            ServerSocket ss = serverChannel.socket();
            InetSocketAddress address = new InetSocketAddress(port);
            ss.bind(address);
            serverChannel.configureBlocking(false);
            selector = Selector.open();
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (Exception ex) {
            err.log(Level.SEVERE, "unexpected error: " + ex.getMessage(), ex);
            return;
        }

        while (true) {
            checkTimeout();
            audit.info(() -> "Total clients connected: " + dataMap.size());
            try {
                selector.select();
            } catch (IOException ex) {
                err.log(Level.SEVERE, "unexpected error: " + ex.getMessage(), ex);
                break;
            }

            Set<SelectionKey> readyKeys = selector.selectedKeys();
            Iterator<SelectionKey> it = readyKeys.iterator();
            while (it.hasNext()) {
                SelectionKey key = it.next();
                it.remove();
                try {
                    if (key.isAcceptable()) {
                        createChannel(serverChannel, key);
                    } else if (key.isReadable()) {
                        doRead(key);
                    } else if (key.isWritable()) {
                        doWrite(key);
                    }
                } catch (IOException ex) {
                    key.cancel();
                    try {
                        key.channel().close();
                    } catch (IOException cex) {
                    }
                }
            }
        }
    }

    private static void checkTimeout() {
        Iterator<Map.Entry<SocketChannel, HttpConnection>> dataIt = dataMap.entrySet().iterator();
        List<SocketChannel> timeout_list = new ArrayList<>();
        while (dataIt.hasNext()) {
            Map.Entry<SocketChannel, HttpConnection> entry = dataIt.next();
            SocketChannel sc = entry.getKey();
            HttpConnection conn = entry.getValue();
            if (conn.isTimeOut()) {
                timeout_list.add(sc);
            }
        }

        for (SocketChannel sc : timeout_list) {
            doClose(sc, "server timeout");
        }
    }

    private static void createChannel(ServerSocketChannel serverSocketChannel, SelectionKey selectionKey) throws IOException {
        SocketChannel socketChannel = serverSocketChannel.accept();
        socketChannel.configureBlocking(false);

        SelectionKey client_key = socketChannel.register(selectionKey.selector(), SelectionKey.OP_READ);// selector pointing to READ operation
        ByteBuffer buf = ByteBuffer.allocate(Const.BUFFER_SIZE);
        client_key.attach(buf);

        // store socket connection
        HttpConnection conn = new HttpConnection(socketChannel, client_key);
        dataMap.put(socketChannel, conn);

        // log
        audit.info(() -> String.format("Accepted connection from %s, total clients = %s", conn.addr, dataMap.size()));
    }


    private static void doRead(SelectionKey selectionKey) throws IOException {
        // 第1步，定义变量
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        ByteBuffer byteBuffer = (ByteBuffer) selectionKey.attachment();
        HttpConnection conn = dataMap.get(socketChannel);

        // 第2步，尝试从客户端读取数据
        byteBuffer.clear();
        int read = socketChannel.read(byteBuffer);
        if (read > 0) {
            byteBuffer.flip(); // put buffer in read mode by setting pos=0 and lim=numberOfBytes
            conn.read(byteBuffer);
            conn.updateReadTime();

            // 第3步，只有在新数据读入的情况下，才切换状态（interest）
            selectionKey.interestOps(SelectionKey.OP_WRITE); // set mode to WRITE to send data
        }

        // 第4步，记录日志
        audit.info(() -> String.format("Read %s bytes from %s", read, conn.addr));

        // 第5步，如果客户端关闭了连接，服务端也进行关闭
        if (read == -1) { // if connection is closed by the client
            doClose(socketChannel, "client");
        }
    }

    private static void doWrite(SelectionKey selectionKey) throws IOException {
        // 第1步，定义变量
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        HttpConnection conn = dataMap.get(socketChannel);
        int response_length; // 用于日志

        // 第2步，尝试向客户端返回数据
//        byte[] response_bytes = conn.process();
//        if (response_bytes != null) {
//            response_length = response_bytes.length;
//
//            ByteBuffer buf = ByteBuffer.wrap(response_bytes);
//            while (buf.hasRemaining() && socketChannel.write(buf) != -1) {
//                audit.fine(() ->
//                        String.format("position=%s, limit=%s, capacity=%s",
//                                buf.position(),
//                                buf.limit(),
//                                buf.capacity())
//                );
//            }
//        } else {
//            response_length = 0;
//        }
//        conn.updateWriteTime();
//
//        // 第3步，切换状态（interest）
//        selectionKey.interestOps(SelectionKey.OP_READ); // change the key to READ
        ByteBuffer buff = conn.getResponse();
        if (buff != null && buff.hasRemaining()) {
            response_length = socketChannel.write(buff);
        }else {
            response_length = 0;
            selectionKey.interestOps(SelectionKey.OP_READ); // change the key to READ
        }
        conn.updateWriteTime();

        // 第4步，记录日志
        audit.info(() -> String.format("Write %s bytes to %s", response_length, conn.addr));
    }

    private static void doClose(SocketChannel socketChannel, String reason) {
        HttpConnection conn = dataMap.get(socketChannel);
        dataMap.remove(socketChannel);
        conn.close();

        // log
        audit.info(() -> String.format("Connection closed by %s: %s", reason, conn.addr));
    }
}
