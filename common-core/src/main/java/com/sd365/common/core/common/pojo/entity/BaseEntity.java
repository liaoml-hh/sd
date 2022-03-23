/**
 * @file:  BaseEntity.java
 * @author: liang_xiaojian
 * @date:   2020/8/26 15:32
 * @copyright: 2020-2023 www.bosssoft.com.cn Inc. All rights reserved.
 */
package com.sd365.common.core.common.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.Version;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @class BaseEntity
 * @classdesc 系统表包含的公用字段进行提取，统一切面也将使用该类型
 * @author liang_xiaojian
 * @date 2020/8/26  15:32
 * @version 1.0.0
 * @see
 * @since
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 2611297623998329330L;
    @Id
    private Long id;

    private Byte status=1;

    private Long createdBy;

    private Date createdTime;

    private Long updatedBy;

    private Date updatedTime;

    @Version
    private Long version;
}
