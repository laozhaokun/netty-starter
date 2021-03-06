package com.github.cjqcn.netty.starter.common.util;


import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.TimeZone;

/**
 * @author chenjinquan
 * @date 2018年6月13日09:29:26
 */
public final class DateUtil {


    /**
     * 获取默认时间格式: yyyy-MM-dd HH:mm:ss
     */
    private static final DateTimeFormatter DEFAULT_DATETIME_FORMATTER = TimeFormat.LONG_DATE_PATTERN_LINE.formatter;


    /**
     * String 转时间
     *
     * @param timeStr
     * @return
     */
    public static LocalDateTime parseTime(String timeStr) {
        return LocalDateTime.parse(timeStr, DEFAULT_DATETIME_FORMATTER);
    }

    /**
     * String 转时间
     *
     * @param timeStr
     * @param format  时间格式
     * @return
     */
    public static LocalDateTime parseTime(String timeStr, TimeFormat format) {
        return LocalDateTime.parse(timeStr, format.formatter);
    }

    /**
     * 时间转 String
     *
     * @param time
     * @return
     */
    public static String parseTime(LocalDateTime time) {
        return DEFAULT_DATETIME_FORMATTER.format(time);
    }

    /**
     * 时间转 String
     *
     * @param time
     * @param format 时间格式
     * @return
     */
    public static String parseTime(LocalDateTime time, TimeFormat format) {
        return format.formatter.format(time);
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getCurrentDateTime() {
        return DEFAULT_DATETIME_FORMATTER.format(LocalDateTime.now());
    }

    /**
     * 获取当前时间
     *
     * @param format 时间格式
     * @return
     */
    public static String getCurrentDateTime(TimeFormat format) {
        return format.formatter.format(LocalDateTime.now());
    }

    /**
     * 获取当前时间戳(精确到毫秒)
     *
     * @return
     */
    private static long currentTimeMillis() {
        return SystemClock.now();
    }

    /**
     * 获取当前时间戳（精确到秒）
     */
    public static long currentTimeSeconds() {
        return currentTimeMillis() / 1000;
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static LocalDateTime now() {
        return LocalDateTime.now();
    }

    /**
     * 获取当前时间戳
     *
     * @return
     */
    public static Long curTime() {
        return currentTimeMillis();
    }

    /**
     * 获取两个时间点的时间差
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static Duration between(LocalDateTime startTime, LocalDateTime endTime) {
        return Duration.between(startTime, endTime);
    }


    /**
     * 检查某个时间点在两个时间点之间(包括时间点重合)，返回true
     *
     * @param someDateTime  需要判断的某个时间
     * @param startDateTime 开始时间
     * @param endDateTime   结束时间
     * @return
     */
    public static boolean checkSomeDateInTwoDateBetweenRange(final LocalDateTime someDateTime, final LocalDateTime
            startDateTime, final LocalDateTime endDateTime) {
        if (compareDate(endDateTime, startDateTime) < 0) {
            throw new RuntimeException("起始时间大于结束时间");
        }
        if (compareDate(someDateTime, startDateTime) >= 0 && compareDate(endDateTime, someDateTime) >= 0) {
            return true;
        }
        return false;
    }


    /**
     * 比较两个时间点大小<b></>
     *
     * @param date1
     * @param date2
     * @return 0 两个时间点相等，1 date1大于date2， -1 date1小与date2
     */
    public static int compareDate(LocalDateTime date1, LocalDateTime date2) {
        Duration duration = between(date2, date1);
        if (duration.isZero()) {
            return 0;
        }
        if (duration.toNanos() > 0) {
            return 1;
        } else {
            return -1;
        }
    }


    /**
     * 获取当天开始时间
     *
     * @param localDateTime
     * @return
     */
    public static LocalDateTime getDayStart(final LocalDateTime localDateTime) {
        LocalDateTime dateStart = LocalDateTime.of(localDateTime.getYear(), localDateTime.getMonth(), localDateTime
                .getDayOfMonth(), 0, 0);
        return dateStart;
    }


    /**
     * 获取当天结束时间
     *
     * @param localDateTime
     * @return
     */
    public static LocalDateTime getDayEnd(final LocalDateTime localDateTime) {
        LocalDateTime dateEnd = LocalDateTime.of(localDateTime.getYear(), localDateTime.getMonth(), localDateTime
                .getDayOfMonth(), 23, 59);
        return dateEnd;
    }


    /**
     * 获取当月开始时间
     *
     * @param localDateTime
     * @return
     */
    public static LocalDateTime getMonthStart(final LocalDateTime localDateTime) {
        LocalDateTime monthStart = LocalDateTime.of(localDateTime.getYear(), localDateTime.getMonth(), 1, 0, 0);
        return monthStart;
    }


    /**
     * 获取当月结束时间
     *
     * @param localDateTime
     * @return
     */
    public static LocalDateTime getMonthEnd(final LocalDateTime localDateTime) {
        LocalDateTime monthEnd = LocalDateTime.of(localDateTime.getYear(), localDateTime.getMonth().plus(1), 1, 23,
                59);
        monthEnd = monthEnd.minusDays(1);
        return monthEnd;
    }


    /**
     * 获取当年开始时间
     *
     * @param localDateTime
     * @return
     */
    public static LocalDateTime getYearStart(final LocalDateTime localDateTime) {
        LocalDateTime monthStart = LocalDateTime.of(localDateTime.getYear(), 1, 1, 0, 0);
        return monthStart;
    }


    /**
     * 获取当年结束时间
     *
     * @param localDateTime
     * @return
     */
    public static LocalDateTime getYearEnd(final LocalDateTime localDateTime) {
        LocalDateTime monthEnd = LocalDateTime.of(localDateTime.getYear(), 12, 31, 23,
                59);
        return monthEnd;
    }

    /**
     * 校验日期字符串是否符合yyyy-MM-dd HH:mm:ss格式
     *
     * @param dateString
     * @return
     */
    public static boolean checkValidDate(String dateString) {
        try {
            LocalDateTime.parse(dateString, DEFAULT_DATETIME_FORMATTER);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取当天剩余的毫秒数
     *
     * @return int(毫秒)
     */
    public static int getDaySurplusTime() {
        //当前时间毫秒数
        long current = System.currentTimeMillis();
        //今天零点零分零秒的毫秒数
        long zero = current / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();
        //今天23点59分59秒的毫秒数
        long twelve = zero + 24 * 60 * 60 * 1000 - 1;
        Long surplusTime = twelve - current;
        return surplusTime.intValue();
    }

    public static long plusDay(int day) {
        LocalDateTime time = LocalDateTime.now();
        LocalDateTime newTime = time.plus(day, ChronoUnit.DAYS);
        return Timestamp.valueOf(newTime).getTime();
    }

    public static long minusDaySecond(int day) {
        LocalDateTime time = LocalDateTime.now();
        LocalDateTime newTime = time.minus(day, ChronoUnit.DAYS);
        return Timestamp.valueOf(newTime).getTime();
    }


    /**
     * 时间格式
     */
    public enum TimeFormat {

        /**
         * 短时间格式
         */
        SHORT_DATE_PATTERN_LINE("yyyy-MM-dd"),
        SHORT_DATE_PATTERN_SLASH("yyyy/MM/dd"),
        SHORT_DATE_PATTERN_DOUBLE_SLASH("yyyy\\MM\\dd"),
        SHORT_DATE_PATTERN_NONE("yyyyMMdd"),
        SHORT_HHMM_PATTERN("HHmm"),

        /**
         * 长时间格式
         */
        LONG_DATE_PATTERN_LINE("yyyy-MM-dd HH:mm:ss"),
        LONG_DATE_PATTERN_SLASH("yyyy/MM/dd HH:mm:ss"),
        LONG_DATE_PATTERN_DOUBLE_SLASH("yyyy\\MM\\dd HH:mm:ss"),
        LONG_DATE_PATTERN_NONE("yyyyMMdd HH:mm:ss"),

        /**
         * 长时间格式 带毫秒
         */
        LONG_DATE_PATTERN_WITH_MILSEC_LINE("yyyy-MM-dd HH:mm:ss.SSS"),
        LONG_DATE_PATTERN_WITH_MILSEC_SLASH("yyyy/MM/dd HH:mm:ss.SSS"),
        LONG_DATE_PATTERN_WITH_MILSEC_DOUBLE_SLASH("yyyy\\MM\\dd HH:mm:ss.SSS"),
        LONG_DATE_PATTERN_WITH_MILSEC_NONE("yyyyMMdd HH:mm:ss.SSS");

        private transient DateTimeFormatter formatter;

        TimeFormat(String pattern) {
            formatter = DateTimeFormatter.ofPattern(pattern);
        }
    }

    public static void main(String[] args) {
        System.out.println(getCurrentDateTime(TimeFormat.SHORT_HHMM_PATTERN));
        System.out.println(getCurrentDateTime(TimeFormat.LONG_DATE_PATTERN_LINE));
        System.out.println(DateUtil.minusDaySecond(1));
        System.out.println(curTime());
        System.out.println(System.currentTimeMillis());
    }
}

