package com.unir.turnos.api.dto.turno;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusquedaTurnoDTO {

  private String codigoTurno;
  private String documentoPaciente;
  private Integer codigoEstado;
  private LocalDateTime antesDe;
  private LocalDateTime despuesDe;

}
