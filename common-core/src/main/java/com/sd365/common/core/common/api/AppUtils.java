/**
 * @file:  AppUtils.java
 * @author: liang_xiaojian
 * @date:   2020/8/26 14:45
 * @copyright: 2020-2023 www.bosssoft.com.cn Inc. All rights reserved.
 */
package com.sd365.common.core.common.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @class AppUtils
 * @classdesc
 * @author liang_xiaojian
 * @date 2020/8/26  14:45
 * @version 1.0.0
 * @see
 * @since
 */
@Component
//@ConfigurationProperties(prefix = "app")
public class AppUtils {

    private AppUtils() {
        // prevent construct
    }

    @Value("${app.version}")
    private String version;

    private static String myVersion;

    @PostConstruct
    public void init() {
        AppUtils.myVersion = version;
    }

    public static <T> void setResponseExtendInfo(CommonResponse<T> commonResponse) {
        commonResponse.getHead().setVersion(myVersion);
    }
}
