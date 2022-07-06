package com.Prograd.EmployeeManagement.Repository;

import com.Prograd.EmployeeManagement.Modals.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganisationRepository extends JpaRepository<Organisation,Integer> {
    Optional<Organisation> findByUsername(String username);
}
