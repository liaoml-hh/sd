/**
 * @file:  CommonBeans.java
 * @author: liang_xiaojian
 * @date:   2020/9/17 12:36
 * @copyright: 2020-2023 www.bosssoft.com.cn Inc. All rights reserved.
 */
package com.sd365.common.core.common.config;

import com.github.dreamroute.locker.interceptor.OptimisticLocker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @class CommonBeans
 * @classdesc MyBatis 乐观锁插件 locker 配置
 * @author liang_xiaojian
 * @date 2020/9/17  12:36
 * @version 1.0.0
 * @see
 * @since
 */
@Configuration
public class CommonBeans {
    //@Bean
    public OptimisticLocker locker() {
        OptimisticLocker locker = new OptimisticLocker();
        // 不设置versionColumn，默认为version
        Properties props = new Properties();
        props.setProperty("versionColumn", "version");
        locker.setProperties(props);
        return locker;
    }
}
