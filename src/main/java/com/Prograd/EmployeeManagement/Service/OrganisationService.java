package com.Prograd.EmployeeManagement.Service;

import com.Prograd.EmployeeManagement.Exceptions.NoAccessException;
import com.Prograd.EmployeeManagement.Exceptions.OrganisationNotFound;
import com.Prograd.EmployeeManagement.Modals.Organisation;

import java.util.List;

public interface OrganisationService {
    Organisation saveOrganisation(Organisation organisation);
    List<Organisation> getAllOrganisations();
    Organisation getOrganisationById(int id) throws OrganisationNotFound, NoAccessException;
    Organisation updateOrganisation(Organisation organisation,int id) throws OrganisationNotFound, NoAccessException;
    void deleteOrganisation(int id) throws OrganisationNotFound, NoAccessException;
}
