package com.Prograd.EmployeeManagement.Controllers;

import com.Prograd.EmployeeManagement.Exceptions.NoAccessException;
import com.Prograd.EmployeeManagement.Exceptions.OrganisationNotFound;
import com.Prograd.EmployeeManagement.Modals.Organisation;
import com.Prograd.EmployeeManagement.Service.OrganisationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("organisation")
public class OrganisationController {

    private OrganisationService organisationService;

    public OrganisationController(OrganisationService organisationService) {
        this.organisationService = organisationService;
    }
    @PostMapping("/create")
    public ResponseEntity<Organisation> saveOrganisation(@Valid @RequestBody Organisation organisation){
        return new ResponseEntity<Organisation>(organisationService.saveOrganisation(organisation), HttpStatus.CREATED);
    }
    @GetMapping
    public List<Organisation> getAllOrganisations(){
        return organisationService.getAllOrganisations();
    }
    @GetMapping("{id}")
    public ResponseEntity<Organisation> getOrganisationById(@PathVariable("id") int id) throws OrganisationNotFound, NoAccessException {
        return new ResponseEntity<Organisation>(organisationService.getOrganisationById(id), HttpStatus.OK);
    }
    @PutMapping("{id}")
    public ResponseEntity<Organisation> updateOrganisation(@PathVariable("id") int id,@Valid @RequestBody Organisation organisation) throws OrganisationNotFound, NoAccessException {
        return new ResponseEntity<Organisation>(organisationService.updateOrganisation(organisation,id), HttpStatus.OK);
    }
//    @DeleteMapping("{id}")
//    public ResponseEntity<String> deleteOrganisation(@PathVariable("id") int id) throws OrganisationNotFound, NoAccessException {
//        organisationService.deleteOrganisation(id);
//        return new ResponseEntity<String>("Organisation is deleted successfully.",HttpStatus.OK);
//    }
}
