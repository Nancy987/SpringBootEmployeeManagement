package com.Prograd.EmployeeManagement.Repository;

import com.Prograd.EmployeeManagement.Modals.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    Optional<Employee> findByUsername(String username);
}
