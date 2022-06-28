package io.github.bluething.playground.multitenancy.imperativeschema.infrastucture.persistence;

import io.github.bluething.playground.multitenancy.imperativeschema.domain.Department;
import io.github.bluething.playground.multitenancy.imperativeschema.domain.department.DepartmentPersistencePort;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentPersistenceAdapter implements DepartmentPersistencePort {
    @Override
    public List<Department> getDepartments() {
        return null;
    }
}
