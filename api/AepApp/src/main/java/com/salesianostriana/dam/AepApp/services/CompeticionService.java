package com.salesianostriana.dam.AepApp.services;

import com.salesianostriana.dam.AepApp.dto.competicion.CreateCompeticionDto;
import com.salesianostriana.dam.AepApp.dto.competicion.GetCompeticionDto;
import com.salesianostriana.dam.AepApp.models.Competicion;
import com.salesianostriana.dam.AepApp.users.models.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.UUID;

public interface CompeticionService {

    Competicion save (CreateCompeticionDto competicion, MultipartFile file, Usuario usuario);
    Page<Competicion> findAll (Pageable pageable);
    Optional<Competicion> findById (UUID id);
    GetCompeticionDto edit (UUID id, CreateCompeticionDto createCompeticionDto, MultipartFile file, Usuario usuario);
    void deleteById (UUID id);
}
