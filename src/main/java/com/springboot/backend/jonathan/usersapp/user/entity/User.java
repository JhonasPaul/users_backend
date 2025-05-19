package com.springboot.backend.jonathan.usersapp.user.entity;


import com.springboot.backend.jonathan.usersapp.common.entity.Persona;
import com.springboot.backend.jonathan.usersapp.rol.entity.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends Persona implements Serializable {
    /*el id lo hereda de la clase abstracta BaseId*/



    @Column(unique = true)
    @Size(min = 4, max = 12)
    @NotBlank
    private String username;

    @NotBlank
    private String password;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role_id"})}
    )
    private List<Role> roles;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public User() {
        this.roles = new ArrayList<>();
    }
}
