package com.Prograd.EmployeeManagement.Security;

import com.Prograd.EmployeeManagement.Modals.Employee;
import com.Prograd.EmployeeManagement.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class EmployeeSecurity {

    @Autowired
    EmployeeRepository employeeRepository;

    public boolean hasEmpId(int empId){
        Employee user = (Employee) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int employee_id = user.getId();
        if(empId==employee_id){
            return true;
        }
        return false;
    }
}
