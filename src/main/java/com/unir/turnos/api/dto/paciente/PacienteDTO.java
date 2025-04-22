package com.unir.turnos.api.dto.paciente;

import com.unir.turnos.infraestructure.dto.paciente.PacienteDataDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PacienteDTO {

  private String nombres;
  private String apellidos;
  private String identificacion;
  private Integer condicion;

  public PacienteDTO(PacienteDataDTO paciente) {
    this.nombres = paciente.getNombres();
    this.apellidos = paciente.getApellidos();
    this.identificacion = paciente.getIdentificacion();
    this.condicion = paciente.getCondicion();
  }

}
