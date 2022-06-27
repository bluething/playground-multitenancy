package io.github.bluething.playground.multitenancy.imperative.config.entity;

public interface TenantAware {
    void setTenantId(String tenantId);

    String getTenantId();
}
