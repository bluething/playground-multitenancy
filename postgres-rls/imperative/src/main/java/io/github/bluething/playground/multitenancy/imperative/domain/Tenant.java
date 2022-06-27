package io.github.bluething.playground.multitenancy.imperative.domain;

import java.util.List;

public record Tenant(String tenantId,
                     String name,
                     String status,
                     String tier,
                     List<TenantUser> tenantUserResponses) {
}
