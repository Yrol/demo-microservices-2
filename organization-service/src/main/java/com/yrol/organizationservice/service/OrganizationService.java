package com.yrol.organizationservice.service;

import com.yrol.organizationservice.dto.OrganizationDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface OrganizationService {
    OrganizationDto saveOrganization(OrganizationDto organizationDto);
    List<OrganizationDto> getAllOrganizations();
    OrganizationDto getOrganizationDtoById(Long Id);
    OrganizationDto getOrganizationByCode(String Code);
    OrganizationDto updateOrganization(OrganizationDto organizationDto);
    void deleteOrganization(Long id);
}
