/**
 * @file:  CommonResponseUtils.java
 * @author: liang_xiaojian
 * @date: 2020/8/27  20:56
 * @copyright: 2020-2023 www.bosssoft.com.cn Inc. All rights reserved.
 */
package com.sd365.common.core.common.api;

import com.sd365.common.core.common.exception.code.IErrorCode;

/**
 * @author liang_xiaojian
 * @version 1.0.0
 * @class CommonResponseUtils
 * @classdesc Common response utils.
 * @date 2020/8/27  21:16
 * @see
 * @since
 */
public class CommonResponseUtils {

    private CommonResponseUtils() {
    }

    public static final String CODE_SUCCESS = "0";
    public static final String CODE_FAILED = "500";

    /**
     *  构建无body的成功应答
     * @return:
     * @see
     * @since
     */
    public static <T> CommonResponse<T> success() {
        return CommonResponse.<T>builder()
                .withCode(CODE_SUCCESS)
                .build();
    }

    /**
     *  构建成功应答
     * @param body  返回的数据可以是任意类型包括空
     * @param <T>
     * @return
     */
    public static <T> CommonResponse<T> success(T body) {
        return CommonResponse.<T>builder()
                .withCode(CODE_SUCCESS)
                .withBody(body)
                .build();
    }


    /**
     *  构建应答指定错误消息
     * @param message
     * @param <T>
     * @return 统一应答体
     */
    public static <T> CommonResponse<T> failed(String message) {
        return CommonResponse.<T>builder()
                .withCode(CODE_FAILED)
                .withMessage(message)
                .build();
    }


    /**
     *  构建失败应答指定 错误码 body 和 错误消息
     * @param code 错误码
     * @param body  错误应答body 任意类型
     * @param message 错误消息
     * @param <T>
     * @return 统一应答体
     */
    public static <T> CommonResponse<T> failed(String code, T body, String message) {
        return CommonResponse.<T>builder()
                .withCode(code)
                .withBody(body)
                .withMessage(message)
                .build();
    }

    /**
     *  构建失败应答
     * @param code 错误码
     * @param message  消息
     * @param <T>
     * @return 统一应答体
     */
    public static <T> CommonResponse<T> failedWithMsg(String code, String message) {
        return CommonResponse.<T>builder()
                .withCode(code)
                .withMessage(message)
                .build();
    }

    /**
     * 构建基于错误码的失败应答
     * @param errorCode  包含了错误和消息
     * @param <T>
     * @return 统一应答体
     */
    public static <T> CommonResponse<T> failed(IErrorCode errorCode) {
        return CommonResponse.<T>builder()
                .withCode(String.valueOf(errorCode.getCode()))
                .withBody(null)
                .withMessage(errorCode.getMessage())
                .build();
    }

}
