package com.salesianostriana.dam.AepApp.repository;

import com.salesianostriana.dam.AepApp.models.Competidor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CompetidorRepository extends JpaRepository<Competidor, UUID> {
}
