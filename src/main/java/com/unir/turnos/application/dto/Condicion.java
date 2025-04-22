package com.unir.turnos.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Condicion {

  private String descripcion;
  private Boolean prioritario;
  private Float multiplicador;

}
