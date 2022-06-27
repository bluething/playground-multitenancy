package io.github.bluething.playground.multitenancy.imperative.infrastructure.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record TenantResponse(@JsonProperty("tenantId") String tenantId,
                             @JsonProperty("name") String name,
                             @JsonProperty("status") String status,
                             @JsonProperty("tier") String tier,
                             @JsonProperty("tenantUsers") List<TenantUserResponse> tenantUserResponses) {
}
