package com.yrol.employeeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO for holding Department DTO
 * Used for auto mapping when calling Department services
 * **/

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {
    private Long id;
    private String departmentName;
    private String departmentDescription;
    private String departmentCode;
}
