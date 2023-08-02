package com.yrol.organizationservice.controller;

import com.yrol.organizationservice.dto.OrganizationDto;
import com.yrol.organizationservice.service.OrganizationService;
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
@Tag(name = "Organization Service", description = "Controller that exposes the organization Service")

@RestController
@AllArgsConstructor
@RequestMapping("api/organization")

public class OrganizationController {

    private OrganizationService organizationService;


    /** Using Operation and ApiResponse for swagger **/
    @Operation(
            summary = "Save organization REST API",
            description = "Saving organizations into the DB"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP status code created 201"
    )
    @PostMapping
    public ResponseEntity<OrganizationDto> createOrganization(@Valid @RequestBody OrganizationDto organizationDto) {
        OrganizationDto saveOrganization = organizationService.saveOrganization(organizationDto);
        return new ResponseEntity<>(saveOrganization, HttpStatus.CREATED);
    }


    @Operation(
            summary = "Getting all organizations",
            description = "REST API for getting all the organizations"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status code created 201"
    )
    @GetMapping
    public ResponseEntity<List<OrganizationDto>> getAllOrganization() {
        List<OrganizationDto> organizations = organizationService.getAllOrganizations();
        return ResponseEntity.ok(organizations);
    }

    @Operation(
            summary = "Getting an organization by ID",
            description = "REST API for getting a specific organization"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status code created 201"
    )
    @GetMapping("{id}")
    public ResponseEntity<OrganizationDto> getOrganizationById(@PathVariable("id") Long organizationId) {
        OrganizationDto organizationDto = organizationService.getOrganizationById(organizationId);
        return ResponseEntity.ok(organizationDto);
    }

    @Operation(
            summary = "Getting an organization by code",
            description = "REST API for getting a specific organization"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status code created 201"
    )
    @GetMapping("/code/{organization-code}")
    public ResponseEntity<OrganizationDto> getOrganizationByCode(@PathVariable("organization-code") String organizationCode) {
        OrganizationDto organizationDto = organizationService.getOrganizationByCode(organizationCode);
        return ResponseEntity.ok(organizationDto);
    }

    @Operation(
            summary = "Updating an organization by ID",
            description = "REST API for updating an organization by ID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status code created 201"
    )
    @PutMapping("{id}")
    public ResponseEntity<OrganizationDto> updateOrganization(@Valid @RequestBody OrganizationDto organizationDto, @PathVariable("id") Long organizationId) {
        organizationDto.setId(organizationId);
        OrganizationDto updatedOrganization = organizationService.updateOrganization(organizationDto);
        return ResponseEntity.ok(updatedOrganization);
    }

    @Operation(
            summary = "Deleting an organization by ID",
            description = "REST API for deleting an organization by ID"
    )
    @ApiResponse(
            responseCode = "204",
            description = "HTTP status code created 201"
    )
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteOrganization(@PathVariable("id") Long organizationId) {
        organizationService.deleteOrganization(organizationId);
        return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
    }
}
