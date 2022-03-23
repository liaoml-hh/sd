/**
 * @file:  StringUtil.java
 * @author:  zhang_yulin
 * @date:  2020/08/26
 * @copyright:  2020-2023 www.bosssoft.com.cn Inc. All rights reserved.
 */
package com.sd365.common.util;

//import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @class:  StringUtil
 * @classdesc:  字符串操作工具类
 * @author:  zhang_yulin
 * @date:  2020/8/26
 * @version:  1.0.0
 * @see
 * @since
 */
public class StringUtil {

    //判断字符串是否为""或者null
    public static boolean isEmpty(String str){
        return StringUtils.isEmpty(str);
    }

    //判断字符串是否为非""或非null
    public static boolean isNotEmpty(String str){
        return StringUtils.isNotEmpty(str);
    }

    //任意一个参数为空的话，返回true，如果这些参数都不为空的话返回false
    public static boolean isAnyEmpty(String... strings){
        return StringUtils.isAnyEmpty();
    }

    //任意一个参数是空，返回false，所有参数都不为空，返回true
    public static boolean isNoneEmpty(String... strings){
        return StringUtils.isNoneEmpty();
    }

    //判断字符对象是不是空字符串
    public static boolean isBlank(String str){
        return StringUtils.isBlank(str);
    }

    //判断字符对象是不是非空字符串
    public static boolean isNotBlank(String str){
        return StringUtils.isNotBlank(str);
    }

    //任意一个字符串对象为空字符串的话，返回true，都不为空字符串返回false
    public static boolean isAnyBlank(String... strings){
        return StringUtils.isAnyBlank(strings);
    }

    //任意一个字符串对象为空字符串的话，返回false，都不为空字符串，返回true
    public static boolean isNoneBlank(String... strings){
        return StringUtils.isNoneBlank(strings);
    }

    //移除字符串两端的空字符串
    public static String trim(String str){
        return StringUtils.trim(str);
    }

    //字符串比对方法，两个比较的字符串都能为空，不会报空指针异常
    public static boolean equals(String str1, String str2){
        return StringUtils.equals(str1, str2);
    }

    //字符串比对方法，忽略大小写
    public static boolean equalsIgnoreCase(String str1, String str2){
        return StringUtils.equalsIgnoreCase(str1, str2);
    }

    //查找字符对应字符串中首次出现的下标位置
    public static int indexOf(String str, int searchChar){
        return StringUtils.indexOf(str, searchChar);
    }

    //字符串在另外一个字符串里，出现第ordinal次的位置
    public static int ordinalIndexOf(String str, String searchStr, int ordinal){
        return  StringUtils.ordinalIndexOf(str, searchStr, ordinal);
    }

    //字符最后一次出现的位置
    public static int lastIndexOf(String str, int searchChar){
        return StringUtils.lastIndexOf(str, searchChar);
    }

    //字符串searchStr在str里面出现倒数第ordinal出现的位置
    public static int lastOrdinalIndexOf(String str, String searchStr, int ordinal){
        return StringUtils.lastOrdinalIndexOf(str, searchStr, ordinal);
    }

    //字符串str是否包含searchChar
    public static boolean contains(String str, int searchChar){
        return StringUtils.contains(str, searchChar);
    }


    //字符串str包含后面数组中的任意对象，返回true
    public static boolean containsAny(String str, char... searchChars){
        return StringUtils.containsAny(str, searchChars);
    }

    //字符串截取
    public static String substring(String str, int start){
        return StringUtils.substring(str, start);
    }

    //字符串截取
    public static String substring(String str, int start, int end){
        return StringUtils.substring(str, start, end);
    }

    //字符串分割
    public static String[] split(String str, String separatorChars){
        return StringUtils.split(str, separatorChars);
    }

    //字符串连接
    public static <T> String join(T... elements){
        return StringUtils.join(elements);
    }

    //将数组相邻元素间插入特定字符并返回所得字符串
    public static String join(Object[] array, char separator){
        return StringUtils.join(array, separator);
    }

    //将数组相邻元素间插入特定字符串并返回所得字符串
    public static String join(Object[] array, String separator){
        return StringUtils.join(array, separator);
    }

    //删除空格
    public static String deleteWhitespace(String str){
        return StringUtils.deleteWhitespace(str);
    }

    //删除以特定字符串开头的字符串，如果没有的话，就不删除
    public static String removeStart(String str, String remove){
        return StringUtils.removeStart(str, remove);
    }

    //字符串右边自动以padChar补齐
    public static String rightPad(String str,int size,char padChar){
        return StringUtils.rightPad(str,size,padChar);
    }

    //左边自动补齐
    public static String leftPad(String str, int size,char padChar){
        return StringUtils.leftPad(str, size, padChar);
    }

    //首字母大写
    public static String capitalize(String str){
        return StringUtils.capitalize(str);
    }

    //反向大小写
    public static String swapCase(String str){
        return StringUtils.swapCase(str);
    }

    //判断字符串是否由字母组成
    public static boolean isAlpha(String str){
        return StringUtils.isAlpha(str);
    }

    //字符串翻转
    public static String reverse(String str){
        return StringUtils.reverse(str);
    }

    // 包装，用后面的字符串对前面的字符串进行包装
    public static String wrap(String str, char wrapWith){
        return StringUtils.wrap(str, wrapWith);
    }

}
