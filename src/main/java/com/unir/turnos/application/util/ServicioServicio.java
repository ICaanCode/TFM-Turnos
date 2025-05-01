package com.unir.turnos.application.util;

import com.unir.turnos.application.dto.Servicio;
import com.unir.turnos.domain.exception.InactiveServiceException;
import com.unir.turnos.infraestructure.dto.servicio.ServicioDataDTO;
import com.unir.turnos.infraestructure.feign.ServicioServiceFeignClient;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServicioServicio {

  private final ServicioServiceFeignClient servicioService;

  private Map<Integer, Servicio> servicios = new HashMap<Integer, Servicio>();

  @PostConstruct
  public void init() {
    List<ServicioDataDTO> serviciosData = servicioService.obtenerServicios().getData();
    this.servicios = serviciosData.stream().collect(Collectors.toMap(ServicioDataDTO::getCodigo, Servicio::new));
  }

  public Servicio validarServicio(Integer codigoServicio) {
    Servicio servicio = Optional.ofNullable(servicios.get(codigoServicio)).orElseThrow(() -> new EntityNotFoundException(String.format("El servicio con código '%d' no existe.", codigoServicio)));
    if (servicio.getActivo() != true) throw new InactiveServiceException(String.format("El servicio '%s' está inactivo.", servicio.getNombre()));
    return servicio;
  }

}
