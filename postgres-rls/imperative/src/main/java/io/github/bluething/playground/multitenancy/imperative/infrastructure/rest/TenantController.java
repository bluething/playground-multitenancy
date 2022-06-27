package io.github.bluething.playground.multitenancy.imperative.infrastructure.rest;

import io.github.bluething.playground.multitenancy.imperative.domain.Tenant;
import io.github.bluething.playground.multitenancy.imperative.domain.TenantRestPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TenantController {
    private TenantRestPort tenantRestPort;

    @Autowired
    public TenantController(TenantRestPort tenantRestPort) {
        this.tenantRestPort = tenantRestPort;
    }

    @GetMapping(path = "/tenant/{tenantId}")
    public ResponseEntity<TenantResponse> getTenant(@PathVariable("tenantId") String tenantId) {
        Tenant tenant = tenantRestPort.getTenant(tenantId);
        List<TenantUserResponse> tenantUserResponse = tenant.tenantUserResponses().stream()
                .map(tenantUser -> new TenantUserResponse(tenantUser.userId(), tenantUser.email(), tenantUser.name()))
                .toList();
        TenantResponse tenantResponse = new TenantResponse(tenant.tenantId(), tenant.name(), tenant.status(), tenant.tier(), tenantUserResponse);
        return new ResponseEntity<>(tenantResponse, HttpStatus.OK);
    }
}
