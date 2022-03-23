/**
 * @file:  BaseWebMvcConfigurerAdapter.java
 * @author: liang_xiaojian
 * @date:   2020/9/13 10:09
 * @copyright: 2020-2023 www.bosssoft.com.cn Inc. All rights reserved.
 */
package com.sd365.common.core.context;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @class BaseWebMvcConfigurerAdapter
 * @classdesc 在这里配置拦截器
 * @author liang_xiaojian
 * @date 2020/9/13  10:09
 * @version 1.0.0
 * @see
 * @since
 */
@Component
public class BaseWebMvcConfigurerAdapter implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserContextHandlerInterceptor());
    }
}
