package com.sd365.common.core.common.exception.code;

/**
 * @file: sd365-common-common-g05 com.sd365.common.core.common.exception.code
 * @author: abel.zhan
 * @date: 2021-7-24  21:58
 * @copyright: 2020-2033 www.ndsd365.com Inc. All rights reserved.
 */
/**
 * @class SystemErrorCode
 * @classdesc
 * @author Administrator
 * @date 2021-7-24  21:58
 * @version 1.0.0
 * @see
 * @since
 */
public enum SystemErrorCode implements IErrorTypeCode  {
    /**
     * 错误码、错误信息
     */
    NULL_POINT_ERROR("S001","空指针"),
    OUT_BOUND_ERROR("S002","越界"),
    NUM_PARSE_ERROR("S003","数字转化异常"),
    IO_ERROR("S004","IO错误"),
    CLASS_NOT_FOUND_ERROR("S005","类没有找到"),
    MEMORY_NOT_ENOUGH_ERROR("S006","内存不足"),
    /**
     * 在以上增加其它错误码
     */
    UNDEFINED("000", "未定义");
    private String code;
    private String message;
    SystemErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * @param: code
     * @return: 错误信息
     * @see
     * @since
     */
    public static String msg(int code) {
        for (SystemErrorCode errorCode : SystemErrorCode.values()) {
            if (errorCode.getCode().equals(code)) {
                return errorCode.message;
            }
        }
        return UNDEFINED.message;
    }


    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setCode(String code) {
        this.code=code;
    }

    @Override
    public void setMessage(String message) {

    }

}
