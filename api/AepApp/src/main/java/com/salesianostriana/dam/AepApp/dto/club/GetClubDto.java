package com.salesianostriana.dam.AepApp.dto.club;

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
public class GetClubDto {

    private UUID id;
    private String nombre, foto, abreviatura, nif, direccionSede, email;
}
