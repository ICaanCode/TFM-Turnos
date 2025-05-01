package com.unir.turnos.infraestructure.dto.usuario;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UsuarioDataDTO {

  private String nombres;
  private String apellidos;
  private String email;
  private String identificacion;
  private Integer rol;
  private String username;
  private List<ServicioUsuarioDataDTO> servicios;
  private LocalDateTime fechaCreacion;
  private LocalDateTime fechaModificacion;

}
