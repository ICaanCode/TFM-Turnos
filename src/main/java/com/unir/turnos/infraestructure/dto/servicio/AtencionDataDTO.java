package com.unir.turnos.infraestructure.dto.servicio;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class AtencionDataDTO {

  private UUID idAtencion;
  private UUID turnoId;
  private UUID usuarioId;
  private Integer servicio;
  private Integer prioridad;
  private LocalDateTime fechaCreacion;
  private LocalDateTime fechaInicio;
  private LocalDateTime fechaFinalizacion;

}
