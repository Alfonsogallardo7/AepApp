package com.salesianostriana.dam.AepApp.dto.competidor;

import com.salesianostriana.dam.AepApp.models.Competidor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CompetidorDtoConvert {

    public GetCompetidorDto convertCompetidorToCompetidorDto (Competidor competidor) {
        return GetCompetidorDto.builder()
                .id(competidor.getId())
                .nombre(competidor.getNombre())
                .apellidos(competidor.getApellidos())
                .categoriaPeso(competidor.getCategoriaPeso())
                .fechaNacimiento(competidor.getFechaNacimiento())
                .marcasSq(competidor.getMarcasSq())
                .marcasBp(competidor.getMarcasBp())
                .marcasDl(competidor.getMarcasDl())
                .foto(competidor.getFoto())
                .club(competidor.getClub())

                .build();
    }

    public Competidor convertCompetidorDtoToCompetidor (GetCompetidorDto competidorDto, Competidor competidor) {
        return new Competidor(
                competidor.getId(),
                competidorDto.getNombre(),
                competidorDto.getApellidos(),
                competidorDto.getFechaNacimiento(),
                competidorDto.getCategoriaPeso(),
                competidorDto.getFoto(),
                competidorDto.getMarcasSq(),
                competidorDto.getMarcasBp(),
                competidorDto.getMarcasDl(),
                competidorDto.getClub(),
                competidor.getListaCompeticiones()
        );
    }
}
