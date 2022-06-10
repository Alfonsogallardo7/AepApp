package com.salesianostriana.dam.AepApp.dto.competidor;

import com.salesianostriana.dam.AepApp.dto.competicion.GetCompeticionDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetCompetidorDto {

    private UUID id;
    private String nombre, apellidos, categoriaPeso, foto, club;
    private LocalDate fechaNacimiento;
    private Double marcasSq, marcasBp, marcasDl;

    @Builder.Default
    private List<GetCompeticionDto> competiciones = new ArrayList<>();
}
