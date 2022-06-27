package io.github.bluething.playground.multitenancy.imperative.config.entity;

import io.github.bluething.playground.multitenancy.imperative.config.multitenancy.TenantContext;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

public class TenantListener {
    @PreUpdate
    @PreRemove
    @PrePersist
    public void setTenant(TenantAware entity) {
        final String tenantId = TenantContext.getTenantId();
        entity.setTenantId(tenantId);
    }
}
