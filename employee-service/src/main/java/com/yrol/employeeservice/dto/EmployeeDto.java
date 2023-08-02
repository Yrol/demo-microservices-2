package com.yrol.employeeservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "Employee Model information")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

    private Long id;

    @Schema(description = "Employee first name - mandatory field")
    @NotEmpty(message = "First name cannot be empty")
    private String firstName;

    @Schema(description = "Employee last name - mandatory field")
    @NotEmpty(message = "Last name cannot be empty")
    private String lastName;

    @Schema(description = "Employee email - mandatory field")
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Must provide a valid email")
    private String email;

    @Schema(description = "Employee department - Optional")
    private String departmentCode;

    @Schema(description = "Employee organization code - Optional")
    private String organizationCode;
}
