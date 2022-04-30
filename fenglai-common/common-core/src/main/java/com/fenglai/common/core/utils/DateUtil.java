package com.fenglai.common.core.utils;

import org.joda.time.DateTime;
import org.joda.time.Instant;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.time.YearMonth;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @description: 日期与时间处理
 *  <joda-time></joda-time>实现
 *
 * @author: TJ
 * @date:  2022-04-28
 **/
public class DateUtil {

    private static final String[] PARSE_PATTERNS = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    /**
     * 获取当前时间戳
     */
    public static Long currentSeconds() {
        return Instant.now().getMillis() / 1000;
    }

    /**
     * 获取毫秒值
     */
    public static Long currentMills() {
        return Instant.now().getMillis();
    }

    /**
     * 获取当前日期
     */
    public static String currentDate() {
        return currentDate("yyyy-MM-dd");
    }

    /**
     * 获取当前日期时间
     */
    public static String currentDateTime() {
        return currentDate("yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 获取当前日期
     * @param format 指定格式
     */
    public static String currentDate(String format) {
        DateTime dateTime = DateTime.now();
        return dateTime.toString(format);
    }

    public static String toString(Long source) {
        return toString(source,"yyyy-MM-dd");
    }

    /**
     * 时间戳格式化
     * @param source 时间戳
     * @param format 指定格式
     * @return 指定格式的字符串
     */
    public static String toString(Long source, String format) {
        DateTime dateTime = new DateTime(source * 1000);
        return dateTime.toString(format);
    }

    public static Long toLong(String source) {
        return toLong(source, "yyyy-MM-dd");
    }

    /**
     * 日期转绝对秒
     * @param source 输入日期
     * @param sourceFormat 输入日期格式
     * @return 时间戳
     */
    public static Long toLong(String source, String sourceFormat) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern(sourceFormat);
        return DateTime.parse(source, formatter).toInstant().getMillis() / 1000;
    }

    /**
     * 往前推 minusMonths 个月份, 并且连续往前推 limit 次数
     * @param minusMonths 往前推算的月份数
     * @param limit 连续往前推算的个数
     */
    public static List<String> minusMonthSeries(int minusMonths, int limit) {
        return Stream.iterate(YearMonth.now(), yearMonth -> yearMonth.minusMonths(minusMonths))
                .limit(limit)
                .sorted(Comparator.comparing(YearMonth::getYear).thenComparing(YearMonth::getMonthValue))
                .map(YearMonth::toString)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {

    }
}
