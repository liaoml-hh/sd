package com.sd365.common.log.api.config;
/**
 * @file:  LogRecordClientConfig.java
 * @author: chh
 * @date:   2020/8/26 18:06
 * @copyright: 2020-2023 www.bosssoft.com.cn Inc. All rights reserved.
 */
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @class LogRecordClientConfig
 * @classdesc 打包后能自动搜索容器
 * @author chh
 * @date 2020/8/26  18:06
 * @version 1.0.0
 * @see
 * @since
 */
@Configuration
@ComponentScan("com.sd365.common.log.api.**")
public class LogRecordClientConfig {

}
