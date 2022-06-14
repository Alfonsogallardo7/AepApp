package com.salesianostriana.dam.AepApp.services.impl;

import com.salesianostriana.dam.AepApp.dto.competidor.CompetidorDtoConvert;
import com.salesianostriana.dam.AepApp.dto.competidor.CreateCompetidorDto;
import com.salesianostriana.dam.AepApp.dto.competidor.GetCompetidorDto;
import com.salesianostriana.dam.AepApp.errors.exceptions.ListEntityNotFoundException;
import com.salesianostriana.dam.AepApp.models.Competicion;
import com.salesianostriana.dam.AepApp.models.Competidor;
import com.salesianostriana.dam.AepApp.repository.CompetidorRepository;
import com.salesianostriana.dam.AepApp.services.CompetidorService;
import com.salesianostriana.dam.AepApp.services.StorageService;
import com.salesianostriana.dam.AepApp.users.models.Usuario;
import lombok.RequiredArgsConstructor;
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
public class CompetidorServiceImpl implements CompetidorService {

    private final CompetidorRepository competidorRepository;
    private final StorageService storageService;
    private final FileSystemStorageService fileSystemStorageService;
    private final CompetidorDtoConvert competidorDtoConvert;
    private GetCompetidorDto build;

    @Override
    public Competidor save(CreateCompetidorDto competidorDto, MultipartFile file, Usuario usuario) {
        String filename = storageService.store(file);

        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(filename)
                .toUriString();

        Competidor competidor = Competidor.builder()
                .nombre(competidorDto.getNombre())
                .apellidos(competidorDto.getApellidos())
                .fechaNacimiento(competidorDto.getFechaNacimiento())
                .categoriaPeso(competidorDto.getCategoriaPeso())
                .foto(uri)
                .marcasSq(competidorDto.getMarcasSq())
                .marcasBp(competidorDto.getMarcasBp())
                .marcasDl(competidorDto.getMarcasDl())
                .club(competidorDto.getClub())
                .build();

        return competidorRepository.save(competidor);
    }

    @Override
    public Page<Competidor> findAll(Pageable pageable) {
        if (competidorRepository.findAll(pageable).isEmpty())
            throw new ListEntityNotFoundException(Competidor.class);
        else
            return competidorRepository.findAll(pageable);
    }

    @Override
    public Optional<Competidor> findById(UUID id) {
        return competidorRepository.findById(id);
    }

    @Override
    public GetCompetidorDto edit(UUID id, CreateCompetidorDto createCompetidorDto, MultipartFile file, Usuario usuario) {
        Optional<Competidor> competidorBuscado = competidorRepository.findById(id);

        fileSystemStorageService.deleteFile(competidorBuscado.get().getFoto());
        String filename = storageService.store(file);

        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(filename)
                .toUriString();
        if (competidorBuscado.isEmpty())
            return null;
        else {
           GetCompetidorDto competidorDto = build;
            
            
            competidorRepository.save(competidorDtoConvert.convertCompetidorDtoToCompetidor(competidorDto, competidorBuscado.get()));
            return competidorDto;
            /*return competidorBuscado.map(competidor -> {
                competidor.setNombre(createCompetidorDto.getNombre());
                competidor.setApellidos(createCompetidorDto.getApellidos());
                competidor.setFechaNacimiento(createCompetidorDto.getFechaNacimiento());
                competidor.setCategoriaPeso(createCompetidorDto.getCategoriaPeso());
                competidor.setFoto(createCompetidorDto.getFoto());
                competidor.setMarcasSq(createCompetidorDto.getMarcasSq());
                competidor.setMarcasBp(createCompetidorDto.getMarcasBp());
                competidor.setMarcasDl(createCompetidorDto.getMarcasDl());
                competidor.setClub(createCompetidorDto.getClub());
                
                competidorRepository.save()
            });*/
        }
    }

    @Override
    public void deleteById(UUID id) {
        competidorRepository.deleteById(id);

    }
}
