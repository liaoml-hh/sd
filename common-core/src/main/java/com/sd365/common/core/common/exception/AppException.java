package com.sd365.common.core.common.exception;

/**
 * @file: sd365-common-common-g05 com.sd365.common.core.common.exception
 * @author: Sukaiting
 * @date: 2020/8/26 16:01
 * @copyright: 2020-2023 www.bosssoft.com.cn Inc. All rights reserved.
 */

import com.sd365.common.core.common.exception.code.IErrorCode;
import com.sd365.common.core.common.exception.code.IModuleCode;

/**
 * @class AppException
 * @classdesc 基于异常码的方式统一了系统异常，对于不稳定的业务方法要求必须捕获异常抛出
 *            异常 全局要求对此类异常做日志记录和构建统一的应答给前端
 * @author Sukaiting
 * @date 2020/8/26  16:02
 * @version 1.0.0
 * @see
 * @since
 */
public class AppException extends RuntimeException{

    private static final long serialVersionUID = -3607818880814070092L;
    /**
     * 异常码
     */
    private String code;

    public AppException() {

    }

    public AppException(String code, String message) {
        super(message);
        this.code = code;
    }

    public AppException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }
    public AppException(IErrorCode errorCode, Throwable cause) {
        this(String.valueOf(errorCode.getCode()), errorCode.getMessage(), cause);
    }
    public AppException(IModuleCode moduleCode,IErrorCode errorCode, Throwable cause) {
        this(String.valueOf(moduleCode.getCode())+"-"+String.valueOf(errorCode.getCode()),moduleCode.getMessage()+errorCode.getMessage(), cause);
    }

    public String getCode() {
        return code;
    }

}
