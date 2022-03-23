/**
 * @file:  AbstractBaseDao.java
 * @author: liang_xiaojian
 * @date:   2020/8/26 15:42
 * @copyright: 2020-2023 www.bosssoft.com.cn Inc. All rights reserved.
 */
package com.sd365.common.core.common.dao.impl;

import com.sd365.common.core.common.dao.IBaseDao;
import com.sd365.common.core.common.dao.CommonMapper;

import java.util.List;

/**
 * @class AbstractBaseDao
 * @classdesc
 * @author liang_xiaojian
 * @date 2020/8/26  15:42
 * @version 1.0.0
 * @see
 * @since
 */
public abstract class AbstractBaseDao<T, M extends CommonMapper<T>, Q> implements IBaseDao<T, Q> {

    /**
     * 注入框架隔离的 Mapper, 后面的数据操作依赖此 mapper
     */
    protected M myMapper;

    @Override
    public int save(T entity) {
        return myMapper.insert(entity);
    }

    @Override
    public int delete(T entity) {
        return myMapper.delete(entity);
    }

    @Override
    public int update(T entity) {
        return myMapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public List<T> queryByCondition(Q query) {
        return null;
    }
}
