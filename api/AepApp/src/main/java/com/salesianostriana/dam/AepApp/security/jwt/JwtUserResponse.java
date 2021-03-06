package com.salesianostriana.dam.AepApp.security.jwt;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Builder
public class JwtUserResponse {

    private String nombre, apellidos, email, username, fotoPerfil, rol, token, direccion, codigoPostal, localidad;
    private LocalDate fechaNacimiento;

}
