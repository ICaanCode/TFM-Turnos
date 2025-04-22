package com.unir.turnos.infraestructure.dto.servicio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServicioDataDTO {

  private Integer codigo;
  private String nombre;
  private String descripcion;
  private Boolean activo;

}
