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
@RequestMapping("/championships")
@RequiredArgsConstructor
@Tag(name = "competicion", description = "El controlador de las competiciones")
public class CompeticionController {

    private final FileSystemStorageService fileSystemStorageService;
    private final CompeticionService service;
    private final CompeticionDtoConverter competicionDtoConverter;
    private final PaginationsLinksUtils paginationsLinksUtils;

    @Operation(summary = "Agregar una nueva competicion")
    @ApiResponses(value = {
            @ApiResponse (responseCode = "201",
                    description = "La competicion se ha creado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema (implementation = Competicion.class))}),
            @ApiResponse (responseCode = "400",
                    description = "No se ha podido crear la competicion correctamente, error sintaxtico",
                    content = @Content),
            @ApiResponse (responseCode = "403",
                    description = "Acceso denegado",
                    content = @Content)
    })
    @PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity <?> create (@RequestPart("newCompeticion")CreateCompeticionDto createCompeticionDto, @RequestPart("file")MultipartFile file, @AuthenticationPrincipal Usuario usuario)throws IOException {
        Competicion competicion = service.save(createCompeticionDto, file, usuario);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(competicionDtoConverter.convertComepticionToCompeticionDto(competicion));
    }

    @Operation(summary = "Listar todas las competiciones existentes")
    @ApiResponses(value = {
            @ApiResponse (responseCode = "200",
                    description = "Se han listado todas las competiciones correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema (implementation = Competicion.class))}),
            @ApiResponse (responseCode = "404",
                    description = "No se ha podido encontrar ninguna competicion",
                    content = @Content)
    })
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

    @Operation(summary = "Listar todos los detalles de una competicion por su id")
    @ApiResponses(value = {
            @ApiResponse (responseCode = "200",
                    description = "Se han listado todos los detalles de la competicion correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema (implementation = Competicion.class))}),
            @ApiResponse (responseCode = "404",
                    description = "No se ha podido encontrar ninguna competicion",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<GetCompeticionDto> findById (@PathVariable UUID id) {
        Optional<Competicion> competicionBuscada = service.findById(id);
        if (competicionBuscada == null)
            return ResponseEntity.notFound().build();
        else return ResponseEntity.ok().body(competicionDtoConverter.convertComepticionToCompeticionDto(competicionBuscada.get()));
    }

    @Operation(summary = "Se edita una competicion existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha editado la competicion correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Competicion.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado la competicion",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Los datos que se han introducido son erroneos",
                    content = @Content),
            @ApiResponse(responseCode = "403",
                    description = "Acceso denegado",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<GetCompeticionDto> edit (@PathVariable UUID id, @AuthenticationPrincipal Usuario usuario, @RequestPart("newCompeticion") CreateCompeticionDto competicionDto, @RequestPart("file") MultipartFile file) {
        GetCompeticionDto competicionBuscada = service.edit(id, competicionDto, file, usuario);
        if (competicionBuscada == null)
            return ResponseEntity.notFound().build();
        else {
            return ResponseEntity.ok().body(competicionBuscada);
        }
    }

    @Operation(summary = "Borrar una competicion por su id")
    @ApiResponses(value = {
            @ApiResponse (responseCode = "204",
                    description = "Se ha borrado la competicion correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema (implementation = Competicion.class))}),
            @ApiResponse (responseCode = "404",
                    description = "No se ha podido encontrar ninguna competicion",
                    content = @Content),
            @ApiResponse (responseCode = "403",
                    description = "Acceso denegado",
                    content = @Content)
    })
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
