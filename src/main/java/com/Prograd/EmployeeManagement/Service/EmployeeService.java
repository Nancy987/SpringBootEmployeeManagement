package com.Prograd.EmployeeManagement.Service;

import com.Prograd.EmployeeManagement.Exceptions.EmployeeNotFound;
import com.Prograd.EmployeeManagement.Exceptions.NoAccessException;
import com.Prograd.EmployeeManagement.Exceptions.OrganisationNotFound;
import com.Prograd.EmployeeManagement.Modals.Employee;

import java.util.List;

public interface EmployeeService {
    Employee saveEmployee(Employee employee) throws OrganisationNotFound;
    List<Employee> getAllEmployees();
    Employee getEmployeeById(int id) throws EmployeeNotFound, NoAccessException;
    Employee updateEmployee(Employee Employee,int id) throws EmployeeNotFound, OrganisationNotFound, NoAccessException;
    void deleteEmployee(int id);
}
