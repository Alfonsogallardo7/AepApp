package com.salesianostriana.dam.AepApp.controller;

import com.salesianostriana.dam.AepApp.dto.competicion.CreateCompeticionDto;
import com.salesianostriana.dam.AepApp.dto.competicion.GetCompeticionDto;
import com.salesianostriana.dam.AepApp.dto.juez.CreateJuezDto;
import com.salesianostriana.dam.AepApp.dto.juez.GetJuezDto;
import com.salesianostriana.dam.AepApp.dto.juez.JuezDtoConvert;
import com.salesianostriana.dam.AepApp.models.Competicion;
import com.salesianostriana.dam.AepApp.models.Juez;
import com.salesianostriana.dam.AepApp.services.JuezService;
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

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/judges")
@RequiredArgsConstructor
public class JuezController {

    private final JuezService service;
    private final JuezDtoConvert juezDtoConvert;
    private final PaginationsLinksUtils paginationsLinksUtils;

    @PostMapping(value = "/")
    public ResponseEntity<?> create (@RequestPart("newJuez") CreateJuezDto createJuezDto, Usuario usuario)throws IOException {
        Juez juez = service.save(createJuezDto, usuario);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(juezDtoConvert.convertJuezToJuezDto(juez));
    }

    @GetMapping("/")
    public ResponseEntity<Page<GetJuezDto>> findAll (@PageableDefault(size = 10, page = 0) Pageable pageable, HttpServletRequest request) {
        Page<Juez> jueces = service.findAll(pageable);

        if (jueces.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {

            Page<GetJuezDto> result =
                    jueces.map(juezDtoConvert :: convertJuezToJuezDto);

            UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());

            return ResponseEntity.ok().header( "Link",
                            paginationsLinksUtils.createLinkHeader(result, uriComponentsBuilder))
                    .body(result);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetJuezDto> findById (@PathVariable UUID id) {
        Optional<Juez> juezBuscado = service.findById(id);
        if (juezBuscado == null)
            return ResponseEntity.notFound().build();
        else return ResponseEntity.ok().body(juezDtoConvert.convertJuezToJuezDto(juezBuscado.get()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetJuezDto> edit (@PathVariable UUID id, @AuthenticationPrincipal Usuario usuario, @RequestPart("newJuez") CreateJuezDto juezDto) {
        GetJuezDto juezBuscado = service.edit(id, juezDto, usuario);
        if (juezBuscado == null)
            return ResponseEntity.notFound().build();
        else {
            return ResponseEntity.ok().body(juezBuscado);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete (@PathVariable UUID id, @AuthenticationPrincipal Usuario usuario) {
        Optional<Juez> juezBuscado = service.findById(id);

        if (juezBuscado.isEmpty())
            return ResponseEntity.notFound().build();
        else {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        }

    }
}
