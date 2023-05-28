package com.yrol.departmentservice.service;

import com.yrol.departmentservice.dto.DepartmentDto;

import java.util.List;

public interface DepartmentService {
    DepartmentDto saveDepartment(DepartmentDto departmentDto);

    List<DepartmentDto> getAllDepartments();

    DepartmentDto getDepartmentById(Long id);

    DepartmentDto getDepartmentByCode(String code);

    DepartmentDto updateDepartment(DepartmentDto departmentDto);

    void deleteDepartment(Long id);

}
