package io.github.bluething.playground.multitenancy.imperativeschema.infrastucture.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

public record EmployeePayload(@JsonProperty("employeeId") String employeeId,
                              @JsonProperty("name") String name) {
}
