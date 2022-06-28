package io.github.bluething.playground.multitenancy.imperativeschema.domain.department;

import io.github.bluething.playground.multitenancy.imperativeschema.domain.Department;

import java.util.List;

public interface DepartmentRestPort {
    List<Department> getDepartments();
}
