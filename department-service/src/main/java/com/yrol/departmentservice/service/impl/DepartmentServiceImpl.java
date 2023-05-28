package com.yrol.departmentservice.service.impl;

import com.yrol.departmentservice.dto.DepartmentDto;
import com.yrol.departmentservice.entity.Department;
import com.yrol.departmentservice.exception.DepartmentAlreadyExistsException;
import com.yrol.departmentservice.exception.ResourceNotFoundException;
import com.yrol.departmentservice.mapper.AutoDepartmentMapper;
import com.yrol.departmentservice.repository.DepartmentRepository;
import com.yrol.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {

        Optional<Department> departmentWithName = departmentRepository.findByDepartmentName(departmentDto.getDepartmentName());

        if(departmentWithName.isPresent()) {
            throw new DepartmentAlreadyExistsException(departmentDto.getDepartmentName());
        }

        //convert department DTO to JPA entity and vice versa
        Department department = AutoDepartmentMapper.MAPPER.mapToDepartment(departmentDto);
        Department savedDepartment = departmentRepository.save(department);
        return AutoDepartmentMapper.MAPPER.mapDepartmentToDto(savedDepartment);
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream().map((department) -> AutoDepartmentMapper.MAPPER.mapDepartmentToDto(department))
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentDto getDepartmentById(Long id) {
        Department department = departmentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Department", "id", id.toString())
        );
        return AutoDepartmentMapper.MAPPER.mapDepartmentToDto(department);
    }

    @Override
    public DepartmentDto getDepartmentByCode(String code) {
        Department department = departmentRepository.findByDepartmentCode(code).orElseThrow(
                () -> new ResourceNotFoundException("Department", "code", code)
        );

        return AutoDepartmentMapper.MAPPER.mapDepartmentToDto(department);
    }

    @Override
    public DepartmentDto updateDepartment(DepartmentDto departmentDto) {
        Department department = departmentRepository.findById(departmentDto.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Department", "id", departmentDto.getId().toString())
        );

        department.setDepartmentName(departmentDto.getDepartmentName());
        department.setDepartmentDescription(departmentDto.getDepartmentDescription());
        department.setDepartmentCode(departmentDto.getDepartmentCode());
        return AutoDepartmentMapper.MAPPER.mapDepartmentToDto(departmentRepository.save(department));
    }

    @Override
    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }
}
