package com.unir.turnos.infraestructure.persistence;

import com.unir.turnos.domain.model.Estado;
import com.unir.turnos.domain.model.Turno;
import com.unir.turnos.domain.repository.TurnoJpaRepository;
import com.unir.turnos.infraestructure.utils.SearchCriteria;
import com.unir.turnos.infraestructure.utils.SearchOperation;
import com.unir.turnos.infraestructure.utils.SearchStatement;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class TurnoRepositoryImpl {

  private final TurnoJpaRepository repository;

  public Turno obtenerTurnoPorUUID(UUID turnoUUID) {
    return repository.findById(turnoUUID).orElseThrow(() -> new EntityNotFoundException(String.format("Turno con UUID '%s' no encontrado.", turnoUUID)));
  }

  public List<Turno> buscarTurnos(String codigo, UUID pacienteId, Estado estado, LocalDateTime antesDe, LocalDateTime despuesDe) {
    SearchCriteria<Turno> especificacion = new SearchCriteria<>();
    if (StringUtils.isNotBlank(codigo)) {
      especificacion.add(new SearchStatement("codigo", codigo, SearchOperation.EQUAL));
    }
    if (pacienteId != null) {
      especificacion.add(new SearchStatement("pacienteId", pacienteId, SearchOperation.EQUAL));
    }
    if (estado != null) {
      especificacion.add(new SearchStatement("estado", estado, SearchOperation.EQUAL));
    }
    if (antesDe != null) {
      especificacion.add(new SearchStatement("fechaCreacion", antesDe, SearchOperation.LESS_THAN_EQUAL));
    }
    if (despuesDe != null) {
      especificacion.add(new SearchStatement("fechaCreacion", despuesDe, SearchOperation.GREATER_THAN_EQUAL));
    }
    return repository.findAll(especificacion);
  }

  public Turno guardarTurno(Turno turno) {
    return repository.save(turno);
  }

}
