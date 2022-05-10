package com.salesianostriana.dam.AepApp.users.controller;

import com.salesianostriana.dam.AepApp.users.dto.CreateUsuarioDto;
import com.salesianostriana.dam.AepApp.users.dto.GetUsuarioDto;
import com.salesianostriana.dam.AepApp.users.dto.UsuarioDtoConvert;
import com.salesianostriana.dam.AepApp.users.models.Usuario;
import com.salesianostriana.dam.AepApp.users.services.UsuarioServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioServices usuarioServices;
    private final UsuarioDtoConvert usuarioDtoConvert;

    @PostMapping("/auth/register")
    public ResponseEntity<GetUsuarioDto> addUsuario (@RequestPart("nuevoUsuario") CreateUsuarioDto nuevoUsuario, @RequestPart("file") MultipartFile file) throws IOException {

        Usuario usuario = usuarioServices.save(nuevoUsuario, file);

        if (usuario == null) {
            return ResponseEntity.badRequest().build();
        } else
            return ResponseEntity.ok(usuarioDtoConvert.convertUsuarioToUsuarioDto(usuario));
    }
}
