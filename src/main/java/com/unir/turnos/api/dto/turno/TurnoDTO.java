package com.unir.turnos.api.dto.turno;

import com.unir.turnos.api.dto.paciente.PacienteDTO;
import com.unir.turnos.domain.model.Turno;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TurnoDTO {

  private UUID turnoId;
  private String codigo;
  private PacienteDTO paciente;
  private Integer estado;
  private LocalDateTime fechaCreacion;
  private LocalDateTime fechaFinalizacion;

  public TurnoDTO(Turno turno, PacienteDTO paciente) {
    this.turnoId = turno.getIdTurno();
    this.codigo = turno.getCodigo();
    this.paciente = paciente;
    this.estado = turno.getEstado().getCodigo();
    this.fechaCreacion = turno.getFechaCreacion();
    this.fechaFinalizacion = turno.getFechaFinalizacion();
  }

}
