package io.github.bluething.playground.multitenancy.imperative.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TenantRestAdapter implements TenantRestPort {
    private TenantPersistencePort tenantPersistencePort;

    @Autowired
    public TenantRestAdapter(TenantPersistencePort tenantPersistencePort) {
        this.tenantPersistencePort = tenantPersistencePort;
    }

    @Override
    @Transactional(readOnly = true)
    public Tenant getTenant(String tenantId) {
        return tenantPersistencePort.getTenant(tenantId);
    }
}
