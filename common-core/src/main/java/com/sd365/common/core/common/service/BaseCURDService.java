/**
 * @file: BaseCURDService.java
 * @author: liang_xiaojian
 * @date: 2020/8/26 15:38
 * @copyright: 2020-2023 www.bosssoft.com.cn Inc. All rights reserved.
 */
package com.sd365.common.core.common.service;

import com.sd365.common.core.common.dao.impl.AbstractBaseDao;
import com.sd365.common.core.common.exception.ServiceException;
import com.sd365.common.core.common.exception.code.ServiceErrorCode;
import com.sd365.common.core.common.api.CommonRequest;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @class BaseCURDService
 * @classdesc 如果子类需要继承父 service 基本的增删改差方法，则可以继承该类，
 * <br/>子类需要重写查询 query 方法和 dto 到 entity 转化的方法 doObjectTransf
 * @author liang_xiaojian
 * @date 2020/8/26  15:38
 * @version 1.0.0
 * @see
 * @since
 */
public abstract class BaseCURDService<D, T, Q, M> {

    protected AbstractBaseDao<T, ? extends Mapper<T>, Q> myDao;

    public Integer save(CommonRequest<D> request) {
        try {
            T entity = doObjectTransf(request.getBody());
            return myDao.save(entity);
        } catch (Exception ex) {
            throw new ServiceException(ServiceErrorCode.BASE_CRUD_SERVICE_ERROR_CODE_CREATE, ex);
        }
    }

    public Integer remove(CommonRequest<D> request) {
        try {
            T entity = doObjectTransf(request.getBody());
            return myDao.delete(entity);
        } catch (Exception ex) {
            throw new ServiceException(ServiceErrorCode.BASE_CRUD_SERVICE_ERROR_CODE_DELETE, ex);
        }
    }

    public Integer update(CommonRequest<D> request) {
        try {
            T entity = doObjectTransf(request.getBody());
            return myDao.update(entity);
        } catch (Exception ex) {
            throw new ServiceException(ServiceErrorCode.BASE_CRUD_SERVICE_ERROR_CODE_UPDATE, ex);
        }
    }

    /**
     * 子类决定如何查询以及返回查询对象
     *
     * @param query
     * @return
     */
    public abstract List<?> query(CommonRequest<Q> query);

    /**
     * 子类决定如何做增删改过程中 dto到entity对象转化
     *
     * @param dto
     * @return
     */
    protected abstract T doObjectTransf(D dto);
}
