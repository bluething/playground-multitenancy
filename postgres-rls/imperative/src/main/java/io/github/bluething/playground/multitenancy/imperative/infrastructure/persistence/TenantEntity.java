package io.github.bluething.playground.multitenancy.imperative.infrastructure.persistence;

import io.github.bluething.playground.multitenancy.imperative.config.entity.AbstractBaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tenant")
@Getter
@Setter
@NoArgsConstructor
public class TenantEntity extends AbstractBaseEntity {

    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "status")
    private String status;
    @Column(name = "tier")
    private String tier;

    @OneToMany(mappedBy= "tenant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TenantUserEntity> tenantUsers = new ArrayList<>();

    @Builder
    public TenantEntity(String id, String name, String status, String tier, List<TenantUserEntity> tenantUsers) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.tier = tier;
        this.tenantUsers = tenantUsers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TenantEntity that = (TenantEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void addTenantUser(TenantUserEntity tenantUser) {
        getTenantUsers().add(tenantUser);
        tenantUser.setTenant(this);
    }

    public void removeTenantUser(TenantUserEntity tenantUser) {
        getTenantUsers().remove(tenantUser);
        tenantUser.setTenant(null);
    }
}
