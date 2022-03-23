/**
 * @file:  ServiceConsts.java
 * @author: liang_xiaojian
 * @date:   2020/9/23 16:23
 * @copyright: 2020-2023 www.bosssoft.com.cn Inc. All rights reserved.
 */
package com.sd365.common.core.common.constant;

/**
 * @class ServiceConsts
 * @classdesc 业务通用常量
 * @author liang_xiaojian
 * @date 2020/9/23  16:22
 * @version 1.0.0
 * @see
 * @since
 */
public class ServiceConsts {

    private ServiceConsts() {
        //
    }

    /**
     * 数据库操作失败影响行数
     */
    public static final int FAILED_AFFECTED_ROWS = 0;

    /**
     * 数据库操作单条记录成功影响行数
     */
    public static final int AFFECTED_ROWS = 1;
}
