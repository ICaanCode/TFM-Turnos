package com.unir.turnos.api.dto.servicio;

import com.unir.turnos.api.dto.paciente.PacienteDTO;
import com.unir.turnos.api.dto.usuario.UsuarioDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AtencionDTO {

  private UUID idAtencion;
  private String codigoTurno;
  private Integer prioridad;
  private UsuarioDTO usuario;
  private Integer servicio;
  private PacienteDTO paciente;
  private LocalDateTime fechaCreacion;
  private LocalDateTime fechaInicio;
  private LocalDateTime fechaFinalizacion;

}
