package com.yrol.departmentservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "Department Model information")

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {
    private Long id;

    @Schema(description = "Department name - mandatory field")
    @NotEmpty(message = "Department name must be provided")
    private String departmentName;

    @Schema(description = "Department description - optional")
    private String departmentDescription;

    @Schema(description = "Department code - mandatory field")
    @NotEmpty(message = "Department code must not be empty")
    private String departmentCode;
}
