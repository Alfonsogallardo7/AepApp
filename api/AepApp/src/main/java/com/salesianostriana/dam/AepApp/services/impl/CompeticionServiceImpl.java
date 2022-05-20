package com.salesianostriana.dam.AepApp.services.impl;

import com.salesianostriana.dam.AepApp.dto.competicion.CompeticionDtoConverter;
import com.salesianostriana.dam.AepApp.dto.competicion.CreateCompeticionDto;
import com.salesianostriana.dam.AepApp.dto.competicion.GetCompeticionDto;
import com.salesianostriana.dam.AepApp.errors.exceptions.ListEntityNotFoundException;
import com.salesianostriana.dam.AepApp.errors.exceptions.SingleEntityNotFoundException;
import com.salesianostriana.dam.AepApp.models.Competicion;
import com.salesianostriana.dam.AepApp.repository.CompeticionRepository;
import com.salesianostriana.dam.AepApp.services.CompeticionService;
import com.salesianostriana.dam.AepApp.services.StorageService;
import com.salesianostriana.dam.AepApp.users.models.Usuario;
import com.salesianostriana.dam.AepApp.users.repository.UsuarioRepository;
import com.salesianostriana.dam.AepApp.utils.paginations.PaginationsLinksUtils;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompeticionServiceImpl implements CompeticionService {

    private final CompeticionRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final StorageService storageService;
    private final FileSystemStorageService fileSystemStorageService;
    private final CompeticionDtoConverter competicionDtoConverter;
    private final PaginationsLinksUtils paginationsLinksUtils;

    @Override
    public Competicion save (CreateCompeticionDto competicionDto, MultipartFile file, Usuario usuario) {
        String filename = storageService.store(file);

        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(filename)
                .toUriString();

        Competicion competicion = Competicion.builder()
                .nombre(competicionDto.getNombre())
                .categoriaCompeticion(competicionDto.getCategoriaCompeticion())
                .cartel(uri)
                .fechaFin(competicionDto.getFechaFin())
                .fechaInicio(competicionDto.getFechaInicio())
                .organizador("yo")
                .sesiones(competicionDto.getSesiones())
                .provincia(competicionDto.getProvincia())
                .localidad(competicionDto.getLocalidad())
                .build();
        return repository.save(competicion);
    }

    @Override
    public Page<Competicion> findAll(Pageable pageable) {
        if(repository.findAll(pageable).isEmpty())
            throw new ListEntityNotFoundException(Competicion.class);
        else
            return repository.findAll(pageable);
    }

    @Override
    public Optional<Competicion> findById(UUID id) {return repository.findById(id);}

    @Override
    public GetCompeticionDto edit(UUID id, CreateCompeticionDto competicionDto, MultipartFile file, Usuario usuario) {
        Optional<Competicion> competicionBuscada = repository.findById(id);

        fileSystemStorageService.deleteFile(competicionBuscada.get().getCartel());
        String filename = storageService.store(file);

        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(filename)
                .toUriString();
        if (competicionBuscada.isEmpty())
            return null;
        else {
            GetCompeticionDto competicion = GetCompeticionDto.builder()
                    .id(competicionBuscada.get().getId())
                    .nombre(competicionDto.getNombre())
                    .categoriaCompeticion(competicionDto.getCategoriaCompeticion())
                    .cartel(uri)
                    .fechaFin(competicionDto.getFechaFin())
                    .fechaInicio(competicionDto.getFechaInicio())
                    .organizador("yo")
                    .localidad(competicionDto.getLocalidad())
                    .provincia(competicionDto.getProvincia())
                    .sesiones(competicionDto.getSesiones()).build();

            repository.save(competicionDtoConverter.convertCompeticionDtoToCompeticion(competicion, competicionBuscada.get()));
            return competicion;
        }
    }

    @Override
    public void deleteById(UUID id) {repository.deleteById(id);}


}
