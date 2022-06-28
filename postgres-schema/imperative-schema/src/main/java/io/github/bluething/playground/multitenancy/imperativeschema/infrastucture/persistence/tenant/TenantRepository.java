package io.github.bluething.playground.multitenancy.imperativeschema.infrastucture.persistence.tenant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TenantRepository extends JpaRepository<TenantEntity, String> {
    @Query("select t from TenantEntity t where t.tenantId = :tenantId")
    Optional<TenantEntity> findByTenantId(@Param("tenantId") String tenantId);
}
