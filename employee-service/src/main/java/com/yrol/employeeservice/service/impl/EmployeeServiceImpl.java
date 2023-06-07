package com.yrol.employeeservice.service.impl;

import com.yrol.employeeservice.dto.APIResponseDto;
import com.yrol.employeeservice.dto.DepartmentDto;
import com.yrol.employeeservice.dto.EmployeeDto;
import com.yrol.employeeservice.entity.Employee;
import com.yrol.employeeservice.exception.EmployeeAlreadyExistException;
import com.yrol.employeeservice.exception.ResourceNotFoundException;
import com.yrol.employeeservice.mapper.AutoEmployeeMapper;
import com.yrol.employeeservice.repository.EmployeeRepository;
import com.yrol.employeeservice.service.APIClient;
import com.yrol.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

//    private RestTemplate restTemplate;

//    private WebClient webClient;

    private APIClient apiClient;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

        Optional<Employee> existingUser = employeeRepository.findByEmail(employeeDto.getEmail());

        if(existingUser.isPresent()) {
            new EmployeeAlreadyExistException(employeeDto.getEmail());
        }

        Employee employee = AutoEmployeeMapper.MAPPER.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return AutoEmployeeMapper.MAPPER.mapEmployeeToDto(savedEmployee);
    }

    @Override
    public APIResponseDto getEmployeeById(Long employeeId){
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "id", employeeId.toString())
        );

        // Getting the department of the employee by using RestTemplate
//        ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/departments/code/" + employee.getDepartmentCode(), DepartmentDto.class);
//        DepartmentDto departmentDto = responseEntity.getBody();

        // Getting the department of the employee by using WebClient (using block() for synchronous class)
//        DepartmentDto departmentDto = webClient.get()
//                .uri(("http://localhost:8080/api/departments/code/" + employee.getDepartmentCode()))
//                .retrieve().bodyToMono(DepartmentDto.class)
//                .block();

        // Getting the department of the employee by using Feign Client
        DepartmentDto departmentDto = apiClient.getDepartmentByCode(employee.getDepartmentCode());

        EmployeeDto employeeDto = AutoEmployeeMapper.MAPPER.mapEmployeeToDto(employee);

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);

        return apiResponseDto;
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();

        return employees.stream().map((employee) -> AutoEmployeeMapper.MAPPER.mapEmployeeToDto(employee))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto) {
        Employee existingUser = employeeRepository.findById(employeeDto.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "id", employeeDto.getId().toString())
        );
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
