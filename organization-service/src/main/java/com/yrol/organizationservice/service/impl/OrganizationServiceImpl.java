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
import java.util.stream.Collectors;

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
        List<Organization> organizations = organizationRepository.findAll();
        return organizations.stream().map((organization) -> AutoOrganizationMapper.MAPPER.mapOrganizationToDto(organization))
                .collect(Collectors.toList());
    }

    @Override
    public OrganizationDto getOrganizationById(Long id) {
        Organization organization = organizationRepository.findById(id).orElseThrow(

        );
        return AutoOrganizationMapper.MAPPER.mapOrganizationToDto(organization);
    }

    @Override
    public OrganizationDto getOrganizationByCode(String code) {
        Organization organization = organizationRepository.findByOrganizationCode(code).orElseThrow(
        );
        return AutoOrganizationMapper.MAPPER.mapOrganizationToDto(organization);
    }

    @Override
    public OrganizationDto updateOrganization(OrganizationDto organizationDto) {
        Organization organization = organizationRepository.findById(organizationDto.getId()).orElseThrow(
        );

        organization.setOrganizationCode(organizationDto.getOrganizationCode());
        organization.setOrganizationDescription(organizationDto.getOrganizationDescription());
        organization.setOrganizationName(organizationDto.getOrganizationName());

        return AutoOrganizationMapper.MAPPER.mapOrganizationToDto(organization);
    }

    @Override
    public void deleteOrganization(Long id) {
        organizationRepository.deleteById(id);
    }
}
