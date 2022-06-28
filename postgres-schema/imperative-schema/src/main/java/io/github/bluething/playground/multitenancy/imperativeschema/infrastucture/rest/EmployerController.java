package io.github.bluething.playground.multitenancy.imperativeschema.infrastucture.rest;

import io.github.bluething.playground.multitenancy.imperativeschema.domain.department.DepartmentRestPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployerController {
    private final DepartmentRestPort departmentRestPort;

    @Autowired
    public EmployerController(DepartmentRestPort departmentRestPort) {
        this.departmentRestPort = departmentRestPort;
    }

    @GetMapping(path = "/departments")
    public ResponseEntity<List<DepartmentPayload>> getDepartments() {
        return null;
    }
}
