package com.sd365.common.core.annotation.stuffer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author jxd
 * @Date 2020/12/6  8:27 下午
 * @Version 1.0
 * @Write For EntityFieldStuffer2
 * @Email waniiu@126.com
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CommonFieldStuffer {
    MethodTypeEnum methodType();
}
