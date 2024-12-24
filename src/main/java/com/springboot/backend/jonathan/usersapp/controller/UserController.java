package com.springboot.backend.jonathan.usersapp.controller;

import com.springboot.backend.jonathan.usersapp.entity.User;
import com.springboot.backend.jonathan.usersapp.entity.dtos.UsuarioDto;
import com.springboot.backend.jonathan.usersapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public List<UsuarioDto> list() {
        return userService.findAll();
    }

    @GetMapping("/page/{page}")
    public ResponseEntity<Page<UsuarioDto>> page(@PathVariable int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return ResponseEntity.status(HttpStatus.OK).body(userService.paginarUsuarios(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarPorId(@PathVariable Long id) {
        Map<String, String> errors = new HashMap<>();
        Optional<UsuarioDto> userOptional = userService.findById(id);
        if (userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(userOptional.get());
        }
        errors.put("error","No existe el usuario con el id " + id);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody User user, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }


    @PutMapping("{id}")
    public ResponseEntity<?> update(@Valid @RequestBody UsuarioDto usuarioDto, BindingResult result,  @PathVariable Long id) {
        if (result.hasErrors()) {
            return validation(result);
        }
       return ResponseEntity.status(HttpStatus.CREATED).body(userService.update(id,  usuarioDto));

    }


    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Map<String, String> errors = new HashMap<>();
        Optional<UsuarioDto> userOptional = userService.findById(id);
        if (userOptional.isPresent()) {
            errors.put("error", "se elimino el usuario con el id: " + id);
            userService.delete(id);
            return new ResponseEntity<>(errors, HttpStatus.OK);
        }
        errors.put("error", "no se encontro el usuario con id " + id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
    }


    /*BindingResult captura el resultado de los errores de la validaes con @Valid y los guarda como objetos de tipo FiledError */
   private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> error = new HashMap<>();
        result.getFieldErrors().forEach(errors ->{
            error.put(errors.getField(), "El campo " + errors.getField() + " " + errors.getDefaultMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
   }

}
