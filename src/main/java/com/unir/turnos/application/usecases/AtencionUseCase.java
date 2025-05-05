package com.unir.turnos.application.usecases;

import com.unir.turnos.api.dto.paciente.PacienteDTO;
import com.unir.turnos.api.dto.servicio.AtencionDTO;
import com.unir.turnos.api.dto.servicio.BusquedaAtencionEsperaDTO;
import com.unir.turnos.api.dto.servicio.ModificarAtencionDTO;
import com.unir.turnos.api.dto.usuario.UsuarioDTO;
import com.unir.turnos.application.util.CondicionServicio;
import com.unir.turnos.application.util.ServicioServicio;
import com.unir.turnos.domain.model.Turno;
import com.unir.turnos.infraestructure.config.CustomLogger;
import com.unir.turnos.infraestructure.dto.servicio.AtencionDataDTO;
import com.unir.turnos.infraestructure.dto.servicio.CrearAtencionDTO;
import com.unir.turnos.infraestructure.dto.servicio.ModificacionAtencionDTO;
import com.unir.turnos.infraestructure.dto.usuario.UsuarioDataDTO;
import com.unir.turnos.infraestructure.feign.ServicioServiceFeignClient;
import com.unir.turnos.infraestructure.feign.UsuarioServiceFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AtencionUseCase {

  private final ServicioServiceFeignClient servicioCliente;
  private final UsuarioServiceFeignClient usuarioCliente;

  private final PacienteUseCase pacienteUseCase;
  private final TurnoUseCase turnoUseCase;

  private final ServicioServicio servicioServicio;
  private final CondicionServicio condicionServicio;

  public List<AtencionDTO> obtenerAtenciones(BusquedaAtencionEsperaDTO busquedaAtencionEsperaDTO) {

    Integer codigoServicio = busquedaAtencionEsperaDTO.getCodigoServicio();
    UUID usuarioId = busquedaAtencionEsperaDTO.getUsuarioId() != null ? UUID.fromString(busquedaAtencionEsperaDTO.getUsuarioId()) : null;

    servicioServicio.validarServicio(codigoServicio);

    List<AtencionDataDTO> atencionesPorServicio = servicioCliente.obtenerAtenciones(null, codigoServicio, true, usuarioId, null, null).getData();
    List<AtencionDTO> atenciones = atencionesPorServicio.stream().map(this::formatearAtencion).collect(Collectors.toList());

    atenciones.sort(
        Comparator
            .comparingInt(AtencionDTO::getPrioridad)
            .thenComparing(
                Comparator.comparingDouble((AtencionDTO a) -> condicionServicio.getCondicion(a.getPaciente().getCondicion()).getMultiplicador()).reversed()
            )
            .thenComparing(AtencionDTO::getFechaCreacion)
    );

    return atenciones;

  }

  public AtencionDTO modificarAtencion(UUID atencionId, ModificarAtencionDTO modificarAtencionDTO) {
    usuarioCliente.obtenerUsuario(modificarAtencionDTO.getUsuarioId());
    ModificacionAtencionDTO modificaciones = new ModificacionAtencionDTO(UUID.fromString(modificarAtencionDTO.getUsuarioId()));
    AtencionDataDTO atencion = servicioCliente.modificarAtencion(atencionId, modificaciones).getData();
    if (modificarAtencionDTO.getCodigoServicio() != null) {
      servicioServicio.validarServicio(modificarAtencionDTO.getCodigoServicio());
      CrearAtencionDTO nuevaAtencion = new CrearAtencionDTO(atencion.getTurnoId(), null, modificarAtencionDTO.getCodigoServicio(), modificarAtencionDTO.getPrioridad());
      servicioCliente.crearAtencion(nuevaAtencion);
    }
    Integer estadoCierre = (!modificarAtencionDTO.getAbandonada()) ? ((modificarAtencionDTO.getCodigoServicio() != null) ? 31001 : 31003) : 31004;
    Integer nuevoEstadoTurno = (atencion.getFechaFinalizacion() != null) ? estadoCierre : 31002;

    turnoUseCase.modificarEstadoTurno(atencion.getTurnoId(), nuevoEstadoTurno);
    return formatearAtencion(atencion);
  }

  public List<AtencionDTO> obtenerAtencionesEnProgreso() {
    List<AtencionDataDTO> atencionesEnProgreso = servicioCliente.obtenerAtenciones(null, null, false, null, null, null).getData();
    return atencionesEnProgreso.stream().map(this::formatearAtencion).collect(Collectors.toList());
  }

  private AtencionDTO formatearAtencion(AtencionDataDTO atencion) {
    try {
      UsuarioDTO usuarioDTO = null;
      if (atencion.getUsuarioId() != null) {
        UsuarioDataDTO usuario = usuarioCliente.obtenerUsuario(atencion.getUsuarioId().toString()).getData();
        usuarioDTO = new UsuarioDTO(usuario.getNombres(), usuario.getApellidos(), usuario.getUsername());
      }
      Turno turno = turnoUseCase.obtenerTurno(atencion.getTurnoId());
      PacienteDTO paciente = pacienteUseCase.obtenerPacienteDTO(turno.getPacienteId().toString());
      return new AtencionDTO(atencion.getIdAtencion(), turno.getCodigo(), atencion.getPrioridad(), usuarioDTO, atencion.getServicio(), paciente, atencion.getFechaCreacion(), atencion.getFechaInicio(), atencion.getFechaFinalizacion());
    } catch (Exception e) {
      CustomLogger.error(String.format("Error al recuperar la atención con UUID '%s': %s", atencion.getIdAtencion(), e.getMessage()));
      return null;
    }
  }


}
