package com.yrol.organizationservice.repository;

import com.yrol.organizationservice.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    Optional<Organization> findByOrganizationCode(String code);
    Optional<Organization> findByOrganizationName(String name);
}
