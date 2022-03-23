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
public enum UserCentreModuleCode implements IModuleCode  {

    /**
     * 错误码、错误信息
     */

    /**
     * 错误码、错误信息
     */
    TENANT_MODULE("002001","核心模块"),
    ORG_MODULE("002002","配置模块"),
    COMPANY_MODULE("002003","日志模块"),
    DEPARTMENT_MODULE("002002","配置模块"),
    SUBSYSTEM_MODULE("002003","日志模块"),
    RESOURCE_MODULE("002002","配置模块"),
    ROLE_MODULE("002003","日志模块"),
    USER_MODULE("002002","配置模块"),
    /**
     * 在以上增加其它错误码
     */
    UNDEFINED("002000", "未定义模块");

    private String code;
    private String message;

    UserCentreModuleCode(String code, String message) {
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
        for (UserCentreModuleCode errorCode : UserCentreModuleCode.values()) {
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

}
