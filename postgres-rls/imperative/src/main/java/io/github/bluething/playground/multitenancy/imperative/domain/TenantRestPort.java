package io.github.bluething.playground.multitenancy.imperative.domain;

public interface TenantRestPort {
    Tenant getTenant(String tenantId);
}
