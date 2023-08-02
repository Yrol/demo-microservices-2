package com.yrol.departmentservice.controller;

import com.yrol.departmentservice.dto.DepartmentDto;
import com.yrol.departmentservice.service.DepartmentService;
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
@Tag(name = "Department Service", description = "Controller that exposes the Department Service")

@RestController
@AllArgsConstructor
@RequestMapping("api/departments")
public class DepartmentController {

    private DepartmentService departmentService;

    /** Using Operation and ApiResponse for swagger **/
    @Operation(
            summary = "Save department REST API",
            description = "Saving departments into the DB"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP status code created 201"
    )
    @PostMapping
    public ResponseEntity<DepartmentDto> createDepartment(@Valid @RequestBody DepartmentDto departmentDto) {
        DepartmentDto savedDepartment = departmentService.saveDepartment(departmentDto);
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Getting all departments",
            description = "REST API for getting all the departments"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status code created 201"
    )
    @GetMapping
    public  ResponseEntity<List<DepartmentDto>> getAllUsers() {
        List<DepartmentDto> departments = departmentService.getAllDepartments();
        return ResponseEntity.ok(departments);
    }

    @Operation(
            summary = "Getting a department by ID",
            description = "REST API for getting a specific department"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status code created 201"
    )
    @GetMapping("{id}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable("id") Long departmentId) {
        DepartmentDto departmentDto = departmentService.getDepartmentById(departmentId);
        return ResponseEntity.ok(departmentDto);
    }

    @Operation(
            summary = "Getting a department by code",
            description = "REST API for getting a specific department"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status code created 201"
    )
    @GetMapping("/code/{department-code}")
    public ResponseEntity<DepartmentDto> getDepartmentByCode(@PathVariable("department-code") String departmentCode) {
        DepartmentDto departmentDto = departmentService.getDepartmentByCode(departmentCode);
        return ResponseEntity.ok(departmentDto);
    }

    @Operation(
            summary = "Updating a department by ID",
            description = "REST API for updating a department by ID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status code created 201"
    )
    @PutMapping("{id}")
    public ResponseEntity<DepartmentDto> updateDepartment(@Valid @RequestBody DepartmentDto departmentDto, @PathVariable("id") Long departmentId) {
        departmentDto.setId(departmentId);
        DepartmentDto updatedDepartment = departmentService.updateDepartment(departmentDto);
        return ResponseEntity.ok(updatedDepartment);
    }

    @Operation(
            summary = "Deleting a department by ID",
            description = "REST API for deleting a department by ID"
    )
    @ApiResponse(
            responseCode = "204",
            description = "HTTP status code created 201"
    )
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable("id") Long departmentId) {
        departmentService.deleteDepartment(departmentId);
        return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
    }
}
