package com.salesianostriana.dam.AepApp.users.models;
import com.salesianostriana.dam.AepApp.models.Club;
import com.salesianostriana.dam.AepApp.models.Competicion;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Builder
@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Usuario implements Serializable, UserDetails {

    @GeneratedValue(generator = "UUID")
    @Id
    @GenericGenerator(name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {
                    @Parameter(
                            name = "uuid_gen_strategy_class",
                            value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
                    )}
    )
    private UUID id;

    private String nombre;

    private String apellidos;

    private String email;

    private String username;

    private String password;

    private String fotoPerfil;

    private String dni;

    private LocalDate fechaNacimieto;

    private String direccion;

    private String codigoPostal;

    private String localidad;

    private String telefono;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @ManyToOne
    @MapsId("club_id")
    private Club club;

    /*@Builder.Default
    @OneToMany(mappedBy = "competicion")
    private List<Competicion> listaCompeticiones;*/

    /*
     ***** Metodos de UserDetails *****
     */

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}


