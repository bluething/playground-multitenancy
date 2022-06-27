package io.github.bluething.playground.multitenancy.imperative.config.multitenancy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TenantContext {
    private static InheritableThreadLocal<String> currentTenant = new InheritableThreadLocal<>();

    public TenantContext() {
    }

    public static void setTenantId(String tenantId) {
        currentTenant.set(tenantId);
        log.debug("Setting tenantId to " + tenantId);
    }

    public static String getTenantId() {
        return currentTenant.get();
    }

    public static void clear() {
        currentTenant.remove();
    }
}
