package com.unir.turnos.infraestructure.feign;

import com.unir.turnos.infraestructure.dto.ApiResponse;
import com.unir.turnos.infraestructure.dto.servicio.AtencionDataDTO;
import com.unir.turnos.infraestructure.dto.servicio.CrearAtencionDTO;
import com.unir.turnos.infraestructure.dto.servicio.ModificacionAtencionDTO;
import com.unir.turnos.infraestructure.dto.servicio.ServicioDataDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@FeignClient(
    name = "servicios",
    configuration = FeignConfiguration.class
)
public interface ServicioServiceFeignClient {

  @GetMapping("/api/atenciones")
  ApiResponse<List<AtencionDataDTO>> obtenerAtenciones(
      @RequestParam("turnoId") UUID turnoId,
      @RequestParam("codigoServicio") Integer codigoServicio,
      @RequestParam("enEspera") Boolean enEspera,
      @RequestParam("usuarioId") UUID usuarioId,
      @RequestParam("antesDe") LocalDateTime antesDe,
      @RequestParam("despuesDe") LocalDateTime despuesDe
  );

  @PostMapping("/api/atenciones")
  ApiResponse<AtencionDataDTO> crearAtencion(@RequestBody CrearAtencionDTO atencionDTO);

  @PatchMapping("/api/atenciones/{atencionId}")
  ApiResponse<AtencionDataDTO> modificarAtencion(
      @PathVariable("atencionId") UUID atencionId,
      @RequestBody ModificacionAtencionDTO modificaciones
  );

  @GetMapping("/api/servicios")
  ApiResponse<List<ServicioDataDTO>> obtenerServicios();

}
