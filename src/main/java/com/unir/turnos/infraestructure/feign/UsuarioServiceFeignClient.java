package com.unir.turnos.infraestructure.feign;

import com.unir.turnos.infraestructure.dto.ApiResponse;
import com.unir.turnos.infraestructure.dto.usuario.UsuarioDataDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "usuarios", configuration = FeignConfiguration.class)
public interface UsuarioServiceFeignClient {

  @GetMapping("/api/usuarios/{parametroBusqueda}")
  ApiResponse<UsuarioDataDTO> obtenerUsuario(@PathVariable("parametroBusqueda") String parametroBusqueda);
}
