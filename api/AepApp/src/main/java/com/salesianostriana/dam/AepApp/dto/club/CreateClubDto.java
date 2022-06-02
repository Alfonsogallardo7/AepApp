package com.salesianostriana.dam.AepApp.dto.club;

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
public class CreateClubDto {

    private String nombre, foto, abreviatura, nif, direccionSede, email;
}
