package com.lod.brightstream.utils;

import java.sql.Date;
import java.sql.Timestamp;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import org.junit.Test;


public class TimeCalcTest {
    
    @Test
    public void testMeaningfulTimePeriod_10hoursAnd5minutes() {
                                                 //year, m, d, h, m, s, nano
        Timestamp initialTimestamp = new Timestamp(2012, 2, 5, 2, 5, 20, 20);
        Timestamp finalTimestamp =   new Timestamp(2012, 2, 5, 12, 10, 20, 20);
        
        String meaningfulDiff = TimeCalc.getMeaningfulTimePeriod(new Date(initialTimestamp.getTime()), new Date(finalTimestamp.getTime()));
        
        assertEquals("10 hours and 5 minutes", meaningfulDiff);
    }
    
    @Test
    public void testMeaningfulTimePeriod_1dayAnd1hour() {
                                                 //year, m, d, h, m, s, nano
        Timestamp initialTimestamp = new Timestamp(2012, 2, 5, 2, 5, 20, 20);
        Timestamp finalTimestamp =   new Timestamp(2012, 2, 6, 3, 10, 20, 20);
        
        String meaningfulDiff = TimeCalc.getMeaningfulTimePeriod(new Date(initialTimestamp.getTime()), new Date(finalTimestamp.getTime()));
        
        assertEquals("1 day and 1 hour", meaningfulDiff);
    }
    
    @Test
    public void testMeaningfulTimePeriod_2monthsAnd3days() {
                                                 //year, m, d, h, m, s, nano
        Timestamp initialTimestamp = new Timestamp(2012, 2, 5, 2, 5, 20, 20);
        Timestamp finalTimestamp =   new Timestamp(2012, 4, 8, 12, 10, 20, 20);
        
        String meaningfulDiff = TimeCalc.getMeaningfulTimePeriod(new Date(initialTimestamp.getTime()), new Date(finalTimestamp.getTime()));
        
        assertEquals("2 months and 3 days", meaningfulDiff);
    }
    
    @Test
    public void testMeaningfulTimePeriod_2yearsAnd1month() {
                                                 //year, m, d, h, m, s, nano
        Timestamp initialTimestamp = new Timestamp(2012, 2, 5, 2, 5, 20, 20);
        Timestamp finalTimestamp =   new Timestamp(2014, 3, 5, 12, 10, 20, 20);
        
        String meaningfulDiff = TimeCalc.getMeaningfulTimePeriod(new Date(initialTimestamp.getTime()), new Date(finalTimestamp.getTime()));
        
        assertEquals("2 years and 1 month", meaningfulDiff);
    }
    
    @Test
    public void itShouldThrowIllegalArgumentException() {
        
        assertThrows(IllegalArgumentException.class, () -> {
                                                     //year, m, d, h, m, s, nano
            Timestamp initialTimestamp = new Timestamp(2016, 2, 5, 2, 5, 20, 20);
            Timestamp finalTimestamp =   new Timestamp(2014, 3, 5, 12, 10, 20, 20);
           
            TimeCalc.getMeaningfulTimePeriod(new Date(initialTimestamp.getTime()), new Date(finalTimestamp.getTime()));
        });
        
    }
    
    @Test
    public void testMeaningfulTimePerioud_52minutes() {
                                                  //year, m, d,  h,  m, s, nano
        Timestamp initialTimestamp = new Timestamp(2020, 10, 21, 13, 8, 52, 0);
        Timestamp finalTimestamp =   new Timestamp(2020, 10, 21, 14, 2, 36, 0);
        
        String meaningfulDiff = TimeCalc.getMeaningfulTimePeriod(new Date(initialTimestamp.getTime()), new Date(finalTimestamp.getTime()));
        
        assertEquals("0 hours and 53 minutes", meaningfulDiff);
    }
    
}
