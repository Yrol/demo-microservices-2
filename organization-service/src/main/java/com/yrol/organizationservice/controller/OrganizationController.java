package com.yrol.organizationservice.controller;

import com.yrol.organizationservice.dto.OrganizationDto;
import com.yrol.organizationservice.service.OrganizationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/organization")

public class OrganizationController {

    private OrganizationService organizationService;

    @PostMapping
    public ResponseEntity<OrganizationDto> createDepartment(@Valid @RequestBody OrganizationDto organizationDto) {
        OrganizationDto saveOrganization = organizationService.saveOrganization(organizationDto);
        return new ResponseEntity<>(saveOrganization, HttpStatus.CREATED);
    }
}
