/**
 * @file:  BaseDTO.java
 * @author: liang_xiaojian
 * @date:   2020/8/26 15:30
 * @copyright: 2020-2023 www.bosssoft.com.cn Inc. All rights reserved.
 */
package com.sd365.common.core.common.pojo.dto;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @class BaseDTO
 * @classdesc DTO 从这里继承便于统一DTO接口和转型判断
 * @author liang_xiaojian
 * @date 2020/8/26  15:30
 * @version 1.0.0
 * @see
 * @since
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseDTO implements Serializable {


    private static final long serialVersionUID = -4183652945764463929L;

    private Long id;

    private Byte status=1;

    private Long createdBy;
    private String creator;
    @ApiModelProperty(example = "2018-10-01 12:18:48")
    private Date createdTime;

    private Long updatedBy;
    private String modifier;
    @ApiModelProperty(example = "2018-10-01 12:18:48")
    private Date updatedTime;

    private Long version;

    @Override
    public String toString() {
       return JSON.toJSONString(this);
    }
}
