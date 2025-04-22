package com.unir.turnos.api.dto.turno;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CrearTurnoDTO {

  @NotBlank(message = "Debe proporcionar un número de documento para el paciente")
  private String documentoPaciente;

  @Min(value = 1, message = "El valor de la prioridad debe ser mayor o igual a 1.")
  @Max(value = 5, message = "El valor de la prioridad debe ser menor o igual a 5.")
  private Integer prioridad = 5;

  @Min(value = 30001, message = "El valor del código del servicio debe ser mayor o igual a 30001.")
  @Max(value = 30999, message = "El valor del código del servicio debe ser menor o igual a 30999.")
  private Integer codigoServicio = 30001;

}
