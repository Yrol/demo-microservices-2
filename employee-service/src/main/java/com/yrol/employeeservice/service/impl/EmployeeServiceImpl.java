package com.yrol.employeeservice.service.impl;

import com.yrol.employeeservice.dto.EmployeeDto;
import com.yrol.employeeservice.entity.Employee;
import com.yrol.employeeservice.mapper.AutoEmployeeMapper;
import com.yrol.employeeservice.repository.EmployeeRepository;
import com.yrol.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = AutoEmployeeMapper.MAPPER.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return AutoEmployeeMapper.MAPPER.mapEmployeeToDto(savedEmployee);
    }
}
