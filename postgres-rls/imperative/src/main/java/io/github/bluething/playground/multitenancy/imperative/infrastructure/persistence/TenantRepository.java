package io.github.bluething.playground.multitenancy.imperative.infrastructure.persistence;

import org.springframework.data.repository.CrudRepository;

public interface TenantRepository extends CrudRepository<TenantEntity, String> {
    TenantEntity findByTenantId(String tenantId);
}