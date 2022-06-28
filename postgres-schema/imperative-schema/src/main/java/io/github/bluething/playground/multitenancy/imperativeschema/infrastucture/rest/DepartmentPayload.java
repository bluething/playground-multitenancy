package io.github.bluething.playground.multitenancy.imperativeschema.infrastucture.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record DepartmentPayload(@JsonProperty("departmentId") String departmentId,
                                @JsonProperty("name") String name,
                                @JsonProperty("employees") List<EmployeePayload> employees) {
}
