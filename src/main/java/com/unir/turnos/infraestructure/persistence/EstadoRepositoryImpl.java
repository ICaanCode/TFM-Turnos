package com.unir.turnos.infraestructure.persistence;

import com.unir.turnos.domain.model.Estado;
import com.unir.turnos.domain.repository.EstadoJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EstadoRepositoryImpl {

  private final EstadoJpaRepository repository;

  public Estado obtenerEstadoPorCodigo(Integer codigo) {
    return repository
        .findByCodigo(codigo)
        .orElseThrow(() -> new EntityNotFoundException(String.format("Estado con código '%d' no encontrado", codigo)));
  }

}
