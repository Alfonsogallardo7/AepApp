package com.salesianostriana.dam.AepApp.controller;

import com.salesianostriana.dam.AepApp.dto.competicion.CreateCompeticionDto;
import com.salesianostriana.dam.AepApp.dto.competidor.CompetidorDtoConvert;
import com.salesianostriana.dam.AepApp.dto.competidor.CreateCompetidorDto;
import com.salesianostriana.dam.AepApp.dto.competidor.GetCompetidorDto;
import com.salesianostriana.dam.AepApp.models.Competidor;
import com.salesianostriana.dam.AepApp.services.CompetidorService;
import com.salesianostriana.dam.AepApp.services.impl.FileSystemStorageService;
import com.salesianostriana.dam.AepApp.users.models.Usuario;
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

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/athletes")
@RequiredArgsConstructor
public class CompetidorController {

    private final FileSystemStorageService fileSystemStorageService;
    private final CompetidorService competidorService;
    private final CompetidorDtoConvert competidorDtoConvert;
    private final PaginationsLinksUtils paginationsLinksUtils;

    @PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity <?> create (@RequestPart("newCompetidor") CreateCompetidorDto createCompetidorDto, @RequestPart("file") MultipartFile file, @AuthenticationPrincipal Usuario usuario)throws IOException {
        Competidor competidor = competidorService.save(createCompetidorDto, file, usuario);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(competidorDtoConvert.convertCompetidorToCompetidorDto(competidor));
    }

    @GetMapping("/")
    public ResponseEntity<Page<GetCompetidorDto>> findAll (@PageableDefault(size = 10, page = 0)Pageable pageable, HttpServletRequest request) {
        Page<Competidor> competidor = competidorService.findAll(pageable);
        if (competidor.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            Page<GetCompetidorDto> result =
                    competidor.map(competidorDtoConvert :: convertCompetidorToCompetidorDto);

            UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());

            return ResponseEntity.ok().header("Link", paginationsLinksUtils.createLinkHeader(result, uriComponentsBuilder)).body(result);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetCompetidorDto> findById (@PathVariable UUID id) {
        Optional<Competidor> competidor = competidorService.findById(id);

        if (competidor == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok().body(competidorDtoConvert.convertCompetidorToCompetidorDto(competidor.get()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetCompetidorDto> edit (@PathVariable UUID id, @AuthenticationPrincipal Usuario usuario, @RequestPart("newCompetidor") CreateCompetidorDto competidorDto, @RequestPart("file")MultipartFile file) {
        GetCompetidorDto competidorBuscado = competidorService.edit(id, competidorDto, file, usuario);

        if (competidorBuscado == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok().body(competidorBuscado);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete (@PathVariable UUID id, @AuthenticationPrincipal Usuario usuario) {
        Optional <Competidor> competidorBuscado = competidorService.findById(id);

        if (competidorBuscado == null)
            return ResponseEntity.notFound().build();
        else {
            competidorService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
    }
}
