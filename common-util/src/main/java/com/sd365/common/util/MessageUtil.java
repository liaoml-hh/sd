/**
 * @file:  MessageUtil.java
 * @author:  zhang_yulin
 * @date:  2020/08/29
 * @copyright:  2020-2023 www.bosssoft.com.cn Inc. All rights reserved.
 */
package com.sd365.common.util;
import java.io.IOException;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

/**
 * @class:  MessageUtil
 * @classdesc:  短信工具类
 * @author:  zhang_yulin
 * @date:  2020/8/26
 * @version:  1.0.0
 * @see
 * @since
 */
public class MessageUtil {

    /**
     * 发送短信
     *
     * @param userName           SMS注册的用户名
     * @param key       注册成功后，登录网站后得到的密钥
     * @param phoneNumbers 接收者手机号码
     * @param message     短信内容
     * @throws IOException
     */
    public static int sendMessage(String userName, String key, String phoneNumbers, String message) throws IOException {
        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod("http://gbk.sms.webchinese.cn");
        // PostMethod post = new PostMethod("http://sms.webchinese.cn/web_api/");
        post.addRequestHeader("Content-Type",
                "application/x-www-form-urlencoded;charset=gbk");// 在头文件中设置转码
        NameValuePair[] data = { new NameValuePair("Uid", userName),// 注册的用户名
                new NameValuePair("Key", key),// 注册成功后，登录网站后得到的密钥
                new NameValuePair("smsMob", phoneNumbers),// 手机号码
                new NameValuePair("smsText", message) };// 短信内容
        post.setRequestBody(data);

        client.executeMethod(post);
        Header[] headers = post.getResponseHeaders();
        int statusCode = post.getStatusCode();

        return statusCode;
    }

}
