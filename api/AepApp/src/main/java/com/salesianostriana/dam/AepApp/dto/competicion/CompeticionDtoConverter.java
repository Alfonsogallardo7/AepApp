package com.salesianostriana.dam.AepApp.dto.competicion;

import com.salesianostriana.dam.AepApp.models.Competicion;
import org.springframework.stereotype.Component;

@Component
public class CompeticionDtoConverter {

    public GetCompeticionDto convertComepticionToCompeticionDto (Competicion competicion) {
        return GetCompeticionDto.builder()
                .id(competicion.getId())
                .nombre(competicion.getNombre())
                .categoriaCompeticion(competicion.getCategoriaCompeticion())
                .cartel(competicion.getCartel())
                .fechaFin(competicion.getFechaFin())
                .fechaInicio(competicion.getFechaInicio())
                .organizador(competicion.getOrganizador())
                .provincia(competicion.getProvincia())
                .localidad(competicion.getLocalidad())
                .sesiones(competicion.getSesiones())
                .build();
    }

    public Competicion convertCompeticionDtoToCompeticion (GetCompeticionDto competicionDto, Competicion competicion) {
        return new Competicion(
                competicion.getId(),
                competicionDto.getNombre(),
                competicionDto.getCartel(),
                competicionDto.getFechaInicio(),
                competicionDto.getFechaFin(),
                competicionDto.getLocalidad(),
                competicionDto.getProvincia(),
                competicionDto.getCategoriaCompeticion(),
                competicionDto.getSesiones(),
                competicionDto.getOrganizador()
        );
    }
}
