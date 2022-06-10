package com.salesianostriana.dam.AepApp.services;

import com.salesianostriana.dam.AepApp.dto.competicion.CreateCompeticionDto;
import com.salesianostriana.dam.AepApp.dto.competicion.GetCompeticionDto;
import com.salesianostriana.dam.AepApp.dto.competidor.CreateCompetidorDto;
import com.salesianostriana.dam.AepApp.dto.competidor.GetCompetidorDto;
import com.salesianostriana.dam.AepApp.models.Competicion;
import com.salesianostriana.dam.AepApp.models.Competidor;
import com.salesianostriana.dam.AepApp.users.models.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.UUID;


public interface CompetidorService {

    Competidor save (CreateCompetidorDto competidor, MultipartFile file, Usuario usuario);
    Page<Competidor> findAll (Pageable pageable);
    Optional<Competidor> findById (UUID id);
    GetCompetidorDto edit (UUID id, CreateCompetidorDto createCompetidorDto, MultipartFile file, Usuario usuario);
    void deleteById (UUID id);
}
