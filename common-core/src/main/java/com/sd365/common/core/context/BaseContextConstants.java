/**
 * @file:  BaseContextConstants.java
 * @author: liang_xiaojian
 * @date:   2020/9/13 19:23
 * @copyright: 2020-2023 www.bosssoft.com.cn Inc. All rights reserved.
 */
package com.sd365.common.core.context;

/**
 * @class BaseContextConstants
 * @classdesc 上下文相关的参数
 * @author liang_xiaojian
 * @date 2020/9/13  19:23
 * @version 1.0.0
 * @see
 * @since
 */
public final class BaseContextConstants {

    public static final String USER_CONTEXT_ATTRIBUTES = "user";
    public static final Long NOT_EXIST = 0L;

    public static final String LOGIN_USER_ID = "userId";
    public static final String LOGIN_USER_NAME = "userName";

    public static final String JWT_KEY_CLIENT_ID = "client_id";
    public static final String JWT_KEY_TOKEN_TYPE = "token_type";
    public static final String JWT_KEY_REFRESH_TOKEN = "refresh_token";
    public static final String JWT_KEY_USER_ID = "user_id";
    public static final String JWT_KEY_USER_NAME = "user_name";
    // 新增租户id
    public static final String JWT_KEY_TANENT_ID = "tenantId";
    public static final String JWT_KEY_ORG_ID = "orgId";
    public static final String JWT_KEY_COMPANY_ID = "companyId";

    public static final String JWT_KEY_AUTHORITIES = "authorities";


    public static final String BEARER_HEADER_KEY = "token";
    public static final String BEARER_HEADER_PREFIX = "Bearer ";
    public static final String BEARER_HEADER_PREFIX_EXT = "Bearer%20";
    public static final String BASIC_HEADER_KEY = "Authorization";
    public static final String BASIC_HEADER_PREFIX = "Basic ";
    public static final String BASIC_HEADER_PREFIX_EXT = "Basic%20";
    public static final String TRACE_ID_HEADER = "x-trace-header";
    public static final String LOG_TRACE_ID = "trace";
    public static final String GRAY_VERSION = "grayversion";
    public static final String GLOBAL_SERIAL_NUMBER = "gnumber";

    /**
     * 某次查询的记录总条数
     */
    public static final String PAGE_TOTAL = "total";

    public final static String ACCESS_TOKEN = "accessToken";
    public static final String TOKEN_TENANT_ID = "tenantId";
    public static final String TOKEN_COMPANY_ID = "companyId";
    public static final String TOKEN_ORG_ID = "orgId";
    public static final String TOKEN_HAED_ALG = "alg";
    public static final String TOKEN_HAED_TYP ="typ";
    public static final String TOKEN_PAYLOAD_ACCOUNT = "account";
    public static final String TOKEN_PAYLOAD_ROLEIDS = "roleIds";
    private BaseContextConstants() {
    }
}
