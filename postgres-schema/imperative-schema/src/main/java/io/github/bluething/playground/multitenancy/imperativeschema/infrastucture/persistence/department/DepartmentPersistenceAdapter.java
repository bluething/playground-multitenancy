package io.github.bluething.playground.multitenancy.imperativeschema.infrastucture.persistence.department;

import io.github.bluething.playground.multitenancy.imperativeschema.domain.Department;
import io.github.bluething.playground.multitenancy.imperativeschema.domain.Employee;
import io.github.bluething.playground.multitenancy.imperativeschema.domain.department.DepartmentPersistencePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DepartmentPersistenceAdapter implements DepartmentPersistencePort {
    private DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentPersistenceAdapter(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<Department> getDepartments() {
        Iterable<DepartmentEntity> departmentEntities = departmentRepository.findAll();
        List<Department> departments = new ArrayList<>();
        List<Employee> employees = null;
        for (DepartmentEntity departmentEntity : departmentEntities) {
            employees = new ArrayList<>();
            for (EmployeeEntity employeeEntity : departmentEntity.getEmployees()) {
                employees.add(new Employee(employeeEntity.getEmployeeId(), employeeEntity.getName()));
            }
            departments.add(new Department(departmentEntity.getDepartmentId(), departmentEntity.getName(), employees));
        }
        return departments;
    }
}
