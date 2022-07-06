package com.Prograd.EmployeeManagement.Security;

import com.Prograd.EmployeeManagement.Modals.Employee;
import com.Prograd.EmployeeManagement.Modals.Organisation;
import com.Prograd.EmployeeManagement.Repository.EmployeeRepository;
import com.Prograd.EmployeeManagement.Repository.OrganisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class OrganisationDetailService implements UserDetailsService {            // authentication with database
    @Autowired
    private OrganisationRepository organisationRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //loads user from database by username
        try {
            Organisation organisation = this.organisationRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not present"));
            return organisation;
        }
        catch(UsernameNotFoundException e){
            Employee employee = this.employeeRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not present"));
            return  employee;
        }
    }
}