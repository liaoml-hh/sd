package com.sd365.common.core.common.exception.code;

/**
 * @file: sd365-common-common-g05 com.sd365.common.core.common.exception.code
 * @author: Sukaiting
 * @date: 2020/8/26 20:49
 * @copyright: 2020-2023 www.bosssoft.com.cn Inc. All rights reserved.
 */
public enum ServiceErrorCode implements IErrorCode {
    /**
     * 错误码、错误信息
     */
    FAIL_TO_REGISTER_USER(100009,"注册用户失败"),

    FAIL_TO_LOGIN(10010, "登录失败"),

    BASE_CRUD_SERVICE_ERROR_CODE_CREATE(100000,"保存失败"),

    BASE_CRUD_SERVICE_ERROR_CODE_RETRIEVE(100001,"查询失败"),

    BASE_CRUD_SERVICE_ERROR_CODE_UPDATE(100002,"更新失败"),

    BASE_CRUD_SERVICE_ERROR_CODE_DELETE(100003,"删除失败"),

    /**
     * 在以上增加其它错误码
     */
    UNDEFINED(900000, "未定义");

    private int code;
    private String message;

    ServiceErrorCode(int code, String message) {
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
        for (ServiceErrorCode errorCode : ServiceErrorCode.values()) {
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
       // this.code=code;
    }

    @Override
    public void setMessage(String message) {

    }
}
