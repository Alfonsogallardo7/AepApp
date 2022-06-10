package com.salesianostriana.dam.AepApp.dto.competidor;

import lombok.*;

import java.time.LocalDate;

@Builder
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class CreateCompetidorDto {

    private String nombre, apellidos, categoriaPeso, foto, club;
    private LocalDate fechaNacimiento;
    private Double marcasSq, marcasBp, marcasDl;

}
