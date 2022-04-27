package com.salesianostriana.dam.AepApp.models;

import com.salesianostriana.dam.AepApp.users.models.Usuario;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Competicion implements Serializable {

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

    private String cartel;

    private LocalDateTime fechaInicio;

    private LocalDateTime fechaFin;

    private String organizador;

    private String categoriaCompeticion;

    private String sesiones;

    /*@Builder.Default
    @OneToMany(mappedBy = "competicion")
    private List<Usuario> listaCompetidores;

    @Builder.Default
    @OneToMany(mappedBy = "competicion")
    private List<Club> listaClubes;

    @Builder.Default
    @OneToMany(mappedBy = "competicion")
    private List<Usuario> listaJueces;*/

}