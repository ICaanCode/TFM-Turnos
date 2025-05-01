package com.unir.turnos.application.dto;

import com.unir.turnos.infraestructure.dto.paciente.CondicionDataDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Condicion {

  private String descripcion;
  private Boolean prioritario;
  private Float multiplicador;

  public Condicion(CondicionDataDTO condicion) {
    this.descripcion = condicion.getDescripcion();
    this.prioritario = condicion.getPrioritario();
    this.multiplicador = condicion.getMultiplicador();
  }

}
