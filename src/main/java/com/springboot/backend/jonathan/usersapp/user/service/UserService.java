package com.springboot.backend.jonathan.usersapp.user.service;

import com.springboot.backend.jonathan.usersapp.user.entity.User;
import com.springboot.backend.jonathan.usersapp.user.dtos.UsuarioDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {


    List<UsuarioDto> findAll();


    Page<UsuarioDto> paginarUsuarios(Pageable pageable);
    Page<User> paginarUsuarios1(Pageable pageable);

    /*Optional ayuda a evitar el NullPinterException*/
    Optional<UsuarioDto> findById(Long id);

    ResponseEntity<?> save(UsuarioDto usuarioDto);

    void delete(Long id);

    ResponseEntity<?> update(Long id, UsuarioDto usuarioDto);
}
