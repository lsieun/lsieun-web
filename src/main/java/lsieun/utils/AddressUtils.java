package lsieun.utils;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;

public class AddressUtils {
    public static String getAddress(SocketChannel socketChannel) {
        SocketAddress socketAddress = socketChannel.socket().getRemoteSocketAddress();
        if (socketAddress instanceof InetSocketAddress) {
            InetSocketAddress addr = (InetSocketAddress) socketAddress;
            return String.format("%s:%s", addr.getHostName(), addr.getPort());
        }
        return "Unknown Host";
    }

    public static String getHost(SocketChannel socketChannel) {
        SocketAddress socketAddress = socketChannel.socket().getRemoteSocketAddress();
        if (socketAddress instanceof InetSocketAddress) {
            InetSocketAddress addr = (InetSocketAddress) socketAddress;
            return addr.getHostName();
        }
        return "Unknown_Host";
    }
}
