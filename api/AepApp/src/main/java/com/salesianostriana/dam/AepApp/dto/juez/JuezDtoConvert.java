package com.salesianostriana.dam.AepApp.dto.juez;

import com.salesianostriana.dam.AepApp.models.Juez;
import org.springframework.stereotype.Component;

@Component
public class JuezDtoConvert {

    public GetJuezDto convertJuezToJuezDto (Juez juez) {
        return GetJuezDto.builder()
                .id(juez.getId())
                .nombre(juez.getNombre())
                .apellidos(juez.getApellidos())
                .tipoJuez(juez.getTipoJuez())
                .build();
    }

    public Juez convertJuezDtoToJuez (GetJuezDto juezDto, Juez juez) {
        return new Juez(
                juez.getId(),
                juezDto.getNombre(),
                juezDto.getApellidos(),
                juezDto.getTipoJuez()
        );
    }
}
