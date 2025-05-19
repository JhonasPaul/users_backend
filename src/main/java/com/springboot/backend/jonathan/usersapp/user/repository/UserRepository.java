package com.springboot.backend.jonathan.usersapp.user.repository;


import com.springboot.backend.jonathan.usersapp.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    /*@Query("SELECT new  com.springboot.backend.jonathan.usersapp.entity.dtos.UsuarioDto" +
            "(u.id, u.name, u.email) FROM User u")
    List<UsuarioDto> findAllUserDtos();*/
}
