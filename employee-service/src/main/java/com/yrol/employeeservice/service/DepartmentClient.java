package com.yrol.employeeservice.service;

import com.yrol.employeeservice.dto.DepartmentDto;
import feign.Headers;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * API client for making call using Feign Client lib
 * **/

//@FeignClient(url = "http://localhost:8080", value = "DEPARTMENT-SERVICE") // without load balancer
@FeignClient(name = "DEPARTMENT-SERVICE") // With load balancer: will call the available Department Service instance (use case: multiple instances)
public interface DepartmentClient {

    // Getting the department by code rest API call
    @Headers("Content-Type: application/json")
    @GetMapping("api/departments/code/{department-code}")
    @CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    DepartmentDto getDepartmentByCode(@PathVariable("department-code") String departmentCode);

    default DepartmentDto getDefaultDepartment(String id, Throwable ex) {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentName("");
        departmentDto.setDepartmentCode("");
        departmentDto.setDepartmentDescription("");
        return departmentDto;
    }
}
