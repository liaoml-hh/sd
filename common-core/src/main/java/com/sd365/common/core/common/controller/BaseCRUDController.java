/**
 * @file:  BaseCRUDController.java
 * @author: liang_xiaojian
 * @date:   2020/8/28 21:36
 * @copyright: 2020-2023 www.bosssoft.com.cn Inc. All rights reserved.
 */
package com.sd365.common.core.common.controller;

import com.sd365.common.core.common.api.CommonPage;
import com.sd365.common.core.common.pojo.vo.BaseQuery;
import com.sd365.common.core.common.service.BaseCURDService;
import com.sd365.common.core.common.api.CommonRequest;
import com.sd365.common.core.common.api.CommonResponse;
import com.sd365.common.core.common.api.CommonResponseUtils;
import com.sd365.common.core.context.BaseContextHolder;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

/**
 * @class BaseCRUDController
 * @classdesc
 * @author liang_xiaojian
 * @date 2020/8/28  21:36
 * @version 1.0.0
 * @see
 * @since
 */
public abstract class BaseCRUDController<D, T, Q extends BaseQuery, M, V> extends AbstractController {

    protected BaseCURDService<D, T, Q, M> service;

    public CommonResponse<Integer> create(@RequestBody @Valid CommonRequest<D> request) {
        Integer result = this.service.save(request);
        return CommonResponseUtils.success(result);
    }

    public CommonResponse<CommonPage<V>> query(@RequestBody @Valid CommonRequest<Q> request) {
        Q requestBody = request.getBody();
        this.doBeforePagination(requestBody.getPageNum(), requestBody.getPageSize());
        List<?> returnDto = service.query(request);
        BaseContextHolder.endPage(returnDto);
        List<V> voList = doObjectTransf(returnDto);
        return CommonResponseUtils.success(CommonPage.restPage(voList));
    }

    protected abstract List<V> doObjectTransf(List<?> returnDto);

    public CommonResponse<Integer> update(@RequestBody @Valid CommonRequest<D> request) {
        Integer result = this.service.update(request);
        return CommonResponseUtils.success(result);
    }

    public CommonResponse<Integer> delete(@RequestBody @Valid CommonRequest<D> request) {
        Integer result = this.service.remove(request);
        return CommonResponseUtils.success(result);
    }
}
