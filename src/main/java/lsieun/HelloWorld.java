package lsieun;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class HelloWorld {
    public static void main(String[] args) {
        Date now = new Date();
        System.out.println(now);

        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        System.out.println("GMT Time  : " + sdf.format(now));
    }
}
