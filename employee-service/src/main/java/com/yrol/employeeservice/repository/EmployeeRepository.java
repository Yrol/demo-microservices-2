package com.yrol.employeeservice.repository;

import com.yrol.employeeservice.dto.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
