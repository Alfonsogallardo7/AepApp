package com.salesianostriana.dam.AepApp.services;

import com.salesianostriana.dam.AepApp.dto.club.CreateClubDto;
import com.salesianostriana.dam.AepApp.dto.club.GetClubDto;
import com.salesianostriana.dam.AepApp.dto.juez.CreateJuezDto;
import com.salesianostriana.dam.AepApp.dto.juez.GetJuezDto;
import com.salesianostriana.dam.AepApp.models.Club;
import com.salesianostriana.dam.AepApp.models.Juez;
import com.salesianostriana.dam.AepApp.users.models.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.UUID;

public interface ClubService {

    Club save (CreateClubDto clubDto, MultipartFile file, Usuario usuario);
    Page<Club> findAll (Pageable pageable);
    Optional<Club> findById (UUID id);
    GetClubDto edit (UUID id, CreateClubDto createClubDto, MultipartFile file, Usuario usuario);
    void deleteById (UUID id);
}
