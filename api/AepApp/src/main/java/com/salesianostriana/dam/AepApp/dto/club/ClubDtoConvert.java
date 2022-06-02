package com.salesianostriana.dam.AepApp.dto.club;

import com.salesianostriana.dam.AepApp.models.Club;
import org.springframework.stereotype.Component;

@Component
public class ClubDtoConvert {

    public GetClubDto convertClubToClubDto (Club club) {
        return GetClubDto.builder()
                .id(club.getId())
                .nombre(club.getNombre())
                .abreviatura(club.getAbreviatura())
                .foto(club.getFoto())
                .nif(club.getNif())
                .direccionSede(club.getDireccionSede())
                .email(club.getDireccionSede())
                .build();
    }

    public Club convertClubDtoToClub (GetClubDto clubDto, Club club) {
        return new Club(
                club.getId(),
                clubDto.getNombre(),
                club.getAbreviatura(),
                clubDto.getFoto(),
                club.getNif(),
                club.getDireccionSede(),
                clubDto.getEmail()
        );
    }
}
