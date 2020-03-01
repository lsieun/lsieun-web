package lsieun;

import lsieun.net.http.HttpConnection;
import lsieun.utils.AddressUtils;
import lsieun.utils.PropertyUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import static lsieun.utils.LogUtils.audit;
import static lsieun.utils.LogUtils.err;

public class KnowThyself {

    public static void main(String[] args) {
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
        } catch (IOException ex) {
            err.severe(() -> "unexpected error: " + ex);
            return;
        }

        while (true) {
            try {
                selector.select();
            } catch (IOException ex) {
                err.severe(() -> "unexpected error: " + ex);
                break;
            }
            Set<SelectionKey> readyKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = readyKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                try {
                    if (key.isAcceptable()) {
                        ServerSocketChannel server = (ServerSocketChannel) key.channel();
                        SocketChannel client = server.accept();
                        audit.info(String.format("Accepted connection from %s", AddressUtils.getAddress(client)));

                        client.configureBlocking(false);
                        SelectionKey client_key = client.register(selector, SelectionKey.OP_READ);
                        HttpConnection conn = new HttpConnection(client);
                        client_key.attach(conn);
                    } else if (key.isReadable()) {
                        SocketChannel client = (SocketChannel) key.channel();
                        String addr = AddressUtils.getAddress(client);
                        audit.info(() -> String.format("Readable connection from %s", addr));

                        HttpConnection conn = (HttpConnection) key.attachment();
                        int len = conn.read();
                        audit.fine(() -> String.format("Read %s bytes from %s", len, addr));

                        if (len > 0) {
                            audit.fine(() -> String.format("Processing connection from %s", addr));
                            conn.process();
                        }
                        if (len == -1) {
                            key.cancel();
                            audit.info(String.format("Cancel connection from %s", addr));
                        }
                    }
                    // TODO: 将来这里可能开放
//                    else if (key.isWritable()) {
//                        SocketChannel client = (SocketChannel) key.channel();
//                        String addr = AddressUtils.getAddress(client);
//                        audit.info(String.format("Writable from %s", addr));
//                    }
                } catch (IOException ex) {
                    key.cancel();
                    try {
                        // TODO: 这里只有出现异常的时候，才会关闭，那么正常情况下，怎么关闭呢？
                        key.channel().close();
                    } catch (IOException cex) {
                    }
                }
            }
        }
    }
}
