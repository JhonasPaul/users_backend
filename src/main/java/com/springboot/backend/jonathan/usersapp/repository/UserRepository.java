package com.springboot.backend.jonathan.usersapp.repository;


import com.springboot.backend.jonathan.usersapp.entity.User;
import com.springboot.backend.jonathan.usersapp.entity.dtos.UsuarioDto;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    /*@Query("SELECT new  com.springboot.backend.jonathan.usersapp.entity.dtos.UsuarioDto" +
            "(u.id, u.name, u.email) FROM User u")
    List<UsuarioDto> findAllUserDtos();*/
}
