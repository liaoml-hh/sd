/**
 * @file: UserContextHandlerInterceptor.java
 * @author: liang_xiaojian
 * @date: 2020/9/13 10:02
 * @copyright: 2020-2023 www.bosssoft.com.cn Inc. All rights reserved.
 */
package com.sd365.common.core.context;

import cn.hutool.core.convert.Convert;
import cn.hutool.json.JSONObject;
import com.alibaba.fastjson.JSONArray;
import com.sd365.common.util.StringUtil;
import com.sd365.common.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author liang_xiaojian
 * @version 1.0.0
 * @class UserContextHandlerInterceptor
 * @classdesc 用户请求上下文拦截器，用于服务间消息传递
 * @date 2020/9/13  10:02
 * @see
 * @since
 */
@Slf4j
public class UserContextHandlerInterceptor extends HandlerInterceptorAdapter {

    public final static String ACCESS_TOKEN = "accessToken";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            // 如果是从登录页面
            if ("doPostAccessToken".equals(handlerMethod.getMethod().getName())) {
                return super.preHandle(request, response, handler);
            }

            //从 Header 中获取用户信息
            String userStr = request.getHeader(BaseContextConstants.USER_CONTEXT_ATTRIBUTES);
            if (StringUtil.isNotEmpty(userStr)) {
                JSONObject userJsonObject = new JSONObject(userStr);

                // 将 用户 ID，用户名，机构 ID，公司 ID，权限集合 存入 BaseContextHolder，方便后续使用
                BaseContextHolder.setUserId(Convert.toLong(userJsonObject.get(BaseContextConstants.JWT_KEY_USER_ID)));
                BaseContextHolder.set(BaseContextConstants.JWT_KEY_USER_NAME,
                        Convert.toLong(userJsonObject.get(BaseContextConstants.JWT_KEY_USER_NAME)));
                // 这里 要用到 tanentId、orgId、companyId等
                BaseContextHolder.setTanentId(Convert.toLong(userJsonObject.get(BaseContextConstants.JWT_KEY_TANENT_ID)));
                BaseContextHolder.setOrgId(Convert.toLong(userJsonObject.get(BaseContextConstants.JWT_KEY_ORG_ID)));
                BaseContextHolder.setCompanyId(Convert.toLong(userJsonObject.get(BaseContextConstants.JWT_KEY_COMPANY_ID)));
                BaseContextHolder.setAuthorities(userJsonObject.getStr(BaseContextConstants.JWT_KEY_AUTHORITIES));
            }
            String token = getToken(request);
            //往BaseContext中设置了token，account与roleIds的列表
            setTokenTOBaseContext(token);
        }
        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        BaseContextHolder.remove();
        super.afterCompletion(request, response, handler, ex);
    }


    public String getToken(HttpServletRequest request) {
        String token = request.getHeader(ACCESS_TOKEN);
        if (token == null) {
            token = request.getParameter(ACCESS_TOKEN);
        }
        return token;
    }

    private void setTokenTOBaseContext(String token) {
        if (StringUtil.isNotEmpty(token)) {
            BaseContextHolder.setToken(token);
            final List<String> list = TokenUtil.decryptToken(token);
            //token的head中的信息
            if (!list.get(0).isEmpty()) {
                com.alibaba.fastjson.JSONObject object1 = com.alibaba.fastjson.JSONObject.parseObject(list.get(0));
                object1.getString("alg");
                object1.getString("typ");
            }
            //token中payload中的信息
            if (!list.get(1).isEmpty()) {
                com.alibaba.fastjson.JSONObject object1 = com.alibaba.fastjson.JSONObject.parseObject(list.get(1));
                final String account = object1.getString("account");
                final String roleIdArrayString = object1.getString("roleIds");
                final String companyId = object1.getString("companyId");
                final String tenantId = object1.getString("tenantId");
                final String userName = object1.getString("userName");
                final String userId = object1.getString("userId");
                final String orgId = object1.getString("orgId");
                List<Object> roleIdList = JSONArray.parseArray(roleIdArrayString, Object.class);
                BaseContextHolder.set(account, roleIdList);
                BaseContextHolder.setCompanyId(companyId);
                BaseContextHolder.setCompanyId(Long.parseLong(companyId));
                BaseContextHolder.setTanentId(Long.parseLong(tenantId));
                BaseContextHolder.setOrgId(Long.parseLong(orgId));
                BaseContextHolder.setOrgId(orgId);
                BaseContextHolder.setLoginUserId(Long.parseLong(userId));
                BaseContextHolder.setLoginUserName(userName);


            }

            /**
             * 先版本token没有Signature
             */
            //token中Signature中的信息
//            if (!list.get(2).isEmpty()) {
//                com.alibaba.fastjson.JSONObject object1 = com.alibaba.fastjson.JSONObject.parseObject(list.get(2));
//            }
        }
    }
}
