package com.salesianostriana.dam.AepApp.dto.competidor;

import com.salesianostriana.dam.AepApp.models.Competidor;
import org.springframework.stereotype.Component;

@Component
public class CompetidorConCompeticionDtoCoverter {

    public GetCompetidorListaDto convertCompetidorConCompeticionToCompetidorConCompeticionDto (Competidor competidor) {
        return GetCompetidorListaDto.builder()
                .id(competidor.getId())
                .nombre(competidor.getNombre())
                .apellidos(competidor.getApellidos())
                .club(competidor.getClub())
                .categoriaPeso(competidor.getCategoriaPeso())
                .fechaNacimiento(competidor.getFechaNacimiento())
                .build();
    }
}
