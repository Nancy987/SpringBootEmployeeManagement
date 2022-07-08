package com.Prograd.EmployeeManagement.Repository;

import com.Prograd.EmployeeManagement.Modals.Employee;
import com.Prograd.EmployeeManagement.Modals.Organisation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    Optional<Employee> findByUsername(String username);
    @Transactional
    @Modifying
    @Query("delete FROM Employee u WHERE u.organisation =:orgId")
    public void deleteAllEmpByOrgId(@Param("orgId") Organisation orgId);

    @Query("select u FROM Employee u WHERE u.organisation =:orgId")
    public List<Employee> findByOrganisation(@Param("orgId") Organisation orgId);

    @Query("select u FROM Employee u WHERE u.id=:empId and u.organisation =:orgId")
    public Employee findOneEmpByOrganisation(@Param("empId") int empId,@Param("orgId") Organisation orgId);
}
