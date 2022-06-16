package com.salesianostriana.dam.AepApp.services;

import com.salesianostriana.dam.AepApp.dto.juez.CreateJuezDto;
import com.salesianostriana.dam.AepApp.dto.juez.GetJuezDto;
import com.salesianostriana.dam.AepApp.models.Juez;
import com.salesianostriana.dam.AepApp.users.models.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.UUID;

public interface JuezService {

    Juez save (CreateJuezDto juezDto, Usuario usuario);
    Page<Juez> findAll (Pageable pageable);
    Optional<Juez> findById (UUID id);
    GetJuezDto edit (UUID id, CreateJuezDto createJuezDto, Usuario usuario);
    void deleteById (UUID id);
}
