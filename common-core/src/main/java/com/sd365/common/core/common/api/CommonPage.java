/**
 * @file:  CommonPage.java
 * @author: liang_xiaojian
 * @date:   2020/9/11 20:49
 * @copyright: 2020-2023 www.bosssoft.com.cn Inc. All rights reserved.
 */
package com.sd365.common.core.common.api;

import com.alibaba.fastjson.JSONObject;
import com.sd365.common.core.common.aop.MyPageInfo;
import com.sd365.common.core.common.exception.BusinessException;
import com.sd365.common.core.common.exception.code.BusinessErrorCode;
import com.sd365.common.core.context.BaseContextHolder;
import com.github.pagehelper.PageInfo;
import com.sd365.common.util.StringUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @class CommonPage
 * @classdesc 通用分页封装，和 CommonResponse 搭配用法：
 * <pre>
 *     // 下面这种是本地服务, 需要开启分页后才会生效
 *     @PostMapping("xxx")
 *     CommonResponse<CommonPage<xxxVO>> listXxx(@Valid @RequestBody CommonRequest<xxxQuery> request) {
 *         List<xxxDTO> dtoList = xxxService.listXxx(request.getBody);
 *         List<xxxVO> voList = BeanUtil.copyList(dtoList, xxxVo.class);
 *         CommonPage<xxxVO> result = CommonPage.restPage(voList);
 *         return CommonResponseUtils.success(result);
 *     }
 *     // 以下这种是远程服务, xxxService 为远程服务接口
 *     @PostMapping("xxx")
 *     CommonResponse<CommonPage<xxxVO>> listXxx(@Valid @RequestBody CommonRequest<xxxQuery> request) {
 *         CommonResponse<CommonPage<xxxDTO>> response = xxxService.listXxx(request);
 *         CommonPage<xxxDTO> commonPage = response.getBody();
 *         List<xxxDTO> dtoList = commonPage.getData();
 *         List<xxxVO> voList = BeanUtil.copyList(dtoList, xxxVo.class);
 *         CommonPage<xxxVO> result = CommonPage.restPage(commonPage);
 *         result.setData(voList);
 *         return CommonResponseUtils.success(result);
 *     }
 * </pre>
 * @author liang_xiaojian
 * @date 2020/9/11  20:49
 * @version 1.0.0
 * @see
 * @since
 */
@Data
@Slf4j
public class CommonPage<T> {

    /**
     * 总记录数
     */
    private Long total;
    /**
     * 页号
     */
    private Integer pageNum;
    /**
     * 页大小
     */
    private Integer pageSize;

    /**
     * 总页数
     */
    private Integer pages;
    /**
     * 数据列表
     */
    private List<T> data;
    public CommonPage() {
    }

    /**
     *  增加方法 针对框架修改只返回查询vo即可由框架完成统一应答对象构建
     *  2020-11-9修复了 返回数据页码信息错误
     * @Date 2020-10-28
     * @param list
     */
    public CommonPage(List<T> list) {

        if(null!=BaseContextHolder.get("page")){
            String info= BaseContextHolder.get("pageInfo");
            if(!StringUtil.isEmpty(info)){ // 兼容没有发送 total 和 pages的 xxxService的commonQuery
                try {
                    MyPageInfo myPageInfo= JSONObject.parseObject(info,MyPageInfo.class);
                    this.total=new Long(myPageInfo.getTotal());
                    this.pages=myPageInfo.getPages();
                }catch (Exception ex){
                    log.info("获取业务模块发送的分页总记录数和页数错误,错误消息:"+ex.getMessage(),ex);
                }

            }
            PageInfo<T> pageInfo=new PageInfo(list);
            this.pageNum=pageInfo.getPageNum();
            this.pageSize=pageInfo.getPageSize();

            this.setData(pageInfo.getList());
        }else{
            throw  new BusinessException(BusinessErrorCode.COMMON_LLB_COMMON_PAGE_RROR,new Exception("CommonPage 构造无法取得 BaseContextHolder 参数"));
        }
    }


    public CommonPage(Long total, Integer pageNum, Integer pageSize, List<T> list) {
        this.total = total;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.data = list;
    }

    public CommonPage(CommonPage<T> commonPage) {
        this.total = commonPage.getTotal();
        this.pageNum = commonPage.getPageNum();
        this.pageSize = commonPage.getPageSize();
        this.data = null;
    }

    /**
     * 将 PageHelper 分页后的 list 转为分页信息
     * 注意：需要在本服务调用，跨服务不可用
     */
    public static <T> CommonPage<T> restPage(List<T> data) {
        CommonPage<T> result = new CommonPage();
        PageInfo<T> pageInfo = new PageInfo(data);
        result.setPageNum(pageInfo.getPageNum());
        result.setPageSize(pageInfo.getPageSize());

        // 需要在 service 调用  BaseContextHolder.endPage(List<?> data);
        result.setTotal(BaseContextHolder.getPageTotal());

        result.setData(pageInfo.getList());
        return result;
    }

    /**
     * 将 CommonPage<T> 转成 CommonPage<U>, 只拷贝分页信息，跳过数据列表
     * 跨服务可用
     * @param commonPage 源数据
     * @param <T> 源类型
     * @param <U> 模板类型
     * @return 目标类型的 CommonPage
     */
    public static <T, U> CommonPage<U> restPage(CommonPage<T> commonPage) {
        CommonPage<U> result = new CommonPage<>();
        result.setTotal(commonPage.getTotal());
        result.setPageNum(commonPage.getPageNum());
        result.setPageSize(commonPage.getPageSize());
        result.setData(null);
        return result;
    }

    /**
     * 将 PageHelper 分页后的 list 转为分页信息
     * 通用版本
     */
    public static <T> CommonPage<T> restPage(PageInfo<T> pageInfo) {
        CommonPage<T> result = new CommonPage<>();
        result.setPageNum(pageInfo.getPageNum());
        result.setPageSize(pageInfo.getPageSize());
        result.setTotal(pageInfo.getTotal());
        result.setData(pageInfo.getList());
        return result;
    }

}
