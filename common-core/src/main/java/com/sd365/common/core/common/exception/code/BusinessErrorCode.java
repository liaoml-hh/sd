/**
 * @file: sd365-common-common-g05 com.sd365.common.core.common.exception.code
 * @author: Sukaiting
 * @date: 2020/8/26 16:00
 * @copyright: 2020-2023 www.bosssoft.com.cn Inc. All rights reserved.
 */
package com.sd365.common.core.common.exception.code;

/**
 * @class BusinessErrorCode
 * @classdesc
 *      * 业务错误为 2xxxxx
 *      * 错误码分配如下：
 *      * 21-XX-YY 基础数据服务 OMP  21-XX 为 子模块编码-YY 为该模块错误吗
 *      *  例子  210101 OMP 子系统 基础数据模块 站点操作错误，关于站点无论是增删除查还是其他
 *      *  错误统一使用 210101 ，具体的错误由消息提示，例如 客户端将看到 "210101 ,删除站点时数据库异常" 或者
 *      *  "保存站点时数据库异常" 此类消息
 *      * 22 OMS 子系统  错误码定义OMP 说明
 *      * 23 PE 子系统  同上
 *      * 24 TMS-CP子系统 同上
 *      * 25 exchange-system  同上
 *      *
 *      * 以上业务相关模块的错误码将在每个子系统中定义
 *      <p>abel.zhan 2021-08-14 重新规划异常为 modulecode+errorcode</p>
 * @author Administrator
 * @date 2020-10-8  11:15
 * @version 1.0.0
 * @see
 * @since
 */
@Deprecated
public enum BusinessErrorCode implements IErrorCode{

    /**
     * 错误码、错误信息
     */
    YSTEM_ERROR(-1, "系统异常"),
    /**
     * 网关错误为 11xxxx
     */
    GATEWAY_SYSTEM_ERROR_GATEWAY(100101,"系统错误: 网关服务异常"),
    GATEWAY_NOT_FOUND_SERVICE(110102, "服务未找到"),
    GATEWAY_ERROR(110103, "网关异常"),
    GATEWAY_CONNECT_TIME_OUT(110104, "网关超时"),
    GATEWAY_SYSTEM_ERROR_AUTHENTICATION(110105, "系统错误: 认证异常"),
    GATEWAY_SYSTEM_ERROR_AUTHORIZATION (110106, "系统错误: 认证异常"),
    GATEWAY_INVALID_TOKEN(110107, "无效token"),
    GATEWAY_SYSTEM_ERROR_STUFFER(110108,"系统错误: 用户请求没有用户信息"),

    /**
     * 限流限流错误码 为 1102xx
     */
    /**
     * 网关熔断错误码为 1103xx
     */
    /**
     * 网关灰色发布错误码为1104xx
     */
    /**
     * 网关黑白名单模块错误码为1105xx
     */

    /**
     * 其他系统错误码12xxxx
     * 调用外部 api 失败
     * 内部服务调用失败
     */
    /**
     * 外部服务调用失败
     */
    SYSTEM_ERROR_CDN(120001,"外部API错误: OOS服务异常"),
    SYSTEM_ERROR_MESSAGE(120002,"外部API错误: 短信服务异常"),
    SYSTEM_ERROR_MAIL(120002,"外部API错误: 邮件服务异常"),
    SYSTEM_ERROR_PAY(120002,"外部API错误: 支付服务异常"),
    /**
     * 内部服务通用失败
     */
    SYSTEM_BUSINESS_ERROR_RPC(130101, "服务降级"),
    SYSTEM_ERROR_LOG(130102,"系统错误: 日志服务异常"),
    SYSTEM_BEAN_COPY_EXCEPTION(130103,"领域对象拷贝属性错误"),
    BUSINESS_ERROR_RPC_USER_PERMISSION(130104,"认证授权远程调用错误"),
    /**
     * controller 层 错误
     */
    SYSTEM_CONTROLLER_ARGUMENT_NOT_VALID(130301, "请求参数校验不通过"),
    SYSTEM_CONTROLLER_UPLOAD_FILE_SIZE_LIMIT(130302, "上传文件大小超过限制"),
    /**
     * 服务层错误
     */
    SYSTEM_SERVICE_ARGUMENT_NOT_VALID(130501, "请求参数校验不通过"),
    SYSTEM_SERVICE_OTHER_EXCEPTION(130799, "请求参数校验不通过"),

    /**
     * 数据库层错误
     */
    SYSTEM_DB_DUPLICATE_PRIMARY_KEY(130801,"唯一键冲突"),
    SYSTEM_DB_CONSTRAINT_EXCEPTION(130802,"数据库约束错误"),
    SYSTEM_DB_NET_EXCEPTION(130803,"数据库网络异常"),
    SYSTEM_DB_OTHER_EXCEPTION(130999,"数据库其他异常"),


    /**
     *  基础框架错误码
     *  core jar 错误码
     *  common 包错误811000-812000
     *  annotation 包错误 812000-813000
     *  context 包错误 8130000-8140000
     *
     *  common-config jar 错误吗
     *  821000-
     *  common-log jar 错误码
     *  831000-840000
     *  common-util jar 错误吗
     *  840000-850000
     */


    COMMON_LLB_COMMON_PAGE_RROR(811000,"返回分页参数错误"),

    /**
     * 在以上增加其它错误码
     */
     LOG_AOP_RROR(990000,"日志切面错误"),
    UNDEFINED(900000, "未定义");

    private int code;
    private String message;

    BusinessErrorCode(int code, String message) {
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
        for (BusinessErrorCode errorCode : BusinessErrorCode.values()) {
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
