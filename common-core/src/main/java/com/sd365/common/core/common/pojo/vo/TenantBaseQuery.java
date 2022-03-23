package com.sd365.common.core.common.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @param
 * @description:
 * @author: Administrator
 * @create: 2020-10-02 17:11
 * @since
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract  class  TenantBaseQuery extends BaseQuery {
    private Long tenantId;
    private Long orgId;
    private Long companyId;
}
