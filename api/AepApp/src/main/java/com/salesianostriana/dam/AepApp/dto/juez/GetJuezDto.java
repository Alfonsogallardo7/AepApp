package com.salesianostriana.dam.AepApp.dto.juez;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetJuezDto {

    private UUID id;

    private String nombre, apellidos, tipoJuez;
}
