package com.Prograd.EmployeeManagement.Config;

import com.Prograd.EmployeeManagement.Security.OrganisationDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private OrganisationDetailService organisationDetailService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.organisationDetailService).passwordEncoder(passwordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {        // basic authentication
        http.csrf().disable().authorizeHttpRequests()
//                .antMatchers("/organisation/create").permitAll()
                .antMatchers(HttpMethod.POST,"/employees").hasAnyRole("ADMIN","EMPLOYEE")
                .antMatchers(HttpMethod.PUT,"/employees/{empId}").hasAnyRole("ADMIN","EMPLOYEE")
                .antMatchers(HttpMethod.DELETE).hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/employees").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/employees/{empId}").hasAnyRole("ADMIN","EMPLOYEE")
                .antMatchers("/organisation/**").hasRole("ADMIN")
                .antMatchers("/assets/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and().httpBasic();
    }
}
