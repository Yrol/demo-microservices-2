package com.yrol.employeeservice.service;

import com.yrol.employeeservice.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    public EmployeeDto saveEmployee(EmployeeDto employeeDto);

    public EmployeeDto getEmployeeById(Long employeeId);

    public List<EmployeeDto> getAllEmployees();

    public EmployeeDto updateEmployee(EmployeeDto employeeDto);

    public void deleteEmployee(Long employeeId);
}
