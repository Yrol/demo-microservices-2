package com.yrol.organizationservice.mapper;

import com.yrol.organizationservice.dto.OrganizationDto;
import com.yrol.organizationservice.entity.Organization;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Interface for MapStruct.
 * No implementation is required for this as MapStruct will create the implementation for this interface at compilation time.
 * The entities must have the same name in both DTO and JPA. Otherwise, need to map manually, ex: @Mapping(source = "email", target = "emailAddress")
 * **/

@Mapper
public interface AutoOrganizationMapper {
    AutoOrganizationMapper MAPPER = Mappers.getMapper(AutoOrganizationMapper.class); // provide implementation at compilation time
    OrganizationDto mapOrganizationToDto(Organization department);
    Organization mapToOrganization(OrganizationDto departmentDto);
}
