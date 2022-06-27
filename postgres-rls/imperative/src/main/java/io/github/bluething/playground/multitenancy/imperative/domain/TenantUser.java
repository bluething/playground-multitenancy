package io.github.bluething.playground.multitenancy.imperative.domain;

public record TenantUser(String userId,
                         String email,
                         String name) {
}
