package com.unir.turnos.application.usecases;

import com.unir.turnos.api.dto.paciente.PacienteDTO;
import com.unir.turnos.infraestructure.dto.paciente.PacienteDataDTO;
import com.unir.turnos.infraestructure.feign.PacienteServiceFeignClient;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PacienteUseCase {

  private final PacienteServiceFeignClient pacienteCliente;

  public PacienteDataDTO obtenerPaciente(String documentoPaciente) {
    try {
      return pacienteCliente.obtenerPaciente(documentoPaciente).getData();
    } catch (EntityNotFoundException e) {
      throw new EntityNotFoundException(String.format("Paciente con id '%s' no encontrado.", documentoPaciente));
    }
  }

  public PacienteDTO obtenerPacienteDTO(String documentoPaciente) {
    PacienteDataDTO pacienteData = pacienteCliente.obtenerPaciente(documentoPaciente).getData();
    return new PacienteDTO(pacienteData);
  }

}
