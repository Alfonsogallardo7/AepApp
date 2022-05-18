package com.salesianostriana.dam.AepApp.dto.juez;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
public class CreateJuezDto {

    private String nombre, apellidos, tipoJuez;
}
