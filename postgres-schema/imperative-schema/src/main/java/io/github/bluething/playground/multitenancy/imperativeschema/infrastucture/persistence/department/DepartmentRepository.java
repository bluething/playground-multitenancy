package io.github.bluething.playground.multitenancy.imperativeschema.infrastucture.persistence.department;

import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends CrudRepository<DepartmentEntity, String> {
}
