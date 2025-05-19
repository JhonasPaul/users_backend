package com.springboot.backend.jonathan.usersapp.user.service;

import com.springboot.backend.jonathan.usersapp.user.entity.User;
import com.springboot.backend.jonathan.usersapp.user.dtos.UsuarioDto;
import com.springboot.backend.jonathan.usersapp.user.mapper.UsuarioMapper;
import com.springboot.backend.jonathan.usersapp.user.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UsuarioMapper usuarioMapper;

    public UserServiceImpl(UserRepository userRepository, UsuarioMapper usuarioMapper) {
        this.userRepository = userRepository;
        this.usuarioMapper = usuarioMapper;
    }



    @Override
    public List<UsuarioDto> findAll() {
        return userRepository.findAll() // Obtén la lista de entidades User
                .stream() // Convierte la lista a un Stream
                .map(u -> usuarioMapper.toUsuarioDto(u)) // Mapea cada User a UsuarioDto
                .collect(Collectors.toList()); // Recolecta los DTOs en una lista
    }


    @Override
    public Page<User> paginarUsuarios1(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Page<UsuarioDto> paginarUsuarios(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(U -> usuarioMapper.toUsuarioDto(U));
    }


    /*ES INNCECESARIO USAR STREAM EN UN SOLO VALOR, O SEA SOBRE UN UNICO USUARIO*/
    @Override
    public Optional<UsuarioDto> findById(Long id) {
        return userRepository.findById(id)
                .map(u -> usuarioMapper.toUsuarioDto(u));
    }


    @Override
    public ResponseEntity<?> save(@Valid UsuarioDto usuarioDto) {
        Map<String, String> errors = new HashMap<>();
        if (userRepository.existsByUsername(usuarioDto.getUsername())) {
            errors.put("error", "username ya existe");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }
        if (userRepository.existsByEmail(usuarioDto.getEmail())) {
            errors.put("error", "email ya existe");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }
        User user = usuarioMapper.toUser(usuarioDto);

        User userSave = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioMapper.toUsuarioDto(userSave));
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public ResponseEntity<?> update(Long id, UsuarioDto usuarioDto) {
        Optional<User> userOptional = userRepository.findById(id);
        // Si el usuario no existe, devolver un error 404
        if (!userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }

        User usuarioEncontrado = userOptional.get();

        // Verificar si el username o el email ya están en uso
        if (userRepository.existsByUsername(usuarioDto.getUsername()) && !usuarioEncontrado.getUsername().equals(usuarioDto.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El username ya está en uso");
        }
        if (userRepository.existsByEmail(usuarioDto.getEmail()) && !usuarioEncontrado.getEmail().equals(usuarioDto.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El email ya está en uso");
        }
        // Actualizar el usuario con los nuevos datos
        usuarioEncontrado.setUsername(usuarioDto.getUsername());
        usuarioEncontrado.setEmail(usuarioDto.getEmail());
        usuarioEncontrado.setName(usuarioDto.getName());
        usuarioEncontrado.setLastname(usuarioDto.getLastname());

        // Guardar los cambios en la base de datos
        User updatedUser = userRepository.save(usuarioEncontrado);

        // Mapear a DTO y devolver la respuesta
        UsuarioDto usuarioDto1 = usuarioMapper.toUsuarioDto(updatedUser);
        return ResponseEntity.status(HttpStatus.OK).body(usuarioDto1);
    }


}
