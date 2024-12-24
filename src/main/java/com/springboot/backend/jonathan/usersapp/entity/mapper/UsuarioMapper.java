package com.springboot.backend.jonathan.usersapp.entity.mapper;

import com.springboot.backend.jonathan.usersapp.entity.User;
import com.springboot.backend.jonathan.usersapp.entity.dtos.UsuarioDto;
import org.mapstruct.Mapper;


import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioDto toUsuarioDto(User user);

    List<UsuarioDto> toUsuariosDto(List<User> users);

}
