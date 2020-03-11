package lsieun.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class DateUtils {
    private static final DateFormat df = new SimpleDateFormat("yyyy_MM_dd.HH_mm_ss");
    public static final DateFormat HUMAN_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static final DateFormat GMT_FORMAT = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss zzz");

    static {
        GMT_FORMAT.setTimeZone(TimeZone.getTimeZone("GMT"));
    }

    public static String getTimestamp() {
        Date now = new Date();
        return df.format(now);
    }

    public static Date addMinutes(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(date.getTime());
        cal.add(Calendar.MINUTE, n);
        return cal.getTime();
    }

    public static Date addDays(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(date.getTime());
        cal.add(Calendar.DAY_OF_MONTH, n);
        return cal.getTime();
    }

    public static Date addMonths(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(date.getTime());
        cal.add(Calendar.MONTH, n);
        return cal.getTime();
    }

    public static Date addYears(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(date.getTime());
        cal.add(Calendar.YEAR, n);
        return cal.getTime();
    }

    public static String getGMTFormat(long timestamp) {
        Date date = new Date(timestamp);
        return GMT_FORMAT.format(date);
    }

    public static String getGMTFormat(Date date) {
        return GMT_FORMAT.format(date);
    }

    public static String getHumanFormat(long timestamp) {
        Date date = new Date(timestamp);
        return HUMAN_FORMAT.format(date);
    }

    public static String getHumanFormat(Date date) {
        return HUMAN_FORMAT.format(date);
    }

    public static Date getToday(int hour, int minute) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, 0);
        Date today = cal.getTime();
        return today;
    }

    public static Date getNextDay(int hour, int minute) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, 0);
        Date today = cal.getTime();
        return today;
    }

    public static long diff(Date fromDate, Date toDate) {
        long delta = toDate.getTime() - fromDate.getTime();
        return delta / 1000;
    }

    public static boolean betweenTime(int startHour, int startMinute, int endHour, int endMinute) {
        Date now = new Date();
        Date startTime = DateUtils.getToday(startHour, startMinute);
        Date endTime = DateUtils.getToday(endHour, endMinute);
        if (startTime.before(now) && endTime.after(now)) {
            return true;
        }
        return false;
    }
}
