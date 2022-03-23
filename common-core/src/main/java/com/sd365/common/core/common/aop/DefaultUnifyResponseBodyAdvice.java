package com.sd365.common.core.common.aop;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.Page;
import com.sd365.common.core.common.api.CommonPage;
import com.sd365.common.core.common.api.CommonResponse;
import com.sd365.common.core.common.api.CommonResponseUtils;
import com.sd365.common.core.context.BaseContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @class DefaultUnifyResponseBodyAdvice
 * @classdesc 所以RestController 定义的统一应答报文通过该类生成,如果需要自定义应答则重写 beforeBodyWrite
 * @author Administrator
 * @date 2020-10-31  15:31
 * @version 1.0.0
 * @see
 * @since
 */
@Slf4j
@RestControllerAdvice(basePackages = "com.sd365")
public class DefaultUnifyResponseBodyAdvice implements ResponseBodyAdvice<Object> {
        /**
     * need not wrapping
     */
    private final static String NOWRAP="nowrap";
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    /**
     *   实现ResponseBodyAware 方法 对返回值进行统一的拦截
     * @Date 2020-10-28
     * @param o 控制器方法返回的对象
     * @param methodParameter
     * @param mediaType
     * @param aClass
     * @param serverHttpRequest
     * @param serverHttpResponse
     * @return
     */

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if(o instanceof CommonResponse){// 控制器进行过返回值处理所以不拦截
            return o;
        }else if(o instanceof CommonPage){ // 控制器直接返回了CommonPage对象 表示前端发起查询
            return CommonResponseUtils.success(o);
        }else{ // 控制器直接返回普通对象 普通对象分为试图分页的对象和没有分页需求的对象
            try {
                if(BaseContextHolder.get("page")!=null && o instanceof List<?>){
                   List<?> list=(List)o;
                    CommonPage<Object> commonPage=new CommonPage(list);
                    return CommonResponseUtils.success(commonPage);
                }else{
                          // fix by abel.zhan  2020-12-18  for authorization return boolean by feign call
                    if(!StringUtils.isEmpty(BaseContextHolder.get(NOWRAP))){
                        return o;
                    }
                    return CommonResponseUtils.success(o);
                }
            }finally {
                if(BaseContextHolder.get("page")!=null){
                    BaseContextHolder.set("page",null);//清零分页请求对象
                }
            }


        }
    }

}
