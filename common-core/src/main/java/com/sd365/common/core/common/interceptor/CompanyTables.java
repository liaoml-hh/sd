package com.sd365.common.core.common.interceptor;
/**
 * @file:  CompanyTables.java
 * @author: chh
 * @date:   2020/9/16 17:21
 * @copyright: 2020-2023 www.bosssoft.com.cn Inc. All rights reserved.
 */
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
@ConfigurationProperties("interceptor")
@Data
/**
 * @class CompanyTables
 * @classdesc 注入配置文件
 * @author chh
 * @date 2020/9/16  17:20
 * @version 1.0.0
 * @see
 * @since
 */
public class CompanyTables {
    private List<String> company;
    private List<String> org;
}
