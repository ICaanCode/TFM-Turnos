package com.unir.turnos.api.dto.servicio;

import com.unir.turnos.domain.validation.ValidUUID;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusquedaAtencionEsperaDTO {

  @Min(value = 30001, message = "El código del servicio debe estar entre 30001 - 30999.")
  @Max(value = 30999, message = "El código del servicio debe estar entre 30001 - 30999.")
  private Integer codigoServicio;

  @ValidUUID(message = "El UUID que ha proporcionado para el usuario no es válido.")
  private String usuarioId;

}
