package io.github.bluething.playground.multitenancy.imperativeschema.domain.department;

import io.github.bluething.playground.multitenancy.imperativeschema.domain.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class DepartmentRestAdapter implements DepartmentRestPort {
    private final DepartmentPersistencePort departmentPersistencePort;

    @Autowired
    public DepartmentRestAdapter(DepartmentPersistencePort departmentPersistencePort) {
        this.departmentPersistencePort = departmentPersistencePort;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Department> getDepartments() {
        return departmentPersistencePort.getDepartments();
    }
}
