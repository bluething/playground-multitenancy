package io.github.bluething.playground.multitenancy.imperativeschema.infrastucture.rest;

import io.github.bluething.playground.multitenancy.imperativeschema.domain.Department;
import io.github.bluething.playground.multitenancy.imperativeschema.domain.Employee;
import io.github.bluething.playground.multitenancy.imperativeschema.domain.department.DepartmentRestPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
        List<Department> departments = departmentRestPort.getDepartments();
        List<DepartmentPayload> departmentPayloads = new ArrayList<>();
        List<EmployeePayload> employeePayloads = null;
        for (Department department : departments) {
            employeePayloads = new ArrayList<>();
            for (Employee employee : department.employees()) {
                employeePayloads.add(new EmployeePayload(employee.employeeId(), employee.name()));
            }
            departmentPayloads.add(new DepartmentPayload(department.departmentId(), department.name(), employeePayloads));
        }
        return new ResponseEntity<>(departmentPayloads, HttpStatus.OK);
    }
}
