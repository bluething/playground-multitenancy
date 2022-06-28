package io.github.bluething.playground.multitenancy.imperativeschema.infrastucture.persistence.tenant;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "tenant")
@NoArgsConstructor
@Getter
public class TenantEntity {
    @Id
    @Column(name = "tenant_id")
    private String tenantId;
    @Column(name = "schema")
    private String schema;

    public TenantEntity(String tenantId, String schema) {
        this.tenantId = tenantId;
        this.schema = schema;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TenantEntity that = (TenantEntity) o;
        return Objects.equals(tenantId, that.tenantId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tenantId);
    }
}
