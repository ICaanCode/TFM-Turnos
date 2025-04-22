package com.unir.turnos.infraestructure.dto.paciente;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CondicionDataDTO {

  private Integer codigo;
  private String descripcion;
  private Boolean prioritario;
  private Float multiplicador;

}
