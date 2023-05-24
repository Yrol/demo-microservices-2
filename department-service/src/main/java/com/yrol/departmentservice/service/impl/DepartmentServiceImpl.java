package com.yrol.departmentservice.service.impl;

import com.yrol.departmentservice.dto.DepartmentDto;
import com.yrol.departmentservice.entity.Department;
import com.yrol.departmentservice.mapper.AutoDepartmentMapper;
import com.yrol.departmentservice.repository.DepartmentRepository;
import com.yrol.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        //convert department DTO to JPA entity

        Optional<Department> departmentWithName = departmentRepository.findByDepartmentName(departmentDto.getDepartmentName());

        Department department = AutoDepartmentMapper.MAPPER.mapToDepartment(departmentDto);
        Department savedDepartment = departmentRepository.save(department);
        return AutoDepartmentMapper.MAPPER.mapDepartmentToDto(savedDepartment);
    }
}
