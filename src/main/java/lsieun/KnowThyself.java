package lsieun;

import lsieun.net.http.HttpConnection;
import lsieun.net.http.utils.HTMLUtils;
import lsieun.net.utils.BlackListUtils;
import lsieun.utils.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.*;
import java.util.logging.Level;

import static lsieun.utils.LogUtils.audit;

public class KnowThyself {
    private static final int HTTP_PORT = PropertyUtils.getInt("http.port");
    private static final Map<SocketChannel, HttpConnection> dataMap = new HashMap<>();

    public static void main(String[] args) {
        try {
            BannerUtils.display();
            audit.info(() -> "Listening for connections on port " + HTTP_PORT);
            audit.info(() -> String.format("Web Server: http://127.0.0.1:%d/", HTTP_PORT));

            ServerSocketChannel serverChannel;
            Selector selector;
            try {
                serverChannel = ServerSocketChannel.open();
                ServerSocket ss = serverChannel.socket();
                InetSocketAddress address = new InetSocketAddress(HTTP_PORT);
                ss.bind(address);
                serverChannel.configureBlocking(false);
                selector = Selector.open();
                serverChannel.register(selector, SelectionKey.OP_ACCEPT);
            } catch (Exception ex) {
                audit.log(Level.SEVERE, "unexpected error: " + ex.getMessage(), ex);
                return;
            }


            while (true) {
                checkTimeout(null);

                try {
                    selector.select();
                } catch (IOException ex) {
                    audit.log(Level.SEVERE, "unexpected error: " + ex.getMessage(), ex);
                    break;
                }

                Set<SelectionKey> readyKeys = selector.selectedKeys();
                checkTimeout(readyKeys);

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
                    } catch (Exception ex) {
                        key.cancel();
                        try {
                            SelectableChannel channel = key.channel();
                            if (channel instanceof SocketChannel) {
                                // 单独处理SocketChannel，是为了复用doClose方法
                                SocketChannel sc = (SocketChannel) channel;
                                doClose(sc, ex.getMessage());
                            } else {
                                // 如果不是SocketChannel，那就会是ServerSocketChannel，
                                // 但是我觉得，这样的事情应该不会发生
                                channel.close();
                            }
                        } catch (IOException cex) {
                        }
                    }
                }
            }
        } catch (Throwable th) {
            audit.log(Level.SEVERE, "record possible error: " + th.getMessage(), th);
        }
    }

    private static void checkTimeout(Set<SelectionKey> readyKeys) {
        Set<SocketChannel> readySocketChannels = new HashSet<>();
        if (readyKeys != null) {
            for (SelectionKey key : readyKeys) {
                SelectableChannel channel = key.channel();
                if (channel instanceof SocketChannel) {
                    readySocketChannels.add((SocketChannel) channel);
                }
            }
        }

        Iterator<Map.Entry<SocketChannel, HttpConnection>> dataIt = dataMap.entrySet().iterator();
        List<SocketChannel> timeout_list = new ArrayList<>();
        List<SocketChannel> black_list = new ArrayList<>();
        while (dataIt.hasNext()) {
            Map.Entry<SocketChannel, HttpConnection> entry = dataIt.next();
            SocketChannel sc = entry.getKey();
            HttpConnection conn = entry.getValue();

            if (BlackListUtils.isBlack(conn.host)) {
                black_list.add(sc);
                continue;
            }

            if (readySocketChannels.contains(sc)) continue;

            if (conn.isTimeOut()) {
                timeout_list.add(sc);
            }
        }

        for (SocketChannel sc : black_list) {
            doClose(sc, "blacklist");
        }

        for (SocketChannel sc : timeout_list) {
            doClose(sc, "server timeout");
        }
    }

    private static void createChannel(ServerSocketChannel serverSocketChannel, SelectionKey selectionKey) throws IOException {
        audit.entering("KnowThyself", "createChannel");
        SocketChannel socketChannel = serverSocketChannel.accept();
        socketChannel.configureBlocking(false);

        SelectionKey client_key = socketChannel.register(selectionKey.selector(), SelectionKey.OP_READ);// selector pointing to READ operation
        ByteBuffer buf = ByteBuffer.allocate(Const.BUFFER_SIZE);
        client_key.attach(buf);

        // store socket connection
        HttpConnection conn = new HttpConnection(socketChannel, client_key);
        dataMap.put(socketChannel, conn);

        // log
        audit.info(() -> String.format("Accepted connection from %s", conn.addr));
        audit.info(() -> "Total clients connected: " + dataMap.size());

        if (BlackListUtils.isBlack(conn.host)) {
            audit.warning(() -> "Black Host Found: " + conn.host);
            doClose(socketChannel, "blacklist");
        }
        audit.exiting("KnowThyself", "createChannel");
    }

    private static void doRead(SelectionKey selectionKey) throws IOException {
        audit.entering("KnowThyself", "doRead");
        // 第1步，定义变量
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        ByteBuffer byteBuffer = (ByteBuffer) selectionKey.attachment();
        HttpConnection conn = dataMap.get(socketChannel);

        // 第2步，尝试从客户端读取数据
        byteBuffer.clear();
        int read = socketChannel.read(byteBuffer);
        if (read > 0) {
            byteBuffer.flip(); // put buffer in read mode by setting pos=0 and lim=numberOfBytes
            conn.data(byteBuffer);
            conn.updateReadTime();

            // 第3步，只有在新数据读入的情况下，才切换状态（interest）
            selectionKey.interestOps(SelectionKey.OP_WRITE); // set mode to WRITE to send data
        }

        // 第4步，记录日志
        audit.finer(() -> String.format("Read %s bytes from %s", read, conn.addr));

        // 第5步，如果客户端关闭了连接，服务端也进行关闭
        if (read == -1) { // if connection is closed by the client
            doClose(socketChannel, "client");
        }
        audit.exiting("KnowThyself", "doRead");
    }

    private static void doWrite(SelectionKey selectionKey) throws IOException {
        audit.entering("KnowThyself", "doWrite");
        // 第1步，定义变量
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        HttpConnection conn = dataMap.get(socketChannel);
        int response_length; // 用于日志

        // 第2步，尝试向客户端返回数据
        ByteBuffer buff = conn.data();
        if (buff != null && buff.hasRemaining()) {
            response_length = socketChannel.write(buff);
        } else {
            // 第3步，切换状态（interest）
            response_length = 0;
            selectionKey.interestOps(SelectionKey.OP_READ); // change the key to READ
        }
        conn.updateWriteTime();

        // 第4步，记录日志
        audit.finer(() -> String.format("Write %s bytes to %s", response_length, conn.addr));
        audit.exiting("KnowThyself", "doWrite");
    }

    private static void doClose(SocketChannel socketChannel, String reason) {
        HttpConnection conn = dataMap.get(socketChannel);
        dataMap.remove(socketChannel);
        conn.close();

        // log
        audit.info(() -> String.format("Connection closed by %s: %s", reason, conn.addr));
    }
}
