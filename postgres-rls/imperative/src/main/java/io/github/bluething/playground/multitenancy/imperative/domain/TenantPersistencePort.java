package io.github.bluething.playground.multitenancy.imperative.domain;

public interface TenantPersistencePort {
    Tenant getTenant(String tenantId);
}
