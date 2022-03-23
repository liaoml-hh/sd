package com.sd365.common.core.common.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

/**
 * @class TenantBaseEntity
 * @classdesc
 * @author Administrator
 * @date 2020-10-2  9:30
 * @version 1.0.0
 * @see
 * @since
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class TenantBaseEntity extends BaseEntity {
    @Column(name="tenant_id")
    private Long tenantId;
    @Column(name="org_id")
    private Long orgId;
    @Column(name="company_id")
    private Long companyId;
    @Column(name="creator")
    private String creator;
    @Column(name="modifier")
    private String modifier;

}
