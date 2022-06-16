package com.salesianostriana.dam.AepApp.models;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Juez implements Serializable{

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

    private String tipoJuez;

    /*@Builder.Default
    @OneToMany(mappedBy = "juez")
    private List<Competicion> listaCompeticiones;*/
}
