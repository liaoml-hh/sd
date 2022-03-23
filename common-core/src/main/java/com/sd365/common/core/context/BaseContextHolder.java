/**
 * @file: BaseContextHolder.java
 * @author: liang_xiaojian
 * @date: 2020/9/13 9:30
 * @copyright: 2020-2023 www.bosssoft.com.cn Inc. All rights reserved.
 */
package com.sd365.common.core.context;

import cn.hutool.core.convert.Convert;
import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @class BaseContextHolder
 * @classdesc 存储用户请求上下文信息，为了方便存储更多内容所以ThreadLocaln内部建立Map存储
 * @author liang_xiaojian
 * @date 2020/9/13  9:30
 * @version 1.0.0
 * @see
 * @since
 */
public class BaseContextHolder {
    private static final ThreadLocal<Map<String, String>> THREAD_LOCAL = new ThreadLocal<>();

    private BaseContextHolder() {
    }

    public static void set(String key, Object value) {
        Map<String, String> map = getLocalMap();
        map.put(key, value == null ? "" : value.toString());
    }

    public static <T> T get(String key, Class<T> type) {
        Map<String, String> map = getLocalMap();
        return Convert.convert(type, map.get(key));
    }

    public static <T> T get(String key, Class<T> type, Object def) {
        Map<String, String> map = getLocalMap();
        return Convert.convert(type, map.getOrDefault(key, String.valueOf(def == null ? "" : def)));
    }

    public static String get(String key) {
        Map<String, String> map = getLocalMap();
        return map.getOrDefault(key, "");
    }

    public static Map<String, String> getLocalMap() {
        Map<String, String> map = THREAD_LOCAL.get();
        if (map == null) {
            map = new HashMap<>(10);
            THREAD_LOCAL.set(map);
        }

        return map;
    }

    public static void setLocalMap(Map<String, String> threadLocalMap) {
        THREAD_LOCAL.set(threadLocalMap);
    }

    // user
    public static Long getUserId() {
        return get(BaseContextConstants.JWT_KEY_USER_ID, Long.class, BaseContextConstants.NOT_EXIST);
    }

    public static String getUserIdStr() {
        return String.valueOf(getUserId());
    }

    public static void setUserId(Long userId) {
        set(BaseContextConstants.JWT_KEY_USER_ID, userId);
    }

    public static void setUserId(String userId) {
        set(BaseContextConstants.JWT_KEY_USER_ID, userId);
    }

    // user code
    public static String getUserCode() {
        return get(BaseContextConstants.JWT_KEY_USER_NAME, String.class);
    }

    public static void setUserCode(String code) {
        set(BaseContextConstants.JWT_KEY_USER_NAME, code);
    }

    // tanent
    public static Long getTanentId() {
        return get(BaseContextConstants.JWT_KEY_TANENT_ID, Long.class, BaseContextConstants.NOT_EXIST);
    }


    public static void setLoginUserId(Long userId) {
        set(BaseContextConstants.LOGIN_USER_ID, userId);
    }

    public static Long getLoginUserId() {
        return get(BaseContextConstants.LOGIN_USER_ID, Long.class, BaseContextConstants.NOT_EXIST);
    }

    public static String getLoginUserIdStr() {
        return String.valueOf(getLoginUserId());
    }

    public static String getLoginUserName() {
        return get(BaseContextConstants.LOGIN_USER_NAME, String.class, BaseContextConstants.NOT_EXIST);
    }

    public static void setLoginUserName(String loginUserName) {
        set(BaseContextConstants.LOGIN_USER_NAME, loginUserName);
    }

    public static void setTanentId(Long tanentId) {
        set(BaseContextConstants.JWT_KEY_TANENT_ID, tanentId);
    }

    // organization
    public static Long getOrgId() {
        return get(BaseContextConstants.JWT_KEY_ORG_ID, Long.class, BaseContextConstants.NOT_EXIST);
    }

    public static String getOrgIdStr() {
        return String.valueOf(getOrgId());
    }

    public static void setOrgId(Long orgId) {
        set(BaseContextConstants.JWT_KEY_ORG_ID, orgId);
    }

    public static void setOrgId(String orgId) {
        set(BaseContextConstants.JWT_KEY_ORG_ID, orgId);
    }

    // company
    public static Long getCompanyId() {
        return get(BaseContextConstants.JWT_KEY_COMPANY_ID, Long.class, BaseContextConstants.NOT_EXIST);
    }

    public static String getCompanyIdStr() {
        return String.valueOf(getCompanyId());
    }

    public static void setCompanyId(Long orgId) {
        set(BaseContextConstants.JWT_KEY_COMPANY_ID, orgId);
    }

    public static void setCompanyId(String orgId) {
        set(BaseContextConstants.JWT_KEY_COMPANY_ID, orgId);
    }


    // token
    public static String getToken() {
        return get(BaseContextConstants.BEARER_HEADER_KEY, String.class);
    }

    public static void setToken(String token) {
        set(BaseContextConstants.BEARER_HEADER_KEY, token);
    }

    // authorities
    public static List<String> getAuthorities() {
        return Convert.toList(String.class, get(BaseContextConstants.JWT_KEY_AUTHORITIES, String.class));
    }

    public static void setAuthorities(String authorities) {
        set(BaseContextConstants.JWT_KEY_AUTHORITIES, authorities);
    }

    // client_id
    public static String getClientId() {
        return get(BaseContextConstants.JWT_KEY_CLIENT_ID, String.class);
    }

    public static void setClientId(String val) {
        set(BaseContextConstants.JWT_KEY_CLIENT_ID, val);
    }

    // page total
    public static <T> void endPage(List<T> data) {
        PageInfo<T> pageInfo = new PageInfo<>(data);
        setPageTotal(pageInfo.getTotal());
    }

    public static Long getPageTotal() {
        return get(BaseContextConstants.PAGE_TOTAL, Long.class);
    }

    public static void setPageTotal(Long val) {
        set(BaseContextConstants.PAGE_TOTAL, val);
    }

    // gray version
    public static String getGrayVersion() {
        return get(BaseContextConstants.GRAY_VERSION, String.class);
    }

    public static void setGrayVersion(String val) {
        set(BaseContextConstants.GRAY_VERSION, val);
    }

    public static void remove() {
        THREAD_LOCAL.remove();
    }
}
