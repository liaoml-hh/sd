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
public enum FrameworkModuleCode implements IModuleCode  {

    /**
     * 错误码、错误信息
     */
    CORE_MODULE("000001","核心模块"),
    CONFIG_MODULE("000002","配置模块"),
    LOG_MODULE("000003","日志模块"),
    CACHE_MODULE("000004","缓存模块"),
    TOOL_MODULE("000005","工具模块"),

    /**
     * 在以上增加其它错误码
     */
    UNDEFINED("000000", "未定义模块");

    private String code;
    private String message;

    FrameworkModuleCode(String code, String message) {
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
        for (FrameworkModuleCode errorCode : FrameworkModuleCode.values()) {
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
