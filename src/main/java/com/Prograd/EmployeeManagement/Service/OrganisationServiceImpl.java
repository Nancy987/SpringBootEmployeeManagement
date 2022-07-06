package com.Prograd.EmployeeManagement.Service;

import com.Prograd.EmployeeManagement.Exceptions.OrganisationNotFound;
import com.Prograd.EmployeeManagement.Modals.Organisation;
import com.Prograd.EmployeeManagement.Repository.OrganisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganisationServiceImpl implements OrganisationService {
    private OrganisationRepository organisationRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public OrganisationServiceImpl(OrganisationRepository organisationRepository) {
        this.organisationRepository = organisationRepository;
    }

    @Override
    public Organisation saveOrganisation(Organisation organisation) {
        String encodedPassword = passwordEncoder.encode(organisation.getPassword());
        organisation.setPassword(encodedPassword);
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
    public Organisation updateOrganisation(Organisation organisation, int id) throws OrganisationNotFound {
        Organisation existingOrganisation = organisationRepository.findById(id).orElseThrow(()->new OrganisationNotFound(("Organisation not exist")));

        existingOrganisation.setOrganisation_name(organisation.getOrganisation_name());
        existingOrganisation.setUsername(organisation.getUsername());
        existingOrganisation.setPassword(organisation.getPassword());

        return organisationRepository.save(existingOrganisation);
    }

    @Override
    public void deleteOrganisation(int id) {
        organisationRepository.deleteById(id);
    }
}

