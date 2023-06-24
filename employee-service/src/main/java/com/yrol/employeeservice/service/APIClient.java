package com.yrol.employeeservice.service;

import com.yrol.employeeservice.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * API client for making call using Feign Client lib
 * **/

//@FeignClient(url = "http://localhost:8080", value = "DEPARTMENT-SERVICE") // without load balancer
@FeignClient(name = "DEPARTMENT-SERVICE") // With load balancer: will call the available Department Service instance (use case: multiple instances)
public interface APIClient {

    // Getting the department by code rest API call
    @GetMapping("api/departments/code/{department-code}")
    DepartmentDto getDepartmentByCode(@PathVariable("department-code") String departmentCode);

}
