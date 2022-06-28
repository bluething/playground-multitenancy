package io.github.bluething.playground.multitenancy.imperativeschema.domain.department;

import io.github.bluething.playground.multitenancy.imperativeschema.domain.Department;

import java.util.List;

public interface DepartmentPersistencePort {
    List<Department> getDepartments();
}
