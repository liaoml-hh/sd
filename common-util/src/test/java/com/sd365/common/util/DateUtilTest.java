package com.sd365.common.util;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class DateUtilTest {

    @Test
    public void isSameDay() {
        Date date = new Date();
        assertTrue(DateUtil.isSameDay(date, date));
    }

    @Test
    public void compareDate() {
        Date date = new Date();
        assertTrue(DateUtil.compareDate(date, date) == 0);
    }
}