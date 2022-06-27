package io.github.bluething.playground.multitenancy.imperative.infrastructure.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TenantUserResponse(@JsonProperty("userId") String userId,
                                 @JsonProperty("email") String email,
                                 @JsonProperty("name") String name) {
}
