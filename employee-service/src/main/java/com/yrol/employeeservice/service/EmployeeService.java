package com.yrol.employeeservice.service;

import com.yrol.employeeservice.dto.APIResponseDto;
import com.yrol.employeeservice.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    public EmployeeDto saveEmployee(EmployeeDto employeeDto);

    public APIResponseDto getEmployeeById(Long employeeId);

    public List<EmployeeDto> getAllEmployees();

    public EmployeeDto updateEmployee(EmployeeDto employeeDto);

    public void deleteEmployee(Long employeeId);
}
