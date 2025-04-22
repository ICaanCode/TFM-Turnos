package com.unir.turnos.infraestructure.dto.servicio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CrearAtencionDTO {

  private UUID turnoId;
  private UUID usuarioId;
  private Integer servicioId;
  private Integer prioridad;

}
