package com.springboot.backend.jonathan.usersapp.entity.dtos;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class RolDto {
    private Long id;
    private String name;


    public RolDto(Long id, String name ){
        this.id = id;
        this.name = name;

    }
}
