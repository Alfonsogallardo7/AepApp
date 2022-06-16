package com.salesianostriana.dam.AepApp.controller;

import com.salesianostriana.dam.AepApp.dto.competicion.CreateCompeticionDto;
import com.salesianostriana.dam.AepApp.dto.competidor.CompetidorDtoConvert;
import com.salesianostriana.dam.AepApp.dto.competidor.CreateCompetidorDto;
import com.salesianostriana.dam.AepApp.dto.competidor.GetCompetidorDto;
import com.salesianostriana.dam.AepApp.models.Club;
import com.salesianostriana.dam.AepApp.models.Competicion;
import com.salesianostriana.dam.AepApp.models.Competidor;
import com.salesianostriana.dam.AepApp.services.CompetidorService;
import com.salesianostriana.dam.AepApp.services.impl.FileSystemStorageService;
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

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/athletes")
@RequiredArgsConstructor
@Tag(name = "competidor", description = "El controlador de los competidores")
public class CompetidorController {

    private final FileSystemStorageService fileSystemStorageService;
    private final CompetidorService competidorService;
    private final CompetidorDtoConvert competidorDtoConvert;
    private final PaginationsLinksUtils paginationsLinksUtils;


    @Operation(summary = "Agregar un nuevo competidor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "El competidor se ha creado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Competidor.class))}),
            @ApiResponse (responseCode = "400",
                    description = "No se ha podido crear el competidor correctamente, error sintaxtico",
                    content = @Content),
            @ApiResponse (responseCode = "403",
                    description = "Acceso denegado",
                    content = @Content)
    })
    @PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity <?> create (@RequestPart("newCompetidor") CreateCompetidorDto createCompetidorDto, @RequestPart("file") MultipartFile file, @AuthenticationPrincipal Usuario usuario)throws IOException {
        Competidor competidor = competidorService.save(createCompetidorDto, file, usuario);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(competidorDtoConvert.convertCompetidorToCompetidorDto(competidor));
    }


    @Operation(summary = "Listar todos los competidores existentes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han listado todos los competidores correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Competidor.class))}),
            @ApiResponse (responseCode = "404",
                    description = "No se ha podido encontrar ningun competidor",
                    content = @Content)
    })
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

    @Operation(summary = "Listar todos los detalles de un competidor por su id")
    @ApiResponses(value = {
            @ApiResponse (responseCode = "200",
                    description = "Se han listado todos los detalles del competidor correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema (implementation = Competidor.class))}),
            @ApiResponse (responseCode = "404",
                    description = "No se ha podido encontrar ningun competidor",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<GetCompetidorDto> findById (@PathVariable UUID id) {
        Optional<Competidor> competidor = competidorService.findById(id);

        if (competidor == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok().body(competidorDtoConvert.convertCompetidorToCompetidorDto(competidor.get()));
    }

    @Operation(summary = "Se edita un competidor existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha editado el competidor correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Competidor.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado el competidor",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Los datos que se han introducido son erroneos",
                    content = @Content),
            @ApiResponse(responseCode = "403",
                    description = "Acceso denegado",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<GetCompetidorDto> edit (@PathVariable UUID id, @AuthenticationPrincipal Usuario usuario, @RequestPart("newCompetidor") CreateCompetidorDto competidorDto, @RequestPart("file")MultipartFile file) {
        GetCompetidorDto competidorBuscado = competidorService.edit(id, competidorDto, file, usuario);

        if (competidorBuscado == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok().body(competidorBuscado);
    }

    @Operation(summary = "Borrar un competidor por su id")
    @ApiResponses(value = {
            @ApiResponse (responseCode = "204",
                    description = "Se ha borrado el competidor correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema (implementation = Competidor.class))}),
            @ApiResponse (responseCode = "404",
                    description = "No se ha podido encontrar ningun competidor",
                    content = @Content),
            @ApiResponse (responseCode = "403",
                    description = "Acceso denegado",
                    content = @Content)
    })
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
