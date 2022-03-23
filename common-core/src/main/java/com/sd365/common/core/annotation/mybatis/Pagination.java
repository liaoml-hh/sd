package com.sd365.common.core.annotation.mybatis;

import org.aspectj.lang.annotation.Pointcut;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @class Pagination
 * @classdesc 该注解启用mybatis分页机制 注解实现类调用 pageHelper的startPage 另外设置分页标志到BaseContextHolder
 * @author Administrator
 * @date 2021-1-12  10:51
 * @version 1.0.0
 * @see
 * @since
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Pagination {


}
