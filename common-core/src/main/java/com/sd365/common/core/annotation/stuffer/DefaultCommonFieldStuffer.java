package com.sd365.common.core.annotation.stuffer;

import com.sd365.common.core.common.constant.EntityConsts;
import com.sd365.common.core.common.pojo.dto.BaseDTO;
import com.sd365.common.core.common.pojo.dto.TenantBaseDTO;
import com.sd365.common.core.common.pojo.entity.BaseEntity;
import com.sd365.common.core.common.pojo.entity.TenantBaseEntity;
import com.sd365.common.core.common.pojo.vo.BaseQuery;
import com.sd365.common.core.common.pojo.vo.TenantBaseQuery;
import com.sd365.common.core.context.BaseContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @Author jxd
 * @Date 2020/12/6  8:46 下午
 * @Version 1.0
 * @Write For 默认的 对于DTO对象的 字段填充器！！！针对的字段有： 租户id、组织id、公司id、时间、版本、创建人、更新人
 * @Email waniiu@126.com
 */
@Aspect
@Component
public class DefaultCommonFieldStuffer {
    // 日志
    private static final Logger log = LoggerFactory.getLogger(DefaultCommonFieldStuffer.class);
    // 静态id
    private static IdGenerator staticIdGenerator;

    @Autowired
    private IdGenerator idGenerator;

    // 从Java EE5规范开始，Servlet中增加了两个影响Servlet生命周期的注解，@PostConstruct和@PreDestroy，这两个注解被用来修饰一个非静态的void（）方法。写法有如下两种方式
    // 被@PostConstruct修饰的方法会在服务器加载Servlet的时候运行，并且只会被服务器执行一次。PostConstruct在构造函数之后执行，init（）方法之前执行。
    @PostConstruct
    public void init() {
        // 注入 全局静态的 id生成器
        staticIdGenerator = idGenerator;
    }

    @Pointcut("@annotation(com.sd365.common.core.annotation.stuffer.CommonFieldStuffer)")
    public void fillPointCut() {

        // just point cut
    }

    @Around("fillPointCut()")
    public Object autoFillDTOField(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("filling DTO or Entity field...");

        // 1. 获取参数列表
        Object[] args = joinPoint.getArgs();
        if (args == null) {
            throw new IllegalArgumentException("参数列表为空");
        }

        // 2. 获取方法类型
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        CommonFieldStuffer annotation = method.getAnnotation(CommonFieldStuffer.class);
        MethodTypeEnum methodType = annotation.methodType();

        // 获取当前操作用户的信息
        Long userId = BaseContextHolder.getUserId();

        // 3. 分情况探究
        Object param = args[0];
        BaseDTO dto;
        BaseEntity entity;
        BaseQuery query;
        TenantBaseDTO tenantBaseDTO;
        TenantBaseEntity tenantBaseEntity;
        TenantBaseQuery tenantBaseQuery;

        // 如果第一个 参数为 BaseDTO 子类
        if (param instanceof BaseDTO) {
            // 如果是 有 租户id 的 实现类
            if (param instanceof TenantBaseDTO){
                tenantBaseDTO = (TenantBaseDTO) param;
                // 交给这个函数 判断填充字段
                tenantDtoTypeHandle(tenantBaseDTO, methodType, userId);
            }else {
                dto = (BaseDTO) param;
                // 交给这个函数 判断填充字段
                dtoTypeHandle(dto, methodType, userId);
            }
        // 如果第一个 参数为 BaseQuery 子类
        }else if (param instanceof BaseQuery){
            // 如果 是 有租户 的query的话
            if (param instanceof TenantBaseQuery){
                tenantBaseQuery = (TenantBaseQuery) param;
                tenantQueryTypeHandle(tenantBaseQuery, methodType, userId);
            }
        // 如果第一个 参数为 BaseEntity 子类
        }else if (param instanceof BaseEntity){
            // 如果是tenantBaseEntity
            if (param instanceof TenantBaseEntity){
                tenantBaseEntity = (TenantBaseEntity)param;
                // 交给这个函数 判断填充字段
                tenantEntityTypeHandle(tenantBaseEntity, methodType, userId);
            }else {
                entity = (BaseEntity) param;
                entityTypeHandle(entity, methodType, userId);
            }
        }
        // 4.继续执行剩下的操作
        return joinPoint.proceed();
    }
    /**
     * entity类型的处理自动填充的函数
     * @param entity
     * @param methodType
     * @param userId
     */
    private void entityTypeHandle(BaseEntity entity, MethodTypeEnum methodType, Long userId) {
        // 初始化， 重新设置 这些字段
        entity.setUpdatedBy(null);
        entity.setUpdatedTime(null);
        // 根据方法类型设置 entity 的值
        // 如果是插入方法的话
        if (methodType == MethodTypeEnum.ADD){
            entity.setCreatedBy(null);
            entity.setCreatedTime(null);
            // 借用 DefaultEntityFieldStuffer 的方法帮助赋值
            DefaultEntityFieldStuffer.autoFillEntityField(entity);
        }else if (methodType == MethodTypeEnum.UPDATE){
            entity.setUpdatedBy(userId);
            entity.setUpdatedTime(new Date());
            if (entity.getVersion() == null) {
                throw new IllegalArgumentException("version must not be null");
            }
            // 此次不做 version 更新，由乐观锁插件 Dreamroute/locker 完成
        }
    }

    /**
     * entity类型的处理自动填充的函数
     * @param entity
     * @param methodType
     * @param userId
     */
    private void tenantEntityTypeHandle(TenantBaseEntity entity, MethodTypeEnum methodType, Long userId) {
        // 初始化， 重新设置 这些字段
        entity.setTenantId(null);
        entity.setOrgId(null);
        entity.setCompanyId(null);
        entity.setUpdatedBy(null);
        entity.setUpdatedTime(null);
        // 根据方法类型设置 entity 的值
        // 如果是插入方法的话
        if (methodType == MethodTypeEnum.ADD){
            entity.setCreatedBy(null);
            entity.setCreatedTime(null);
            autoFillInsertEntityField(entity);
        }else if (methodType == MethodTypeEnum.UPDATE){
            autoFillUpdateEntityField(entity);
        }else if (methodType == MethodTypeEnum.DELETE){
            autoFillDeleteEntityField(entity);
        }else if (methodType == MethodTypeEnum.QUERY){
            autoFillSelectEntityField(entity);
        }
    }

    /**
     * tenantDto类型的处理自动填充的函数
     * @param dto
     * @param methodType
     * @param userId
     */
    public static void dtoTypeHandle(BaseDTO dto, MethodTypeEnum methodType, Long userId){
        // reset dto field value
        dto.setUpdatedBy(null);
        dto.setUpdatedTime(null);
        // 根据方法类型设置 dto 的值
        // 如果是插入方法的话
        if (methodType == MethodTypeEnum.ADD) {
            dto.setCreatedBy(null);
            dto.setCreatedTime(null);
            // 填充dto字段
            autoFillDTOField(dto);
        } else if (methodType == MethodTypeEnum.UPDATE) {
            dto.setUpdatedBy(userId);
            dto.setUpdatedTime(new Date());
            if (dto.getVersion() == null) {
                throw new IllegalArgumentException("version must not be null");
            }
        } else if (methodType == MethodTypeEnum.DELETE) {
            dto.setUpdatedBy(userId);
            dto.setUpdatedTime(new Date());
            if (dto.getVersion() == null) {
                throw new IllegalArgumentException("version must not be null");
            }
        } else if (methodType == MethodTypeEnum.QUERY) {
            dto.setUpdatedBy(userId);
            dto.setUpdatedTime(new Date());
            if (dto.getVersion() == null) {
                throw new IllegalArgumentException("version must not be null");
            }
        }
    }
    /**
     * tenantDto类型的处理自动填充的函数
     * @param dto
     * @param methodType
     * @param userId
     */
    public static void tenantDtoTypeHandle(TenantBaseDTO dto, MethodTypeEnum methodType, Long userId){
        // reset dto field value

        dto.setUpdatedBy(null);
        dto.setTenantId(null);
        dto.setOrgId(null);
        dto.setCompanyId(null);
        dto.setUpdatedTime(null);
        // 根据方法类型设置 dto 的值
        // 如果是插入方法的话
        if (methodType == MethodTypeEnum.ADD) {
            dto.setCreatedBy(null);
            dto.setCreatedTime(null);
            // 填充dto字段
            autoFillInsertDTOField(dto);
        } else if (methodType == MethodTypeEnum.UPDATE) {
            autoFillUpdateDTOField(dto);
        } else if (methodType == MethodTypeEnum.DELETE) {
            autoFillDeleteDTOField(dto);
        } else if (methodType == MethodTypeEnum.QUERY) {
            autoFillSelectDTOField(dto);
        }
    }
    /**
     *
     * @param tenantBaseQuery
     * @param methodType
     * @param userId
     */
    public static void tenantQueryTypeHandle(TenantBaseQuery tenantBaseQuery,MethodTypeEnum methodType, Long userId){
        tenantBaseQuery.setOrgId(null);
        tenantBaseQuery.setTenantId(null);
        tenantBaseQuery.setCompanyId(null);
        // 如果是 查询方法的话，就设置 tanentId， orgId， CompanuId 这三个字段
        if (methodType == MethodTypeEnum.QUERY){
            tenantBaseQuery.setTenantId(BaseContextHolder.getTanentId());
            tenantBaseQuery.setOrgId(BaseContextHolder.getOrgId());
            tenantBaseQuery.setCompanyId(BaseContextHolder.getCompanyId());
        }
    }
    // -------------------------------  下面四个为 entity的填充方法 -------------------------

    /**
     * 自动为 insert 的通用字段赋值
     * @param baseEntity 所有 entity 的基类
     */
    public static void autoFillInsertEntityField(TenantBaseEntity baseEntity) {
        baseEntity.setId(staticIdGenerator.snowflakeId());
        // 没有设置时再用默认项填充
        if (baseEntity.getStatus() == null) {
            baseEntity.setStatus(EntityConsts.INITIAL_STATUS);
        }
        baseEntity.setCreatedBy(BaseContextHolder.getUserId());
        baseEntity.setCreatedTime(new Date());
        baseEntity.setTenantId(BaseContextHolder.getTanentId());
        baseEntity.setOrgId(BaseContextHolder.getOrgId());
        baseEntity.setCompanyId(BaseContextHolder.getCompanyId());
        baseEntity.setUpdatedBy(BaseContextHolder.getUserId());
        baseEntity.setUpdatedTime(new Date());
        baseEntity.setVersion(EntityConsts.INITIAL_VERSION);
    }
    /**
     * 自动为 update 的通用字段赋值
     * @param baseEntity 所有 entity 的基类
     */
    public static void autoFillUpdateEntityField(TenantBaseEntity baseEntity) {
        baseEntity.setId(staticIdGenerator.snowflakeId());
        // 没有设置时再用默认项填充
        if (baseEntity.getStatus() == null) {
            baseEntity.setStatus(EntityConsts.INITIAL_STATUS);
        }
        baseEntity.setTenantId(BaseContextHolder.getTanentId());
        baseEntity.setOrgId(BaseContextHolder.getOrgId());
        baseEntity.setCompanyId(BaseContextHolder.getCompanyId());
        baseEntity.setUpdatedBy(BaseContextHolder.getUserId());
        baseEntity.setUpdatedTime(new Date());
        baseEntity.setVersion(EntityConsts.INITIAL_VERSION);
    }
    /**
     * 自动为 delete 的通用字段赋值
     * @param baseEntity 所有 entity 的基类
     */
    public static void autoFillDeleteEntityField(TenantBaseEntity baseEntity) {
        baseEntity.setId(staticIdGenerator.snowflakeId());
        // 没有设置时再用默认项填充
        if (baseEntity.getStatus() == null) {
            baseEntity.setStatus(EntityConsts.INITIAL_STATUS);
        }
        baseEntity.setTenantId(BaseContextHolder.getTanentId());
        baseEntity.setOrgId(BaseContextHolder.getOrgId());
        baseEntity.setCompanyId(BaseContextHolder.getCompanyId());
        baseEntity.setUpdatedBy(BaseContextHolder.getUserId());
        baseEntity.setUpdatedTime(new Date());
        baseEntity.setVersion(EntityConsts.INITIAL_VERSION);
    }
    /**
     * 自动为select 的 通用字段赋值
     * @param baseEntity 所有 entity 的基类
     */
    public static void autoFillSelectEntityField(TenantBaseEntity baseEntity) {
        baseEntity.setId(staticIdGenerator.snowflakeId());
        // 没有设置时再用默认项填充
        if (baseEntity.getStatus() == null) {
            baseEntity.setStatus(EntityConsts.INITIAL_STATUS);
        }
        baseEntity.setTenantId(BaseContextHolder.getTanentId());
        baseEntity.setOrgId(BaseContextHolder.getOrgId());
        baseEntity.setCompanyId(BaseContextHolder.getCompanyId());
        baseEntity.setVersion(EntityConsts.INITIAL_VERSION);
    }




    // ----------------------------- 一下为dto的自动填充方法----------------------------
    /**
     * 自动为 insert 通用字段赋值
     * @param baseDTO 所有 DTO 的基类
     */
    public static void autoFillInsertDTOField(TenantBaseDTO baseDTO) {
        baseDTO.setId(staticIdGenerator.snowflakeId());
        // 没有设置时再用默认项填充
        if (baseDTO.getStatus() == null) {
            baseDTO.setStatus(EntityConsts.INITIAL_STATUS);
        }
        baseDTO.setCreatedBy(BaseContextHolder.getUserId());
        baseDTO.setCreatedTime(new Date());
        baseDTO.setTenantId(BaseContextHolder.getTanentId());
        baseDTO.setOrgId(BaseContextHolder.getOrgId());
        baseDTO.setCompanyId(BaseContextHolder.getCompanyId());
        baseDTO.setUpdatedBy(BaseContextHolder.getUserId());
        baseDTO.setUpdatedTime(new Date());
        baseDTO.setVersion(EntityConsts.INITIAL_VERSION);
    }
    /**
     * 自动为 update 通用字段赋值
     * @param dto 所有 DTO 的基类
     */
    public static void autoFillUpdateDTOField(TenantBaseDTO dto){
        // 没有设置时再用默认项填充
        if (dto.getStatus() == null) {
            dto.setStatus(EntityConsts.INITIAL_STATUS);
        }
        dto.setTenantId(BaseContextHolder.getTanentId());
        dto.setOrgId(BaseContextHolder.getOrgId());
        dto.setCompanyId(BaseContextHolder.getCompanyId());
        dto.setUpdatedBy(BaseContextHolder.getUserId());
        dto.setUpdatedTime(new Date());
        if (dto.getVersion() == null) {
            throw new IllegalArgumentException("version must not be null");
        }
        // 此次不做 version 更新，由乐观锁插件 Dreamroute/locker 完成
    }
    /**
     * 自动为 delete 通用字段赋值
     * @param dto 所有 DTO 的基类
     */
    public static void autoFillDeleteDTOField(TenantBaseDTO dto){
        // 没有设置时再用默认项填充
        if (dto.getStatus() == null) {
            dto.setStatus(EntityConsts.INITIAL_STATUS);
        }
        dto.setTenantId(BaseContextHolder.getTanentId());
        dto.setOrgId(BaseContextHolder.getOrgId());
        dto.setCompanyId(BaseContextHolder.getCompanyId());
        dto.setUpdatedBy(BaseContextHolder.getUserId());
        dto.setUpdatedTime(new Date());
        if (dto.getVersion() == null) {
            throw new IllegalArgumentException("version must not be null");
        }
        // 此次不做 version 更新，由乐观锁插件 Dreamroute/locker 完成
    }
    /**
     * 自动为 select 通用字段赋值
     * @param dto 所有 DTO 的基类
     */
    public static void autoFillSelectDTOField(TenantBaseDTO dto){
        // 没有设置时再用默认项填充
        if (dto.getStatus() == null) {
            dto.setStatus(EntityConsts.INITIAL_STATUS);
        }
        dto.setTenantId(BaseContextHolder.getTanentId());
        dto.setOrgId(BaseContextHolder.getOrgId());
        dto.setCompanyId(BaseContextHolder.getCompanyId());
        if (dto.getVersion() == null) {
            throw new IllegalArgumentException("version must not be null");
        }
    }

    /**
     * 自动为通用字段赋值
     * @param dto 所有 dto 的基类
     */
    public static void autoFillDTOField(BaseDTO dto) {
        dto.setId(staticIdGenerator.snowflakeId());
        // 没有设置时再用默认项填充
        if (dto.getStatus() == null) {
            dto.setStatus(EntityConsts.INITIAL_STATUS);
        }
        dto.setCreatedBy(BaseContextHolder.getUserId());
        dto.setCreatedTime(new Date());
        dto.setUpdatedBy(BaseContextHolder.getUserId());
        dto.setUpdatedTime(new Date());
        dto.setVersion(EntityConsts.INITIAL_VERSION);
    }

}
