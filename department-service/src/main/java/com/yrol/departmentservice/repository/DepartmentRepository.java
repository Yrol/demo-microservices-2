package com.yrol.departmentservice.repository;

import com.yrol.departmentservice.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Optional<Department> findByDepartmentName(String name);

    Optional<Department> findByDepartmentCode(String code);
}
