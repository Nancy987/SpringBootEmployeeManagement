package com.Prograd.EmployeeManagement.Modals;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name="Employees")
public class Employee implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private int id;

    @Column(nullable = false)
    @NotEmpty
    @Pattern(message="Username must be of 4 to 12 length with no special characters", regexp = "^[a-zA-Z0-9]{4,12}$")
    private String username;

    @Column(nullable = false)
    @NotEmpty
    @Pattern(message="Only characters are allowed", regexp = "^[a-zA-Z ]+$")
    private String employee_name;

    @Column(nullable = false)
    @NotEmpty
    @Email(message = "Email is not valid", regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
    private String email_id;

    @Column(nullable = false)
    @NotEmpty
    @Pattern(message="Phone number is not valid", regexp = "^[0-9]{10}$")
    private String phone_no;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    @NotEmpty
    private String password;

    @Column(nullable = false)
    private String role = "ROLE_EMPLOYEE";

    @ManyToOne
    @JoinColumn(name="organisation_id")
    @JsonBackReference
    private Organisation organisation;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(getRole());

        return List.of(simpleGrantedAuthority);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
