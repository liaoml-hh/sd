package com.sd365.common.core.common.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @class TenantBaseVO
 * @classdesc  基于租户的系统的数据的VO从这里继承，包含了租户和组织
 * 机构以及公司最基本的信息，若非租户系统则从其他VO继承
 * @author Administrator
 * @date 2020-10-2  17:15
 * @version 1.0.0
 * @see
 * @since
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class TenantBaseVO extends BaseVO {
    private Long tenantId;
    private Long orgId;
    private Long companyId;
    private String tenantName;
    private String OrgName;
    private String companyName;
}
