package com.Prograd.EmployeeManagement.Controllers;

import com.Prograd.EmployeeManagement.Exceptions.EmployeeNotFound;
import com.Prograd.EmployeeManagement.Exceptions.NoAccessException;
import com.Prograd.EmployeeManagement.Exceptions.OrganisationNotFound;
import com.Prograd.EmployeeManagement.Modals.Employee;
import com.Prograd.EmployeeManagement.Service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody Employee employee) throws OrganisationNotFound, NoAccessException {
        return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }
    @GetMapping
    public List<Employee> getAllEmployees() throws OrganisationNotFound, EmployeeNotFound {
        return employeeService.getAllEmployees();
    }
    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") int id) throws EmployeeNotFound, NoAccessException, OrganisationNotFound {
        return new ResponseEntity<Employee>(employeeService.getEmployeeById(id), HttpStatus.OK);
    }
    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") int id,@Valid  @RequestBody Employee employee) throws EmployeeNotFound, OrganisationNotFound, NoAccessException {
        return new ResponseEntity<Employee>(employeeService.updateEmployee(employee,id), HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") int id) throws NoAccessException, OrganisationNotFound {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<String>("Employee is deleted successfully.",HttpStatus.OK);
    }
}
