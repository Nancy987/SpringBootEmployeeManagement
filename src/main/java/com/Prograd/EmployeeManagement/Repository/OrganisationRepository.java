package com.Prograd.EmployeeManagement.Repository;

import com.Prograd.EmployeeManagement.Modals.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganisationRepository extends JpaRepository<Organisation,Integer> {
}
