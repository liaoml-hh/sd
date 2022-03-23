/**
 * @file:  CommonMapper.java
 * @author: liang_xiaojian
 * @date:   2020/8/26 15:43
 * @copyright: 2020-2023 www.bosssoft.com.cn Inc. All rights reserved.
 */
package com.sd365.common.core.common.dao;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

/**
 * @class CommonMapper
 * @classdesc 使业务层和具体的 mybatis 的 mapper 解耦，所有 mapper 都实现本接口，注意，不能扫描到本接口
 * @author liang_xiaojian
 * @date 2020/8/26  15:43
 * @version 1.0.0
 * @see
 * @since
 */
public interface CommonMapper<T> extends Mapper<T>, MySqlMapper<T> {
}