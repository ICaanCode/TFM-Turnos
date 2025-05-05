package com.unir.turnos.application.usecases;

import com.unir.turnos.api.dto.paciente.PacienteDTO;
import com.unir.turnos.api.dto.turno.BusquedaTurnoDTO;
import com.unir.turnos.api.dto.turno.CrearTurnoDTO;
import com.unir.turnos.api.dto.turno.TurnoDTO;
import com.unir.turnos.application.util.CodigoTurnoServicio;
import com.unir.turnos.application.util.ServicioServicio;
import com.unir.turnos.domain.model.Estado;
import com.unir.turnos.domain.model.Turno;
import com.unir.turnos.infraestructure.dto.paciente.PacienteDataDTO;
import com.unir.turnos.infraestructure.dto.servicio.CrearAtencionDTO;
import com.unir.turnos.infraestructure.feign.ServicioServiceFeignClient;
import com.unir.turnos.infraestructure.persistence.EstadoRepositoryImpl;
import com.unir.turnos.infraestructure.persistence.TurnoRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TurnoUseCase {

  private final EstadoRepositoryImpl estadoRepository;
  private final TurnoRepositoryImpl turnoRepository;

  private final ServicioServiceFeignClient servicioCliente;

  private final PacienteUseCase pacienteUseCase;

  private final CodigoTurnoServicio codigoTurnoServicio;
  private final ServicioServicio servicioServicio;

  private List<Integer> estadosCierre = Arrays.asList(31003, 31004);

  public List<TurnoDTO> obtenerTurno(BusquedaTurnoDTO busquedaTurno) {

    UUID pacienteId = null;
    Estado estado = null;

    if (busquedaTurno.getDocumentoPaciente() != null) {
      pacienteId = pacienteUseCase.obtenerPaciente(busquedaTurno.getDocumentoPaciente()).getIdPaciente();
    }

    if (busquedaTurno.getCodigoEstado() != null) {
      estado = estadoRepository.obtenerEstadoPorCodigo(busquedaTurno.getCodigoEstado());
    }

    List<Turno> turnos = turnoRepository.buscarTurnos(busquedaTurno.getCodigoTurno(), pacienteId, estado, busquedaTurno.getAntesDe(), busquedaTurno.getDespuesDe());
    List<TurnoDTO> turnoDTOs = turnos.stream().map(this::formatearTurno).filter(Objects::nonNull).collect(Collectors.toList());

    return turnoDTOs;

  }

  public Turno obtenerTurno(UUID turnoUUID) { return turnoRepository.obtenerTurnoPorUUID(turnoUUID); }

  public TurnoDTO crearTurno(CrearTurnoDTO crearTurnoDTO) {

    PacienteDataDTO pacienteData = pacienteUseCase.obtenerPaciente(crearTurnoDTO.getDocumentoPaciente());
    Estado estado = estadoRepository.obtenerEstadoPorCodigo(31001);

    String codigo = codigoTurnoServicio.generarCodigoTurno(pacienteData.getCondicion());

    Turno nuevoTurno = Turno.builder().codigo(codigo).pacienteId(pacienteData.getIdPaciente()).estado(estado).build();
    turnoRepository.guardarTurno(nuevoTurno);

    servicioServicio.validarServicio(crearTurnoDTO.getCodigoServicio());

    CrearAtencionDTO nuevaAtencion = new CrearAtencionDTO(nuevoTurno.getIdTurno(), null, crearTurnoDTO.getCodigoServicio(), crearTurnoDTO.getPrioridad());
    servicioCliente.crearAtencion(nuevaAtencion);

    return formatearTurno(nuevoTurno);

  }

  void modificarEstadoTurno(UUID turnoId, Integer codigoEstado) {
    Turno turno = obtenerTurno(turnoId);
    Estado nuevoEstado = estadoRepository.obtenerEstadoPorCodigo(codigoEstado);
    turno.setEstado(nuevoEstado);
    if (estadosCierre.contains(codigoEstado)) { turno.setFechaFinalizacion(LocalDateTime.now()); }
    turnoRepository.guardarTurno(turno);
  }

  private TurnoDTO formatearTurno(Turno turno) {

    try {

      PacienteDTO paciente = pacienteUseCase.obtenerPacienteDTO(turno.getPacienteId().toString());
      return new TurnoDTO(turno, paciente);

    } catch (Exception e) {

      return null;

    }

  }

}