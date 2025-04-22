package com.unir.turnos.api.dto.servicio;

import com.unir.turnos.domain.validation.ValidUUID;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusquedaAtencionEsperaDTO {

  @NotNull(message = "Debe proporcionar el código del servicio de las atenciones a buscar.")
  @Min(value = 30001, message = "El código del servicio debe estar entre 30001 - 30999.")
  @Max(value = 30999, message = "El código del servicio debe estar entre 30001 - 30999.")
  private Integer codigoServicio;

  @NotBlank(message = "Debe proporcionar el UUID del usuario que solicita las atenciones.")
  @ValidUUID(message = "El UUID que ha proporcionado para el usuario no es válido.")
  private String usuarioId;

}
