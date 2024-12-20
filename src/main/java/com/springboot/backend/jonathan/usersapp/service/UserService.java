package com.springboot.backend.jonathan.usersapp.service;

import com.springboot.backend.jonathan.usersapp.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    Page<User> paginarUsuarios(Pageable pageable);

    /*Optional ayuda a evitar el NullPinterException*/
    Optional<User> findById(Long id);

    User save(User user);

    void delete(Long id);
}
