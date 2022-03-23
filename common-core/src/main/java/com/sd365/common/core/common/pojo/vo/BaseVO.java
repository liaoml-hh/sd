/**
 * @file: BaseVO.java
 * @author: liang_xiaojian
 * @date: 2020/8/26 15:33
 * @copyright: 2020-2023 www.bosssoft.com.cn Inc. All rights reserved.
 */
package com.sd365.common.core.common.pojo.vo;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @class BaseVO
 * @classdesc
 * @author liang_xiaojian
 * @date 2020/8/26  15:33
 * @version 1.0.0
 * @see
 * @since
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseVO implements Serializable {

    private static final long serialVersionUID = -2361201137582920579L;

    private Long id;

    private Long createdBy;

    private String creator;

    private Long updatedBy;

    //private String updater;
    private String modifier;

    private Date createdTime;

    private Date updatedTime;

    private Byte status;

    private Long version;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
