package com.unir.turnos.infraestructure.dto.usuario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDataDTO {

  private String nombres;
  private String apellidos;
  private String email;
  private String identificacion;
  private Integer rol;
  private String username;

}
