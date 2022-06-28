package io.github.bluething.playground.multitenancy.imperativeschema.infrastucture.persistence.department;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "department")
@NoArgsConstructor
@Getter
public class DepartmentEntity {
    @Id
    @Column(name = "department_id")
    private String departmentId;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "department", cascade = CascadeType.REFRESH, orphanRemoval = true)
    private List<EmployeeEntity> employees = new ArrayList<>();

    public DepartmentEntity(String departmentId, String name, List<EmployeeEntity> employees) {
        this.departmentId = departmentId;
        this.name = name;
        this.employees = employees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartmentEntity that = (DepartmentEntity) o;
        return Objects.equals(departmentId, that.departmentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departmentId);
    }

    public void addEmployee(EmployeeEntity employee) {
        getEmployees().add(employee);
        employee.setDepartment(this);
    }

    public void removeEmployee(EmployeeEntity employee) {
        getEmployees().remove(employee);
        employee.setDepartment(null);
    }
}
