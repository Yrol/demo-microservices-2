package com.yrol.departmentservice.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {
    private Long id;

    @NotEmpty(message = "Department name must be provided")
    private String departmentName;

    private String departmentDescription;

    @NotEmpty(message = "Department code must not be empty")
    private String departmentCode;
}
