package com.sd365.common.core.common.exception;

/**
 * @file: sd365-common-common-g05 com.sd365.common.core.common.exception
 * @author: Sukaiting
 * @date: 2020/8/26 16:20
 * @copyright: 2020-2023 www.bosssoft.com.cn Inc. All rights reserved.
 */

import com.sd365.common.core.common.api.CommonResponse;
import com.sd365.common.core.common.exception.code.BusinessErrorCode;
import com.sd365.common.core.common.uitls.ExceptionUtil;
import com.sd365.common.core.context.BaseContextConstants;
import com.sd365.common.core.context.BaseContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.Version;
import java.sql.SQLException;
import java.util.Vector;

/**
 * @author Sukaiting
 * @version 1.0.2
 * @class GlobalControllerResolver
 * @classdesc 全局异常处理器
 * <p>维护历史记录 : 增加了 ResponseBodyAware 对 分页方法进行自动判断调用</p>
 * <p> abel.zhan 增加了 SQLException处理，如果是数据库异常则直接展示数据库异常码 </p>
 * <p>abel.zhan 增加了 SQLException处理,SystemException,ThirdPartApiException，如果是数据库异常则直接展示数据库异常码 </p>
 * @date 2020/8/26  16:20
 * @see
 * @since
 */
@Slf4j
@RestControllerAdvice
public class GlobalControllerResolver {

    private static final int BIND_EXCEPTION_CODE = 300001;
    private static final String BIND_EXCEPTION_MESSAGE = "参数错误";

    private GlobalControllerResolver() {
        // no-args constructor

    }
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(BusinessException.class)
    public CommonResponse<Object> businessException(BusinessException exception) {
        log.error("业务异常：{},错误码：{} 全局业务流水号：{}, 堆栈：",
                ExceptionUtil.getAllExceptionMsg(exception),exception.getCode(),
                BaseContextHolder.get(BaseContextConstants.GLOBAL_SERIAL_NUMBER),
                exception);
        return CommonResponse.builder()
                .withCode(exception.getCode())
                .withMessage(exception.getMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ServiceException.class)
    public CommonResponse<Object> serviceException(ServiceException exception) {
        log.error("中间服务层异常：{},错误码:{}, 全局业务流水号：{}, 堆栈：{}",
                ExceptionUtil.getAllExceptionMsg(exception),exception.getCode(),
                BaseContextHolder.get(BaseContextConstants.GLOBAL_SERIAL_NUMBER),
                exception);
        return CommonResponse.builder()
                .withCode(exception.getCode())
                .withMessage(exception.getMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ThirdPartyApiException.class)
    public CommonResponse<Object> thirdPartyApiException(ServiceException exception) {
        log.error("调用第3方api异常：{},错误码:{}, 全局业务流水号：{}, 堆栈：{}",
                ExceptionUtil.getAllExceptionMsg(exception),exception.getCode(),
                BaseContextHolder.get(BaseContextConstants.GLOBAL_SERIAL_NUMBER),
                exception);
        return CommonResponse.builder()
                .withCode(exception.getCode())
                .withMessage(exception.getMessage())
                .build();
    }

    /**
     *   对于一些系统错误业务逻辑层抛出系统错误异常
     * @param exception
     * @return
     */

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(SystemException.class)
    public CommonResponse<Object> systemException(ServiceException exception) {
        log.error("OS异常包括内存不足 IO 错误 类型转换错误 空指针等：{},错误码:{}, 全局业务流水号：{}, 堆栈：{}",
                ExceptionUtil.getAllExceptionMsg(exception),exception.getCode(),
                BaseContextHolder.get(BaseContextConstants.GLOBAL_SERIAL_NUMBER),
                exception);
        return CommonResponse.builder()
                .withCode(exception.getCode())
                .withMessage(exception.getMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(SQLException.class)
    public CommonResponse<Object> databaseException(SQLException exception) {
        log.error("数据库层异常：{},错误码:{}, 全局业务流水号：{}, 堆栈：{}",
                ExceptionUtil.getAllExceptionMsg(exception),exception.getErrorCode(),
                BaseContextHolder.get(BaseContextConstants.GLOBAL_SERIAL_NUMBER),
                exception);
        return CommonResponse.builder()
                .withCode(String.valueOf(exception.getErrorCode()))
                .withMessage(exception.getMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DaoException.class)
    public CommonResponse<Object> daoException(DaoException exception) {
        log.error("数据访问层异常：{},错误码:{}, 全局业务流水号：{}, 堆栈：{}",
                ExceptionUtil.getAllExceptionMsg(exception),"D"+exception.getCode(),
                BaseContextHolder.get(BaseContextConstants.GLOBAL_SERIAL_NUMBER),
                exception);
        return CommonResponse.builder()
                .withCode(exception.getCode())
                .withMessage(exception.getMessage())
                .build();
    }

    /**
     * JSR303
     *
     * @param exception
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResponse<Object> handleBindException(MethodArgumentNotValidException exception) {
        FieldError fieldError = exception.getBindingResult().getFieldError();
        log.error("参数校验异常：{}({})", fieldError.getDefaultMessage(), fieldError.getField());
        log.error("全局业务流水号：{}", BaseContextHolder.get(BaseContextConstants.GLOBAL_SERIAL_NUMBER));
        return CommonResponse.builder()
                .withCode("500010")
                .withMessage(exception.getMessage())
                .build();
    }

    /**
     * 参数绑定异常
     * @param exception
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public CommonResponse<Object> handleBindException(BindException exception) {
        //校验 除了 requestbody 注解方式的参数校验 对应的 bindingresult 为 BeanPropertyBindingResult
        FieldError fieldError = exception.getBindingResult().getFieldError();
        log.error("必填校验异常:{}({})", fieldError.getDefaultMessage(), fieldError.getField());
        log.error("全局业务流水号：{}", BaseContextHolder.get(BaseContextConstants.GLOBAL_SERIAL_NUMBER));
        return CommonResponse.builder()
                .withCode("500011")
                .withMessage(exception.getMessage())
                .build();
    }


    /**
     * 保证异常都会被捕获
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public CommonResponse<Object> illegalArgumentException(Exception exception) {
        log.error("[其他异常]: {}, 全局业务流水号：{}, 堆栈：",
                exception.getMessage(), BaseContextHolder.get(BaseContextConstants.GLOBAL_SERIAL_NUMBER),exception);
        return CommonResponse.builder()
                .withCode(String.valueOf(BusinessErrorCode.UNDEFINED.getCode()))
                .withMessage(exception.getMessage())
                .build();
    }



}
