package com.yrol.employeeservice.service.impl;

import com.yrol.employeeservice.dto.EmployeeDto;
import com.yrol.employeeservice.entity.Employee;
import com.yrol.employeeservice.mapper.AutoEmployeeMapper;
import com.yrol.employeeservice.repository.EmployeeRepository;
import com.yrol.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public EmployeeDto getEmployeeById(Long employeeId){
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        return AutoEmployeeMapper.MAPPER.mapEmployeeToDto(employee.get());
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();

        return employees.stream().map((employee) -> AutoEmployeeMapper.MAPPER.mapEmployeeToDto(employee))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto) {
        Employee existingUser = employeeRepository.findById(employeeDto.getId()).get();
        existingUser.setEmail(employeeDto.getEmail());
        existingUser.setFirstName(employeeDto.getFirstName());
        existingUser.setLastName(employeeDto.getLastName());
        return AutoEmployeeMapper.MAPPER.mapEmployeeToDto(existingUser);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if(employee.isPresent()) {
            employeeRepository.deleteById(employeeId);
        }
    }
}
