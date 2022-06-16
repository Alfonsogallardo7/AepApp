package com.salesianostriana.dam.AepApp.dto.competicion;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
public class CreateCompeticionDto {

    private String nombre, cartel, organizador, categoriaCompeticion, cuadranteJueces, sesiones, localidad, provincia;
    private LocalDate fechaInicio, fechaFin;
}
