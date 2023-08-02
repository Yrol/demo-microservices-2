package com.yrol.employeeservice.controller;


import com.yrol.employeeservice.dto.APIResponseDto;
import com.yrol.employeeservice.dto.EmployeeDto;
import com.yrol.employeeservice.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** Using Tag for swagger **/
@Tag(name = "Employee Service", description = "Controller that exposes the Employee Service")

@RestController
@AllArgsConstructor
@RequestMapping("api/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    /** Using Operation and ApiResponse for swagger **/
    @Operation(
            summary = "Save employee REST API",
            description = "Saving employees into the DB"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP status code created 201"
    )
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
        EmployeeDto savedEmployee = employeeService.saveEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Getting an employee by ID",
            description = "REST API for getting a specific employee"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status code created 201"
    )
    @GetMapping("{id}")
    public ResponseEntity<APIResponseDto> getEmployeeById(@PathVariable("id") Long employeeId) {
        APIResponseDto employee = employeeService.getEmployeeById(employeeId);
        return ResponseEntity.ok(employee);
    }

    @Operation(
            summary = "Getting all employees",
            description = "REST API for getting all the employees"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status code created 201"
    )
    @GetMapping
    public ResponseEntity<List<APIResponseDto>> getEmployees() {
        List<APIResponseDto> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @Operation(
            summary = "Updating an employee by ID",
            description = "REST API for updating an employee by ID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status code created 201"
    )
    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long employeeId, @Valid @RequestBody EmployeeDto employee) {
        employee.setId(employeeId);
        EmployeeDto updatedUser = employeeService.updateEmployee(employee);
        return ResponseEntity.ok(updatedUser);
    }

    @Operation(
            summary = "Deleting an employee by ID",
            description = "REST API for deleting an employee by ID"
    )
    @ApiResponse(
            responseCode = "204",
            description = "HTTP status code created 201"
    )
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId){
        employeeService.deleteEmployee(employeeId);
        return new ResponseEntity<>("Successfully deleted", HttpStatus.NO_CONTENT);
    }
}
