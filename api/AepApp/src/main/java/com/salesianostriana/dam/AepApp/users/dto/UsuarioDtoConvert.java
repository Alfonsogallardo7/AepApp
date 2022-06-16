package com.salesianostriana.dam.AepApp.users.dto;

import com.salesianostriana.dam.AepApp.users.models.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioDtoConvert {

    public GetUsuarioDto convertUsuarioToUsuarioDto (Usuario usuario) {
        return GetUsuarioDto.builder()
                .nombre(usuario.getNombre())
                .apellidos(usuario.getApellidos())
                .id(usuario.getId())
                .email(usuario.getEmail())
                .fotoPerfil(usuario.getFotoPerfil())
                .username(usuario.getUsername())
                .rol(usuario.getRole().name())
                .build();
        }
}
