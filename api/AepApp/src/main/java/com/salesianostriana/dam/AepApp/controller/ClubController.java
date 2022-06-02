package com.salesianostriana.dam.AepApp.controller;

import com.salesianostriana.dam.AepApp.dto.club.ClubDtoConvert;
import com.salesianostriana.dam.AepApp.dto.club.CreateClubDto;
import com.salesianostriana.dam.AepApp.dto.club.GetClubDto;

import com.salesianostriana.dam.AepApp.dto.competicion.CreateCompeticionDto;
import com.salesianostriana.dam.AepApp.dto.competicion.GetCompeticionDto;
import com.salesianostriana.dam.AepApp.models.Club;
import com.salesianostriana.dam.AepApp.models.Competicion;
import com.salesianostriana.dam.AepApp.services.ClubService;
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

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/clubs")
@RequiredArgsConstructor
public class ClubController {

    private final FileSystemStorageService fileSystemStorageService;
    private final ClubService service;
    private final ClubDtoConvert clubDtoConvert;
    private final PaginationsLinksUtils paginationsLinksUtils;

    @PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> create (@RequestPart("newClub") CreateClubDto createClubDto, @RequestPart("file") MultipartFile file, @AuthenticationPrincipal Usuario usuario)throws IOException {
        Club club = service.save(createClubDto, file, usuario);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(clubDtoConvert.convertClubToClubDto(club));
    }

    @GetMapping("/")
    public ResponseEntity<Page<GetClubDto>> findAll (@PageableDefault(size = 10, page = 0) Pageable pageable, HttpServletRequest request) {
        Page<Club> club = service.findAll(pageable);

        if (club.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {

            Page<GetClubDto> result =
                    club.map(clubDtoConvert :: convertClubToClubDto);

            UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());

            return ResponseEntity.ok().header( "Link",
                            paginationsLinksUtils.createLinkHeader(result, uriComponentsBuilder))
                    .body(result);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetClubDto> findById (@PathVariable UUID id) {
        Optional<Club> clubBuscada = service.findById(id);
        if (clubBuscada == null)
            return ResponseEntity.notFound().build();
        else return ResponseEntity.ok().body(clubDtoConvert.convertClubToClubDto(clubBuscada.get()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetClubDto> edit (@PathVariable UUID id, @AuthenticationPrincipal Usuario usuario, @RequestPart("newClub") CreateClubDto clubDto, @RequestPart("file") MultipartFile file) {
        GetClubDto clubBuscada = service.edit(id, clubDto, file, usuario);
        if (clubBuscada == null)
            return ResponseEntity.notFound().build();
        else {
            return ResponseEntity.ok().body(clubBuscada);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete (@PathVariable UUID id, @AuthenticationPrincipal Usuario usuario) {
        Optional<Club> clubBuscada = service.findById(id);

        if (clubBuscada.isEmpty())
            return ResponseEntity.notFound().build();
        else {
            fileSystemStorageService.deleteFile(clubBuscada.get().getFoto());
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        }

    }

}
