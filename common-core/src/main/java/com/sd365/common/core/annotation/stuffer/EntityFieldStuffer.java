/**
 * @file: EntityFieldStuffer.java
 * @author: linlu
 * @date: 2020/8/26
 * @copyright: 2020-2023 www.bosssoft.com.cn Inc. All rights reserved.
 */
package com.sd365.common.core.annotation.stuffer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * @class EntityFieldStuffer
 * @classdesc 自定义的注解，methodType 方法为方法的类型， stuffer：填充器
 * @author linlu
 * @date 2020/8/26
 * @version 1.0.0
 * @see
 * @since
 */

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EntityFieldStuffer {
    MethodTypeEnum methodType();
}
