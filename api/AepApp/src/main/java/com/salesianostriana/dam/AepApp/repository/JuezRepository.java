package com.salesianostriana.dam.AepApp.repository;

import com.salesianostriana.dam.AepApp.models.Juez;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JuezRepository extends JpaRepository<Juez, UUID> {
}
