package com.unir.turnos.application.util;

import com.unir.turnos.application.dto.Condicion;
import com.unir.turnos.infraestructure.dto.paciente.CondicionDataDTO;
import com.unir.turnos.infraestructure.feign.PacienteServiceFeignClient;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CondicionServicio {

  private final PacienteServiceFeignClient pacienteService;

  private Map<Integer, Condicion> condiciones = new HashMap<Integer, Condicion>();

  @PostConstruct
  public void init() {
    List<CondicionDataDTO> condicionesData = pacienteService.obtenerCondiciones().getData();
    this.condiciones = condicionesData.stream().collect(Collectors.toMap(CondicionDataDTO::getCodigo, c -> new Condicion(c.getDescripcion(), c.getPrioritario(), c.getMultiplicador())));
  }

  public Condicion getCondicion(Integer codigo) {
    return condiciones.get(codigo);
  }

}
