package com.unir.turnos.application.dto;

import com.unir.turnos.infraestructure.dto.servicio.ServicioDataDTO;
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

  public Servicio(ServicioDataDTO servicioDataDTO) {
    this.nombre = servicioDataDTO.getNombre();
    this.descripcion = servicioDataDTO.getDescripcion();
    this.activo = servicioDataDTO.getActivo();
  }

}
