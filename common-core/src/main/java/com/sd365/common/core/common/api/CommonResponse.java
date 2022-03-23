/**
 * @file: CommonResponse.java
 * @author: liang_xiaojian
 * @date: 2020/8/26 14:36
 * @copyright: 2020-2023 www.bosssoft.com.cn Inc. All rights reserved.
 */
package com.sd365.common.core.common.api;

import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @class CommonResponse
 * @classdesc  通过该类实施统一应答，统一应答参考 DefaultUnifyResponseBodyAware
 * @author liang_xiaojian
 * @date 2020/8/26  14:36
 * @version 1.0.0
 * @see
 * @since
 */
public class CommonResponse<T> implements Serializable {

    private static final long serialVersionUID = -6372561804247815227L;

    public static final class Head implements Serializable {

        private static final long serialVersionUID = 9068029931352525287L;
        // 应用程序版本，必填
        @NotNull
        private String version;
        // 应答码，0 代表成功，失败则填写异常错误码
        private String code;
        // 消息的显示全部服务端定义
        private String message;
        // 加密标志，1标记加密 0不加密
        private Integer flag = 0;

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Integer getFlag() {
            return flag;
        }

        public void setFlag(Integer flag) {
            this.flag = flag;
        }
    }

    /**
     * 应答报文头
     */
    private Head head;

    /***
     *
     */
    @NotNull
    private T body;

    public CommonResponse() {
        this.head = new Head();
    }

    public Head getHead() {
        return head;
    }

    public void setHead(Head head) {
        this.head = head;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "CommonResponse{" +
                "head=" + head +
                ", body=" + body +
                '}';
    }

    public static <T> CommonResponseBuilder<T> builder() {
        return new CommonResponseBuilder<>();
    }

    public static final class CommonResponseBuilder<T> {

        private final Head head;

        private T body;

        private CommonResponseBuilder() {
            this.head = new Head();
        }

        public CommonResponseBuilder<T> withVersion(String version) {
            this.head.setCode(version);
            return this;
        }

        public CommonResponseBuilder<T> withCode(String code) {
            this.head.setCode(code);
            return this;
        }

        public CommonResponseBuilder<T> withMessage(String message) {
            this.head.setMessage(message);
            return this;
        }

        public CommonResponseBuilder<T> withFlag(int flag) {
            this.head.setFlag(flag);
            return this;
        }

        public CommonResponseBuilder<T> withBody(T body) {
            this.body = body;
            return this;
        }

        /**
         * Build result.
         *
         * @return result
         */
        public CommonResponse<T> build() {
            CommonResponse<T> commonResponse = new CommonResponse();
            commonResponse.setHead(head);
            if (StringUtils.isEmpty(commonResponse.getHead().getVersion())) {
                AppUtils.setResponseExtendInfo(commonResponse);
            }
            commonResponse.setBody(body);
            return commonResponse;
        }
    }
}
