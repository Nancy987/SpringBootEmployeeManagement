package com.Prograd.EmployeeManagement.Service;

import com.Prograd.EmployeeManagement.Exceptions.OrganisationNotFound;
import com.Prograd.EmployeeManagement.Modals.Organisation;

import java.util.List;

public interface OrganisationService {
    Organisation saveOrganisation(Organisation organisation);
    List<Organisation> getAllOrganisations();
    Organisation getOrganisationById(int id) throws OrganisationNotFound;
    Organisation updateOrganisation(Organisation organisation,int id) throws OrganisationNotFound;
    void deleteOrganisation(int id);
}
