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
public enum BizErrorCode implements IErrorTypeCode  {
    /**
     * 错误码、错误信息
     */
    VERIFICATION_FAIL("B001","校验不通过"),
     DEPEND_LACK("B002","依赖数据缺失"),
    ACID_ERROR("B003","依赖数据缺失"),
    BUSINESS_ROLE_ERROR("004","依赖数据缺失"),
    /**
     * 在以上增加其它错误码
     */
    UNDEFINED("000", "未定义");

    private String code;
    private String message;

    BizErrorCode(String code, String message) {
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
        for (BizErrorCode errorCode : BizErrorCode.values()) {
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
