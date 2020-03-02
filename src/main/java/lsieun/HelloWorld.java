package lsieun;

import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class HelloWorld {
    public static void main(String[] args) {
        byte[] bytes = new byte[] {1,2,3,4};
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        byteBuffer.flip();
        byteBuffer.flip();
        System.out.println(byteBuffer);
    }
}
