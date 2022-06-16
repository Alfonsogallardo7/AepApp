package com.salesianostriana.dam.AepApp.controller;

import com.salesianostriana.dam.AepApp.dto.competicion.CreateCompeticionDto;
import com.salesianostriana.dam.AepApp.dto.competicion.GetCompeticionDto;
import com.salesianostriana.dam.AepApp.dto.juez.CreateJuezDto;
import com.salesianostriana.dam.AepApp.dto.juez.GetJuezDto;
import com.salesianostriana.dam.AepApp.dto.juez.JuezDtoConvert;
import com.salesianostriana.dam.AepApp.models.Club;
import com.salesianostriana.dam.AepApp.models.Competicion;
import com.salesianostriana.dam.AepApp.models.Juez;
import com.salesianostriana.dam.AepApp.services.JuezService;
import com.salesianostriana.dam.AepApp.users.models.Usuario;
import com.salesianostriana.dam.AepApp.utils.paginations.PaginationsLinksUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "juez", description = "El controlador de los jueces")
public class JuezController {

    private final JuezService service;
    private final JuezDtoConvert juezDtoConvert;
    private final PaginationsLinksUtils paginationsLinksUtils;

    @Operation(summary = "Agregar un nuevo juez")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "El juez se ha creado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Juez.class))}),
            @ApiResponse (responseCode = "400",
                    description = "No se ha podido crear el juez correctamente, error sintaxtico",
                    content = @Content),
            @ApiResponse (responseCode = "403",
                    description = "Acceso denegado",
                    content = @Content)
    })
    @PostMapping(value = "/")
    public ResponseEntity<?> create (@RequestPart("newJuez") CreateJuezDto createJuezDto, Usuario usuario)throws IOException {
        Juez juez = service.save(createJuezDto, usuario);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(juezDtoConvert.convertJuezToJuezDto(juez));
    }

    @Operation(summary = "Listar todos los jueces existentes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han listado todos los jueces correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Juez.class))}),
            @ApiResponse (responseCode = "404",
                    description = "No se ha podido encontrar ningun juez",
                    content = @Content)
    })
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

    @Operation(summary = "Listar todos los detalles de un juez por su id")
    @ApiResponses(value = {
            @ApiResponse (responseCode = "200",
                    description = "Se han listado todos los detalles del juez correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema (implementation = Juez.class))}),
            @ApiResponse (responseCode = "404",
                    description = "No se ha podido encontrar ningun juez",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<GetJuezDto> findById (@PathVariable UUID id) {
        Optional<Juez> juezBuscado = service.findById(id);
        if (juezBuscado == null)
            return ResponseEntity.notFound().build();
        else return ResponseEntity.ok().body(juezDtoConvert.convertJuezToJuezDto(juezBuscado.get()));
    }

    @Operation(summary = "Se edita un juez existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha editado el juez correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Juez.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado el juez",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Los datos que se han introducido son erroneos",
                    content = @Content),
            @ApiResponse(responseCode = "403",
                    description = "Acceso denegado",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<GetJuezDto> edit (@PathVariable UUID id, @AuthenticationPrincipal Usuario usuario, @RequestPart("newJuez") CreateJuezDto juezDto) {
        GetJuezDto juezBuscado = service.edit(id, juezDto, usuario);
        if (juezBuscado == null)
            return ResponseEntity.notFound().build();
        else {
            return ResponseEntity.ok().body(juezBuscado);
        }
    }

    @Operation(summary = "Borrar un juez por su id")
    @ApiResponses(value = {
            @ApiResponse (responseCode = "204",
                    description = "Se ha borrado el juez correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema (implementation = Juez.class))}),
            @ApiResponse (responseCode = "404",
                    description = "No se ha podido encontrar ningun juez",
                    content = @Content),
            @ApiResponse (responseCode = "403",
                    description = "Acceso denegado",
                    content = @Content)
    })
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
