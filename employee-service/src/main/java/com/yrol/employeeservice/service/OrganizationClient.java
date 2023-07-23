package com.yrol.employeeservice.service;

import com.yrol.employeeservice.dto.OrganizationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * API client for making call using Feign Client lib
 * **/

//@FeignClient(url = "http://localhost:8083", value = "ORGANIZATION-SERVICE") // without load balancer
@FeignClient(name = "ORGANIZATION-SERVICE") // With load balancer: will call the available Organization Service instance (use case: multiple instances)
public interface OrganizationClient {
    // Getting the organization by code rest API call
    @GetMapping("api/organization/code/{organization-code}")
    OrganizationDto getOrganizationByCode(@PathVariable("organization-code") String organizationCode);
}
