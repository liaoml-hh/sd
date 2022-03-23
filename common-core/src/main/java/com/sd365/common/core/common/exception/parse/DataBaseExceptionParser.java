package com.sd365.common.core.common.exception.parse;
/**
 * @class DataBaseExceptionParser
 * @classdesc 将统一异常捕捉的数据库异常转化为负责标准异常码定义的数据库异常
 * @author Administrator
 * @date 2021-7-24  22:57
 * @version 1.0.0
 * @see
 * @since
 */
public class DataBaseExceptionParser implements ExceptionParser {
    @Override
    public Exception parse(Exception sourceEx) {
        Exception exception=new Exception();
        // do your logic code
        return exception;
    }
}
