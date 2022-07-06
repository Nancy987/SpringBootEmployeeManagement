package com.Prograd.EmployeeManagement.Modals;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name="Organisations")
public class Organisation implements UserDetails {
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
    private String organisation_name;

    @Column(nullable = false)
    @NotEmpty
    private String password;

    @Column(nullable = false)
    private String role = "ROLE_ADMIN";


    @OneToMany(mappedBy = "organisation")
    @JsonManagedReference
    private List<Employee> employees;

    @OneToMany(mappedBy = "organisation")
    private List<Asset> assets;

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
