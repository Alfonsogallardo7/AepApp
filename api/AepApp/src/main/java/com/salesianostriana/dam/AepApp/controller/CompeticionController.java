package com.salesianostriana.dam.AepApp.controller;

import com.salesianostriana.dam.AepApp.dto.competicion.CompeticionDtoConverter;
import com.salesianostriana.dam.AepApp.dto.competicion.CreateCompeticionDto;
import com.salesianostriana.dam.AepApp.dto.competicion.GetCompeticionDto;
import com.salesianostriana.dam.AepApp.models.Competicion;
import com.salesianostriana.dam.AepApp.services.CompeticionService;
import com.salesianostriana.dam.AepApp.services.impl.FileSystemStorageService;
import com.salesianostriana.dam.AepApp.users.models.Usuario;
import com.salesianostriana.dam.AepApp.utils.MediaTypeUrlResource;
import com.salesianostriana.dam.AepApp.utils.paginations.PaginationsLinksUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/championships")
@RequiredArgsConstructor
public class CompeticionController {

    private final FileSystemStorageService fileSystemStorageService;
    private final CompeticionService service;
    private final CompeticionDtoConverter competicionDtoConverter;
    private final PaginationsLinksUtils paginationsLinksUtils;

    @PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity <?> create (@RequestPart("newCompeticion")CreateCompeticionDto createCompeticionDto, @RequestPart("file")MultipartFile file, @AuthenticationPrincipal Usuario usuario)throws IOException {
        Competicion competicion = service.save(createCompeticionDto, file, usuario);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(competicionDtoConverter.convertComepticionToCompeticionDto(competicion));
    }

    @GetMapping("/")
    public ResponseEntity<Page<GetCompeticionDto>> findAll (@PageableDefault(size = 10, page = 0)Pageable pageable, HttpServletRequest request) {
        Page<Competicion> competicion = service.findAll(pageable);

        if (competicion.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {

            Page<GetCompeticionDto> result =
                    competicion.map(competicionDtoConverter :: convertComepticionToCompeticionDto);

            UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());

            return ResponseEntity.ok().header( "Link",
                            paginationsLinksUtils.createLinkHeader(result, uriComponentsBuilder))
                    .body(result);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetCompeticionDto> findById (@PathVariable UUID id) {
        Optional<Competicion> competicionBuscada = service.findById(id);
        if (competicionBuscada == null)
            return ResponseEntity.notFound().build();
        else return ResponseEntity.ok().body(competicionDtoConverter.convertComepticionToCompeticionDto(competicionBuscada.get()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetCompeticionDto> edit (@PathVariable UUID id, @AuthenticationPrincipal Usuario usuario, @RequestPart("newCompeticion") CreateCompeticionDto competicionDto, @RequestPart("file") MultipartFile file) {
        GetCompeticionDto competicionBuscada = service.edit(id, competicionDto, file, usuario);
        if (competicionBuscada == null)
            return ResponseEntity.notFound().build();
        else {
            return ResponseEntity.ok().body(competicionBuscada);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete (@PathVariable UUID id, @AuthenticationPrincipal Usuario usuario) {
        Optional<Competicion> competicionBuscada = service.findById(id);

        if (competicionBuscada.isEmpty())
            return ResponseEntity.notFound().build();
        else {
            fileSystemStorageService.deleteFile(competicionBuscada.get().getCartel());
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        }

    }
}
