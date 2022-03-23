package com.sd365.common.core.annotation.stuffer.mybatis;

import com.github.pagehelper.Page;
import com.github.pagehelper.page.PageMethod;
import com.sd365.common.core.common.pojo.vo.BaseQuery;
import com.sd365.common.core.context.BaseContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @class PaginationAspect
 * @classdesc
 * @author Administrator
 * @date 2021-1-12  10:56
 * @version 1.0.0
 * @see 
 * @since 
 */
@Aspect
@Slf4j
@Component
public class PaginationAspect {
    @Pointcut("@annotation(com.sd365.common.core.annotation.mybatis.Pagination)")
    public void pointcut(){
    }

    @Before("pointcut()")
    public void startPaination(JoinPoint joinPoint){
         checkForPagination(joinPoint.getArgs());
    }


    /**
     * @param: 拦截参数
     * @return:
     * @see
     * @since
     */
    private void checkForPagination(Object args[]){
        for(Object arg: args){
            if(arg instanceof BaseQuery){
                BaseQuery baseQuery=(BaseQuery)arg;
                if(BaseContextHolder.get("page")==null){
                    // 开启分页
                    if (baseQuery.getPageNum()<=0){
                        baseQuery.setPageNum(1);
                    }
                    if(baseQuery.getPageSize()<=0){
                        baseQuery.setPageSize(20);
                    }
                    Page page = PageMethod.startPage(baseQuery.getPageNum(), baseQuery.getPageSize(), true);
                    // 设置分页标记 DefaultUnifyResponseBodyAware 将判断该标记
                    BaseContextHolder.set("page",page);
                }

                return ;
            }
        }
    }

}
