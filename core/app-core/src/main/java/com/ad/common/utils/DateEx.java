package com.ad.common.utils;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/**
 * @author CoderYoung
 */
public class DateEx {

    private final Calendar calendar = Calendar.getInstance();

    public DateEx() {
        this.calendar.setTime(new Date());
    }

    public DateEx(String dateStr) {
        this.calendar.setTime(DateUtil.parse(dateStr));
    }

    public DateEx(String dateStr, String pattern) {
        this.parse(dateStr, pattern);
    }

    public DateEx parse(String dateStr, String pattern) {
        this.calendar.setTime(DateUtil.parse(dateStr, pattern));
        return this;
    }

    public DateEx setDate(Date date) {
        this.calendar.setTime(date);
        return this;
    }

    public static DateEx now() {
        return new DateEx();
    }

    public DateEx addDay(int days) {
        calendar.add(Calendar.DAY_OF_YEAR, days);
        return this;
    }

    public DateEx addMonth(int months) {
        calendar.add(Calendar.MONTH, months);
        return this;
    }

    public DateEx addYear(int years) {
        calendar.add(Calendar.YEAR, years);
        return this;
    }

    public DateEx getStartOfDay() {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return this;
    }

    public DateEx getEndOfDay() {
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return this;
    }

    public Date getDate() {
        return calendar.getTime();
    }

    public LocalDateTime getLocalDateTime() {
        return LocalDateTime.ofInstant(calendar.toInstant(), ZoneId.systemDefault());
    }

    public LocalDate getLocalDate() {
        return getLocalDateTime().toLocalDate();
    }

    public long getSeconds() {
        return calendar.getTimeInMillis() / 1000;
    }

    public long getMillis() {
        return calendar.getTimeInMillis();
    }

    public String format() {
        return format(DatePattern.NORM_DATETIME_PATTERN);
    }

    public String format(String pattern) {
        return DateUtil.format(calendar.getTime(), pattern);
    }


}
