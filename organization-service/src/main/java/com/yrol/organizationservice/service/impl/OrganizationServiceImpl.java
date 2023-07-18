package com.yrol.organizationservice.service.impl;

import com.yrol.organizationservice.dto.OrganizationDto;
import com.yrol.organizationservice.entity.Organization;
import com.yrol.organizationservice.mapper.AutoOrganizationMapper;
import com.yrol.organizationservice.repository.OrganizationRepository;
import com.yrol.organizationservice.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private OrganizationRepository organizationRepository;

    @Override
    public OrganizationDto saveOrganization(OrganizationDto organizationDto) {
        Optional<Organization> organizationWithCode = organizationRepository.findByOrganizationCode(organizationDto.getOrganizationCode());

        if(organizationWithCode.isPresent()) {
//            throw new DepartmentAlreadyExistsException(organizationDto.getOrganizationCode());
        }

        //convert organization DTO to JPA entity and vice versa
        Organization organization = AutoOrganizationMapper.MAPPER.mapToOrganization(organizationDto);
        Organization saveOrganization = organizationRepository.save(organization);
        return AutoOrganizationMapper.MAPPER.mapOrganizationToDto(saveOrganization);
    }

    @Override
    public List<OrganizationDto> getAllOrganizations() {
        return null;
    }

    @Override
    public OrganizationDto getOrganizationDtoById(Long Id) {
        return null;
    }

    @Override
    public OrganizationDto getOrganizationByCode(String Code) {
        return null;
    }

    @Override
    public OrganizationDto updateOrganization(OrganizationDto organizationDto) {
        return null;
    }

    @Override
    public void deleteOrganization(Long id) {

    }
}
