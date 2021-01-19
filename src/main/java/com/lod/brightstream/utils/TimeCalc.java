/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lod.brightstream.utils;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Period;
import java.util.concurrent.TimeUnit;


public class TimeCalc {
    
    public static String getMeaningfulTimePeriod(Timestamp initialTimestamp, Timestamp finalTimestamp) {
        Date initialDate = new Date(initialTimestamp.getTime());
        Date finalDate = new Date(finalTimestamp.getTime());
        return getMeaningfulTimePeriod(initialDate, finalDate);
    }
    
    public static String getMeaningfulTimePeriod(Date initialDate, Date finalDate) {
        
        Timestamp i = new Timestamp(initialDate.getTime());
        Timestamp f = new Timestamp(finalDate.getTime());
        
        if (initialDate.after(finalDate)) throw new IllegalArgumentException(
           String.format("Initial date is after the final date. Initial date:%s, Final date:%s", i.toString(), f.toString())         
        );
        
        long diffInNanos = finalDate.getTime() - initialDate.getTime();
        Period period = Period.between(initialDate.toLocalDate(), finalDate.toLocalDate());
        
        return getMeaningfulString(diffInNanos, period);
    }
    
    static String getMeaningfulString(long timeDiff, Period period) {
        
        long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(timeDiff);
        long diffInHours = TimeUnit.MILLISECONDS.toHours(timeDiff);
        
        long diffInDays = period.getDays();
        long diffInMonths = period.getMonths();
        long diffInYears = period.getYears();
        
        if (diffInYears > 0) return getYearMonthPeriod(diffInYears, diffInMonths);
        
        if (diffInMonths > 0) return getMonthDayPeriod(diffInMonths, period.minusMonths(diffInMonths).getDays());
        
        if (diffInDays > 0) return getDayHourPeriod(diffInDays, diffInHours % 24);
        
        return getHourMinutePeriod(diffInHours, diffInMinutes % 60);
        
    }
    
    private static String getYearMonthPeriod(long years, long months) {
        return compileStringPeriod(years, months, new String[]{"year", "month"});
    }
    
    private static String getMonthDayPeriod(long months, long days) {
        return compileStringPeriod(months, days, new String[]{"month", "day"});
    }
    
    private static String getDayHourPeriod(long days, long hours) {
        return compileStringPeriod(days, hours, new String[]{"day", "hour"});
    }
    
    private static String getHourMinutePeriod(long hours, long minutes) {
        return compileStringPeriod(hours, minutes, new String[]{"hour", "minute"});
    }
    
    private static String compileStringPeriod(long timeUnit1, long timeUnit2, String[] names) {
        
        StringBuilder stringBuilder = new StringBuilder();
        
        stringBuilder.append(Long.toString(timeUnit1));
        stringBuilder.append(timeUnit1 == 1 ? (" " + names[0]) : (" " + names[0] + "s"));
        
        stringBuilder.append(" and ");
        
        stringBuilder.append(Long.toString(timeUnit2));
        stringBuilder.append(timeUnit2 == 1 ? (" " + names[1]) : (" " + names[1] + "s"));
        
        return stringBuilder.toString(); 
    }
    
}
