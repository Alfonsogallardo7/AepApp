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
import java.util.List;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Club implements Serializable {

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

    private String abreviatura;

    private String nif;

    private String direccionSede;

    private String email;

    /*@Builder.Default
    @OneToMany(mappedBy = "club")
    private List<Usuario> listaCompetidores;

    @Builder.Default
    @OneToMany(mappedBy = "club")
    private List<Competicion> listaCompeticiones;*/
}
