package com.springboot.backend.jonathan.usersapp.entity.dtos;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;


@NoArgsConstructor
@Getter
@Setter
public class UsuarioDto {


    private Long id;
    private String name;
    private String lastname;
    private String email;
    private String username;
    private String password;
    private List<RolDto> roles;
    
}
