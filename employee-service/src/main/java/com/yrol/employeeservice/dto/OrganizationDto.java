package com.yrol.employeeservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "organization Model which is used for automapping when calling the Organization services")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class OrganizationDto {
    private Long id;
    private String organizationName;
    private String organizationDescription;
    private String organizationCode;
}
