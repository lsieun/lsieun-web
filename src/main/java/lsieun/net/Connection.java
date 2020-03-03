package lsieun.net;

import lsieun.utils.AddressUtils;
import lsieun.utils.Const;
import lsieun.utils.PropertyUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public abstract class Connection {
    public final SocketChannel socketChannel;
    public final SelectionKey selectionKey;
    public final String addr;
    public final String host;

    public final long initial_time;
    private long last_read_time;
    private long last_write_time;

    public Connection(SocketChannel socketChannel, SelectionKey selectionKey) {
        this.socketChannel = socketChannel;
        this.selectionKey = selectionKey;
        this.addr = AddressUtils.getAddress(socketChannel);
        this.host = AddressUtils.getHost(socketChannel);
        this.initial_time = System.currentTimeMillis();
        this.last_read_time = initial_time;
        this.last_write_time = initial_time;
    }

    public abstract void data(ByteBuffer buff);

    public abstract ByteBuffer data();

    public boolean isTimeOut() {
        // 对于最近一次传递数据，不超过一分钟
        long last_time = Math.max(getLastReadTime(), getLastWriteTime());
        long timestamp = System.currentTimeMillis();
        long diff = timestamp - last_time;
        if (diff > Const.TIME_MINUTE) return true;

        // 持续的访问时间总长不能超过一小时，例如：下载一个文件，不能超过1小时
        long total_diff = timestamp - initial_time;
        if (diff > Const.TIME_HOUR) return true;
        return false;
    }

    public abstract Request getRequest();

    public abstract Response getResponse();

    public final void updateReadTime() {
        this.last_read_time = System.currentTimeMillis();
    }

    public final void updateWriteTime() {
        this.last_write_time = System.currentTimeMillis();
    }

    public final long getLastReadTime() {
        return this.last_read_time;
    }

    public final long getLastWriteTime() {
        return this.last_write_time;
    }
}
