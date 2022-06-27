package io.github.bluething.playground.multitenancy.imperative.infrastructure.persistence;

import io.github.bluething.playground.multitenancy.imperative.domain.Tenant;
import io.github.bluething.playground.multitenancy.imperative.domain.TenantPersistencePort;
import io.github.bluething.playground.multitenancy.imperative.domain.TenantUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TenantPersistenceAdapter implements TenantPersistencePort {
    private TenantRepository tenantRepository;

    @Autowired
    public TenantPersistenceAdapter(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    @Override
    public Tenant getTenant(String tenantId) {
        TenantEntity entity = tenantRepository.findByTenantId(tenantId);
        List<TenantUser> tenantUsers = entity.getTenantUsers().stream()
                .map(tenantUser -> new TenantUser(tenantUser.getUserId(), tenantUser.getEmail(), tenantUser.getGivenName() + " " + tenantUser.getFamilyName()))
                .toList();
        Tenant tenant = new Tenant(entity.getTenantId(), entity.getName(), entity.getStatus(), entity.getTier(), tenantUsers);
        return tenant;
    }
}
