package com.nip.public_issue.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity

public class SecurityConfig {

    

    

@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    // disable CSRF
    http.csrf().disable();

    // allow all requests
    http.authorizeRequests().anyRequest().permitAll();

    // enable CORS (important for multipart)
    http.cors();

    // stateless session management
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    return http.build();
}



}
        