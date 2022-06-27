package io.github.bluething.playground.multitenancy.imperative.infrastructure.persistence;

import io.github.bluething.playground.multitenancy.imperative.config.entity.AbstractBaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tenant_user")
@Getter
@Setter
@NoArgsConstructor
public class TenantUserEntity extends AbstractBaseEntity {
    @Id
    @Column(name = "user_id")
    private String userId;
    @Column(name = "email")
    private String email;
    @Column(name = "given_name")
    private String givenName;
    @Column(name = "family_name")
    private String familyName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_ref")
    private TenantEntity tenant;

    public TenantUserEntity(String userId, String tenantId, String email, String givenName, String familyName, TenantEntity tenantEntity) {
        super(tenantId);
        this.userId = userId;
        this.email = email;
        this.givenName = givenName;
        this.familyName = familyName;
        this.tenant = tenantEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TenantUserEntity that = (TenantUserEntity) o;
        return Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}
