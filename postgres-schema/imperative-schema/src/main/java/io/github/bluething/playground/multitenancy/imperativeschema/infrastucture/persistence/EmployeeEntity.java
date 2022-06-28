package io.github.bluething.playground.multitenancy.imperativeschema.infrastucture.persistence;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "employee")
@NoArgsConstructor
@Getter
@Setter
public class EmployeeEntity {
    @Id
    @Column(name = "employee_id")
    private String employeeId;
    @Column(name = "name")
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private DepartmentEntity department;

    public EmployeeEntity(String employeeId, String name, DepartmentEntity department) {
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeEntity that = (EmployeeEntity) o;
        return Objects.equals(employeeId, that.employeeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId);
    }
}
