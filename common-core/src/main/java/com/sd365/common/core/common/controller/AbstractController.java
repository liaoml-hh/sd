/**
 * @file: AbstractController.java
 * @author: liang_xiaojian
 * @date: 2020/8/26 14:19
 * @copyright: 2020-2023 www.bosssoft.com.cn Inc. All rights reserved.
 */
package com.sd365.common.core.common.controller;

import com.github.pagehelper.Page;
import com.sd365.common.core.common.api.AppUtils;
import com.sd365.common.core.common.api.CommonResponse;
import com.github.pagehelper.page.PageMethod;
import com.sd365.common.core.context.BaseContextHolder;

/**
 * @class AbstractController
 * @classdesc 请分析所有的controller 可能存在哪些公用的功能
 * <br/> 定义通用的方法在该控制器下 例如分页插件的管理
 * <br/> 因为基础框架增加了 根据参数类型自动调用 PageHelper 所以 该类分页方法做了兼容处理
 * @author liang_xiaojian
 * @date 2020/8/26  14:19
 * @version 1.0.0
 * @see
 * @since
 */
public abstract class AbstractController {

    /** 查询前判断是否已经被框架调用如果调用则不需要重复调用
     * @param:
     * @return:
     * @see
     * @since
     */
    protected void doBeforePagination(int pageIndex, int pageSize) {
        if(callBeforeMe()) return ;
        Page page= PageMethod.startPage(pageIndex, pageSize);
    }

    protected Page doBeforePagination(int pageIndex, int pageSize,boolean count) {
        if(callBeforeMe()){
            return BaseContextHolder.get("page",Page.class);
        }else{
            Page page= PageMethod.startPage(pageIndex, pageSize,count);
            return page;
        }

    }

    private Boolean callBeforeMe(){
        return BaseContextHolder.get("page")!=null;
    }

    protected CommonResponse<Object> buildCommonResponse(Object object) {
        CommonResponse<Object> commonResponse = new CommonResponse<>();
        // 调用工具类设置版本等信息
        AppUtils.setResponseExtendInfo(commonResponse);
        commonResponse.setBody(object);
        return commonResponse;
    }

}
