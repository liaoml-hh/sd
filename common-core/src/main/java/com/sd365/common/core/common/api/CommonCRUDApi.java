package com.sd365.common.core.common.api;

import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

/**
 * @class CommonCRUDApi
 * @classdesc 增删除改查的 xxServiceApi
 * @author liang_xiaojian
 * @date 2020/8/28  21:42
 * @version 1.0.0
 * @see
 * @since
 */
public interface CommonCRUDApi<D, Q, V> {

    /**
     * @param:
     * @return:
     * @see
     * @since
     */
    CommonResponse<Integer> create(@RequestBody @Valid CommonRequest<D> request);

    /**
     * @param:
     * @return:
     * @see
     * @since
     */
    CommonResponse<List<V>> query(@RequestBody @Valid CommonRequest<Q> request);

    /**
     * @param:
     * @return:
     * @see
     * @since
     */
    CommonResponse<Integer> update(@RequestBody @Valid CommonRequest<D> request);

    /**
     * @param:
     * @return:
     * @see
     * @since
     */
    CommonResponse<Integer> delete(@RequestBody @Valid CommonRequest<D> request);
}
