package com.unir.turnos.domain.repository;

import com.unir.turnos.domain.model.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface TurnoJpaRepository extends JpaRepository<Turno, UUID>, JpaSpecificationExecutor<Turno> {}
