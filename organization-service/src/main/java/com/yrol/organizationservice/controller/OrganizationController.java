package com.yrol.organizationservice.controller;

import com.yrol.organizationservice.dto.OrganizationDto;
import com.yrol.organizationservice.service.OrganizationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/organization")

public class OrganizationController {

    private OrganizationService organizationService;

    @PostMapping
    public ResponseEntity<OrganizationDto> createOrganization(@Valid @RequestBody OrganizationDto organizationDto) {
        OrganizationDto saveOrganization = organizationService.saveOrganization(organizationDto);
        return new ResponseEntity<>(saveOrganization, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<OrganizationDto>> getAllOrganization() {
        List<OrganizationDto> organizations = organizationService.getAllOrganizations();
        return ResponseEntity.ok(organizations);
    }

    @GetMapping("{id}")
    public ResponseEntity<OrganizationDto> getOrganizationById(@PathVariable("id") Long organizationId) {
        OrganizationDto organizationDto = organizationService.getOrganizationById(organizationId);
        return ResponseEntity.ok(organizationDto);
    }

    @GetMapping("/code/{organization-code}")
    public ResponseEntity<OrganizationDto> getOrganizationByCode(@PathVariable("organization-code") String organizationCode) {
        OrganizationDto organizationDto = organizationService.getOrganizationByCode(organizationCode);
        return ResponseEntity.ok(organizationDto);
    }

    @PutMapping("{id}")
    public ResponseEntity<OrganizationDto> updateOrganization(@Valid @RequestBody OrganizationDto organizationDto, @PathVariable("id") Long organizationId) {
        organizationDto.setId(organizationId);
        OrganizationDto updatedOrganization = organizationService.updateOrganization(organizationDto);
        return ResponseEntity.ok(updatedOrganization);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteOrganization(@PathVariable("id") Long organizationId) {
        organizationService.deleteOrganization(organizationId);
        return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
    }
}
