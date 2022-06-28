package io.github.bluething.playground.multitenancy.imperativeschema.domain;

import java.util.List;

public record Department(String departmentId,
                         String name,
                         List<Employee> employees) {
}
