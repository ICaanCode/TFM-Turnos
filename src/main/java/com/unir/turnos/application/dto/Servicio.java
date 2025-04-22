package com.unir.turnos.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Servicio {

  private String nombre;
  private String descripcion;
  private Boolean activo;

}
