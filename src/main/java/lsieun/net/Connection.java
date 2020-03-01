package lsieun.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;

public abstract class Connection {
    public final SocketAddress remoteAddress;
    public final SocketChannel channel;

    public Connection(SocketChannel channel) {
        this.channel = channel;
        this.remoteAddress = channel.socket().getRemoteSocketAddress();
    }

    public String getHost() {
        InetSocketAddress addr = (InetSocketAddress) remoteAddress;
        return addr.getHostName();
    }

    public int getPort() {
        InetSocketAddress addr = (InetSocketAddress) remoteAddress;
        return addr.getPort();
    }

    public abstract int read() throws IOException;

    public abstract void process() throws IOException;
}
