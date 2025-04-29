package com.springboot.backend.jonathan.usersapp.service;

import com.springboot.backend.jonathan.usersapp.entity.User;
import com.springboot.backend.jonathan.usersapp.repository.UserRepository;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/*clase encargada del inicio de sesion*/
/*poner dependencia sprign security en en el pom para usar UserDetailsService*/
@Service
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository repository;

    public JpaUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    /*implementar*/
    @Transactional(readOnly = true)
    @Override                         /*este username viene del postman cuando nos logeamos*/ public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = repository.findByUsername(username);
        /*si no lo encuentra*/
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("El username " + username + " no existe en el sistema");
        }
        /*si lo encuentra*/
        User user = optionalUser.get();

        /*covierte los roles a una lista de roles de tipo GrantedAuthority*/
        List<GrantedAuthority> authorities =
                user.getRoles()
                        .stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName()))
                        .collect(Collectors.toList());
        /*retorna un User de spring security*/
        return new org.springframework.security.core.userdetails.User(username,
                user.getPassword(),
                true,
                true,
                true,
                true,
                authorities);
    }
}


/*--> auth/SpringSecurityConfig.java*/