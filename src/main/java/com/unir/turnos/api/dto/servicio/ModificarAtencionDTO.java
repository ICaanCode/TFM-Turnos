package com.unir.turnos.api.dto.servicio;

import com.unir.turnos.domain.validation.ValidUUID;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModificarAtencionDTO {

  @NotBlank(message = "Debe proporcionar el UUID del usuario que modifica el turno.")
  @ValidUUID(message = "El UUID que ha proporcionado para el usuario no es válido.")
  private String usuarioId;

  private Boolean abandonada = false;

  @Min(value = 30001, message = "El código del servicio debe estar entre 30001 - 30999.")
  @Max(value = 30999, message = "El código del servicio debe estar entre 30001 - 30999.")
  private Integer codigoServicio;

  @Min(value = 1, message = "El valor de la prioridad debe ser mayor o igual a 1.")
  @Max(value = 5, message = "El valor de la prioridad debe ser menor o igual a 5.")
  private Integer prioridad = 5;

}
