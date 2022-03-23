/**
 * @file:  BaseQuery.java
 * @author: liang_xiaojian
 * @date:   2020/8/11 16:00
 * @copyright: 2020-2023 www.bosssoft.com.cn Inc. All rights reserved.
 */
package com.sd365.common.core.common.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @class BaseQuery
 * @classdesc
 * @author liang_xiaojian
 * @date 2020/8/11  16:00
 * @version 1.0.0
 * @see
 * @since
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseQuery extends AbstractQuery{
    /**
     *  当前页索引
     */
    private Integer pageNum = 1;
    /**
     * 每页的数据
     */
    private Integer pageSize = 10;
}
