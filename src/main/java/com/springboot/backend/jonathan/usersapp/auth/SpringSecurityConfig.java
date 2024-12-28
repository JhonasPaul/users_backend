package com.springboot.backend.jonathan.usersapp.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

/*2*/
@Configuration
public class SpringSecurityConfig {

    /*metodo para manejar las cadenas de filtros de SpringSecurity*/
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(authz -> {
                    authz.requestMatchers(HttpMethod.GET, "/api/users/page/{page}").permitAll()
                            .requestMatchers(HttpMethod.GET, "/api/users/{id}").hasAnyRole("USER", "ADMIN")
                            .requestMatchers(HttpMethod.POST, "/api/users").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.PUT, "/api/users/{id}").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.DELETE, "/api/users/{id}").hasRole("ADMIN")
                            .anyRequest().authenticated();
                })
                .csrf(config -> config.disable())
                .sessionManagement(managment -> managment.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }
}
/*--> /auth/filter/JwtAuthenticationFilter.java*/