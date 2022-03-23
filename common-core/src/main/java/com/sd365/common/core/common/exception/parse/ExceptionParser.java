package com.sd365.common.core.common.exception.parse;

/**
 * @class ExceptionParser
 * @classdesc
 * @author Administrator
 * @date 2021-7-24  22:53
 * @version 1.0.0
 * @see
 * @since
 */
public interface ExceptionParser {
    /**
     * 将全局异常处理器捕获的异常转化为期望具体异常
     * 例如 在业务层多数据库增删除操作可能自动抛出异常，异常进入全部异常处理
     * 器异常处理器需要根据异常内部的信息构建出带有异常码和消息的异常返回
     * @param sourceEx 错误抛出的异常
     * @return 包含 code和message 以及ex异常堆栈的自定义异常
     */
    Exception parse(Exception sourceEx);
}
