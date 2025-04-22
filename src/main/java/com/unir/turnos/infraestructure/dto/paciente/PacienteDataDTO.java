package com.unir.turnos.infraestructure.dto.paciente;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class PacienteDataDTO {

  private UUID idPaciente;
  private String nombres;
  private String apellidos;
  private LocalDate fechaNacimiento;
  private String telefono;
  private String identificacion;
  private Integer condicion;

}
