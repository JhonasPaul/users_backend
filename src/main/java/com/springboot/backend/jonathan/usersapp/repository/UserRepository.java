package com.springboot.backend.jonathan.usersapp.repository;


import com.springboot.backend.jonathan.usersapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
}
