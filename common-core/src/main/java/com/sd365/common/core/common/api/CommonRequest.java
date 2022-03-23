/**
 * @file:  CommonRequest.java
 * @author: liang_xiaojian
 * @date:   2020/8/27 22:48
 * @copyright: 2020-2023 www.bosssoft.com.cn Inc. All rights reserved.
 */
package com.sd365.common.core.common.api;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @class CommonRequest
 * @classdesc
 * @author liang_xiaojian
 * @date 2020/8/27  22:48
 * @version 1.0.0
 * @see
 * @since
 */
public class CommonRequest<T> implements Serializable {

    private static final long serialVersionUID = 4432305443028998723L;

    private Head head;

    @NotNull
    private T body;

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
        return "CommonRequest{" +
                "head=" + head +
                ", body=" + body +
                '}';
    }
}
