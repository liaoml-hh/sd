package com.sd365.common.core.common.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @class TenantBaseDTO
 * @classdesc 继承基本的DTO 主要包含 租户ID 和 机构ID 以及公司ID
 * @author Administrator
 * @date 2020-10-2  17:08
 * @version 1.0.0
 * @see
 * @since
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class TenantBaseDTO extends BaseDTO {
    /**
     * 租户ID
     */
    private Long tenantId;
    /**
     * 机构ID
     */
    private Long orgId;
    /**
     * 公司ID
     */
    private Long companyId;
}
