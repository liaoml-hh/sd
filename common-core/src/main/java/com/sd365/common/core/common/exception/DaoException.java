package com.sd365.common.core.common.exception;

/**
 * @file: sd365-common-common-g05 com.sd365.common.core.common.exception
 * @author: Sukaiting
 * @date: 2020/8/26 16:03
 * @copyright: 2020-2023 www.bosssoft.com.cn Inc. All rights reserved.
 */

import com.sd365.common.core.common.exception.code.IErrorCode;
import com.sd365.common.core.common.exception.code.IModuleCode;

/**
 * @class BusinessException
 * @classdesc 基本业务操作异常，所有业务操作异常都继承于该类
 * @author Sukaiting
 * @date 2020/8/26  16:03
 * @version 1.0.0
 * @see
 * @since
 */
public class DaoException extends AppException {
    private static final long serialVersionUID = 6304058622501786159L;

    public DaoException() {
    }

    public DaoException(String code, String message) {
        super(code, message);
    }

    public DaoException(String code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public DaoException(IErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public DaoException(IModuleCode moduleCode, IErrorCode errorCode, Throwable cause) {
        super(moduleCode, errorCode, cause);
    }
}
