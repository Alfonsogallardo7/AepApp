package com.salesianostriana.dam.AepApp.models;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Competidor implements Serializable {

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

    private LocalDate fechaNacimiento;

    private String categoriaPeso;

    private String foto;

    private Double marcasSq;

    private Double marcasBp;

    private Double marcasDl;

    private String club;


    /*@Builder.Default
    @OneToMany(mappedBy = "competidor")
    private List<Competicion> listaCompeticiones = new ArrayList<>();*/

}
