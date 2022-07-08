package com.Prograd.EmployeeManagement.Service;

import com.Prograd.EmployeeManagement.Exceptions.NoAccessException;
import com.Prograd.EmployeeManagement.Exceptions.OrganisationNotFound;
import com.Prograd.EmployeeManagement.Modals.Organisation;
import com.Prograd.EmployeeManagement.Repository.AssetRepository;
import com.Prograd.EmployeeManagement.Repository.EmployeeRepository;
import com.Prograd.EmployeeManagement.Repository.OrganisationRepository;
import com.Prograd.EmployeeManagement.Security.EmployeeSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganisationServiceImpl implements OrganisationService {
    private OrganisationRepository organisationRepository;

    public OrganisationServiceImpl(OrganisationRepository organisationRepository) {
        this.organisationRepository = organisationRepository;
    }

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private EmployeeSecurity employeeSecurity;

    @Override
    public Organisation saveOrganisation(Organisation organisation) {
        return organisationRepository.save(organisation);
    }

    @Override
    public List<Organisation> getAllOrganisations() {
        return organisationRepository.findAll();
    }

    @Override
    public Organisation getOrganisationById(int id) throws OrganisationNotFound {
        Organisation organisation = organisationRepository.findById(id).orElseThrow(()->new OrganisationNotFound("Organisation not exist"));
        return organisation;
    }

    @Override
    public Organisation updateOrganisation(Organisation organisation, int id) throws OrganisationNotFound, NoAccessException {
        Organisation existingOrganisation = organisationRepository.findById(id).orElseThrow(()->new OrganisationNotFound(("Organisation not exist")));
        existingOrganisation.setOrganisation_name(organisation.getOrganisation_name());

        return organisationRepository.save(existingOrganisation);
    }

    @Override
    public void deleteOrganisation(int id) throws OrganisationNotFound, NoAccessException {
        Organisation organisation = organisationRepository.findById(id).orElseThrow(() -> new OrganisationNotFound("Organisation not exist"));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        boolean hasEmpRole = authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_EMPLOYEE"));

        if(!hasEmpRole) {
            boolean res = employeeSecurity.hasOrgId(id);
            if (res) {
                employeeRepository.deleteAllEmpByOrgId(organisation);
                assetRepository.deleteAllAssetByOrgId(organisation);
                organisationRepository.deleteById(id);
            } else {
                throw new NoAccessException("You have no access for this request");
            }
        }else{
            throw new NoAccessException("You have no access for this request");
        }
    }
}

