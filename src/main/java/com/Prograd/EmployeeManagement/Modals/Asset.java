package com.Prograd.EmployeeManagement.Modals;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Data
@Entity
@Table(name="Assets")
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private int id;

    @Column(nullable = false)
    @Pattern(message="Special characters are not allowed", regexp = "^[a-zA-Z0-9 ]+$")
    private String asset_name;

    @Column(nullable = false)
    private int quantity;
    @Column(nullable = false)
    private float cost_per_asset;
    @Column(nullable = false)
    private float total_cost;

    @ManyToOne
    @JoinColumn(name="organisation_id")
    @JsonBackReference
    private Organisation organisation;
}
