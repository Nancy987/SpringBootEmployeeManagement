package com.Prograd.EmployeeManagement.Modals;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@Entity
@Table(name="Organisations")
public class Organisation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private int id;

    @Column(nullable = false)
    @NotEmpty
    @Pattern(message="Only characters are allowed", regexp = "^[a-zA-Z ]+$")
    private String organisation_name;

    @OneToMany(mappedBy = "organisation")
    @JsonManagedReference
    private List<Employee> employees;

    @OneToMany(mappedBy = "organisation")
    private List<Asset> assets;
}
