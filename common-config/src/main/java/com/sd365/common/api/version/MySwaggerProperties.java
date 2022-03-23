/**
 * @file:  SwaggerProperties.java
 * @author: liang_xiaojian
 * @date:   2020/9/4 13:57
 * @copyright: 2020-2023 www.bosssoft.com.cn Inc. All rights reserved.
 */
package com.sd365.common.api.version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @class SwaggerProperties
 * @classdesc
 * @author liang_xiaojian
 * @date 2020/9/4  13:57
 * @version 1.0.0
 * @see
 * @since
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Component
@ConfigurationProperties(prefix = "swagger")
public class MySwaggerProperties {
    /**
     * API 文档生成基础路径
     */
    private String apiBasePackage;
    /**
     * 文档标题
     */
    private String title;
    /**
     * 文档描述
     */
    private String description;
    /**
     * 文档版本
     */
    private String version;
    /**
     * 文档联系人姓名
     */
    private String contactName;
    /**
     * 文档联系人网址
     */
    private String contactUrl;
    /**
     * 文档联系人邮箱
     */
    private String contactEmail;

}
