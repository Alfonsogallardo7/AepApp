package com.salesianostriana.dam.AepApp.users.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUsuarioDto {
    private String nombre, apellidos, username, email, password, password2, fotoPerfil, dni, direccion, codigoPostal, localidad, telefono;

    private LocalDate fechaNacimiento;
}
