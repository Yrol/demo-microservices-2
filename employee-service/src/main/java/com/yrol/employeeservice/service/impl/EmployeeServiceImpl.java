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
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

//    private RestTemplate restTemplate;

    private WebClient webClient;

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

    /**
     * Using CircuitBreaker for resilience (properties are defined in application-dev.properties)
     * **/
    @Override
    @CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultEmployeeById")
    public APIResponseDto getEmployeeById(Long employeeId){
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "id", employeeId.toString())
        );

        // Getting the department of the employee by using RestTemplate
//        ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/departments/code/" + employee.getDepartmentCode(), DepartmentDto.class);
//        DepartmentDto departmentDto = responseEntity.getBody();

        // WebClient - Getting the department of the employee (using block() for synchronous class)
//        DepartmentDto departmentDto = webClient.get()
//                .uri(("http://localhost:8080/api/departments/code/" + employee.getDepartmentCode()))
//                .retrieve().bodyToMono(DepartmentDto.class)
//                .block();

        // Feign Client - Getting the department of the employee.
        DepartmentDto departmentDto = apiClient.getDepartmentByCode(employee.getDepartmentCode());

        EmployeeDto employeeDto = AutoEmployeeMapper.MAPPER.mapEmployeeToDto(employee);

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);

        return apiResponseDto;
    }

    /**
     * Using Retry for resilience (properties are defined in application-dev.properties)
     * **/
    @Override
    @Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultAllEmployees")
    public List<APIResponseDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();

        List<APIResponseDto> responseDtos = new ArrayList();

        for(Employee e : employees) {
            DepartmentDto departmentDto = apiClient.getDepartmentByCode(e.getDepartmentCode());
            responseDtos.add(new APIResponseDto(AutoEmployeeMapper.MAPPER.mapEmployeeToDto(e), departmentDto));
        }

//        return employees.stream().map((employee) -> AutoEmployeeMapper.MAPPER.mapEmployeeToDto(employee))
//                .collect(Collectors.toList());

        return responseDtos;
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto) {
        Employee existingUser = employeeRepository.findById(employeeDto.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "id", employeeDto.getId().toString())
        );
        existingUser.setEmail(employeeDto.getEmail());
        existingUser.setFirstName(employeeDto.getFirstName());
        existingUser.setLastName(employeeDto.getLastName());

        Employee updatedEmployee = employeeRepository.save(existingUser);

        return AutoEmployeeMapper.MAPPER.mapEmployeeToDto(updatedEmployee);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if(employee.isPresent()) {
            employeeRepository.deleteById(employeeId);
        }
    }

    /**
     * Fallback method for getEmployeeById when department internal call fails
     * **/
    public APIResponseDto getDefaultEmployeeById(Long employeeId, Throwable throwable){
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "id", employeeId.toString())
        );

        EmployeeDto employeeDto = AutoEmployeeMapper.MAPPER.mapEmployeeToDto(employee);

        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentName("");
        departmentDto.setDepartmentCode("");
        departmentDto.setDepartmentDescription("");

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);

        return apiResponseDto;
    }


    /**
     * Fallback method for getAllEmployees when department internal call fails
     * **/
    public List<APIResponseDto> getDefaultAllEmployees(Throwable throwable) {
        List<Employee> employees = employeeRepository.findAll();

        List<APIResponseDto> responseDtos = new ArrayList();

        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentName("");
        departmentDto.setDepartmentCode("");
        departmentDto.setDepartmentDescription("");

        for(Employee e : employees) {
            responseDtos.add(new APIResponseDto(AutoEmployeeMapper.MAPPER.mapEmployeeToDto(e), departmentDto));
        }
        return responseDtos;
    }
}
