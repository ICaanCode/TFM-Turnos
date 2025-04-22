package com.unir.turnos.infraestructure.feign;

import com.unir.turnos.infraestructure.dto.ApiResponse;
import com.unir.turnos.infraestructure.dto.paciente.CondicionDataDTO;
import com.unir.turnos.infraestructure.dto.paciente.PacienteDataDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(
    name = "pacientes",
    configuration = FeignConfiguration.class
)
public interface PacienteServiceFeignClient {

  @GetMapping("/api/pacientes/{id}")
  ApiResponse<PacienteDataDTO> obtenerPaciente(@PathVariable("id") String id);

  @GetMapping("/api/condiciones")
  ApiResponse<List<CondicionDataDTO>> obtenerCondiciones();

}
