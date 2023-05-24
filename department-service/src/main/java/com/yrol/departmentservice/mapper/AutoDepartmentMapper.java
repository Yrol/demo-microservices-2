package com.yrol.departmentservice.mapper;


import com.yrol.departmentservice.dto.DepartmentDto;
import com.yrol.departmentservice.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Interface for MapStruct.
 * No implementation is required for this as MapStruct will create the implementation for this interface at compilation time.
 * The entities must have the same name in both DTO and JPA. Otherwise, need to map manually, ex: @Mapping(source = "email", target = "emailAddress")
 * **/

@Mapper
public interface AutoDepartmentMapper {

    AutoDepartmentMapper MAPPER = Mappers.getMapper(AutoDepartmentMapper.class); // provide implementation at compilation time

    DepartmentDto mapDepartmentToDto(Department department);

    Department mapToDepartment(DepartmentDto departmentDto);

}
