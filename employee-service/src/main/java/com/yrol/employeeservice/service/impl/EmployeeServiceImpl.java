package com.yrol.employeeservice.service.impl;

import com.yrol.employeeservice.dto.APIResponseDto;
import com.yrol.employeeservice.dto.DepartmentDto;
import com.yrol.employeeservice.dto.EmployeeDto;
import com.yrol.employeeservice.dto.OrganizationDto;
import com.yrol.employeeservice.entity.Employee;
import com.yrol.employeeservice.exception.EmployeeAlreadyExistException;
import com.yrol.employeeservice.exception.ResourceNotFoundException;
import com.yrol.employeeservice.mapper.AutoEmployeeMapper;
import com.yrol.employeeservice.repository.EmployeeRepository;
import com.yrol.employeeservice.service.DepartmentClient;
import com.yrol.employeeservice.service.EmployeeService;
import com.yrol.employeeservice.service.OrganizationClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

//    private RestTemplate restTemplate;

    private WebClient webClient;

    private DepartmentClient departmentClient;
    private OrganizationClient organizationClient;

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
        DepartmentDto departmentDto = departmentClient.getDepartmentByCode(employee.getDepartmentCode());
        OrganizationDto organizationDto = organizationClient.getOrganizationByCode(employee.getOrganizationCode());

        EmployeeDto employeeDto = AutoEmployeeMapper.MAPPER.mapEmployeeToDto(employee);

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);
        apiResponseDto.setOrganization(organizationDto);

        return apiResponseDto;
    }

    private DepartmentDto getDepartment(String departmentCode) {
        return departmentClient.getDepartmentByCode(departmentCode);
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
            DepartmentDto departmentDto = departmentClient.getDepartmentByCode(e.getDepartmentCode());
            OrganizationDto organizationDto = organizationClient.getOrganizationByCode(e.getOrganizationCode());
            responseDtos.add(new APIResponseDto(AutoEmployeeMapper.MAPPER.mapEmployeeToDto(e), departmentDto, organizationDto));
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
        existingUser.setOrganizationCode(employeeDto.getOrganizationCode());

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
        departmentDto.setDepartmentName("Test");
        departmentDto.setDepartmentCode("Test");
        departmentDto.setDepartmentDescription("Test");

        OrganizationDto organizationDto = new OrganizationDto();
        organizationDto.setOrganizationName("Test");
        organizationDto.setOrganizationCode("Test");
        organizationDto.setOrganizationDescription("Test");


        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);
        apiResponseDto.setOrganization(organizationDto);

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

        OrganizationDto organizationDto = new OrganizationDto();
        organizationDto.setOrganizationName("");
        organizationDto.setOrganizationCode("");
        organizationDto.setOrganizationDescription("");

        for(Employee e : employees) {
            responseDtos.add(new APIResponseDto(AutoEmployeeMapper.MAPPER.mapEmployeeToDto(e), departmentDto, organizationDto));
        }
        return responseDtos;
    }
}
