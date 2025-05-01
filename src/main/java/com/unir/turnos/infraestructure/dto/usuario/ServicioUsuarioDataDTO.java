package com.unir.turnos.infraestructure.dto.usuario;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ServicioUsuarioDataDTO {

  private Integer codigoServicio;
  private Boolean activo;
  private LocalDateTime fechaCreacion;
  private LocalDateTime fechaModificacion;

}
