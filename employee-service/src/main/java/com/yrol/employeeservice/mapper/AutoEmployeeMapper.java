package com.yrol.employeeservice.mapper;

import com.yrol.employeeservice.dto.EmployeeDto;
import com.yrol.employeeservice.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Interface for MapStruct.
 * No implementation is required for this as MapStruct will create the implementation for this interface at compilation time.
 * The entities must have the same name in both DTO and JPA. Otherwise, need to map manually, ex: @Mapping(source = "email", target = "emailAddress")
 * **/

@Mapper
public interface AutoEmployeeMapper {


    AutoEmployeeMapper MAPPER = Mappers.getMapper(AutoEmployeeMapper.class); // provide implementation at compilation time

    EmployeeDto mapEmployeeToDto(Employee employee);

    Employee mapToEmployee(EmployeeDto employeeDto);
}
