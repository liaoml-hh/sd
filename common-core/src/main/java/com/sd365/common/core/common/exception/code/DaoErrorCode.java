package com.sd365.common.core.common.exception.code;

/**
 * @file: sd365-common-common-g05 com.sd365.common.core.common.exception.code
 * @author: Sukaiting
 * @date: 2020/8/26 20:45
 * @copyright: 2020-2023 www.bosssoft.com.cn Inc. All rights reserved.
 */
/**
 * @class DaoErrorCode
 * @classdesc
 * @author Sukaiting
 * @date 2020/8/26  21:05
 * @version 1.0.0
 * @see
 * @since
 */
public enum DaoErrorCode implements IErrorCode  {

    /**
     * 错误码、错误信息
     */

    FAIL_TO_CREATE(200009,"增加失败"),

    FAIL_TO_DELETE(200010, "删除失败"),

    FAIL_TO_UPDATE(200011,"修改失败"),

    FAIL_TO_QUERY(200012,"查询失败"),

    /**
     * 在以上增加其它错误码
     */
    UNDEFINED(900000, "未定义");

    private int code;
    private String message;

    DaoErrorCode (int code, String message) {
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
        for (DaoErrorCode errorCode : DaoErrorCode.values()) {
            if (errorCode.getCode() == code) {
                return errorCode.message;
            }
        }
        return UNDEFINED.message;
    }


    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setCode(String code) {

    }

    @Override
    public void setMessage(String message) {

    }

}
