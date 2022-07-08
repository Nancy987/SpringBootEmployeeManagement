package com.Prograd.EmployeeManagement.Security;

import com.Prograd.EmployeeManagement.Modals.Employee;
import com.Prograd.EmployeeManagement.Modals.Organisation;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class EmployeeSecurity {
    public boolean hasEmpId(int empId){
        Employee user = (Employee) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int employee_id = user.getId();
        if(empId==employee_id){
            return true;
        }
        return false;
    }
    public boolean hasOrgId(int orgId){
        Organisation user = (Organisation) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int organisation_id = user.getId();
        if(orgId==organisation_id){
            return true;
        }
        return false;
    }
}
