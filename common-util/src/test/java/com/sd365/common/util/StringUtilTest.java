package com.sd365.common.util;


import org.junit.Test;

import static org.junit.Assert.*;

public class StringUtilTest {

    @Test
    public void isEmpty() {
        assertTrue(StringUtil.isEmpty(null));
        assertTrue(StringUtil.isEmpty(""));
    }

    @Test
    public void isNotEmpty() {
        assertTrue(StringUtil.isNotEmpty(null) == false);
        assertTrue(StringUtil.isNotEmpty("") == false);
    }

    @Test
    public void trim() {
        assertEquals(StringUtil.trim(" 123 "),"123");
    }

    @Test
    public void testEquals() {
        assertTrue(StringUtil.equals("123", "123"));
    }

    @Test
    public void equalsIgnoreCase() {
        assertTrue(StringUtil.equalsIgnoreCase("123A", "123a"));
    }

    @Test
    public void indexOf() {
        String str = "abcdddfg";
        System.out.println(StringUtil.indexOf(str,'d'));
    }

    @Test
    public void contains() {
        assertTrue(StringUtil.contains("abcdef", 'e'));
    }

    @Test
    public void substring() {
        assertEquals(StringUtil.substring("123456789",5),"6789");
    }

    @Test
    public void split() {
        String str = "a b c";
        String [] strings = StringUtil.split(str, " ");
        assertEquals(strings[0], "a");
        assertEquals(strings[1], "b");
        assertEquals(strings[2], "c");
    }

    @Test
    public void join() {
        assertEquals(StringUtil.join("123", "456"),"123456");
    }

    @Test
    public void reverse() {
        assertEquals(StringUtil.reverse("123"),"321");
    }

}