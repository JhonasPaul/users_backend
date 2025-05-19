package com.springboot.backend.jonathan.usersapp.user.mapper;

import com.springboot.backend.jonathan.usersapp.user.dtos.UsuarioDto;
import com.springboot.backend.jonathan.usersapp.user.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioDto toUsuarioDto(User usuarioDto);

    List<UsuarioDto> toUsuariosDto(List<User> users);

    User toUser(UsuarioDto usuarioDto);
}
