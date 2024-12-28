package com.springboot.backend.jonathan.usersapp.auth.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.backend.jonathan.usersapp.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

/*3*/
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager1) {
        this.authenticationManager = authenticationManager1;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = null;
        String password = null;
        User user = null;

        try {
            user = new ObjectMapper().readValue(request.getInputStream(), User.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
