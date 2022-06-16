package com.salesianostriana.dam.AepApp.security;

import com.salesianostriana.dam.AepApp.security.dto.LoginDto;
import com.salesianostriana.dam.AepApp.security.jwt.JwtProvider;
import com.salesianostriana.dam.AepApp.security.jwt.JwtUserResponse;
import com.salesianostriana.dam.AepApp.users.models.UserRole;
import com.salesianostriana.dam.AepApp.users.models.Usuario;
import com.salesianostriana.dam.AepApp.users.repository.UsuarioRepository;
import io.swagger.models.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager manager;
    //private final CompeticionDtoConverter publicacionDtoConverter;
    private final JwtProvider jwtProvider;
    private final UsuarioRepository usuarioRepository;

    String jwt = "";

    @PostMapping("/auth/login")
    public ResponseEntity<?> login (@RequestBody LoginDto loginDto) {
        Authentication authentication = manager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),
                        loginDto.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        jwt = jwtProvider.generateToken(authentication);

        Usuario usuario = (Usuario) authentication.getPrincipal();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(convertUserToJwtUserResponse(usuario, jwt));
    }

    @PostMapping("/auth/admin-login")
    public ResponseEntity<?> adminLogin (@RequestBody LoginDto loginDto) {

        Usuario usuarioRegistrado = usuarioRepository.findByEmail(loginDto.getEmail()).get();

        if (usuarioRegistrado.getRole() == UserRole.ADMINISTRADOR) {
            Authentication authentication = manager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getEmail(),
                            loginDto.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            jwt = jwtProvider.generateToken(authentication);

            Usuario usuario = (Usuario) authentication.getPrincipal();

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(convertUserToJwtUserResponse(usuario, jwt));
        } else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    private JwtUserResponse convertUserToJwtUserResponse (Usuario usuario, String jwt) {
        return JwtUserResponse.builder()
                .nombre(usuario.getNombre())
                .apellidos(usuario.getApellidos())
                .email(usuario.getEmail())
                .fotoPerfil(usuario.getFotoPerfil())
                .localidad(usuario.getLocalidad())
                .username(usuario.getUsername())
                .rol(usuario.getRole().name())
                .token(jwt)
                .build();
    }

    @GetMapping("/me")
    public ResponseEntity<?> me (@AuthenticationPrincipal Usuario usuario) {
        return ResponseEntity.ok(convertUserToJwtMeResponse(usuario, jwt));
    }

    private JwtUserResponse convertUserToJwtMeResponse (Usuario usuario, String jwt) {
        return JwtUserResponse.builder()
                .nombre(usuario.getNombre())
                .apellidos(usuario.getApellidos())
                .email(usuario.getEmail())
                .fotoPerfil(usuario.getFotoPerfil())
                .fechaNacimiento(usuario.getFechaNacimieto())
                .direccion(usuario.getDireccion())
                .codigoPostal(usuario.getCodigoPostal())
                .localidad(usuario.getLocalidad())
                .username(usuario.getUsername())
                .rol(usuario.getRole().name())
                .token(jwt)
                .build();
    }
}
