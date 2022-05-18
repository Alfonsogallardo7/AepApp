package com.salesianostriana.dam.AepApp.services.impl;

import com.salesianostriana.dam.AepApp.dto.club.ClubDtoConvert;
import com.salesianostriana.dam.AepApp.dto.club.CreateClubDto;
import com.salesianostriana.dam.AepApp.dto.club.GetClubDto;
import com.salesianostriana.dam.AepApp.errors.exceptions.ListEntityNotFoundException;
import com.salesianostriana.dam.AepApp.models.Club;
import com.salesianostriana.dam.AepApp.repository.ClubRepository;
import com.salesianostriana.dam.AepApp.services.ClubService;
import com.salesianostriana.dam.AepApp.services.StorageService;
import com.salesianostriana.dam.AepApp.users.models.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClubServiceImpl implements ClubService {

    private final ClubRepository repository;
    private final StorageService storageService;
    private final FileSystemStorageService fileSystemStorageService;
    private final ClubDtoConvert clubDtoConvert;

    @Override
    public Club save(CreateClubDto clubDto, MultipartFile file, Usuario usuario) {
        String filename = storageService.store(file);

        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(filename)
                .toUriString();

        Club club = Club.builder()
                .nombre(clubDto.getNombre())
                .abreviatura(clubDto.getAbreviatura())
                .nif(clubDto.getNif())
                .direccionSede(clubDto.getDireccionSede())
                .email(clubDto.getEmail())
                .foto(uri).build();

        return repository.save(club);
    }

    @Override
    public Page<Club> findAll(Pageable pageable) {
        if(repository.findAll(pageable).isEmpty())
            throw new ListEntityNotFoundException(Club.class);
        else
            return repository.findAll(pageable);
    }

    @Override
    public Optional<Club> findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public GetClubDto edit(UUID id, CreateClubDto createClubDto, MultipartFile file, Usuario usuario) {
        Optional<Club> clubBuscado = repository.findById(id);

        fileSystemStorageService.deleteFile(clubBuscado.get().getFoto());
        String filename = storageService.store(file);

        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(filename)
                .toUriString();
        if (clubBuscado.isEmpty())
            return null;
        else {
            GetClubDto club = GetClubDto.builder()
                    .id(clubBuscado.get().getId())
                    .nombre(createClubDto.getNombre())
                    .abreviatura(createClubDto.getAbreviatura())
                    .nif(createClubDto.getNif())
                    .direccionSede(createClubDto.getDireccionSede())
                    .email(createClubDto.getEmail())
                    .foto(uri).build();

            repository.save(clubDtoConvert.convertClubDtoToClub(club, clubBuscado.get()));
            return club;
        }
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
