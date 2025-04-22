package com.unir.turnos.api.dto.turno;

import com.unir.turnos.api.dto.paciente.PacienteDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TurnoDTO {

  private String codigo;
  private PacienteDTO paciente;
  private Integer estado;
  private LocalDateTime fechaCreacion;
  private LocalDateTime fechaFinalizacion;

}
