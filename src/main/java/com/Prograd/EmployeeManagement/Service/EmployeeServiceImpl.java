package com.Prograd.EmployeeManagement.Service;

import com.Prograd.EmployeeManagement.Exceptions.EmployeeNotFound;
import com.Prograd.EmployeeManagement.Exceptions.NoAccessException;
import com.Prograd.EmployeeManagement.Exceptions.OrganisationNotFound;
import com.Prograd.EmployeeManagement.Modals.Employee;
import com.Prograd.EmployeeManagement.Modals.Organisation;
import com.Prograd.EmployeeManagement.Repository.EmployeeRepository;

import com.Prograd.EmployeeManagement.Repository.OrganisationRepository;
import com.Prograd.EmployeeManagement.Security.EmployeeSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    @Autowired
    private OrganisationRepository organisationRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) throws OrganisationNotFound {
        String encodedPassword = passwordEncoder.encode(employee.getPassword());
        employee.setPassword(encodedPassword);
        Organisation organisation = organisationRepository.findById(10).orElseThrow(()->new OrganisationNotFound("Organisation not exist"));
        employee.setOrganisation(organisation);
        return employeeRepository.save(employee);
    }
    @Autowired
    private EmployeeSecurity employeeSecurity;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(int id) throws EmployeeNotFound, NoAccessException {
        Employee employee = employeeRepository.findById(id).orElseThrow(()->new EmployeeNotFound("Employee not exist"));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        boolean hasEmpRole = authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_EMPLOYEE"));

        if(hasEmpRole) {
            boolean res = employeeSecurity.hasEmpId(id);
            if (res) {
                return employee;
            } else {
                throw new NoAccessException("You have no access for this request");
            }
        }else{
            return employee;
        }
    }

    @Override
    public Employee updateEmployee(Employee employee, int id) throws EmployeeNotFound, OrganisationNotFound, NoAccessException {
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(()->new EmployeeNotFound(("Employee not exist")));

        existingEmployee.setEmployee_name(employee.getEmployee_name());
        existingEmployee.setEmail_id(employee.getEmail_id());
        existingEmployee.setAddress(employee.getAddress());
        String encodedPassword = passwordEncoder.encode(employee.getPassword());
        existingEmployee.setPassword(encodedPassword);
        existingEmployee.setUsername(employee.getUsername());
        existingEmployee.setPhone_no(employee.getPhone_no());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        boolean hasEmpRole = authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_EMPLOYEE"));

        if(hasEmpRole) {
            boolean res = employeeSecurity.hasEmpId(id);
            if (res) {
                return employeeRepository.save(existingEmployee);
            } else {
                throw new NoAccessException("You have no access for this request");
            }
        }else{
            return employeeRepository.save(existingEmployee);
        }
    }

    @Override
    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }
}
