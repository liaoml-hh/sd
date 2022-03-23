/**
 * @file:  ExceptionUtil.java
 * @author: liang_xiaojian
 * @date:   2020/9/9 17:13
 * @copyright: 2020-2023 www.bosssoft.com.cn Inc. All rights reserved.
 */
package com.sd365.common.core.common.uitls;

import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Objects;

/**
 * @class ExceptionUtil
 * @classdesc Common methods for exception.
 * @author liang_xiaojian
 * @date 2020/9/9  17:13
 * @version 0.0.1
 * @see
 * @since
 */
public class ExceptionUtil {

    private ExceptionUtil() {
        //
    }

    public static String getAllExceptionMsg(Throwable e) {
        Throwable cause = e;
        StringBuilder strBuilder = new StringBuilder();
        while (cause != null && !StringUtils.isEmpty(cause.getMessage())) {
            strBuilder.append("caused: ").append(cause.getMessage()).append(";");
            cause = cause.getCause();
        }

        return strBuilder.toString();
    }

    public static Throwable getCause(final Throwable t) {
        final Throwable cause = t.getCause();
        if (Objects.isNull(cause)) {
            return t;
        }
        return cause;
    }

    public static String getStackTrace(final Throwable t) {
        if (t == null) {
            return "";
        }

        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final PrintStream ps = new PrintStream(out);
        t.printStackTrace(ps);
        ps.flush();
        return new String(out.toByteArray());
    }
}
