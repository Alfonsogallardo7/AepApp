package com.salesianostriana.dam.AepApp.repository;

import com.salesianostriana.dam.AepApp.models.Competicion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CompeticionRepository extends JpaRepository<Competicion, UUID> {
}
