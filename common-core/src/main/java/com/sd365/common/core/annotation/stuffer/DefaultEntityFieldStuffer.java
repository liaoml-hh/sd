/**
 * @file:  DefaultEntityFieldStuffer.java
 * @author: liang_xiaojian
 * @date:   2020/9/2 10:49
 * @copyright: 2020-2023 www.bosssoft.com.cn Inc. All rights reserved.
 */
package com.sd365.common.core.annotation.stuffer;
import com.sd365.common.core.common.constant.EntityConsts;
import com.sd365.common.core.context.BaseContextHolder;
import com.sd365.common.core.common.pojo.entity.BaseEntity;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @class DefaultEntityFieldStuffer
 * @classdesc 切面类用于填充类通用属性
 * @author liang_xiaojian
 * @date 2020/9/2  10:50
 * @version 1.0.0
 * @see
 * @since
 */
@Aspect
@Component
public class DefaultEntityFieldStuffer {
    private static final Logger log = LoggerFactory.getLogger(DefaultEntityFieldStuffer.class);
    private static IdGenerator staticIdGenerator;

    @Autowired
    private IdGenerator idGenerator;

    @PostConstruct
    public void init() {
        staticIdGenerator = idGenerator;
    }

    @Pointcut("@annotation(com.sd365.common.core.annotation.stuffer.EntityFieldStuffer)")
    public void fillPointCut() {
        // just point cut
    }

    @Around("fillPointCut()")
    public Object autoFillEntityField(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("filling entity field...");

        // 1. 获取参数列表
        Object[] args = joinPoint.getArgs();
        if (args == null) {
            throw new IllegalArgumentException("参数列表为空");
        }
        // 2. 判断参数是否为 BaseEntity 子类
        Object param = args[0];
        if (!(param instanceof BaseEntity)) {
            throw new IllegalArgumentException("第一个参数不是 BaseEntity 的子类");
        }
        BaseEntity entity = (BaseEntity) param;

        // 3. 获取方法类型
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        EntityFieldStuffer annotation = method.getAnnotation(EntityFieldStuffer.class);
        MethodTypeEnum methodTypeEnum = annotation.methodType();

        // 获取当前操作用户的信息
        Long userId = BaseContextHolder.getUserId();

        // reset dto field value
        entity.setCreatedBy(null);
        entity.setCreatedTime(null);
        entity.setUpdatedBy(null);
        entity.setUpdatedTime(null);

        // 4. 根据方法类型设置 dto 的值
        if (methodTypeEnum == MethodTypeEnum.ADD) {
            autoFillEntityField(entity);
        } else if (methodTypeEnum == MethodTypeEnum.UPDATE) {
            entity.setUpdatedBy(userId);
            entity.setUpdatedTime(new Date());
            if (entity.getVersion() == null) {
                throw new IllegalArgumentException("version must not be null");
            }
            // 此次不做 version 更新，由乐观锁插件 Dreamroute/locker 完成
        }
        // 继续执行剩下的操作
        return joinPoint.proceed();
    }

    /**
     * 自动为通用字段赋值
     * @param baseEntity 所有 entity 的基类
     */
    public static void autoFillEntityField(BaseEntity baseEntity) {
        baseEntity.setId(staticIdGenerator.snowflakeId());
        // 没有设置时再用默认项填充
        if (baseEntity.getStatus() == null) {
            baseEntity.setStatus(EntityConsts.INITIAL_STATUS);
        }
        baseEntity.setCreatedBy(BaseContextHolder.getUserId());
        baseEntity.setCreatedTime(new Date());
        baseEntity.setUpdatedBy(baseEntity.getCreatedBy());
        baseEntity.setUpdatedTime(baseEntity.getCreatedTime());
        baseEntity.setVersion(EntityConsts.INITIAL_VERSION);
    }
}
