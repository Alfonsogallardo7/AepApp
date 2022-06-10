package com.salesianostriana.dam.AepApp.dto.competicion;

//import com.salesianostriana.dam.AepApp.dto.competidor.GetCompetidorDto;
//import com.salesianostriana.dam.AepApp.dto.competidor.GetCompetidorListaDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetCompeticionDto {

    private UUID id;
    private String nombre, cartel, organizador, categoriaCompeticion, cuadranteJueces, sesiones, localidad, provincia;
    private LocalDate fechaInicio, fechaFin;

   /* @Builder.Default
    private List<GetCompetidorListaDto> competidores = new ArrayList<>();*/
}
