package com.unir.turnos.api.controller;

import com.unir.turnos.api.dto.servicio.AtencionDTO;
import com.unir.turnos.api.dto.servicio.BusquedaAtencionEsperaDTO;
import com.unir.turnos.api.dto.servicio.ModificarAtencionDTO;
import com.unir.turnos.api.dto.turno.BusquedaTurnoDTO;
import com.unir.turnos.api.dto.turno.CrearTurnoDTO;
import com.unir.turnos.api.dto.turno.TurnoDTO;
import com.unir.turnos.api.response.ApiResponse;
import com.unir.turnos.application.usecases.AtencionUseCase;
import com.unir.turnos.application.usecases.TurnoUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/turnos")
@Validated
public class TurnoController {

  private final AtencionUseCase atencionUseCase;
  private final TurnoUseCase turnoUseCase;

  @GetMapping
  public ResponseEntity<Map<String, Object>> obtenerTurnos(@Valid BusquedaTurnoDTO busquedaTurno
  ) {
    List<TurnoDTO> turnos = turnoUseCase.obtenerTurno(busquedaTurno);

    if (turnos != null && !turnos.isEmpty()) {
      return ApiResponse.success(turnos, HttpStatus.OK);
    } else {
      return ApiResponse.success(Collections.emptyList(), HttpStatus.OK);
    }

  }

  @GetMapping("/atenciones")
  public ResponseEntity<Map<String, Object>> obtenerAtencionesEnEspera(@Valid BusquedaAtencionEsperaDTO busquedaAtencionEspera) {
    Boolean usuarioIdNulo = busquedaAtencionEspera.getUsuarioId() == null;
    Boolean codigoServicioNulo = busquedaAtencionEspera.getCodigoServicio() == null;
    if (usuarioIdNulo && codigoServicioNulo) {
      List<AtencionDTO> atencionesEnProgreso = atencionUseCase.obtenerAtencionesEnProgreso();
      return ApiResponse.success(atencionesEnProgreso, HttpStatus.OK);
    }
    if (!usuarioIdNulo && !codigoServicioNulo) {
      List<AtencionDTO> atencionesEnEspera = atencionUseCase.obtenerAtenciones(busquedaAtencionEspera);
      return ApiResponse.success(atencionesEnEspera, HttpStatus.OK);
    }
    throw new IllegalArgumentException("Debe especificar el codigoServicio y usuarioId.");
  }

  @PostMapping
  public ResponseEntity<Map<String, Object>> crearTurno(@RequestBody @Valid CrearTurnoDTO nuevoTurno) {
    TurnoDTO turnoDTO = turnoUseCase.crearTurno(nuevoTurno);
    return ApiResponse.success(turnoDTO, HttpStatus.CREATED);
  }

  @PatchMapping("/atenciones/{atencionId}")
  public ResponseEntity<Map<String, Object>> actualizarAtencion(
    @PathVariable UUID atencionId,
    @RequestBody @Valid ModificarAtencionDTO modificarAtencionDTO
  ) {
    AtencionDTO atencionModificada = atencionUseCase.modificarAtencion(atencionId, modificarAtencionDTO);
    return ApiResponse.success(atencionModificada, HttpStatus.OK);
  }

}
