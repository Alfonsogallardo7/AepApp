package com.salesianostriana.dam.AepApp.services.impl;

import com.salesianostriana.dam.AepApp.dto.competicion.CompeticionDtoConverter;
import com.salesianostriana.dam.AepApp.dto.competicion.CreateCompeticionDto;
import com.salesianostriana.dam.AepApp.dto.competicion.GetCompeticionDto;
import com.salesianostriana.dam.AepApp.dto.juez.CreateJuezDto;
import com.salesianostriana.dam.AepApp.dto.juez.GetJuezDto;
import com.salesianostriana.dam.AepApp.dto.juez.JuezDtoConvert;
import com.salesianostriana.dam.AepApp.errors.exceptions.ListEntityNotFoundException;
import com.salesianostriana.dam.AepApp.models.Competicion;
import com.salesianostriana.dam.AepApp.models.Juez;
import com.salesianostriana.dam.AepApp.repository.JuezRepository;
import com.salesianostriana.dam.AepApp.services.JuezService;
import com.salesianostriana.dam.AepApp.services.StorageService;
import com.salesianostriana.dam.AepApp.users.models.Usuario;
import com.salesianostriana.dam.AepApp.users.repository.UsuarioRepository;
import com.salesianostriana.dam.AepApp.utils.paginations.PaginationsLinksUtils;
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
public class JuezServiceImpl implements JuezService {

    private final JuezRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final StorageService storageService;
    private final FileSystemStorageService fileSystemStorageService;
    private final JuezDtoConvert juezDtoConvert;
    private final PaginationsLinksUtils paginationsLinksUtils;


    @Override
    public Juez save (CreateJuezDto juezDto, Usuario usuario) {
        Juez juez = Juez.builder()
                .nombre(juezDto.getNombre())
                .apellidos(juezDto.getApellidos())
                .tipoJuez(juezDto.getTipoJuez())
                .build();
        return repository.save(juez);
    }

    @Override
    public Page<Juez> findAll(Pageable pageable) {
        if(repository.findAll(pageable).isEmpty())
            throw new ListEntityNotFoundException(Juez.class);
        else
            return repository.findAll(pageable);
    }

    @Override
    public Optional<Juez> findById(UUID id) {return repository.findById(id);}

    @Override
    public void deleteById(UUID id) {repository.deleteById(id);}

    @Override
    public GetJuezDto edit(UUID id, CreateJuezDto juezDto, Usuario usuario) {
        Optional<Juez> juezBuscado = repository.findById(id);

        if (juezBuscado.isEmpty())
            return null;
        else {
            GetJuezDto juez = GetJuezDto.builder()
                    .id(juezBuscado.get().getId())
                    .nombre(juezDto.getNombre())
                    .apellidos(juezDto.getApellidos())
                    .tipoJuez(juezDto.getTipoJuez())
                    .build();

            repository.save(juezDtoConvert.convertJuezDtoToJuez(juez, juezBuscado.get()));
            return juez;
        }
    }
}
