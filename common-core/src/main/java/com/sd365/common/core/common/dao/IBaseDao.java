/**
 * @file:  IBaseDao.java
 * @author: liang_xiaojian
 * @date:   2020/9/4 19:11
 * @copyright: 2020-2023 www.bosssoft.com.cn Inc. All rights reserved.
 */
package com.sd365.common.core.common.dao;

import java.util.List;

/**
 * @class IBaseDao
 * @classdesc
 * @author liang_xiaojian
 * @date 2020/9/4  19:10
 * @version 1.0.0
 * @see
 * @since
 */
public interface IBaseDao<T, Q> {
    int save(T entity);

    int delete(T entity);

    int update(T entity);

    List<T> queryByCondition(Q query);
}
